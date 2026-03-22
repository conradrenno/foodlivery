package br.com.fiap.foodlivery.foodlivery.mappers;

import br.com.fiap.foodlivery.foodlivery.dtos.UsuarioRequestDTO;
import br.com.fiap.foodlivery.foodlivery.dtos.UsuarioResponseDTO;
import br.com.fiap.foodlivery.foodlivery.entities.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(dto.getSenha());
        usuario.setDataUltimaAlteracao(dto.getDataUltimaAlteracao());

        // Ideal: mapear endereco também (se tiver DTO próprio)
        usuario.setEndereco(dto.getEndereco());

        return usuario;
    }

    public Usuario updateDromDTO(UsuarioRequestDTO usuarioDTO, Usuario usuario){

        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setDataUltimaAlteracao(usuarioDTO.getDataUltimaAlteracao());
        usuario.setEndereco(usuarioDTO.getEndereco());
        return usuario;
    }

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(usuario);
    }

}
