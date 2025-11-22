package com.marlonmachado.dscatalog.services;

import com.marlonmachado.dscatalog.dto.CategoryDTO;
import com.marlonmachado.dscatalog.entities.Category;
import com.marlonmachado.dscatalog.repositories.CategoryRepository;
import com.marlonmachado.dscatalog.services.exceptions.DatabaseException;
import com.marlonmachado.dscatalog.services.exceptions.ResourcesNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(Pageable pageable){
        Page<Category> list =  repository.findAll(pageable);
        return list.map(x -> new CategoryDTO(x));

    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
      Optional<Category> obj =   repository.findById(id);
      Category entity = obj.orElseThrow(() -> new ResourcesNotFoundException("Entity Not Found"));
      return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        try {
            Category entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);
        }catch (EntityNotFoundException e ){
            throw new ResourcesNotFoundException("Id not found" + id);
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourcesNotFoundException("id not Found");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }
}
