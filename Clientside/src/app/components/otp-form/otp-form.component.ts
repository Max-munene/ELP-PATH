import { Component } from '@angular/core';

@Component({
  selector: 'app-otp-form',
  templateUrl: './otp-form.component.html',
  styleUrls: ['./otp-form.component.scss']
})
export class OtpFormComponent {
  ngOnInit(){
    this.otpget = this.obj.otp.split("")
  }obj={
    otp: "123456"
  }
  otpget: any = [];

}
