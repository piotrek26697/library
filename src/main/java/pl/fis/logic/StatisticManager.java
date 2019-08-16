package pl.fis.logic;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.fis.data.entities.Book;
import pl.fis.data.entities.BookHire;

public class StatisticManager
{
	public static Map<Book, Integer> countBooks(List<BookHire> input, LocalDate fromDate, LocalDate toDate)
	{
		Map<Book, Integer> results = new HashMap<>();

		input.stream().filter(a -> {
			if (a.getRentDate().compareTo(fromDate) > 0 && a.getRentDate().compareTo(toDate) < 0)
				return true;
			else
				return false;
		}).forEach(a -> {
			if (results.containsKey(a.getBook()))
			{
				results.put(a.getBook(), results.get(a.getBook()) + 1);
			} else
				results.put(a.getBook(), 1);
		});
		return results;
	}
}
