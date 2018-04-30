import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { User } from './user';
import { Observable } from 'rxjs/Observable';


@Injectable()
export class UsersService {

  constructor(@Inject("API_PATH") private apiPath, private http: HttpClient) {}

  getUsers(offset = 0, limit = 10, orderBy?: string, orderDirection?: string): Observable<User[]>{

    let p = {"offset": offset, "limit": limit, "orderBy": orderBy, "orderDirection": orderDirection};
    const params = new HttpParams({ fromObject:
      {
        offset : offset.toString(),
        limit: limit.toString(),
        orderBy: orderBy,
        orderDirection: orderDirection
      }
    });
    return this.http.get<User[]>(`${this.apiPath}/users`, {
      params: params
    });
  }

}
