package br.com.fiap.foodlivery.foodlivery.dtos;

import br.com.fiap.foodlivery.foodlivery.entities.Endereco;
import br.com.fiap.foodlivery.foodlivery.entities.Usuario;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UsuarioRequestDTO {

    private String nome;
    private String email;
    private String login;
    private String senha;
    private Date dataUltimaAlteracao;
    private Endereco endereco;

    public UsuarioRequestDTO(Usuario usuario) {
        nome = usuario.getNome();
        email = usuario.getEmail();
        login = usuario.getLogin();
        senha = usuario.getSenha();
        dataUltimaAlteracao = usuario.getDataUltimaAlteracao();
        endereco = usuario.getEndereco();
    }
}
