import { Component, ViewChild, Inject } from '@angular/core';
import { HttpServiceService } from 'src/app/services/http-service.service';
import { FormBuilder, FormGroup, NgForm, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-education-form',
  templateUrl: './education-form.component.html',
  styleUrls: ['./education-form.component.scss'],
})
export class EducationFormComponent {
  @ViewChild('eduform', { static: false }) eduform!: NgForm;
  course!: string;
  userId!: string;
  eduForm!: FormGroup;
  isChecked: boolean = true;
  urlGetInstitution!: string;
  urlGetCluster!: string;
  urlGetClusterCourse!: string;
  institutionOptions!: any[];
  courses!: any[];
  clusters!: any[];
  grades: string[] = ['First Class Honors', 'Scond Class Honors Upper D'];
  clusterOptions!: any[];
  courseOptions!: any[];

  constructor(
    private fb: FormBuilder,
    private http: HttpServiceService,
    public dialogRef: MatDialogRef<EducationFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    // Create a reactive form using FormBuilder
    this.eduForm = this.fb.group({
      school_name: ['', Validators.required],
      clusters: [''],
      course: ['', Validators.required],
      grade: ['', Validators.required],
      start_date: ['', Validators.required],
      completion_date: ['', Validators.required],
    });
  }

  url!: string;
  ngOnInit() {
    const storedData = localStorage.getItem('userData');
    if (this.data.data) {
      this.userId = this.data.data.toString();
      // Use the parsed data in your application
      this.url = this.http.serverUrl + 'education/' + this.userId + '/create';
    }
    this.getInstitution();
    this.getCluster();
  }

  // Fetch Institution data from the server
  getInstitution() {
    this.urlGetInstitution = this.http.serverUrl + 'education/universities/all'; // URL to fetch insitiutuion data
    this.http.getData(this.urlGetInstitution).subscribe({
      next: (response) => {
        console.log('Universities', response);
        this.institutionOptions = response.payload; // Set Institution options array
        console.log('Institution ', this.institutionOptions);
      },
      error: (error) => {
        console.log('Error:', error);
      },
      complete: () => {},
    });
  }

  getCluster() {
    // Set the URL to fetch cluster data
    this.urlGetCluster = this.http.serverUrl + 'education/course-clusters/all';

    // Send an HTTP GET request to fetch cluster data
    this.http.getData(this.urlGetCluster).subscribe({
      next: (response) => {
        // When the request is successful, store the cluster data in the 'clusterOptions' variable
        this.clusterOptions = response.payload;
        console.log('Cluster ', this.clusterOptions);
      },
      error: (error) => {
        // Handle and log any errors that occur during the request
        console.log('Error:', error);
      },
      complete: () => {},
    });
  }

  getCourses() {
    // Log the selected cluster ID from a form
    console.log('CLuster ID', this.eduForm.value.cluster);

    // Get the selected cluster ID from the form and convert it to a string
    const clusterId = this.eduForm.value.clusters;

    // Call the 'getClusterCourse' function to fetch course data for the selected cluster
    this.getClusterCourse(clusterId.toString());
  }

  getClusterCourse(clusterId: string) {
    // Set the URL to fetch course data for the selected cluster
    const urlGetClusterCourses =
      this.http.serverUrl + `education/course-clusters/${clusterId}`;

    // Send an HTTP GET request to fetch course data for the selected cluster
    this.http.getData(urlGetClusterCourses).subscribe({
      next: (response) => {
        // When the request is successful, store the course data in the 'courseOptions' variable
        this.courseOptions = response.payload.courses;

        // Update the 'courses' variable with the course options
        this.courses = this.courseOptions;
        console.log('Courses ', this.courses);
      },
      error: (error) => {
        // Handle and log any errors that occur during the request
        console.log('Error:', error);
      },
      complete: () => {},
    });
  }

  submit() {
    console.log('Form Submitted !', this.eduform.value);
    this.http.postData(this.url, this.eduform.value).subscribe({
      next: (response) => {
        console.log('POST request successful:', response);
        console.log(this.eduform.value);
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
