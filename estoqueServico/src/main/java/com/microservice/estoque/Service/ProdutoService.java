package com.microservice.estoque.Service;

import com.microservice.estoque.Entities.Product;
import com.microservice.estoque.Repositories.ProductRepository;

import com.microservice.estoque.Service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return produto.orElseThrow(()-> new ObjectNotFoundException(id));
    }

    public Product saveProduto(Product product){
        return productRepository.save(product);
    }

    public Product atualizarProduto(Product product){
        Product newProduct = findById(product.getId());
        updateData(newProduct, product);
        return productRepository.save(newProduct);
    }

    private void updateData(Product newProduct, Product product){
        newProduct.setNome(product.getNome());
        newProduct.setPreco(product.getPreco());
        newProduct.setQuantidadeEstoque(product.getQuantidadeEstoque());
        newProduct.setUpdatedAt(LocalDateTime.now());
    }
}
