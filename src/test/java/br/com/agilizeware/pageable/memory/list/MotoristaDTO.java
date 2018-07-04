package br.com.agilizeware.pageable.memory.list;

import java.io.Serializable;
import java.util.Date;

public class MotoristaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3127212120620765905L;
	private Integer id;
	private String nome;
	private Date dataNascimento;
	private String address;
	private DocumentoDTO documento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public DocumentoDTO getDocumento() {
		return documento;
	}

	public void setDocumento(DocumentoDTO documento) {
		this.documento = documento;
	}

}
