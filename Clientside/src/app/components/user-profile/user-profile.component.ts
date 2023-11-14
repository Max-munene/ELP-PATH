import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ProfileFormComponent } from '../profile-form/profile-form.component';
import { ProfilePicComponent } from '../profile-pic/profile-pic.component';
import { SocialMediaFormComponent } from '../social-media-form/social-media-form.component';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { ActivatedRoute } from '@angular/router';

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
  userid!: string; //to hold userid
  useridparam!: string;

  skillsData: { name: string; progress: number }[] = [
    { name: 'Angular', progress: 50 },
    { name: 'Data Analysis', progress: 60 },
    { name: 'Python', progress: 70 },
  ];
  constructor(
    public dialog: MatDialog,
    private http: HttpServiceService,
    public route: ActivatedRoute
  ) {}

  // Initialize function runs when the component is first loaded in the DOM
  ngOnInit() {
    const userData = localStorage.getItem('userData');

    const id = this.route.snapshot.paramMap.get('id');

    if (id !== undefined && id !== null) {
      this.useridparam = id;
      this.userid = id;
      this.getProfileData();
      this.getSocialMediaData();
    } else if (userData) {
      this.userInfo = JSON.parse(userData);
      this.userid = this.userInfo.id.toString();
      this.getProfileData();
      this.getSocialMediaData();
      // Handle the case when the id is null
    }
  }

  //============================= method to get social data from api============================
  getSocialMediaData() {
    //url get social information

    this.urlsocials =
      this.http.serverUrl + 'socials/' + this.userid.toString() + '/view';

    this.http.getData(this.urlsocials).subscribe({
      next: (response) => {
        this.social_profileData = response.payload;

        console.log('social data', this.social_profileData);
      },
      error: (error) => {
        console.log('Error:', error);
        // Handle the error here
      },
      complete: () => {},
    });
  }

  // =========================== method to get profile data from api============================
  getProfileData() {
    // url get profile information
    this.urlprofile = this.http.serverUrl + 'profile/' + this.userid + '/view';
    // Fetch profile data from the server

    this.http.getData(this.urlprofile).subscribe({
      next: (response) => {
        this.profileData = response.payload;
        console.log('Image', this.profileData);

        this.imageurl = 'data:' + this.profileData.profileImage;
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

  // method to get active tab
  showTab(tab: string) {
    this.activeTab = tab;
  }

  // =====================================profile pic image form=======================================
  picformDialog(editAdd: string): void {
    // Open the dialog using the MatDialog service
    const dialogRef: MatDialogRef<ProfilePicComponent> = this.dialog.open(
      ProfilePicComponent,
      {
        panelClass: 'dialog-responsive',
        width: '50%', // Set the width of the dialog

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
        panelClass: 'dialog-responsive',
        width: '45%', // Set the width of the dialog
        // height: '60%', // Set the height of the dialog

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
        panelClass: 'dialog-responsive',

        width: '45%',
        height: '75%', // Set the width of the dialog

        data: { data: editAdd }, // You can pass data to the dialog component using the `data` property
      }
    );

    // Handle the dialog result (if needed)
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      this.ngOnInit();
    });
  }
  updateSkills() {
    // Update the skillsData array with new values
    this.skillsData = [
      { name: 'Angular', progress: 70 },
      { name: 'Data Analysis', progress: 80 },
      { name: 'Python', progress: 90 },
      { name: 'MysQL', progress: 30 },
      { name: 'DataBase', progress: 75 },
    ];
  }
}
