import { Component } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.scss'],
})
export class UserRegisterComponent {
  constructor(private http: HttpServiceService, private router: Router) {}
  firstName!: string;
  secondName!: string;
  email!: string;
  password!: string;
  confirmPassword!: string;
  code!: string;
  userRegData!: any;
  url = this.http.serverUrl + 'auth/register';
  submit(register: any) {
    this.userRegData = register;
    console.log('Form Submitted !', register);
    this.http.postData(this.url, this.userRegData).subscribe({
      next: (response) => {
        console.log('POST request successful:', response);
        this.router.navigate(['/']);
        console.log(register);
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
