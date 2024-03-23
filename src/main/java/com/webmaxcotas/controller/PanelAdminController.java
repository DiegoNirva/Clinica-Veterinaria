package com.webmaxcotas.controller;

import com.webmaxcotas.model.Usuario;
import com.webmaxcotas.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PanelAdminController {
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @GetMapping("/gestorRoles")
    public String gestorRoles(Model model){
        model.addAttribute("usuarios", customUserDetailService.findAllUsuario());
        return "gestorRoles";
    }

    @GetMapping("/eliminarUsuarioId/{id}")
    public String deleteUsuarioById(@PathVariable long id){
            customUserDetailService.deleteUsuario(id);
            return "redirect:/gestorRoles";
    }

    @GetMapping("/actualizarRolUsuario/{id}")
    public String viewUpDateUsuario(@PathVariable Long id, Model model){
        Usuario usuario = customUserDetailService.findByIdUsuario(id);
        model.addAttribute("usuario", usuario);
        return "actualizarRolUsuario";
    }

    @PostMapping("/actualizarRolUsuario/{id}")
    public String upDateUsuario(@PathVariable Long id, @RequestParam String rol){
        Usuario usuario = customUserDetailService.findByIdUsuario(id);
        usuario.setRol(rol);
        customUserDetailService.saveUsuario(usuario);
        return "redirect:/gestorRoles";
    }


}
