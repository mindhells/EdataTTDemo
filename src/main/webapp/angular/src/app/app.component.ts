import { Component } from '@angular/core';
import { ErrorBufferService } from './error-buffer.service';
import { Observable } from 'rxjs/Observable';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  errors: Observable<string[]>;

  constructor(private errorBufferService: ErrorBufferService, public authService: AuthService){
    this.errors = errorBufferService.getErrors();
  }

  addError(message){
    this.errorBufferService.addError(message);
  }

}
