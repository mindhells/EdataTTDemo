import { Component, OnInit } from '@angular/core';
import { UsersService } from '../users.service';
import { Observable } from 'rxjs/Observable';
import { User } from '../user';
import { tap } from 'rxjs/operators';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss']
})
export class UsersListComponent implements OnInit {

  constructor(private usersService: UsersService) { }

  users: Observable<User[]>;

  ngOnInit() {
    this.loadPage(true);
    console.log("init users");
  }

  //simple pagination
  pageSize = 5;
  page = 0;
  hasNext = false;
  hasPrevious = false;
  orderBy = "id";
  orderDirection = "asc";
  nextPage(){
    if (!this.hasNext) return;
    this.page++;
    this.loadPage();
  }
  previousPage(){
    if (!this.hasPrevious) return;
    this.page--;
    this.loadPage();
  }
  changeOrder(orderBy: string){
    if (this.orderBy == orderBy)
      this.orderDirection = this.orderDirection == "asc" ? "desc" : "asc";
    else
      [this.orderBy, this.orderDirection] = [orderBy, "asc"];
    this.loadPage(true);
  }

  loadPage(gotoFirstPage = false){
    if (gotoFirstPage){
      this.page = 0;
      this.hasNext = false;
      this.hasPrevious = false;
    }
    this.users = this.usersService.getUsers(this.page * this.pageSize, this.pageSize, this.orderBy, this.orderDirection).pipe(
      tap( users => {
        this.hasNext = users.length >= this.pageSize;
        this.hasPrevious = this.page > 0;
      })
    );
  }

  trackUser(i: number, u: User){
    return u.id;
  }

}
