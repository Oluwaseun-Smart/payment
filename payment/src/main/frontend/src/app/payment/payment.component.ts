import {Component, OnInit} from '@angular/core';

import {ToastrService} from "ngx-toastr";
import {NgForm} from "@angular/forms";

import {Router} from "@angular/router";

import {PaymentService} from "../shared/payment-service.service";

import {PaymentGuardService} from "../shared/payment-guard.service";
import {HttpErrorResponse} from "@angular/common/http";
import {ErrorObservable} from "rxjs/observable/ErrorObservable";
import {School} from "../shared/School";
import {Department} from "../shared/Department";
import {DataService} from "../shared/DataService";
import {User} from "../shared/user";
import {Payment} from "../shared/payment";

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css'],
  providers: [DataService]
})
export class PaymentComponent implements OnInit {

  selectedSchool: School = new School(0, '');

  schools: School[];
  departments: Department[];

  user = new User();

  loading = false;

  dropdownList : Payment[];
  selectedItems : Payment[];
  dropdownSettings = {};

  totalAmountToPay = 0;
  totalFees = 0;

  constructor(private toastrService: ToastrService, private router: Router, private paymentService: PaymentService, private guard: PaymentGuardService, private _dataService: DataService) {
    this.schools = this._dataService.getSchool();
  }

  ngOnInit() {
    this.user.level = "";
    this.user.ses = "";
    this.user.department = "";


    this.dropdownList = [
      {"id":1,"itemName":"ASSOCIATION DUES","amount":1400,"fee":80},
      {"id":2,"itemName":"CONFERENCE AND JOURNAL","amount":2000,"fee":100},
      {"id":3,"itemName":"ICT","amount":4000,"fee":150},
      {"id":4,"itemName":"ASOGOV","amount":1000,"fee":70}
    ];
    this.selectedItems = [];
    this.dropdownSettings = {
      singleSelection: false,
      text:"Select Payment",
      selectAllText:'Select All',
      unSelectAllText:'UnSelect All',
      enableSearchFilter: true,
      classes:"row col-md-12"
    };
  }

  onItemSelect(item:any){
    this.totalAmountToPay +=item.amount;
    this.totalFees +=item.fee;
  }
  OnItemDeSelect(item:any){
    this.totalAmountToPay -=item.amount;
    this.totalFees -=item.fee;
  }
  onSelectAll(items: any){
    this.totalAmountToPay = 0;
    this.totalFees = 0;
    for(let i : number = 0;i < items.length;i++) {
     this.totalAmountToPay += items[i].amount;
     this.totalFees += items[i].fee;
    }
  }
  onDeSelectAll(items: any){
    this.totalAmountToPay = 0;
    this.totalFees = 0;
  }


  onSelect(schoolid) {
    this.departments = this._dataService.getDepartment()
      .filter((item) => item.schoolid == schoolid);

    this.user.school = this.getSchoolByName(schoolid);
  }

  onSave(form: NgForm) {
    this.user.amount = this.totalAmountToPay;
    this.user.fee = this.totalFees;
    for(let i : number = 0;i < this.selectedItems.length;i++) {
      this.selectedItems[i].id = null;
    }
    this.loading = true;
    this.paymentService.SavePayment(this.user,this.selectedItems).subscribe((user) => {
      this.paymentService.setPayment(user);
      this.toastrService.success('Record Saved Successfully', 'Done!!!');
      this.resetForm(form);
      this.guard.allow = true;
      this.router.navigateByUrl('confirm');
    }, (error: HttpErrorResponse) => {
      this.loading = false;
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
    })
  }


  // get diagnostic() {
  //   return JSON.stringify(this.payment);
  // }

  resetForm(form?: NgForm) {
    if (form != null)
      form.reset();
    this.user = {
      id: null,
      surname: '',
      othername: '',
      matric: '',
      department: '',
      school: '',
      level: '',
      ses: '',
      email: '',
      phone: '',
      ref: '',
      status: '',
      date: '',
      fee: null,
      amount: null,
      total: null,
      transactionId: '',
      payments:null
    }
  }

  getSchoolByName(schoolid: number): string {
    if (schoolid == 1) {
      return 'School of Science and Computer studies';
    } else if (schoolid == 2) {
      return 'School of Business Studies';
    } else if (schoolid == 3) {
      return 'School of Engineering';
    } else if (schoolid == 4) {
      return 'School of Environmental';
    }
  }
}
