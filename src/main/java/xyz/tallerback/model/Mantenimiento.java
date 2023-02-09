package xyz.tallerback.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;





@Entity
@Table(name = "mantenimientos")
public class Mantenimiento {
	
	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
	@Column(length = 36, nullable = false, updatable = false)
	private String id;
	private String placa;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime fechaMantenimiento;
	@Column(name = "observacion", length = 2048)
	private String observacion;
	private String estado;
	private String cliente;
	private String telefono;
	private String tipoVehiculo;
	
	public String getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public LocalDateTime getFechaMantenimiento() {
		return fechaMantenimiento;
	}
	public void setFechaMantenimiento(LocalDateTime fechaMantenimiento) {
		this.fechaMantenimiento = fechaMantenimiento;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	public Mantenimiento(String placa, LocalDateTime fechaMantenimiento, String observacion, String estado,
			String cliente, String telefono, String tipoVehiculo) {
		super();
		this.placa = placa;
		this.fechaMantenimiento = fechaMantenimiento;
		this.observacion = observacion;
		this.estado = estado;
		this.cliente = cliente;
		this.telefono = telefono;
		this.tipoVehiculo = tipoVehiculo;
	}
	public Mantenimiento() {
		super();
	}
	
	

}
