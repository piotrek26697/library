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
import pl.fis.data.entities.Category;
import pl.fis.data.resources.CategoryResource;
import pl.fis.errors.ResourceNotFoundException;

@Path("/categories")
public class CategoryEndpoint
{
	@Inject
	private BasicDAO<Category> categoryManager;
	
	@Context
	private UriInfo uriInfo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategories()
	{
		List<Category> categories = categoryManager.getObjects(Category.class.getName());
		List<CategoryResource> results = new ArrayList<>();
		for(Category category : categories)
		{
			CategoryResource categoryResource = new CategoryResource(category);
			UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getBaseUri()).path(CategoryEndpoint.class)
					.path(Long.toString(category.getId()));
			Link categoryLink = Link.fromUriBuilder(uriBuilder).rel("self").type("GET").build();
			categoryResource.getLinks().add(categoryLink);
			results.add(categoryResource);
		}
		return Response.ok(results).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCategory(@Valid Category category)
	{
		categoryManager.addObject(category);
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getCategory(@PathParam("id") long id)
	{
		Category category = categoryManager.getObject(Category.class, id);
		if(category == null)
			throw new ResourceNotFoundException("category");
		
		CategoryResource categoryResource = new CategoryResource(category);
		for(Book book : category.getBookList())
		{
			UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getBaseUri()).path(BookEndpoint.class)
					.path(Long.toString(book.getId()));
			Link bookLink = Link.fromUriBuilder(uriBuilder).rel("book").type("GET").build();
			categoryResource.getLinks().add(bookLink);
		}
		
		return Response.ok(categoryResource).build();
		
	}
}
