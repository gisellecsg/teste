package br.com.ideiasportsgroup.web.restfull.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.ideiasportsgroup.web.restfull.rest.beans.PessoaRest;

@ApplicationPath("/rest")
public class ApplicationRest extends Application {

	private final Set<Object> singletons = new HashSet<Object>();
	private final Set<Class<?>> empty = new HashSet<Class<?>>();

	public ApplicationRest() {
		empty.add(PessoaRest.class);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return empty;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}

}