package com.example.parcial_web_back.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.parcial_web_back.dtos.DTOContrato;
import com.example.parcial_web_back.servicios.ServicioContrato;

@RestController 
@RequestMapping("/api/contratos")
public class ControladorContrato {

    private final ServicioContrato servicioContrato;

    public ControladorContrato(ServicioContrato servicioContrato) {
        this.servicioContrato = servicioContrato;
    }

    @PostMapping
    public DTOContrato crearContrato(@RequestBody DTOContrato dtoContrato) {
        return servicioContrato.crearContrato(dtoContrato);
    }

    @GetMapping
    public List<DTOContrato> obtenerTodosContratos() {
        return servicioContrato.obtenerTodosContratos();
    }

    @GetMapping("/{id}")
    public DTOContrato obtenerContratoPorId(@PathVariable Long id) {
        return servicioContrato.obtenerContratoPorId(id);
    }

    @PutMapping("/{id}")
    public DTOContrato actualizarContrato(@PathVariable Long id, @RequestBody DTOContrato dtoContrato) {
        return servicioContrato.actualizarContrato(id, dtoContrato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarContrato(@PathVariable Long id) {
        servicioContrato.eliminarContrato(id);
        return ResponseEntity.noContent().build();
    }

}
