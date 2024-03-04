package com.webmaxcotas;

import com.webmaxcotas.model.Vacuna;
import com.webmaxcotas.service.VacunaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class VacunaTest {

    @Autowired
    private VacunaService vacunaService;
    private Vacuna vacunaGuardado;

    @BeforeEach
    void setup(){
        Vacuna vacuna = new Vacuna();
        vacuna.setNombre("Rabia");
        vacuna.setFechaVencimiento(LocalDate.now().plusDays(30L));

        vacunaGuardado = vacunaService.saveVacuna(vacuna);
    }

    @Test
    void saveVacuna(){
        assertNotNull(vacunaGuardado.getId());
        assertEquals("Rabia", vacunaGuardado.getNombre());
        assertEquals(LocalDate.now().plusDays(30L), vacunaGuardado.getFechaVencimiento());
    }

    @Test
    void findAllVacunas(){
        List<Vacuna> vacuna = vacunaService.findAllByVacuna();
        assertFalse(vacuna.isEmpty());
    }

    @Test
    void findByiDVacunas(){
        long idVacuna = vacunaGuardado.getId();
        Vacuna vacuna = vacunaService.findByIdVacuna(idVacuna);

        assertNotNull(vacuna);
        assertEquals(idVacuna, vacuna.getId());
    }

    @Test
    void deleteVacuna(){

        Long idVacuna = 1L;

        //verificamos si exite
        assertNotNull(vacunaService.findByIdVacuna(idVacuna));

        vacunaService.deleteVacuna(idVacuna);

    }
}

