package com.nominas.empresa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nominas.empresa.models.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
	Empleado findByDni(String dni);
}
