package com.webmaxcotas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    private String nombre;
    private LocalDate fechaVencimiento;

    @ManyToMany (mappedBy = "vacunasAplicadas", fetch = FetchType.EAGER)
    private List<Mascota>mascotas = new ArrayList<>();



}
