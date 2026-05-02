package br.com.yasmin.crud.repository;


//Aqui ficam os dados, usado para armazenar seu estado, acesso e manipulação de dados

import br.com.yasmin.crud.models.User;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryInMemory implements UserRepository {
    private List<User> users = new ArrayList<>();


    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User findUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null; //retorna nulo caso user não encontrado
    }

    @Override
    public void deleteUserById(String id) {
        users.remove(findUserById(id));

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

    @Override
    public void updateName(String id, String name) {
    System.out.println("Name updated");
            }

    @Override
    public void updateAge(String id, int age) {
        System.out.println("gotta do this one");
    }

    @Override
    public void updateEmail(String id, String email) {
        System.out.println("gotta do this one");
    }
}
