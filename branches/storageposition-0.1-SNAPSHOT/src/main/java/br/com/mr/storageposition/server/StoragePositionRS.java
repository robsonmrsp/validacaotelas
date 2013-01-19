package br.com.mr.storageposition.server;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.mr.storageposition.server.facade.PositionsService;

@Path("/storage")
public class StoragePositionRS {

	@Inject
	PositionsService positionsService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sample")
	public Response getSample() {

		JsonPosition entity = new JsonPosition();
		entity.setDeviceId("ROBSON_MOBILE_001");
		entity.setLatitude(0.0);
		entity.setLongitude(0.0);
		entity.setAccuracy(0f);
		entity.setSpeed(0f);
		entity.setAltitude(0d);

		return Response.ok().entity(entity).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/samples")
	public Response getSamples() {
		List<JsonPosition> jsonPositions = new ArrayList<JsonPosition>();
		JsonPosition entity = new JsonPosition();
		entity.setDeviceId("ROBSON_MOBILE_001");
		entity.setLatitude(0.0);
		entity.setLongitude(0.0);
		entity.setAccuracy(0f);
		entity.setSpeed(0f);
		entity.setAltitude(0d);
		jsonPositions.add(entity);

		JsonPosition entity2 = new JsonPosition();
		entity2.setDeviceId("ROBSON_MOBILE_002");
		entity2.setLatitude(0.0);
		entity2.setLongitude(1.0);
		entity2.setAccuracy(0f);
		entity2.setSpeed(0f);
		entity2.setAltitude(0d);
		jsonPositions.add(entity2);

		JsonPosition entity3 = new JsonPosition();
		entity3.setDeviceId("ROBSON_MOBILE_003");
		entity3.setLatitude(1.0);
		entity3.setLongitude(0.0);
		entity3.setAccuracy(0f);
		entity3.setSpeed(0f);
		entity3.setAltitude(0d);
		jsonPositions.add(entity3);

		return Response.ok().entity(jsonPositions).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/position")
	public Response storagePosition(JsonPosition position) {
		positionsService.save(position);
		return Response.ok().entity(Boolean.TRUE).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/positions")
	public Response storagePositions(List<JsonPosition> positions) {
		Boolean response = positionsService.save(positions);

		return Response.ok().entity(response).build();
	}
}
