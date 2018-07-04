package br.com.agilizeware.pageable.memory.common;

public enum ActiveEnum {
	INACTIVE("inactive"),
	ACTIVE("active");

	private String label;

	private ActiveEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public static ActiveEnum findByLabel(String label) {
		for (ActiveEnum userType : ActiveEnum.values()) {
			if (userType.getLabel().equals(label)) {
				return userType;
			}
		}
		throw new IllegalArgumentException("Invalid label.");
	}
	
	public static ActiveEnum findByCode(Integer code) {
		ActiveEnum[] array = ActiveEnum.values();
		return array[code];
	}
	
	/**
	 * Sobrescrever toString para servico de Descricao utilizar o label para o front-end
	 */
	@Override
	public String toString() {
		return label;
	}
}
