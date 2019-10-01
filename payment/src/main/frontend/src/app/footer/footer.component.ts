import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  year: string;

  constructor() {
  }

  ngOnInit() {
    this.getYearAsString();
  }

  getYearAsString(){
    let today = new Date();
    let yyyy = today.getFullYear();
    this.year = yyyy+"";
  }

}
