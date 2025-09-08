package api_cadastro_produto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import api_cadastro_produto.dto.ProdutoDTO;
import api_cadastro_produto.entities.Categoria;
import api_cadastro_produto.entities.Produto;
import api_cadastro_produto.repository.CategoriaRepository;
import api_cadastro_produto.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	 private final ProdutoRepository produtoRepository;
	    private final CategoriaRepository categoriaRepository;

	    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
	        this.produtoRepository = produtoRepository;
	        this.categoriaRepository = categoriaRepository;
	    }

	    public List<Produto> listarTodos() {
	        return produtoRepository.findAll();
	    }

	    public Optional<Produto> buscarPorId(Long id) {
	        return produtoRepository.findById(id);
	    }

	    public Optional<Produto> criar(ProdutoDTO dto) {
	        Optional<Categoria> categoriaOpt = categoriaRepository.findById(dto.getCategoriaId());
	        if (categoriaOpt.isEmpty()) {
	            return Optional.empty();
	        }
	        Produto produto = new Produto();
	        produto.setNome(dto.getNome());
	        produto.setPreco(dto.getPreco());
	        produto.setCategoria(categoriaOpt.get());
	        return Optional.of(produtoRepository.save(produto));
	    }

	    public Optional<Produto> atualizar(Long id, ProdutoDTO dto) {
	        Optional<Categoria> categoriaOpt = categoriaRepository.findById(dto.getCategoriaId());
	        if (categoriaOpt.isEmpty()) {
	            return Optional.empty();
	        }

	        return produtoRepository.findById(id).map(produto -> {
	            produto.setNome(dto.getNome());
	            produto.setPreco(dto.getPreco());
	            produto.setCategoria(categoriaOpt.get());
	            return produtoRepository.save(produto);
	        });
	    }

	    public boolean deletar(Long id) {
	        return produtoRepository.findById(id).map(produto -> {
	            produtoRepository.delete(produto);
	            return true;
	        }).orElse(false);
	    }
}
