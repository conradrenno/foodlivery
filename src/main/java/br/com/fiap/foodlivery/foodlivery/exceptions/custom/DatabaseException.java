package br.com.fiap.foodlivery.foodlivery.exceptions.custom;

public class DatabaseException extends RuntimeException{
    public DatabaseException(String message) {
        super(message);
    }
}
