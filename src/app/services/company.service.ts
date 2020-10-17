import { Coupon } from './../models/Coupon';
import { Company } from './../models/Company';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private httpClient: HttpClient) { }

  public login(email: string, password: string): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8081/Company/login/' + email + "/" + password, null, { observe: 'response', withCredentials: true });
  }

  public getCompanyDetails(): Observable<Company> {
    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.get<Company>('http://localhost:8081/Company/get-company-details', options);
  }

  public deleteCoupon(coupon: Coupon): Observable<any> {
    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers, body: coupon };
    return this.httpClient.request('delete', 'http://localhost:8081/Company/delete-coupon', options);
  }

  public sortByCategory(category: string): Observable<any> {
    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.get<any>('http://localhost:8081/Company/get-all-coupons-by-catagory/' + category, options);
  }

  public sortByMaxPrice(price: number): Observable<any> {
    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.get<any>('http://localhost:8081/Company/get-all-coupons-by-price/' + price, options);
  }

  public updateCoupon(coupon: Coupon): Observable<any> {
    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.put('http://localhost:8081/Company/update-coupon', coupon, options);
  }

  public addCoupon(coupon: Coupon): Observable<any> {
    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.post<any>('http://localhost:8081/Company/add-coupon', coupon, options);
  }

  public updateCompany(company: Company): Observable<Company> {

    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.put('http://localhost:8081/Company/update-company', company, options);
  }
}
