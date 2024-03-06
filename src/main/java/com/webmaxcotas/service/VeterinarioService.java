package com.webmaxcotas.service;

import com.webmaxcotas.model.Veterinario;
import com.webmaxcotas.repository.VeterinarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VeterinarioService {

    private VeterinarioRepository veterinarioRepository;

    //insertamos las operaciones basicas para un CRUD.

    public Veterinario saveVeterinario(Veterinario veterinario){
        return veterinarioRepository.save(veterinario);
    }

    //traer todos los veterinarios
    public List<Veterinario> findAllVeterinario(){
       return veterinarioRepository.findAll();
    }

    //buscar por id
    public Veterinario findByIdVeterinario(long id){
        return veterinarioRepository.findById(id).orElseThrow(() ->new RuntimeException("no se encontro el veterinario"));
    }


    //eliminar
    public void deleteByIdVeterinario(long id){
        veterinarioRepository.deleteById(id);
    }
}
