import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { HttpServiceService } from 'src/app/services/http-service.service';

@Component({
  selector: 'app-user-profile-card',
  templateUrl: './user-profile-card.component.html',
  styleUrls: ['./user-profile-card.component.scss'],
})
export class UserProfileCardComponent {
  activeTab: string = 'bio'; //active tab
  imageurl!: string; //to display image image
  profileData!: any; //for user profile data
  urlgetimage!: string; //url to get image from api
  urlsocials!: string; //url to get user social media data
  urlprofile!: string; //url to get user profile data
  userInfo!: any; //to hold user information
  social_profileData!: any; //to hold social profile data
  constructor(public dialog: MatDialog, private http: HttpServiceService) {}

  // Initialize function runs when the component is first loaded in the DOM
  ngOnInit() {
    const userData = localStorage.getItem('userData');
    if (userData) {
      this.userInfo = JSON.parse(userData);
      console.log('userdata', this.userInfo);
      this.getProfileData();
    }
  }

  // =========================== method to get profile data from api============================
  getProfileData() {
    // url get profile information
    this.urlprofile =
      this.http.serverUrl + 'profile/' + this.userInfo.id + '/view';
    // Fetch profile data from the server
    this.http.getData(this.urlprofile).subscribe({
      next: (response) => {
        this.profileData = response.payload;
        this.imageurl = this.profileData.profileImage.type;
        localStorage.removeItem('userImageData');
        localStorage.setItem('userImageData', JSON.stringify(this.imageurl));
        console.log('data', this.profileData);
      },
      error: (error) => {
        console.log('Error:', error);
        // Handle the error here
      },

      complete: () => {},
    });
  }
}
