package com.microservice.estoque.Controller;

import com.microservice.estoque.Entities.Product;
import com.microservice.estoque.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping(value = "/products")
public class productControler {

    @Autowired
    private ProdutoService produtoService;


    @PostMapping
    public ResponseEntity<Product> insert (@RequestBody Product product){
        product = produtoService.saveProduto(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }
}
