import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  constructor(private http: HttpClient) {}

  getProfileCompletion(): Observable<number> {
    return this.http.get<number>('profile-progress/get/{userId}'); // Adjust the endpoint URL accordingly
  }
}
