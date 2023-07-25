import { Component, ViewChild } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-bio-form',
  templateUrl: './bio-form.component.html',
  styleUrls: ['./bio-form.component.scss'],
})
export class BioFormComponent {
  constructor(private http: HttpServiceService) {}
  @ViewChild('bio', { static: false }) bio!: NgForm;
  url!: string;
  userId!: string;
  textareaData!: string;
  ngOnInit() {
    const storedData = localStorage.getItem('userData');

    if (storedData) {
      const parsedData = JSON.parse(storedData);
      this.userId = parsedData.id;
      // Use the parsed data in your application
      this.url = this.http.serverUrl + 'bio/' + this.userId + '/add';
    }
  }
  submit() {
    console.log(this.bio.value);
    this.http.postData(this.url, this.bio.value).subscribe({
      next: (response) => {
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
