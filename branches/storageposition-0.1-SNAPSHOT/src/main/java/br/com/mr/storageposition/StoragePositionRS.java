package br.com.mr.storageposition;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.mr.storageposition.facade.PositionsService;

@Path("/storage")
public class StoragePositionRS {

	@Inject
	PositionsService positionsService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sample")
	public Response getSample() {
		JsonPosition entity = new JsonPosition();
		entity.setDevideId("ROBSON_MOBILE_001");
		entity.setLatitude(0.0);
		entity.setLongitude(0.0);
		return Response.ok().entity(entity).build(); 
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/position")
	public void storagePosition(JsonPosition position) {
		positionsService.save(position);
	}
}
