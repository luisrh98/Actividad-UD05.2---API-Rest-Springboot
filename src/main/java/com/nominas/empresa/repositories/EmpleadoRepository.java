package com.nominas.empresa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nominas.empresa.models.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, String> {
	Empleado findByDni(String dni);
	
	@Query("SELECT e FROM Empleado e " + 
		       "WHERE (:nombre IS NULL OR e.nombre LIKE :nombre%) " + 
		       "AND (:dni IS NULL OR e.dni LIKE :dni%) " + 
		       "AND (:sexo IS NULL OR e.sexo = :sexo) " + 
		       "AND (:categoria IS NULL OR e.categoria = :categoria) " + 
		       "AND (:anyos IS NULL OR e.anyos = :anyos)")
		List<Empleado> listarEmpleadosPorCualquierCampo(@Param("nombre") String nombre, 
		                                               @Param("dni") String dni, 
		                                               @Param("sexo") String sexo,
		                                               @Param("categoria") Integer categoria, 
		                                               @Param("anyos") Integer anyos);
}
