import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private HttpClient: HttpClient) { }

  public gatAllCoupons(): Observable<any> {
    return this.HttpClient.get('http://localhost:8081/home/get-all-coupons');
  }
}
