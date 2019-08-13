package pl.fis.endpoints;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import pl.fis.daos.BasicDAO;
import pl.fis.data.entities.BookHire;

@Path("/book_hires")
public class BookHireEndpoint
{
	@Inject
	private BasicDAO<BookHire> hireManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBookHires()
	{
		// List
		return null;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBookHire(BookHire bookHire)
	{
		hireManager.addObject(bookHire);
		return Response.status(Status.NO_CONTENT).build();
	}
}
