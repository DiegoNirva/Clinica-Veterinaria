package com.webmaxcotas;

import com.webmaxcotas.model.Usuario;
import com.webmaxcotas.service.CustomUserDetailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomUserTest {

    @Autowired
    private CustomUserDetailService customUserDetailService;
    private Usuario usuarioGuardado;

    @BeforeEach
    void setup(){
        Usuario usuario = new Usuario();
        usuario.setUsername("admin");
        usuario.setContrasenia("1234");
        usuario.setRol("ROL_ADMIN");
        usuario.setNombre("diego");
        usuario.setApellido("narvaez");

        usuarioGuardado = customUserDetailService.saveUsuario(usuario);
    }

    @Test
    void testSaveUsuario(){
        assertNotNull(usuarioGuardado.getId());
        assertEquals("admin", usuarioGuardado.getUsername());
        assertEquals("ROL_ADMIN", usuarioGuardado.getRol());
        assertEquals("diego", usuarioGuardado.getNombre());
        assertEquals("narvaez", usuarioGuardado.getApellido());
    }

    @Test
    void testFindByUsuario(){
        usuarioGuardado = customUserDetailService.findByIdUsuario(1L);

        assertNotNull(usuarioGuardado);
    }

    @Test
    void testFindByAllUsuario(){
        List<Usuario> usuarios = customUserDetailService.findAllUsuario();

        assertFalse(usuarios.isEmpty());
    }

    @Test
    void testUpDateUsuario(){
        Usuario usuarioActualizado = customUserDetailService.findByIdUsuario(1L);
        usuarioActualizado.setRol("ROL_LECTURA");
        customUserDetailService.saveUsuario(usuarioActualizado);
        assertNotNull(usuarioActualizado);
        assertEquals("ROL_LECTURA", usuarioActualizado.getRol());
    }

}
