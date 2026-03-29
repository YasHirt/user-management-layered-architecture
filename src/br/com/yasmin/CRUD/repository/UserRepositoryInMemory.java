package br.com.yasmin.CRUD.repository;
import br.com.yasmin.CRUD.models.User;
import java.util.ArrayList;
import java.util.List;

//Aqui ficam os dados, usado para armazenar seu estado, acesso e manipulação de dados

public class UserRepositoryInMemory implements UserRepository {
    private List<User> users = new ArrayList<>();


    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null; //retorna nulo caso user não encontrado
    }

    @Override
    public void deleteUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                users.remove(user);
            }
        }

    }
    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User findByEmail(String email) {
        for (User user : users) {
            if(user.getEmail().equals(email)) {
                return user;
            }

        }
        return null;
    }
}
