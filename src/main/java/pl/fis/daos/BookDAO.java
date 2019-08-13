package pl.fis.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.fis.daos.qualifiers.BookImp;
import pl.fis.data.Book;

@Stateless
@BookImp
public class BookDAO implements ObjectDAO<Book>
{
	@PersistenceContext
	private EntityManager em;

	@Override
	public void addObject(Book book)
	{
		em.persist(book);
	}

	@Override
	public List<Book> getObjects()
	{
		Query query = em.createQuery("select b from Book b");
		@SuppressWarnings("unchecked")
		List<Book> bookList = query.getResultList();
		return bookList;
	}

	@Override
	public Book getObject(long id)
	{
		Book book = em.find(Book.class, id);
		return book;
	}
}
