import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AdminService, } from '../../../../services/admin.service';
import { Company } from '../../../../models/Company';

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.css']
})
export class AddCompanyComponent implements OnInit {

  ok: boolean;

  selected = 'add company';

  hide = true;

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

  private newMethod() {
    alert('you shall not pass'),
      this.router.navigate(['/home']);
  }

  addComp = () => {
    const company: Company = new Company(
      0,
      this.companyForm.get('name').value,
      this.companyForm.get('email').value,
      this.companyForm.get('password').value,
      null
    );
    console.log(company);

    this.adminService.addCompany(company).subscribe(
      (res) => {
        console.log(res),
        alert(company.name + " added successfully")
        location.reload()
      },
      (err) => {
        console.log(err),
        alert(err.error.message)
        ;
      }
    );
  }

}
