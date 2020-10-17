import { FormGroup, FormControl } from '@angular/forms';
import { Coupon } from './../../models/Coupon';
import { Customer } from './../../models/Customer';
import { CustomerService } from './../../services/customer.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  ok: boolean

  showAll = true;

  showMyCoupons = false;

  showOneCategory = false;

  showSort = false;

  filterByCatagory = false;

  filterByPrice = false;

  customer: Customer = new Customer;

  catagoriesList: string[] = ['food', 'electricity', 'restaurant', 'vacation', 'sports', 'clothing'];

  categoriesMap = new Map();

  customerCoupons: Coupon[];

  oneCategory: Coupon[];

  customerCategoryCoupons: Coupon[];

  public sortForm = new FormGroup({
    category: new FormControl(null),
    price: new FormControl(null)
  })

  constructor(
    private customerService: CustomerService,

    private router: Router
  ) { }



  ngOnInit(): void {



    this.ok = sessionStorage.getItem('service') === 'customer' ? true : false;
    if (this.ok == false) {
      this.newMethod();
    }

    this.customerService.getCustomerDetails().subscribe(
      (res) => {
        this.customer = res,
          console.log(this.customer)
      },
      (err) => {
        console.log(err)
      })


    for (let index = 0; index < this.catagoriesList.length; index++) {
      var str = this.catagoriesList[index];
      let key = {
        name: str
      }
      this.customerService.getCouponsByCatagory(str).subscribe(
        (res) => {
          this.categoriesMap.set(key, res);
        },
        (err) =>
          console.log(err)
      )
    }
    console.log(this.categoriesMap)




  }





  private newMethod() {
    alert('you shall not pass'),
      this.router.navigate(['/home']);
  }



  myCoupons = () => {
    this.customerService.getCustomerCoupons().subscribe(
      (res) => {
        this.customerCoupons = res;
        this.showMyCoupons = true;
        this.showAll = false;
        this.showOneCategory = false;
        this.showSort = true;
        this.filterByCatagory = false;
        this.filterByPrice = false;
        console.log(res)
      },
      (err) => {
        console.log(err);
      }
    )
  }


  purchaseCoupon = (coupon: Coupon) => {
    if (confirm('Are you sure you want to purchase ' + coupon.title + ' ?')) {
      this.customerService.purchaseCoupon(coupon).subscribe(
        (res) => {
          alert(coupon.title + 'was purchased and added to your coupons. enjoy!')
        },
        (err) => {
          alert(err.error.message)
        }
      )
    }
  }




  allCatagoryCoupons = (coupons: Coupon[]) => {
    this.showMyCoupons = false;
    this.showAll = false;
    this.showOneCategory = true;
    this.filterByPrice = false;
    this.filterByCatagory=false;
    this.oneCategory = coupons;
  }

  backToAll = () => {
    this.showMyCoupons = false;
    this.showAll = true;
    this.showOneCategory = false;
    this.showSort = false;
    this.filterByPrice = false;
    this.filterByCatagory=false;
  }

  sortByCategory = () => {
    const cat = this.sortForm.get('category').value;

    this.customerService.getCustomerCouponsByCatagory(cat).subscribe(
      (res) => {
        this.customerCategoryCoupons = res;
        this.filterByCatagory = true;
        this.showAll = false;
        this.showMyCoupons = false;
        this.showOneCategory = false
        this.showSort = true;
        this.filterByPrice = false;
      },
      (err) => {
        console.log(err)
      }
    )
  }

  sortByPrice = () => {
    const price = this.sortForm.get('price').value;

    this.customerService.getCustomerCouponsByPrice(price).subscribe(
      (res) => {
        this.customerCategoryCoupons = res;
        this.filterByPrice = true;
        this.filterByCatagory = false;
        this.showAll = false;
        this.showMyCoupons = false;
        this.showOneCategory = false
        this.showSort = true;
      },
      (err) => {
        console.log(err)
      }
    )
  }


}




