package pl.fis.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.fis.data.Category;

@Stateless
public class CategoryDAO
{
	@PersistenceContext
	private EntityManager em;

	public void addCategory(Category category)
	{
		em.persist(category);
	}

	public List<Category> getCategories()
	{
		Query query = em.createQuery("select c from Category c");
		@SuppressWarnings("unchecked")
		List<Category> categoryList = query.getResultList();
		return categoryList;
	}
}
