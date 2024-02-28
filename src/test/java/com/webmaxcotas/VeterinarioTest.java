package com.webmaxcotas;

import com.webmaxcotas.model.Veterinario;
import com.webmaxcotas.service.VeterinarioService;
import  org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VeterinarioTest {

    @Autowired
    private VeterinarioService veterinarioService;

    private Veterinario veterianioGuardado; 

    @Test
    void testSaveVeterinario(){}
}
