package com.lib.bibliotheca.domain.library_user;

import com.lib.bibliotheca.domain.user.User;
import com.lib.bibliotheca.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LibraryUserService {

    @Resource
    private LibraryUserMapper libraryUserMapper;

    @Resource
    private LibraryUserRepository libraryUserRepository;

    @Resource
    private UserRepository userRepository;

    public void addNewUser(LibraryUserRequest request) {
        LibraryUser libraryUser = libraryUserMapper.toEntity(request);
        User user = userRepository.findByUserName(request.getUserName());

        LibraryUser newLibraryUser = new LibraryUser();
        newLibraryUser.setIdCode(libraryUser.getIdCode());
        newLibraryUser.setFirstName(libraryUser.getFirstName());
        newLibraryUser.setLastName(libraryUser.getLastName());
        newLibraryUser.setUser(user);

        libraryUserRepository.save(newLibraryUser);
    }

    public List<LibraryUserResponse> getAllUsers() {
        List<LibraryUser> libraryUsers = libraryUserRepository.findAll();
        return libraryUserMapper.toResponse(libraryUsers);
    }

    public LibraryUserResponse getUserByIdCode(String idCode) {
        LibraryUser libraryUser = libraryUserRepository.findByIdCode(idCode);
        return libraryUserMapper.toResponse(libraryUser);
    }
}
