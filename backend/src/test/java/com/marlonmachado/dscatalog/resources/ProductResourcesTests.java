package com.marlonmachado.dscatalog.resources;

import com.marlonmachado.dscatalog.dto.ProductDTO;
import com.marlonmachado.dscatalog.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;


@WebMvcTest(ProductResources.class)
public class ProductResourcesTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    private PageImpl<ProductDTO> page;


    @BeforeEach
    void setUp() throws Exception {
        page = new PageImpl<>(List.of())

        when(service.findAllPaged()).thenReturn(page)
    }

}
