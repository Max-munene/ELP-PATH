import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class HttpServiceService {
  constructor(private http: HttpClient) {}
  serverUrl: string = 'http://192.168.100.237:8080/';
  getData(url: string): Observable<any> {
    return this.http.get(url, {
      headers: {},
    });
  }
  getImage(url: string): Observable<any> {
    return this.http.get(url, { responseType: 'text' });
  }
  postData(url: string, data: any): Observable<any> {
    // Make the HTTP POST request
    const headers = new HttpHeaders();
    return this.http.post(url, data, { headers });
  }
  putData(url: string, data: any): Observable<any> {
    // Make the HTTP PUT request
    const headers = new HttpHeaders();
    return this.http.put(url, data, { headers });
  }
}
