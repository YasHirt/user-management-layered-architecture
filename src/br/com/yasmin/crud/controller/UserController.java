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
    public boolean registerUser(User user)
    {
        userServices.registerUser(user);
        return true;
    }
    public List<User> ReadUsers()
    {
        return  userServices.getAllUsers();
    }

}
