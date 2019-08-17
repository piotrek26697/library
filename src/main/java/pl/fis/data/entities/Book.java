package pl.fis.data.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Book
{
	@Id
	@GeneratedValue
	@Column(name = "book_id")
	private long id;

	@OneToMany(mappedBy = "book")
	private List<BookHire> rentHistory;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;

	@NotNull
	@ManyToMany
	@JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;

	@NotEmpty
	@Column(nullable = false)
	private String title;

	private LocalDate publishYear;
	
	@Column(nullable = false)
	private boolean available; // not the same as 'currently not borrowed'!

	public boolean isAvailable()
	{
		return available;
	}

	public void setAvailable(boolean available)
	{
		this.available = available;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public LocalDate getPublishYear()
	{
		return publishYear;
	}

	public void setPublishYear(LocalDate publishYear)
	{
		this.publishYear = publishYear;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public List<BookHire> getRentHistory()
	{
		return rentHistory;
	}

	public void setRentHistory(List<BookHire> rentHistory)
	{
		this.rentHistory = rentHistory;
	}

	public Author getAuthor()
	{
		return author;
	}

	public void setAuthor(Author author)
	{
		this.author = author;
	}

	public List<Category> getCategories()
	{
		return categories;
	}

	public void setCategories(List<Category> categories)
	{
		this.categories = categories;
	}

}
