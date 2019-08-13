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
import pl.fis.data.entities.Author;
import pl.fis.errors.ResourceNotFoundException;

@Path("/authors")
public class AuthorEndpoint
{
	@Inject
	private BasicDAO<Author> authorManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAuthors()
	{
		List<Author> authors = authorManager.getObjects(Author.class.getName());
		return Response.ok(authors).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAuthor(Author author)
	{
		authorManager.addObject(author);
		return Response.status(Status.NO_CONTENT).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getAuthor(@PathParam("id") long id)
	{
		Author author = authorManager.getObject(Author.class, id);
		if (author == null)
			throw new ResourceNotFoundException("author");
		return Response.ok(author).build();
	}
}
