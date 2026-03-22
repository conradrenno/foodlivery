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

    public Usuario(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.dataUltimaAlteracao = usuario.getDataUltimaAlteracao();
        this.endereco = usuario.getEndereco();
    }
    
}
