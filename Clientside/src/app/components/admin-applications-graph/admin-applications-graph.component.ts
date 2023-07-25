import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-applications-graph',
  templateUrl: './admin-applications-graph.component.html',
  styleUrls: ['./admin-applications-graph.component.scss'],
})
export class AdminApplicationsGraphComponent {
  chartOptions = {
    animationEnabled: true,
    axisY: {
      title: 'Number of Applications',
    },
    axisX: {
      title: 'Year',
    },
    data: [
      {
        type: 'line',
        xValueFormatString: 'YYYY',
        color: '#c48e25',
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
