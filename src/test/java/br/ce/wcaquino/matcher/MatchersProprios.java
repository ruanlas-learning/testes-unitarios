package br.ce.wcaquino.matcher;

import java.util.Calendar;

public class MatchersProprios {

	public static DiaSemanaMatcher caiEm(Integer diaSemana) {
		return new DiaSemanaMatcher(diaSemana);
	}
	
	public static DiaSemanaMatcher caiNumaSegunda() {
		return caiEm(Calendar.MONDAY);
	}
	
	public static DataDiferencaDiasMatcher ehHojeComDiferencaDias(Integer diferencaDias) {
		return new DataDiferencaDiasMatcher(diferencaDias);
	}
	
	public static DataDiferencaDiasMatcher ehHoje() {
		return ehHojeComDiferencaDias(0);
	}
}
