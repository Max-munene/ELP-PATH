import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-wtfs-graph',
  templateUrl: './admin-wtfs-graph.component.html',
  styleUrls: ['./admin-wtfs-graph.component.scss'],
})
export class AdminWtfsGraphComponent {
  chartOptions = {
    animationEnabled: true,
    axisY: {
      title: 'Number of WTFs',
    },
    axisX: {
      title: 'Year',
    },
    data: [
      {
        type: 'line',
        xValueFormatString: 'YYYY',
        color: '#3d4ac0',
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
