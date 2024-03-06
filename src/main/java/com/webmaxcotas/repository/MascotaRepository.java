package com.webmaxcotas.repository;

import com.webmaxcotas.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MascotaRepository  extends JpaRepository<Mascota, Long> {

    //usamos un Optional
    Optional<Mascota> findByNombre(String nombre);


    //query personalizada de JPQL
    @Query("SELECT m FROM Mascota m ORDER BY LOWER(m.nombre) ASC")
    List<Mascota> findAllByOrderByNombreIgnorecaseAsc();



}
