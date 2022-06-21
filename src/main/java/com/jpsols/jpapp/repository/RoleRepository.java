package com.jpsols.jpapp.repository;

import com.jpsols.jpapp.models.ERole;
import com.jpsols.jpapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
