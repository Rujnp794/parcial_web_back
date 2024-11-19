package com.example.parcial_web_back.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.parcial_web_back.modelos.Contrato;


public interface RepositorioContrato extends JpaRepository<Contrato, Long> {

}
