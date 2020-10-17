import { Component, OnInit } from '@angular/core';
import { FormControl, FormControlDirective, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Company } from 'src/app/models/Company';
import { Coupon } from 'src/app/models/Coupon';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.css']
})
export class CouponsComponent implements OnInit {

  ok: boolean;

  selected = 'coupons';

  showImage = false;

  showCoupons = true;

  showButtons = true;

  showUpdate = false;

  showAddCoupon = false;

  couponDataSource = null;

  allCompanyCoupons = null;

  displayedColumns: string[] = ['id', 'name', 'email', 'password', 'commands'];

  catagories: string[] = ['food', 'electricity', 'restaurant', 'vacation', 'sports', 'clothing'];

  couponDisplayedColumns: string[] = ['id', 'title', 'category', 'description', 'amount', 'price', 'startDate', 'endDate', 'image', 'commands'];

  company: Company = new Company;

  coupon: Coupon = new Coupon;

  public sortForm = new FormGroup({
    category: new FormControl(null),
    price: new FormControl(null)
  })

  public addCouponForm = new FormGroup({
    title: new FormControl(null, [
      Validators.required,
      Validators.maxLength(10)
    ]),
    catagory: new FormControl(null, [
      Validators.required,
    ]),
    description: new FormControl(null, [
      Validators.required,
      Validators.minLength(15),
      Validators.maxLength(250)
    ]),
    amount: new FormControl(null, [
      Validators.required,
    ]),
    price: new FormControl(null, [
      Validators.required,
    ]),
    startDate: new FormControl(null, [
      Validators.required,
    ]),
    endDate: new FormControl(null, [
      Validators.required,
    ]),
    image: new FormControl()

  })

  public updateForm = new FormGroup({
    title: new FormControl(null, [
      Validators.required,
      Validators.maxLength(15),
    ]),
    catagory: new FormControl(null, [
      Validators.required,
    ]),
    description: new FormControl(null, [
      Validators.required,
      Validators.minLength(15),
      Validators.maxLength(250)
    ]),
    amount: new FormControl(null, [
      Validators.required,
    ]),
    price: new FormControl(null, [
      Validators.required,
    ]),
    date: new FormControl(null, [
      Validators.required,
    ]),
    image: new FormControl(null, [
      Validators.required
    ])

  });

  public image: string;


  constructor(
    private companyService: CompanyService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.ok = sessionStorage.getItem('service') === 'company' ? true : false;
    if (this.ok == false) {
      this.newMethod();
    }
    this.getCompanyDetails();
  }

  private newMethod() {
    alert('you shall not pass'),
      this.router.navigate(['/home']);
  }

  getCompanyDetails = () => {
    this.companyService.getCompanyDetails().subscribe(
      (res) => {
        this.company = res,
          this.allCompanyCoupons = res.coupons,
          this.couponDataSource = res.coupons,
          console.log(res)
      },
      (err) => {
        console.log(err)
      }
    )
  }

  sortByCategory = () => {

    const category = this.sortForm.get('category').value;

    this.companyService.sortByCategory(category).subscribe(
      (res) => {
        this.couponDataSource = res
      },
      (err) => {
        console.log(err)
      }
    )
  }

  sortByMaxPrice = () => {
    const price = this.sortForm.get('price').value;
    this.companyService.sortByMaxPrice(price).subscribe(
      (res) => {
        this.couponDataSource = res
      },
      (err) => {
        console.log(err)
      }
    )
  }

  goUpdate = () => {

    const c1: Coupon = new Coupon(
      this.coupon.id,
      this.company.id,
      this.updateForm.get('title').value,
      this.coupon.startDate,
      this.updateForm.get('date').value,
      this.updateForm.get('amount').value,
      this.updateForm.get('catagory').value,
      this.updateForm.get('description').value,
      this.updateForm.get('price').value,
      this.updateForm.get('image').value
    );




    if (confirm('Are you sure you want to update coupon: ' + " ` " + this.coupon.title + "`" + ' ?')) {
      this.companyService.updateCoupon(c1).subscribe(
        (res) => {
          console.log('coupon updated successfully'),
            this.getCompanyDetails(),
            this.backFromUpdate();
        },
        (err) => {
          console.log(err)
        }
      )
    }
  }

  addCoupon = () => {

    const coupon: Coupon = new Coupon(
      null,
      null,
      this.addCouponForm.get('title').value,
      this.addCouponForm.get('startDate').value,
      this.addCouponForm.get('endDate').value,
      this.addCouponForm.get('amount').value,
      this.addCouponForm.get('catagory').value,
      this.addCouponForm.get('description').value,
      this.addCouponForm.get('price').value,
      this.addCouponForm.get('image').value
    );

    if (confirm('are you sure you want to add `' + this.addCouponForm.get('title').value + '` to your coupons?')) {
      this.companyService.addCoupon(coupon).subscribe(
        (res) => {
          console.log('coupon added successfully'),
            this.getCompanyDetails(),
            this.backFromAddCoupon();
        },
        (err) => {
          console.log(err),
            alert(err.error.message)
        }
      )
    }
  }


  deleteCoup = (coupon: Coupon) => {
    if (confirm('Are you sure you want to delete ' + coupon.title + ' ?')) {
      this.companyService.deleteCoupon(coupon).subscribe(
        (res) => {
          console.log(res),
            alert('coupon: ' + coupon.title + ' was deleted from dataBase')
          this.getCompanyDetails()
        },
        (err) => {
          console.log(err)
        }
      )
    }
  }



  closeImage = () => {
    this.showImage = false;
    this.showCoupons = true;
    this.showButtons = true;

  }

  getImage = (image: string) => {
    this.image = image;
    this.showImage = true;
    this.showCoupons = false;
    this.showButtons = false;
  }

  getUpdate = (coup: Coupon) => {
    this.showCoupons = false;
    this.showButtons = false;
    this.showUpdate = true;
    this.coupon = coup;

  }

  backFromUpdate = () => {
    this.showCoupons = true;
    this.showButtons = true;
    this.showUpdate = false;

  }

  backToAll = () => {
    this.couponDataSource = this.allCompanyCoupons;
  }

  goToAddCoupon = () => {
    this.showCoupons = false;
    this.showButtons = false;
    this.showImage = false;
    this.showUpdate = false;
    this.showAddCoupon = true;
  }

  backFromAddCoupon = () => {
    this.showCoupons = true;
    this.showButtons = true;
    this.showAddCoupon = false;
  }
}
