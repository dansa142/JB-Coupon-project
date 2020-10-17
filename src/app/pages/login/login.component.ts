import { SecureService } from './../../services/secure.service';
import { error } from '@angular/compiler/src/util';
import { CustomerService } from './../../services/customer.service';
import { CompanyService } from './../../services/company.service';
import { AdminService } from './../../services/admin.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  hide = true;

  selected = 'none';

  public loginForm = new FormGroup({
    email: new FormControl(null, [
      Validators.required,
      Validators.email,
    ]),

    password: new FormControl(null, [
      Validators.required,
      Validators.minLength(1)
    ]),

    type: new FormControl(null, [
      Validators.required

    ])

  });

  constructor(
    private adminService: AdminService,
    private companyService: CompanyService,
    private customerService: CustomerService,
    private secureService: SecureService,
    private router: Router,
  ) { }

  ngOnInit(): void {
  }

  login = () => {
    const type = this.loginForm.get('type').value;
    const email = this.loginForm.get('email').value;
    const password = this.loginForm.get('password').value;


    switch (type) {
      case 'admin':
        this.adminService.login(email, password).subscribe(
          (res) => {
            sessionStorage.setItem("isLogged", "true");
            sessionStorage.setItem("service", "admin");
            sessionStorage.setItem("token", res.headers.get('token'));
            this.secureService.isLogged = true;
            console.log(res);
            this.router.navigate(['/admin']);

          },
          (err) => {
            console.log(err)
            alert(err.error)
          }
        );
        break;

      case 'company':
        this.companyService.login(email, password).subscribe(
          (res) => {
            sessionStorage.setItem("isLogged", "true");
            sessionStorage.setItem("service", "company");
            sessionStorage.setItem("token", res.headers.get('token'));
            this.secureService.isLogged = true;
            console.log(res);
            this.router.navigate(['//companyDetails']);

          },
          (err) => {
            console.log(err)
            alert(err.error)
          }
        );
        break;

      case 'customer':
        this.customerService.login(email, password).subscribe(
          (res) => {
            sessionStorage.setItem("isLogged", "true");
            sessionStorage.setItem("service", "customer");
            sessionStorage.setItem("token", res.headers.get('token'));
            this.secureService.isLogged = true;
            this.router.navigate(['//customer']);
          },
          (err) => {
            console.log(err)
            alert(err.error)
          }
        );
        break;

      default:
        break;
    }


  }

}
