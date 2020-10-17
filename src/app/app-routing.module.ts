import { CustomerComponent } from './pages/customer/customer.component';
import { CouponsComponent } from './pages/company/components/coupons/coupons.component';
import { GetCompanyDetailsComponent } from './pages/company/components/get-company-details/get-company-details.component';
import { AddCustomerComponent } from './pages/admin/components/add-customer/add-customer.component';
import { GetOneCompanyComponent } from './pages/admin/components/get-one-company/get-one-company.component';
import { CompanyComponent } from './pages/company/company.component';
import { AdminComponent } from './pages/admin/admin.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddCompanyComponent } from './pages/admin/components/add-company/add-company.component';
import { GetAllCompaniesComponent } from './pages/admin/components/get-all-companies/get-all-companies.component';
import { GetOneCustomerComponent } from './pages/admin/components/get-one-customer/get-one-customer.component';
import { GetAllCustomersComponent } from './pages/admin/components/get-all-customers/get-all-customers.component';
import { GetAllCouponsComponent } from './pages/admin/components/get-all-coupons/get-all-coupons.component';

const routes: Routes = [
  { path: "home", component: HomepageComponent },
  { path: "register", component: RegisterComponent },
  { path: "login", component: LoginComponent },
  { path: "admin", component: AdminComponent },
  { path: "addCompany", component: AddCompanyComponent },
  { path: "getOneCompany", component: GetOneCompanyComponent },
  { path: "getAllCompanies", component: GetAllCompaniesComponent },
  { path: "addCustomer", component: AddCustomerComponent },
  { path: "getOneCustomer", component: GetOneCustomerComponent },
  { path: "getAllCustomers", component: GetAllCustomersComponent },
  { path: "getAllCoupons", component: GetAllCouponsComponent },
  { path: "company", component: CompanyComponent },
  { path: "companyDetails", component: GetCompanyDetailsComponent },
  { path: "coupons", component: CouponsComponent },
  { path: "customer", component: CustomerComponent },
  { path: "", redirectTo: "home", pathMatch: "full" },



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
