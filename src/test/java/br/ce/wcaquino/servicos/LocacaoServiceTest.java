package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static br.ce.wcaquino.matcher.MatchersProprios.caiEm;
import static br.ce.wcaquino.matcher.MatchersProprios.caiNumaSegunda;
import static br.ce.wcaquino.matcher.MatchersProprios.ehHoje;
import static br.ce.wcaquino.matcher.MatchersProprios.ehHojeComDiferencaDias;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.matcher.DiaSemanaMatcher;
import br.ce.wcaquino.matcher.MatchersProprios;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private LocacaoService locacaoService;
	
	@Before
	public void setup() {
		locacaoService = new LocacaoService();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@BeforeClass
	public static void setupClass() {
		
	}
	
	@AfterClass
	public static void tearDownClass() {
		
	}
	
	@Test
	public void deveAlugarFilme() throws Exception {
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		//cenário
		Usuario usuario = new Usuario("Raj");
		List<Filme> filmes = Arrays.asList(new Filme("A estrela cadente", 2, 12.9));
		
		//ação
		Locacao locacao = locacaoService.alugarFilme(usuario, filmes);
			
		//verificação
//			System.out.println(locacao.getValor());
//			System.out.println(locacao.getDataLocacao());
//			System.out.println(locacao.getDataRetorno());

//			System.out.println(locacao.getValor() == 12.9);
//			System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
//			System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
//			assertEquals(12.9, locacao.getValor(), 0.01);
//			assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
//			assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
//			
//			assertThat(locacao.getValor(), is(equalTo(12.9)));
//			assertThat(locacao.getValor(), is(not(6.0)));
//			assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
//			assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
		error.checkThat(locacao.getValor(), is(equalTo(12.9)));
		error.checkThat(locacao.getValor(), is(not(6.0)));
//		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(locacao.getDataLocacao(), ehHoje());
//		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
		error.checkThat(locacao.getDataRetorno(), ehHojeComDiferencaDias(1));
		
	}
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test(expected = FilmeSemEstoqueException.class)
	public void naoDeveAlugarFilmeSemEstoque() throws Exception {
		//cenário
		Usuario usuario = new Usuario("Raj");
		List<Filme> filmes = Arrays.asList(new Filme("A estrela cadente", 0, 12.9));
		
		//ação
		locacaoService.alugarFilme(usuario, filmes);
	}
	
	@Test
	public void naoDeveAlugarFilmeSemEstoque2() {
		//cenário
		Usuario usuario = new Usuario("Raj");
		List<Filme> filmes = Arrays.asList(new Filme("A estrela cadente", 0, 12.9));
		
		//ação
		try {
			locacaoService.alugarFilme(usuario, filmes);
			fail("Deveria ter lancado uma excecao");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Filme sem estoque"));
			e.printStackTrace();
		}
	}
	
	@Test
	public void naoDeveAlugarFilmeSemEstoque3() throws Exception {
		//cenário
		Usuario usuario = new Usuario("Raj");
		List<Filme> filmes = Arrays.asList(new Filme("A estrela cadente", 0, 12.9));
		
		exception.expect(Exception.class);
		exception.expectMessage("Filme sem estoque");
		
		//ação
		locacaoService.alugarFilme(usuario, filmes);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
		//cenário
		List<Filme> filmes = Arrays.asList(new Filme("A estrela cadente", 2, 12.9));
		
		
		//ação
		try {
			locacaoService.alugarFilme(null, filmes);
			fail("Deveria ter lançado uma exception");
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
			e.printStackTrace();
		}

	}
	
	@Test
	public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException {
		//cenário
		Usuario usuario = new Usuario("Raj");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		//ação
		locacaoService.alugarFilme(usuario, null);
		
	}
	
//	@Test
//	public void devePagar75PctNoFilme3() throws FilmeSemEstoqueException, LocadoraException {
//		//cenario
//		Usuario usuario = new Usuario("Usuario 1");
//		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 2, 4.0), new Filme("Filme 2", 2, 4.0), new Filme("Filme 3", 2, 4.0));
//		
//		//ação
//		Locacao resultado = locacaoService.alugarFilme(usuario, filmes);
//		
//		//verificação
//		assertThat(resultado.getValor(), is(11.0));
//	}
//	
//	@Test
//	public void devePagar50PctNoFilme4() throws FilmeSemEstoqueException, LocadoraException {
//		//cenario
//		Usuario usuario = new Usuario("Usuario 1");
//		List<Filme> filmes = Arrays.asList(
//				new Filme("Filme 1", 2, 4.0), 
//				new Filme("Filme 2", 2, 4.0), 
//				new Filme("Filme 3", 2, 4.0),
//				new Filme("Filme 4", 2, 4.0));
//		
//		//ação
//		Locacao resultado = locacaoService.alugarFilme(usuario, filmes);
//		
//		//verificação
//		assertThat(resultado.getValor(), is(13.0));
//	}
//	
//	@Test
//	public void devePagar25PctNoFilme5() throws FilmeSemEstoqueException, LocadoraException {
//		//cenario
//		Usuario usuario = new Usuario("Usuario 1");
//		List<Filme> filmes = Arrays.asList(
//				new Filme("Filme 1", 2, 4.0), 
//				new Filme("Filme 2", 2, 4.0), 
//				new Filme("Filme 3", 2, 4.0),
//				new Filme("Filme 4", 2, 4.0),
//				new Filme("Filme 5", 2, 4.0));
//		
//		//ação
//		Locacao resultado = locacaoService.alugarFilme(usuario, filmes);
//		
//		//verificação
//		assertThat(resultado.getValor(), is(14.0));
//	}
//
//	@Test
//	public void devePagar0PctNoFilme6() throws FilmeSemEstoqueException, LocadoraException {
//		//cenario
//		Usuario usuario = new Usuario("Usuario 1");
//		List<Filme> filmes = Arrays.asList(
//				new Filme("Filme 1", 2, 4.0), 
//				new Filme("Filme 2", 2, 4.0), 
//				new Filme("Filme 3", 2, 4.0),
//				new Filme("Filme 4", 2, 4.0),
//				new Filme("Filme 5", 2, 4.0),
//				new Filme("Filme 6", 2, 4.0));
//		
//		//ação
//		Locacao resultado = locacaoService.alugarFilme(usuario, filmes);
//		
//		//verificação
//		assertThat(resultado.getValor(), is(14.0));
//	}
	
	@Test
//	@Ignore
	public void deveDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException {
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		//cenario
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		
		//acao
		Locacao retorno = locacaoService.alugarFilme(usuario, filmes);
		
		//verificacao
		boolean ehSegunda = DataUtils.verificarDiaSemana(retorno.getDataRetorno(), Calendar.MONDAY);
		
		assertTrue(ehSegunda);
//		assertThat(retorno.getDataRetorno(),  new DiaSemanaMatcher(Calendar.MONDAY));
//		assertThat(retorno.getDataRetorno(),  MatchersProprios.caiEm(Calendar.MONDAY));
		assertThat(retorno.getDataRetorno(),  caiEm(Calendar.MONDAY));
		assertThat(retorno.getDataRetorno(),  caiNumaSegunda());
	}
}
