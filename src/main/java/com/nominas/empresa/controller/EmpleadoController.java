package com.nominas.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nominas.empresa.models.Empleado;
import com.nominas.empresa.models.Nomina;
import com.nominas.empresa.services.EmpleadoService;
import com.nominas.empresa.services.NominaService;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;
    
    @Autowired
    private NominaService nominaService;

    @GetMapping
    public List<Empleado> listarEmpleados() {
        return empleadoService.listarEmpleados();
    }

    @GetMapping("/{dni}")
    public Nomina obtenerSalario(@PathVariable String dni) {
        return nominaService.findByDni(dni);
    }

    @PostMapping
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        return empleadoService.crearEmpleado(empleado);
    }

    @PutMapping("/{dni}")
    public Empleado actualizarEmpleado(@PathVariable String dni, @RequestBody Empleado empleado) {
        return empleadoService.actualizarEmpleado(dni, empleado);
    }

    @DeleteMapping("/{dni}")
    public void eliminarEmpleado(@PathVariable String dni) {
        empleadoService.eliminarEmpleado(dni);
    }
}
