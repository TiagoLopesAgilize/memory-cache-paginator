package br.com.agilizeware.pageable.memory.list;

import java.io.Serializable;
import java.util.Date;

public class VeiculoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6669210240158392568L;
	private Integer id;
	private PlacaDTO placa;
	private MotoristaDTO motorista;
	private String marca;
	private String modelo;
	private String ano;
	private Date dataFabricacao;

	public PlacaDTO getPlaca() {
		return placa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPlaca(PlacaDTO placa) {
		this.placa = placa;
	}

	public MotoristaDTO getMotorista() {
		return motorista;
	}

	public void setMotorista(MotoristaDTO motorista) {
		this.motorista = motorista;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Date getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(Date dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

}
