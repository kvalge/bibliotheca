package com.lib.bibliotheca.domain.library_user;

import com.lib.bibliotheca.domain.user.User;
import com.lib.bibliotheca.domain.user.UserRepository;
import com.lib.bibliotheca.domain.user.UserService;
import com.lib.bibliotheca.validation.ValidationService;
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

    @Resource
    private UserService userService;

    @Resource
    private ValidationService validationService;

    /**
     * Validates whether the library user already exists in database before adding new user.
     */
    public void addNewUser(LibraryUserRequest request) {
        validationService.libraryUserExists(request.getIdCode());

        LibraryUser libraryUser = libraryUserMapper.toEntity(request);
        User user = userRepository.findByUserName(request.getUserName());

        LibraryUser newLibraryUser = new LibraryUser();
        newLibraryUser.setIdCode(libraryUser.getIdCode());
        newLibraryUser.setFirstName(libraryUser.getFirstName());
        newLibraryUser.setLastName(libraryUser.getLastName());
        newLibraryUser.setUser(user);

        libraryUserRepository.save(newLibraryUser);
    }

    /**
     * It is checked isn't the library users' database empty before returning all users.
     */
    public List<LibraryUserResponse> getAllUsers() {
        validationService.libraryUsersNotFound();

        List<LibraryUser> libraryUsers = libraryUserRepository.findAll();
        return libraryUserMapper.toResponse(libraryUsers);
    }

    /**
     * It is checked is there a requested library user in database before finding user by id code.
     */
    public LibraryUserResponse getUserByIdCode(String idCode) {
        validationService.libraryUserNotFound(idCode);

        LibraryUser libraryUser = libraryUserRepository.findByIdCode(idCode);
        return libraryUserMapper.toResponse(libraryUser);
    }

    /**
     * It is checked is there a requested library user in database before finding user by id code
     * for deleting.
     */
    public void deleteUser(String idCode) {
        validationService.libraryUserNotFound(idCode);

        String username = libraryUserRepository.findByIdCode(idCode).getUser().getUsername();

        libraryUserRepository.deleteByIdCode(idCode);

        userService.deleteUser(username);
    }
}
