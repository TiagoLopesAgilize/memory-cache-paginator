package br.com.agilizeware.pageable.memory.list;

import java.io.Serializable;

public class PlacaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7163203733763740841L;
	private String estado;
	private String numero;
	private Integer sequencia;
	private Integer id;

	public String getEstado() {
		return estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

}
