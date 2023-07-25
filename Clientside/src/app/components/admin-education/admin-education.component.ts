import { Component, Input } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { AdminEducationFormComponent } from '../admin-education-form/admin-education-form.component';
@Component({
  selector: 'app-admin-education',
  templateUrl: './admin-education.component.html',
  styleUrls: ['./admin-education.component.scss'],
})
export class AdminEducationComponent {
  constructor(public dialog: MatDialog, public http: HttpServiceService) {}
  @Input() applicantid!: string;
  url!: string;
  educationdata!: any;
  userId!: string;

  ngOnInit() {
    console.log(this.applicantid.toString());
    this.url =
      this.http.serverUrl +
      'scholar/education/' +
      this.applicantid.toString() +
      '/view';

    this.http.getData(this.url).subscribe({
      next: (response) => {
        this.educationdata = response;
        console.log('POST request successful:', response);

        // Handle the response data here
        // localStorage.setItem('token', JSON.stringify(response));
      },
      error: (error) => {
        console.log('Error:', error.message);
        // Handle the error here
      },
      complete: () => {},
    });
    // }
  }
  // open bio dialog
  eduformDialog(): void {
    // Open the dialog using the MatDialog service
    const dialogRef: MatDialogRef<AdminEducationFormComponent> =
      this.dialog.open(AdminEducationFormComponent, {
        width: '60%', // Set the width of the dialog

        data: { data: this.applicantid.toString() }, // You can pass data to the dialog component using the `data` property
      });

    // Handle the dialog result (if needed)
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      this.ngOnInit();
    });
  }
}
