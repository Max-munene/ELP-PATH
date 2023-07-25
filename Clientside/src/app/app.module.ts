import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

// angular material imports
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatBadgeModule } from '@angular/material/badge';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatMenuModule } from '@angular/material/menu';
import { MatTableModule } from '@angular/material/table';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule, MatNavList } from '@angular/material/list';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDividerModule } from '@angular/material/divider';

// chart js
import { CanvasJSAngularChartsModule } from '@canvasjs/angular-charts';

// admin
import { AdminScholarGraphComponent } from './components/admin-scholar-graph/admin-scholar-graph.component';
import { AdminApplicationsGraphComponent } from './components/admin-applications-graph/admin-applications-graph.component';
import { AdminExpenditureGraphComponent } from './components/admin-expenditure-graph/admin-expenditure-graph.component';
import { AdminElpsGraphComponent } from './components/admin-elps-graph/admin-elps-graph.component';
import { AdminWtfsGraphComponent } from './components/admin-wtfs-graph/admin-wtfs-graph.component';
import { AdminScholarTableComponent } from './components/admin-scholar-table/admin-scholar-table.component';
import { AdminApplicationTableComponent } from './components/admin-application-table/admin-application-table.component';
import { AdminElpsTableComponent } from './components/admin-elps-table/admin-elps-table.component';
import { AdminWftsTableComponent } from './components/admin-wfts-table/admin-wfts-table.component';
import { AdminJourneyComponent } from './components/admin-journey/admin-journey.component';
import { AdminApplicationInfoComponent } from './components/admin-application-info/admin-application-info.component';
import { AdminEducationComponent } from './components/admin-education/admin-education.component';
import { AdminExpensesComponent } from './components/admin-expenses/admin-expenses.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminScholarsComponent } from './components/admin-scholars/admin-scholars.component';
import { AdminApplicationsComponent } from './components/admin-applications/admin-applications.component';
import { AdminAwardedComponent } from './components/admin-awarded/admin-awarded.component';
import { AdminHomeComponent } from './components/admin-home/admin-home.component';
import { AdminSideBarMenuComponent } from './components/admin-side-bar-menu/admin-side-bar-menu.component';
import { AdminNavbarComponent } from './components/admin-navbar/admin-navbar.component';
import { ScholarsCardComponent } from './components/scholars-card/scholars-card.component';
import { ApplicationsCardComponent } from './components/applications-card/applications-card.component';
import { ElpsCardComponent } from './components/elps-card/elps-card.component';
import { ExpenditureCardComponent } from './components/expenditure-card/expenditure-card.component';
import { WtfsCardComponent } from './components/wtfs-card/wtfs-card.component';

// user
import { UserProfileHomeComponent } from './components/user-profile-home/user-profile-home.component';
import { UserLoginComponent } from './components/user-login/user-login.component';
import { UserRegisterComponent } from './components/user-register/user-register.component';
import { BioFormComponent } from './components/bio-form/bio-form.component';
import { ProfileFormComponent } from './components/profile-form/profile-form.component';
import { CareerFormComponent } from './components/career-form/career-form.component';
import { EducationFormComponent } from './components/education-form/education-form.component';
import { SocialMediaFormComponent } from './components/social-media-form/social-media-form.component';
import { ImageFormComponent } from './components/image-form/image-form.component';
import { HttpServiceService } from './services/http-service.service';
import { ProfilePicComponent } from './components/profile-pic/profile-pic.component';
import { CareerComponent } from './components/career/career.component';
import { BioComponent } from './components/bio/bio.component';
import { EducationComponent } from './components/education/education.component';
import { HomeComponent } from './components/home/home.component';
import { HubsComponent } from './components/hubs/hubs.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { SideBarMenuComponent } from './components/side-bar-menu/side-bar-menu.component';
import { FeedsComponent } from './components/feeds/feeds.component';
import { PostComponent } from './components/post/post.component';
import { PostFormComponent } from './components/post-form/post-form.component';
import { MorePeopleComponent } from './components/more-people/more-people.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { AdminApplicationFormComponent } from './components/admin-application-form/admin-application-form.component';
import { AdminEducationFormComponent } from './components/admin-education-form/admin-education-form.component';
import { AdminExpenseFormComponent } from './components/admin-expense-form/admin-expense-form.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HubsComponent,
    NavbarComponent,
    SideBarMenuComponent,
    FeedsComponent,
    PostComponent,
    AdminHomeComponent,
    AdminSideBarMenuComponent,
    AdminNavbarComponent,
    ScholarsCardComponent,
    ApplicationsCardComponent,
    ElpsCardComponent,
    ExpenditureCardComponent,
    WtfsCardComponent,
    PostFormComponent,
    MorePeopleComponent,
    AdminDashboardComponent,
    AdminScholarsComponent,
    AdminApplicationsComponent,
    AdminAwardedComponent,
    UserProfileComponent,
    AdminScholarGraphComponent,
    AdminApplicationsGraphComponent,
    AdminExpenditureGraphComponent,
    AdminElpsGraphComponent,
    AdminWtfsGraphComponent,
    AdminScholarTableComponent,
    AdminApplicationTableComponent,
    AdminElpsTableComponent,
    AdminWftsTableComponent,
    UserProfileHomeComponent,
    AdminJourneyComponent,
    AdminApplicationInfoComponent,
    AdminEducationComponent,
    AdminExpensesComponent,
    UserLoginComponent,
    UserRegisterComponent,
    BioFormComponent,
    ProfileFormComponent,
    CareerFormComponent,
    EducationFormComponent,
    SocialMediaFormComponent,
    ImageFormComponent,
    ProfilePicComponent,
    CareerComponent,
    BioComponent,
    EducationComponent,
    AdminApplicationFormComponent,
    AdminEducationFormComponent,
    AdminExpenseFormComponent,
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTooltipModule,
    MatDialogModule,
    MatBadgeModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatSortModule,
    MatToolbarModule,
    MatInputModule,
    MatCardModule,
    MatSidenavModule,
    MatMenuModule,
    MatSlideToggleModule,
    MatSelectModule,
    MatOptionModule,
    MatExpansionModule,
    MatListModule,
    MatDividerModule,
    CanvasJSAngularChartsModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [HttpServiceService],
  bootstrap: [AppComponent],
})
export class AppModule {}
