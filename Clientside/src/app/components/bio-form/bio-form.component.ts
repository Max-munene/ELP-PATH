import { Component, ViewChild, Inject } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { NgForm } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-bio-form',
  templateUrl: './bio-form.component.html',
  styleUrls: ['./bio-form.component.scss'],
})
export class BioFormComponent {
  constructor(
    private http: HttpServiceService,
    public dialogRef: MatDialogRef<BioFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}
  @ViewChild('bio', { static: false }) bio!: NgForm;
  url!: string;
  userId!: string;
  textareaData!: string;
  ngOnInit() {
    console.log('user id in bio', this.data.data.toString());

    if (this.data.data) {
      const userData = localStorage.getItem('userData');
      if (userData) {
        const parsedData = JSON.parse(userData);
        // Use the parsed data in your application
        this.userId = parsedData.id;

        this.url = this.http.serverUrl + 'bio/' + this.userId + '/add';
      }
    }
  }
  submit() {
    console.log(this.bio.value);
    this.http.postData(this.url, this.bio.value).subscribe({
      next: (response) => {
        console.log('POST request successful:', response);
        // Handle the response data here
        // localStorage.setItem('token', JSON.stringify(response));
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
