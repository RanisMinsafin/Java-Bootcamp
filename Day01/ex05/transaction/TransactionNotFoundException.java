package ex05.transaction;

public class TransactionNotFoundException extends RuntimeException{
    TransactionNotFoundException(String message){
       super(message);
    }
}
