package com.example.backend_final.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend_final.entities.EntidadContrato;

public interface RepositoryContrato extends JpaRepository<EntidadContrato, Long> {
    
    // Método para buscar por ID (opcional, ya que JpaRepository lo incluye por defecto)
    Optional<EntidadContrato> findById(Long id);

    // Método para verificar si existe un contrato por un usuario (debe coincidir con un campo en la entidad)
    boolean existsByIdentificador(String identificador);
}
