package br.ce.wcaquino.servicos;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	@Test
	public void teste() {
		//cenário
		LocacaoService locacaoService = new LocacaoService();
		Usuario usuario = new Usuario("Raj");
		Filme filme = new Filme("A estrela cadente", 2, 12.9);
		
		//ação
		Locacao locacao = locacaoService.alugarFilme(usuario, filme);
		
		
		//verificação
//		System.out.println(locacao.getValor());
//		System.out.println(locacao.getDataLocacao());
//		System.out.println(locacao.getDataRetorno());
		
//		System.out.println(locacao.getValor() == 12.9);
//		System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
//		System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
		Assert.assertTrue(locacao.getValor() == 12.9);
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
	}
}
