import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpServiceService } from 'src/app/services/http-service.service';

@Component({
  selector: 'app-admin-chapters',
  templateUrl: './admin-chapters.component.html',
  styleUrls: ['./admin-chapters.component.scss'],
})
export class AdminChaptersComponent {
  dataUrl!: string;
  options: string[] = ['chapterId', 'chapterName'];
  selectedOption: string | null = null;
  showOptionsList: boolean = false;
  constructor(public http: HttpServiceService,     private fb:FormBuilder,
    ) {}

  chapterData: any[] = [];
  filterform!: FormGroup;
  filteredChapterData: any[] = [];
  filterText: string = '';
  selectedFilter: string = '';
  selected='';
  showFilterInput: boolean = false;
 

  ngOnInit() {
    this.filterform=this.fb.group({
    chapterId: ["",[Validators.required]],
    chapterName: ["",[Validators.required]],
    })
    this.getAllChapters();
    // this.getChapters()
  }

  // method to get all chapters
  getAllChapters() {
    const getChaptersUrl = this.http.serverUrl + 'chapters/all';
    //===================get method ========================
    this.http.getData(getChaptersUrl).subscribe({
      next: (response) => {
        console.log(response);
        this.chapterData = response.map((item: any) => ({
          chapterName: item.chapterName,
          chapterDescription: item.chapterDescription,
          imageUrl:
            item.chapterImage !== null
              ? 'data:' +
                item.chapterImage.type +
                ';base64,' +
                item.chapterImage.data
              : null,
          // chapterType: item.chapterType.chapterTypeName,
          // chapterId: item.id.toString(),
        }));
        console.log(this.chapterData);
      },
      error: (error) => {
        console.log(error);
      },
      complete: () => {},
    });
  }
  applyFilter(filterValue:string) {
    filterValue = this.filterText.toLowerCase();

   // Use Array.filter to create a filtered array
   this.filteredChapterData = this.chapterData.filter((data) => {
     const chapterName = data.chapterName.toLowerCase();
     const chapterId = data.chapterId.toLowerCase();

     // Check if chapterName or chapterId contains the filter text
     return chapterName.includes(filterValue) || chapterId.includes(filterValue);
   });
 }


  toggleOptionsList() {
    this.showOptionsList = !this.showOptionsList;
  }

  selectOption(option: string) {
    this.selectedOption = option;
    this.showOptionsList = false;
  }

  filterOptions(event: any) {
    const searchTerm = event.target.value;
    this.options = this.options.filter((option) => option.toLowerCase().includes(searchTerm.toLowerCase()));
  }

}
