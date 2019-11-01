package lacerda.luhan.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

import lacerda.luhan.entity.Produto;

public class ProdutoDAO implements GenericDAO<Produto> {

	@Override
	public Produto findById(String id) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		Produto produto = entityManager.find(Produto.class, id);
		if (produto == null) {
			throw new EntityNotFoundException("Can't find User for ID " + id);
		}
		entityManager.close();
		return produto;
	}

	@Override
	public List<Produto> listAll() {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		List<Produto> produtoList = entityManager.createQuery("Select distinct p from Produto p", Produto.class)
				.getResultList();
		entityManager.close();
		return produtoList;
	}

	@Override
	public void create(Produto entity) {
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
	public void update(Produto entity) {
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
	public void update(List<Produto> entities) {
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
	public void delete(Produto entity) {
		EntityManager entityManager = new JPAUtil().getEntityManager();
		try {
			Produto produto = entityManager.find(Produto.class, entity.getCodigo());
			entityManager.getTransaction().begin();
			entityManager.remove(produto);
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
	public Produto findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
