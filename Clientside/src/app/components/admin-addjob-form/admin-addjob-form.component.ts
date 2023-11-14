import { Component, Inject } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators, FormArray } from '@angular/forms';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogRef,
} from '@angular/material/dialog';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { AdminAddOrganizationFormComponent } from '../admin-add-organization-form/admin-add-organization-form.component';
import { ServiceService } from 'src/app/services/service.service';

@Component({
  selector: 'app-admin-addjob-form',
  templateUrl: './admin-addjob-form.component.html',
  styleUrls: ['./admin-addjob-form.component.scss'],
})
export class AdminAddjobFormComponent {
  // Declare class properties
  jobPostings: any[] = [];
  error: string = '';
  url!: string;
  jobform: FormGroup; // Form group for the application form
  qualification!: any;
  qualifications: string[] = [];
  responsibilities: string[] = [];
  responsibility!: any;
  imageUrl!: string;
  selectedImage: File | null = null; // To hold the selected image
  IevelOptions: string[] = [
    'PHD',
    'MASTERS',
    'DEGREE',
    'DIPLOMA',
    'KCSE',
    'KCPE',
    'NONE',
  ];
  organizationOptions: any;
  urlGetorganization!: string;
  organizationId: any;
  jobQualifications: FormArray;
  jobResponsibilities: FormArray;

  constructor(
    private service: ServiceService,
    private fb: FormBuilder,
    private http: HttpServiceService,
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<AdminAddjobFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {

    this.jobQualifications = this.fb.array([this.fb.control('')]);
    this.jobResponsibilities = this.fb.array([this.fb.control('')]);

    // Initialize the application form using FormBuilder
    this.jobform = this.fb.group({
      jobTitle: ['', Validators.required],
      jobDescription: ['', Validators.required],
      howToApply: ['',Validators.required],
      applicationDeadLine: [new Date(), Validators.required],
      jobType: ['',Validators.required],
      organization:['',Validators.required],
      jobSalary: [0, Validators.min(0)],
      educationLevel: ['',Validators.required],
      jobPoster: this.fb.group({
        id: [0],
        name: [''],
        type: [''],
        data: [null],
      }),
      jobQualifications: this.jobQualifications,
      jobResponsibilities: this.jobResponsibilities, // Initialize with one empty control
    });
  }




  private setFormArrayValues(formArray: FormArray, values: any[]) {
    formArray.clear(); // Clear existing values
    if (values && values.length > 0) {
      values.forEach((value) => {
        formArray.push(this.fb.control(value));
      });
    }
  }
    
  ngOnInit() {
    // Initialize URL for HTTP request
    // this.getorganization();
  }

  onSubmit() {
    const formData = this.jobform.value;
    const fd = new FormData();
  
    
      // Append all the form data fields
      fd.append('jobTitle', formData.jobTitle);
      fd.append('jobDescription', formData.jobDescription);
      fd.append('howToApply', formData.howToApply);
      // Assuming that formData.applicationDeadLine is a date string, parse it into a Date object
     fd.append('applicationDeadLine', new Date(formData.applicationDeadLine).toISOString());
    
      fd.append('jobType', formData.jobType);
      fd.append('organization', formData.organization);
      fd.append('jobSalary', formData.jobSalary);
      fd.append('educationLevel', formData.educationLevel);
  
      // Append qualifications and responsibilities as JSON strings
      fd.append('jobQualifications', formData.jobQualifications);
      fd.append('jobResponsibilities', formData.jobResponsibilities);
      if (this.selectedImage) {
      // Append the image
      fd.append('jobPoster', this.selectedImage, this.selectedImage.name);
    }else{
      fd.append('jobPoster','');

    }
  
    this.service.postJobPosting(fd).subscribe(
      (response) => {
        console.log('Data posted:', response);
      },
      (error) => {
        this.error = 'Error posting data: ' + error.message;
      }
    );
  }
  

  onFileChange(event: any) {

    if (event.target.files && event.target.files.length) {
      this.selectedImage = event.target.files[0];
      const fileNameParts = this.selectedImage!.name.split('.');
      const imageFormat = fileNameParts[fileNameParts.length - 1];
  
      console.log('Image format:', imageFormat); // Log the image format
  
      this.jobform.get('jobPoster.data')!.setValue(this.selectedImage);
  
      // Optionally, you can preview the image in your UI if needed.
      this.imageUrl = URL.createObjectURL(this.selectedImage!);
    }
  }
  
  addQualification() {
    if (this.qualification !== undefined) {
      const qualificationsArray = this.jobform.get('jobQualifications') as FormArray;
      qualificationsArray.push(this.fb.control(this.qualification));
      this.qualifications.push(this.qualification);
      this.qualification = undefined;
    }
  }
  
  addResponsibilities() {
    if (this.responsibility !== undefined) {
      const responsibilitiesArray = this.jobform.get('jobResponsibilities') as FormArray;
      responsibilitiesArray.push(this.fb.control(this.responsibility));
      this.responsibilities.push(this.responsibility);
      this.responsibility = undefined;
    }
  }
  
  
  // addQualification() {
  //   if (this.qualification !== undefined) {
  //     this.jobform.value.jobQualifications.push(this.qualification);
  //     this.qualifications.push(this.qualification);
  //     this.qualification = undefined;
  //   }
  // }

  // addResponsibilities() {
  //   if (this.responsibility !== undefined) {
  //     this.jobform.value.jobResponsibilities.push(this.responsibility);
  //     this.responsibilities.push(this.responsibility);
  //     this.responsibility = undefined;
  //   }
  // }

  // open bio dialog
  addorganizationDialog() {
    // Open the dialog using the MatDialog service
    const dialogRef: MatDialogRef<AdminAddOrganizationFormComponent> =
      this.dialog.open(AdminAddOrganizationFormComponent, {
        width: '60%', // Set the width of the dialog
        data: { data: '' }, // You can pass data to the dialog component using the `data` property
      });

    // Handle the dialog result (if needed)
    dialogRef.afterClosed().subscribe((result) => {
      console.log(`Dialog result: ${result}`);
      this.ngOnInit();
    });
  }

  // Fetch branch data from the server
  getorganization() {
    this.urlGetorganization = this.http.serverUrl + 'organization/all'; // URL to fetch organization data
    this.http.getData(this.urlGetorganization).subscribe({
      next: (response) => {
        console.log('organization id', response.id);
        this.organizationOptions = response; // Set organizationOptionss array
        console.log(this.organizationOptions);
        this.organizationId = response.id;
      },
      error: (error) => {
        console.log('Error:', error);
      },
      complete: () => {},
    });
  }
}
