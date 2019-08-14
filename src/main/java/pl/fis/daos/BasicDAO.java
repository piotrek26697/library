package pl.fis.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.fis.data.entities.Author;
import pl.fis.data.entities.Book;
import pl.fis.data.entities.Category;
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
		if (result == null)
			return null;

		if (result instanceof Customer)
		{
			if (((Customer) result).getHireHistory() != null)
				((Customer) result).getHireHistory().size();
		} else if (result instanceof Author)
		{
			if (((Author) result).getBookList() != null)
				((Author) result).getBookList().size();
		} else if (result instanceof Book)
		{
			((Book) result).getAuthor().getId();
			((Book) result).getCategories().size();
			if (((Book) result).getRentHistory() != null)
			{
				((Book) result).getRentHistory().size();
			}
		} else if (result instanceof Category)
			if (((Category) result).getBookList() != null)
				((Category) result).getBookList().size();

		return result;
	}

	public void updateObject(T obj)
	{
		em.merge(obj);
	}
}
