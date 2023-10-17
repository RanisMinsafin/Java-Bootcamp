package ex03.transaction;

public class TransactionNotFoundException extends RuntimeException{
    TransactionNotFoundException(String message){
       super(message);
    }
}
