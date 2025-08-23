package com.microservice.estoque.DTO;

import com.microservice.estoque.Entities.Product;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductDTO(Long id, String nome, String descricao, BigDecimal preco, int quantidadeEstoque, LocalDateTime criadoEm, LocalDateTime atualizadoEm) {

    public ProductDTO(Product product) {
        this(
                product.getId(),
                product.getNome(),
                product.getDescricao(),
                product.getPreco(),
                product.getQuantidadeEstoque(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
