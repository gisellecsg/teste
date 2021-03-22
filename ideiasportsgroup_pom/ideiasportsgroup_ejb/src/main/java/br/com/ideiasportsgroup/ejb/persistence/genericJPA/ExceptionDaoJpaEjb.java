package br.com.ideiasportsgroup.ejb.persistence.genericJPA;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class ExceptionDaoJpaEjb extends Exception {

	private static final long serialVersionUID = 1L;

	public ExceptionDaoJpaEjb(String msg, Throwable t) {
		super(msg, t);     
	}

	public ExceptionDaoJpaEjb() {
		super();
	}

	public ExceptionDaoJpaEjb(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ExceptionDaoJpaEjb(Throwable arg0) {
		super(arg0);
	}

	public ExceptionDaoJpaEjb(String msg) {
		super(msg);
	}

}