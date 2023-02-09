package xyz.tallerback.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.tallerback.model.Usuario;
import xyz.tallerback.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioRest {
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping
	private ResponseEntity<Respuesta> getAllUsuarios() {
		Respuesta resp = new Respuesta(null, usuarioService.findAll());
		return ResponseEntity.ok(resp);
	}
	
	@PostMapping
	private ResponseEntity<Respuesta> saveUsuario(@RequestBody Usuario usuario) {
		try {
			Usuario usuarioGuardado = usuarioService.save(usuario);
			Respuesta resp = new Respuesta(null, usuarioGuardado);
			return ResponseEntity.created(new URI ("/usuario/" + usuarioGuardado.getId())).body(resp);
		}catch (Exception e) {
			// TODO: handle exception
			Respuesta resp = new Respuesta(e.getMessage(), null);
			return ResponseEntity.ok(resp);
		}
		
	}
	
	@PostMapping("/login")
	private ResponseEntity<Respuesta> login (@RequestBody Usuario usuario) {
		try {
			Usuario usuarioConsultado = usuarioService.findAll().stream().filter(x-> x.getUsuario().equals(usuario.getUsuario()) && x.isEstaActivo() ).findFirst().orElse(null);
			if (usuarioConsultado!= null) {
				if (usuarioConsultado.getClave().equals(usuario.getClave())) {
					usuarioConsultado.setClave(null);
					Respuesta resp = new Respuesta(null, usuarioConsultado);
					return ResponseEntity.ok().body(resp);
				} else {
					Respuesta resp = new Respuesta("Credenciales invalidas", null);
					return ResponseEntity.ok(resp);
				}
			} else {
				Respuesta resp = new Respuesta("Credenciales invalidas", null);
				return ResponseEntity.ok(resp);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			Respuesta resp = new Respuesta(e.getMessage(), null);
			return ResponseEntity.ok(resp);
		}
	}

}
