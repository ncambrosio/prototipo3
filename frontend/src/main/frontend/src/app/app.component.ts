import { Component, NgModule } from '@angular/core';
import { User } from './users/user-model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string = 'YesShare';
  users: User[] = [
    {id: 25, name: 'Noé', username : 'Chinaski'},
    {id: 26, name: 'Patricia', username : 'Ferepelente'},
    {id: 27, name: 'Miguel', username : 'Cunetas'}
  ];

  // Usuario activo/seleccionado
  activeUser: User;

  selectUser(user) {
    this.activeUser=user;
    console.log(this.activeUser);
  }

  userCreationEventHandler(event) {
    this.users.push(event.user);
  }
}