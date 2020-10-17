import { Router } from '@angular/router';
import { AdminService } from './../../../../services/admin.service';
import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/Company';
import { Coupon } from 'src/app/models/Coupon';
import { FormControl, FormGroup, Validators } from '@angular/forms';



@Component({
  selector: 'app-get-all-companies',
  templateUrl: './get-all-companies.component.html',
  styleUrls: ['./get-all-companies.component.css']
})


export class GetAllCompaniesComponent implements OnInit {

  ok: boolean;

  selected = 'get all companies';

  dataSource = null;

  couponDataSource = null;




  public company: Company = new Company;

  public companies: Company[];

  public image: string;

  displayedColumns: string[] = ['id', 'name', 'email', 'password', 'commands'];

  couponDisplayedColumns: string[] = ['id', 'title', 'category', 'description', 'amount', 'price', 'startDate', 'endDate', 'image', 'commands'];

  hideCompanyCoupons = true;

  showTable = true;

  showUpdate = false;

  hide = true;

  showImage = false;






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
    this.getAllCompanies()
  }

  private newMethod() {
    alert('you shall not pass'),
      this.router.navigate(['/home']);
  }



  getAllCompanies = () => {

    this.adminService.getAllCompanies().subscribe(
      (res) => {
        console.log(res),
          this.companies = res,
          this.dataSource = res;
      },
      (err) => {
        console.log(err),
          alert(err.error.message)
      }
    )
  }

  delete = (company: Company) => {
    if (confirm('Are you sure you want to delete ' + company.name + ' ?')) {
      this.adminService.deleteCompany(company).subscribe(
        (res) => {
          console.log(res),
            alert('company: ' + this.company.name + ' was deleted from dataBase')
          this.getAllCompanies()
        },
        (err) => {
          console.log(err)
        }
      )
    }
  }



  openCoupons = (company: Company) => {
    this.couponDataSource = company.coupons
  }

  deleteCoup = (coupon: Coupon) => {
    if (confirm('Are you sure you want to delete ' + coupon.title + ' ?')) {
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

  getName = (company: Company) => {
    this.company.name = company.name;
  }

  openUpdate = (company: Company) => {
    this.company = company;
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
        this.getAllCompanies()
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
