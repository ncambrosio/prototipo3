import { Component, OnInit } from '@angular/core';
import { SessionService } from '../../global/session.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private sessionService: SessionService) {
  }

  ngOnInit() {
    this.sessionService.checkSession();
  }

  doLogout() {
    this.sessionService.doLogout();
  }
}