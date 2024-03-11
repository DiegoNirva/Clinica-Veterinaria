package com.webmaxcotas.controller;

import com.webmaxcotas.model.Veterinario;
import com.webmaxcotas.service.VeterinarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class VeterinarioViewController {

    private final VeterinarioService veterinarioService;

    @GetMapping("/veterinarios")
    public String findAllVeterinario(Model model){
        List<Veterinario> veterinarios =veterinarioService.findAllVeterinario();
        model.addAttribute("veterinarios", veterinarios);
        return "veterinarios";
    }

    @GetMapping("/agregarVeterinario")
    public String showTheFormSaveVeterinario(Model model){
        model.addAttribute("veterinario", new Veterinario());
        return "agregarVeterinario";
    }

    @PostMapping("/guardarVeterinario")
    public String saveVeterinario(@ModelAttribute Veterinario veterinario){
        veterinarioService.saveVeterinario(veterinario);
        return "redirect:/veterinarios";
    }
}
