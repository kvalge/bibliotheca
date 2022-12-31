package com.lib.bibliotheca.domain.lending;

import com.lib.bibliotheca.domain.book.Book;
import com.lib.bibliotheca.domain.book.BookRepository;
import com.lib.bibliotheca.domain.librarian.Librarian;
import com.lib.bibliotheca.domain.librarian.LibrarianRepository;
import com.lib.bibliotheca.domain.library_user.LibraryUser;
import com.lib.bibliotheca.domain.library_user.LibraryUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.Period;

@Service
public class LendingService {

    @Resource
    private LendingMapper lendingMapper;

    @Resource
    private LendingRepository lendingRepository;

    @Resource
    private BookRepository bookRepository;

    @Resource
    private LibraryUserRepository libraryUserRepository;

    @Resource
    private LibrarianRepository librarianRepository;

    /**
     * The due date is 1 week when there are less than 5 copies left or when the book has been in library
     * less than 90 DAYS. Otherwise, the due date is 4 weeks.
     */
    public void addLending(LendingRequest request) {
        Lending lending = lendingMapper.toEntity(request);

        Lending newLending = new Lending();

        LocalDate lendingDate = lending.getLendingDate();
        newLending.setLendingDate(lendingDate);

        int lendingPeriodOneWeek = 1;
        int lendingPeriodFourWeek = 4;

        String bookName = request.getBookName();
        Integer copyQuantity = bookRepository.findByName(bookName).getCopyQuantity();
        LocalDate bookAcquisitionDate = bookRepository.findByName(bookName).getAcquisitionDate();
        LocalDate dueDate;
        Period bookAge = Period.between(bookAcquisitionDate, lendingDate);
        long diffDays = bookAge.getDays();
        if (copyQuantity < 5 || diffDays < 90) {
            dueDate = lendingDate.plusWeeks(lendingPeriodOneWeek);
        } else {
            dueDate = lendingDate.plusWeeks(lendingPeriodFourWeek);
        }
        newLending.setDueDate(dueDate);

        newLending.setStatus("Lent out");

        LibraryUser libraryUser = libraryUserRepository.findByIdCode(request.getLibraryUserIdCode());
        newLending.setLibraryUser(libraryUser);

        Book book = bookRepository.findByName(bookName);
        newLending.setBook(book);

        Librarian librarian = librarianRepository.findByIdCode(request.getLibrarianIdCode());
        newLending.setLibrarian(librarian);

        lendingRepository.save(newLending);
    }
}
