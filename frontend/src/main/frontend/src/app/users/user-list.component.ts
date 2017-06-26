import { Component, NgModule } from '@angular/core';
import { User } from './user-model';
import { UserService } from './user-service';

@Component({
  selector: 'user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
  providers: [UserService]
})
export class UserListComponent {

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