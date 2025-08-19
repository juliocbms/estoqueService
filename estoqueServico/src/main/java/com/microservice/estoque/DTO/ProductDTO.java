package com.microservice.estoque.DTO;

import java.math.BigDecimal;

public class ProductDTO {

    private String name;
    private String descricao;
    private BigDecimal price;
    private int quantidadeEstoque;

    public ProductDTO() {
    }

    public ProductDTO(String name, String descricao, BigDecimal price, int quantidadeEstoque) {
        this.name = name;
        this.descricao = descricao;
        this.price = price;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
}
