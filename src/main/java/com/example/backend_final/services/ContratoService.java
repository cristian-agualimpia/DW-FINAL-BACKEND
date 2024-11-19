package com.example.backend_final.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_final.DTOS.DTOContrato;
import com.example.backend_final.entities.EntidadContrato;
import com.example.backend_final.repositories.RepositoryContrato;

@Service
public class ContratoService {

    @Autowired
    private RepositoryContrato repositoryContrato;

    @Autowired
    private ModelMapper modelMapper;

    // Crear un nuevo contrato
    public DTOContrato crearContrato(DTOContrato DTOContrato) {
        EntidadContrato contrato = modelMapper.map(DTOContrato, EntidadContrato.class);
        EntidadContrato savedContrato = repositoryContrato.save(contrato);
        return modelMapper.map(savedContrato, DTOContrato.class);
    }

    // Actualizar un contrato existente
    public Optional<DTOContrato> actualizarContrato(Long id, DTOContrato DTOContrato) {
        return repositoryContrato.findById(id).map(contrato -> {
            contrato.setIdentificador(DTOContrato.getIdentificador());
            contrato.setValor(DTOContrato.getValor());
            contrato.setNombreContratante(DTOContrato.getNombreContratante());
            contrato.setNombreContratantista(DTOContrato.getNombreContratantista());
            contrato.setFechaInicial(DTOContrato.getFechaInicial());
            contrato.setFechaFinal(DTOContrato.getFechaFinal());
            EntidadContrato updatedContrato = repositoryContrato.save(contrato);
            return modelMapper.map(updatedContrato, DTOContrato.class);
        });
    }

    // Eliminar un contrato
    public boolean eliminarContrato(Long id) {
        if (repositoryContrato.existsById(id)) {
            repositoryContrato.deleteById(id);
            return true;
        }
        return false;
    }

    // Obtener todos los contratos
    public List<DTOContrato> obtenerTodosLosContratos() {
        List<EntidadContrato> contratos = repositoryContrato.findAll();
        return contratos.stream()
                .map(contrato -> modelMapper.map(contrato, DTOContrato.class))
                .collect(Collectors.toList());
    }

    // Obtener un contrato por ID
    public Optional<DTOContrato> obtenerContratoPorId(Long id) {
        return repositoryContrato.findById(id)
                .map(contrato -> modelMapper.map(contrato, DTOContrato.class));
    }
}
