import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsersListComponent } from './users-list/users-list.component';
import { UsersFormComponent } from './users-form/users-form.component';
import { AuthGuard } from '../auth/auth.guard';

const routes: Routes = [
  {path: 'users', children: [
    {path: '', component: UsersListComponent},
    {path: 'new', component: UsersFormComponent, canActivate: [AuthGuard]}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
