package br.com.agilizeware.pageable.memory.common;

import java.io.Serializable;

public class PageableFilterParam implements Serializable {

	private static final long serialVersionUID = -1883781932235127736L;

	public PageableFilterParam() {
	}

	public PageableFilterParam(String param, FilterOperator filterOperator, String valueParam) {
		super();
		this.param = param;
		this.valueParam = valueParam;
		this.filterOperator = filterOperator;
		this.predicateType = PageableTypePredicateEnum.FILTER;
	}

	private String param;
	private String valueParam;
	// private Boolean isTypeEnum;
	private FilterOperator filterOperator;
	private PageableTypePredicateEnum predicateType;
	private PageableTypePredicateEnum predicateRestricBindingTypeParam;

	public PageableTypePredicateEnum getPredicateType() {
		return predicateType;
	}

	public void setPredicateType(PageableTypePredicateEnum predicateType) {
		this.predicateType = predicateType;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getValueParam() {
		return valueParam;
	}

	public void setValueParam(String valueParam) {
		this.valueParam = valueParam;
	}

	public PageableTypePredicateEnum getPredicateRestricBindingTypeParam() {
		return predicateRestricBindingTypeParam;
	}

	public void setPredicateRestricBindingTypeParam(PageableTypePredicateEnum predicateRestricBindingTypeParam) {
		this.predicateRestricBindingTypeParam = predicateRestricBindingTypeParam;
	}

	public FilterOperator getFilterOperator() {
		return filterOperator;
	}

	public void setFilterOperator(FilterOperator filterOperator) {
		this.filterOperator = filterOperator;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PageableFilterParam that = (PageableFilterParam) o;

		return param.equals(that.param);

	}

	@Override
	public int hashCode() {
		return param.hashCode();
	}
// /**
	// * @return the isTypeEnum
	// */
	// public Boolean getIsTypeEnum() {
	// return isTypeEnum;
	// }
	//
	// /**
	// * @param isTypeEnum the isTypeEnum to set
	// */
	// public void setIsTypeEnum(Boolean isTypeEnum) {
	// this.isTypeEnum = isTypeEnum;
	// }

}
