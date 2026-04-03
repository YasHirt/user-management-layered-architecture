package br.com.yasmin.crud.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String message)
    {
        super(message);
    }
}
