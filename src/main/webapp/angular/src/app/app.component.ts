import { Component } from '@angular/core';
import { ErrorBufferService } from './error-buffer.service';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  errors: Observable<string[]>;

  constructor(private errorBufferService: ErrorBufferService){
    this.errors = errorBufferService.getErrors();
  }

  addError(message){
    this.errorBufferService.addError(message);
  }

}
