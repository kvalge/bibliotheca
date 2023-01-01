package com.lib.bibliotheca.domain.lending;

import com.lib.bibliotheca.domain.book.Book;
import com.lib.bibliotheca.domain.book.BookRepository;
import com.lib.bibliotheca.domain.book.BookService;
import com.lib.bibliotheca.domain.librarian.Librarian;
import com.lib.bibliotheca.domain.librarian.LibrarianRepository;
import com.lib.bibliotheca.domain.library_user.LibraryUser;
import com.lib.bibliotheca.domain.library_user.LibraryUserRepository;
import com.lib.bibliotheca.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class LendingService {

    public static final String STATUS_LENT_OUT = "Välja laenutatud";
    public static final String STATUS_RETURNED = "Tagastatud";
    public static final int LENDING_PERIOD_ONE_WEEK = 1;
    public static final int LENDING_PERIOD_FOUR_WEEKS = 4;

    @Resource
    private LendingMapper lendingMapper;

    @Resource
    private LendingRepository lendingRepository;

    @Resource
    private BookRepository bookRepository;

    @Resource
    private BookService bookService;

    @Resource
    private LibraryUserRepository libraryUserRepository;

    @Resource
    private LibrarianRepository librarianRepository;

    @Resource
    private ValidationService validationService;

    /**
     * It's checked whether there is the requested book in the library and at least one copy of it in the database.
     * And if the library user with the inserted id code is registered as the user of the library.
     * The due date is 1 week when there are less than 5 copies left or when the book has been acquired to
     * the library less than 90 days ago. Otherwise, the due date is 4 weeks.
     * Updates the copy quantity subtracting 1 as it's assumed that it's allowed to lend only one copy of a book
     * at the time by one user.
     */
    public void addLending(LendingRequest request) {
        validationService.bookNotFound(request.getBookName());
        validationService.noCopiesLeft(request.getBookName());
        validationService.libraryUserNotFound(request.getLibraryUserIdCode());

        Lending lending = lendingMapper.toEntity(request);

        Lending newLending = new Lending();

        LocalDate lendingDate = lending.getLendingDate();
        newLending.setLendingDate(lendingDate);

        String bookName = lending.getBook().getName();
        Integer copyQuantity = bookRepository.findByName(bookName).getCopyQuantity();
        LocalDate bookAcquisitionDate = bookRepository.findByName(bookName).getAcquisitionDate();
        LocalDate dueDate;
        long bookAge = Math.abs(ChronoUnit.DAYS.between(bookAcquisitionDate, lendingDate));
        if (copyQuantity < 5 || bookAge < 90) {
            dueDate = lendingDate.plusWeeks(LENDING_PERIOD_ONE_WEEK);
        } else {
            dueDate = lendingDate.plusWeeks(LENDING_PERIOD_FOUR_WEEKS);
        }
        newLending.setDueDate(dueDate);

        newLending.setStatus(STATUS_LENT_OUT);

        LibraryUser libraryUser = libraryUserRepository.findByIdCode(request.getLibraryUserIdCode());
        newLending.setLibraryUser(libraryUser);

        Book book = bookRepository.findByName(bookName);
        newLending.setBook(book);

        Librarian librarian = librarianRepository.findByIdCode(request.getLibrarianIdCode());
        newLending.setLibrarian(librarian);

        lendingRepository.save(newLending);

        bookService.updateCopyQuantity(bookName, copyQuantity - 1);
    }

    /**
     * Updates the book from the list of not yet returned books (books the same user has been lent and returned
     * before is left out). Returned lending gets a return date value and the new status "Tagastatud".
     * Updates the copy quantity adding 1 as it's assumed that it's allowed to lend only one copy of a book
     * at the time by one user.
     */
    public void updateOnReturn(String idCode, String bookName) {
        List<Lending> lendingList = lendingRepository.findByUserIdCodeAndBookName(idCode, bookName);
        for (Lending lending : lendingList) {
            if (lending.getStatus().equals(STATUS_LENT_OUT)) {
                lending.setReturnDate(LocalDate.now());
                lending.setStatus(STATUS_RETURNED);
                lendingRepository.save(lending);

                Integer copyQuantity = lending.getBook().getCopyQuantity();
                bookService.updateCopyQuantity(bookName, copyQuantity + 1);
            }
        }
    }

    /**
     * Finds lending list with status "Välja laenutatud" and selects books which due date is before
     * current date. The difference between due date and current date is returned as daysOverdue property
     * value in LendingReturn list.
     */
    public List<LendingReturn> getOverdueLendingList() {
        List<Lending> lendingList = lendingRepository.findByStatus(STATUS_LENT_OUT);
        List<Lending> newLendingList = new ArrayList<>();
        for (Lending lending : lendingList) {
            if (lending.getDueDate().isBefore(LocalDate.now())) {
                newLendingList.add(lending);
            }
        }

        List<LendingDto> lendingDtoList = lendingMapper.toDto(newLendingList);
        List<LendingReturn> lendingReturns = new ArrayList<>();
        for (LendingDto dto : lendingDtoList) {
            long daysOver = Math.abs(ChronoUnit.DAYS.between(dto.getDueDate(), LocalDate.now()));
            LendingReturn lendingReturn = new LendingReturn();
            lendingReturn.setLibraryUserFirstName(dto.getLibraryUser().getFirstName());
            lendingReturn.setLibraryUserLastName(dto.getLibraryUser().getLastName());
            lendingReturn.setBookName(dto.getBook().getName());
            lendingReturn.setDaysOverdue(daysOver);
            lendingReturns.add(lendingReturn);
        }

        return lendingReturns;
    }
}
