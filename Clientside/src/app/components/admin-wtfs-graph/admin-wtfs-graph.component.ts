import { Component } from '@angular/core';
import Chart from 'chart.js/auto';
import { HttpServiceService } from 'src/app/services/http-service.service';
@Component({
  selector: 'app-admin-wtfs-graph',
  templateUrl: './admin-wtfs-graph.component.html',
  styleUrls: ['./admin-wtfs-graph.component.scss'],
})
export class AdminWtfsGraphComponent {
  private chart: any;
  urlGetWftsCount!: string;
  wftsCountData: any;
  yearList: string[] = [];
  totalCountList: string[] = [];

  constructor(private http: HttpServiceService) {}

  ngOnInit() {
    //============url to get applications data =================
    this.urlGetWftsCount =
      this.http.serverUrl + 'applications/count-awarded-applications-by-year';

    //=============================get application data from the server===========
    this.http.getData(this.urlGetWftsCount).subscribe({
      next: (response) => {
        this.wftsCountData = response;
        this.loopThroughObject();
        console.log('awarded', this.wftsCountData);
        this.setupChart(this.yearList, this.totalCountList);
      },
      error: (error) => {
        console.log('Error:', error);
        // Handle the error here
      },
      complete: () => {},
    });
  }
  //======================method to loop through an object item objects key and value to a list===============
  loopThroughObject() {
    for (const year in this.wftsCountData) {
      if (this.wftsCountData.hasOwnProperty(year)) {
        this.yearList.push(year);
        this.totalCountList.push(this.wftsCountData[year].toString());
      }
    }
  }

  // method to set up chart data and configuration========================================================
  private setupChart(yearList: string[], totalCountList: string[]): void {
    // data for the chart

    const ctx = document.getElementById('wtfs') as HTMLCanvasElement;
    this.chart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: yearList,
        datasets: [
          {
            label: 'Number of Awarded',
            data: totalCountList,
            backgroundColor: ['#fff'],
            borderColor: ['#01b8ff'],
            tension: 0.35,
          },
        ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          x: {
            display: true,
          },
          y: {
            display: true,
            beginAtZero: true,
          },
        },
      },
    });
  }
  //=====================================end of method functions=============================================
}
