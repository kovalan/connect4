package com.games.demo.connect4.resource;

import java.util.concurrent.TimeUnit;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.ManagedAsync;

import com.games.demo.connect4.exception.Connect4NotFoundException;
import com.games.demo.connect4.model.Connect4Model;
import com.games.demo.connect4.model.NewGameModel;
import com.games.demo.connect4.service.Connect4ServiceImpl;
import com.games.demo.connect4.service.IConnect4Service;

@Path("/api/games")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class Connect4Resource {
	
	public static int REQUEST_TIME_OUT = 20; // Default
	
	private IConnect4Service connect4Service = new Connect4ServiceImpl();
	
	/**
	 * Example - http://localhost:8080/api/games/new
	 * @param newGameModel
	 * @param uriInfo
	 * @param asyncResponse
	 */
	@POST
	@Path("/new")
	@ManagedAsync
	public void startNewGame(@Valid final NewGameModel newGameModel,
			@Context UriInfo uriInfo,
			@Suspended final AsyncResponse asyncResponse) {
		
		setResponseTimeOut(asyncResponse);
		
		Connect4Model connect4Model = connect4Service.createNewGame(newGameModel);
		String gameUri = constructGameURI(uriInfo, connect4Model, ""+newGameModel.getPlayer1Id());
		connect4Model.setGameLink(gameUri);
		
		asyncResponse.resume(connect4Model);
	}
	
	/**
	 * Example - http://localhost:8080/api/games/1/player/1?insert_in_column=3
	 * @param gameId
	 * @param playerId
	 * @param colNum
	 * @param uriInfo
	 * @param asyncResponse
	 * @throws Connect4NotFoundException
	 */
	@PUT
	@Path("/{gameId}/player/{playerId}")
	@ManagedAsync
	public void play(@PathParam("gameId") String gameId,
			@PathParam("playerId") int playerId,
			@QueryParam("insert_in_column") int colNum,
			@Context UriInfo uriInfo,
			@Suspended final AsyncResponse asyncResponse) throws Connect4NotFoundException{
		
		setResponseTimeOut(asyncResponse);
		
		Connect4Model connect4Model = connect4Service.playGame(gameId, colNum, playerId);
		
		String gameUri = "";
		if(connect4Model.isGameOver()) {
			gameUri = constructGameStatusURI(uriInfo, connect4Model);
		} else {
			gameUri = constructGameURI(uriInfo, connect4Model, ""+connect4Model.getNextPlayerId(playerId));
		}
		connect4Model.setGameLink(gameUri);
		
		asyncResponse.resume(connect4Model);
		
	}
	
	/**
	 * Example - http://localhost:8080/api/games/1
	 * @param gameId
	 * @param uriInfo
	 * @param asyncResponse
	 * @throws Connect4NotFoundException
	 */
	@GET
	@Path("/{gameId}")
	@ManagedAsync
	public void viewGameStatus(@PathParam("gameId") String gameId,
			@Context UriInfo uriInfo,
			@Suspended final AsyncResponse asyncResponse) throws Connect4NotFoundException{
		
		setResponseTimeOut(asyncResponse);
		Connect4Model connect4Model = connect4Service.viewGame(gameId);
		if(connect4Model==null) {
			asyncResponse.resume(Response.status(Response.Status.NOT_FOUND));
			return;
		}
		asyncResponse.resume(connect4Model);
	}
	
	private String constructGameURI(final UriInfo uriInfo, final Connect4Model connect4Model, final String playerId) {
		String gameUri = uriInfo.getBaseUriBuilder()
				.path(Connect4Resource.class)
				.path(connect4Model.getGameId())
				.path("player")
				.path(playerId)
				.build()
				.toString();
		
		return gameUri;
	}
	
	private String constructGameStatusURI(final UriInfo uriInfo, final Connect4Model connect4Model) {
		String gameUri = uriInfo.getBaseUriBuilder()
				.path(Connect4Resource.class)
				.path(connect4Model.getGameId())
				.build()
				.toString();
		
		return gameUri;
	}
	
	private static void setResponseTimeOut(final AsyncResponse asyncResponse) {
		asyncResponse.setTimeoutHandler(new TimeoutHandler() {
			public void handleTimeout(AsyncResponse asyncResponse) {
				asyncResponse.resume(Response.status(Response.Status.SERVICE_UNAVAILABLE)
	                    .entity("Operation time out.").build());
			}
	      
	    });
	    asyncResponse.setTimeout(REQUEST_TIME_OUT, TimeUnit.SECONDS);
	}
	
}
