package com.dh.clinica.repository.impl;

import com.dh.clinica.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OdontologoRepository extends JpaRepository<Odontologo, Integer> {
/*    @Modifying
    @Query("update Odontologo u set u.nombre = :nombre where u.id = :id")
    void actualizarQuery(@Param(value = "id") Integer id, @Param(value = "nombre") String nombre);*/

}
