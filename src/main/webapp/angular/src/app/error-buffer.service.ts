import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { bufferTime } from 'rxjs/operators';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class ErrorBufferService {

  errors$: Subject<string> = new Subject<string>();

  constructor() {
  }

  addError(message){
    this.errors$.next(message);
  }

  getErrors(): Observable<string[]>{
    return this.errors$.pipe(
      bufferTime(3000, 0)
    )
  }

}
