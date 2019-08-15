package pl.fis.data.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Author
{
	@Id
	@GeneratedValue
	@Column(name = "author_id")
	private long id;

	@NotEmpty
	@Column(nullable = false)
	private String name;

	@NotEmpty
	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	@OneToMany(mappedBy = "author")
	private List<Book> bookList;

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

	public List<Book> getBookList()
	{
		return bookList;
	}

	public void setBookList(List<Book> bookList)
	{
		this.bookList = bookList;
	}

}
