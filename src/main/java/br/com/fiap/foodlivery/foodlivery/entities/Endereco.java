package br.com.fiap.foodlivery.foodlivery.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Endereco {

    String rua;
    String bairro;
    String cidade;
    String estado;
    String cep;
}
