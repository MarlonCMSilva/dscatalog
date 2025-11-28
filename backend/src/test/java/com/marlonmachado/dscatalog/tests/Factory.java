package com.marlonmachado.dscatalog.tests;

import com.marlonmachado.dscatalog.dto.CategoryDTO;
import com.marlonmachado.dscatalog.dto.ProductDTO;
import com.marlonmachado.dscatalog.entities.Category;
import com.marlonmachado.dscatalog.entities.Product;

import java.time.Instant;

public class Factory {

    public static Product creatProduct(){
        Product product = new Product(1L,"Phone", "Good Phone", 800.0, "https://img.com/img.png", Instant.parse("2020-10-20T03:00:00Z"));
        product.getCategories().add(createCategory());
        return product;
    }

    public static ProductDTO createProductDTO(){
        Product product = creatProduct();
        return new ProductDTO(product, product.getCategories());
    }


    public static Category createCategory(){
        return new Category(2L, "Electronics");
    }

}
