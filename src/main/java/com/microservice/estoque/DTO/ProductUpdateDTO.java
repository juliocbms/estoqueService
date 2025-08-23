package com.microservice.estoque.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductUpdateDTO(String nome,String descricao, BigDecimal preco) {

}
