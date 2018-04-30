import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { User } from './user';
import { Observable } from 'rxjs/Observable';
import { AuthService } from '../auth/auth.service';


@Injectable()
export class UsersService {

  constructor(@Inject("API_PATH") private apiPath, private http: HttpClient, private authService: AuthService) {}

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

  createUser(user: User): Observable<User>{
    return this.http.post<User>(`${this.apiPath}/users`, user, {headers: {'Content-Type': 'application/json', "Authorization": `Basic ${this.authService.authToken}` }});
  }

}
