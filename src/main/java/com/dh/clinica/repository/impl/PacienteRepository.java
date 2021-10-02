package com.dh.clinica.repository.impl;

import com.dh.clinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    @Query("from Paciente s where s.dni like %:dni%")
    Set<Paciente> buscarPacientePorDNI(@Param("dni")String dni);
}
