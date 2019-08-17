package pl.fis.endpoints;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import pl.fis.daos.BasicDAO;
import pl.fis.data.entities.BookHire;
import pl.fis.data.entities.Customer;
import pl.fis.data.resources.CustomerResource;
import pl.fis.errors.ResourceNotFoundException;

@Path("/customers")
public class CustomerEndpoint
{
	@Inject
	private BasicDAO<Customer> customerManager;

	@Context
	private UriInfo uriInfo;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomers()
	{
		List<Customer> customerList = customerManager.getObjects(Customer.class.getName());
		List<CustomerResource> resultList = new ArrayList<>();

		for (Customer customer : customerList)
		{
			CustomerResource customerResource = new CustomerResource(customer);

			UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getBaseUri()).path(CustomerEndpoint.class)
					.path(Long.toString(customer.getId()));
			Link customerLink = Link.fromUriBuilder(uriBuilder).rel("self").type("GET").build();
			customerResource.getLinks().add(customerLink);
			resultList.add(customerResource);
		}

		return Response.ok(resultList).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCustomer(@Valid Customer customer)
	{
		customer.setActive(true);
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

		CustomerResource customerResource = new CustomerResource(customer);

		for (BookHire bookHire : customer.getHireHistory())
		{
			UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getBaseUri()).path(BookHireEndpoint.class)
					.path(Long.toString(bookHire.getId()));
			Link bookHireLink = Link.fromUriBuilder(uriBuilder).rel("bookRent").type("GET").build();
			customerResource.getLinks().add(bookHireLink);
		}

		return Response.ok(customerResource).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomer(@Valid Customer customer)
	{
		customerManager.updateObject(customer);
		return Response.status(Status.NO_CONTENT).build();
	}
}
