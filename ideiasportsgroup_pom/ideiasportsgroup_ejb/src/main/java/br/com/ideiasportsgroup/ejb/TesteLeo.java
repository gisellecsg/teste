package br.com.ideiasportsgroup.ejb;

import javax.ejb.Stateless;

@Stateless
public class TesteLeo implements TesteLeoLocal{

	public String teste() {
		return "TesteLeo []";
	}

}
