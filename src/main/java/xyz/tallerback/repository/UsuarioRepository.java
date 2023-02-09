package xyz.tallerback.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.tallerback.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

}
