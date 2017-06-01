import { Component, NgModule } from '@angular/core';
import { User } from './users/user-model';
import { UserService } from './users/user-service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [UserService]
})
export class AppComponent {

  users: User[];
  title: string = 'YesShare';
  activeUser: User;

  constructor(private userService: UserService) {

  }
  
  ngOnInit() {
    this.updateUserList();
    /*
    this.userService.getUser()
                    .subscribe(
                      data => console.log(data),
                      err => console.log(err),
                      () => console.log("Listo User simple!"));
    */
  }

  selectUser(user) {
    this.activeUser=user;
    console.log(this.activeUser);
  }

  userCreationEventHandler(event) {
    //this.users.push(event.user);
    this.userService.createNewUser(event.user)
                    .subscribe(
                      data => console.log(data),
                      err => console.log("Create User ERROR: " + err),
                      () => this.updateUserList()
                    );
  }

  updateUserList(filtro? : string) {
    this.userService.getUserList()
                .subscribe(
                  data => console.log(this.users = data),
                  err => console.log("Error en lista: " + err),
                  () => console.log("Listo Users!"));
  }
}