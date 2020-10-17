import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { LayoutComponent } from './layout/layout/layout.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './components/header/header.component';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { RegisterComponent } from './pages/register/register.component';
import { FooterComponent } from './components/footer/footer.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './pages/login/login.component';
import { AdminComponent } from './pages/admin/admin.component';
import { MatTableModule } from '@angular/material/table';
import { CompanyComponent } from './pages/company/company.component';
import { AddCompanyComponent } from './pages/admin/components/add-company/add-company.component';
import { GetOneCompanyComponent } from './pages/admin/components/get-one-company/get-one-company.component';
import { GetAllCompaniesComponent } from './pages/admin/components/get-all-companies/get-all-companies.component';
import { AddCustomerComponent } from './pages/admin/components/add-customer/add-customer.component';
import { GetAllCustomersComponent } from './pages/admin/components/get-all-customers/get-all-customers.component';
import { GetOneCustomerComponent } from './pages/admin/components/get-one-customer/get-one-customer.component';
import { GetAllCouponsComponent } from './pages/admin/components/get-all-coupons/get-all-coupons.component';
import { GetCompanyDetailsComponent } from './pages/company/components/get-company-details/get-company-details.component';
import { MatListModule } from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { CouponsComponent } from './pages/company/components/coupons/coupons.component';
import { CustomerComponent } from './pages/customer/customer.component';







@NgModule({
  declarations: [
    LayoutComponent,
    HeaderComponent,
    HomepageComponent,
    RegisterComponent,
    FooterComponent,
    LoginComponent,
    AdminComponent,
    CompanyComponent,
    AddCompanyComponent,
    GetOneCompanyComponent,
    GetAllCompaniesComponent,
    AddCustomerComponent,
    GetAllCustomersComponent,
    GetOneCustomerComponent,
    GetAllCouponsComponent,
    GetCompanyDetailsComponent,
    CouponsComponent,
    CustomerComponent,



  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    MatIconModule,
    MatFormFieldModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    HttpClientModule,
    MatTableModule,
    MatListModule,
    MatCardModule


  ],
  providers: [],
  bootstrap: [LayoutComponent]
})
export class AppModule { }
