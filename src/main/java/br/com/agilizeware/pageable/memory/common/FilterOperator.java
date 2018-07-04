package br.com.agilizeware.pageable.memory.common;

/**
 * Comparison operator.
 */
public enum FilterOperator {
    EQ ("filter.op.equal"),
    GE ("filter.op.greater.than.or.equal"),
    LE ("filter.op.less.than.or.equal"),
    GT ("filter.op.greater.than"),
    LT ("filter.op.less.than"),
    IN ("filter.op.in"),
    LIKE ("filter.op.like"),
    CONTAINS ("filter.op.contains"),
    STARTSWITH ("filter.op.starts.with"),
    ENDSWITH ("filter.op.ends.with"),
    ISEMPTY ("filter.op.is.empty"),
    ISNOTEMPTY ("filter.op.is.not.empty"),
    BETWEEN("filter.op.between");
    
	private String label;

	private FilterOperator(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public static FilterOperator findByLabel(String label) {
		for (FilterOperator userType : FilterOperator.values()) {
			if (userType.getLabel().equals(label)) {
				return userType;
			}
		}
		throw new IllegalArgumentException("Invalid label.");
	}
	
	public static FilterOperator findByCode(Integer code) {
		FilterOperator[] array = FilterOperator.values();
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
