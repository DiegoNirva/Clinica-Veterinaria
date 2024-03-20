package com.webmaxcotas.controller;

import com.webmaxcotas.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.webmaxcotas.model.Usuario;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AutoController {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @GetMapping("/")
    public String redireccionarPaginaPrincipal(){
    return "redirect:/mascotas";
    }

    @GetMapping("/login")
    public String loginUsuario(){
        return "login";
    }

    @GetMapping("/registro")
    public String SingUp(Model model){
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    @PostMapping("/registro")
    public String saveUsuario(Usuario usuario){
        customUserDetailService.saveUsuario(usuario);
        return "redirect:/login";
    }

    @GetMapping("/gestorRoles")
    public String viewRoles(Model model){
        model.addAttribute("usuario", customUserDetailService.findAllUsuario());
        return "redirect:/roles";
    }

}
