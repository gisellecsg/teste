package br.com.ideiasportsgroup.ejb.persistence.genericDaoJPA;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.ideiasportsgroup.ejb.persistence.enumeration.ErroEnumPersistence;

public abstract class GenericDaoJpaEjb<T> {

	private Class<T> entityClass;

	@Inject
	private EntityManager entityManager;

	private final Logger logger = LogManager.getLogger(this.getClass());

	public GenericDaoJpaEjb(Class<T> persistentEntityClass) {
		this.entityClass = persistentEntityClass;
	}

	public GenericDaoJpaEjb() {
	}

	protected void persist(T objeto) throws ExceptionDaoJpaEjb {

		try {
			this.entityManager.joinTransaction();
			this.entityManager.persist(objeto);
		} catch (PersistenceException t) {
			findMessagePersistenceException(t);
		}

		this.tryFlushEntity();
	}

	protected void persist(T[] objeto) throws ExceptionDaoJpaEjb {

		try {
			this.entityManager.joinTransaction();
			if (objeto instanceof Object[]) {
				for (Object objRemover : objeto) {
					this.entityManager.persist(objRemover);
				}
			}
		} catch (PersistenceException t) {
			findMessagePersistenceException(t);
		}

		this.tryFlushEntity();
	}

	protected void merge(T objeto) throws ExceptionDaoJpaEjb {

		this.entityManager.joinTransaction();

		try {
			this.entityManager.merge(objeto);
		} catch (PersistenceException t) {
			findMessagePersistenceException(t);
		}

		this.tryFlushEntity();

	}

	protected int merge(String queryString, Map<String, Object> parameters) throws ExceptionDaoJpaEjb {

		this.entityManager.joinTransaction();

		Query query = this.entityManager.createQuery(queryString);

		if (parameters != null && !parameters.isEmpty()) {
			this.populateQueryParameters(query, parameters);
		}

		return query.executeUpdate();
	}

	protected void remove(Object id) throws ExceptionDaoJpaEjb {

		this.entityManager.joinTransaction();
		T entityToBeRemoved;
		try {
			entityToBeRemoved = this.entityManager.getReference(this.entityClass, id);
		} catch (EntityNotFoundException e) {
			this.logger.error(ErroEnumPersistence.ENTITY_NOT_FOUND.toString(), e);
			throw new ExceptionDaoJpaEjb(String.valueOf(ErroEnumPersistence.ENTITY_NOT_FOUND.getCodigo()) + "; ", e);
		}

		this.entityManager.remove(entityToBeRemoved);
		this.tryFlushEntity();
	}

	protected T find(int id) {
		this.entityManager.joinTransaction();
		return this.entityManager.find(this.entityClass, id);
	}

	@SuppressWarnings("unchecked")
	protected T getSingleResult(String queryString, Map<String, Object> parameters) throws ExceptionDaoJpaEjb {

		this.entityManager.joinTransaction();

		Query query = this.entityManager.createQuery(queryString);

		if (parameters != null && !parameters.isEmpty()) {
			this.populateQueryParameters(query, parameters);
		}

		try {
			return (T) query.getSingleResult();
		} catch (NoResultException e1) {
			this.logger.error(ErroEnumPersistence.NO_RESULT.toString() + ";" + this.entityClass, e1);
			throw new ExceptionDaoJpaEjb(String.valueOf(ErroEnumPersistence.NO_RESULT.getCodigo()) + ";" + this.entityClass, e1);
		} catch (NonUniqueResultException e2) {
			this.logger.error(ErroEnumPersistence.MORE_THAN_ONE_RESULT.toString() + ";" + this.entityClass, e2);
			throw new ExceptionDaoJpaEjb(
					String.valueOf(ErroEnumPersistence.MORE_THAN_ONE_RESULT.getCodigo()) + ";" + this.entityClass, e2);
		}

	}

	protected Object getSingleResultObject(String queryString, Map<String, Object> parameters)
			throws ExceptionDaoJpaEjb {

		this.entityManager.joinTransaction();

		Query query = this.entityManager.createQuery(queryString);

		if (parameters != null && !parameters.isEmpty()) {
			this.populateQueryParameters(query, parameters);
		}

		try {
			return query.getSingleResult();
		} catch (NoResultException e1) {
			this.logger.error(ErroEnumPersistence.NO_RESULT.toString() + ";" + this.entityClass, e1);
			throw new ExceptionDaoJpaEjb(String.valueOf(ErroEnumPersistence.NO_RESULT.getCodigo()) + ";" + this.entityClass, e1);
		} catch (NonUniqueResultException e2) {
			this.logger.error(ErroEnumPersistence.MORE_THAN_ONE_RESULT.toString() + ";" + this.entityClass, e2);
			throw new ExceptionDaoJpaEjb(
					String.valueOf(ErroEnumPersistence.MORE_THAN_ONE_RESULT.getCodigo()) + ";" + this.entityClass, e2);
		}

	}

	@SuppressWarnings("unchecked")
	protected List<T> resultList(String queryString, Map<String, Object> parameters) {

		this.entityManager.joinTransaction();

		Query query = this.entityManager.createQuery(queryString);

		if (parameters != null && !parameters.isEmpty()) {
			this.populateQueryParameters(query, parameters);
		}

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	protected List<Object> resultListObject(String queryString, Map<String, Object> parameters) {

		this.entityManager.joinTransaction();

		Query query = this.entityManager.createQuery(queryString);

		if (parameters != null && !parameters.isEmpty()) {
			this.populateQueryParameters(query, parameters);
		}

		return query.getResultList();

	}

	protected List<T> getAll() {
		return this.resultList("SELECT e FROM " + this.getEntityName() + " e ", null);
	}

	protected List<T> getAllOrderByAsc(String atributo) {
		return this.resultList("SELECT e FROM " + this.getEntityName() + " e ORDER BY e." + atributo, null);
	}

	protected List<T> getAllOrderByDesc(String atributo) {
		return this.resultList("SELECT e FROM " + this.getEntityName() + " e ORDER BY e." + atributo + " DESC", null);
	}

	protected List<T> getAllDistinctBy(String distinct) {
		return resultList("SELECT DISTINCT " + distinct + " FROM " + this.getEntityName() + " e", null);
	}

	protected List<Object> getAllObjectDistinctBy(String distinct) {
		return resultListObject("SELECT DISTINCT " + distinct + " FROM " + this.getEntityName() + " e", null);
	}

	private String getEntityName() {
		return this.entityClass.getName().substring(this.entityClass.getName().lastIndexOf('.') + 1);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	protected void tryFlushEntity() throws ExceptionDaoJpaEjb {

		try {
			this.entityManager.flush();
		} catch (PersistenceException e) {
			findMessagePersistenceException(e);
		}

	}

	private void populateQueryParameters(Query query, Map<String, Object> parameters) {

		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}

	}

	protected void findMessagePersistenceException(PersistenceException e) throws ExceptionDaoJpaEjb {

		Throwable t = e;
		boolean flag = true;

		while (t != null) {

			if (t.getMessage().startsWith(ErroEnumPersistence.COLUMN_DOES_NOT_ALLOW_NULL.getDescricao())) {

				flag = false;
				String coluna = t.getMessage().substring(42, t.getMessage().indexOf("'", 43));
				this.logger.error(ErroEnumPersistence.COLUMN_DOES_NOT_ALLOW_NULL.toString() + ";" + coluna, e);
				throw new ExceptionDaoJpaEjb(
						String.valueOf(ErroEnumPersistence.COLUMN_DOES_NOT_ALLOW_NULL.getCodigo()) + ";" + coluna, e);

			} else {
				if (t.getMessage().startsWith(ErroEnumPersistence.DUPLICATE_ENTRY.getDescricao())) {

					flag = false;
					this.logger.error(ErroEnumPersistence.DUPLICATE_ENTRY.toString(), e);
					throw new ExceptionDaoJpaEjb(String.valueOf(ErroEnumPersistence.DUPLICATE_ENTRY.getCodigo()) + "; ", e);

				} else {

					if (t.getMessage().startsWith(ErroEnumPersistence.VIOLACAO_UNIQUEKEY.getDescricao())) {

						String mensagem = t.getMessage();
						String campoDuplicado = mensagem.substring(39, mensagem.indexOf("'", 39));
						// String valorDuplicado =
						// mensagem.substring(mensagem.lastIndexOf("("));
						flag = false;
						this.logger.error(ErroEnumPersistence.VIOLACAO_UNIQUEKEY.toString() + ";" + campoDuplicado,
								e);
						throw new ExceptionDaoJpaEjb(
								String.valueOf(ErroEnumPersistence.VIOLACAO_UNIQUEKEY.getCodigo()) + ";" + campoDuplicado, e);

					} else {
						if (t.getMessage().startsWith(ErroEnumPersistence.DELETE_CONFLICTED_REFERENCE_CONSTRAINT.getDescricao())) {

							String mensagem = t.getMessage();
							String tabelaReferencia = mensagem.substring(mensagem.indexOf("\", table \"dbo.") + 14,
									mensagem.length());
							flag = false;
							this.logger.error(
									ErroEnumPersistence.DELETE_CONFLICTED_REFERENCE_CONSTRAINT.toString() + ";" + tabelaReferencia,
									e);
							throw new ExceptionDaoJpaEjb(
									String.valueOf(ErroEnumPersistence.DELETE_CONFLICTED_REFERENCE_CONSTRAINT.getCodigo()) + ";"
											+ tabelaReferencia,
									e);

						} else {

							if (t.getMessage()
									.startsWith(ErroEnumPersistence.INSERT_CONFLICTED_REFERENCE_CONSTRAINT.getDescricao())) {

								String mensagem = t.getMessage();
								String tabelaReferencia = mensagem.substring(mensagem.indexOf("\", table \"dbo.") + 14,
										mensagem.length());
								flag = false;
								this.logger
										.error(ErroEnumPersistence.INSERT_CONFLICTED_REFERENCE_CONSTRAINT.toString() + ";"
												+ tabelaReferencia, e);
								throw new ExceptionDaoJpaEjb(
										String.valueOf(ErroEnumPersistence.INSERT_CONFLICTED_REFERENCE_CONSTRAINT.getCodigo())
												+ ";" + tabelaReferencia,
										e);

							}

						}

					}
				}
				t = t.getCause();
			}
		}

		if (flag) {
			this.logger.error(ErroEnumPersistence.NAO_CONHECIDO_PERSISTENCIA.toString(), e);
			throw new ExceptionDaoJpaEjb(String.valueOf(ErroEnumPersistence.NAO_CONHECIDO_PERSISTENCIA.getCodigo()) + ";"
					+ ErroEnumPersistence.NAO_CONHECIDO_PERSISTENCIA.toString(), e);
		}
	}

}