import { Component, OnInit } from '@angular/core';
import { ServiceService } from 'src/app/services/service.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-admin-add-spoltlight',
  templateUrl: './admin-add-spoltlight.component.html',
  styleUrls: ['./admin-add-spoltlight.component.scss']
})
export class AdminAddSpoltlightComponent implements OnInit{
  postDataForm: FormGroup<any> = new FormGroup({
    title: new FormControl(''),
    content: new FormControl(''),
    image: new FormControl('') // Initialize the 'image' FormControl
  });
  imageUrl!: string;
  constructor(private http: ServiceService, private formbuilder: FormBuilder){
    this.postDataForm = this.formbuilder.group({
      title: ['', Validators.required],
    content: ['', Validators.required],
    image: this.formbuilder.group({
      id: [0],
      name: [''],
      type: [''],
      data: [''],
    }),        
    });
  }
  

  ngOnInit(): void {
    
  }

  onSubmit() {
    if (this.postDataForm.valid) {
      const formData = new FormData();
      formData.append('title', this.postDataForm.get('title')!.value);
      formData.append('content', this.postDataForm.get('content')!.value);
      const imageControl = this.postDataForm.get('image');
      if (imageControl && imageControl.value) {
        formData.append('image', imageControl.value);
      }
      this.http.postSpotlight(formData).subscribe(
        (res)=>{
          console.log('Post request successful', res)
        }, (error)=>{
          console.error('Post request error', error)
        }
      )

      // Continue with your HTTP request to post the data using the ServiceService
    }
  }
  
  
  onFileSelected(event: any) {
    const input = event.target;
    if (input.files.length > 0) {
      const selectedFile = input.files[0];
      // Here you can update specific properties within the 'image' FormGroup
      this.postDataForm.get('image.id')!.setValue(1);
      this.postDataForm.get('image.name')!.setValue(selectedFile.name);
      this.postDataForm.get('image.type')!.setValue(selectedFile.type);
  
      // If you want to store the binary data (base64 or other format) of the selected image
      const reader = new FileReader();
      reader.onload = () => {
        this.postDataForm.get('image.data')!.setValue(reader.result);
      };
      reader.readAsDataURL(selectedFile);
    }}
  }
  
  

