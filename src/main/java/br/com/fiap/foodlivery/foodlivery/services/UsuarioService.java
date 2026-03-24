package br.com.fiap.foodlivery.foodlivery.services;

import br.com.fiap.foodlivery.foodlivery.dtos.UpdatePasswordDTO;
import br.com.fiap.foodlivery.foodlivery.dtos.UsuarioRequestDTO;
import br.com.fiap.foodlivery.foodlivery.dtos.UsuarioResponseDTO;
import br.com.fiap.foodlivery.foodlivery.entities.Usuario;
import br.com.fiap.foodlivery.foodlivery.exceptions.custom.BusinessException;
import br.com.fiap.foodlivery.foodlivery.exceptions.custom.DatabaseException;
import br.com.fiap.foodlivery.foodlivery.exceptions.custom.ResourceNotFoundException;
import br.com.fiap.foodlivery.foodlivery.mappers.UsuarioMapper;
import br.com.fiap.foodlivery.foodlivery.repositories.UsuarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<UsuarioResponseDTO> findAll(Pageable pageable) {
        return usuarioRepository.findAll(pageable)
                .map(usuarioMapper::toResponseDTO);
    }

    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        return usuarioMapper.toResponseDTO(usuario);
    }

    public UsuarioResponseDTO save(UsuarioRequestDTO usuarioRequestDTO) {

        if (usuarioRepository.existsByLogin(usuarioRequestDTO.getLogin())) {
            throw new BusinessException("Login já cadastrado");
        }
        Usuario usuario = usuarioMapper.toEntity(usuarioRequestDTO);
        usuario.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toResponseDTO(usuario);
    }

    public UsuarioResponseDTO update(Long id, UsuarioRequestDTO usuarioRequestDTO) {

        Usuario existingUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        if (usuarioRequestDTO.getLogin() != null &&
                !usuarioRequestDTO.getLogin().equals(existingUsuario.getLogin()) &&
                usuarioRepository.existsByLogin(usuarioRequestDTO.getLogin())){
                throw new BusinessException("Login já cadastrado");
        }

            existingUsuario = usuarioMapper.updateFromDTO(usuarioRequestDTO, existingUsuario);

            if (usuarioRequestDTO.getSenha() != null &&
                    !usuarioRequestDTO.getSenha().isBlank()) {
                existingUsuario.setSenha(passwordEncoder.encode(usuarioRequestDTO.getSenha()));
            }

            try {
            existingUsuario = usuarioRepository.save(existingUsuario);
            } catch (DataIntegrityViolationException e) {
                throw new DatabaseException("Falha de integridade referencial");
            }
            UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.toResponseDTO(existingUsuario);
            return usuarioResponseDTO;
    }

    public void updatePassword(Long id, UpdatePasswordDTO updatePasswordDTO) {

        String senhaAtual = updatePasswordDTO.getSenhaAtual();
        String novaSenha = updatePasswordDTO.getSenhaNova();
        String confirmarSenha = updatePasswordDTO.getConfirmarSenha();

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new BusinessException("Senha atual inválida");
        }

        if (!updatePasswordDTO.getSenhaNova().equals(confirmarSenha)) {
            throw new BusinessException("As senhas não coincidem");
        }

        if (passwordEncoder.matches(novaSenha, usuario.getSenha())) {
            throw new BusinessException("A nova senha deve ser diferente da atual");
        }

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);
    }

    public void delete(Long id) {

        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            usuarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

}
