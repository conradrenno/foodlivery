package br.com.fiap.foodlivery.foodlivery.Security;

import br.com.fiap.foodlivery.foodlivery.dtos.LoginRequestDTO;
import br.com.fiap.foodlivery.foodlivery.dtos.UsuarioResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        UsuarioResponseDTO usuarioResponseDTO = authService.login(dto);
        return ResponseEntity.ok(usuarioResponseDTO);
    }
}
