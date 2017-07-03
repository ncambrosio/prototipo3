import { Component, OnInit } from '@angular/core';
import { SessionService } from '../../global/session.service';

@Component({
  selector: 'app-navbar-2',
  templateUrl: './navbar-2.component.html',
  styleUrls: ['./navbar-2.component.css']
})
export class Navbar2Component implements OnInit {

  constructor(private sessionService: SessionService) {
  }

  ngOnInit() {
    this.sessionService.checkSession();
  }

  doLogout() {
    this.sessionService.doLogout();
  }

}
