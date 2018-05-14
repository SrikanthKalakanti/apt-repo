import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

// import { HomeComponent } from './home/index';
import { LoginViewComponent } from './components/login-view/login-view.component';
import { RegisterComponent } from './components/register/register.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AddCustomerComponent } from './components/add-customer/add-customer.component';
import { CustomerDetailsComponent } from './components/customer-details/customer-details.component';
import { AuthGuardService } from './services/auth-guard.service';
// import { AuthGuard } from './_guards/index';

const appRoutes: Routes = [
  { path: 'login', component: LoginViewComponent },
  { path: 'register', component: RegisterComponent },
  //{ path: 'dashboard', component: DashboardComponent, canActivate: [ AuthGuardService ] },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'addclient', component: AddCustomerComponent },
  { path: 'customerDetails', component: CustomerDetailsComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  // otherwise redirect to home
  { path: '**', component: LoginViewComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: false } // <-- debugging purposes only
    )
  ],
  exports: [
    RouterModule
  ]
})

export class AppRoutingModule { }
