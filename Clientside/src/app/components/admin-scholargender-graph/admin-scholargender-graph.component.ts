import { Component, OnInit } from '@angular/core';
import Chart from 'chart.js/auto';
import { HttpServiceService } from 'src/app/services/http-service.service';

@Component({
  selector: 'app-admin-scholargender-graph',
  templateUrl: './admin-scholargender-graph.component.html',
  styleUrls: ['./admin-scholargender-graph.component.scss'],
})
export class AdminScholargenderGraphComponent {
  private chart: any;

  constructor(private http: HttpServiceService) {}

  maleDataByYear: any;
  femaleDataByYear: any;
  urlGetScholarsData!: string;

  ngOnInit() {
    this.urlGetScholarsData = this.http.serverUrl + 'scholar/all-scholars'; // Adjust the endpoint accordingly

    this.http.getData(this.urlGetScholarsData).subscribe({
      next: (response: any[]) => {
        const maleScholars = response.filter(
          (scholar) => scholar.scholarGender === 'MALE'
        );
        const femaleScholars = response.filter(
          (scholar) => scholar.scholarGender === 'FEMALE'
        );
        console.log('Female', femaleScholars);

        this.maleDataByYear = this.getDataByYear(maleScholars);
        this.femaleDataByYear = this.getDataByYear(femaleScholars);

        this.setupChart(this.maleDataByYear, this.femaleDataByYear);
      },
      error: (error) => {
        console.log('Error:', error);
        this.setupDefaultChart();
      },
      complete: () => {
        // Additional completion logic if needed
      },
    });
  }

  getDataByYear(data: any[]): any {
    return data.reduce((acc, curr) => {
      const year = curr.yearOfJoiningTertiaryProgram;
      if (!acc[year]) {
        acc[year] = [];
      }
      acc[year].push(curr);
      return acc;
    }, {});
  }

  private setupChart(maleDataByYear: any, femaleDataByYear: any): void {
    const maleDataList: number[] = [];
    const femaleDataList: number[] = [];
    const yearList: string[] = [];

    // Iterate through the years to collect data
    for (const year in maleDataByYear) {
      if (maleDataByYear.hasOwnProperty(year)) {
        yearList.push(year);
        maleDataList.push(maleDataByYear[year].length);
        femaleDataList.push(
          femaleDataByYear[year] ? femaleDataByYear[year].length : 0
        );
      }
    }

    const ctx = document.getElementById('gender') as HTMLCanvasElement;
    this.chart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: yearList,
        datasets: [
          {
            label: 'Male',
            data: maleDataList,
            borderColor: '#a32a29',
            fill: false,
          },
          {
            label: 'Female',
            data: femaleDataList,
            borderColor: '#041014',
            fill: false,
          },
        ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          y: {
            beginAtZero: true,
          },
        },
      },
    });
  }

  private setupDefaultChart(): void {
    const ctx = document.getElementById('gender') as HTMLCanvasElement;
    this.chart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: ['Year 1', 'Year 2'],
        datasets: [
          {
            data: [0, 0],
            backgroundColor: ['#a32a29', '#041014'],
            borderColor: '#a32a29',
            fill: false,
          },
        ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          y: {
            beginAtZero: true,
          },
        },
      },
    });
  }
}
