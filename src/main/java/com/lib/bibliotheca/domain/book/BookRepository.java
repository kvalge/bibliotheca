package com.lib.bibliotheca.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select b from Book b where upper(b.name) = upper(?1)")
    Book findByName(String name);
}