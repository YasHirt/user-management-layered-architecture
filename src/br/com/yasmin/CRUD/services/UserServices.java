package br.com.yasmin.CRUD.services;
import br.com.yasmin.CRUD.models.User;
import br.com.yasmin.CRUD.repository.UserRepository;
import java.util.List;


public class UserServices {
    private UserRepository userRepository;
    public UserServices(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    private void verifyIfEmailIsUnique(String email) {
        //Assume email já validado antes
           if (userRepository.findByEmail(email) != null) {
               throw new RuntimeException("Email already exists");
           }
    }
    public void registerUser(User u)
    {
        if (u.getName() == null || u.getName().isBlank())
        {
            throw new IllegalArgumentException("Username is null or blank");
        }
        if (u.getEmail() == null || u.getEmail().isBlank())
        {
            throw new IllegalArgumentException("Email is null or blank");
        }
        if (u.getAge() <= 0)
        {
            throw new IllegalArgumentException("Age is negative or 0");
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

    private User validatesUserExists(String id) {
        User user = userRepository.getUserById(id);
        if (user == null)
        {
            throw new IllegalArgumentException("User not found");
        }
        return user;
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
    public List<User> getAllUsers()
    {
        return userRepository.getAllUsers();
    }
}

