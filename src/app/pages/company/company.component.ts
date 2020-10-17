import { Router } from '@angular/router';
import { CompanyService } from './../../services/company.service';
import { Company } from './../../models/Company';
import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

  ok: boolean

  company: Company = new Company;

  selected = "none";

  constructor(
    private companyService: CompanyService,
    private router: Router
  ) { }

  ngOnInit(): void {



    this.ok = sessionStorage.getItem('service') === 'company' ? true : false;
    if (this.ok == false) {
      this.newMethod();
    }


    this.companyService.getCompanyDetails().subscribe(
      (res) => {
        this.company = res,
          console.log(this.company)
      },
      (err) => {
        console.log(err)
      }
    )
  }

  private newMethod() {
    alert('you shall not pass'),
      this.router.navigate(['/home']);
  }

}

