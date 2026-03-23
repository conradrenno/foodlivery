package br.com.fiap.foodlivery.foodlivery.exceptions.dtos;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CustomError {

    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private String path;

    public CustomError(LocalDateTime timestamp, Integer status, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
