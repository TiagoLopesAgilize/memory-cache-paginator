package br.com.agilizeware.pageable.memory.core;

import java.util.List;

import br.com.agilizeware.pageable.memory.common.PageableFilterDTO;

/**
 * 
 * Nome: PageableListHelper.java
 * 
 * Propósito:
 * <p>
 * Responsável pela paginação de listas em memória. Deve receber como parametro
 * o PageableFilterDTO que contém informações necessárias para a paginação
 * </p>
 * 
 * @author Agilizeware <BR/>
 *         Equipe: Agilizeware - Software - São Paulo <BR>
 * @version: 1.7 Parâmetro de compilação insira os parâmetros de compilação
 *
 * @throws: caso
 *              a classe não lance exception retirar essa linha
 * 
 * @see: insira a referência
 * 
 *       Registro de Manutenção: 26/02/2018 16:57:14 - Autor: Tiago Lopes -
 *       Responsável: Tiago Lopes - Criação
 */
public class PageableListHelper {
	PageableFilterDTO pageableFilterDTO;

	public PageableListHelper(PageableFilterDTO pageableFilterDTO) {
		this.pageableFilterDTO = pageableFilterDTO;
	}

	/**
	 * 
	 * Retorna a próxima visualização de pagina da lista passada como parametro, com
	 * base nas informações contidas no pageableFilterDTO.
	 * 
	 * @return
	 */
	public List getNextPage(List list) {
		if (list != null && this.pageableFilterDTO != null) {
			int paginaAtual = pageableFilterDTO.getPage();
			int linhasPorPagina = pageableFilterDTO.getRowsPerPage();
			int totalDeLinhas = pageableFilterDTO.getTotalRows();

			int startIndex = paginaAtual * linhasPorPagina;
			int endingIndex = startIndex + linhasPorPagina;

			if (endingIndex >= pageableFilterDTO.getTotalRows()) {
				endingIndex = totalDeLinhas;
			}
			return list.subList(startIndex, endingIndex);
		}
		return null;
	}
}
