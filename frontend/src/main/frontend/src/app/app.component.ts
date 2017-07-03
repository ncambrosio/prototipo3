import { Component, NgModule } from '@angular/core';
import { User } from './users/user-model';
import { UserService } from './users/user-service';
import { SessionService } from './global/session.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [UserService]
})
export class AppComponent {

  constructor(private sessionService: SessionService) {
  }

  ngOnInit() {
    this.sessionService.checkSession();
    //this.settings$ = this.settingsService.settings$;
  }

  doLogout() {
    this.sessionService.doLogout();
  }
}