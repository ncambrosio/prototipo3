import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { UserListComponent } from './users/user-list.component';
import { UserProfileComponent } from './users/user-profile.component';
import { UserFormComponent } from './users/user-form.component';
import { NuevoComp1Component } from './nuevo-comp-1/nuevo-comp-1.component';
import { AuthComponent } from './auth/auth.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserProfileComponent,
    UserFormComponent,
    NuevoComp1Component,
    AuthComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
