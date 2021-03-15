package br.com.ideiasportsgroup.rest.application;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.ideiasportsgroup.rest.beans.RestInscricao;

@ApplicationPath("/rest")
public class RestApplication extends Application {

	private final Set<Object> singletons = new HashSet<Object>();
	private final Set<Class<?>> empty = new HashSet<Class<?>>();

	public RestApplication() {
		empty.add(RestInscricao.class);
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