package pl.fis.data;

import java.time.LocalDate;
import java.util.List;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Book
{
	@Id
	@GeneratedValue
	@Column(name = "book_id")
	private long id;

	@JsonbTransient
	@OneToMany(mappedBy = "book")
	private List<BookHire> rentHistory;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;

	@JsonbTransient
	@ManyToMany
	@JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;

	private String title;

	private LocalDate publishYear;

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
