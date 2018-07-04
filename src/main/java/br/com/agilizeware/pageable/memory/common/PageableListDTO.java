package br.com.agilizeware.pageable.memory.common;

import java.io.Serializable;

/**
 * 
 * Nome: PageableListDTO.java Propósito:
 * <p>
 * Encapsula os dados do paginador quando estiver paginando na lista de
 * resultado diretamente na paginação no banco ou em cache.
 * </p>
 * 
 * @author Agilizeware <BR/>
 *         Equipe: Agilizeware - Software - São Paulo <BR>
 * @version: 1.7
 *
 *           Registro de Manutenção: 28/02/2018 08:27:24 - Autor: Lázaro Gilvan - Responsável: Lázaro Gilvan - Criação
 */
public class PageableListDTO<DTO> implements Serializable {

	/**
	 * Introduzir aqui os comentários necessários para o campo.
	 */
	private static final long serialVersionUID = 2674451009610071375L;

	/**
	 * Atributo pageableFilterDTO.
	 */
	private PageableFilterDTO pageableFilterDTO;

	/**
	 * Atributo list.
	 */
	private Iterable<DTO> list;

	/**
	 * Atributo totalRowsInCachePageable.
	 */
	private Long totalRowsInCachePageable;

	/**
	 * Construtor padrão da classe.
	 */
	public PageableListDTO() {
		super();
	}

	/**
	 * @return Retorna o valor do campo 'pageableFilterDTO'.
	 */
	public PageableFilterDTO getPageableFilterDTO() {
		return pageableFilterDTO;
	}

	/**
	 * @return Retorna o valor do campo 'list'.
	 */
	public Iterable<DTO> getList() {
		return list;
	}

	/**
	 * @param list
	 *            - O valor do campo 'list' a determinar.
	 */
	public void setList(Iterable<DTO> list) {
		this.list = list;
	}

	/**
	 * @param pageableFilterDTO
	 *            - O valor do campo 'pageableFilterDTO' a determinar.
	 */
	public void setPageableFilterDTO(PageableFilterDTO pageableFilterDTO) {
		this.pageableFilterDTO = pageableFilterDTO;
	}

	/**
	 * @return Retorna o valor do campo 'totalRowsInCachePageable'.
	 */
	public Long getTotalRowsInCachePageable() {
		return totalRowsInCachePageable;
	}

	/**
	 * @param totalRowsInCachePageable
	 *            - O valor do campo 'totalRowsInCachePageable' a determinar.
	 */
	public void setTotalRowsInCachePageable(Long totalRowsInCachePageable) {
		this.totalRowsInCachePageable = totalRowsInCachePageable;
	}
}
