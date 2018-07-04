/**
 * 
 */
package br.com.agilizeware.pageable.memory.paginador;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.PredicateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.agilizeware.pageable.memory.common.FilterOperator;
import br.com.agilizeware.pageable.memory.common.Functions;
import br.com.agilizeware.pageable.memory.common.PageableFilterDTO;
import br.com.agilizeware.pageable.memory.common.PageableFilterParam;
import br.com.agilizeware.pageable.memory.common.PageableListDTO;
import br.com.agilizeware.pageable.memory.common.PageableTypePredicateEnum;
import br.com.agilizeware.pageable.memory.common.Util;
import br.com.agilizeware.pageable.memory.core.PageableListHelper;
import br.com.agilizeware.pageable.memory.core.PredicateCommonsBuilder;

/**
 * @author tiagolopes
 *
 */
public abstract class MemoryCachePaginatorAB<P extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2371711106455114967L;

	static Logger log = LoggerFactory.getLogger(Functions.class);

	/**
	 * Atributo dto. Data transfer object atual na instância do DAO no tempo de
	 * execução de acesso a dados através da API padrão da plataforma.
	 */
	protected P dto;

	/**
	 * 
	 * Construtor padrão da classe.
	 * 
	 * @see insira o 'see" aqui.
	 *
	 * @param dto
	 * @param entity
	 * @param pRepostory
	 */
	public MemoryCachePaginatorAB(Class<P> dto) throws InstantiationException, IllegalAccessException {
		super();
		try {
			this.dto = dto.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw e;
		}
	}

	/**
	 * 
	 * Método responsável por filtrar e paginar a lista passada como parametro com
	 * base nos filtros do PageableFilterDTO
	 * 
	 * @param cacheList
	 * @param pageableFiltersDTO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageableListDTO<P> findPageableCache(List<P> cacheList, PageableFilterDTO pageableFiltersDTO) {
		pageableFiltersDTO.setTotalRows(cacheList.size());

		List<P> lista = null;
		try {
			Predicate<P> sqlOrQueryPredicate;
			List<Predicate<P>> orQueryPredicates = new ArrayList<Predicate<P>>();
			Predicate<P> sqlAndQueryPredicate;
			List<Predicate<P>> andQueryPredicates = new ArrayList<Predicate<P>>();

			if (pageableFiltersDTO.getParamsFilter() != null) {
				for (PageableFilterParam filterParam : pageableFiltersDTO.getParamsFilter()) {
					boolean isEnum = false;
					boolean isDate = false;
					Enum objEnum = null;

					Field field = Util.getField(dto, filterParam.getParam());

					if (field != null) {

						if (field.getType().isEnum()) {
							isEnum = true;
						}
						if (field.getType().equals(java.util.Date.class)) {
							isDate = true;
						}

						if (isEnum) {
							objEnum = Functions.getEnumByOrdinal(Integer.valueOf(filterParam.getValueParam()),
									field.getType());
						}

						Predicate<P> predicate = null;
						if (isEnum) {
							// TODO implementar lógica no makepredicate para poder
							// utilizar em outros tipos de filtros o enum
							predicate = PredicateCommonsBuilder.makePredicate(filterParam.getParam(), objEnum, field,
									FilterOperator.EQ);// Fixo EQ igual
														// implementação de
														// paginação no banco
						} else if (isDate) {
							Date dtValue = new Date();
							if (!(filterParam.getValueParam() == null
									|| filterParam.getValueParam().toLowerCase().equals("notnull"))) {
								String dt = filterParam.getValueParam();
								try {
									if (dt != null) {
										dt = dt.replace("Z", "");
										dt = dt.replace("T", " ");
										SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.000");
										try {
											dtValue = sdf.parse(dt);
										} catch (ParseException e) {
										}
									}
								} catch (Exception ee) {
								}
							}
							if (filterParam.getValueParam() == null) {
								predicate = PredicateCommonsBuilder.makePredicate(filterParam.getParam(),
										filterParam.getValueParam(), field, FilterOperator.ISEMPTY);
							} else if (filterParam.getValueParam().toLowerCase().equals("notnull")) {
								predicate = PredicateCommonsBuilder.makePredicate(filterParam.getParam(),
										filterParam.getValueParam(), field, FilterOperator.ISNOTEMPTY);
							} else {
								predicate = PredicateCommonsBuilder.makePredicate(filterParam.getParam(), dtValue,
										field, filterParam.getFilterOperator());
							}
						} else {
							if (filterParam.getValueParam() == null) {
								predicate = PredicateCommonsBuilder.makePredicate(filterParam.getParam(),
										filterParam.getValueParam(), field, FilterOperator.ISEMPTY);
							} else if (filterParam.getValueParam().toLowerCase().equals("notnull")) {
								predicate = PredicateCommonsBuilder.makePredicate(filterParam.getParam(),
										filterParam.getValueParam(), field, FilterOperator.ISNOTEMPTY);
							} else {
								predicate = PredicateCommonsBuilder.makePredicate(filterParam.getParam(),
										filterParam.getValueParam(), field, filterParam.getFilterOperator());

							}
						}
						if (filterParam.getPredicateType().equals(PageableTypePredicateEnum.FILTER) || filterParam
								.getPredicateRestricBindingTypeParam().equals(PageableTypePredicateEnum.AND)) {
							andQueryPredicates.add(predicate);
						} else if (filterParam.getPredicateRestricBindingTypeParam()
								.equals(PageableTypePredicateEnum.OR)) {
							orQueryPredicates.add(predicate);
						}
					}
				}

				List<Predicate<P>> conditions = new ArrayList<Predicate<P>>();

				// Teste para garantir que não vá um predicate nulo para o
				// método select
				if (!andQueryPredicates.isEmpty()) {
					sqlAndQueryPredicate = PredicateUtils.allPredicate(andQueryPredicates);
					conditions.add(sqlAndQueryPredicate);
				}
				if (!orQueryPredicates.isEmpty()) {
					sqlOrQueryPredicate = PredicateUtils.anyPredicate(orQueryPredicates);
					conditions.add(sqlOrQueryPredicate);
				}

				// Todos os predicates tem que dar verdadeiro se não, não será
				// encontrado nenhum registro. Comportamento igual a and.
				lista = (List<P>) CollectionUtils.select((Collection<P>) cacheList,
						PredicateUtils.allPredicate(conditions));
			} else {
				lista = cacheList;
			}
		} catch (Exception e2) {
			log.error("Erro na determinacao da classe do parametro informado para filtro", e2);
		}
		// Setar o total de paginas antes de passar ao PageableListHelper
		pageableFiltersDTO.setTotalRows(lista.size());

		PageableListHelper pageableListHelper = new PageableListHelper(pageableFiltersDTO);

		PageableListDTO<P> pageableListDTO = new PageableListDTO<P>();
		pageableListDTO.setList(pageableListHelper.getNextPage(lista));
		pageableListDTO.setPageableFilterDTO(pageableFiltersDTO);
		return pageableListDTO;
	}
}
