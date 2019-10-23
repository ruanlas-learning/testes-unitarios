package br.ce.wcaquino.matcher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import br.ce.wcaquino.utils.DataUtils;

public class DataDiferencaDiasMatcher extends TypeSafeMatcher<Date> {

	private Integer qtdDias;
	
	public DataDiferencaDiasMatcher(Integer dias) {
		this.qtdDias = dias;
	}
	
	public void describeTo(Description description) {
		// mensagem de erro
		Date dataEsperada = DataUtils.obterDataComDiferencaDias(qtdDias);
//		DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
//		description.appendText(format.format(dataEsperada));
		description.appendText(dataEsperada.toString());
	}

	@Override
	protected boolean matchesSafely(Date date) {
		return DataUtils.isMesmaData(
				date, 
				DataUtils.obterDataComDiferencaDias(qtdDias)
			);
	}

}
