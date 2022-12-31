package com.lib.bibliotheca.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b where upper(b.name) = upper(?1)")
    Book findByName(String name);

    @Transactional
    @Modifying
    @Query("delete from Book b where upper(b.name) = upper(?1)")
    int deleteByName(String name);
}