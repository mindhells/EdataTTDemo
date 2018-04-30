import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UsersService } from '../users.service';
import { ErrorBufferService } from '../../error-buffer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-users-form',
  templateUrl: './users-form.component.html',
  styleUrls: ['./users-form.component.scss']
})
export class UsersFormComponent implements OnInit {

  constructor(private formBuilder: FormBuilder, private usersService: UsersService,
    private errorBufferService: ErrorBufferService, private router: Router) { }

  userForm: FormGroup;

  ngOnInit() {
    this.userForm = this.formBuilder.group({
      name: ['', Validators.required],
      login: ['', Validators.required],
      password: ['', Validators.required],
      roles: [['standard']]
    })
  }

  sending: boolean = false;
  create(){
    if (this.sending) return;
    this.sending = true;
    this.usersService.createUser(this.userForm.value).subscribe(
      user => {
        console.log("created", user);
        this.router.navigate(["/users"]);
      },
      result => {
        console.log("error", result.error);
        result.error && this.errorBufferService.addError(result.error.message);
        this.sending = false;
      }
    );
  }

}
