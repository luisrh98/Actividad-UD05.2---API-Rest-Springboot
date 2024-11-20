package com.nominas.empresa.services;

import com.nominas.empresa.models.Nomina;
import com.nominas.empresa.repositories.NominaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NominaService {

    @Autowired
    private NominaRepository nominaRepository;

    public Nomina findByDni(String dni) {
        return nominaRepository.findByDni(dni);
    }

    public Nomina save(Nomina nomina) {
        return nominaRepository.save(nomina); // Usa el método del repositorio
    }
}