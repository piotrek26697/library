package pl.fis.data.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

public abstract class Resource
{
	private List<Link> links;

	public Resource()
	{
		links = new ArrayList<>();
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
