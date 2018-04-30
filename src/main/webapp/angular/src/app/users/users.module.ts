import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { UsersListComponent } from './users-list/users-list.component';
import { UsersFormComponent } from './users-form/users-form.component';
import { UsersService } from './users.service';
import { SharedModule } from '../shared.module';

@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    UsersRoutingModule
  ],
  declarations: [UsersListComponent, UsersFormComponent],
  exports: [UsersListComponent, UsersFormComponent],
  providers: [UsersService]
})
export class UsersModule { }
