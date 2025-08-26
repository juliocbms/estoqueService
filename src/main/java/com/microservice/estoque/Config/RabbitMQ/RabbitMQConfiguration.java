package com.microservice.estoque.Config.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }


    @Value("${broker.exchange.vendas.name}")
    private String exchangeVendas;

    @Value("${broker.queue.estoque.processamento.name}")
    private String queueProcessamento;

    @Bean
    public TopicExchange vendasExchange() {
        return new TopicExchange(exchangeVendas);
    }

    @Bean
    public Queue processamentoQueue() {
        return new Queue(queueProcessamento, true);
    }

    @Bean
    public Binding bindingProcessamento() {
        return BindingBuilder.bind(processamentoQueue())
                .to(vendasExchange())
                .with("pedido.criado");
    }


    @Value("${broker.queue.estoque.confirmacao.name}")
    private String queueConfirmacao;

    @Bean
    public Queue estoqueConfirmacaoQueue() {
        return new Queue(queueConfirmacao, true);
    }

    @Bean
    public Binding bindingConfirmacaoEstoque() {
        return BindingBuilder.bind(estoqueConfirmacaoQueue())
                .to(vendasExchange())
                .with("pedido.aprovado");
    }
}