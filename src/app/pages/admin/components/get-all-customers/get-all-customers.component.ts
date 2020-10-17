import { Router } from '@angular/router';
import { AdminService } from './../../../../services/admin.service';
import { Customer } from './../../../../models/Customer';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';


@Component({
  selector: 'app-get-all-customers',
  templateUrl: './get-all-customers.component.html',
  styleUrls: ['./get-all-customers.component.css']
})
export class GetAllCustomersComponent implements OnInit {

  ok: boolean;

  selected = 'get all customers';

  dataSource = null;

  couponDataSource = null;

  showTable = true;

  hideCustomerCoupons = true;

  showUpdate = false;

  hide = true;

  showImage = false;

  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'email', 'password', 'commands'];

  couponDisplayedColumns: string[] = ['id', 'title', 'category', 'description', 'amount', 'price', 'startDate', 'endDate', 'image']

  public customer: Customer = new Customer;

  public customers: Customer[];

  public image: string;


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
    this.getAllCustomers()
  }

  private newMethod() {
    alert('you shall not pass'),
      this.router.navigate(['/home']);
  }

  getAllCustomers = () => {

    this.adminService.getAllCustomers().subscribe(
      (res) => {
        console.log(res),
          this.customers = res,
          this.dataSource = res
      },
      (err) => {
        console.log(err),
          alert(err.error.message)
      }
    )
  }

  getName = (customer: Customer) => {
    this.customer.firstName = customer.firstName;
    this.customer.lastName = customer.lastName;
  }

  openCoupons = (customer: Customer) => {
    this.couponDataSource = customer.coupons
  }

  openUpdate = (customer: Customer) => {
    this.customer = customer;
  }

  delete = (customer: Customer) => {
    if (confirm('Are you sure you want to delete ' + customer.firstName + " " + customer.lastName + ' ?')) {
      this.deleteCustomer(customer);
    }
  }

  deleteCustomer = (customer: Customer) => {

    this.adminService.deleteCustomer(customer).subscribe(
      (res) => {
        console.log(res),
          alert('customer: ' + customer.firstName + " " + customer.lastName + ' was deleted from dataBase')
        this.getAllCustomers()
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
