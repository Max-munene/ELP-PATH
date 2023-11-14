import { Component, Input } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';

@Component({
  selector: 'app-admin-hub-events',
  templateUrl: './admin-hub-events.component.html',
  styleUrls: ['./admin-hub-events.component.scss'],
})
export class AdminHubEventsComponent {
  constructor(public http: HttpServiceService) {}

  @Input() hubid!: string;

  eventsData: any[] = [];
  getEventsUrl!: string;
  ngOnInit() {
    if (this.hubid !== undefined) {
      console.log('hubid in event', this.hubid);
    } else {
      console.log('hubid in event', this.hubid);
    }
    this.getEvents();
  }

  // method to get events by chapter id or all events
  getEvents() {
    if (this.hubid !== undefined) {
      console.log('hubid in event', this.hubid);
      this.getEventsUrl =
        this.http.serverUrl + 'events/' + this.hubid + '/display-chaper-events';
    } else {
      this.getEventsUrl = this.http.serverUrl + 'events/all';
    }

    // ====================================get method=======================================

    this.http.getData(this.getEventsUrl).subscribe({
      next: (response) => {
        console.log(response);
        this.eventsData = response.reverse().map((item: any) => ({
          eventTitle: item.eventTitle,
          description: item.eventDescription,
          imageUrl:
            'data:' +
            (item.eventImage.type ? item.eventImage.type : '') +
            ';base64,' +
            (item.eventImage.data ? item.eventImage.data : ''),
          organizer: item.organizer,
          location: item.location,
          link: item.eventLink,
          eventDate: item.eventDate,
        }));
      },
      error: (error) => {
        console.log(error);
      },
      complete: () => {},
    });

    // ============================================================================
  }
}
