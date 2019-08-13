package pl.fis.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.fis.daos.qualifiers.CategoryImp;
import pl.fis.data.Category;

@CategoryImp
@Stateless
public class CategoryDAO implements ObjectDAO<Category>
{
	@PersistenceContext
	private EntityManager em;

	@Override
	public void addObject(Category category)
	{
		em.persist(category);
	}

	@Override
	public List<Category> getObjects()
	{
		Query query = em.createQuery("select c from Category c");
		@SuppressWarnings("unchecked")
		List<Category> categoryList = query.getResultList();
		return categoryList;
	}

	@Override
	public Category getObject(long id)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
