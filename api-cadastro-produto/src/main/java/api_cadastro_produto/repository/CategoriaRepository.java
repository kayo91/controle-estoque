package api_cadastro_produto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api_cadastro_produto.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
