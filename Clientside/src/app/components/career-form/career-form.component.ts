import { Component, ViewChild } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-career-form',
  templateUrl: './career-form.component.html',
  styleUrls: ['./career-form.component.scss'],
})
export class CareerFormComponent {
  @ViewChild('careerform', { static: false }) careerform!: NgForm;
  end_date!: string;
  start_date!: string;
  description!: string;
  companyName!: string;
  title!: string;
  userId!: string;
  constructor(private http: HttpServiceService) {}
  url!: string;
  ngOnInit() {
    const storedData = localStorage.getItem('userData');

    if (storedData) {
      const parsedData = JSON.parse(storedData);
      this.userId = parsedData.id;
      // Use the parsed data in your application
      this.url = this.http.serverUrl + 'career/' + this.userId + '/create';
    }
  }
  submit() {
    console.log('Form Submitted !', this.careerform.value);
    this.http.postData(this.url, this.careerform.value).subscribe({
      next: (response) => {
        console.log('POST request successful:', response);
        console.log(this.careerform.value);
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
