package edu.school21.chat.repository;

public class NotSavedSubEntityException extends RuntimeException{
    NotSavedSubEntityException(String message){
        super(message);
    }
}
