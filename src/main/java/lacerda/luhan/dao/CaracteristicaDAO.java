package lacerda.luhan.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import lacerda.luhan.entity.Caracteristica;

public class CaracteristicaDAO implements GenericDAO<Caracteristica> {

	@Override
	public Caracteristica findById(Long id) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		Caracteristica caracteristica = entityManager.find(Caracteristica.class, id);
		if (caracteristica == null) {
			throw new EntityNotFoundException("Can't find User for ID " + id);
		}
		entityManager.close();
		return caracteristica;
	}

	@Override
	public Caracteristica findById(String id) {
		return null;
	}

	@Override
	public List<Caracteristica> listAll() {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		List<Caracteristica> caracteristicaList = entityManager
				.createQuery("Select distinct c from Caracteristica c", Caracteristica.class).getResultList();
		entityManager.close();
		return caracteristicaList;
	}

	@Override
	public void create(Caracteristica entity) {
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
	public void update(Caracteristica entity) {
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
	public void update(List<Caracteristica> entities) {
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
	public void delete(Caracteristica entity) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		try {
			Caracteristica caracteristica = entityManager.find(Caracteristica.class, entity.getId());
			entityManager.getTransaction().begin();
			entityManager.remove(caracteristica);
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
