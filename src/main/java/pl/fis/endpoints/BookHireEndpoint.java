package pl.fis.endpoints;

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
import pl.fis.data.entities.BookHire;
import pl.fis.errors.ResourceNotFoundException;

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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getBookHire(@PathParam("id") long id)
	{
		BookHire bookHire = hireManager.getObject(BookHire.class, id);
		if(bookHire == null)
			throw new ResourceNotFoundException();
		return Response.ok(bookHire).build();
	}
}
