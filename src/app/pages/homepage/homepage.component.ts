import { HomeService } from './../../services/home.service';
import { Coupon } from './../../models/Coupon';
import { SecureService } from './../../services/secure.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  allCoupons: Coupon[];

  constructor(
    public secureService: SecureService,
    public homeService: HomeService,
  ) { }

  ngOnInit(): void {

   // this.secureService.isLogged = false;

    this.homeService.gatAllCoupons().subscribe(
      (res) => {
        this.allCoupons = res
      },
      (err) => {
        console.log(err)
      }
    )
  }

}
