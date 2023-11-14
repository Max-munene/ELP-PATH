import { Component, Input } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';

@Component({
  selector: 'app-admin-hub-activities-card',
  templateUrl: './admin-hub-activities-card.component.html',
  styleUrls: ['./admin-hub-activities-card.component.scss'],
})
export class AdminHubActivitiesCardComponent {
  constructor(public http: HttpServiceService) {}
  @Input() hubid!: string;
  getActivitiesUrl!: string;
  activityCount!: string;
  currentDate!: string;
  ngOnInit() {
    if (this.hubid !== undefined) {
      console.log('hubid in Activity card', this.hubid);
    }
    this.getcountActivity();
    this.getCurrentDate();
  }
  // method to get current date
  getCurrentDate() {
    const today = new Date();
    this.currentDate = today.toISOString(); // Example format, you can change it as needed
  }

  // method to get activity by chapter id
  getcountActivity() {
    if (this.hubid !== undefined) {
      this.getActivitiesUrl =
        this.http.serverUrl +
        'activities/' +
        this.hubid +
        '/count-hub-activities';
    } else {
      this.getActivitiesUrl =
        this.http.serverUrl + 'activities/count-all-activities';
    }
    this.http.getData(this.getActivitiesUrl).subscribe({
      next: (response) => {
        this.activityCount = response;
      },
      error: (error) => {
        console.log('Error', error);
      },
      complete: () => {},
    });
  }
}
