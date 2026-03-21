package br.com.fiap.foodlivery.foodlivery.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Usuario {
    public String nome;
    public String email;
    public String login;
    public String senha;
    public Date dataUltimaAlteracao;

    @Embedded
    public Endereco endereco;
    
}
