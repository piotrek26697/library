package pl.fis.logic;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
		List<String> notificationList = new ArrayList<>();
		for (BookHire bookHire : rentList)
		{
			LocalDate expectedReturnDate = bookHire.getRentDate().plusDays(daysToRead);
			if (bookHire.getReturnDate() == null && !bookHire.isEmailSent()
					&& LocalDate.now().compareTo(expectedReturnDate) < 0)
			{
				notificationList.add(bookHire.getCustomer().getEmail());
				bookHire.setEmailSent(true);
				hireManager.updateObject(bookHire);
			}
		}
	}
}
