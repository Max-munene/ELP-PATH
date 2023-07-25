import { Component, Inject } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
@Component({
  selector: 'app-profile-form',
  templateUrl: './profile-form.component.html',
  styleUrls: ['./profile-form.component.scss'],
})
export class ProfileFormComponent {
  profileData!: any;
  userId!: string;
  urlAddProfile!: string;
  urlUpdateProfile!: string;
  urlGetProfile!: string;
  constructor(
    private http: HttpServiceService,
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<ProfileFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any //data from the component where the dialogue is invoked
  ) {}

  // reactive form ....for builder
  profileForm = this.fb.group({
    title: [''],
    website: [''],
    phoneNo: [''],
    email: [''],
  });

  //intialize function runs when the component is first loaded in the dom
  ngOnInit() {
    const userData = localStorage.getItem('userData');
    if (userData) {
      const parsedData = JSON.parse(userData);
      this.userId = parsedData.id;
      this.urlGetProfile =
        this.http.serverUrl + 'profile/' + this.userId + '/view';
      this.http.getData(this.urlGetProfile).subscribe({
        next: (response) => {
          if (response.length !== 0) {
            this.profileData = response[0];
            console.log(this.profileData);
          }
          if (this.profileData) {
            this.urlUpdateProfile =
              this.http.serverUrl + 'profile/' + this.userId + '/update';
          } else if (!this.profileData) {
            this.urlAddProfile =
              this.http.serverUrl + 'profile/' + this.userId + '/add';
          }

          // prepopulate the form with data from the api response
          this.profileForm.patchValue({
            title: this.profileData.title,
            website: this.profileData.website,
            phoneNo: this.profileData.phoneNo,
            email: this.profileData.email,
          });
        },
        error: (error) => {
          console.log('Error:', error);
          // Handle the error here
        },
        complete: () => {},
      });
    }
  }
  submit() {
    console.log(this.urlUpdateProfile);
    // =================================add profile================================================

    if (this.data.data === 'add') {
      this.http.postData(this.urlAddProfile, this.profileForm.value).subscribe({
        next: (response) => {
          console.log(this.profileForm.value);
        },
        error: (error) => {
          console.log('Error:', error);
          // Handle the error here
        },
        complete: () => {},
      });
    }

    // =====================================edit profle====================================
    else if (this.data.data === 'edit') {
      console.log(this.urlUpdateProfile);
      this.http
        .putData(this.urlUpdateProfile, this.profileForm.value)
        .subscribe({
          next: (response) => {
            console.log(this.profileForm.value);
            this.dialogRef.close();
          },
          error: (response) => {
            console.log('Error:', response);
          },
          complete: () => {},
        });
    }
  }
}
