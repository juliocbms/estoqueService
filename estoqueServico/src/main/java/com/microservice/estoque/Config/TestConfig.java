package com.microservice.estoque.Config;

import com.microservice.estoque.Entities.Produto;
import com.microservice.estoque.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        productRepository.deleteAll();

        Produto p1 = new Produto(null, "Maça", new BigDecimal(7.00),10);
        Produto p2 = new Produto(null, "Laranja", new BigDecimal("5.50"), 25);
        Produto p3 = new Produto(null, "Pão de Forma", new BigDecimal("8.99"), 5);
        Produto p4 = new Produto(null, "Leite Integral", new BigDecimal("4.25"), 30);
        Produto p5 = new Produto(null, "Ovo (dúzia)", new BigDecimal("12.50"), 15);

        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));

    }
}
