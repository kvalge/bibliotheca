package com.lib.bibliotheca.domain.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("select r from Role r where upper(r.name) = upper(?1)")
    Role findByName(String name);

    @Transactional
    @Modifying
    @Query("delete from Role r where upper(r.name) = upper(?1)")
    int deleteByName(String name);
}