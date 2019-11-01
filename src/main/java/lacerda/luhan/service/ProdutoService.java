package lacerda.luhan.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import lacerda.luhan.entity.Produto;
import lacerda.luhan.exception.ProductInvalidException;

public interface ProdutoService {

	public Produto findById(String id);

	public List<Produto> listAll();

	public void create(Produto entity) throws InvalidKeySpecException, NoSuchAlgorithmException, ProductInvalidException;

	public void update(Produto entity) throws ProductInvalidException;

	public void update(List<Produto> entities);

	public void delete(Produto entity);
}
