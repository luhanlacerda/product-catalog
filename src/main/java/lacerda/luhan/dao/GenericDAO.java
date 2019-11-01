package lacerda.luhan.dao;

import java.util.List;

public interface GenericDAO<T> {

	public T findById(Long id);

	public T findById(String id);

	public List<T> listAll();

	public void create(T entity);

	public void update(T entity);

	public void update(List<T> entities);

	public void delete(T entity);

}
