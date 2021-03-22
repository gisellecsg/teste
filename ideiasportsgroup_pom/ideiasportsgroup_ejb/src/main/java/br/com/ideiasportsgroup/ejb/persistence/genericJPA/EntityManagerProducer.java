package br.com.ideiasportsgroup.ejb.persistence.genericJPA;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Named
@ApplicationScoped
public class EntityManagerProducer {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
	}

	public void close(@Disposes EntityManager entityManager) throws Exception {
		entityManager.close();
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

}