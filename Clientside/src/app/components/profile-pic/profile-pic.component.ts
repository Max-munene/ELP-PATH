import { Component, Inject } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
@Component({
  selector: 'app-profile-pic',
  templateUrl: './profile-pic.component.html',
  styleUrls: ['./profile-pic.component.scss'],
})
export class ProfilePicComponent {
  constructor(
    public http: HttpServiceService,
    public dialogRef: MatDialogRef<ProfilePicComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}
  url!: string;
  userId!: string;
  imageId!: string;
  file!: File;
  formData = new FormData();
  urlAddImage!: string;
  urlUpdateImage!: string;
  urlGetImage!: string;
  ngOnInit() {
    const storedData = localStorage.getItem('userData');

    if (storedData) {
      const parsedData = JSON.parse(storedData);
      this.userId = parsedData.id;
      // Use the parsed data in your application
      this.urlGetImage = this.http.serverUrl + 'image/' + this.userId + '/view';
      // ============================get image data (url)================================================
      this.http.getImage(this.urlGetImage).subscribe({
        next: (response: any) => {
          const responsedata: any = JSON.parse(response);
          this.urlUpdateImage =
            this.http.serverUrl + 'image/' + this.userId + '/update';
          console.log(responsedata);
        },
        error: (error) => {
          if (error.error === 'Image not found.') {
            console.log(error.error);
            this.urlAddImage =
              this.http.serverUrl + 'image/' + this.userId + '/add';
          }
          // Handle the error here
        },
        complete: () => {},
      });
      console.log(this.data);
    }
  }
  selectedFile(event: any) {
    const reader = new FileReader();
    this.file = event.target.files[0];
    this.formData.append('file', this.file);
    console.log(this.file);
    reader.readAsDataURL(this.file);
    reader.onload = (event: any) => {
      this.url = event.target.result;
    };
  }

  submit() {
    if (this.data.data === 'edit') {
      this.http.putData(this.urlUpdateImage, this.formData).subscribe({
        next: (response) => {
          console.log('Put Request successful');
          this.dialogRef.close();
        },
        error: (error) => {
          console.log('Error:', error);
          // Handle the error here
        },
        complete: () => {},
      });
    } else {
      this.http.postData(this.urlAddImage, this.formData).subscribe({
        next: (response) => {
          console.log('Post Request successful');

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
