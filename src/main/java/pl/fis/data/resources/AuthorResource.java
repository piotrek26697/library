package pl.fis.data.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

import pl.fis.data.entities.Author;

public class AuthorResource
{
	private long id;

	private String name;

	private String lastName;
	
	private List<Link> links;

	public AuthorResource(Author author)
	{
		id = author.getId();
		name = author.getName();
		lastName = author.getLastName();
		links = new ArrayList<>();
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

	public List<Link> getLinks()
	{
		return links;
	}

	public void setLinks(List<Link> links)
	{
		this.links = links;
	}
	
	
}
