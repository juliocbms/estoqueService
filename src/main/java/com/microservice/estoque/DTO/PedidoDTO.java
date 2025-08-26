package com.microservice.estoque.DTO;

import java.util.List;
import java.util.UUID;

public record PedidoDTO(Long id, UUID clientId, List<ItemPedidoDTO> itensList) {
}
