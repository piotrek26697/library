package pl.fis.data;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book_hire")
public class BookHire
{
	@Id
	@GeneratedValue
	@Column(name = "book_hire_id")
	private long id;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	private LocalDate rentDate;

	private LocalDate returnDate;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Book getBook()
	{
		return book;
	}

	public void setBook(Book book)
	{
		this.book = book;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
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
