package com.webmaxcotas;

import com.webmaxcotas.model.Mascota;
import com.webmaxcotas.model.Vacuna;
import com.webmaxcotas.model.Veterinario;
import com.webmaxcotas.service.MascotaService;
import com.webmaxcotas.service.VacunaService;
import com.webmaxcotas.service.VeterinarioService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MascotaTest {

    @Autowired
    private MascotaService mascotaService;
    @Autowired
    private VeterinarioService veterinarioService;
    @Autowired
    private VacunaService vacunaService;
    private Mascota mascotaBasica;
    private Veterinario veterinarioGuardado;
    private Vacuna vacunaGuardado;

    @BeforeEach()
    void setup(){

        Veterinario veterinario = new Veterinario();
        veterinario.setNombre("Diego");
        veterinario.setMatricula("1234MP");
        veterinario.setEmail("diego@live.com");
        veterinarioGuardado = veterinarioService.saveVeterinario(veterinario);

        Vacuna vacuna = new Vacuna();
        vacuna.setNombre("rabia");
        vacuna.setFechaVencimiento(LocalDate.now().plusDays(30L));
        vacunaGuardado = vacunaService.saveVacuna(vacuna);

        mascotaBasica = new Mascota();
        mascotaBasica.setNombre("bati");
        mascotaBasica.setEspecie("caniche");
        mascotaBasica.setSexo("hembra");
        mascotaBasica.setFechaNacimiento(LocalDate.now());

    }
    @Test
    void saveMascotaNullVacunas(){
        mascotaBasica.setVeterinario(veterinarioGuardado);
        Mascota mascotaGuardado = mascotaService.saveMascota(mascotaBasica, veterinarioGuardado.getId(), null);

        assertNotNull(mascotaGuardado.getId());
        assertEquals("bati", mascotaGuardado.getNombre());
        assertEquals("caniche", mascotaGuardado.getEspecie());
        assertEquals(veterinarioGuardado.getId(), mascotaGuardado.getVeterinario().getId());
    }

    @Test
    void saveMascotaComplete(){
        mascotaBasica.setVeterinario(veterinarioGuardado);
        List<Long> idsVacunasAplicadas = new ArrayList<>();
        idsVacunasAplicadas.add(vacunaGuardado.getId());
        Mascota mascotaGuardado = mascotaService.saveMascota(mascotaBasica, veterinarioGuardado.getId(), idsVacunasAplicadas);

        assertNotNull(mascotaGuardado.getId());
        assertEquals("bati", mascotaGuardado.getNombre());
        assertEquals("caniche", mascotaGuardado.getEspecie());
        assertEquals(veterinarioGuardado.getId(), mascotaGuardado.getVeterinario().getId());
        assertFalse(mascotaGuardado.getVacunasAplicadas().isEmpty());
    }

    @Test
    void findAllMascota(){
        List<Mascota> mascotas = mascotaService.findAllMascota();

        assertFalse(mascotas.isEmpty());
    }
    @Test
    void findMascotaById(){
        Long idMascota = 1l;
        Mascota mascota = mascotaService.findByIdMascota(idMascota);

        assertNotNull(mascota);
        assertEquals(idMascota, mascota.getId());

    }
    @Test
    void testActualizarMascotaSinVacunas() {
        Long mascotaId = 2l;

        Mascota mascotaActualizada = new Mascota();
        mascotaActualizada.setNombre("firulais");
        mascotaActualizada.setEspecie("perro");
        mascotaActualizada.setSexo("macho");
        mascotaActualizada.setFechaNacimiento(LocalDate.now());
        mascotaActualizada.setVeterinario(veterinarioGuardado);

        mascotaService.updateMascota(mascotaId, mascotaActualizada, veterinarioGuardado.getId() ,null);

        Mascota mascotaDespuesDeActualizar = mascotaService.findByIdMascota(mascotaId);

        assertEquals(mascotaActualizada.getNombre(), mascotaDespuesDeActualizar.getNombre());
        assertEquals(mascotaActualizada.getEspecie(), mascotaDespuesDeActualizar.getEspecie());
        assertEquals(mascotaActualizada.getVeterinario().getId(), mascotaDespuesDeActualizar.getVeterinario().getId());
    }

    @Test
    void deleteMascota(){
        Long idMascota = 1l;

        //buscamos si la mascota existe
        assertNotNull(mascotaService.findByIdMascota(idMascota));
        mascotaService.deleteMascota(idMascota);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            mascotaService.findByIdMascota(idMascota);
        });

        String expectedMessage = "No se encontró la mascota: " + idMascota;
        String actualMessage = exception.getMessage();

        //Corroboramos que efectivamente fue eliminada
        assertFalse(actualMessage.contains(expectedMessage));
    }

}
