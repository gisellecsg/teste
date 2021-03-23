package br.com.ideiasportsgroup.web.restfull.rest.beans;

import javax.ws.rs.core.Response;

import br.com.ideiasportsgroup.ejb.persistence.genericDaoJPA.exceptions.ExceptionDaoJpaEjb;

public interface IPessoaRest {

	Response cadastrar() throws ExceptionDaoJpaEjb;

}