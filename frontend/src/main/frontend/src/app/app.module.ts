// Imports
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

// Declarations: Services
import { SessionService } from './global/session.service';
// Declarations: Components
import { AppComponent } from './app.component';
import { UserListComponent } from './users/user-list.component';
import { UserProfileComponent } from './users/user-profile.component';
import { UserFormComponent } from './users/user-form.component';
import { NuevoComp1Component } from './nuevo-comp-1/nuevo-comp-1.component';
import { NavbarComponent } from './menus/navbar/navbar.component';
// Declarations: Routing
import { routing } from './app.routes';
import { LoginComponent } from './login/login.component';
import { Navbar2Component } from './menus/navbar-2/navbar-2.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  declarations: [
    AppComponent,
    UserListComponent,
    UserProfileComponent,
    UserFormComponent,
    NuevoComp1Component,
    NavbarComponent,
    LoginComponent,
    Navbar2Component
  ],
  providers: [
    SessionService
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
