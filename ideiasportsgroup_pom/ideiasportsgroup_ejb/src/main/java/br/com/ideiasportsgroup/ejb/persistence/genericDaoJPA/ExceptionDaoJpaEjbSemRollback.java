package br.com.ideiasportsgroup.ejb.persistence.genericDaoJPA;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = false)
public class ExceptionDaoJpaEjbSemRollback extends Exception {

    private static final long serialVersionUID = 1L;

    public ExceptionDaoJpaEjbSemRollback(String msg, Throwable t) {
	super(msg, t);
    }

    public ExceptionDaoJpaEjbSemRollback() {
	super();
    }

    public ExceptionDaoJpaEjbSemRollback(String arg0, Throwable arg1, boolean arg2,
	    boolean arg3) {
	super(arg0, arg1, arg2, arg3);
    }

    public ExceptionDaoJpaEjbSemRollback(Throwable arg0) {
	super(arg0);
    }

    public ExceptionDaoJpaEjbSemRollback(String msg) {
	super(msg);
    }

}