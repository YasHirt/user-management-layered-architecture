package br.com.yasmin.crud.console;
import br.com.yasmin.crud.controller.UserController;
import br.com.yasmin.crud.exceptions.EmailAlreadyExistis;
import br.com.yasmin.crud.models.User;
import java.util.Scanner;

public class UserInterface {
    private UserController userController;
    private Scanner sc = new Scanner(System.in);
    public UserInterface(final UserController userController) {
        this.userController = userController;
    }
    public void interfaceMenu()
    {
        System.out.println(" 1. Create User\n 2. Update User\n 3. Delete User\n 4. List Users\n 5. Get User by email\n 6. Exit");
    }
    public int getInput()
    {
        System.out.println("What would you like to do?");
        return  Integer.parseInt(sc.nextLine());
    }
    public String setUserName()
    {
        while(true)
        {
            try
            {
                System.out.println("Enter your name");
                String userName = sc.nextLine();
                if (userName.isBlank() || userName == null){
                    throw new RuntimeException("Username is null or blank");
                }
                return userName;
            }
            catch(Exception e)
            {
                System.out.println("ERROR: " + e.getMessage());
            }

        }



    }
    public String setUserEmail()
    {
        while(true)
        {
            try
            {
                System.out.println("Enter your email");
                String userEmail = sc.nextLine();
                if (userEmail.isBlank() || userEmail == null){
                    throw new RuntimeException("Email is null or blank");
                }
                return userEmail;
            }
            catch(Exception e)
                {
                System.out.println("ERROR: " + e.getMessage());
                }
        }
    }
    public int  setUserAge()
    {
        while(true)
        {
            try
            {
                System.out.println("Enter your age");
                int userAge = Integer.parseInt(sc.nextLine());
                if (userAge < 0 || userAge > 100){
                    throw new RuntimeException("Invalid Age");
                }
                return userAge;
            }
            catch(Exception e)
                {
                System.out.println("ERROR: " + e.getMessage());
                }
        }
    }
    public void start()
    {
         int userInput = 1;
        while (true)
        {
            User u = new User();


            interfaceMenu();
            userInput = getInput();
            switch (userInput)
            {
                case 1:
                    u.setName(setUserName());
                    u.setEmail(setUserEmail());
                    u.setAge(setUserAge());
                    while (true)
                    {
                        try {
                            userController.registerUser(u);
                            System.out.println("User registered successfully");
                            break;

                        }
                        catch(Exception e)
                        {
                            if (e.getClass() == EmailAlreadyExistis.class) {

                                System.out.println(e.getMessage());
                                u.setEmail(setUserEmail());


                            }

                        }
                    }
                    break;

                case 6:
                    System.exit(0);
                    break;
                case 4:
                    System.out.println(userController.ReadUsers().toString());
                    break;
            }


        }


    }
}
