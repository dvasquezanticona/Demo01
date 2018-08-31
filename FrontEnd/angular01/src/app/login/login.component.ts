import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router : Router) {
  }

  ngOnInit() {
  }

  username : string
  password : string
  isValid : boolean=true;
  message: string;

  login() : void {
    if(this.username == 'admin' && this.password == 'admin'){
     this.router.navigate(["home"]);
    }else {
      //alert("Invalid credentials");
      this.isValid=false;
      this.message="Credenciales incorrectas"
    }
  }

}
