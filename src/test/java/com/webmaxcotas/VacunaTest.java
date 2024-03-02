package com.webmaxcotas;

import com.webmaxcotas.model.Vacuna;
import com.webmaxcotas.service.VacunaService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VacunaTest {

    @Autowired
    private VacunaService vacunaService;
    private Vacuna vacunaGuardado;

    @BeforeEach
    void setup(){
        Vacuna vacuna = new Vacuna();
        vacuna.setNombre("Rabia");
        //vacuna.setFechaVencimiento("");
    }


}
