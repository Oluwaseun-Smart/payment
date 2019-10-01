import {Injectable} from '@angular/core';
import {CanActivate} from "@angular/router";

@Injectable()
export class PaymentGuardService implements CanActivate {

  allow = false;


  canActivate() {

    if (this.allow) {
      this.allow = false;
      return true;
    } else return false;

  }


}
