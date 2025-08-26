package com.microservice.estoque.Consumer;

import com.microservice.estoque.DTO.EstoqueDebitadoEvent;
import com.microservice.estoque.DTO.ItemPedidoDTO;
import com.microservice.estoque.DTO.PedidoDTO;
import com.microservice.estoque.Service.ProdutoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProcessamentoConsumer {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${broker.exchange.vendas.name}")
    private String exchange;

    @RabbitListener(queues = "${broker.queue.processamento.name}")
    public void listenerProcessamentoQueue(@Payload PedidoDTO pedidoDTO) {
        System.out.println("Recebido evento para o pedido ID: " + pedidoDTO.id());

        try {
            for (ItemPedidoDTO item : pedidoDTO.itensList()) {
                produtoService.removerEstoque(item.produtoId(), item.quantidade());
            }
            EstoqueDebitadoEvent event = new EstoqueDebitadoEvent(pedidoDTO.id(), pedidoDTO.clientId());
            rabbitTemplate.convertAndSend(exchange, "pedido.aprovado", event);
            System.out.println("Evento 'EstoqueDebitadoEvent' publicado para o pedido: " + pedidoDTO.id());

        } catch (Exception e) {
            System.err.println("Erro ao processar o pedido " + pedidoDTO.id() + ": " + e.getMessage());
        }
    }
}
