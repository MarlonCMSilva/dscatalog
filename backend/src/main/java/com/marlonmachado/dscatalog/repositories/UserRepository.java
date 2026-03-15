package com.marlonmachado.dscatalog.repositories;

import com.marlonmachado.dscatalog.entities.Category;
import com.marlonmachado.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
