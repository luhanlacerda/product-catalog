package lacerda.luhan.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import lacerda.luhan.entity.Unidade;
import lacerda.luhan.exception.ProductInvalidException;

public interface UnidadeService {

	public Unidade findById(Long id);

	public List<Unidade> listAll();

	public void create(Unidade entity)
			throws InvalidKeySpecException, NoSuchAlgorithmException, ProductInvalidException;

	public void update(Unidade entity) throws ProductInvalidException;

	public void update(List<Unidade> entities);

	public void delete(Unidade entity);
}
