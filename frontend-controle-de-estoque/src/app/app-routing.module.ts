import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: '', loadChildren: () => import('./auth/auth.component.module').then(m => m.AuthModule) },
  { path: 'home', loadChildren: () => import('./home/home.component.module').then(m => m.HomeModule) },
  { path: 'categoria', loadChildren: () => import('./categoria/categoria.module').then(m => m.CategoriaModule) },
  { path: 'produto', loadChildren: () => import('./produto/produto.module').then(m => m.ProdutoModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

