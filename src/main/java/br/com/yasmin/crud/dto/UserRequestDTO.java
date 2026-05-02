package br.com.yasmin.crud.dto;

public record UserRequestDTO(String name, String email, int age) {
    public UserRequestDTO {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name can´t be null");
        }
        if (email == null || email.isEmpty())
        {
            throw new IllegalArgumentException("Email can´t be null");
        }
        if (age <= 0)
        {
            throw new IllegalArgumentException("Age can´t be negative or 0");
        }
    }
}
