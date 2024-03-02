package com.webmaxcotas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;
    @NotNull(message = "La fecha de vencimineto no puede estar en blanco")
    private LocalDate fechaVencimiento;

    @ManyToMany (mappedBy = "vacunasAplicadas", fetch = FetchType.EAGER)
    private List<Mascota>mascotas = new ArrayList<>();



}
