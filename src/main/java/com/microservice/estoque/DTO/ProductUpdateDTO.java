package com.microservice.estoque.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductUpdateDTO {

    private String name;
    private String descricao;
    private BigDecimal price;


    public ProductUpdateDTO() {
    }

    public ProductUpdateDTO(String name, String descricao, BigDecimal price) {
        this.name = name;
        this.descricao = descricao;
        this.price = price;

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
}
