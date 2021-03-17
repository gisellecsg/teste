package br.com.ideiasportsgroup.rest.application;

import javax.ejb.Stateless;

@Stateless
public class TesteLeo implements TesteLeoLocal{

	public String teste() {
		return "TesteLeo []";
	}

}
