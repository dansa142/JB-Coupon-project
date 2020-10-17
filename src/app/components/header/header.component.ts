import { SecureService } from './../../services/secure.service';
import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  client: string = sessionStorage.getItem("service");


  constructor(
    public secureService: SecureService,
    private router: Router,
  ) { }

  ngOnInit(): void { }

  logout = () => {
    this.secureService.logOut(sessionStorage.getItem('token')).subscribe(
      (res) => {
        console.log(res),
          sessionStorage.setItem("isLogged", "false");
        sessionStorage.setItem("service", null);
        sessionStorage.setItem("token", null);
        this.secureService.isLogged = false;
      },
      (err) => {
        console.log(err)
      }
    )
  }

  goToMyPage = () => {
    switch (sessionStorage.getItem('service')) {
      case 'admin':
        this.router.navigate(['//admin'])

        break;

      case 'company':
        this.router.navigate(['//company'])
        break;

      case 'customer':
        this.router.navigate(['//customer'])
        break;


      default:
        break;
    }
  }
}
