package br.com.yasmin.crud.console;
import br.com.yasmin.crud.controller.UserController;
import br.com.yasmin.crud.exceptions.EmailAlreadyExistsException;
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

            } catch (IllegalArgumentException e) {
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
                if (userEmail.isBlank())
                {
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
        int userAge;
        while (true) {
            System.out.println("Enter your AGE");

            try {
                userAge = Integer.parseInt(sc.nextLine());
                if (userAge < 0 || userAge > 100) {
                    System.out.println("Invalid age, try again");
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
            interfaceMenu();
            userInput = getInputInt(1,6);
            switch (userInput)
            {
                case 1:
                    handleCreateUser();
                    break;
                case 2:
                    handleUpdateUser();
                    break;
                case 3:
                    handleDeleteUser();
                    break;
                case 4:
                    System.out.println(userController.readUsers().toString());
                    break;
                case 5:
                    handleFindUserByEmail();
                    break;
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
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Try again");
            }

        }
    }

    private void handleUpdateUser() {
        int optionToUpdate;
        User h;
        while (true) {
            try {
                System.out.println("What's the email of the user you want to update?");
                String EmailToBeFound = sc.nextLine();
                h = userController.getUserByEmail(EmailToBeFound);
                break;
            } catch (UserNotFoundException e) {
                System.out.println("User not found with this email, what would you like to do?\n 1- Try again\n 2- Exit to main menu \n 3- Exit system");
                optionToUpdate = getInputInt(1, 3);
                if (optionToUpdate == 2) {
                    return;
                }
                if (optionToUpdate == 3) {
                    System.exit(0);
                }
            }
        }
        System.out.println("Welcome " + h.getName() + " what would you like to do?");
        System.out.println("1- Update email\n2- Update name\n3- Update age");
        optionToUpdate = getInputInt(1, 3);
        switch (optionToUpdate) {
            case 1:
                while (true) {
                    try {
                        System.out.println("Enter your new email");
                        String newEmail = sc.nextLine();
                        userController.updateUserEmail(h.getId(), newEmail);
                        System.out.println("Email updated successfully");
                        break;
                    } catch (EmailAlreadyExistsException e) {
                        System.out.println(e.getMessage() + " try again");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage() + " try again");
                    }
                }

                break; //break case 1
            case 2:
                while (true) {
                    try {
                        System.out.println("What is your new name? ");
                        String newName = sc.nextLine();
                        userController.updateUserName(h.getId(), newName);
                        System.out.println("Name updated successfully");
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage() + " try again");
                    }
                }
                break;
            case 3:
                try {
                    userController.updateUserAge(h.getId(), setUserAge());
                    System.out.println("Age updated successfully");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage() + " try again");
                }
                break;
        }
    }
    private void handleCreateUser() {
        User u = new User();
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
            catch(EmailAlreadyExistsException e)
            {
                    System.out.println(e.getMessage());
                    u.setEmail(setUserEmail());
            }
        }
        }
        public void handleDeleteUser()
        {
            User deletion;
            System.out.println("What is the email of the user you wanna delete? ");
            String email = sc.nextLine();
            while (true)
            {
                try {
                    deletion = userController.getUserByEmail(email);
                    break;
                } catch (UserNotFoundException e) {
                    System.out.println("User not found with this email, try again");
                    email = sc.nextLine();
                }
            }
            System.out.println("User found, are you sure you want to delete  " + deletion.getName() + " | Email: " + deletion.getEmail());
            System.out.println("1- Yes\n2- No");
            int decision = getInputInt(1,2);
            switch (decision) {
                case 1:
                    userController.deleteUser(deletion.getId());
                    System.out.println("User deleted successfully");
                    break;
                    case 2:
                        return;
            }

        }
}
