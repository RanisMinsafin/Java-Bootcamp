package ex04.transaction;

public class TransactionNotFoundException extends RuntimeException{
    TransactionNotFoundException(String message){
       super(message);
    }
}
