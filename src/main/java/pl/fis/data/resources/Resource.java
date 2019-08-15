package pl.fis.data.resources;

import java.util.ArrayList;
import java.util.List;

import javax.json.bind.annotation.JsonbTypeSerializer;
import javax.ws.rs.core.Link;

import pl.fis.logic.serializers.LinkListSerializer;

public abstract class Resource
{
	@JsonbTypeSerializer(LinkListSerializer.class)
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
