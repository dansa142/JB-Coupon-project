import { Customer } from './../../models/Customer';
import { RegisterService } from './../../services/register.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Company } from 'src/app/models/Company';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

//Class name
export class RegisterComponent implements OnInit {

  // Attributes:

  hide = true;


  public type = new FormGroup({
    type: new FormControl(null, [
      Validators.required
    ])
  })

  public company = new FormGroup({
    email: new FormControl(null, [
      Validators.required,
      Validators.email,
    ]),
    password: new FormControl(null, [
      Validators.required,
      Validators.minLength(8)
    ]),
    name: new FormControl(null, [
      Validators.required,
    ]),
  });

  public customer = new FormGroup({
    email: new FormControl(null, [
      Validators.required,
      Validators.email,
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


  //CTOR+Lifecycle

  constructor(private registerService: RegisterService) { }

  ngOnInit(): void { }

  //Customized func:



  companyReg = () => {
    const company: Company = new Company(
      0,
      this.company.get('name').value,
      this.company.get('email').value,
      this.company.get('password').value,
      null
    );
    console.log(company);

    this.registerService.registerCompany(company).subscribe(
      (res) => {
        console.log(res),
        alert("your company " + company.name + " was added successfully")

      },
      (err) => {
        console.log(err),
        alert(err.error.message)
        ;
      }
    );
  }

  customerReg = () => {
    const customer: Customer = new Customer(
      0,
      this.customer.get('firstName').value,
      this.customer.get('lastName').value,
      this.customer.get('email').value,
      this.customer.get('password').value,
      null
    );
    console.log(customer);

    this.registerService.registerCustomer(customer).subscribe(
      (res) => {
        console.log(res),
        alert("Hi " + customer.firstName + ", you have registered successfully")
        ;
      },
      (err) => {
        console.log(err),
        alert(err.error.message)

        ;
      }
    );

  }
}
