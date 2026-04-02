package br.com.yasmin.crud.services;
import br.com.yasmin.crud.models.User;
import br.com.yasmin.crud.repository.UserRepository;
import java.util.List;
import br.com.yasmin.crud.exceptions.*;

public class UserServices {
    private final UserRepository userRepository;
    public UserServices(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    private void verifyIfEmailIsUnique(String email) {
           if (userRepository.findByEmail(email) != null) {
               throw new EmailAlreadyExistis("Email Already Exists");
           }
    }
    private User validatesUserExists(String id) {
        User user = userRepository.getUserById(id);
        if (user == null)
        {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }
    public void registerUser(User u)
    {
        if (u.getName() == null || u.getName().isBlank())
        {
            throw new IllegalArgumentException(" Username is null or blank");
        }
        if (u.getEmail() == null || u.getEmail().isBlank())
        {
            throw new IllegalArgumentException(" Email is null or blank");
        }
        if (u.getAge() <= 0)
        {
            throw new IllegalArgumentException(" Age is negative or 0");
        }
        verifyIfEmailIsUnique(u.getEmail());
        userRepository.save(u);

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
        User u = userRepository.getUserById(id);
        u.setName(newName);
    }

    public void updateUserAge(String id, int newAge)
    {
       User u = validatesUserExists(id);
        if (newAge <= 0)
        {
            throw new IllegalArgumentException("Age is negative or 0");
        }
        u.setAge(newAge);
    }
    public void updateUserEmail(String id, String newEmail)
    {
        User u = validatesUserExists(id);
        if (newEmail.isBlank())
        {
            throw new IllegalArgumentException("Email is null or blank");
        }
        verifyIfEmailIsUnique(newEmail);
        u.setEmail(newEmail);
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

