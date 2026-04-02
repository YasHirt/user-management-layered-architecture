package br.com.yasmin.crud.console;
import br.com.yasmin.crud.controller.UserController;
import br.com.yasmin.crud.exceptions.EmailAlreadyExistis;
import br.com.yasmin.crud.exceptions.UserNotFoundException;
import br.com.yasmin.crud.models.User;
import java.util.Scanner;

public class UserInterface {
    private final UserController userController;
    private final Scanner sc = new Scanner(System.in);
    public UserInterface(final UserController userController) {
        this.userController = userController;
    }
    public void interfaceMenu()
    {
        System.out.println(" 1. Create User\n 2. Update User\n 3. Delete User\n 4. List Users\n 5. Get User by email\n 6. Exit");
    }
    public int getInputInt(int min, int max)
    {
        while (true)
        {
            try {
                int input = Integer.parseInt(sc.nextLine());
                if (input < min || input > max)
                {
                    throw new IllegalArgumentException("Invalid input");
                }
                return input;

            } catch (Exception e) {
                System.out.println("Please enter a number between " + min + " and " + max);
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
                    throw new IllegalArgumentException("Username is null or blank");
                }
                return userName;
            }
            catch(IllegalArgumentException e)
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
                    throw new IllegalArgumentException("Email is null or blank");
                }
                return userEmail;
            }
            catch(IllegalArgumentException e)
                {
                System.out.println("ERROR: " + e.getMessage());
                }
        }
    }
    public int  setUserAge()
    {
        //there is a bug here somewhere, solve it
        System.out.println("Enter your AGE");
        int userAge;
        while (true) {
            try {
                userAge = Integer.parseInt(sc.nextLine());
                if (userAge < 0 || userAge > 100) {
                    System.out.println("Invalid age, try again");
                    userAge = Integer.parseInt(sc.nextLine());
                }
                else {
                    return userAge;
                }
            } catch (NumberFormatException e) {
                System.out.println("Age not numeric, try again");
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
            userInput = getInputInt(1,6);
            switch (userInput)
            {
                case 1:
                    handleCreateUser(u);
                    break; //break case 1
                case 2:
                    handleUpdateUser();
                    break; //break case 2
                case 4:
                    System.out.println(userController.ReadUsers().toString());
                    break;
                case 5:
                    handleFindUserByEmail();
                    break; //break case 5
                case 6:
                    System.exit(0);
                    break;
            }
        }
    }

    private void handleFindUserByEmail() {
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
    }

    private void handleUpdateUser() {
        int optionToUpdate;
        User h =  new User();
        while (true)
        {
            try {
                System.out.println("What's the email of the user you want to update?");
                String EmailToBeFound = sc.nextLine();
                h = userController.getUserByEmail(EmailToBeFound);
                break;
            } catch (UserNotFoundException e) {
                System.out.println("User not found with this email, what would you like to do?\n 1- Try again\n 2- Exit to main menu \n 3- Exit system");
                optionToUpdate = getInputInt(1,3);
                if (optionToUpdate == 2) {
                    h.setName("not found"); //jeito para voltar ao menu principal
                    break;
                }
                if (optionToUpdate == 3) {
                    System.exit(0);
                }
            }
        }
        if (h.getEmail() != null)
        {
            System.out.println("Welcome mr/ms " + h.getName() + " what would you like to do?");
            System.out.println("1- Update email\n2- Update name\n3- Update age");
            optionToUpdate = getInputInt(1,3);
            switch (optionToUpdate) {
                case 1:
                    while (true) {
                        try {
                            System.out.println("Enter your new email");
                            String newEmail = sc.nextLine();
                            userController.UpdateUserEmail(h.getId(), newEmail);
                            System.out.println("Email updated successfully");
                            break;
                        } catch (EmailAlreadyExistis e) {
                            System.out.println(e.getMessage() + " try again");
                        }
                        catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage() + " try again");
                        }
                    }

                    break; //break case 1
                case 2:
                    while (true) {
                        try {
                            System.out.println("What is your new name? ");
                            String newName = sc.nextLine();
                            userController.UpdateUserName(h.getId(), newName);
                            System.out.println("Name updated successfully");
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage() + " try again");
                        }
                    }
                    break; //break case 2
                case 3:
                    while (true) {
                        try {
                            int newAge;
                            System.out.println("What is your new age? ");
                            newAge = Integer.parseInt(sc.nextLine());
                            userController.UpdateUserAge(h.getId(), newAge);
                            System.out.println("Age updated successfully");
                            break;
                        } catch (Exception e) {
                            System.out.println(e.getMessage() + " try again");
                        }
                    }


                    break; //break case 3

            }

        }
        else
        {
            return;
        }
    }
    private void handleCreateUser(User u) {
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
            catch(EmailAlreadyExistis e)
            {
                    System.out.println(e.getMessage());
                    u.setEmail(setUserEmail());
            }
        }
        }
}
