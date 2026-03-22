package br.com.fiap.foodlivery.foodlivery.repositories;

import br.com.fiap.foodlivery.foodlivery.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
