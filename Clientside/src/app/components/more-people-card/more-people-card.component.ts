import { Component } from '@angular/core';
import { count } from 'rxjs';
import { HttpServiceService } from 'src/app/services/http-service.service';

@Component({
  selector: 'app-more-people-card',
  templateUrl: './more-people-card.component.html',
  styleUrls: ['./more-people-card.component.scss'],
})
export class MorePeopleCardComponent {
  constructor(public http: HttpServiceService) {}

  PeopleProfileData: any[] = [];
  getPeopleProfileUrl!: string;
  ngOnInit() {
    const userData = localStorage.getItem('userData');
    if (userData) {
      this.getPeopleProfile();
    }
  }

  // method to get PeopleProfile by chapter id or all PeopleProfile
  getPeopleProfile() {
    this.getPeopleProfileUrl = this.http.serverUrl + 'profile/all';

    // ====================================get method=======================================

    this.http.getData(this.getPeopleProfileUrl).subscribe({
      next: (response) => {
        console.log(response);
        let count = 0;

        this.PeopleProfileData = response.payload
          .map((item: any) => {
            console.log('count', count);
            const userData = localStorage.getItem('userData');
            if (userData) {
              const parsedData = JSON.parse(userData);
              let userDataId = parsedData.id;
              if (userDataId !== item.user.id) {
                count++;
                if (count <= 4) {
                  return {
                    title: item.title,
                    imageUrl:
                      item.profileImage !== null
                        ? 'data:' +
                          item.profileImage.type +
                          item.profileImage.data
                        : null,
                    firstName: item.user.firstName,
                    lastName: item.user.lastName,
                    userId: item.user.id,
                  };
                }
              }
            }
            // Return null when conditions aren't met
            return null;
          })
          .filter((item: any) => item !== null);
        console.log(this.PeopleProfileData);
      },
      error: (error) => {
        console.log(error);
      },
      complete: () => {},
    });

    // ============================================================================
  }
}
