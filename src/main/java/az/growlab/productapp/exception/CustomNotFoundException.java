package az.growlab.productapp.exception;

public class CustomNotFoundException extends RuntimeException {

    public CustomNotFoundException(String message){
        super(message);
    }
}
