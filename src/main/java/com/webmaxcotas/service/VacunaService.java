package com.webmaxcotas.service;

import com.webmaxcotas.model.Vacuna;
import com.webmaxcotas.repository.VacunaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacunaService {

    @Autowired
    private VacunaRepository vacunaRepository;

    public Vacuna saveVacuna(Vacuna vacuna){
        return vacunaRepository.save(vacuna);
    }

    public List<Vacuna> findAllByVacuna(){
        return vacunaRepository.findAll();
    }

    public Vacuna findByIdVacuna(long id){
        return vacunaRepository.findById(id).orElseThrow(()-> new RuntimeException("no se encontro la vacuna"));
    }

    public void deleteVacuna(long id){
         vacunaRepository.deleteById(id);
    }

}
