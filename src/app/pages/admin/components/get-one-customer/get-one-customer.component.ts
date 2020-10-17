import { Router } from '@angular/router';
import { Coupon } from './../../../../models/Coupon';
import { Customer } from './../../../../models/Customer';
import { AdminService } from './../../../../services/admin.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-get-one-customer',
  templateUrl: './get-one-customer.component.html',
  styleUrls: ['./get-one-customer.component.css']
})
export class GetOneCustomerComponent implements OnInit {


  selected = 'get one customer';

  showTable = false;

  hideCustomerCoupons = true;

  showUpdate = false;

  hide = true;

  showImage = false;

  ok: boolean


  public customer: Customer = new Customer;

  public coupon: Coupon = new Coupon;

  public image: string;

  dataSource = null;

  couponDataSource = null;

  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'email', 'password', 'commands'];

  couponDisplayedColumns: string[] = ['id', 'title', 'category', 'description', 'amount', 'price', 'startDate', 'endDate', 'image']

  public customerId = new FormGroup({
    id: new FormControl(null, [
      Validators.required,
      Validators.pattern("^[0-9]*$"),

    ]),
  });

  public customerForm = new FormGroup({
    email: new FormControl(null, [
      Validators.required,
      Validators.email
    ]),
    password: new FormControl(null, [
      Validators.required,
      Validators.minLength(8)
    ]),
    firstName: new FormControl(null, [
      Validators.required,
    ]),
    lastName: new FormControl(null, [
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

  private newMethod() {
    alert('you shall not pass'),
      this.router.navigate(['/home']);
  }


  getCustomer = () => {

    const customerId: Number = this.customerId.get('id').value;

    var cust: Customer[] = [];

    this.adminService.getCustomer(customerId).subscribe(
      (res) => {
        console.log(res),
          this.customer.id = res.id,
          this.customer.firstName = res.firstName,
          this.customer.lastName = res.lastName,
          this.customer.email = res.email,
          this.customer.password = res.password,
          this.customer.coupons = res.coupons,
          cust.push(this.customer),
          this.dataSource = cust;
        this.couponDataSource = res.coupons;

      },

      (err) => {
        console.log(err)
        this.showTable = false
        alert('sorry, there is no customer with that id')

      }
    )
  }

  delete = () => {
    if (confirm('Are you sure you want to delete ' + this.customer.firstName + " " + this.customer.lastName + ' ?')) {
      this.deleteCustomer();
    }
  }

  deleteCustomer = () => {

    this.adminService.deleteCustomer(this.customer).subscribe(
      (res) => {
        console.log(res),
          alert('customer: ' + this.customer.firstName + " " + this.customer.lastName + ' was deleted from dataBase')
        location.reload()
      },
      (err) => {
        console.log(err)
      }
    )
  }

  update = () => {

    const customer: Customer = new Customer(
      this.customer.id,
      this.customerForm.get('firstName').value,
      this.customerForm.get('lastName').value,
      this.customerForm.get('email').value,
      this.customerForm.get('password').value,
      this.customer.coupons,
    );



    this.adminService.updateCustomer(customer).subscribe(
      (res) => {
        console.log(res),
          alert(this.customer.firstName + " " + this.customer.lastName + ' updated successfully.')
        location.reload()


      },
      (err) => {
        console.log(err)
        alert(err.error.message)
      }
    )
  }

  getImage = (image: string) => {
    this.image = image;
    this.hideCustomerCoupons = true;
    this.showImage = true;
  }

  closeImage = () => {
    this.hideCustomerCoupons = false;
    this.showImage = false;
  }

}
