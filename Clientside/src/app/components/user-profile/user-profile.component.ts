import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ProfileFormComponent } from '../profile-form/profile-form.component';
import { ProfilePicComponent } from '../profile-pic/profile-pic.component';
import { SocialMediaFormComponent } from '../social-media-form/social-media-form.component';
import { HttpServiceService } from 'src/app/services/http-service.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss'],
})
export class UserProfileComponent {
  activeTab: string = 'bio'; //active tab
  imageurl!: string; //to display image image
  profileData!: any; //for user profile data
  urlgetimage!: string; //url to get image from api
  urlsocials!: string; //url to get user social media data
  urlprofile!: string; //url to get user profile data
  userInfo!: any; //to hold user information
  social_profileData!: any; //to hold social profile data
  constructor(public dialog: MatDialog, private http: HttpServiceService) {}

  ngOnInit() {
    const userData = localStorage.getItem('userData');
    if (userData) {
      const parsedData = JSON.parse(userData);
      this.userInfo = parsedData;

      // ============================url get image================================================
      this.urlgetimage =
        this.http.serverUrl + 'image/' + parsedData.id + '/view';

      // ===========================url get social information=====================================
      this.urlsocials =
        this.http.serverUrl + 'socials/' + parsedData.id + '/view';

      // url get profile information
      this.urlprofile =
        this.http.serverUrl + 'profile/' + parsedData.id + '/view';

      // ============================get image data from api================================================
      this.http.getImage(this.urlgetimage).subscribe({
        next: (response: any) => {
          const responsedata: any = JSON.parse(response);
          this.imageurl =
            'data:' + response.type + ';base64,' + responsedata.data;

          console.log(responsedata);
        },
        error: (error) => {
          console.log('Error:', error);
          // Handle the error here
        },
        complete: () => {},
      });

      //============================= get social data from api============================
      this.http.getData(this.urlsocials).subscribe({
        next: (response: any[]) => {
          if (response.length !== 0) {
            this.social_profileData = response;
          }

          console.log(this.social_profileData);
        },
        error: (error) => {
          console.log('Error:', error);
          // Handle the error here
        },
        complete: () => {},
      });

      // ===========================get profile data from api============================

      this.http.getData(this.urlprofile).subscribe({
        next: (response) => {
          if (response.length !== 0) {
            this.profileData = response[0];
            console.log('data', this.profileData);
          }
        },
        error: (error) => {
          console.log('Error:', error);
          // Handle the error here
        },

        complete: () => {},
      });
    }
  }

  showTab(tab: string) {
    this.activeTab = tab;
  }

  // =====================================profile pic image form=======================================
  picformDialog(editAdd: string): void {
    // Open the dialog using the MatDialog service
    const dialogRef: MatDialogRef<ProfilePicComponent> = this.dialog.open(
      ProfilePicComponent,
      {
        width: '60%', // Set the width of the dialog

        data: { data: editAdd }, // You can pass data to the dialog component using the `data` property
      }
    );

    // Handle the dialog result (if needed)
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      this.ngOnInit();
    });
  }

  // =============================================open profile from dialog=================================
  pformDialog(editAdd: string): void {
    // Open the dialog using the MatDialog service
    const dialogRef: MatDialogRef<ProfileFormComponent> = this.dialog.open(
      ProfileFormComponent,
      {
        width: '60%', // Set the width of the dialog

        data: { data: editAdd }, // You can pass data to the dialog component using the `data` property
      }
    );

    // Handle the dialog result (if needed)
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      this.ngOnInit();
    });
  }
  // =========================================================open social form dialog=======================
  sformDialog(editAdd: string): void {
    // Open the dialog using the MatDialog service
    const dialogRef: MatDialogRef<SocialMediaFormComponent> = this.dialog.open(
      SocialMediaFormComponent,
      {
        width: '60%', // Set the width of the dialog

        data: { data: editAdd }, // You can pass data to the dialog component using the `data` property
      }
    );

    // Handle the dialog result (if needed)
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      this.ngOnInit();
    });
  }
}
