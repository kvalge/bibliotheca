package com.lib.bibliotheca.domain.librarian;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {

    @Query("select l from Librarian l where l.idCode = ?1")
    Librarian findByIdCode(String idCode);

    @Transactional
    @Modifying
    @Query("delete from Librarian l where l.idCode = ?1")
    int deleteByIdCode(String idCode);
}