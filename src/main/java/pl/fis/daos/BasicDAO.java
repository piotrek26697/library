package pl.fis.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class BasicDAO<T>
{
	@PersistenceContext
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
		return em.find(clazz, id);
	}
	
	public void updateObject(T obj)
	{
		em.merge(obj);
	}
}
