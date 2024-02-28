package com.webmaxcotas.repository;

import com.webmaxcotas.model.Vacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacunaRepository extends JpaRepository<Vacuna, Long> {
}
