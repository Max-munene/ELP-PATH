import { Component, Input } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { AdminExpenseFormComponent } from '../admin-expense-form/admin-expense-form.component';

@Component({
  selector: 'app-admin-expenses',
  templateUrl: './admin-expenses.component.html',
  styleUrls: ['./admin-expenses.component.scss'],
})
export class AdminExpensesComponent {
  constructor(public dialog: MatDialog, public http: HttpServiceService) {}
  @Input() applicantid!: string;
  url!: string;
  expensedata!: any;
  userId!: string;

  ngOnInit() {
    // const storedData = localStorage.getItem('userData');

    // if (storedData) {
    //   const parsedData = JSON.parse(storedData);
    //   this.userId = parsedData.id;
    // Use the parsed data in your application
    console.log(this.applicantid.toString());
    this.url =
      this.http.serverUrl +
      'scholar/expenses/' +
      this.applicantid.toString() +
      '/view';

    this.http.getData(this.url).subscribe({
      next: (response) => {
        this.expensedata = response;
        console.log('POST request successful:', response);

        // Handle the response data here
        // localStorage.setItem('token', JSON.stringify(response));
      },
      error: (error) => {
        console.log('Error:', error.message);
        // Handle the error here
      },
      complete: () => {},
    });
    // }
  }
  // open bio dialog
  expenseformDialog(): void {
    // Open the dialog using the MatDialog service
    const dialogRef: MatDialogRef<AdminExpenseFormComponent> = this.dialog.open(
      AdminExpenseFormComponent,
      {
        width: '60%', // Set the width of the dialog

        data: { data: this.applicantid.toString() }, // You can pass data to the dialog component using the `data` property
      }
    );

    // Handle the dialog result (if needed)
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      this.ngOnInit();
    });
  }
}
