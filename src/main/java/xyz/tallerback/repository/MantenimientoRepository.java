package xyz.tallerback.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import xyz.tallerback.model.Mantenimiento;

public interface MantenimientoRepository extends JpaRepository<Mantenimiento, UUID> {

}
