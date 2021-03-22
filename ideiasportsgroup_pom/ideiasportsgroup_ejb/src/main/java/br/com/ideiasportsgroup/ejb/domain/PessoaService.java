package br.com.ideiasportsgroup.ejb.domain;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.ideiasportsgroup.ejb.model.Pessoa;
import br.com.ideiasportsgroup.ejb.persistence.dao.PessoaDao;
import br.com.ideiasportsgroup.ejb.persistence.genericDaoJPA.ExceptionDaoJpaEjb;

@Stateless
public class PessoaService {

	// private final Logger logger = LogManager.getLogger(this.getClass());

	@EJB
	PessoaDao pessoaDao;

	public void cadastrar(Pessoa pessoa) throws ExceptionDaoJpaEjb {
		this.pessoaDao.persist(pessoa);
	}

}
