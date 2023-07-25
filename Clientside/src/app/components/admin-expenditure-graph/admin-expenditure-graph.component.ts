import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-expenditure-graph',
  templateUrl: './admin-expenditure-graph.component.html',
  styleUrls: ['./admin-expenditure-graph.component.scss'],
})
export class AdminExpenditureGraphComponent {
  chartOptions = {
    animationEnabled: true,
    axisY: {
      title: 'Total Expenditure',
    },
    axisX: {
      title: 'Year',
    },
    data: [
      {
        type: 'line',
        xValueFormatString: 'YYYY',

        color: '#c42542',
        dataPoints: [
          { x: new Date(2000, 0, 1), y: 10 },
          { x: new Date(2001, 0, 1), y: 15 },
          { x: new Date(2002, 0, 1), y: 25 },
          { x: new Date(2003, 0, 1), y: 5 },
          { x: new Date(2004, 0, 1), y: 28 },
        ],
      },
    ],
  };
}
