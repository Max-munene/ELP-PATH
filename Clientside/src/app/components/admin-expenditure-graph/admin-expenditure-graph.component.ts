import { Component } from '@angular/core';
import Chart from 'chart.js/auto';
import { HttpServiceService } from 'src/app/services/http-service.service';
@Component({
  selector: 'app-admin-expenditure-graph',
  templateUrl: './admin-expenditure-graph.component.html',
  styleUrls: ['./admin-expenditure-graph.component.scss'],
})
export class AdminExpenditureGraphComponent {
  constructor(private http: HttpServiceService) {}
  private chart: any;
  expensesSumData: any;
  urlGetExpensesSum!: any;
  yearList: string[] = [];
  totalExpenseList: string[] = [];

  ngOnInit() {
    //============url to get applications data =================
    this.urlGetExpensesSum =
      this.http.serverUrl + 'scholar/expenses/total_expense_per_year';

    //=============================get application data from the server===========
    this.http.getData(this.urlGetExpensesSum).subscribe({
      next: (response) => {
        this.expensesSumData = response;
        this.loopThroughObject();
        console.log(this.expensesSumData);
        this.setupChart(this.yearList, this.totalExpenseList);
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
    for (const year in this.expensesSumData) {
      if (this.expensesSumData.hasOwnProperty(year)) {
        this.yearList.push(year);
        this.totalExpenseList.push(this.expensesSumData[year].toString());
      }
    }
  }

  // method to set up chart data and configuration========================================================
  private setupChart(yearList: string[], totalExpenseList: string[]): void {
    // data for the chart

    const ctx = document.getElementById('expenses') as HTMLCanvasElement;
    this.chart = new Chart(ctx, {
      type: 'line',
      data: {
        labels: yearList,
        datasets: [
          {
            label: 'Total Expenditure in Ksh',
            data: totalExpenseList,
            backgroundColor: ['#fff'],
            borderColor: ['#f1388b'],
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
            // ticks: {
            //   stepSize: 10,
            // },
          },
        },
      },
    });
  }
}
