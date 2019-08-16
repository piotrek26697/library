package pl.fis.data.resources;

public class BookHireStatistic 
{
	private BookResource book;
	private int numberOfOccurences;
	public BookResource getBook()
	{
		return book;
	}
	public void setBook(BookResource book)
	{
		this.book = book;
	}
	public int getNumberOfOccurences()
	{
		return numberOfOccurences;
	}
	public void setNumberOfOccurences(int numberOfOccurences)
	{
		this.numberOfOccurences = numberOfOccurences;
	}
	
}
