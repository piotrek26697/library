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
import javax.ws.rs.core.Response.Status;

import pl.fis.daos.BasicDAO;
import pl.fis.data.entities.Category;

@Path("/categories")
public class CategoryEndpoint
{
	@Inject
	private BasicDAO<Category> categoryManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategories()
	{
		List<Category> categories = categoryManager.getObjects(Category.class.getName());
		return Response.ok(categories).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCategory(Category category)
	{
		categoryManager.addObject(category);
		return Response.status(Status.NO_CONTENT).build();
	}
}
