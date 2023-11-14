import { Component, Inject } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-add-admin-form',
  templateUrl: './add-admin-form.component.html',
  styleUrls: ['./add-admin-form.component.scss'],
})
export class AddAdminFormComponent {
  // Declare class properties
  userId!: string;
  url!: string;
  urlGetroles!: string;
  urlGetSchool!: string;
  urlLinkApplicationSchool!: string;
  adminform: FormGroup; // Form group for the application form

  // Initialize roles and school options arrays
  rolesOptions!: any[];
  schoolOptions!: any[];

  constructor(
    private http: HttpServiceService,

    private fb: FormBuilder,
    public dialogRef: MatDialogRef<AddAdminFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    // Initialize the application form using FormBuilder
    this.adminform = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      userEmail: ['', [Validators.required, Validators.email]],
      userPassword: ['', Validators.required],

      role: this.fb.group({
        id: ['', Validators.required],
      }),
    });
  }

  ngOnInit() {
    // Initialize URL for HTTP request
    this.url = this.http.serverUrl + 'auth/register-admin';

    // Fetch roles options from the server
    this.getRole();
    this.http
      .postData(' http://192.168.100.147:3324/auth/authenticate', {
        // firstName: 'nigga',
        // lastName: 'hyui',
        // role: 'stuff',
        email: 'bruh30@gmail.com',
        password: '123456',
      })
      .subscribe({
        next: () => console.log('bruh is happy'),
        error: (err) => {
          console.log('bruh is miserable');
          console.log(err);
        },
      });
  }

  // Fetch roles data from the server
  getRole() {
    this.urlGetroles = this.http.serverUrl + 'roles/all'; // URL to fetch roles data
    this.http.getData(this.urlGetroles).subscribe({
      next: (response) => {
        console.log(response);
        this.rolesOptions = response.data; // Set roles options array
        console.log(this.rolesOptions);
      },
      error: (error) => {
        console.log('Error:', error);
      },
      complete: () => {},
    });
  }

  // Handle form submission
  submit() {
    console.log(this.adminform.value);
    // Submit application form data to the server

    this.http.postData(this.url, this.adminform.value).subscribe({
      next: (response) => {
        console.log('POST request successful:', response);

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
