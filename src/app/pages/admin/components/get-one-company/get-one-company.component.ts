import { Router } from '@angular/router';
import { Company } from './../../../../models/Company';
import { Coupon } from './../../../../models/Coupon';
import { Component, OnInit } from '@angular/core';
import { AdminService, } from '../../../../services/admin.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSort } from '@angular/material/sort';


@Component({
  selector: 'app-get-one-company',
  templateUrl: './get-one-company.component.html',
  styleUrls: ['./get-one-company.component.css']
})
export class GetOneCompanyComponent implements OnInit {


  // hide buttons

  selected = 'get one company';

  showTable = false;

  hideCompanyCoupons = true;

  showUpdate = false;

  hide = true;

  showImage = false;

  // parameters

  ok: boolean;

  dataSource = null;

  couponDataSource = null;

  displayedColumns: string[] = ['id', 'name', 'email', 'password', 'commands'];

  couponDisplayedColumns: string[] = ['id', 'title', 'category', 'description', 'amount', 'price', 'startDate', 'endDate', 'image', 'commands'];

  public company: Company = new Company;

  public coupon: Coupon = new Coupon;

  public image: string;

  // Forms

  public companyId = new FormGroup({
    id: new FormControl(null, [
      Validators.required,
      Validators.pattern("^[0-9]*$"),

    ]),
  });

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
    private adminService: AdminService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.ok = sessionStorage.getItem('service') === 'admin' ? true : false;
    if (this.ok == false) {
      this.newMethod();
    }
  }

  // functions

  private newMethod() {
    alert('you shall not pass'),
      this.router.navigate(['/home']);
  }

  getCompany = () => {

    const companyId: Number = this.companyId.get('id').value;

    var comp: Company[] = [];

    this.adminService.getCompany(companyId).subscribe(
      (res) => {
        console.log(res),
          this.company.id = res.id,
          this.company.name = res.name,
          this.company.email = res.email,
          this.company.password = res.password,
          this.company.coupons = res.coupons,
          comp.push(this.company),
          this.dataSource = comp;
        this.couponDataSource = res.coupons;

      },

      (err) => {
        console.log(err)
        this.showTable = false
        alert('sorry, there is no company with that id')

      }
    )
  }

  delete = () => {
    if (confirm('Are you sure you want to delete ' + this.company.name + ' ?')) {
      this.deleteCompany();
    }
  }

  deleteCompany = () => {

    this.adminService.deleteCompany(this.company).subscribe(
      (res) => {
        console.log(res),
          alert('company: ' + this.company.name + ' was deleted from dataBase')
        location.reload()
      },
      (err) => {
        console.log(err)
      }
    )
  }

  deleteCoup = (coupon: Coupon) => {
    if (confirm('Are you sure you want to delete ' + coupon.title + ' ?')) {
      this.adminService.deleteCoupon(coupon).subscribe(
        (res) => {
          console.log(res),
            alert('coupon: ' + coupon.title + ' was deleted from dataBase')
          this.getCompany()
        },
        (err) => {
          console.log(err)
        }
      )
    }
  }



  update = () => {

    const company: Company = new Company(
      this.company.id,
      this.companyForm.get('name').value,
      this.companyForm.get('email').value,
      this.companyForm.get('password').value,
      this.company.coupons,
    );



    this.adminService.updateCompany(company).subscribe(
      (res) => {
        console.log(res),
          alert(this.company.name + ' updated successfully.')
        this.getCompany()
      },
      (err) => {
        console.log(err)
        alert(err.error.message)
      }
    )
  }

  getImage = (image: string) => {
    this.image = image;
    this.showImage = true;
    this.hideCompanyCoupons = true;
  }

  closeImage = () => {
    this.hideCompanyCoupons = false;
    this.showImage = false;
  }

}
