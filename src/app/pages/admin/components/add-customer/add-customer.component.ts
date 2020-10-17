import { AdminService } from './../../../../services/admin.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Customer } from 'src/app/models/Customer';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {

  ok: boolean;

  selected = 'add customer';

  hide = true;



  public customerForm = new FormGroup({
    firstName: new FormControl(null, [
      Validators.required
    ]),
    lastName: new FormControl(null, [
      Validators.required
    ]),
    email: new FormControl(null, [
      Validators.required,
      Validators.email,
    ]),
    password: new FormControl(null, [
      Validators.required,
      Validators.minLength(8)
    ])
  })

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


  addCustomer = () => {
    const customer: Customer = new Customer(
      0,
      this.customerForm.get('firstName').value,
      this.customerForm.get('lastName').value,
      this.customerForm.get('email').value,
      this.customerForm.get('password').value,
      null
    );

    console.log(customer);

    this.adminService.addCustomer(customer).subscribe(
      (res) => {
        console.log(res),
          alert(`${customer.firstName} ${customer.lastName} was added successfully`)
      },
      (err) => {
        console.log(err),
          alert(err.error.message)
          ;
      }
    );
  }

}
