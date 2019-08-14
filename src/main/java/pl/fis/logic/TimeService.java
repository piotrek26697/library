package pl.fis.logic;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;
import javax.inject.Inject;

@Singleton
@Startup
public class TimeService
{
	@Inject
	private Notifications notifications;

	@Schedule(hour = "*/12", minute = "*", second = "*", info = "Every 12 hours timer", persistent = false)
	public void automaticallyScheduled(Timer timer)
	{
		notifications.checkOverdueRenting();
	}
}
