import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProdutoRoutingModule } from './produto-routing.module';
import { Produto } from './produto';
import { CadastroProdutoComponent } from './cadastro-produto/cadastro-produto.component';
import { EditarProdutoComponent } from './editar-produto/editar-produto.component';
import { ExcluirProdutoComponent } from './excluir-produto/excluir-produto.component';
import { ListaProdutoComponent } from './lista-produto/lista-produto.component';


@NgModule({
  declarations: [
    Produto,
    CadastroProdutoComponent,
    EditarProdutoComponent,
    ExcluirProdutoComponent,
    ListaProdutoComponent
  ],
  imports: [
    CommonModule,
    ProdutoRoutingModule
  ]
})
export class ProdutoModule { }
