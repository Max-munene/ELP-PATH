import { Component } from '@angular/core';
import { EducationFormComponent } from '../education-form/education-form.component';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { HttpServiceService } from 'src/app/services/http-service.service';
@Component({
  selector: 'app-education',
  templateUrl: './education.component.html',
  styleUrls: ['./education.component.scss'],
})
export class EducationComponent {
  constructor(public dialog: MatDialog, public http: HttpServiceService) {}
  userId!: string;
  url!: string;
  rowData!: any[];
  ngOnInit() {
    const storedData = localStorage.getItem('userData');

    if (storedData) {
      const parsedData = JSON.parse(storedData);
      this.userId = parsedData.id;
      // Use the parsed data in your application
      this.url = this.http.serverUrl + 'education/' + this.userId + '/view';

      this.http.getData(this.url).subscribe({
        next: (response) => {
          this.rowData = response;
          console.log('POST request successful:', response);

          // Handle the response data here
          // localStorage.setItem('token', JSON.stringify(response));
        },
        error: (error) => {
          console.log('Error:', error);
          // Handle the error here
        },
        complete: () => {},
      });
    }
  }

  // open eduaction form dialog
  eformDialog(): void {
    // Open the dialog using the MatDialog service
    const dialogRef: MatDialogRef<EducationFormComponent> = this.dialog.open(
      EducationFormComponent,
      {
        width: '60%', // Set the width of the dialog

        data: { name: 'John' }, // You can pass data to the dialog component using the `data` property
      }
    );

    // Handle the dialog result (if needed)
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      this.ngOnInit();
    });
  }
}
