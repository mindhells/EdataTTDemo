import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;

  constructor(private authService: AuthService, public router: Router,
    private formBuilder: FormBuilder) { }

  ngOnInit(){
    this.loginForm = this.formBuilder.group({
      login: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  login(){
    this.authService.login(this.loginForm.value.login, this.loginForm.value.password);
    let redirect = this.authService.redirectUrl ? this.authService.redirectUrl : '/';
    this.router.navigate([redirect]);
  }

}
