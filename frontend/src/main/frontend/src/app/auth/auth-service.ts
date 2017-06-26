import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions } from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';

@Injectable()
export class AuthService {
	constructor (private http: Http) {
	}

	public getLoguedUser() : Observable<any> {
		return this.get("http://localhost:8080/principal")
						.map((res:any) => res.json())
						.catch((error:any) => Observable.throw(error.json().error || 'Server error'));
	}

    public logout() : Observable<boolean> {
        let body = JSON.stringify({});
        //let csrfcookie = Cookie.getCookie('csrftoken');
        let headers = new Headers({
            //'X-CSRFToken': csrfcookie,
            'Content-Type': 'application/json'
        });
        let options = new RequestOptions({headers: headers});
        return this.http.post("http://localhost:8080/logout", body, options)
                        .map(res => { 
                        		console.log("AuthService.doLogout(): " + res);
                        		return true; 
                        	} 
                        )
                        .catch(this.handleError);

    }

  private handleError (error: Response) {
      console.error(error);
      return Observable.throw(error || 'Server error');
  }

	private get(url: string): Observable<any> {
      let username: string = 'admin';
      let password: string = '1234';
      let headers: Headers = new Headers();
      headers.append("Authorization", "Basic " + btoa(username + ":" + password)); 
      return this.http.get(url, {headers: headers})
    }

	private post(url: string): Observable<any> {
      let username: string = 'admin';
      let password: string = '1234';
      let headers: Headers = new Headers();
      headers.append("Authorization", "Basic " + btoa(username + ":" + password)); 
      return this.http.post(url, {headers: headers})
    }
}