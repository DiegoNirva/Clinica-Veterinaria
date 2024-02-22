package com.webmaxcotas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data // contiene los getters, setters, toString, Hascode, equals
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    private String nombre;
    private String especie;
    private String sexo;
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    @ManyToMany
    @JoinTable(name = "Mascota_Vacuna",
            joinColumns = @JoinColumn(name = "mascota_id"),
            inverseJoinColumns = @JoinColumn(name = "vacuna_id"))
    private List<Vacuna>vacunasAplicadas = new ArrayList<>();
}
