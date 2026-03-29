package br.com.yasmin.CRUD.repository;

import br.com.yasmin.CRUD.models.User;

import java.util.List;

public interface UserRepository {
    public void save(User user);
    public User getUserById(String id);
    public void deleteUserById(String id);
    public List<User> getAllUsers();
    public User findByEmail(String email);

}
