package lacerda.luhan.service;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import lacerda.luhan.dao.ProdutoDAO;
import lacerda.luhan.entity.Produto;
import lacerda.luhan.exception.ProductInvalidException;

@Stateless
public class ProdutoServiceImpl implements ProdutoService, Serializable {

	@Inject
	private ProdutoDAO produtoDAO;

	@Override
	public Produto findById(String id) {
		return produtoDAO.findById(id);
	}

	@Override
	public List<Produto> listAll() {
		return produtoDAO.listAll();
	}

	@Override
	public void create(Produto entity)
			throws InvalidKeySpecException, NoSuchAlgorithmException, ProductInvalidException {
		if (entity != null)
			produtoDAO.create(entity);
		else
			throw new ProductInvalidException();

	}

	@Override
	public void update(Produto entity) throws ProductInvalidException {
		if (entity != null)
			produtoDAO.update(entity);
		else
			throw new ProductInvalidException();

	}

	@Override
	public void update(List<Produto> entities) {
		for (Produto entity : entities) {
			produtoDAO.update(entity);
		}
	}

	@Override
	public void delete(Produto entity) {
		entity.setDelecao(true);
		produtoDAO.update(entity);
	}

}
