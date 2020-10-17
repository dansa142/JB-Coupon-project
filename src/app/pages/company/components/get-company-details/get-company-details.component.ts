import { Company } from './../../../../models/Company';
import { Router } from '@angular/router';
import { CompanyService } from './../../../../services/company.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Coupon } from 'src/app/models/Coupon';

@Component({
  selector: 'app-get-company-details',
  templateUrl: './get-company-details.component.html',
  styleUrls: ['./get-company-details.component.css']
})
export class GetCompanyDetailsComponent implements OnInit {

  ok: boolean;

  showUpdate = false;

  hide = true;

  showCoupons = false;

  showImage = false;

  public image: string;

  selected = 'company details';

  couponDataSource = null;

  displayedColumns: string[] = ['id', 'name', 'email', 'password', 'commands'];

  couponDisplayedColumns: string[] = ['id', 'title', 'category', 'description', 'amount', 'price', 'startDate', 'endDate', 'image', 'commands'];

  company: Company = new Company;



  public companyForm = new FormGroup({
    email: new FormControl(null, [
      Validators.required,
      Validators.email
    ]),
    password: new FormControl(null, [
      Validators.required,
      Validators.minLength(8)
    ]),
    name: new FormControl(null, [
      Validators.required,
    ]),
  });


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
          this.couponDataSource = res.coupons,
          console.log(res)
      },
      (err) => {
        console.log(err)
      }
    )
  }

  update = () => {

    const company: Company = new Company(
      this.company.id,
      this.companyForm.get('name').value,
      this.companyForm.get('email').value,
      this.companyForm.get('password').value,
      this.company.coupons,
    );

    this.companyService.updateCompany(company).subscribe(
      (res) => {
        console.log(res),
          alert('updated successfully.')
        this.getCompanyDetails()
      },
      (err) => {
        console.log(err)
        alert(err.error.message)
      }
    )
  }

  updateCompanyForm = () => {
    this.showUpdate = true;
    this.showCoupons = false;
  }

  closeImage = () => {
    this.showImage = false;
    this.showCoupons = true;

  }

  getImage = (image: string) => {
    this.image = image;
    this.showImage = true;
    this.showCoupons = false;
  }

  deleteCoup = (coupon: Coupon) => {
    if (confirm('Are you sure you want to delete ' + coupon.title + ' ?')) {
      this.deleteCoupon(coupon);
    }
  }

  deleteCoupon = (coupon: Coupon) => {

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

  openCoupons = () => {
    this.showCoupons = true;
    this.showUpdate = false;
  }

  closeCoupons = () => {
    this.showCoupons = false;
  }

}

