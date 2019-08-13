package pl.fis.data.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import pl.fis.data.entities.Customer;

public class CustomerResource
{
	private long id;
	private String name;
	private String lastName;
	private String email;

	private List<Link> linkList;

	public CustomerResource(Customer customer)
	{
		id = customer.getId();
		name = customer.getName();
		lastName = customer.getLastName();
		email = customer.getEmail();
		linkList = new ArrayList<>();
	}

	public List<Link> getLinkList()
	{
		return linkList;
	}

	public void setLinkList(List<Link> linkList)
	{
		this.linkList = linkList;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}
