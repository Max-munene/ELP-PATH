import { Component } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';

@Component({
  selector: 'app-expenditure-card',
  templateUrl: './expenditure-card.component.html',
  styleUrls: ['./expenditure-card.component.scss'],
})
export class ExpenditureCardComponent {
  url!: string;
  totalExpense!: any;
  constructor(private http: HttpServiceService) {}

  ngOnInit() {
    const storedData = localStorage.getItem('userData');

    this.url = this.http.serverUrl + 'scholar/expenses/grand-totals';

    this.http.getData(this.url).subscribe({
      next: (response) => {
        this.totalExpense = response;
        console.log(this.totalExpense);
      },
      error: (error) => {
        console.log('Error:', error);
        // Handle the error here
      },
      complete: () => {},
    });
  }
}
