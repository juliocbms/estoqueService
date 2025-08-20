package com.microservice.estoque.Controller;

import com.microservice.estoque.DTO.EstoqueDTO;
import com.microservice.estoque.DTO.ProductDTO;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
public class productControler {

    @Autowired
    private ProdutoService produtoService;




    @PostMapping
    @Tag(name = "Salvar Produtos", description = "salva produtos")
    public ResponseEntity<Product> insert (@RequestBody ProductDTO productDTO){
        Product product = produtoService.fromDTO(productDTO);
        product = produtoService.saveProduto(product);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @GetMapping
    @Tag(name = "Lista Produtos", description = "retorna uma lista de produtos")
    public ResponseEntity<List<ProductDTO>> findAll(){
        List<Product> list = produtoService.findAll();
        List<ProductDTO> listDTO = list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
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
    public ResponseEntity<Product> atualizarProduto(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        Product product = produtoService.fromDTO(productDTO);
        product.setId(id);
        product = produtoService.atualizarProduto(product);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/add-stock")
    public ResponseEntity<Product> adicionarEstoque(@PathVariable Long id, @RequestBody EstoqueDTO estoqueDTO) {
       Product product = produtoService.findById(id);
       product.adicionarNoEstoque(estoqueDTO.getQuantidade());
       produtoService.saveProduto(product);
        return ResponseEntity.ok().body(product);
    }

    @PatchMapping("/{id}/remove-stock")
    public ResponseEntity<Product> removerEstoque(@PathVariable Long id, @RequestBody EstoqueDTO estoqueDTO) {
       Product product = produtoService.findById(id);
       product.removerNoEstoque(estoqueDTO.getQuantidade());
       produtoService.saveProduto(product);
        return  ResponseEntity.ok().body(product);
    }
}
