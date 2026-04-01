package br.com.yasmin.crud.console;
import br.com.yasmin.crud.controller.UserController;
import br.com.yasmin.crud.exceptions.EmailAlreadyExistis;
import br.com.yasmin.crud.exceptions.UserNotFoundException;
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
        while (true)
        {
            try {
                int input = Integer.parseInt(sc.nextLine());
                if (input < 1 || input > 5)
                {
                    throw new IllegalArgumentException("Invalid input");
                }
                return input;

            } catch (Exception e) {
                System.out.println("Please enter a number between in the correct range");
            }
        }

    }
    public String setUserName()
    {
        while(true)
        {
            try
            {
                System.out.println("Enter your name");
                String userName = sc.nextLine();
                if (userName.isBlank()){
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
                if (userEmail.isBlank()){
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
        System.out.println("What would you like to do?");
        int userInput;
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
                    break; //break case 1
                case 2:
                    int optionToUpdate = 1;
                    User h;
                    while (true) {
                        try {
                            System.out.println("What's the email of the user you want to update?");
                            String EmailToBeFound = sc.nextLine();
                             h = userController.getUserByEmail(EmailToBeFound);
                            break;
                        } catch (UserNotFoundException e) {
                            System.out.println("User not found with this email, try again");
                        }
                    }
                    System.out.println("Welcome mr/ms " + h.getName() + " what would you like to do?");
                    System.out.println("1- Update email\n2- Update name\n3- Update age");
                    optionToUpdate = getInput();
                    switch (optionToUpdate)
                    {
                        case 1:
                            while (true)
                            {
                                try {
                                    System.out.println("Enter your new email");
                                    String newEmail = sc.nextLine();
                                    userController.UpdateUserEmail(h.getId(),  newEmail);
                                    System.out.println("Email updated successfully");
                                    break;
                                } catch (EmailAlreadyExistis e) {
                                    System.out.println(e.getMessage() + " try again");
                                }
                            }

                            break; //break case 1
                        case 2:
                        while (true)
                        {
                            try {
                                System.out.println("What is your new name? ");
                                String newName = sc.nextLine();
                                userController.UpdateUserName(h.getId(),  newName);
                                System.out.println("Name updated successfully");
                                break;
                            } catch (Exception e) {
                                System.out.println(e.getMessage() + " try again");
                            }
                        }
                        break; //break case 2
                            case 3:
                                while (true)
                                {
                                    try {
                                        int newAge;
                                        System.out.println("What is your new age? ");
                                        newAge = Integer.parseInt(sc.nextLine());
                                        userController.UpdateUserAge(h.getId(),  newAge);
                                        System.out.println("Age updated successfully");
                                        break;
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage() + " try again");
                                    }
                                }


                                break; //break case 3
                    }

                break; //break case 2


                case 4:
                    System.out.println(userController.ReadUsers().toString());
                    break;
                case 5:
                    while (true) {
                        try {
                            System.out.println("What's the email of the user you wanna find? ");
                            String email = sc.nextLine();
                            User k = userController.getUserByEmail(email);
                            System.out.println(k.toString());
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage() + " Try again");
                        }

                    }
                break; //break case 5
                case 6:
                    System.exit(0);
                    break;

            }


        }


    }
}
