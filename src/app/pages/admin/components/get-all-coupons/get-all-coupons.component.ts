import { Router } from '@angular/router';
import { Coupon } from './../../../../models/Coupon';
import { AdminService } from './../../../../services/admin.service';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-get-all-coupons',
  templateUrl: './get-all-coupons.component.html',
  styleUrls: ['./get-all-coupons.component.css']
})
export class GetAllCouponsComponent implements OnInit {

  ok: boolean;

  selected = "get all coupons"

  showTable = true;S

  showImage = false;

  public coupons: Coupon[]

  couponDataSource = null;


  public image: string;

  couponDisplayedColumns: string[] = ['id', 'title', 'category', 'description', 'amount', 'price', 'startDate', 'endDate', 'image', 'commands'];

  constructor(
    private adminService: AdminService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.ok = sessionStorage.getItem('service') === 'admin' ? true : false;
    if (this.ok == false) {
      this.newMethod();
    }
    this.getAllCoupons()
  }

  private newMethod() {
    alert('you shall not pass'),
      this.router.navigate(['/home']);
  }

  getAllCoupons = () => {

    this.adminService.getAllCoupons().subscribe(
      (res) => {
        console.log(res)
        this.couponDataSource = res;
        this.coupons = res;

      },
      (err) => {
        console.log(err)
        alert(err.error.message);

      }
    )
  }

  deleteCoup = (coupon: Coupon) => {
    if (confirm('Are you sure you want to delete `' + coupon.title + '` ?')) {
      this.deleteCoupon(coupon);
    }
  }

  deleteCoupon = (coupon: Coupon) => {

    this.adminService.deleteCoupon(coupon).subscribe(
      (res) => {
        console.log(res),
          alert('coupon: ' + coupon.title + ' was deleted from dataBase')
        location.reload()
      },
      (err) => {
        console.log(err)
      }
    )
  }

  getImage = (image: string) => {
    this.image = image;
    this.showTable = false;
    this.showImage = true;
  }

  closeImage = () => {
    this.showTable = true;
    this.showImage = false;
  }


}
