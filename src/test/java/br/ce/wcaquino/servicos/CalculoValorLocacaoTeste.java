package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.builders.FilmeBuilder.umFilme;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import br.ce.wcaquino.daos.LocacaoDAO;
import br.ce.wcaquino.daos.LocacaoDAOFake;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTeste {

	@Parameter(value = 0)
	public List<Filme> filmes;
	@Parameter(value = 1)
	public Double valorLocacao;
	@Parameter(value = 2)
	public String cenario;
	
	private LocacaoService locacaoService;
	
	@Before
	public void setup() {
		locacaoService = new LocacaoService();
//		LocacaoDAO dao = new LocacaoDAOFake();
		LocacaoDAO dao = Mockito.mock(LocacaoDAO.class);
		SPCService spcService = Mockito.mock(SPCService.class);
		
		locacaoService.setLocacaoDAO(dao);
		locacaoService.setSPCService(spcService);
	}
	
	@Parameters(name = "Teste {index} = {2}")
	public static Collection<Object[]> getParametros() {
//		Filme filme1 = new Filme("Filme 1", 2, 4.0);
//		Filme filme2 = new Filme("Filme 2", 2, 4.0);
//		Filme filme3 = new Filme("Filme 3", 2, 4.0);
//		Filme filme4 = new Filme("Filme 4", 2, 4.0);
//		Filme filme5 = new Filme("Filme 5", 2, 4.0);
//		Filme filme6 = new Filme("Filme 6", 2, 4.0);
//		Filme filme7 = new Filme("Filme 7", 2, 4.0);
		Filme filme1 = umFilme().agora();
		Filme filme2 = umFilme().agora();
		Filme filme3 = umFilme().agora();
		Filme filme4 = umFilme().agora();
		Filme filme5 = umFilme().agora();
		Filme filme6 = umFilme().agora();
		Filme filme7 = umFilme().agora();
		return Arrays.asList(new Object[][] {
			{Arrays.asList(filme1, filme2), 8.0, "2 Filmes: Sem Desconto"},
			{Arrays.asList(filme1, filme2, filme3), 11.0, "3 Filmes: 25%"},
			{Arrays.asList(filme1, filme2, filme3, filme4), 13.0, "4 Filmes: 50%"},
			{Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0, "5 Filmes: 75%"},
			{Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0, "6 Filmes: 100%"},
			{Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6, filme7), 18.0, "7 Filmes: Sem Desconto"},
		});
	}
	
	@Test
	public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, LocadoraException {
		//cenario
		Usuario usuario = new Usuario("Usuario 1");
		
		//ação
		Locacao resultado = locacaoService.alugarFilme(usuario, filmes);
		
		//verificação
		assertThat(resultado.getValor(), is(valorLocacao));
	}
	
	@Test
	public void print() {
		System.out.println(valorLocacao);
	}
}
