package br.com.fiap.foodlivery.foodlivery.dtos;

import br.com.fiap.foodlivery.foodlivery.entities.Endereco;
import br.com.fiap.foodlivery.foodlivery.entities.Usuario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UsuarioRequestDTO {

    @NotNull(message = "Name is required")
    @Size(min = 3, max = 80, message = "Must be at least 3 characters and max of 80 characters long")
    private String nome;
    @NotNull(message = "Email is required")
    private String email;
    @NotNull(message = "Login is required")
    private String login;
    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must have at least 6 characters")
    private String senha;
    private Endereco endereco;

    public UsuarioRequestDTO(Usuario usuario) {
        nome = usuario.getNome();
        email = usuario.getEmail();
        login = usuario.getLogin();
        senha = usuario.getSenha();
        endereco = usuario.getEndereco();
    }
}
