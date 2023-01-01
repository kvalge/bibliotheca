package com.lib.bibliotheca.domain.lending;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LendingRepository extends JpaRepository<Lending, Integer> {
    @Query("select l from Lending l where l.libraryUser.idCode = ?1")
    Lending findByLibraryUserIdCode(String idCode);
}