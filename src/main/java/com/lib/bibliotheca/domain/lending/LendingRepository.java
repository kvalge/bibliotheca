package com.lib.bibliotheca.domain.lending;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LendingRepository extends JpaRepository<Lending, Integer> {

    @Query("select l from Lending l where l.libraryUser.idCode = ?1 and upper(l.book.name) = upper(?2)")
    List<Lending> findByUserIdCodeAndBookName(String idCode, String name);
}