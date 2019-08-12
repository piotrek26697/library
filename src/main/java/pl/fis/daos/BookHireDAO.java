package pl.fis.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.fis.data.BookHire;

@Stateless
public class BookHireDAO
{
	@PersistenceContext
	private EntityManager em;
	
	public void registerBookHire(BookHire bookHire)
	{
		em.persist(bookHire);
	}
	
	public List<BookHire> getBookHires()
	{
		return null;
	}
}
