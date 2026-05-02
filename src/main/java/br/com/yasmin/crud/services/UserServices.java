package br.com.yasmin.crud.services;

import br.com.yasmin.crud.dto.UserRequestDTO;
import br.com.yasmin.crud.dto.UserResponseDTO;
import br.com.yasmin.crud.exceptions.EmailAlreadyExistsException;
import br.com.yasmin.crud.exceptions.UserNotFoundException;
import br.com.yasmin.crud.models.User;
import br.com.yasmin.crud.repository.UserRepository;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UserServices {

    private final UserRepository userRepository;
    public UserServices(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    private void verifyIfEmailIsUnique(String email) {
           if (userRepository.findByEmail(email) != null) {
               throw new EmailAlreadyExistsException("Email Already Exists");
           }
    }
    private User validatesUserExists(String id) {
        User user = userRepository.findUserById(id);
        if (user == null)
        {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }
    public UserResponseDTO registerUser(UserRequestDTO u)
    {
        verifyIfEmailIsUnique(u.email());
        User user = toEntity(u);
        User userSaved = userRepository.save(user);
        return toDTO(userSaved);
    }
    private User toEntity(UserRequestDTO u) {
        return new User(u.name(), u.email(), u.age());
    }
    private UserResponseDTO toDTO(User u)
    {
        return new UserResponseDTO(u.getId(), u.getName(), u.getEmail(), u.getAge());
    }

    public void deleteUser(String id)
    {
        validatesUserExists(id);
        userRepository.deleteUserById(id);
    }

    public void updateUserName(String id, String newName)
    {
        validatesUserExists(id);
        if (newName == null || newName.isBlank())
        {
            throw new IllegalArgumentException("New name is null or blank");
        }
        User u = userRepository.findUserById(id);
        userRepository.updateName(u.getId(), newName);
    }

    public void updateUserAge(String id, int newAge)
    {
       User u = validatesUserExists(id);
        if (newAge <= 0)
        {
            throw new IllegalArgumentException("Age is negative or 0");
        }
        userRepository.updateAge(u.getId(), newAge);
    }
    public void updateUserEmail(String id, String newEmail)
    {
        User u = validatesUserExists(id);
        if (newEmail.isBlank())
        {
            throw new IllegalArgumentException("Email is null or blank");
        }
        verifyIfEmailIsUnique(newEmail);
        userRepository.updateEmail(u.getId(), newEmail);
    }

    public User getUserByEmail(String email)
    {
        if (email == null || email.isBlank())
        {
            throw new IllegalArgumentException("Email is null or blank");
        }
       User u = userRepository.findByEmail(email);
       if (u == null)
        {
            throw new UserNotFoundException("User not found with this email");
        }
        return u;
    }
    public List<User> getAllUsers()
    {
        return userRepository.getAllUsers();
    }
}

