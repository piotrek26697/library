package pl.fis.endpoints;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import pl.fis.daos.BasicDAO;
import pl.fis.data.Book;
import pl.fis.errors.ResourceNotFoundException;

@Path("/books")
public class BookEndpoint
{
	@Inject
	private BasicDAO<Book> bookManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks()
	{
		List<Book> bookList = bookManager.getObjects(Book.class.getName());
		return Response.ok(bookList).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBook(Book book)
	{
		bookManager.addObject(book);
		return Response.status(Status.NO_CONTENT).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getBook(@PathParam("id") long id)
	{
		Book book = bookManager.getObject(Book.class, id);
		if (book == null)
			throw new ResourceNotFoundException("book");
		return Response.ok(book).build();
	}
}
