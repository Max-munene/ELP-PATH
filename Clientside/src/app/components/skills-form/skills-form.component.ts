import { Component, ViewChild, Inject } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { NgForm } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
@Component({
  selector: 'app-skills-form',
  templateUrl: './skills-form.component.html',
  styleUrls: ['./skills-form.component.scss'],
})
export class SkillsFormComponent {
  @ViewChild('skillsform', { static: false }) skillsform!: NgForm;
  end_date!: string;
  start_date!: string;
  description!: string;
  companyName!: string;
  title!: string;
  userId!: string;
  constructor(
    private http: HttpServiceService,
    public dialogRef: MatDialogRef<SkillsFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}
  url!: string;
  ngOnInit() {
    console.log('user id in bio', this.data.data.toString());

    if (this.data.data) {
      this.userId = this.data.data.toString();
      // Use the parsed data in your application
      this.url = this.http.serverUrl + 'skills/' + this.userId + '/create';
    }
  }
  submit() {
    console.log('Form Submitted !', this.skillsform.value);
    this.http.postData(this.url, this.skillsform.value).subscribe({
      next: (response) => {
        console.log('POST request successful:', response);
        console.log(this.skillsform.value);
        // Handle the response data here
        // localStorage.setItem('token', JSON.stringify(response));
        this.dialogRef.close();
      },
      error: (error) => {
        console.log('Error:', error);
        // Handle the error here
      },
      complete: () => {},
    });
  }
}
