package pl.fis.endpoints;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
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
import pl.fis.data.entities.Book;
import pl.fis.data.entities.BookHire;
import pl.fis.data.entities.Category;
import pl.fis.data.resources.BookResource;
import pl.fis.errors.ResourceNotFoundException;

@Path("/books")
public class BookEndpoint
{
	@Inject
	private BasicDAO<Book> bookManager;
	
	@Context
	private UriInfo uriInfo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBooks()
	{
		List<Book> bookList = bookManager.getObjects(Book.class.getName());
		List<BookResource> results = new ArrayList<>();
		for(Book book : bookList)
		{
			BookResource bookResource = new BookResource(book);
			UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getBaseUri()).path(BookEndpoint.class)
					.path(Long.toString(book.getId()));
			Link bookLink =  Link.fromUriBuilder(uriBuilder).rel("self").type("GET").build();
			bookResource.getLinks().add(bookLink);
			results.add(bookResource);
		}
		
		return Response.ok(results).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBook(@Valid Book book)
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
		
		BookResource bookResource = new BookResource(book);
		UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getBaseUri()).path(AuthorEndpoint.class)
				.path(Long.toString(book.getAuthor().getId()));
		Link authorLink = Link.fromUriBuilder(uriBuilder).rel("author").type("GET").build();
		bookResource.getLinks().add(authorLink);
		
		for(Category category : book.getCategories())
		{
			UriBuilder uriBuilderCategory = UriBuilder.fromUri(uriInfo.getBaseUri()).path(CategoryEndpoint.class)
					.path(Long.toString(category.getId()));
			Link categoryLink = Link.fromUriBuilder(uriBuilderCategory).rel("category").type("GET").build();
			bookResource.getLinks().add(categoryLink);
		}
		for(BookHire bookHire : book.getRentHistory())
		{
			UriBuilder uriBuilderBookHire = UriBuilder.fromUri(uriInfo.getBaseUri()).path(BookHireEndpoint.class)
					.path(Long.toString(bookHire.getId()));
			Link bookHireLink = Link.fromUriBuilder(uriBuilderBookHire).rel("bookRent").type("GET").build();
			bookResource.getLinks().add(bookHireLink);
		}
		
		return Response.ok(bookResource).build();
	}
}
