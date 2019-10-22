package br.ce.wcaquino.matcher;

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
		Date date = DataUtils.obterDataComDiferencaDias(qtdDias);
		description.appendText(date.toString());
	}

	@Override
	protected boolean matchesSafely(Date date) {
		return DataUtils.isMesmaData(
				date, 
				DataUtils.obterDataComDiferencaDias(qtdDias)
			);
	}

}
