import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AdminAddjobFormComponent } from '../admin-addjob-form/admin-addjob-form.component';
@Component({
  selector: 'app-admin-jobs',
  templateUrl: './admin-jobs.component.html',
  styleUrls: ['./admin-jobs.component.scss'],
})
export class AdminJobsComponent {
  constructor(public dialog: MatDialog) {}
  addJob() {
    // Open the dialog using the MatDialog service
    const dialogRef: MatDialogRef<AdminAddjobFormComponent> = this.dialog.open(
      AdminAddjobFormComponent,
      {
        width: '50%',
        // Set the width of the dialog

        data: { data: 'john' }, // You can pass data to the dialog component using the `data` property
      }
    );

    // Handle the dialog result (if needed)
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
