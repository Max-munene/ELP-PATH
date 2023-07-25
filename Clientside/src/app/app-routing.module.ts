import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { HubsComponent } from './components/hubs/hubs.component';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminScholarsComponent } from './components/admin-scholars/admin-scholars.component';
import { MorePeopleComponent } from './components/more-people/more-people.component';
import { AdminApplicationsComponent } from './components/admin-applications/admin-applications.component';
import { AdminAwardedComponent } from './components/admin-awarded/admin-awarded.component';
import { UserProfileHomeComponent } from './components/user-profile-home/user-profile-home.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { UserRegisterComponent } from './components/user-register/user-register.component';
import { AdminExpenseFormComponent } from './components/admin-expense-form/admin-expense-form.component';
import { AdminEducationFormComponent } from './components/admin-education-form/admin-education-form.component';
import { AdminApplicationFormComponent } from './components/admin-application-form/admin-application-form.component';

const routes: Routes = [
  { path: '', component: UserLoginComponent, pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'hubs', component: HubsComponent },
  {
    path: 'admin',
    component: AdminHomeComponent,
    children: [
      { path: 'dashboard', component: AdminDashboardComponent },
      {
        path: 'scholars',
        component: AdminScholarsComponent,
      },
      { path: 'applications', component: AdminApplicationsComponent },
      { path: 'awarded', component: AdminAwardedComponent },
      // {path}
    ],
  },

  { path: 'people', component: MorePeopleComponent },
  { path: 'user_profile', component: UserProfileHomeComponent },
  { path: 'register', component: UserRegisterComponent },
  { path: 'pform', component: AdminApplicationFormComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
