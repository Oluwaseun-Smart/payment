import {Component, OnInit, OnDestroy} from '@angular/core';

import {PaymentService} from "../shared/payment-service.service";
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";
import {NgProgress} from "ngx-progressbar";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorObservable} from "rxjs/observable/ErrorObservable";
import {User} from "../shared/user";


@Component({
  selector: 'app-confirm',
  templateUrl: './confirm.component.html',
  styleUrls: ['./confirm.component.css']
})
export class ConfirmComponent implements OnInit {

  private user = new User();


  private transaction: string;

  loading = false;


  //live key
 private key: string = 'FLWPUBK-a0444a587f62dda262292d9e0544efa5-X';

  // test key
  //  private key: string = 'FLWPUBK-ae7a3116aee44a9f43eb9eee7d91fbe9-X';

  showconfirm: boolean;

  showsuccess: boolean;


  constructor(private paymentService: PaymentService, private toastrService: ToastrService, private router: Router, public ngProgress: NgProgress) {

  }

  ngOnInit() {
    this.loading = false;

    this.showconfirm = true;
    this.showsuccess = false;


    this.paymentService.cast.subscribe(user => this.user = user);
  }


  cancelledPayment() {
    console.log('Payment Cancel ');
    this.toastrService.error('Transaction cancelled', 'Failed');
    this.loading = false;
  }

  confirmPayment(response: object) {
    this.loading = false;
    console.log(response);
    this.transaction = response['tx']['txRef'];
    this.paymentService.updatePaymentDetails(this.transaction).subscribe((user) => {

        this.user = user;

        this.paymentService.setPayment(user);

        this.toastrService.success('Payment Successfully Made!!!', 'Done!!!');


      }, (error: HttpErrorResponse) => {
        if (error.error instanceof ErrorEvent) {
          // A client-side or network error occurred. Handle it accordingly.
          console.error('An error occurred:', error.error.message);
          this.toastrService.error('Network issue...Please try again later', 'Bad Network!!!');
        } else {
          // The backend returned an unsuccessful response code.
          // The response body may contain clues as to what went wrong,
          console.error(
            `Backend returned code ${error.status}, ` +
            `body was: ${error.error}`);
          this.toastrService.error('The Server is down...Please try again later', 'Failed!!!');
        }
        // return an ErrorObservable with a user-facing error message
        return new ErrorObservable(
          'Something bad happened; please try again later.');
      }
    ),

      this.showconfirm = false;
    this.showsuccess = true;


  }



  showMessage(){
    this.loading = true;
  }

}
