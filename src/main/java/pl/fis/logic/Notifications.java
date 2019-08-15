package pl.fis.logic;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Singleton;
import javax.inject.Inject;

import pl.fis.daos.BasicDAO;
import pl.fis.data.entities.BookHire;
import pl.fis.data.entities.Config;

@Singleton
public class Notifications
{
	@Inject
	private BasicDAO<BookHire> hireManager;

	@Inject
	private BasicDAO<Config> configManager;

	private int daysToRead;

	private void checkConfig()
	{
		if (configManager.getObjects(Config.class.getName()) == null)
			daysToRead = 20;
		else
		{
			daysToRead = configManager.getObjects(Config.class.getName()).get(0).getDaysToRead();
		}
	}

	public void checkOverdueRenting()
	{
		checkConfig();

		List<BookHire> rentList = hireManager.getObjects(BookHire.class.getName());

		List<String> notificationList = rentList.stream()
				.filter(a -> a.getReturnDate() == null)
				.filter(a -> a.isEmailSent() == false)
				.filter(a -> {
					if (LocalDate.now().compareTo(a.getRentDate().plusDays(daysToRead)) > 0)
						return true;
					else
						return false;
				}).map(a -> a.getCustomer().getEmail()).collect(Collectors.toList()); //TODO test
	}
}
