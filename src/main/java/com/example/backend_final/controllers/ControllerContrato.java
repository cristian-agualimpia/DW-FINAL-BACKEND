package com.example.backend_final.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_final.DTOS.DTOContrato;
import com.example.backend_final.services.ContratoService;

@RestController
@RequestMapping("/contratos")
public class ControllerContrato {

    @Autowired
    private ContratoService contratoService;

    // Crear un nuevo registro
    @PostMapping("/nuevoContrato")
    public ResponseEntity<DTOContrato> crearContrato(@RequestBody DTOContrato DTOContrato) {
        return ResponseEntity.ok(contratoService.crearContrato(DTOContrato));
    }

    // Actualizar un registro existente
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<DTOContrato> actualizarContrato(
            @PathVariable Long id,
            @RequestBody DTOContrato DTOContrato) {
        return contratoService.actualizarContrato(id, DTOContrato)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un registro por su identificador
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> eliminarContrato(@PathVariable Long id) {
        if (contratoService.eliminarContrato(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Consultar todos los registros
    @GetMapping("/todos")
    public ResponseEntity<List<DTOContrato>> obtenerTodosLosContratos() {
        return ResponseEntity.ok(contratoService.obtenerTodosLosContratos());
    }

    // Consultar un registro por su identificador
    @GetMapping("/{id}")
    public ResponseEntity<DTOContrato> obtenerContratoPorId(@PathVariable Long id) {
        return contratoService.obtenerContratoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

