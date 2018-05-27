import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
// import { InMemoryDataService }  from './in-memory-data.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';


import { AppComponent } from './app.component';
import { HeaderComponent } from './shared/header/header.component';
import { FooterComponent } from './shared/footer/footer.component';
import { LoginComponent } from './components/login-view/login/login.component';
import { AppRoutingModule } from './/app-routing.module';
import { RegisterComponent } from './components/register/register.component';

import { AlertService } from './services/alert.service';
import { UserService } from './services/user.service';
import { AlertComponent } from './directives/alert/alert.component';
import { LoginViewComponent } from './components/login-view/login-view.component';
import { AuthService } from './services/auth.service';
import { ErrorService } from './shared/services/error.service';
import { ApiService } from './services/api.service';
import { ListErrorsComponent } from './shared/components/list-errors/list-errors.component';
import { RegisterService } from './services/register.service';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DataTablesModule } from 'angular-datatables';
import { AddCustomerComponent } from './components/add-customer/add-customer.component';
import { TabComponent } from './components/tab/tab.component';
import { TabsetComponent } from './components/tabset/tabset.component';
import { CustomerDetailsComponent } from './components/customer-details/customer-details.component';
import { AuthGuardService } from './services/auth-guard.service';
import { DashboardService } from './services/dashboard.service';
import { AddClientService } from './services/add-client.service';
import { SharedService } from './shared/services/shared.service';
import { BasicInfoComponent } from './components/customer-details/basic-info/basic-info.component';
import { CustomerDetailsService } from './services/customer-details.service';
import { AgGridModule } from 'ag-grid-angular';
import 'ag-grid-enterprise';
import { AssetComponent } from './components/customer-details/asset/asset.component';
import { ExpensesComponent } from './components/customer-details/expenses/expenses.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    RegisterComponent,
    AlertComponent,
    LoginViewComponent,
    ListErrorsComponent,
    DashboardComponent,
    AddCustomerComponent,
    TabComponent,
    TabsetComponent,
    CustomerDetailsComponent,
    BasicInfoComponent,
    AssetComponent,
    ExpensesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    DataTablesModule,
    AgGridModule.withComponents([])
  ],
  providers: [
    AuthService,
    AlertService,
    UserService,
    ErrorService,
    ApiService,
    RegisterService,
    AuthGuardService,
    DashboardService,
    AddClientService,
    SharedService,
    CustomerDetailsService
  ],
  entryComponents: [BasicInfoComponent, AssetComponent, ExpensesComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
