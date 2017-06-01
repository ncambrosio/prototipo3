import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { User } from './user-model';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class UserService {
	constructor (private http: Http) {
	}

	public getUserList() : Observable<User[]> {
		return this.get("http://localhost:8080/user/list")
						.map((res:any) => res.json())
						.catch((error:any) => Observable.throw(error.json().error || 'Server error'));
	}

	public getUser() : Observable<User> {
		return this.http.get("http://localhost:8080/user/1")
						.map((res:any) => res.json())
						.catch((error:any) => console.log(error) || Observable.throw(error.json().error || 'Server error'));
	}

	get(url: string, user?: User): Observable<any> {
      let username: string = 'admin';
      let password: string = '1234';
      let headers: Headers = new Headers();
      headers.append("Authorization", "Basic " + btoa(username + ":" + password)); 
      //headers.append("Content-Type", "application/x-www-form-urlencoded");
      return this.http.get(url, {headers: headers})
    }

	post(url: string, user?: User): Observable<any> {
      let username: string = 'admin';
      let password: string = '1234';
      let headers: Headers = new Headers();
      headers.append("Authorization", "Basic " + btoa(username + ":" + password)); 
      //headers.append("Content-Type", "application/x-www-form-urlencoded");
      return this.http.post(url, {headers: headers})
    }

    createNewUser(user: User): Observable<any> {
      let url: string = "http://localhost:8080/user/";
  	  let username: string = 'admin';
      let password: string = '1234';
      let headers: Headers = new Headers();
      headers.append("Authorization", "Basic " + btoa(username + ":" + password)); 
      headers.append("Content-Type", "application/json");
      return this.http.post(url, JSON.stringify(user), {headers: headers});
	}

	private extractData(res) {
	    let body;
	    if (res.text()) {
	        body = res.json();
	    }
	    return body || {};
	}

    private handleError(error:any) {
        console.error(error);
        return Observable.throw(error);
    }
}