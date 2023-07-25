import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-elps-graph',
  templateUrl: './admin-elps-graph.component.html',
  styleUrls: ['./admin-elps-graph.component.scss'],
})
export class AdminElpsGraphComponent {
  chartOptions = {
    animationEnabled: true,
    axisY: {
      title: 'Number of ELPs',
    },
    axisX: {
      title: 'Year',
    },
    data: [
      {
        type: 'line',
        xValueFormatString: 'YYYY',
        color: '#50901d',
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
