import { Component } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss'],
})
export class UserLoginComponent {
  constructor(private http: HttpServiceService, private router: Router) {}
  email!: string;
  password!: string;
  url = this.http.serverUrl + 'auth/authenticate';
  userLogData!: any;
  submit(login: any) {
    this.userLogData = login;
    console.log(login);
    this.http.postData(this.url, this.userLogData).subscribe({
      next: (response) => {
        console.log('POST request successful:', response);
        localStorage.setItem('userData', JSON.stringify(response));
        this.router.navigate(['/home']);
      },
      error: (error) => {
        console.log('Error:', error);
        // Handle the error here
      },
      complete: () => {},
    });
  }
}
