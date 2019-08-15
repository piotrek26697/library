package pl.fis.logic.serializers;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.bind.serializer.JsonbSerializer;
import javax.json.bind.serializer.SerializationContext;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.core.Link;

public class LinkListSerializer implements JsonbSerializer<List<Link>>
{

	@Override
	public void serialize(List<Link> obj, JsonGenerator generator, SerializationContext ctx)
	{
		JsonArrayBuilder linkListBuilder = Json.createArrayBuilder();
		
		for(Link link : obj)
		{
			JsonObject result = Json.createObjectBuilder()
					.add("href", link.getUri().getPath())
					.add("rel", link.getRel())
					.add("type", link.getType())
					.build();
			linkListBuilder.add(result);
		}
		generator.write(linkListBuilder.build());
	}

}
