import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgProgressModule, NgProgressInterceptor } from 'ngx-progressbar';

// import {HashLocationStrategy,LocationStrategy} from "@angular/common";


// import {}

import { AppComponent } from './app.component';
import { AppRoutingModule,routingComponents } from './/app-routing.module';
import { NavbarComponent } from './navbar/navbar.component';
import { ReceiptsComponent } from './receipts/receipts.component';

import {ToastrModule} from "ngx-toastr";

import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { FooterComponent } from './footer/footer.component';

import {FormsModule} from "@angular/forms";

import { ConfirmComponent } from './confirm/confirm.component';

import {PaymentService} from "./shared/payment-service.service";

import {PaymentGuardService} from "./shared/payment-guard.service";
import { NotfoundpageComponent } from './notfoundpage/notfoundpage.component';

import { AngularMultiSelectModule } from 'angular2-multiselect-dropdown';

import { RavepaymentModule } from 'angular4-ravepayment';




@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    NavbarComponent,
    ReceiptsComponent,
    FooterComponent,
    ConfirmComponent,
    NotfoundpageComponent,
  ],
  imports: [
    BrowserModule,
    RavepaymentModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    AngularMultiSelectModule,
    FormsModule,
    NgProgressModule,
    HttpClientModule,
    NgProgressModule
  ],
  providers: [
    PaymentService,
    PaymentGuardService,
    // {provide:LocationStrategy,useClass:HashLocationStrategy},
    { provide: HTTP_INTERCEPTORS, useClass: NgProgressInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
