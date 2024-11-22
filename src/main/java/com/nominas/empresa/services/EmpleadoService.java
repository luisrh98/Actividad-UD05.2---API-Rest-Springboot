package com.nominas.empresa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nominas.empresa.models.Empleado;
import com.nominas.empresa.models.Nomina;
import com.nominas.empresa.repositories.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    @Autowired
    private NominaService nominaService;

    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }
    
    public List<Empleado> listarEmpleadosPorCualquierCampo(String nombre, String dni, String sexo, Integer categoria, Integer anyos) {
        // Convertir parámetros vacíos en null
        nombre = (nombre != null && nombre.trim().isEmpty()) ? null : nombre;
        dni = (dni != null && dni.trim().isEmpty()) ? null : dni;
        sexo = (sexo != null && sexo.trim().isEmpty()) ? null : sexo;

        // Llamar al repositorio con los parámetros convertidos
        return empleadoRepository.listarEmpleadosPorCualquierCampo(nombre, dni, sexo, categoria, anyos);
    }

    public Empleado obtenerEmpleado(String dni) {
        return empleadoRepository.findByDni(dni);
    }

    public Empleado crearEmpleado(Empleado empleado) {
    	
    	empleadoRepository.save(empleado);
    	
    	// Buscar la nómina asociada al empleado
        Nomina nomina = nominaService.findByDni(empleado.getDni());

        if (nomina == null) {
            // Si no existe una nómina asociada, crear una nueva
            nomina = new Nomina();
            nomina.setDni(empleado.getDni());
        }

        // Calcular y actualizar el sueldo basado en la nueva información del empleado
        Integer sueldo = Nomina.sueldo(empleado);
        nomina.setSueldo(sueldo);

        // Guardar la nómina actualizada
        nominaService.save(nomina);
    	
    	
    	
        return empleado;
    }

    public Empleado actualizarEmpleado(String dni, Empleado empleado) {
        // Buscar al empleado existente por su DNI
        Empleado existente = empleadoRepository.findByDni(dni);

        if (existente == null) {
            throw new RuntimeException("Empleado con DNI " + dni + " no encontrado.");
        }

        // Actualizar los campos del empleado existente
        existente.setNombre(empleado.getNombre());
        existente.setSexo(empleado.getSexo());
        existente.setCategoria(empleado.getCategoria());
        existente.setAnyos(empleado.getAnyos());

        // Guardar los cambios en el repositorio de empleados
        empleadoRepository.save(existente);

        // Buscar la nómina asociada al empleado
        Nomina nomina = nominaService.findByDni(dni);

        if (nomina == null) {
            // Si no existe una nómina asociada, crear una nueva
            nomina = new Nomina();
            nomina.setDni(dni);
        }

        // Calcular y actualizar el sueldo basado en la nueva información del empleado
        Integer sueldo = Nomina.sueldo(existente);
        nomina.setSueldo(sueldo);

        // Guardar la nómina actualizada
        nominaService.save(nomina);

        return existente; // Devolver el empleado actualizado
    }

    public void eliminarEmpleado(String dni) {
        Empleado existente = empleadoRepository.findByDni(dni);
        empleadoRepository.delete(existente);
    }
}
