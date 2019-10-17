package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar método para salvar
		
		return locacao;
	}

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