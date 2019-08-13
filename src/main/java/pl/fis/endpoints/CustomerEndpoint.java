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
import pl.fis.data.Customer;
import pl.fis.errors.ResourceNotFoundException;

@Path("/customers")
public class CustomerEndpoint
{
	@Inject
	private BasicDAO<Customer> customerManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomers()
	{
		List<Customer> customerList = customerManager.getObjects("Customer");
		return Response.ok(customerList).header("Access-Control-Allow-Origin", "*").build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCustomer(Customer customer)
	{
		customerManager.addObject(customer);
		return Response.status(Status.NO_CONTENT).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getCustomer(@PathParam("id") long id)
	{
		Customer customer = customerManager.getObject(Customer.class, id);
		if (customer == null)
			throw new ResourceNotFoundException("customer");
		return Response.ok(customer).build();
	}
}
