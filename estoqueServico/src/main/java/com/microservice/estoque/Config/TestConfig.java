package com.microservice.estoque.Config;

import com.microservice.estoque.Entities.Product;
import com.microservice.estoque.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        productRepository.deleteAll();

        Product p1 = new Product(null, "Maça","Unidade maça", new BigDecimal(7.00),10, LocalDateTime.now(),null);
        Product p2 = new Product(null, "Laranja","Unidade laranja", new BigDecimal("5.50"), 25, LocalDateTime.now(),null);
        Product p3 = new Product(null, "Pão de Forma","Unidade Pão de forma", new BigDecimal("8.99"), 5,LocalDateTime.now(),null);
        Product p4 = new Product(null, "Leite Integral","Caixa de leite", new BigDecimal("4.25"), 30,LocalDateTime.now(),null);
        Product p5 = new Product(null, "Ovo (dúzia)","12 ovos", new BigDecimal("12.50"), 15,LocalDateTime.now(),null);

        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));

    }
}
