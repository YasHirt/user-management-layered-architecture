package br.com.yasmin.crud.app;

import br.com.yasmin.crud.console.UserInterface;
import br.com.yasmin.crud.controller.UserController;
import br.com.yasmin.crud.repository.UserRepository;
import br.com.yasmin.crud.repository.UserRepositoryInMemory;
import br.com.yasmin.crud.services.UserServices;

public class Main {
    final static UserRepository userRepository = new UserRepositoryInMemory();
    private static  UserServices userServices = new UserServices(userRepository);
    private static UserController userController = new UserController(userServices);
    private static  UserInterface userInterface = new UserInterface(userController);
    public static void main(String[] args) {
        userInterface.start();

    }
}
