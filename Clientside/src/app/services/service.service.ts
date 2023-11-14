import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JobOpportunity } from './jobOpportunity';
import { AnyObject } from 'chart.js/dist/types/basic';
@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  // private apiUrl = 'http://52.15.152.26:5555/opportunities/all';
  private apiUrl = 'http://192.168.0.35:8080/opportunities/all';
  // private postJobs='http://52.15.152.26:5555/opportunities/job/create-with-poster';
  private postJobs='http://192.168.0.35:8080/opportunities/job/create-with-poster';
  private spotlightGetUrl='http://192.168.0.56:8080/sportlight/sportlight-all';
  private spotlightPostUrl='http://192.168.0.56:8080/sportlight/sportlight-create';


  constructor(private http: HttpClient) {}

  postJobPosting(jobOpportunity: FormData):Observable<any> {
    jobOpportunity.forEach(console.log)
    return this.http.post(this.postJobs, jobOpportunity);
  }

  getJobOpportunities(): Observable<any> {
    return this.http.get(this.apiUrl);
  }


  getSpotlight(): Observable<any>{
      return this.http.get<any>(this.spotlightGetUrl)
  }

  postSpotlight(spoltlight: AnyObject): Observable<any>{
       return  this.http.post(this.spotlightPostUrl,spoltlight)
  }
}
