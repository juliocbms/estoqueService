package com.microservice.estoque.Service;

import com.microservice.estoque.DTO.ProductDTO;
import com.microservice.estoque.DTO.ProductUpdateDTO;
import com.microservice.estoque.Entities.Product;
import com.microservice.estoque.Repositories.ProductRepository;

import com.microservice.estoque.Service.exception.DatabaseException;
import com.microservice.estoque.Service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }
    
    public Product findById(Long id){
        Optional<Product> produto = productRepository.findById(id);
        return produto.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public Product saveProduto(Product product){
        return productRepository.save(product);
    }

    public Product atualizarProduto(Long id, Product product){
       try {
           Product newProduct = productRepository.getReferenceById(id);
           updateData(newProduct, product);
           return productRepository.save(newProduct);
       }  catch (DataIntegrityViolationException e){
           throw  new DatabaseException(e.getMessage());
       }
    }

    @Transactional
    public void deletarProduto(Long id){
       try {
           productRepository.deleteById(id);
       } catch (EmptyResultDataAccessException e){
           throw new ResourceNotFoundException(id);
       } catch (DataIntegrityViolationException e){
            throw  new DatabaseException(e.getMessage());
       }

    }


    @Transactional
    public Product adicionarEstoque(Long id, int quantidade) {
        try{
            Product produto = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));
            produto.adicionarNoEstoque(quantidade);
            produto.setUpdatedAt(LocalDateTime.now());
            return productRepository.save(produto);
        }  catch (DataIntegrityViolationException e){
            throw  new DatabaseException(e.getMessage());
        }
    }

    @Transactional
    public Product removerEstoque(Long id, int quantidade) {
        try{
            Product produto = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));
            produto.removerNoEstoque(quantidade);
            produto.setUpdatedAt(LocalDateTime.now());
            return productRepository.save(produto);
        }  catch (DataIntegrityViolationException e){
            throw  new DatabaseException(e.getMessage());
        }
    }

    public boolean isAvailable(Long id ){
        try {
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
            return product.getQuantidadeEstoque() > 0;
        }  catch (DataIntegrityViolationException e){
            throw  new DatabaseException(e.getMessage());
        }

    }

    private void updateData(Product newProduct, Product product){
        newProduct.setNome(product.getNome());
        newProduct.setPreco(product.getPreco());
        newProduct.setDescricao(product.getDescricao());
        newProduct.setQuantidadeEstoque(product.getQuantidadeEstoque());
        newProduct.setUpdatedAt(LocalDateTime.now());
    }

    public Product fromDTO(ProductDTO productDTO){
        return  new Product(productDTO.getId(), productDTO.getName(), productDTO.getDescricao(), productDTO.getPrice(), productDTO.getQuantidadeEstoque(),productDTO.getCreatedAt(),null);
    }

    public Product fromUpdateDTO(ProductUpdateDTO dto) {
        Product product = new Product();
        product.setNome(dto.getName());
        product.setDescricao(dto.getDescricao());
        product.setPreco(dto.getPrice());
        return product;
    }
}
