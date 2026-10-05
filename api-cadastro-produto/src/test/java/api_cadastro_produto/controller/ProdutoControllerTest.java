package api_cadastro_produto.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import api_cadastro_produto.entities.Categoria;
import api_cadastro_produto.entities.Produto;
import api_cadastro_produto.service.ProdutoService;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProdutoService produtoService;

    @Test
    void listaProdutos() throws Exception {
        Categoria categoria = new Categoria(1L, "Informática");
        Produto produto = new Produto(
            1L, "Mouse", new BigDecimal("50.00"), categoria
        );

        when(produtoService.listarTodos()).thenReturn(List.of(produto));

        mockMvc.perform(get("/produtos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].nome").value("Mouse"));
    }
}