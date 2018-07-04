package br.com.agilizeware.pageable.memory.common;

import java.io.Serializable;

public class SortDTO implements Serializable {

	private static final long serialVersionUID = 1098325128537123L;

	public SortDTO() {
	}

	public SortDTO(String name, ActiveEnum asc) {
		super();
		this.name = name;
		this.asc = asc;
	}

	private String name;
	private ActiveEnum asc;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ActiveEnum getAsc() {
		return asc;
	}

	public void setAsc(ActiveEnum asc) {
		this.asc = asc;
	}
}
