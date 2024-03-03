package com.webmaxcotas.controller;

import com.webmaxcotas.model.Vacuna;
import com.webmaxcotas.service.VacunaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class VacunaViewController {
    @Autowired
    private  VacunaService vacunaService;

    private  Vacuna vacuna;

    @GetMapping("/vacunas")
        public String findAllVacuna(Model model){
        List<Vacuna> vacunas = vacunaService.findAllByVacuna();
        model.addAttribute("vacunas", vacunas);
        return "/vacunas";
        }

    @GetMapping("/agregarVacuna")
        public String showTheFromVacuna(Model model){
        model.addAttribute("vacuna", new Vacuna());
        return "/agregarVacuna";
    }
    @PostMapping("/guardarVacuna")
        public String saveVacuna(@ModelAttribute Vacuna vacuna){
        vacunaService.saveVacuna(vacuna);
        return ("redirect:/vacunas");
    }



    }


