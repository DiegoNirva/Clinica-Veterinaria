package com.webmaxcotas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veterinario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @NotBlank(message = "La matricula no puede estar en blanco")
    private String matricula;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Ingrese un email valido")
    private String email;

    @OneToMany (mappedBy = "veterinario", fetch = FetchType.EAGER)
    private List<Mascota> mascotaAtendidas;

}
