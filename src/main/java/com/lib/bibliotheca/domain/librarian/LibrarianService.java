package com.lib.bibliotheca.domain.librarian;

import com.lib.bibliotheca.domain.user.User;
import com.lib.bibliotheca.domain.user.UserRepository;
import com.lib.bibliotheca.validation.ValidationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LibrarianService {

    @Resource
    private LibrarianMapper librarianMapper;

    @Resource
    private LibrarianRepository librarianRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private ValidationService validationService;

    /**
     * Validates whether the librarian already exists in database before adding new librarian.
     */
    public void addLibrarian(LibrarianRequest request) {
        validationService.librarianExists(request.getIdCode());

        Librarian librarian = librarianMapper.toEntity(request);

        User user = userRepository.findByUserName(request.getUserName());

        Librarian newLibrarian = new Librarian();
        newLibrarian.setIdCode(librarian.getIdCode());
        newLibrarian.setUser(user);

        librarianRepository.save(newLibrarian);
    }

    /**
     * It is checked isn't the librarians' database empty before returning all librarians.
     */
    public List<LibrarianResponse> getAllLibrarians() {
        validationService.librariansNotFound();

        List<Librarian> librarians = librarianRepository.findAll();

        return librarianMapper.toResponse(librarians);
    }

    /**
     * It is checked is there a requested librarian in database before finding librarian by id code
     * for deleting.
     */
    public void deleteLibrarian(String idCode) {
        validationService.librarianNotFound(idCode);

        librarianRepository.deleteByIdCode(idCode);
    }
}
