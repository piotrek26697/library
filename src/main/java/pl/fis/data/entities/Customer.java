package pl.fis.data.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Customer
{
	@Column(name = "customer_id")
	@Id
	@GeneratedValue
	private long id;

	@NotEmpty
	@Column(nullable = false)
	private String name;
	@NotEmpty
	@Column(nullable = false)
	private String lastName;
	@NotNull
	@Email
	@Column(nullable = false)
	private String email;

	@OneToMany(mappedBy = "customer")
	private List<BookHire> hireHistory;

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

	public List<BookHire> getHireHistory()
	{
		return hireHistory;
	}

	public void setHireHistory(List<BookHire> hireHistory)
	{
		this.hireHistory = hireHistory;
	}

}
