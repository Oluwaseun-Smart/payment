import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {HomeComponent} from "./home/home.component";
import {PaymentComponent} from "./payment/payment.component";
import {ReceiptsComponent} from "./receipts/receipts.component";
import {ConfirmComponent} from "./confirm/confirm.component";
import {PaymentGuardService} from "./shared/payment-guard.service";
import {NotfoundpageComponent} from "./notfoundpage/notfoundpage.component";

const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: '', component: HomeComponent},
  {path: 'payment', component: PaymentComponent},
  {path: 'receipts', component: ReceiptsComponent},
  {path: 'confirm', component: ConfirmComponent, canActivate: [PaymentGuardService]},
  { path: '**', component: NotfoundpageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

export const routingComponents = [HomeComponent, PaymentComponent];
