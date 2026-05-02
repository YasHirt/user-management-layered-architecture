package br.com.yasmin.crud.controller;

import br.com.yasmin.crud.dto.ApiResponse;
import br.com.yasmin.crud.dto.UserRequestDTO;
import br.com.yasmin.crud.dto.UserResponseDTO;
import br.com.yasmin.crud.models.User;
import br.com.yasmin.crud.services.UserServices;
import java.util.List;

public class UserController
{
    private final UserServices userServices;
    public  UserController(final UserServices userServices)
    {
        this.userServices = userServices;
    }
    public ApiResponse registerUser(UserRequestDTO user)
    {
        UserResponseDTO userResponseDTO = userServices.registerUser(user);
        return new ApiResponse(userResponseDTO, "OK", 200);
    }
    public List<User> readUsers()
    {
        return  userServices.getAllUsers();
    }

    public User getUserByEmail(String email)
    {
        return userServices.getUserByEmail(email);
    }
    public void updateUserEmail(String id, String newEmail)
    {
        userServices.updateUserEmail(id, newEmail);
    }
    public void updateUserName(String id, String newName)
    {
        userServices.updateUserName(id, newName);
    }
    public void updateUserAge(String id, int newAge)
    {
        userServices.updateUserAge(id, newAge);
    }
    public void deleteUser(String id)
    {
        userServices.deleteUser(id);
    }

}
