package pl.fis.endpoints;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pl.fis.daos.BookDAO;
import pl.fis.data.Book;

@Path("/books")
public class BookEndpoint
{
	@Inject
	private BookDAO bookManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks()
	{
		List<Book> bookList = bookManager.getBooks();
		return Response.ok(bookList).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBook(Book book)
	{
		bookManager.addBook(book);
		return Response.ok().build();
	}
}
