package api_cadastro_produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api_cadastro_produto.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
