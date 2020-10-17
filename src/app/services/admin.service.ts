import { Coupon } from './../models/Coupon';
import { Customer } from './../models/Customer';
import { Company } from './../models/Company';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private httpClient: HttpClient) { }

  public login(email: string, password: string): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8081/Admin/login/' + email + "/" + password, null, { observe: 'response', withCredentials: true });
  }

  public addCompany(company: Company): Observable<any> {

    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.post<any>('http://localhost:8081/Admin/add-company', company, options);
  }

  public getCompany(companyId: Number): Observable<Company> {

    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.get<any>('http://localhost:8081/Admin/get-one-company/' + companyId, options);
  }

  public getAllCompanies(): Observable<any> {

    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.get<any>('http://localhost:8081/Admin/get-all-companies', options);
  }

  public deleteCompany(company: Company): Observable<any> {

    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers, body: company };
    return this.httpClient.request('delete', 'http://localhost:8081/Admin/delete-company', options);
  }

  public updateCompany(company: Company): Observable<Company> {

    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.put('http://localhost:8081/Admin/update-company', company, options);
  }

  public deleteCoupon(coupon: Coupon): Observable<any> {

    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers, body: coupon };
    return this.httpClient.request('delete', 'http://localhost:8081/Admin/delete-coupon', options);
  }

  public addCustomer(customer: Customer): Observable<any> {

    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.post<Customer>('http://localhost:8081/Admin/add-customer', customer, options);
  }

  public getCustomer(customerId: Number): Observable<Customer> {

    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.get<Customer>('http://localhost:8081/Admin/get-one-customer/' + customerId, options);
  }

  public deleteCustomer(customer: Customer): Observable<any> {

    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers, body: customer };
    return this.httpClient.request<any>('delete', 'http://localhost:8081/Admin/delete-customer', options);
  }

  public updateCustomer(customer: Customer): Observable<Customer> {

    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.put('http://localhost:8081/Admin/update-customer', customer, options);
  }

  public getAllCustomers(): Observable<any> {

    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.get<any>('http://localhost:8081/Admin/get-all-customers', options);
  }

  public getAllCoupons(): Observable<any> {

    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.get<any>('http://localhost:8081/Admin/get-all-coupons', options);
  }
}
