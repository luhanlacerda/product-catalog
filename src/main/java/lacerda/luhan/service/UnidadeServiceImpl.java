package lacerda.luhan.service;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import lacerda.luhan.dao.UnidadeDAO;
import lacerda.luhan.entity.Unidade;
import lacerda.luhan.exception.ProductInvalidException;

@Stateless
public class UnidadeServiceImpl implements UnidadeService, Serializable {

	@Inject
	private UnidadeDAO unidadeDAO;

	@Override
	public Unidade findById(Long id) {
		return unidadeDAO.findById(id);
	}

	@Override
	public List<Unidade> listAll() {
		return unidadeDAO.listAll();
	}

	@Override
	public void create(Unidade entity)
			throws InvalidKeySpecException, NoSuchAlgorithmException, ProductInvalidException {
		if (entity != null)
			unidadeDAO.create(entity);
		else
			throw new ProductInvalidException();

	}

	@Override
	public void update(Unidade entity) throws ProductInvalidException {
		if (entity != null)
			unidadeDAO.update(entity);
		else
			throw new ProductInvalidException();

	}

	@Override
	public void update(List<Unidade> entities) {
		for (Unidade entity : entities) {
			unidadeDAO.update(entity);
		}
	}

	@Override
	public void delete(Unidade entity) {
		unidadeDAO.update(entity);
	}

}
