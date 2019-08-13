package pl.fis.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.fis.daos.qualifiers.BookHireImp;
import pl.fis.data.BookHire;

@BookHireImp
@Stateless
public class BookHireDAO implements ObjectDAO<BookHire>
{
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void addObject(BookHire bookHire)
	{
		em.persist(bookHire);
	}
	
	@Override
	public List<BookHire> getObjects()
	{
		return null;
	}

	@Override
	public BookHire getObject(long id)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
