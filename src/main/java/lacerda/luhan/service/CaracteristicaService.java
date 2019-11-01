package lacerda.luhan.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import lacerda.luhan.entity.Caracteristica;
import lacerda.luhan.exception.ProductInvalidException;

public interface CaracteristicaService {

	public Caracteristica findById(Long id);

	public List<Caracteristica> listAll();

	public void create(Caracteristica entity)
			throws InvalidKeySpecException, NoSuchAlgorithmException, ProductInvalidException;

	public void update(Caracteristica entity) throws ProductInvalidException;

	public void update(List<Caracteristica> entities);

	public void delete(Caracteristica entity);
}
