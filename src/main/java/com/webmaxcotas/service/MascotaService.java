package com.webmaxcotas.service;

import com.webmaxcotas.model.Mascota;
import com.webmaxcotas.model.Veterinario;
import com.webmaxcotas.repository.MascotaRepository;
import com.webmaxcotas.repository.VacunaRepository;
import com.webmaxcotas.repository.VeterinarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MascotaService {

    private MascotaRepository mascotaRepository;
    private VeterinarioRepository veterinarioRepository;
    private VacunaRepository vacunaRepository;

    public Mascota findByIdMascota(long id){
        return mascotaRepository.findById(id).orElseThrow(() ->new RuntimeException("no se encontro el veterinario"));
    }

    public List<Mascota> findByMascota(){
        return mascotaRepository.findAllByOrderByNombreIgnorecaseAsc();
    }

    public Mascota saveMascota(Mascota mascota, long idVeterinario, List<Long> idVacunas){

        Veterinario veterinario = veterinarioRepository.findById(idVeterinario).orElseThrow(()-> new RuntimeException("no se encontro el veterionario"));

        mascota.setVeterinario(veterinario);

        if(idVacunas != null){
            mascota.setVacunasAplicadas(vacunaRepository.findAllById(idVacunas));
        }

        return mascotaRepository.save(mascota);
    }


}
