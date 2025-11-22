package com.marlonmachado.dscatalog.services;

import com.marlonmachado.dscatalog.dto.CategoryDTO;
import com.marlonmachado.dscatalog.entities.Category;
import com.marlonmachado.dscatalog.repositories.CategoryRepository;
import com.marlonmachado.dscatalog.services.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> list =  repository.findAll();

        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
      Optional<Category> obj =   repository.findById(id);
      Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));
      return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }
}
