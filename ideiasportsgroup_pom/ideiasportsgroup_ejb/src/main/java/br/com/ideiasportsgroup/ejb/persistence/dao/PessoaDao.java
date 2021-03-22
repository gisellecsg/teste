package br.com.ideiasportsgroup.ejb.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.com.ideiasportsgroup.ejb.model.Pessoa;
import br.com.ideiasportsgroup.ejb.persistence.genericDaoJPA.ExceptionDaoJpaEjb;
import br.com.ideiasportsgroup.ejb.persistence.genericDaoJPA.GenericDaoJpaEjb;

@Stateless
public class PessoaDao extends GenericDaoJpaEjb<Pessoa> {

	public PessoaDao() {
		super(Pessoa.class);
	}
	
	public void persist(Pessoa pessoa) throws ExceptionDaoJpaEjb {
		super.persist(pessoa);
	}

	public List<Pessoa> getAllOrderByAsc(String atributo) {
		return super.getAllOrderByAsc(atributo);
	}

}
