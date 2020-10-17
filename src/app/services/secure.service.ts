import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SecureService {

  public isLogged: boolean = true;

  constructor(private httpClient: HttpClient) { }

  public logOut(token: string): Observable<any> {
    return this.httpClient.delete('http://localhost:8081/home/logout/' + token);
  }
}
