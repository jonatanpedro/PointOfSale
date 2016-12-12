package com.outrider.domain.repository;

import com.outrider.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jonat on 11/12/2016.
 */
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByAuthority(String authority);
}