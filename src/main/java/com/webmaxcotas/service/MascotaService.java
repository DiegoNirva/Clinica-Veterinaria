package com.webmaxcotas.service;

import com.webmaxcotas.model.Mascota;
import com.webmaxcotas.model.Veterinario;
import com.webmaxcotas.repository.MascotaRepository;
import com.webmaxcotas.repository.VacunaRepository;
import com.webmaxcotas.repository.VeterinarioRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor

public class MascotaService {

    private MascotaRepository mascotaRepository;
    private VeterinarioRepository veterinarioRepository;
    private VacunaRepository vacunaRepository;

    public Mascota findByIdMascota(long id){
        return mascotaRepository.findById(id).orElseThrow(() ->new RuntimeException("no se encontro el veterinario"));
    }

    public List<Mascota> findAllMascota(){
        return mascotaRepository.findAllByOrderByNombreIgnorecaseAsc();
    }

    public Mascota saveMascota(Mascota mascota, long idVeterinario, List<Long> idVacunas){

        Veterinario veterinario = veterinarioRepository.findById(idVeterinario).orElseThrow(()-> new RuntimeException("no se encontro el veterinario" + idVeterinario +"al momento de guardar" ));

        mascota.setVeterinario(veterinario);

        if(idVacunas != null){
            mascota.setVacunasAplicadas(vacunaRepository.findAllById(idVacunas));
        }

        return mascotaRepository.save(mascota);
    }
    public void deleteMascota(Long id){
        mascotaRepository.deleteById(id);
    }

    public void updateMascota(long idMascota, Mascota mascotaActualizada, long idVeterinario, List<Long> idVacunas){

        Optional<Mascota> mascotaOptional = mascotaRepository.findById(idMascota);

        Veterinario veterinario = veterinarioRepository.findById(idVeterinario).orElseThrow(()-> new RuntimeException("no se encontro el veterinario"+ idVeterinario+" al momento de guardar"));

        if(idVacunas != null){
            mascotaActualizada.setVacunasAplicadas(vacunaRepository.findAllById(idVacunas));
        }

        mascotaActualizada.setVeterinario(veterinario);

        Mascota mascotaExistente = construirMascota(mascotaActualizada, mascotaOptional);
         mascotaRepository.save(mascotaExistente);
    }


    //uso del builder()
    private Mascota construirMascota(Mascota mascotaActualizada, Optional<Mascota> mascotaOptional){

        Mascota.MascotaBuilder mascotaBuilder = Mascota.builder();

        mascotaOptional.ifPresent(mascotaExistente -> {
           mascotaBuilder
                   .id(mascotaExistente.getId())
                   .nombre(mascotaActualizada.getNombre())
                   .especie(mascotaActualizada.getEspecie())
                   .sexo(mascotaActualizada.getSexo())
                   .fechaNacimiento(mascotaActualizada.getFechaNacimiento())
                   .veterinario(mascotaActualizada.getVeterinario())
                   .vacunasAplicadas(mascotaActualizada.getVacunasAplicadas());
        });
        return mascotaBuilder.build();
    }



}
