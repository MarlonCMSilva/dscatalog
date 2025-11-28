package com.marlonmachado.dscatalog.services;

import com.marlonmachado.dscatalog.dto.ProductDTO;
import com.marlonmachado.dscatalog.entities.Category;
import com.marlonmachado.dscatalog.entities.Product;
import com.marlonmachado.dscatalog.repositories.CategoryRepository;
import com.marlonmachado.dscatalog.repositories.ProductRepository;
import com.marlonmachado.dscatalog.services.exceptions.DatabaseException;
import com.marlonmachado.dscatalog.services.exceptions.ResourcesNotFoundException;
import com.marlonmachado.dscatalog.tests.Factory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    @Mock
    private CategoryRepository categoryRepository;

    private long existingId;
    private long nonExistingId;
    private long dependentId;
    private PageImpl<Product> page;
    private Product product;
    private Category category;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 100000L;
        dependentId = 2L;
        product = Factory.creatProduct();
        category = Factory.createCategory();
        productDTO = Factory.createProductDTO();
        page = new PageImpl<>(List.of(product));

        when(repository.findAll((Pageable)ArgumentMatchers.any())).thenReturn(page);

        when(repository.save(ArgumentMatchers.any())).thenReturn(product);

        when(repository.findById(existingId)).thenReturn(Optional.of(product));
        when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        when(repository.getReferenceById(existingId)).thenReturn(product);
        when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);


        when(categoryRepository.getReferenceById(existingId)).thenReturn(category);
        when(categoryRepository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);

        doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);
        when(repository.existsById(existingId)).thenReturn(true);
        when(repository.existsById(nonExistingId)).thenReturn(false);
        when(repository.existsById(dependentId)).thenReturn(true);


    }

    @Test
    public void updateIdShouldReturnProductDTOWhenIdExists(){
        ProductDTO result = service.update(existingId, productDTO);
        Assertions.assertNotNull(result );
    }

    @Test
    public void updateShouldReturnResourcesNotFoundExceptionWhenDoesNotIdExists(){
        Assertions.assertThrows(ResourcesNotFoundException.class, () -> {
            service.update(nonExistingId, productDTO);
        });
    }

    @Test
    public void findByIdShouldReturnProductDTOWhenIdExists(){
        productDTO = service.findById(existingId);
        Assertions.assertNotNull(productDTO);
    }

    @Test
    public void findByIdShouldReturnResourcesNotFoundExceptionWhenDoesNotIdExists(){
        Assertions.assertThrows(ResourcesNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
    }

    @Test
    public void findAllPagedShouldReturnPage(){
        Pageable pageable = PageRequest.of(0, 10);

        Page<ProductDTO> result = service.findAllPaged(pageable);

        Assertions.assertNotNull(result);
        verify(repository, times(1)).findAll(pageable);

    }

    @Test public void deleteShouldThrowDatabaseExceptionWhenDependentId(){
        Assertions.assertThrows(DatabaseException.class, () ->{
            service.delete(dependentId);
        });
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {

        Assertions.assertDoesNotThrow(() -> {
            service.delete(existingId);
        });
        Mockito.verify(repository, times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

        Assertions.assertThrows(ResourcesNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });
    }

}
