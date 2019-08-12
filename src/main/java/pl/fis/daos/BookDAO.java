package pl.fis.daos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.fis.data.Book;

@Stateless
public class BookDAO
{
	@PersistenceContext
	private EntityManager em;

	public void addBook(Book book)
	{
		em.persist(book);
	}
	
	public List<Book> getBooks()
	{
		Query query = em.createQuery("select b from Book b");
		@SuppressWarnings("unchecked")
		List<Book> bookList = query.getResultList();
		return bookList;
	}
}
