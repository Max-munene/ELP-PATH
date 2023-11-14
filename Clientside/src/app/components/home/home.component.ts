import { Component, ViewChild } from '@angular/core';
import { FeedsComponent } from '../feeds/feeds.component';
import { PostComponent } from '../post/post.component';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { ProfileService } from 'src/app/services/profile-progress.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  @ViewChild(FeedsComponent) feedsComponent!: FeedsComponent;
  @ViewChild(PostComponent)
  postComponent!: PostComponent;
  userInfo: any;
  profileData: any;
  profileCompletion: any;
  constructor(
    private http: HttpServiceService,
    private profileService: ProfileService
  ) {}

  // Initialize function runs when the component is first loaded in the DOM
  ngOnInit() {
    const userData = localStorage.getItem('userData');
    if (userData) {
      this.userInfo = JSON.parse(userData);
      this.getProfileData();
      this.fetchProfileCompletion();
    }
  }
  ngAfterViewInit() {
    // Subscribe to changes in the PostComponent
    this.postComponent.someChangeEvent.subscribe(() => {
      this.feedsComponent.ngOnInit(); // Trigger FeedsComponent's ngOnInit
    });
  }
  // =========================== method to get profile data from api============================
  getProfileData() {
    // url get profile information
    const urlprofile =
      this.http.serverUrl + 'profile/' + this.userInfo.id + '/view';
    // Fetch profile data from the server
    this.http.getData(urlprofile).subscribe({
      next: (response) => {
        this.profileData = response;
      },
      error: (error) => {
        console.log('Error:', error);
        // Handle the error here
      },

      complete: () => {},
    });
  }

  fetchProfileCompletion() {
    this.profileService.getProfileCompletion().subscribe(
      (percentage: number) => {
        this.profileCompletion = percentage;
      },
      (error) => {
        console.error('Error Fetching profile completion:', error);
      }
    );
  }
}
