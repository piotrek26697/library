package pl.fis.data.resources;

import java.time.LocalDate;

public class BookHireStatistic extends Resource
{
	private String title;
	private LocalDate publishYear;
	private int numberOfOccurences;

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

	public int getNumberOfOccurences()
	{
		return numberOfOccurences;
	}

	public void setNumberOfOccurences(int numberOfOccurences)
	{
		this.numberOfOccurences = numberOfOccurences;
	}

}
