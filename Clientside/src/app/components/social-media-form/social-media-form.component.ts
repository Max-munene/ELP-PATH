import { Component, Inject } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { FormBuilder } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
@Component({
  selector: 'app-social-media-form',
  templateUrl: './social-media-form.component.html',
  styleUrls: ['./social-media-form.component.scss'],
})
export class SocialMediaFormComponent {
  socialdata!: any;

  url!: string;
  // reactive form ....for builder
  socialForm = this.fb.group({
    facebook: [''],
    github: [''],
    instagram: [''],
    linkedIn: [''],
    twitter: [''],
  });

  urlAddSocials = this.http.serverUrl + 'socials/1/add';
  urlUpdateSocials!: string;
  userId!: string;
  socialId!: string;
  constructor(
    private http: HttpServiceService,
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<SocialMediaFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  ngOnInit() {
    //========================= set initial values====================================================
    const userData = localStorage.getItem('userData');
    if (userData) {
      const parsedData = JSON.parse(userData);
      this.userId = parsedData.id;
      this.url = this.http.serverUrl + 'socials/' + this.userId + '/view';
      this.http.getData(this.url).subscribe({
        next: (response) => {
          if (response.length !== 0) {
            this.socialdata = response[0];
          }
          // ==========================check if response data is empty or not===========================
          if (this.socialdata) {
            this.urlUpdateSocials =
              this.http.serverUrl + 'socials/' + this.userId + '/update';
          } else if (!this.socialdata) {
            this.urlAddSocials =
              this.http.serverUrl + 'socials/' + this.userId + '/add';
          }

          // ===========================prepopulate the form with data from the api response================
          this.socialForm.patchValue({
            facebook: this.socialdata.facebook,
            github: this.socialdata.github,
            linkedIn: this.socialdata.linkedin,
            twitter: this.socialdata.twitter,
            instagram: this.socialdata.instagram,
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
    console.log(this.socialForm.value);
    //=============================edit social medial====================================
    if (this.data.data === 'edit') {
      this.http
        .putData(this.urlUpdateSocials, this.socialForm.value)
        .subscribe({
          next: (response) => {
            this.dialogRef.close();
          },
          error: (error) => {
            console.log('Error:', error);
            // Handle the error here
          },
          complete: () => {},
        });
    }
    //===================================add user social media ===========================
    else if (this.data.data === 'add') {
      this.http.postData(this.urlAddSocials, this.socialForm.value).subscribe({
        next: (response) => {
          this.dialogRef.close();
        },
        error: (error) => {
          console.log('Error:', error);
          // Handle the error here
        },
        complete: () => {},
      });
    }
  }
}
