package br.com.fiap.foodlivery.foodlivery.mappers;

import br.com.fiap.foodlivery.foodlivery.dtos.UsuarioRequestDTO;
import br.com.fiap.foodlivery.foodlivery.dtos.UsuarioResponseDTO;
import br.com.fiap.foodlivery.foodlivery.entities.Usuario;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setLogin(dto.getLogin());
        usuario.setEndereco(dto.getEndereco());

        return usuario;
    }

    public Usuario updateFromDTO(UsuarioRequestDTO usuarioDTO, Usuario usuario){

        Optional.ofNullable(usuarioDTO.getNome())
                .filter(v -> !v.isBlank())
                .ifPresent(usuario::setNome);

        Optional.ofNullable(usuarioDTO.getEmail())
                .filter(v -> !v.isBlank())
                .ifPresent(usuario::setEmail);

        Optional.ofNullable(usuarioDTO.getLogin())
                .filter(v -> !v.isBlank())
                .ifPresent(usuario::setLogin);

        Optional.ofNullable(usuarioDTO.getEndereco())
                .ifPresent(usuario::setEndereco);
        return usuario;
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(usuario);
    }

}
