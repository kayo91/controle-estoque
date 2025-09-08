package api_cadastro_produto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import api_cadastro_produto.dto.CategoriaDTO;
import api_cadastro_produto.entities.Categoria;
import api_cadastro_produto.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria criar(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        return categoriaRepository.save(categoria);
    }

    public Optional<Categoria> atualizar(Long id, CategoriaDTO dto) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNome(dto.getNome());
            return categoriaRepository.save(categoria);
        });
    }

    public boolean deletar(Long id) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoriaRepository.delete(categoria);
            return true;
        }).orElse(false);
    }

}
