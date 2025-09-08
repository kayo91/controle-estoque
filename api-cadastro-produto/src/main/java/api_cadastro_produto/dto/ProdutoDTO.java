package api_cadastro_produto.dto;

import java.math.BigDecimal;

public class ProdutoDTO {
	
	private Long id;
	private String nome;
	private BigDecimal preco;
	
	private Long CategoriaId;
	
	public ProdutoDTO() {	
	}

	public ProdutoDTO(Long id, String nome, BigDecimal preco, Long categoriaId) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		CategoriaId = categoriaId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Long getCategoriaId() {
		return CategoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		CategoriaId = categoriaId;
	}
	
	

}
