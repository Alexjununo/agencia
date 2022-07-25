package Helpers;

public class SaldoInsuficienteException extends RuntimeException {
    private String message;

    public SaldoInsuficienteException(String message) {
        super(message);
        this.message = message;
    }
}
