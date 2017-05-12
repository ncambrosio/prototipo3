import { Component, Output, EventEmitter } from '@angular/core';
import { User } from './user-model';

@Component({
	selector: 'user-form',
	styles: [`
		form {
			padding: 10px;
			background: #ECF0F1;
			border-radius: 3px;
		}
	`],
	template: `
		<form #form="ngForm" (ngSubmit)="onSubmit()" *ngIf="ready">

			{{form.valid}}

			<div class="form-group" [ngClass]="{'has-error': name.invalid && name.touched}">
				<input type="text" class="form-control" placeholder="Name"
					name="name" required
					[(ngModel)]="newUser.name" #name="ngModel"
				/>
				<span class="help-block" *ngIf="name.invalid && name.touched">Name is required</span>
			</div>
			<div class="form-group" [ngClass]="{'has-error': username.invalid && username.touched}">
				<input type="text" class="form-control" placeholder="Username"
					name="username" required
					[(ngModel)]="newUser.username" #username="ngModel" />
				<span class="help-block" *ngIf="username.invalid && username.touched">Username is required</span>
			</div>

			<button type="submit" class="btn btn.lg btn-block btn-primary"
				[disabled]="form.invalid">
				Create User
			</button>
		</form>
	`
})
export class UserFormComponent {
	@Output() userCreationEvent = new EventEmitter();
	newUser: User = new User();
	ready: boolean = true;

	onSubmit() {
		// show the event that user was created
		this.userCreationEvent.emit({user: this.newUser}); // lanzar evento
		console.log(this.newUser);
		
		this.newUser = new User();
		this.ready = false;
		setTimeout(()=>this.ready = true, 0);
	}
}