import { Component } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { Router } from '@angular/router';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import * as moment from 'moment';
import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
} from '@angular/material/dialog';
import { TermsAndConditionsComponent } from '../terms-and-conditions/terms-and-conditions.component';

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss'],
})
export class UserLoginComponent {
  constructor(
    private http: HttpServiceService,
    private router: Router,
    private fb: FormBuilder,
    public dialog: MatDialog
  ) {}
  ngOnInit() {
    //  console.log('user id in bio', this.data.data.toString());
  }

  // Properties
  url: string = this.http.serverUrl;
  error: string = '';
  register: string = '';
  scholarVerify: string = '';
  login: string = '';
  data: any;
  hide: boolean = false;

  // Forms using FormBuilder
  scholarVerForm = this.fb.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    highSchool: this.fb.group({
      schoolName: [''],
    }),
    branch: this.fb.group({
      branchName: ['', Validators.required],
    }),
    dob: ['', [Validators.required, this.dateValidator]],
  });

  //Date validator function
  dateValidator(control: AbstractControl): { [key: string]: boolean } | null {
    if (control.value && isNaN(Date.parse(control.value))) {
      return { invalidDate: true };
    }
    return null;
  }

  forgotPfForm = this.fb.group({
    firstName: [''],
    secondName: [''],
    school: [''],
    branch: [''],
    dob: [''],
    email: [''],
  });

  loginRegForm = this.fb.group({
    userEmail: ['', [Validators.email, Validators.required]],
    userPassword: ['', [Validators.required]],
  });

  // loginRegForm = this.fb.group({
  //   userEmail: ['', [Validators.email, Validators.required]],
  //   userPassword: ['', [Validators.required]],
  // });
  additionalRegForm = this.fb.group({
    userName: [''],
    userEmail: ['', [Validators.email, Validators.required]],
    userPassword: ['', [Validators.required]],
    confirmPassword: [''],
    data: [''],
  });

  pfForm = this.fb.group({
    pfNo: ['', [Validators.required]],
  });
  tcformDialog(): void {
    // Open the dialog using the MatDialog service
    const dialogue: MatDialogRef<TermsAndConditionsComponent> =
      this.dialog.open(TermsAndConditionsComponent, {
        width: '35%', // Set the width of the dialog
        // data: { data: this.userId }, // You can pass data to the dialog component using the `data` property
      });

    // Handle the dialog result (if needed)
    dialogue.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      this.ngOnInit();
    });
  }

  forgotPfSubmit() {}

  // Method to submit scholar verification data
  scholarVerSubmit() {
    const formData = {
      firstName: this.scholarVerForm.value.firstName,
      lastName: this.scholarVerForm.value.lastName,
      highschool: this.scholarVerForm.value.highSchool?.schoolName,
      branch: this.scholarVerForm.value.branch?.branchName,
      dob: moment(this.scholarVerForm.value.dob).format('YYYY-MM-DD'),
    };

    const url = this.url + 'join-request/alumni/verify-details';

    this.http.postScholarDataForVerification(url, formData).subscribe({
      next: (response) => {
        if (response.success) {
          this.register = 'register';
          this.data = formData;
        } else {
          this.error = 'Verification Failed, Contact us.';
          console.log('ErrorMSG', this.error);
        }
      },
      error: (error) => {
        this.error =
          error.error.message || 'Error occurred during verification.';
        console.log('Errormsg', this.error);
      },
      complete: () => {},
    });
  }

  displayedForm: string = ''; // Variable to track which form to display

  showForm(forgotPf: string) {
    this.displayedForm = 'forgotPf'; // Set which form to display based on the button click
  }
  // Method to submit login data to server
  loginSubmit() {
    console.log(this.loginRegForm);

    //send the from data to the server for validation
    this.http
      .postData(this.url + 'auth/authenticate', this.loginRegForm.value)
      .subscribe({
        next: (response) => {
          console.log('request successful:', response);
          localStorage.setItem('userData', JSON.stringify(response));
          //redirect to home page if authenticated
          this.router.navigate(['/home']);
        },
        error: (error) => {
          // error message handling
          if (error.error.errorMessage) {
            this.error = error.error.errorMessage;
          } else {
            this.error = 'Server connection error';
          }
          console.log('Error:', error);
        },
        complete: () => {},
      });
  }

  password: any = document.getElementById('password');
  confirm_password: any = document.getElementById('confirmPassword');

  validatePassword() {
    if (this.password.value != this.confirm_password.value) {
      this.confirm_password.setCustomValidity("Passwords Don't Match");
    } else {
      this.confirm_password.setCustomValidity('');
    }
  }

  // this.password.onchange.validatePassword();
  // this.confirm_password.onkeyup.validatePassword();

  // Method to submit register data to server
  registerSubmit() {
    if (this.additionalRegForm.valid) {
      const obj = {
        username: this.additionalRegForm.value.userName || null,
        email: this.additionalRegForm.value.userEmail || null,
        password: this.additionalRegForm.value.userPassword || null,
        // data: this.additionalRegForm.value.data || null,
        pfNumberOrScholarCode: this.data,
      };

      const url = this.url + `join-request/alumni/register`;

      this.http.postData(url, obj).subscribe({
        next: (response) => {
          this.register = '';
          this.login = 'login';
          console.log('Request successful:', response);
        },
        error: (error) => {
          console.log('Error:', error);
        },
        complete: () => {
          // You can add finalization logic here if needed
        },
      });
    } else {
      // Handle invalid form submission (optional)
      console.log('Form is invalid. Cannot submit.');
    }
  }

  // Method to submit PF number
  pfSubmit() {
    const url = this.url + `join-request/${this.pfForm.value.pfNo}/join`;

    this.http.postNoData(url).subscribe({
      next: (response) => {
        if (!response.success) {
          this.scholarVerify = 'scholarVerify';
          this.data = this.pfForm.value.pfNo;
        } else if (response.success && response.message.includes('Login')) {
          this.login = 'login';
        } else {
          this.error = 'Invalid PF/SCH Code';
          console.log('ErrorMSG', this.error);
        }
      },
      error: (error) => {
        this.error =
          error.error.message || 'Error occurred during PF submission.';
        console.log('Errormsg', this.error);
      },
      complete: () => {},
    });
  }
}
