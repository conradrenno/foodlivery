package br.com.fiap.foodlivery.foodlivery.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    public String email;
    public String login;
    public String senha;
    public Date dataUltimaAlteracao;

    @Embedded
    public Endereco endereco;

    @PrePersist
    public void prePersist() {
        dataUltimaAlteracao = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        dataUltimaAlteracao = new Date();
    }
    
}
