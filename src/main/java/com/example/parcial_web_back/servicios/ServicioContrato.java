package com.example.parcial_web_back.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.parcial_web_back.dtos.DTOContrato;
import com.example.parcial_web_back.modelos.Contrato;
import com.example.parcial_web_back.repositorios.RepositorioContrato;

@Service
public class ServicioContrato {

    private final RepositorioContrato repositorioContrato;
    private final ModelMapper modelMapper;

    public ServicioContrato(RepositorioContrato repositorioContrato, ModelMapper modelMapper) {
        this.repositorioContrato = repositorioContrato;
        this.modelMapper = modelMapper;
    }

    public DTOContrato crearContrato(DTOContrato dtoContrato) {

        if(dtoContrato.getFechaInicial().isAfter(dtoContrato.getFechaFinal())) {
            throw new IllegalArgumentException("La fecha inicial no puede ser posterior a la fecha final");
        }
        Contrato contrato = modelMapper.map(dtoContrato, Contrato.class);
        Contrato contratoGuardado = repositorioContrato.save(contrato);
        return modelMapper.map(contratoGuardado, DTOContrato.class);
    }

    public List<DTOContrato> obtenerTodosContratos() {
        return repositorioContrato.findAll()
                .stream()
                .map(contrato -> modelMapper.map(contrato, DTOContrato.class))
                .collect(Collectors.toList());
    }

    public DTOContrato obtenerContratoPorId(Long id) {
        Contrato contrato = repositorioContrato.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato con ID " + id + " no encontrado"));
    
        return modelMapper.map(contrato, DTOContrato.class);
    }

    public DTOContrato actualizarContrato(Long id, DTOContrato dtoContrato) {
        Contrato contrato = modelMapper.map(dtoContrato, Contrato.class);
        contrato.setId(id); // Asegúrate de que el ID se mantenga para actualizar el registro existente.
        Contrato contratoActualizado = repositorioContrato.save(contrato);
        return modelMapper.map(contratoActualizado, DTOContrato.class);
    }

    public void eliminarContrato(Long id) {
        repositorioContrato.deleteById(id);
    }
}
