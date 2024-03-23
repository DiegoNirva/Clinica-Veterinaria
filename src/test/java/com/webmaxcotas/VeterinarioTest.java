package com.webmaxcotas;

import com.webmaxcotas.model.Veterinario;
import com.webmaxcotas.service.VeterinarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
 class VeterinarioTest {
    @Autowired
    private  VeterinarioService veterinarioService;
    private Veterinario veterinarioGuardado;

    @BeforeEach
    void setup (){
        Veterinario  veterinario= new Veterinario();
        veterinario.setNombre("Diego lopez");
        veterinario.setMatricula("7886MP");
        veterinario.setEmail("diegolp@test.com");

        veterinarioGuardado = veterinarioService.saveVeterinario(veterinario);
    }
    @Test
    void testSaveVeterinario(){
        assertNotNull(veterinarioGuardado.getId());
        assertEquals("Diego lopez", veterinarioGuardado.getNombre());
        assertEquals("7886MP", veterinarioGuardado.getMatricula());
        assertEquals("diegolp@test.com", veterinarioGuardado.getEmail());
    }

    @Test
    void testFindAllVeterionario(){
         List<Veterinario> veterinario = veterinarioService.findAllVeterinario();
         assertFalse(veterinario.isEmpty());
    }

    @Test
    void testFindByIdVeterinario(){
        Long idVeterinario = 1L;
        Veterinario veterinario = veterinarioService.findByIdVeterinario(idVeterinario);

        assertNotNull(veterinario);
        assertEquals(idVeterinario, veterinario.getId());

    }



}
