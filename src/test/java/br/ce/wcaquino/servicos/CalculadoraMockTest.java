package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.ce.wcaquino.entidades.Locacao;

public class CalculadoraMockTest {

	@Mock
	private Calculadora calcMock;
	
	@Spy
	private Calculadora calcSpy;
	
	@Mock
	private EmailService emailService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void devoMostrarDiferencaEntreMockSpy() {
//		Mockito.when(calcMock.somar(1, 2)).thenCallRealMethod();
		Mockito.when(calcMock.somar(1, 2)).thenReturn(8);
		Mockito.doReturn(8).when(calcSpy).somar(1, 2);
//		Mockito.when(calcSpy.somar(1, 2)).thenReturn(8);
		Mockito.doNothing().when(calcSpy).imprime();
		
		System.out.println("Mock: " + calcMock.somar(1, 2));
		System.out.println("Spy: " + calcSpy.somar(1, 2));
		
		System.out.println("MOCK");
		calcMock.imprime();
		System.out.println("Spy");
		calcSpy.imprime();
	}
	
	@Test
	public void teste() {
		Calculadora calc = Mockito.mock(Calculadora.class); 
		
		ArgumentCaptor<Integer> argCapt = ArgumentCaptor.forClass(Integer.class);
//		Mockito.when(calc.somar(1, 2)).thenReturn(5);
//		Mockito.when(calc.somar(Mockito.anyInt(), Mockito.anyInt())).thenReturn(5);
		
//		Mockito.when(calc.somar(Mockito.eq(1), Mockito.anyInt())).thenReturn(5);
		Mockito.when(calc.somar(argCapt.capture(), argCapt.capture())).thenReturn(5);
		
//		System.out.println(calc.somar(1, 2));
		assertEquals(5, calc.somar(1, 2));
		System.out.println(argCapt.getValue());
		System.out.println(argCapt.getAllValues());
	}
}
