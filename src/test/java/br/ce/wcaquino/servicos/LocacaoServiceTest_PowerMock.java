package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static br.ce.wcaquino.builders.FilmeBuilder.umFilme;
import static br.ce.wcaquino.builders.LocacaoBuilder.umLocacao;
import static br.ce.wcaquino.builders.UsuarioBuilder.umUsuario;
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
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import br.ce.wcaquino.builders.FilmeBuilder;
import br.ce.wcaquino.builders.LocacaoBuilder;
import br.ce.wcaquino.builders.UsuarioBuilder;
import br.ce.wcaquino.daos.LocacaoDAO;
import br.ce.wcaquino.daos.LocacaoDAOFake;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.matcher.DiaSemanaMatcher;
import br.ce.wcaquino.matcher.MatchersProprios;
import br.ce.wcaquino.utils.DataUtils;
import buildermaster.BuilderMaster;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
	LocacaoService.class,
//	DataUtils.class
})
@PowerMockIgnore("jdk.internal.reflect.*")
public class LocacaoServiceTest_PowerMock {

	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@InjectMocks
	private LocacaoService locacaoService;
	
	@Mock
	private SPCService spcService;
	@Mock
	private LocacaoDAO dao;
	@Mock
	private EmailService emailService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
//		locacaoService = new LocacaoService();
////		LocacaoDAO dao = new LocacaoDAOFake();
//		
//		dao = Mockito.mock(LocacaoDAO.class);
//		spcService = Mockito.mock(SPCService.class);
//		emailService = Mockito.mock(EmailService.class);
//		
//		locacaoService.setLocacaoDAO(dao);
//		locacaoService.setSPCService(spcService);
//		locacaoService.setEmailService(emailService);
		locacaoService = PowerMockito.spy(locacaoService);
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
//		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(DataUtils.obterData(27, 4, 2017));
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(2017, Calendar.APRIL, 27);
//		PowerMockito.mockStatic(Calendar.class);
//		PowerMockito.when(Calendar.getInstance()).thenReturn(calendar);
		
		//cenário
//		Usuario usuario = new Usuario("Raj");
//		Usuario usuario = UsuarioBuilder.umUsuario().agora();
		Usuario usuario = umUsuario().agora();
//		List<Filme> filmes = Arrays.asList(new Filme("A estrela cadente", 2, 12.9));
		List<Filme> filmes = Arrays.asList(umFilme().comValor(12.9).agora());
		
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
//		error.checkThat(locacao.getDataLocacao(), ehHoje());
//		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
//		error.checkThat(locacao.getDataRetorno(), ehHojeComDiferencaDias(1));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), DataUtils.obterData(27, 4, 2017)), is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterData(28, 4, 2017)), is(true));
	}
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test(expected = FilmeSemEstoqueException.class)
	public void naoDeveAlugarFilmeSemEstoque() throws Exception {
		//cenário
//		Usuario usuario = new Usuario("Raj");
		Usuario usuario = umUsuario().agora();
//		List<Filme> filmes = Arrays.asList(new Filme("A estrela cadente", 0, 12.9));
		List<Filme> filmes = Arrays.asList(umFilme().semEstoque().agora());
		
		//ação
		locacaoService.alugarFilme(usuario, filmes);
	}
	
	@Test
	public void naoDeveAlugarFilmeSemEstoque2() {
		//cenário
//		Usuario usuario = new Usuario("Raj");
		Usuario usuario = umUsuario().agora();
//		List<Filme> filmes = Arrays.asList(new Filme("A estrela cadente", 0, 12.9));
		List<Filme> filmes = Arrays.asList(umFilme().semEstoque().agora());
		
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
//		Usuario usuario = new Usuario("Raj");
		Usuario usuario = umUsuario().agora();
//		List<Filme> filmes = Arrays.asList(new Filme("A estrela cadente", 0, 12.9));
		List<Filme> filmes = Arrays.asList(umFilme().semEstoque().agora());		
		
		exception.expect(Exception.class);
		exception.expectMessage("Filme sem estoque");
		
		//ação
		locacaoService.alugarFilme(usuario, filmes);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
		//cenário
//		List<Filme> filmes = Arrays.asList(new Filme("A estrela cadente", 2, 12.9));
		List<Filme> filmes = Arrays.asList(umFilme().agora());
		
		
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
//		Usuario usuario = new Usuario("Raj");
		Usuario usuario = umUsuario().agora();
		
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
	public void deveDevolverNaSegundaAoAlugarNoSabado() throws Exception {
//		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(DataUtils.obterData(29, 4, 2017));
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(2017, Calendar.APRIL, 29);
//		PowerMockito.mockStatic(Calendar.class);
//		PowerMockito.when(Calendar.getInstance()).thenReturn(calendar);
		
		//cenario
//		Usuario usuario = new Usuario("Usuario 1");
		Usuario usuario = umUsuario().agora();
//		List<Filme> filmes = Arrays.asList(new Filme("Filme 1", 1, 5.0));
		List<Filme> filmes = Arrays.asList(umFilme().agora());
		
		//acao
		Locacao retorno = locacaoService.alugarFilme(usuario, filmes);
		
		//verificacao
		boolean ehSegunda = DataUtils.verificarDiaSemana(retorno.getDataRetorno(), Calendar.MONDAY);
		
		assertTrue(ehSegunda);
//		assertThat(retorno.getDataRetorno(),  new DiaSemanaMatcher(Calendar.MONDAY));
//		assertThat(retorno.getDataRetorno(),  MatchersProprios.caiEm(Calendar.MONDAY));
		assertThat(retorno.getDataRetorno(),  caiEm(Calendar.MONDAY));
		assertThat(retorno.getDataRetorno(),  caiNumaSegunda());
		PowerMockito.verifyNew(Date.class, Mockito.times(2)).withNoArguments();
		
//		PowerMockito.verifyStatic(Mockito.times(2));
//		Calendar.getInstance();
	}
	
//	public static void main(String[] args) {
//		new BuilderMaster().gerarCodigoClasse(Locacao.class);
//	}
	
	@Test
//	public void naoDeveAlugarParaNegativadoSPC() throws FilmeSemEstoqueException, LocadoraException {
	public void naoDeveAlugarParaNegativadoSPC() throws Exception {
		//cenario
		Usuario usuario = umUsuario().agora();
		Usuario usuario2 = umUsuario().comNome("teste").agora();
		List<Filme> filmes = Arrays.asList(umFilme().agora());
		
//		Mockito.when(spcService.possuiNegativacao(usuario)).thenReturn(true);
		Mockito.when(spcService.possuiNegativacao(Mockito.any(Usuario.class))).thenReturn(true);
		
//		exception.expect(LocadoraException.class);
//		exception.expectMessage("Usuário Negativado");
		
		//acao
		try {
			locacaoService.alugarFilme(usuario, filmes);
//			locacaoService.alugarFilme(usuario2, filmes);
			fail();
		} catch (LocadoraException e) {
			//verificacao
			assertThat(e.getMessage(), is("Usuário Negativado"));
		}
//		locacaoService.alugarFilme(usuario2, filmes);
		
		Mockito.verify(spcService).possuiNegativacao(usuario);
//		Mockito.verify(spcService).possuiNegativacao(usuario2);
	}
	
	@Test
	public void deveEnviarEmailParaLocacoesAtrasadas() {
		//cenario
		Usuario usuario = umUsuario().agora();
		Usuario usuario2 = umUsuario().comNome("Usuario 2").agora();
		Usuario usuario3 = umUsuario().comNome("Usuario em dia").agora();
		Usuario usuario4 = umUsuario().comNome("Atrasado").agora();
		List<Locacao> locacoes = Arrays.asList(
				umLocacao().comUsuario(usuario).atrasada().agora(),
				umLocacao().comUsuario(usuario3).agora(),
				umLocacao().comUsuario(usuario4).atrasada().agora(),
				umLocacao().comUsuario(usuario4).atrasada().agora()
			);
		
		Mockito.when(dao.obterLocacoesPendentes()).thenReturn(locacoes);
		//acao
		locacaoService.notificarAtrasos();
		
		//verificacao
//		Mockito.verify(emailService, Mockito.times(3)).notificarAtraso(Mockito.any(Usuario.class));
		Mockito.verify(emailService).notificarAtraso(usuario);
		Mockito.verify(emailService, Mockito.times(2)).notificarAtraso(usuario4);
//		Mockito.verify(emailService, Mockito.atLeastOnce()).notificarAtraso(usuario4);
		Mockito.verify(emailService, Mockito.never()).notificarAtraso(usuario3);
		Mockito.verifyNoMoreInteractions(emailService);
		
//		Mockito.verifyZeroInteractions(spcService);
//		Mockito.verify(emailService).notificarAtraso(usuario3);
//		Mockito.verify(emailService).notificarAtraso(usuario2);
		
	}
	
	@Test
	public void deveTratarErroNoSPC() throws Exception {
		//cenario
		Usuario usuario = umUsuario().agora();
		List<Filme> filmes = Arrays.asList(umFilme().agora());
		
		Mockito.when(spcService.possuiNegativacao(usuario)).thenThrow(new Exception("Falha catastrófica"));
		
		//verificacao
		exception.expect(LocadoraException.class);
		exception.expectMessage("Problemas com o SPC, tente novamente");
		
		//acao
		locacaoService.alugarFilme(usuario, filmes);
		
	}
	
	@Test
	public void deveProrrogarUmaLocacao() {
		//cenario
		Locacao locacao = umLocacao().agora();
		
		//acao
		locacaoService.prorrogarLocacao(locacao, 3);
		
		//verificacao
//		Mockito.verify(dao).salvar(Mockito.any(Locacao.class));
//		Mockito.verify(dao).salvar(locacao);
		ArgumentCaptor<Locacao> argCapt = ArgumentCaptor.forClass(Locacao.class);
		Mockito.verify(dao).salvar(argCapt.capture());
		Locacao locacaoRetornada = argCapt.getValue();
		
//		assertThat(locacaoRetornada.getValor(), is(4.0));
//		assertThat(locacaoRetornada.getDataLocacao(), ehHoje());
//		assertThat(locacaoRetornada.getDataRetorno(), ehHojeComDiferencaDias(5));
		
		error.checkThat(locacaoRetornada.getValor(), is(4.0 * 3));
		error.checkThat(locacaoRetornada.getDataLocacao(), ehHoje());
		error.checkThat(locacaoRetornada.getDataRetorno(), ehHojeComDiferencaDias(3));
	}
	
	@Test
	public void deveAlugarFilmeSemCalcularValor() throws Exception {
		//cenario
		Usuario usuario = umUsuario().agora();
		List<Filme> filmes = Arrays.asList(umFilme().agora());
		
		PowerMockito.doReturn(1.0).when(locacaoService, "calcularValorLocacao", filmes);
		
		//acao
		Locacao locacao = locacaoService.alugarFilme(usuario, filmes);
		
		//verificacao
		assertThat(locacao.getValor(), is(1.0));
		PowerMockito.verifyPrivate(locacaoService).invoke("calcularValorLocacao", filmes);
	}
	
	@Test
	public void deveCalcularValorLocacao() throws Exception {
		//cenario
		List<Filme> filmes = Arrays.asList(umFilme().agora());
		
		//acao
		Double valor = (Double) Whitebox.invokeMethod(locacaoService, "calcularValorLocacao", filmes);
		
		//verificacao
		assertThat(valor, is(4.0));
		
	}
}
