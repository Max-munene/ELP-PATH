import { Component, Inject } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { countries } from 'src/assets/json_files/countries';
import { cities } from 'src/assets/json_files/cities';

@Component({
  selector: 'app-profile-form',
  templateUrl: './profile-form.component.html',
  styleUrls: ['./profile-form.component.scss'],
})
export class ProfileFormComponent {
  // Define class properties
  profileData!: any;
  userId!: string;
  urlAddProfile!: string;
  urlUpdateProfile!: string;
  urlGetProfile!: string;
  imageUrl!: string;
  selectedImage!: File;
  profileForm!: FormGroup;
  compare: string = 'technology';

  // Define job status options
  jobStatus: any[] = [
    { value: 'Employed', viewValue: 'Employed' },
    { value: 'Intern', viewValue: 'Intern' },
    { value: 'Not Employed', viewValue: 'Not Employed' },
    { value: 'Self-Employed', viewValue: 'Self-Employed' },
  ];

  // Define country options
  country = cities;

  // Define courses as an empty array

  constructor(
    private http: HttpServiceService,
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<ProfileFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any //data from the component where the dialogue is invoked
  ) {
    // Create a reactive form using FormBuilder
    this.profileForm = this.fb.group({
      title: [''],
      website: [''],
      status: [this.jobStatus],
      country: [this.country],
      phoneNo: [null],
      email: [''],
      profileImageFile: [null],
    });
  }
  // Subscribe to value changes in the form
  // Initialize function runs when the component is first loaded in the DOM
  ngOnInit() {
    // Retrieve user data from local storage
    const userData = localStorage.getItem('userData');
    console.log('userdata', this.data.data.editAdd);

    if (this.data.data.editAdd) {
      // Parse user data

      this.userId = this.data.data.id;
    } else if (userData) {
      const parsedData = JSON.parse(userData);
      this.userId = parsedData.id;
    }
    this.getprofileData();
  }

  // method to handle selected image
  onFileChange(event: any) {
    if (event.target.files && event.target.files.length) {
      this.selectedImage = event.target.files[0];
      this.profileForm
        .get('profileImageFile')
        ?.setValue(this.selectedImage as any);
      console.log(this.profileForm.value.profileImageFile);
      const reader = new FileReader();
      reader.readAsDataURL(this.selectedImage);
      reader.onload = (e: any) => {
        this.imageUrl = e.target.result;
      };
    }
  }

  // method to Fetch profile data from the server
  getprofileData() {
    // Set URLs to get profile data
    this.urlGetProfile =
      this.http.serverUrl + 'profile/' + this.userId + '/view';
    // Fetch profile data from the server
    this.http.getData(this.urlGetProfile).subscribe({
      next: (response) => {
        this.profileData = response.payload;
        console.log('ProfileData', this.profileData);

        if (
          this.profileData &&
          this.profileData.profile &&
          this.profileData.profile.id
        ) {
          // Set URLs for updating profile data
          this.urlUpdateProfile =
            this.http.serverUrl +
            'profile/' +
            this.profileData.profile.id +
            '/update';
        } else {
          console.error('Profile data or profile id is undefined');
        }

        // Prepopulate the form with data from the API response
        this.profileForm.patchValue({
          title: this.profileData.title,
          website: this.profileData.website,
          phoneNo: this.profileData.phoneNo,
          email: this.profileData.email,
        });

        // Convert and display profile image
        if (this.profileData.profileImage !== null) {
          // this.convertTofile();
          this.imageUrl = 'data:' + this.profileData.profileImage;
        }
      },
      error: (error) => {
        console.log('Error:', error);

        // Handle the error here
      },
      complete: () => {},
    });
  }

  // Method to submit the form data
  submit() {
    console.log('profile form data', this.profileForm.value);

    const formData = new FormData();

    // add all the activityForm control to the form data object
    Object.keys(this.profileForm.controls).forEach((controlName) => {
      formData.append(controlName, this.profileForm.get(controlName)?.value);
    });

    // =================================add profile================================================

    if (this.data.data.editAdd === 'add' || this.data.data === 'add') {
      // Set URLs for adding profile data
      this.urlAddProfile =
        this.http.serverUrl + 'profile/' + this.userId + '/create';
      this.http.postData(this.urlAddProfile, formData).subscribe({
        next: () => {
          this.dialogRef.close();
        },
        error: (error) => {
          console.log('Error:', error);
          // this.dialogRef.close();
          // Handle the error here
        },
        complete: () => {},
      });
    }

    // =====================================edit profle====================================
    else if (this.data.data.editAdd === 'edit' || this.data.data === 'edit') {
      this.urlUpdateProfile =
        this.http.serverUrl +
        'profile/' +
        this.profileData.profile.id +
        '/update';
    } else {
      this.http.putData(this.urlUpdateProfile, formData).subscribe({
        next: () => {
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
