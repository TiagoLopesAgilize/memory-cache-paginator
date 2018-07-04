package br.com.agilizeware.pageable.memory.list;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import br.com.agilizeware.pageable.memory.common.ActiveEnum;
import br.com.agilizeware.pageable.memory.common.DateUtil;
import br.com.agilizeware.pageable.memory.common.FilterOperator;
import br.com.agilizeware.pageable.memory.common.PageableFilterDTO;
import br.com.agilizeware.pageable.memory.common.PageableFilterParam;
import br.com.agilizeware.pageable.memory.common.PageableListDTO;
import br.com.agilizeware.pageable.memory.common.PageableTypePredicateEnum;
import br.com.agilizeware.pageable.memory.common.SortDTO;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		MemoryCachePaginatorVeiculoImpl memoryCacheVeiculoPaginator;
		try {
			memoryCacheVeiculoPaginator = new MemoryCachePaginatorVeiculoImpl(VeiculoDTO.class);

			PageableListDTO<VeiculoDTO> pageabble = new PageableListDTO<VeiculoDTO>();
			List<VeiculoDTO> cacheList = new ArrayList<VeiculoDTO>();
			PageableFilterDTO pageableFiltersDTO = new PageableFilterDTO();
			LinkedHashSet<PageableFilterParam> paramsFilter = new LinkedHashSet<PageableFilterParam>();
			int page = 0;
			Integer rowsPerPage = 10;
			List<SortDTO> sorts = new ArrayList<SortDTO>();

			SortDTO sortDTO = new SortDTO();
			sortDTO.setAsc(ActiveEnum.ACTIVE);
			PageableFilterParam param1 = new PageableFilterParam();
			param1.setFilterOperator(FilterOperator.LIKE);
			param1.setParam("marca");
			param1.setValueParam("FIAT");
			// param1.setParam("active");
			// param1.setValueParam("1");
			param1.setPredicateType(PageableTypePredicateEnum.FILTER);

			paramsFilter.add(param1);

			cacheList = this.generateCacheList();
			pageableFiltersDTO.setParamsFilter(paramsFilter);
			pageableFiltersDTO.setPage(page);
			pageableFiltersDTO.setRowsPerPage(rowsPerPage);
			pageableFiltersDTO.setTotalRows((cacheList).size());
			pageableFiltersDTO.setSorts(sorts);
			pageabble = memoryCacheVeiculoPaginator.findPageableCache(cacheList, pageableFiltersDTO);
			this.imprmirResultado(pageabble);

			pageableFiltersDTO.setPage(1);

			pageabble = memoryCacheVeiculoPaginator.findPageableCache(cacheList, pageableFiltersDTO);
			this.imprmirResultado(pageabble);
			assertTrue(true);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testAppMuita() {
		assertTrue(true);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testAppCaso1() {
		assertTrue(true);
	}

	public void imprmirResultado(PageableListDTO<VeiculoDTO> resultadoPageado) {

		System.out.println("Rows Per Page: " + resultadoPageado.getPageableFilterDTO().getRowsPerPage());
		System.out.println("Page: " + resultadoPageado.getPageableFilterDTO().getPage());
		System.out.println("Total Rows: " + resultadoPageado.getPageableFilterDTO().getTotalRows());
		System.out.println("######################################################################################");
		Iterable<VeiculoDTO> resultList = resultadoPageado.getList();

		for (VeiculoDTO veiculoDTO : resultList) {
			System.out.println("");
			System.out.println("Modelo: " + veiculoDTO.getModelo());
			System.out.println("Motorista: " + veiculoDTO.getMotorista().getNome());
			System.out.println("CNH: " + veiculoDTO.getMotorista().getDocumento().getCnh());
			System.out
					.println("######################################################################################");

		}
	}

	public List<VeiculoDTO> generateCacheList() {
		List<VeiculoDTO> cacheList = new ArrayList<VeiculoDTO>();
		VeiculoDTO lDto = null;
		PlacaDTO placaDTO = null;
		MotoristaDTO motoristaDTO = null;
		DocumentoDTO documentoDTO = null;
		int random = 0;
		for (int i = 0; i < 10000; i++) {
			lDto = new VeiculoDTO();
			random = RandomUtils.nextInt();
			placaDTO = new PlacaDTO();
			motoristaDTO = new MotoristaDTO();
			documentoDTO = new DocumentoDTO();
			if (random % 2 == 0) {

				motoristaDTO.setId(i);
				motoristaDTO.setNome("Vinicius " + i);
				motoristaDTO.setAddress("Adress " + random);

				documentoDTO.setCnh("123343558" + random);
				documentoDTO.setCpf("9863245" + random);
				documentoDTO.setRg("7532937252" + random);

				motoristaDTO.setDocumento(documentoDTO);

				placaDTO.setEstado((random % 2 == 0 ? "BA" : "SP"));
				placaDTO.setId(i);
				placaDTO.setNumero((random % 2 == 0 ? "BAS " + random : "RND " + random));
				placaDTO.setSequencia(random);

				lDto.setDataFabricacao(DateUtil.plus(Calendar.SECOND, i));
				lDto.setPlaca(placaDTO);
				lDto.setAno("198" + i);
				lDto.setModelo((random % 2 == 0 ? "UNO " + random : "Corola " + random));
				lDto.setMotorista(motoristaDTO);
				lDto.setMarca((random % 2 == 0 ? "FIAT " + random : "Toyota " + random));
				lDto.setId(random);

			} else {
				motoristaDTO.setId(i);
				motoristaDTO.setNome("Vinicius " + i);
				motoristaDTO.setAddress("Adress " + random);

				documentoDTO.setCnh("97637292" + random);
				documentoDTO.setCpf("78645233" + random);
				documentoDTO.setRg("10925385" + random);

				motoristaDTO.setDocumento(documentoDTO);

				placaDTO.setEstado((random % 2 == 0 ? "MG" : "RJ"));
				placaDTO.setId(i);
				placaDTO.setNumero((random % 2 == 0 ? "SAB " + random : "DNR " + random));
				placaDTO.setSequencia(random);

				lDto.setDataFabricacao(DateUtil.plus(Calendar.SECOND, i));
				lDto.setPlaca(placaDTO);
				lDto.setAno("199" + i);
				lDto.setModelo((random % 2 == 0 ? "Ranger " + random : "Civic " + random));
				lDto.setMotorista(motoristaDTO);
				lDto.setMarca((random % 2 == 0 ? "Ford " + random : "Honda " + random));
				lDto.setId(random);
			}

			cacheList.add(lDto);
		}

		return cacheList;
	}

}
