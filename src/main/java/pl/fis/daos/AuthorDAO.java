package pl.fis.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.fis.data.Author;

@Stateless
public class AuthorDAO
{
	@PersistenceContext
	private EntityManager em;

	public void addAuthor(Author author)
	{
		em.persist(author);
	}

	public List<Author> getAuthors()
	{
		Query query = em.createQuery("select a from Author a");
		@SuppressWarnings("unchecked")
		List<Author> authorList = query.getResultList();
		return authorList;
	}
}
