import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AdminHomeComponent } from '../admin-home/admin-home.component';

@Component({
  selector: 'app-admin-navbar',
  templateUrl: './admin-navbar.component.html',
  styleUrls: ['./admin-navbar.component.scss'],
})
export class AdminNavbarComponent {
  constructor(
    private router: Router,
    private toggleValue: AdminHomeComponent
  ) {}
  logout() {
    localStorage.clear();
    this.router.navigate(['/']);
  }

  toggleButton() {
    return this.toggleValue.toggle();
  }
  applyFilter(event: Event) {
    // const filterValue = (event.target as HTMLInputElement).value;
    // this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
