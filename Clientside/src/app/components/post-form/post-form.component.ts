import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { MatDialogRef } from '@angular/material/dialog';
@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.scss'],
})
export class PostFormComponent {
  userimageUrl!: string;
  imageUrl!: string;
  addFeedUrl!: string;
  selectedImage!: File;

  constructor(
    private http: HttpServiceService,
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<PostFormComponent>
  ) {}
  postForm = this.fb.group({
    description: [''],
    file: [null],
  });

  ngOnInit() {
    const userImageData = localStorage.getItem('userImageData');
    const userData = localStorage.getItem('userData');
    if (userImageData) {
      this.userimageUrl = JSON.parse(userImageData);
      console.log('userImageData', typeof this.userimageUrl);
    }
    if (userData) {
      const userdata = JSON.parse(userData);
      console.log('userData', userdata.id.toString());
      this.addFeedUrl =
        this.http.serverUrl + 'feeds/v2/' + userdata.id.toString() + '/add';
    }
  }
  // method to handle selected image
  onFileChange(event: any) {
    if (event.target.files && event.target.files.length) {
      this.selectedImage = event.target.files[0];
      this.postForm.get('file')?.setValue(this.selectedImage as any);
      const reader = new FileReader();
      reader.readAsDataURL(this.selectedImage);
      reader.onload = (e: any) => {
        this.imageUrl = e.target.result;
      };
    }
  }

  // submit
  submit() {
    console.log('feed data', this.postForm.value);
    // instantiate form data object
    const formData = new FormData();
    // add all the activityForm control to the form data object
    Object.keys(this.postForm.controls).forEach((controlName) => {
      formData.append(controlName, this.postForm.get(controlName)?.value);
    });

    this.http.postData(this.addFeedUrl, formData).subscribe({
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
