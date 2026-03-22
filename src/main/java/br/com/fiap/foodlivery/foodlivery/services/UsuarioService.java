package br.com.fiap.foodlivery.foodlivery.services;

import br.com.fiap.foodlivery.foodlivery.dtos.UsuarioRequestDTO;
import br.com.fiap.foodlivery.foodlivery.dtos.UsuarioResponseDTO;
import br.com.fiap.foodlivery.foodlivery.entities.Usuario;
import br.com.fiap.foodlivery.foodlivery.mappers.UsuarioMapper;
import br.com.fiap.foodlivery.foodlivery.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public Page<UsuarioResponseDTO> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable)
                .map(usuarioMapper::toResponseDTO);
    }

    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        return usuarioMapper.toResponseDTO(usuario);
    }

    public UsuarioResponseDTO save(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioRequestDTO);
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(usuario);
    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        existingUsuario = usuarioMapper.updateDromDTO(usuarioRequestDTO, existingUsuario);
        existingUsuario = usuarioRepository.save(existingUsuario);
        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.toResponseDTO(existingUsuario);
        return usuarioResponseDTO;
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

}
