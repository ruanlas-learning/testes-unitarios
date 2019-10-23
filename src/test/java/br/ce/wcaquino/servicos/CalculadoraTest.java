package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.ce.wcaquino.exceptions.NaoPodeDividirPorZeroException;
import br.ce.wcaquino.runners.ParallelRunner;

@RunWith(ParallelRunner.class)
public class CalculadoraTest {
	
	private Calculadora	calc;
	
	@Before
	public void setup() {
		calc = new Calculadora();
		System.out.println("iniciando....");
	}
	
	@After
	public void tearDown() {
		System.out.println("finalizando....");
	}
	
	@AfterClass
	public static void tearDownClass() {
		
	}
	
	@Test
	public void deveSomarDoisValores() {
		//cenário
		int a = 5;
		int b = 3;
		
		//ação
		int resultado = calc.somar(a, b);
		
		//verificação
		assertEquals(8, resultado);
	}
	
	@Test
	public void deveSubtrairDoisValores() {
		//cenario
		int a = 8;
		int b = 5;
		
		//ação
		int resultado = calc.subtrair(a, b);
		
		//verificação
		assertEquals(3, resultado);
	}
	
	@Test
	public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
		//cenario
		int a = 6;
		int b = 3;
		
		//ação
		int resultado = calc.divide(a, b);
		
		//verificação
		assertEquals(2, resultado);
	}
	
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
		int a = 10;
		int b = 0;
		
		calc.divide(a, b);
	}
}
