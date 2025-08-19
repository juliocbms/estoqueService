package com.microservice.estoque.Controller;

import com.microservice.estoque.Entities.Product;
import com.microservice.estoque.Service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class productControler {

    @Autowired
    private ProdutoService produtoService;




    @PostMapping
    @Tag(name = "Salvar Produtos", description = "salva produtos")
    public ResponseEntity<Product> insert (@RequestBody Product product){
        product = produtoService.saveProduto(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @GetMapping
    @Tag(name = "Lista Produtos", description = "retorna uma lista de produtos")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> list = produtoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    @Tag(name = "Porduto por ID", description = "retorna um produto por Id")
    public ResponseEntity<Product> findById(@PathVariable Long id){
        Product product = produtoService.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping(value = "/{id}")
    @Tag(name = "Deletar Produtos", description = "deleta produtos")
    public ResponseEntity<Void> deletarById(@PathVariable Long id){
       produtoService.deletarProduto(id);
       return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @Tag(name = "Atualizar Produtos", description = "atualiza produtos")
    public ResponseEntity<Product> atualizarProduto(@PathVariable Long id, @RequestBody Product product){
        product = produtoService.atualizarProduto(id,product);
        return ResponseEntity.ok().body(product);
    }

    @PatchMapping("/{id}/add-stock")
    public ResponseEntity<Product> adicionarEstoque(@PathVariable Long id, @RequestParam int quantidade) {
       Product product = produtoService.adicionarEstoque(id, quantidade);
        return  ResponseEntity.ok().body(product);
    }

    @PatchMapping("/{id}/remove-stock")
    public ResponseEntity<Product> removerEstoque(@PathVariable Long id, @RequestParam int quantidade) {
       Product product = produtoService.removerEstoque(id, quantidade);
        return  ResponseEntity.ok().body(product);
    }
}
