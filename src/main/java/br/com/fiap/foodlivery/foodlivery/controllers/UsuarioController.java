package br.com.fiap.foodlivery.foodlivery.controllers;

import br.com.fiap.foodlivery.foodlivery.dtos.UpdatePasswordDTO;
import br.com.fiap.foodlivery.foodlivery.dtos.UsuarioRequestDTO;
import br.com.fiap.foodlivery.foodlivery.dtos.UsuarioResponseDTO;
import br.com.fiap.foodlivery.foodlivery.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDTO>> findAll(Pageable pageable) {
        Page<UsuarioResponseDTO> usuarios = usuarioService.findAll(pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> findById(@PathVariable Long id) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.findById(id);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> save(@Valid @RequestBody UsuarioRequestDTO usuario) {
        UsuarioResponseDTO savedUsuario = usuarioService.save(usuario);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUsuario.getId()).toUri();

        return ResponseEntity.created(uri).body(savedUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(@PathVariable Long id,@Valid @RequestBody UsuarioRequestDTO usuarioDTO) {
        UsuarioResponseDTO updatedUsuarioDTO = usuarioService.update(id, usuarioDTO);
        return ResponseEntity.ok(updatedUsuarioDTO);
    }

    @PostMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UpdatePasswordDTO updatePasswordDTO) {
        usuarioService.updatePassword(id, updatePasswordDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
