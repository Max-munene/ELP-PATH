import { Component, Input } from '@angular/core';
import { BioFormComponent } from '../bio-form/bio-form.component';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { HttpServiceService } from 'src/app/services/http-service.service';
@Component({
  selector: 'app-bio',
  templateUrl: './bio.component.html',
  styleUrls: ['./bio.component.scss'],
})
export class BioComponent {
  constructor(public dialog: MatDialog, public http: HttpServiceService) {}
  @Input() userIdadmin!: string;
  @Input() viewer!: string;
  url!: string;
  biodata!: any[];
  userId!: string;

  ngOnInit() {
    const storedData = localStorage.getItem('userData');

    if (this.userIdadmin) {
      console.log('useridadmin', this.userIdadmin.toString());
      this.userId = this.userIdadmin.toString();
    } else if (storedData) {
      const parsedData = JSON.parse(storedData);
      this.userId = parsedData.id;
    }
    // Use the parsed data in your application
    this.url = this.http.serverUrl + 'bio/' + this.userId + '/view';
    this.http.getData(this.url).subscribe({
      next: (response) => {
        this.biodata = response.payload.description;
        console.log(response);

        // Handle the response data here
        // localStorage.setItem('token', JSON.stringify(response));
      },
      error: (error) => {
        console.log('Error:', error.message);
        // Handle the error here
      },
      complete: () => {},
    });
  }
  // open bio dialog
  bformDialog(editAdd: string): void {
    // Open the dialog using the MatDialog service
    const dialogRef: MatDialogRef<BioFormComponent> = this.dialog.open(
      BioFormComponent,
      {
        width: '50%', // Set the width of the dialog

        data: { data: editAdd }, // You can pass data to the dialog component using the `data` property
      }
    );

    // Handle the dialog result (if needed)
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      this.ngOnInit();
    });
  }
}
