import { Component, NgModule } from '@angular/core';
//import { Router } from '@angular/router';
import { User } from './users/user-model';
import { UserService } from './users/user-service';
import { AuthService } from './auth/auth-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [UserService
              , AuthService
              //, Router
              ]
})
export class AppComponent {

  loguedUser: User;
  loguedUsername: string;
  authenticated: boolean;

  constructor(	private authService: AuthService, 
  				//private router: Router
    		 )
  {

  }
  
  ngOnInit() {
    this.isUserLogued();
  }

  private isUserLogued() {
    this.authService.getLoguedUser()
    	.subscribe(
          data => {
          	  console.log("isUserLogued().data: " + data);
              this.loguedUsername = data.userAuthentication.details.name;
              this.authenticated = true;
          },
          err => {
              console.log("isUserLogued().err: " + err);
              this.loguedUsername = "N/A";
              this.authenticated = false;
          },
          () => console.log("isUserLogued().END"));
  }

  doLogout() {
  	this.authService.logout()
  		.subscribe(
          data => {
          	  console.log("doLogout().data: " + data);
              this.loguedUsername = "N/A";
              this.authenticated = false;
              //this.router.navigate(['/'], { queryParams: { returnUrl: '/'}});
          },
          err => {
            console.log("doLogout().err :" + err);
          },
          () => console.log("doLogout().END"));
  }
}