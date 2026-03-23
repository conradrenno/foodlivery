package br.com.fiap.foodlivery.foodlivery.Security;

import br.com.fiap.foodlivery.foodlivery.dtos.LoginRequestDTO;
import br.com.fiap.foodlivery.foodlivery.dtos.UsuarioResponseDTO;
import br.com.fiap.foodlivery.foodlivery.entities.Usuario;
import br.com.fiap.foodlivery.foodlivery.exceptions.custom.BusinessException;
import br.com.fiap.foodlivery.foodlivery.mappers.UsuarioMapper;
import br.com.fiap.foodlivery.foodlivery.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioResponseDTO login(LoginRequestDTO dto) {

        Usuario usuario = usuarioRepository.findByLogin(dto.getLogin())
                .orElseThrow(() -> new BusinessException("Credenciais inválidas"));

        if (!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new BusinessException("Credenciais inválidas");
        }

        UsuarioResponseDTO usuarioResponseDTO = usuarioMapper.toResponseDTO(usuario);
        return usuarioResponseDTO;
    }

}
