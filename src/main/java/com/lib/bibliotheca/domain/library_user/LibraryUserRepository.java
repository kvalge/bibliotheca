package com.lib.bibliotheca.domain.library_user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Integer> {
    @Query("select l from LibraryUser l where l.idCode = ?1")
    LibraryUser findByIdCode(String idCode);

    @Transactional
    @Modifying
    @Query("delete from LibraryUser l where l.idCode = ?1")
    int deleteByIdCode(String idCode);
}