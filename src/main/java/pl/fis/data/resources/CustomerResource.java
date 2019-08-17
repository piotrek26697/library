package pl.fis.data.resources;

import pl.fis.data.entities.Customer;

public class CustomerResource extends Resource
{
	private long id;
	private String name;
	private String lastName;
	private String email;
	private boolean active;

	public CustomerResource(Customer customer)
	{
		super();
		id = customer.getId();
		name = customer.getName();
		lastName = customer.getLastName();
		email = customer.getEmail();
		active = customer.isActive();
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
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
