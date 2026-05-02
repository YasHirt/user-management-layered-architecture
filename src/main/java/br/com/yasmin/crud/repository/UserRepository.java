package br.com.yasmin.crud.repository;
import br.com.yasmin.crud.models.User;
import java.util.List;

public interface UserRepository {
    public User save(User user);
    public User findUserById(String id);
    public void deleteUserById(String id);
    public List<User> getAllUsers();
    public User findByEmail(String email);
    public void updateName(String id, String name);
    public void updateAge(String id, int age);
    public void updateEmail(String id, String email);

}
