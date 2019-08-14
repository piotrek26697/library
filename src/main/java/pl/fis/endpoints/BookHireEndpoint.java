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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import pl.fis.daos.BasicDAO;
import pl.fis.data.entities.BookHire;
import pl.fis.data.resources.BookHireResource;
import pl.fis.errors.ResourceNotFoundException;

@Path("/book_hires")
public class BookHireEndpoint
{
	@Inject
	private BasicDAO<BookHire> hireManager;
	
	@Context
	private UriInfo uriInfo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBookHires()
	{
		List<BookHire> bookHireList = hireManager.getObjects(BookHire.class.getName());
		List<BookHireResource> resultList = new ArrayList<>();
		for(BookHire bookHire : bookHireList)
		{
			BookHireResource bookHireResource = new BookHireResource(bookHire);
			UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getBaseUri()).path(BookHireEndpoint.class)
					.path(Long.toString(bookHire.getId()));
			Link bookHireLink = Link.fromUriBuilder(uriBuilder).rel("self").type("GET").build();
			bookHireResource.getLinks().add(bookHireLink);
			resultList.add(bookHireResource);
		}
		return Response.ok(resultList).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addBookHire(@Valid BookHire bookHire)
	{
		bookHire.setEmailSent(false);
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
		
		BookHireResource bookHireResource = new BookHireResource(bookHire);
		UriBuilder uriBuilderBook = UriBuilder.fromUri(uriInfo.getBaseUri()).path(BookEndpoint.class)
				.path(Long.toString(bookHire.getBook().getId()));
		Link bookLink = Link.fromUriBuilder(uriBuilderBook).rel("book").type("GET").build();
		bookHireResource.getLinks().add(bookLink);
		
		UriBuilder uriBuilderCustomer = UriBuilder.fromUri(uriInfo.getBaseUri()).path(CustomerEndpoint.class)
				.path(Long.toString(bookHire.getCustomer().getId()));
		Link customerLink = Link.fromUriBuilder(uriBuilderCustomer).rel("customer").type("GET").build();
		bookHireResource.getLinks().add(customerLink);
		
		return Response.ok(bookHireResource).build();
	}
}
