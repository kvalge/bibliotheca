package com.lib.bibliotheca.domain.book;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookService {

    @Resource
    private BookMapper bookMapper;

    @Resource BookRepository bookRepository;

    public void addNewBook(BookRequest request) {
        Book book = bookMapper.toEntity(request);

        Book newBook = new Book();
        newBook.setName(book.getName());
        newBook.setAcquisitionDate(book.getAcquisitionDate());

        int copyQuantity = book.getCopyQuantity();
        newBook.setCopyQuantity(copyQuantity);

        int loanPeriod = 0;
        if (copyQuantity < 5) {
            loanPeriod = 1;
        } else {
            loanPeriod = book.getLoanPeriod();
        }
        newBook.setLoanPeriod(loanPeriod);
        newBook.setLocation(book.getLocation());

        bookRepository.save(newBook);
    }
}
