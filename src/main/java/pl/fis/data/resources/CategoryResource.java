package pl.fis.data.resources;

import pl.fis.data.entities.Category;

public class CategoryResource extends Resource
{
	private long id;

	private String name;

	private String description;

	public CategoryResource(Category category)
	{
		super();
		id = category.getId();
		name = category.getName();
		description = category.getDescription();
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

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

}
