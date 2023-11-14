import { Component ,OnInit, ElementRef, Renderer2, QueryList, AfterViewInit, HostListener} from '@angular/core';
import { faPlus, faClock } from '@fortawesome/free-solid-svg-icons';
import { ServiceService } from 'src/app/services/service.service';
import { trigger, state, style, animate, transition } from '@angular/animations';

type YourItemType = {
  faq: string;
  counts: string;
  isHidden: boolean;
};
@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss'],
  animations: [ 
    trigger('count', [
      state('initial', style({ opacity: 0, transform: 'translateY(20px)' })),
      state('final', style({ opacity: 1, transform: 'translateY(0)' })),
      transition('initial => final', animate('1s ease-in-out')),
    ]),
  ],
})
export class LandingPageComponent implements OnInit , AfterViewInit{
 
  public faPlus=faPlus;
  public faClock=faClock;
  public panelOpenState:boolean = false;
  public item_nav_hidden: boolean=false;
  jobList: any[] = [1, 2, 3];
  public list:any[] =[{'img':'/assets/images/landing_page/elimuv22.jpg','width':15},
  {'img':'/assets/images/landing_page/PAN7953-1-1536x1020.jpg','width':10},
  {'img':'/assets/images/landing_page/pexels-katerina-holmes-5905902-removebg-preview (1).png','width':8},
  {'img':'/assets/images/landing_page/dr-mwangi-wtf-2020.jpg','width':3}]
  cards:any[]=[{image:'/assets/images/landing_page/wings-to-fly.png',scholar:'Number of Wings to Fly scholars',counts: 1000, state: 'initial' }, 
               {image:'/assets/images/landing_page/download__1_-removebg-preview (1).png',scholar:'Enrolled TVET scholars',counts: 2000,  state: 'initial'},
               {image:'/assets/images/landing_page/elp.png',scholar:'Equity Leaders Program Scholars',counts: 3000,  state: 'initial' },
               {image:'/assets/images/landing_page/Elimu-removebg-preview.png',scholar:'Registered Elimu Scholar',counts: 4000,  state: 'initial' }]
  faqs: YourItemType[]=[{'faq':'How can I discover job opportunities ?','counts':' provides a dedicated job board where you can find part-time jobs, internships, and other employment opportunities tailored to students. Browse listings and apply directly through the platform.',isHidden: true }, 
               {'faq':' Is the website free to use?','counts':'The site is designed to help the ELPs got connected to other ELPs all over the world', isHidden: true  },
               {'faq':'How do I report inappropriate content or behavior on the platform?','counts':'Your safety is important to us. If you encounter any inappropriate content or behavior, you can report it through our platform. Our moderation team will review and take appropriate action.',isHidden: true  },
               {'faq':'How can I reset my password if I forget it?','counts':"If you forget your password, simply click on the Forgot Password link on the login page. Well guide you through the process of resetting your password", isHidden: true }]
  
//   spotlight: any[]= [  {'img':'/assets/images/landing_page/elimuv22.jpg','date':'October 28, 2020','spotLight': 'The Activity Engaged', 'par':'Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptas consequuntur ipsa sunt,' },
//   {'img':'/assets/images/landing_page/elimuv22.jpg','date':'October 28, 2020','spotLight': 'The Activity Engaged', 'par':'Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptas consequuntur ipsa sunt,' },
//   {'img':'/assets/images/landing_page/elimuv22.jpg','date':'October 28, 2020','spotLight': 'The Activity Engaged', 'par':'Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptas consequuntur ipsa sunt,' },
//    {'img':'/assets/images/landing_page/elimuv22.jpg','date':'October 28, 2020','spotLight': 'The Activity Engaged', 'par':'Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptas consequuntur ipsa sunt,' },

// ]

spotlight:any[]=[]

  jobPostings: any;;
  private sectionInView = false;
  imageSrc: string='';
  
  
constructor(private service:ServiceService, private renderer: Renderer2, private elementRef: ElementRef){
  
}
@HostListener('window:scroll', ['$event'])
  onScroll(event: Event) {
    const sectionElement = this.elementRef.nativeElement.querySelector('.cards');
    const sectionOffset = sectionElement.getBoundingClientRect().top;

    // You can adjust this offset threshold as needed
    const offsetThreshold = window.innerHeight - 100;

    if (sectionOffset <= offsetThreshold && !this.sectionInView) {
      this.sectionInView = true;
      this.startCountingAnimation();
    }
  }

ngOnInit(): void {
  this.getJobPostings();
  this.getGetSpotlight()
 ;
}
ngAfterViewInit() {
  this.startCountingAnimation();
}

isOpen(item:any){
  item.isHidden=!item.isHidden;

}
getJobPostings(){
  this.service.getJobOpportunities().subscribe(
    (response) => {
      // Handle the response, which should be an array of job postings
      this.jobPostings = response;
      
      console.log(response)

})}


getGetSpotlight(){
    this.service.getSpotlight().subscribe(
      (res: any)=>{
        this.spotlight=res.payload
        console.log(res.payload) 
      }
    )
}



startCountingAnimation() {
  this.cards.forEach((item, index) => {
    const countElement = document.getElementsByClassName('counts')[index];
    const finalValue = item.counts;
    this.animateCountUp(countElement, finalValue, 1000); // Adjust the duration as needed
  });
}

animateCountUp(element: Element, finalValue: number, duration: number) {
  const startTime = Date.now();
  const initialValue = 1;
  const interval = 1000 / 60; // 60 frames per second

  const animate = () => {
    const currentTime = Date.now();
    const elapsedTime = currentTime - startTime;

    if (elapsedTime < duration) {
      const progress = elapsedTime / duration;
      const count = Math.floor(initialValue + progress * (finalValue - initialValue));
      this.renderer.setProperty(element, 'textContent', count);
      setTimeout(animate, interval);
    } else {
      this.renderer.setProperty(element, 'textContent', finalValue);
    }
  };

  animate();
}}