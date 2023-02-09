package xyz.tallerback.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;




@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
	@Column(length = 36, nullable = false, updatable = false)
	private String id;
	private String nombre;
	private String usuario;
	private String clave;
	private boolean estaActivo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public boolean isEstaActivo() {
		return estaActivo;
	}
	public void setEstaActivo(boolean estaActivo) {
		this.estaActivo = estaActivo;
	}
	
	public Usuario(String nombre, String usuario, String clave, boolean estaActivo) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.clave = clave;
		this.estaActivo = estaActivo;
	}
	public Usuario() {
		super();
	}
	
	
}
