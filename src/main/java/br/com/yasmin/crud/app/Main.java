package br.com.yasmin.crud.app;


import br.com.yasmin.crud.console.UserInterface;
import br.com.yasmin.crud.controller.UserController;
import br.com.yasmin.crud.repository.UserRepository;
import br.com.yasmin.crud.repository.UserRepositoryInMemory;
import br.com.yasmin.crud.services.UserServices;

public class Main {
    final static UserRepository userRepository = new UserRepositoryInMemory();
    final private static UserServices userServices = new UserServices(userRepository);
    final private static UserController userController = new UserController(userServices);
    final private static UserInterface userInterface = new UserInterface(userController);
    public static void main(String[] args) {
        userInterface.start();

    }
}
