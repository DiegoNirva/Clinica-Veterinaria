package com.webmaxcotas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data // contiene los getters, setters, toString, Hascode, equals
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @NotBlank(message = "la especie no puede estar en blanco")
    private String especie;

    @NotBlank(message = "El sexo no puede estar en blanco")
    private String sexo;

    @NotNull(message = "La fecha de nacimiento no puede estar en blanco")
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "veterinario_id")
    @NotNull(message = "El veterinario no puede estar en blanco")
    private Veterinario veterinario;

    @ManyToMany
    @JoinTable(name = "Mascota_Vacuna",
            joinColumns = @JoinColumn(name = "mascota_id"),
            inverseJoinColumns = @JoinColumn(name = "vacuna_id"))
    private List<Vacuna>vacunasAplicadas = new ArrayList<>();
}
