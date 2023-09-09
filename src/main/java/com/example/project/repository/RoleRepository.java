package com.example.project.repository;

import com.example.project.model.auth.Role;
import com.example.project.model.auth.RoleTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleTypes name);

}
