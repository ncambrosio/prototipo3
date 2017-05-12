import { Component, Input } from '@angular/core';
import { User } from './user-model';

@Component({
  selector: 'user-profile',
  template: `
	<div class="jumbotron" *ngIf="user">
		<h2> {{user.username}} <small>{{user.name}}</small> </h2>
		<input class="form-control" [(ngModel)]="user.name">
    </div>
  `
})
export class UserProfileComponent {
	@Input() user: User;
}