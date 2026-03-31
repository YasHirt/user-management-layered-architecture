package br.com.yasmin.crud.exceptions;

public class EmailAlreadyExistis extends RuntimeException{
    public EmailAlreadyExistis(String message)
    {
        super(message);
    }
}
