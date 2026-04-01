package br.com.yasmin.crud.controller;

import br.com.yasmin.crud.console.UserInterface;
import br.com.yasmin.crud.models.User;
import br.com.yasmin.crud.services.UserServices;

import java.util.List;

public class UserController
{
    private UserServices userServices;
    public  UserController(final UserServices userServices)
    {
        this.userServices = userServices;
    }
    public void registerUser(User user)
    {
        userServices.registerUser(user);
    }
    public List<User> ReadUsers()
    {
        return  userServices.getAllUsers();
    }

    public User getUserByEmail(String email)
    {
        return userServices.getUserByEmail(email);
    }
    public void UpdateUserEmail(String id, String newEmail)
    {
        userServices.updateUserEmail(id, newEmail);
    }
    public void UpdateUserName(String id, String newName)
    {
        userServices.updateUserName(id, newName);
    }
    public void UpdateUserAge(String id, int newAge)
    {
        userServices.updateUserAge(id, newAge);
    }

}
