package br.com.ideiasportsgroup.ejb.domain;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ideiasportsgroup.ejb.model.Pessoa;
import br.com.ideiasportsgroup.ejb.persistence.dao.PessoaDao;
import br.com.ideiasportsgroup.ejb.persistence.genericDaoJPA.exceptions.ExceptionDaoJpaEjb;

@Stateless
public class PessoaService {

	@EJB
	PessoaDao pessoaDao;

	public void cadastrar(Pessoa pessoa) throws ExceptionDaoJpaEjb {
		this.pessoaDao.persist(pessoa);
	}

	public Pessoa obterPorNome(String nome) throws ExceptionDaoJpaEjb {
		return this.pessoaDao.getByNome(nome);
	}

	public List<Pessoa> obterPorNomeOrdenandoPorAsc() {
		return this.pessoaDao.getAllOrderAscBy("nome");
	}

}
