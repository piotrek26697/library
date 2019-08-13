package pl.fis.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.fis.daos.qualifiers.AuthorImp;
import pl.fis.data.Author;

@AuthorImp
@Stateless
public class AuthorDAO implements ObjectDAO<Author>
{
	@PersistenceContext
	private EntityManager em;

	@Override
	public void addObject(Author author)
	{
		em.persist(author);
	}

	@Override
	public List<Author> getObjects()
	{
		Query query = em.createQuery("select a from Author a");
		@SuppressWarnings("unchecked")
		List<Author> authorList = query.getResultList();
		return authorList;
	}

	@Override
	public Author getObject(long id)
	{
		return em.find(Author.class, id);
	}
}
