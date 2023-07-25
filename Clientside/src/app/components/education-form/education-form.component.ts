import { Component, ViewChild } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-education-form',
  templateUrl: './education-form.component.html',
  styleUrls: ['./education-form.component.scss'],
})
export class EducationFormComponent {
  @ViewChild('eduform', { static: false }) eduform!: NgForm;
  school_name!: string;
  grade!: string;
  course!: string;
  end_date!: string;
  start_date!: string;
  userId!: string;
  constructor(private http: HttpServiceService) {}
  url!: string;
  ngOnInit() {
    const storedData = localStorage.getItem('userData');

    if (storedData) {
      const parsedData = JSON.parse(storedData);
      this.userId = parsedData.id;
      // Use the parsed data in your application
      this.url = this.http.serverUrl + 'education/' + this.userId + '/create';
    }
  }
  submit() {
    console.log('Form Submitted !', this.eduform.value);
    this.http.postData(this.url, this.eduform.value).subscribe({
      next: (response) => {
        console.log('POST request successful:', response);
        console.log(this.eduform.value);
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
