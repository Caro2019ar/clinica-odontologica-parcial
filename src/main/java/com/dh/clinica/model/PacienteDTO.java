package com.dh.clinica.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class PacienteDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    @CreationTimestamp
    private Date fechaIngreso;
    private Domicilio domicilio;

    public PacienteDTO(String nombre, String apellido, String dni, Date fechaIngreso, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilio = domicilio;
    }

    public PacienteDTO(String nombre, String apellido, String dni, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
    }
}
