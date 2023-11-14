import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, tap, throwError } from 'rxjs';
// import { unis } from 'src/assets/json_files/schools';

@Injectable({
  providedIn: 'root',
})
export class HttpServiceService {
  constructor(private http: HttpClient) {}
  // serverUrl: string = 'http://localhost:8080/';
  // serverUrl: string = 'http://192.168.0.83:8080/';

  // serverUrl: string = 'http://192.168.0.74:8080/';
  serverUrl: string = 'http://52.15.152.26:5555/';
  // serverUrl: string = 'http://192.168.0.81:8080/';
  dataUrl: string = '/assets/json_files/regions.json';

  // // serverUrl: string = 'http://192.168.0.87:8080/';

  // serverUrl: string = 'http://192.168.100.232:8080/';
  getData(url: string): Observable<any> {
    const res = this.http
      .get(url, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
      .pipe(
        tap(
          () => {},
          (error) => console.error('Error', error)
        )
      );

    console.log(res);
    return res;
  }

  getDataF(url: string, val: any): Observable<any> {
    const res = this.http.get(url, {
      headers: {
        'Content-Type': 'application/json',
      },
    });

    console.log(res);
    return res;
  }
  getData1(): Observable<any> {
    return this.http.get<any[]>(this.dataUrl);
  }

  // getData1():Observable<any>{
  //    return unis
  // }
  // getImage(url: string): Observable<any> {
  //   return this.http.get(url, { responseType: 'text' });
  // }
  postData(url: string, data: any): Observable<any> {
    // Make the HTTP POST request
    console.log(data);
    const headers = new HttpHeaders();
    return this.http.post(url, data, { headers });
  }

  postScholarDataForVerification(url: string, data: any): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(url, data, { headers });
  }

  postNoData(url: string): Observable<any> {
    // Make the HTTP POST request
    const headers = new HttpHeaders({ 'content-type': 'application/json' });
    return this.http.post(url, '', { headers });
  }
  putData(url: string, data: any): Observable<any> {
    // Make the HTTP PUT request
    const headers = new HttpHeaders();
    return this.http.put(url, data, { headers });
  }
  deleteData(url: string): Observable<any> {
    const headers = new Headers();
    return this.http.delete(url);
  }
  getPaginatedData(page: number, pageSize: number) {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('pageSize', pageSize.toString());
    return this.http.get(this.serverUrl, { params });
  }
}
