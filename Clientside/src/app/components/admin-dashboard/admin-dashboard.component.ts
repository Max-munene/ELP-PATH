import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss'],
})
export class AdminDashboardComponent {
  chartOptions = {
    animationEnabled: true,
    axisY: {
      title: 'Number of Scholars',
      crosshair: {
        enabled: true,
      },
    },
    axisX: {
      title: 'Year',
      crosshair: {
        enabled: true,
      },
    },
    data: [
      {
        type: 'line',
        xValueFormatString: 'YYYY',
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
