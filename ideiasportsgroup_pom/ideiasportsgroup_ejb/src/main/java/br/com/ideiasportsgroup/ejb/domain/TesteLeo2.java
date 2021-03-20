package br.com.ideiasportsgroup.ejb.domain;

import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Stateless
public class TesteLeo2 {

	private final Logger logger = LogManager.getLogger(this.getClass());

	public String teste() {
		this.logger.error("TesteLeo [] LOG");
		return "TesteLeo []";
	}

}
