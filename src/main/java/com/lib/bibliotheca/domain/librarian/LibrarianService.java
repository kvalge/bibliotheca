package com.lib.bibliotheca.domain.librarian;

import com.lib.bibliotheca.domain.user.User;
import com.lib.bibliotheca.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LibrarianService {

    @Resource
    private LibrarianMapper librarianMapper;

    @Resource
    private LibrarianRepository librarianRepository;

    @Resource
    private UserRepository userRepository;

    public void addLibrarian(LibrarianRequest request) {
        Librarian librarian = librarianMapper.toEntity(request);

        User user = userRepository.findByUserName(request.getUserName());

        Librarian newLibrarian = new Librarian();
        newLibrarian.setIdCode(librarian.getIdCode());
        newLibrarian.setUser(user);

        librarianRepository.save(newLibrarian);
    }
}
