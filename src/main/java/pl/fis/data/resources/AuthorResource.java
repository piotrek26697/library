package pl.fis.data.resources;

import pl.fis.data.entities.Author;

public class AuthorResource extends Resource
{
	private long id;

	private String name;

	private String lastName;

	public AuthorResource(Author author)
	{
		super();
		id = author.getId();
		name = author.getName();
		lastName = author.getLastName();
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

}
