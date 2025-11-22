package com.marlonmachado.dscatalog.repositories;

import com.marlonmachado.dscatalog.entities.Category;
import com.marlonmachado.dscatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
