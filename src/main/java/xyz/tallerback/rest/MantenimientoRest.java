package xyz.tallerback.rest;

import java.net.URI;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.tallerback.model.Mantenimiento;
import xyz.tallerback.model.Usuario;
import xyz.tallerback.service.MantenimientoService;
import xyz.tallerback.service.UsuarioService;

@RestController
@RequestMapping("/mantenimiento")
public class MantenimientoRest {
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private MantenimientoService mantenimientoService;
	
	@GetMapping
	private ResponseEntity<Respuesta> getAllMantenimientos() {
		Respuesta resp = new Respuesta(null, mantenimientoService.findAll());
		return ResponseEntity.ok(resp);
	}
	
	@PostMapping
	private ResponseEntity<Respuesta> saveMantenimiento(@RequestBody Mantenimiento mantenimiento) {
		try {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime mantenimientoFecha = LocalDateTime.parse(mantenimiento.getFechaMantenimiento().toString());
			if (now.isBefore(mantenimientoFecha)) {
				Mantenimiento mantenimientoRepetido = mantenimientoService.findAll().
						stream().
						filter(x -> x.getPlaca().equals(mantenimiento.getPlaca()) || x.getFechaMantenimiento().isEqual(mantenimientoFecha)).
						findFirst().
						orElse(null);
				if(mantenimientoRepetido != null) {
					Respuesta resp = new Respuesta("Placa repetida o Fecha repetida", null);
					return ResponseEntity.ok(resp);
				}else {
					Mantenimiento mantenimientoGuardado = mantenimientoService.save(mantenimiento);
					Respuesta resp = new Respuesta(null, mantenimientoGuardado);
					return ResponseEntity.created(new URI ("/mantenimiento/" + mantenimientoGuardado.getId())).body(resp);
				}
			}else {
				Respuesta resp = new Respuesta("Fecha invalida", null);
				return ResponseEntity.ok(resp);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			Respuesta resp = new Respuesta(e.getMessage(), null);
			return ResponseEntity.ok(resp);
		}	
	}
	
	@PostMapping("/buscar")
	private ResponseEntity<Respuesta> searchMantenimiento(@RequestBody Mantenimiento mantenimiento) {
		try {
			Mantenimiento mantenimientoConsultado = mantenimientoService.
					findAll().
					stream().
					filter(x -> x.getPlaca().equals(mantenimiento.getPlaca())).
					findFirst().
					orElse(null);
			if (mantenimientoConsultado != null) {
				Respuesta resp = new Respuesta(null, mantenimientoConsultado);
				return ResponseEntity.ok(resp);
			} else {
				Respuesta resp = new Respuesta("No se encontro esta placa", null);
				return ResponseEntity.ok(resp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			Respuesta resp = new Respuesta(e.getMessage(), null);
			return ResponseEntity.ok(resp);
		}	
	}
	
	@PostMapping("/cambiarEstado")
	private ResponseEntity<Respuesta> cambiarEstadoMantenimiento(@RequestBody Mantenimiento mantenimiento) {
		try {
			Mantenimiento mantenimientoConsultado = mantenimientoService.
					findAll().
					stream().
					filter(x -> x.getId().equals(mantenimiento.getId())).
					findFirst().
					orElse(null);
			if (mantenimientoConsultado != null) {
				mantenimientoConsultado.setEstado("ATENDIDA");
				mantenimientoService.save(mantenimientoConsultado);
				Respuesta resp = new Respuesta(null, mantenimientoConsultado);
				return ResponseEntity.ok(resp);
			} else {
				Respuesta resp = new Respuesta("No se encontro esta placa", null);
				return ResponseEntity.ok(resp);
			}
		}catch (Exception e) {
			// TODO: handle exception
			Respuesta resp = new Respuesta(e.getMessage(), null);
			return ResponseEntity.ok(resp);
		}	
	}
}
