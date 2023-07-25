import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-scholar-graph',
  templateUrl: './admin-scholar-graph.component.html',
  styleUrls: ['./admin-scholar-graph.component.scss'],
})
export class AdminScholarGraphComponent {
  chartOptions = {
    animationEnabled: true,
    axisY: {
      title: 'Number of Scholars',
    },
    axisX: {
      title: 'Year',
    },
    data: [
      {
        type: 'line',
        xValueFormatString: 'YYYY',
        color: '#258bc4',
        dataPoints: [
          { x: new Date(2000, 6, 1), y: 20 },
          { x: new Date(2001, 0, 1), y: 15 },
          { x: new Date(2002, 0, 1), y: 25 },
          { x: new Date(2003, 0, 1), y: 5 },
          { x: new Date(2004, 0, 1), y: 28 },
        ],
      },
    ],
  };
}
