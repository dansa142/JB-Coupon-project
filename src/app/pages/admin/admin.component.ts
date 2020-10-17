import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';




@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})




export class AdminComponent implements OnInit {
  [x: string]: any;

  selected = "none";

  ok: boolean;

  constructor(
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
}
