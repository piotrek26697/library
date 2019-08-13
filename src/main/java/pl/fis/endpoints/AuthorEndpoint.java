package pl.fis.endpoints;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import pl.fis.daos.BasicDAO;
import pl.fis.data.entities.Author;
import pl.fis.data.entities.Book;
import pl.fis.data.resources.AuthorResource;
import pl.fis.errors.ResourceNotFoundException;

@Path("/authors")
public class AuthorEndpoint
{
	@Inject
	private BasicDAO<Author> authorManager;
	
	@Context
	private UriInfo uriInfo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAuthors()
	{
		List<Author> authors = authorManager.getObjects(Author.class.getName());
		List<AuthorResource> resultList = new ArrayList<>();
		for(Author author : authors)
		{
			AuthorResource authorResource = new AuthorResource(author);
			UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getBaseUri()).path(getClass())
					.path(Long.toString(author.getId()));
			Link authorLink = Link.fromUriBuilder(uriBuilder).rel("self").type("GET").build();
			authorResource.getLinks().add(authorLink);
			resultList.add(authorResource);
		}
		return Response.ok(resultList).build();
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
		
		AuthorResource authorResource = new AuthorResource(author);
		
		for(Book book: author.getBookList())
		{
			UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getBaseUri()).path(BookEndpoint.class)
					.path(Long.toString(book.getId()));
			Link bookLink = Link.fromUriBuilder(uriBuilder).rel(book.getTitle()).type("GET").build();
			authorResource.getLinks().add(bookLink);
		}
		
		return Response.ok(authorResource).build();
	}
}
