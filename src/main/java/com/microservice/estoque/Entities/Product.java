package com.microservice.estoque.Entities;

import jakarta.persistence.*;

import java.lang.Long;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private int quantidadeEstoque;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product() {
    }

    public Product(Long id, String nome, String descricao, BigDecimal preco, int quantidadeEstoque, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void adicionarNoEstoque( int quantidadeAdicionada){
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
