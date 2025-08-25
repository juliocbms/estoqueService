package com.microservice.estoque.Controller;

import com.microservice.estoque.DTO.EstoqueDTO;
import com.microservice.estoque.DTO.ProductDTO;
import com.microservice.estoque.DTO.ProductUpdateDTO;
import com.microservice.estoque.Entities.Product;
import com.microservice.estoque.Service.ProdutoService;
import com.microservice.estoque.Service.exception.DatabaseException;
import com.microservice.estoque.Service.exception.EstoqueInsuficienteException;
import com.microservice.estoque.Service.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    @Tag(name = "Deletar Produtos", description = "deleta produtos")
    public ResponseEntity<Void> deletarById(@PathVariable Long id){
       produtoService.deletarProduto(id);
       return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}")
    @Tag(name = "Atualizar Produtos", description = "atualiza produtos")
    public ResponseEntity<Product> atualizarProduto(@PathVariable Long id, @RequestBody ProductUpdateDTO productDTO){
        Product product = produtoService.fromUpdateDTO(productDTO);
        product.setId(id);
        product = produtoService.atualizarProduto(id,product);
        return ResponseEntity.ok().body(product);
    }

    @PatchMapping("/{id}/add-stock")
    @Tag(name = "Adiciona no estoque", description = "adiciona no estoque por id")
    public ResponseEntity<Product> adicionarEstoque(@PathVariable Long id, @RequestBody EstoqueDTO estoqueDTO) {
        Product product = produtoService.adicionarEstoque(id, estoqueDTO.quantidade());
        return ResponseEntity.ok().body(product);
    }

    @PatchMapping("/{id}/remove-stock")
    @Tag(name = "Remove no estoque", description = "remove no estoque por id")
    public ResponseEntity<Product> removerEstoque(@PathVariable Long id, @RequestBody EstoqueDTO estoqueDTO) {
        Product product = produtoService.removerEstoque(id, estoqueDTO.quantidade());
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/{id}/available")
    @Tag(name = "Verifica disponibilidade", description = "verifica no estoque por id")
    public ResponseEntity<Boolean> isAvailable(@PathVariable Long id, @RequestParam int quantidade) {
        try {
            boolean available = produtoService.isAvailable(id, quantidade);
            return ResponseEntity.ok(available);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        } catch (EstoqueInsuficienteException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        } catch (DatabaseException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }
}
