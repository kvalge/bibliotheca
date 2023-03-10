package com.lib.bibliotheca.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username = ?1 and u.password = ?2")
    User findByUsernameAndPassword(String username, String password);

    @Query("select u from User u where upper(u.username) = upper(?1)")
    User findByUserName(String username);

    @Transactional
    @Modifying
    @Query("delete from User u where u.username = ?1")
    int deleteByUsername(String username);
}