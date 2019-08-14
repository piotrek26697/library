package pl.fis.data.resources;

import java.time.LocalDate;

import pl.fis.data.entities.BookHire;

public class BookHireResource extends Resource
{
	private long id;
	private LocalDate rentDate;
	private LocalDate returnDate;

	public BookHireResource(BookHire bookHire)
	{
		super();
		id = bookHire.getId();
		rentDate = bookHire.getRentDate();
		returnDate = bookHire.getReturnDate();
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public LocalDate getRentDate()
	{
		return rentDate;
	}

	public void setRentDate(LocalDate rentDate)
	{
		this.rentDate = rentDate;
	}

	public LocalDate getReturnDate()
	{
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate)
	{
		this.returnDate = returnDate;
	}
}
