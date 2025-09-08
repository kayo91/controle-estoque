import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CategoriaRoutingModule } from './categoria-routing.module';
import { Categoria } from './categoria';
import { CadastroCategoriaComponent } from './cadastro-categoria/cadastro-categoria.component';


@NgModule({
  declarations: [
    Categoria,
    CadastroCategoriaComponent
  ],
  imports: [
    CommonModule,
    CategoriaRoutingModule
  ]
})
export class CategoriaModule { }
