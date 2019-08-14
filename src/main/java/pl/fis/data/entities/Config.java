package pl.fis.data.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Config
{
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	@Positive
	private int daysToRead;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public int getDaysToRead()
	{
		return daysToRead;
	}

	public void setDaysToRead(int daysToRead)
	{
		this.daysToRead = daysToRead;
	}
	
	
}
