import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {BehaviorSubject} from "rxjs/BehaviorSubject";

import {Payment} from "./Payment";

import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {ErrorObservable} from "rxjs/observable/ErrorObservable";
import "rxjs/add/operator/catch";
import {User} from "./user";


@Injectable()
export class PaymentService {

  private user = new User();

  private static api_url:string = 'https://www.mycecpayment.com:8443';

   // private static api_url:string = 'http://localhost:6471';

  private save = new BehaviorSubject<User>(this.user);
  cast = this.save.asObservable();

  constructor(private _http: HttpClient) {
  }

  getPayments(): Observable<any> {
    return this._http.get(PaymentService.api_url+'/api/payments');
  }


  getPaymentDetails(ref: string): Observable<any> {
    return this._http.get(PaymentService.api_url+'/api/payment/' + ref);
  }

  updatePaymentDetails(ref: string): Observable<any> {
    return this._http.get(PaymentService.api_url+'/api/payment/update/' + ref);
  }

  getPaymentDetailsWithStatus(ref: string): Observable<any> {
    return this._http.get(PaymentService.api_url+'/api/payment/find/' + ref);
  }

  SavePayment(user: User,payments : Payment[]): Observable<any> {
    return this._http.post(PaymentService.api_url+'/api/payment', {
      user,
      payments
      },{
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    })

  }

  updatePayment(payment: Payment): Observable<any> {
    return this._http.put(PaymentService.api_url+'/api/payment', JSON.stringify(payment), {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    })
  }

  setPayment(user) {
    this.save.next(user);
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an ErrorObservable with a user-facing error message
    return new ErrorObservable(
      'Something bad happened; please try again later.');
  };

}
