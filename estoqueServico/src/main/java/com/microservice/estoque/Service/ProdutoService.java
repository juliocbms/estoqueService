package com.microservice.estoque.Service;

import com.microservice.estoque.Entities.Produto;
import com.microservice.estoque.Repositories.ProductRepository;

import com.microservice.estoque.Service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProductRepository productRepository;

    public List<Produto> findAll(){
        return productRepository.findAll();
    }
    
    public Produto findById(Long id){
        Optional<Produto> produto = productRepository.findById(id);
        return produto.orElseThrow(()-> new ObjectNotFoundException(id));
    }

    public Produto saveProduto(Produto produto){
        return productRepository.save(produto);
    }

    public Produto atualizarProduto( Produto produto){
        Produto newProduto = findById(produto.getId());
        updateData(newProduto,produto);
        return productRepository.save(newProduto);
    }

    private void updateData(Produto newProduto, Produto produto){
        newProduto.setNome(produto.getNome());
        newProduto.setPreco(produto.getPreco());
        newProduto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
    }
}
