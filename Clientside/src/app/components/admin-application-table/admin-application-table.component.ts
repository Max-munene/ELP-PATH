import { Component, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { AdminJourneyComponent } from '../admin-journey/admin-journey.component';
import { UserProfileComponent } from '../user-profile/user-profile.component';
import { AdminApplicationFormComponent } from '../admin-application-form/admin-application-form.component';

@Component({
  selector: 'app-admin-application-table',
  templateUrl: './admin-application-table.component.html',
  styleUrls: ['./admin-application-table.component.scss'],
})
export class AdminApplicationTableComponent {
  constructor(private http: HttpServiceService, public dialog: MatDialog) {}
  @ViewChild(MatSort) empTbSort = new MatSort();
  @ViewChild(MatPaginator) paginator!: MatPaginator; //  '!' to indicate that it will be initialized later

  url!: string;
  displayedColumns: string[] = [
    'name',
    'applicationStatus',
    'dateOfApplication',
    'branch',
    'button',
    'journey',
  ];
  dataSource = new MatTableDataSource();

  ngOnInit() {
    const storedData = localStorage.getItem('userData');

    this.url = this.http.serverUrl + 'applications/display-applications';

    this.http.getData(this.url).subscribe({
      next: (response) => {
        this.dataSource.data = response;
        console.log(this.dataSource.data.length);
      },
      error: (error) => {
        console.log('Error:', error);
        // Handle the error here
      },
      complete: () => {},
    });
  }

  // application form dialog
  applicationDialog(): void {
    // Open the dialog using the MatDialog service
    const dialogRef: MatDialogRef<AdminApplicationFormComponent> =
      this.dialog.open(AdminApplicationFormComponent, {
        width: '50%',

        // Set the width of the dialog

        data: { data: 'john' }, // You can pass data to the dialog component using the `data` property
      });

    // Handle the dialog result (if needed)
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      this.ngOnInit();
    });
  }
  // profile dialog
  profileDialog(id: string): void {
    // Open the dialog using the MatDialog service
    const dialogRef: MatDialogRef<UserProfileComponent> = this.dialog.open(
      UserProfileComponent,
      {
        width: '80%',
        // Set the width of the dialog

        data: { data: id }, // You can pass data to the dialog component using the `data` property
      }
    );

    // Handle the dialog result (if needed)
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
    });
  }

  // journey dialog
  journeyDialog(id: string): void {
    // Open the dialog using the MatDialog service
    const dialogRef: MatDialogRef<AdminJourneyComponent> = this.dialog.open(
      AdminJourneyComponent,
      {
        width: '80%',
        // Set the width of the dialog

        data: { data: id }, // You can pass data to the dialog component using the `data` property
      }
    );

    // Handle the dialog result (if needed)
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  ngAfterViewInit() {
    this.dataSource.sort = this.empTbSort;
    this.dataSource.paginator = this.paginator;
  }
}
