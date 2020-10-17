import { Company } from './../models/Company';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../models/Customer';


@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private httpClient: HttpClient) { }

  public registerCompany(company: Company): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8081/register/company', company);
  }

  public registerCustomer(customer: Customer): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8081/register/customer', customer);
  }


}
