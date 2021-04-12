package cat.marcsicr.gifty.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import cat.marcsicr.gifty.filter.Secured;
import cat.marcsicr.pojo.request.LoginDAO;
import cat.marcsicr.pojo.request.RegisterDAO;


@RequestScoped
public interface IUserServiceREST {

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateUser(LoginDAO dao);
	
	
	@HEAD
	@Path("/exists/{username}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response existsUser(@PathParam("username") String username);
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response hello();
	
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerUser(RegisterDAO dao);
	
	@POST
	@Path("/favorites/{idGif}")
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addGifToFavorites(@Context SecurityContext securityContext, @PathParam("idGif") String idGif);
	
	@DELETE
	@Path("/favorites/{idGif}")
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeGifFromFavorites(@Context SecurityContext securityContext, @PathParam("idGif") String idGif);
	
	@GET
	@Path("/favorites")
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFavorites(@Context SecurityContext securityContext);
	
}
