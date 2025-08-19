package com.microservice.estoque.Repositories;

import com.microservice.estoque.Entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Produto,Long> {
}
