import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { UsersModule } from './users/users.module';
import { SharedModule } from './shared.module';
import { ErrorBufferService } from './error-buffer.service';

const routes: Routes = [
  { path: '', redirectTo: 'users', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    SharedModule,
    RouterModule.forRoot(routes, {useHash: true}),
    UsersModule
  ],
  providers: [
    {provide: "API_PATH", useValue: "webapi"},
    ErrorBufferService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
