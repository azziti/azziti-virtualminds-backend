package com.virtualminds.test.backend.repositories;

import com.virtualminds.test.backend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByRoleName(String role);
}
