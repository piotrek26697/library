package pl.fis.endpoints;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import pl.fis.daos.BasicDAO;
import pl.fis.data.entities.Config;

@Path("/config")
public class ConfigEndpoint
{
	@Inject
	private BasicDAO<Config> configManager;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getConfig()
	{
		List<Config> config = configManager.getObjects(Config.class.getName());
		int daysToRead = 0;
		if(config!=null && config.size() > 0)
			daysToRead= config.get(0).getDaysToRead();
		return Response.ok(daysToRead).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response setConfig(@Valid Config newConfig)
	{
		List<Config> config = configManager.getObjects(Config.class.getName());
		
		if(config!=null && config.size() > 0)
		{
			config.get(0).setDaysToRead(newConfig.getDaysToRead());
			configManager.updateObject(config.get(0));
		}else
		{
			configManager.addObject(newConfig);
		}
		return Response.status(Status.NO_CONTENT).build();
	}
}
