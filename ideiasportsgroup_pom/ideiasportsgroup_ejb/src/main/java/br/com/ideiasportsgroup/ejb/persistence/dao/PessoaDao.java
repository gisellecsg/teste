package br.com.ideiasportsgroup.ejb.persistence.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import br.com.ideiasportsgroup.ejb.model.Pessoa;
import br.com.ideiasportsgroup.ejb.persistence.genericDaoJPA.GenericDaoJpaEjb;
import br.com.ideiasportsgroup.ejb.persistence.genericDaoJPA.exceptions.ExceptionDaoJpaEjb;

@Stateless
public class PessoaDao extends GenericDaoJpaEjb<Pessoa> {

	public PessoaDao() {
		super(Pessoa.class);
	}

	public void persist(Pessoa pessoa) throws ExceptionDaoJpaEjb {
		super.persist(pessoa);
	}

	public List<Pessoa> getAllOrderAscBy(String atributo) {
		return super.getAllOrderAscBy(atributo);
	}

	public Pessoa getByNome(String nome) throws ExceptionDaoJpaEjb {

		final String queryString = "SELECT * FROM Pessoa p WHERE p.nome = :parametro1";

		@SuppressWarnings("serial")
		final Map<String, Object> parametersMap = new HashMap<String, Object>(1) {
			{
				put("parametro1", nome);
			}
		};

		return super.getSingleResult(queryString, Collections.unmodifiableMap(parametersMap));

	}

}
