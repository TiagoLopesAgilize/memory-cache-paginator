package br.com.agilizeware.pageable.memory.common;

public enum PageableTypePredicateEnum {

	OR("or"), FILTER("filter"), AND("and");

	String label;

	private PageableTypePredicateEnum(String typePredicate) {
		this.label = typePredicate;
	}

	public static PageableTypePredicateEnum findByLabel(String name) {
		for (PageableTypePredicateEnum userType : PageableTypePredicateEnum.values()) {
			if (userType.getLabel().equals(name)) {
				return userType;
			}
		}
		throw new IllegalArgumentException("Invalid Label.");
	}

	public static PageableTypePredicateEnum findByCode(Integer code) {
		PageableTypePredicateEnum[] array = PageableTypePredicateEnum.values();
		return array[code];
	}

	/**
	 * Sobrescrever toString para servico de Descricao utilizar o label para o
	 * front-end
	 */
	@Override
	public String toString() {
		return label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
