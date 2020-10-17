import { Coupon } from 'src/app/models/Coupon';
import { Customer } from 'src/app/models/Customer';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private httpClient: HttpClient) { }

  public login(email: string, password: string): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8081/customer/login/' + email + "/" + password, null, { observe: 'response', withCredentials: true });
  }

  public getCustomerDetails(): Observable<Customer> {
    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.get<Customer>('http://localhost:8081/customer/get-customer-details', options);
  }

  public getAllCoupons(): Observable<any> {
    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.get<Customer>('http://localhost:8081/customer/get-all-coupons', options);
  }

  public getCouponsByCatagory(category: string): Observable<any> {
    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.get<any>('http://localhost:8081/customer/get-all-coupons-by-catagory/' + category, options);
  }

  public getCustomerCoupons(): Observable<any> {
    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.get<any>('http://localhost:8081/customer/get-all-customer-coupons', options);
  }

  public purchaseCoupon(coupon: Coupon): Observable<any> {
    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.post<any>('http://localhost:8081/customer/purchase-coupon', coupon, options);
  }

  public getCustomerCouponsByCatagory(category: string): Observable<any> {
    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.get<any>('http://localhost:8081/customer/my-coupons-by-catagory/' + category, options);
  }

  public getCustomerCouponsByPrice(price: Number): Observable<any> {
    const headers = new HttpHeaders({ 'token': sessionStorage.getItem('token') });
    const options = { headers: headers };
    return this.httpClient.get<any>('http://localhost:8081/customer/get-coupons-by-price/' + price, options);
  }

}
