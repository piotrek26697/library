package pl.fis.data.resources;

import java.time.LocalDate;

import pl.fis.data.entities.Book;

public class BookResource extends Resource
{
	private long id;

	private String title;

	private LocalDate publishYear;

	public BookResource(Book book)
	{
		super();
		id = book.getId();
		title = book.getTitle();
		publishYear = book.getPublishYear();
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
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

}
