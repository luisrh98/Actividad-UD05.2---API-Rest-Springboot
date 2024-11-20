package com.nominas.empresa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nominas.empresa.models.Nomina;

@Repository
public interface NominaRepository extends JpaRepository<Nomina, String> {
	Nomina findByDni(String dni);
}