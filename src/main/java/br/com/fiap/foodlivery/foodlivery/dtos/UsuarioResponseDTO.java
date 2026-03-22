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
public class UsuarioResponseDTO {

    public Long id;
    public String nome;
    public String email;
    public String login;
    public Date dataUltimaAlteracao;
    public Endereco endereco;

    public UsuarioResponseDTO(Usuario usuario){
        id = usuario.getId();
        nome = usuario.getNome();
        email = usuario.getEmail();
        login = usuario.getLogin();
        dataUltimaAlteracao = usuario.getDataUltimaAlteracao();
        endereco = usuario.getEndereco();
    }
}
