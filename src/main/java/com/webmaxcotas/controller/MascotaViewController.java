package com.webmaxcotas.controller;


import com.webmaxcotas.model.Mascota;
import com.webmaxcotas.model.Vacuna;
import com.webmaxcotas.model.Veterinario;
import com.webmaxcotas.service.MascotaService;
import com.webmaxcotas.service.VacunaService;
import com.webmaxcotas.service.VeterinarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class MascotaViewController {

    private  final MascotaService mascotaService;

    private final VeterinarioService veterinarioService;

    private final VacunaService vacunaService;

    @GetMapping("/mascotas")
    public String findAllMascotas(Model model){
        List<Mascota> mascotas = mascotaService.findAllMascota();
        model.addAttribute("mascotas", mascotas);
        return "mascotas";
    }

    @GetMapping("/agregarMascota")
    public String showTheFormMascotas(Model model){
        model.addAttribute("veterinarios", veterinarioService.findAllVeterinario());
        model.addAttribute("vacunas", vacunaService.findAllByVacuna());
        model.addAttribute("mascota", new Mascota());
        return "agregarMascota";
    }

    @PostMapping("guardarMascota")
    public  String saveMascota(@ModelAttribute Mascota mascota, @RequestParam Long idVeterinario, @RequestParam (required = false) List<Long> idVacunas){
        mascotaService.saveMascota(mascota, idVeterinario, idVacunas);
        return "redirect:/mascotas";
    }

    @GetMapping("/actualizarMascota/{id}")
    public String showTheFormUpdateMascota(@PathVariable Long id, Model model){
        Mascota mascota = mascotaService.findByIdMascota(id);
        model.addAttribute("veterinario", veterinarioService.findAllVeterinario());
        model.addAttribute("vacunas", vacunaService.findAllByVacuna());
        model.addAttribute("mascota", new Mascota());

        return "actualizarMascota";
    }

    @PostMapping("/actualizarMascota/{idMascota}")
    public String updateMascota(@RequestParam Long idMascota, @ModelAttribute Mascota mascotaActualizada, @RequestParam Long idVeterinario, @RequestParam(required = false) List<Long> idVacunas){
        mascotaService.updateMascota(idMascota, mascotaActualizada, idVeterinario, idVacunas);
        return "redirect:/mascotas";
    }


}
