package com.microservice.estoque.Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_product")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal preco;
    private int quantidadeEstoque;

    public Produto() {
    }

    public Produto(Long id, String nome, BigDecimal preco, int quantidadeEstoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
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

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    public void adicionarNoEstoque(Long id,int total,int quantidadeAdicionada){
        if (quantidadeAdicionada <= 0){
            throw new IllegalArgumentException("A quantidade a ser adicionada deve ser maior que 0.");
        }
        this.quantidadeEstoque += quantidadeAdicionada;
    }

    public void removerNoEstoque(int quantidadeRemovida){
        if (quantidadeRemovida <= 0){
            throw new IllegalArgumentException("A quantidade a ser removida deve ser maior que 0.");
        }
        this.quantidadeEstoque -= quantidadeRemovida;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
