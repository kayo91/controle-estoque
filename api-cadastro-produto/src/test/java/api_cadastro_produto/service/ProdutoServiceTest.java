package api_cadastro_produto.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import api_cadastro_produto.dto.ProdutoDTO;
import api_cadastro_produto.entities.Categoria;
import api_cadastro_produto.entities.Produto;
import api_cadastro_produto.repository.CategoriaRepository;
import api_cadastro_produto.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void deveCriarProdutoQuandoCategoriaExiste() {
        Categoria categoria = new Categoria(1L, "Informática");
        ProdutoDTO dto = new ProdutoDTO(
            null, "Teclado", new BigDecimal("100.00"), 1L
        );

        when(categoriaRepository.findById(1L))
            .thenReturn(Optional.of(categoria));
        when(produtoRepository.save(any(Produto.class)))
            .thenAnswer(invocacao -> invocacao.getArgument(0));

        Optional<Produto> resultado = produtoService.criar(dto);

        assertTrue(resultado.isPresent());
        assertEquals("Teclado", resultado.get().getNome());
        assertEquals(new BigDecimal("100.00"), resultado.get().getPreco());
        assertSame(categoria, resultado.get().getCategoria());

        verify(produtoRepository).save(any(Produto.class));
    }

    @Test
    void naoDeveSalvarProdutoQuandoCategoriaNaoExiste() {
        ProdutoDTO dto = new ProdutoDTO(
            null, "Teclado", new BigDecimal("100.00"), 99L
        );

        when(categoriaRepository.findById(99L))
            .thenReturn(Optional.empty());

        Optional<Produto> resultado = produtoService.criar(dto);

        assertTrue(resultado.isEmpty());
        verify(produtoRepository, never()).save(any(Produto.class));
    }
    
    @Test
    void deveAtualizarProdutoQuandoProdutoECategoriaExistem() {
        Categoria categoria = new Categoria(1L, "Informática");
        Produto produtoExistente = new Produto(
            10L, "Mouse", new BigDecimal("50.00"), categoria
        );
        ProdutoDTO dto = new ProdutoDTO(
            null, "Teclado", new BigDecimal("100.00"), 1L
        );

        when(categoriaRepository.findById(1L))
            .thenReturn(Optional.of(categoria));
        when(produtoRepository.findById(10L))
            .thenReturn(Optional.of(produtoExistente));
        when(produtoRepository.save(any(Produto.class)))
            .thenAnswer(invocacao -> invocacao.getArgument(0));

        Optional<Produto> resultado = produtoService.atualizar(10L, dto);

        assertTrue(resultado.isPresent());
        assertEquals("Teclado", resultado.get().getNome());
        assertEquals(new BigDecimal("100.00"), resultado.get().getPreco());
        assertSame(categoria, resultado.get().getCategoria());

        verify(produtoRepository).save(produtoExistente);
    }
    
    @Test
    void retornaVazioParaProdutoInexistente() {
        Categoria categoria = new Categoria(1L, "Informática");
        ProdutoDTO dto = new ProdutoDTO(
            null, "Teclado", new BigDecimal("100.00"), 1L
        );

        when(categoriaRepository.findById(1L))
            .thenReturn(Optional.of(categoria));
        when(produtoRepository.findById(99L))
            .thenReturn(Optional.empty());

        Optional<Produto> resultado = produtoService.atualizar(99L, dto);

        assertTrue(resultado.isEmpty());
        verify(produtoRepository, never()).save(any(Produto.class));
    }
    
    @Test
    void deletaProdutoExistente() {
        Categoria categoria = new Categoria(1L, "Informática");
        Produto produto = new Produto(
            10L, "Mouse", new BigDecimal("50.00"), categoria
        );

        when(produtoRepository.findById(10L))
            .thenReturn(Optional.of(produto));

        boolean resultado = produtoService.deletar(10L);

        assertTrue(resultado);
        verify(produtoRepository).delete(produto);
    }
    
    @Test
    void naoDeletaProdutoInexistente() {
        when(produtoRepository.findById(99L))
            .thenReturn(Optional.empty());

        boolean resultado = produtoService.deletar(99L);

        assertFalse(resultado);
        verify(produtoRepository, never()).delete(any(Produto.class));
    }
    
    @Test
    void listaProdutos() {
        Categoria categoria = new Categoria(1L, "Informática");
        Produto produto = new Produto(
            1L, "Mouse", new BigDecimal("50.00"), categoria
        );

        when(produtoRepository.findAll())
            .thenReturn(List.of(produto));

        List<Produto> resultado = produtoService.listarTodos();

        assertEquals(1, resultado.size());
        assertSame(produto, resultado.get(0));
        verify(produtoRepository).findAll();
    }
    
    @Test
    void buscaProdutoPorId() {
        Categoria categoria = new Categoria(1L, "Informática");
        Produto produto = new Produto(
            10L, "Mouse", new BigDecimal("50.00"), categoria
        );

        when(produtoRepository.findById(10L))
            .thenReturn(Optional.of(produto));

        Optional<Produto> resultado = produtoService.buscarPorId(10L);

        assertTrue(resultado.isPresent());
        assertSame(produto, resultado.get());
        verify(produtoRepository).findById(10L);
    }
    
    @Test
    void atualizaSemCategoriaValida() {
        ProdutoDTO dto = new ProdutoDTO(
            null, "Teclado", new BigDecimal("100.00"), 99L
        );

        when(categoriaRepository.findById(99L))
            .thenReturn(Optional.empty());

        Optional<Produto> resultado = produtoService.atualizar(10L, dto);

        assertTrue(resultado.isEmpty());
        verify(produtoRepository, never()).findById(10L);
    }
}
