package br.com.yasmin.crud.app;


import br.com.yasmin.crud.connection.ConnectionFactory;
import br.com.yasmin.crud.console.UserInterface;
import br.com.yasmin.crud.controller.UserController;
import br.com.yasmin.crud.repository.UserRepository;
import br.com.yasmin.crud.repository.UserRepositoryMySql;
import br.com.yasmin.crud.services.UserServices;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.Objects;

public class Main {
    private final static Dotenv dotenv = Dotenv.load();
    private final  static String url = Objects.requireNonNull(dotenv.get("DB_URL"), "DB_URL is null");
    private final static String username = Objects.requireNonNull(dotenv.get("DB_USERNAME"), "DB_USER is null");

    private final static String password = Objects.requireNonNull(dotenv.get("DB_PASSWORD"), "DB_PASSWORD is null");
    private final static ConnectionFactory factory = new ConnectionFactory(url, username, password);
    private final static UserRepository userRepository = new UserRepositoryMySql(factory);

    private final static UserServices userServices = new UserServices(userRepository);
    private final  static UserController userController = new UserController(userServices);
    final private static UserInterface userInterface = new UserInterface(userController);
    public static void main(String[] args) {
        userInterface.start();
    }
}
