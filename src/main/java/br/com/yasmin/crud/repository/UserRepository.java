package br.com.yasmin.crud.repository;
import br.com.yasmin.crud.models.User;
import java.util.List;

public interface UserRepository {
    public void save(User user);
    public User getUserById(String id);
    public void deleteUserById(String id);
    public List<User> getAllUsers();
    public User findByEmail(String email);

}
