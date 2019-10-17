package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
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
	public void testeLocacao() throws Exception {
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
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
		
	}
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test(expected = FilmeSemEstoqueException.class)
	public void testeLocacao_filmeSemEstoque() throws Exception {
		//cenário
		Usuario usuario = new Usuario("Raj");
		List<Filme> filmes = Arrays.asList(new Filme("A estrela cadente", 0, 12.9));
		
		//ação
		locacaoService.alugarFilme(usuario, filmes);
	}
	
	@Test
	public void testeLocacao_filmeSemEstoque2() {
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
	public void testeLocacao_filmeSemEstoque3() throws Exception {
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
	public void testeLocacao_usuarioVazio() throws FilmeSemEstoqueException {
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
	public void testeLocacao_filmeVazio() throws FilmeSemEstoqueException, LocadoraException {
		//cenário
		Usuario usuario = new Usuario("Raj");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		//ação
		locacaoService.alugarFilme(usuario, null);
		
	}
}
