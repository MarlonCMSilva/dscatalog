package com.marlonmachado.dscatalog.repositories;

import com.marlonmachado.dscatalog.entities.Role;
import com.marlonmachado.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
