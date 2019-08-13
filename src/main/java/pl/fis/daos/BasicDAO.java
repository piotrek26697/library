package pl.fis.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.fis.data.entities.Author;
import pl.fis.data.entities.Customer;

@Stateless
public class BasicDAO<T>
{
	@PersistenceContext(name = "postgres-pu")
	private EntityManager em;

	public void addObject(T obj)
	{
		em.persist(obj);
	}

	public List<T> getObjects(String name)
	{
		Query query = em.createQuery("select c from " + name + " c");
		@SuppressWarnings("unchecked")
		List<T> objList = query.getResultList();
		return objList;
	}

	public T getObject(Class<T> clazz, long id)
	{
		T result = em.find(clazz, id);
		if (result != null && result instanceof Customer)
			((Customer) result).getHireHistory().size();
		else if (result != null && result instanceof Author)
			((Author) result).getBookList().size();
		return result;
	}

	public void updateObject(T obj)
	{
		em.merge(obj);
	}
}
