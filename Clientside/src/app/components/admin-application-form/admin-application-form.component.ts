import { Component } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-admin-application-form',
  templateUrl: './admin-application-form.component.html',
  styleUrls: ['./admin-application-form.component.scss'],
})
export class AdminApplicationFormComponent {
  userId!: string;
  //applications status options
  applicationstatus: any[] = [
    { value: 'AWAITING', viewValue: 'Awaiting' },
    { value: 'AWARDED', viewValue: 'Awarded' },
    { value: 'NOT_AWARDED', viewValue: 'Not Awarded' },
  ];
  //branch options
  branchOptions: any[] = [
    { value: 'NAIROBI', viewValue: 'NAIROBI' },
    { value: 'KISUMU', viewValue: 'KISUMU' },
    { value: 'NAKURU', viewValue: 'NAKURU' },
    { value: 'MACHAKOS', viewValue: 'MACHAKOS' },
    { value: 'MOMBASA', viewValue: 'MOMBASA' },
    { value: 'THIKA', viewValue: 'THIKA' },
    { value: 'JUJA', viewValue: 'JUJA' },
    { value: 'SAMBURU', viewValue: 'SAMBURU' },
    { value: 'NYERI', viewValue: 'NYERI' },
    { value: 'KIAMBU', viewValue: 'KIAMBU' },
    { value: 'MERU', viewValue: 'MERU' },
  ];
  constructor(
    private http: HttpServiceService,
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<AdminApplicationFormComponent>
  ) {}
  // form builder
  applicationform = this.fb.group({
    applicantFirstName: [''],
    applicantLastName: [''],
    applicationStatus: [''],
    dateOfApplication: [''],
    dateOfAwarding: [''],
    dateOfInterview: [''],
    branch: [''],
  });

  url!: string;
  ngOnInit() {
    this.url = this.http.serverUrl + 'applications/add-new-application';
  }

  submit() {
    console.log(this.applicationform.value);
    this.http.postData(this.url, this.applicationform.value).subscribe({
      next: (response) => {
        console.log('POST request successful:', response);
        console.log(this.applicationform.value);
        this.dialogRef.close();

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
