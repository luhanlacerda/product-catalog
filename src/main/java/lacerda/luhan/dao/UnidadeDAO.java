package lacerda.luhan.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import lacerda.luhan.entity.Unidade;

public class UnidadeDAO implements GenericDAO<Unidade> {

	@Override
	public Unidade findById(Long id) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		Unidade unidade = entityManager.find(Unidade.class, id);
		if (unidade == null) {
			throw new EntityNotFoundException("Can't find User for ID " + id);
		}
		entityManager.close();
		return unidade;
	}

	@Override
	public Unidade findById(String id) {
		return null;
	}

	@Override
	public List<Unidade> listAll() {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		List<Unidade> unidadeList = entityManager.createQuery("Select distinct u from Unidade u", Unidade.class)
				.getResultList();
		entityManager.close();
		return unidadeList;
	}

	@Override
	public void create(Unidade entity) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			entityManager.close();
		} catch (Exception e) {
			if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}

	@Override
	public void update(Unidade entity) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			entityManager.close();
		} catch (Exception e) {
			if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}

	@Override
	public void update(List<Unidade> entities) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.getTransaction().commit();
			entityManager.close();
		} catch (Exception e) {
			if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}

	@Override
	public void delete(Unidade entity) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		try {
			Unidade unidade = entityManager.find(Unidade.class, entity.getId());
			entityManager.getTransaction().begin();
			entityManager.remove(unidade);
			entityManager.getTransaction().commit();
			entityManager.close();
		} catch (Exception e) {
			if (entityManager.getTransaction() != null && entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
		} finally {
			if (entityManager.isOpen()) {
				entityManager.close();
			}
		}
	}

}
