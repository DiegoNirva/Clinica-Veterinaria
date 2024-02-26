package com.webmaxcotas.repository;

import com.webmaxcotas.model.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacunaResository extends JpaRepository<Vacuna, Long> {
}
