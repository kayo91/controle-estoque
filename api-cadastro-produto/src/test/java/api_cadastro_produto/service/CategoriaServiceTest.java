package api_cadastro_produto.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import api_cadastro_produto.dto.CategoriaDTO;
import api_cadastro_produto.entities.Categoria;
import api_cadastro_produto.repository.CategoriaRepository;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    void criaCategoria() {
        CategoriaDTO dto = new CategoriaDTO(null, "Informática");

        when(categoriaRepository.save(any(Categoria.class)))
            .thenAnswer(invocacao -> invocacao.getArgument(0));

        Categoria resultado = categoriaService.criar(dto);

        assertEquals("Informática", resultado.getNome());
        verify(categoriaRepository).save(any(Categoria.class));
    }
    
    @Test
    void listaCategorias() {
        List<Categoria> categorias = List.of(
            new Categoria(1L, "Informática"),
            new Categoria(2L, "Móveis")
        );

        when(categoriaRepository.findAll()).thenReturn(categorias);

        List<Categoria> resultado = categoriaService.listarTodas();

        assertEquals(2, resultado.size());
        assertSame(categorias, resultado);
        verify(categoriaRepository).findAll();
    }
    
    @Test
    void buscaCategoriaPorId() {
        Categoria categoria = new Categoria(1L, "Informática");

        when(categoriaRepository.findById(1L))
            .thenReturn(Optional.of(categoria));

        Optional<Categoria> resultado = categoriaService.buscarPorId(1L);

        assertTrue(resultado.isPresent());
        assertSame(categoria, resultado.get());
        verify(categoriaRepository).findById(1L);
    }
    
    @Test
    void atualizaCategoriaExistente() {
        Categoria categoria = new Categoria(1L, "Informática");
        CategoriaDTO dto = new CategoriaDTO(null, "Eletrônicos");

        when(categoriaRepository.findById(1L))
            .thenReturn(Optional.of(categoria));
        when(categoriaRepository.save(any(Categoria.class)))
            .thenAnswer(invocacao -> invocacao.getArgument(0));

        Optional<Categoria> resultado = categoriaService.atualizar(1L, dto);

        assertTrue(resultado.isPresent());
        assertEquals("Eletrônicos", resultado.get().getNome());
        verify(categoriaRepository).save(categoria);
    }
    
    @Test
    void deletaCategoriaExistente() {
        Categoria categoria = new Categoria(1L, "Informática");

        when(categoriaRepository.findById(1L))
            .thenReturn(Optional.of(categoria));

        boolean resultado = categoriaService.deletar(1L);

        assertTrue(resultado);
        verify(categoriaRepository).delete(categoria);
    }
}
