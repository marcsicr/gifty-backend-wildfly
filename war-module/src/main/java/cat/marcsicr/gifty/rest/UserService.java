package cat.marcsicr.gifty.rest;



 

import java.util.Set;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;



import javax.ws.rs.core.Response.ResponseBuilder;

import cat.marcsicr.gifty.ejb.IUserLocal;
import cat.marcsicr.gifty.jwt.GiftyJWT;
import cat.marcsicr.pojo.request.LoginDAO;
import cat.marcsicr.pojo.request.RegisterDAO;
import cat.marcsicr.pojo.response.APIResponse;
import cat.marcsicr.pojo.response.FavoritesDataResponse;
import cat.marcsicr.pojo.response.LoginDataResponse;


@Path("user")
public class UserService implements IUserServiceREST{

	@EJB(beanInterface=IUserLocal.class)
	private IUserLocal userEJB;
	
	
	@Override
	public Response hello() {
		return Response.ok("{\"info\":\"UserService ENDPOINT\"}").build();
	}

	
	@Override
	public Response authenticateUser(LoginDAO dao) {
		
		try {
			//Authenticate the user using the credentials provided
			authenticate(dao.getUser(),dao.getPwd());
			
			//Issue a token for the user
			String token = GiftyJWT.issueToken(dao.getUser());
			
			LoginDataResponse data = new LoginDataResponse(dao.getUser(),token);
			APIResponse response = new APIResponse(false, null, data);
			return Response.ok(response).build();
					
		}catch(Exception e) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	
	}
	
	
	@Override
	public Response existsUser(String username) {
		
		boolean exists = userEJB.existsUser(username);
		if(exists) {
			return Response.ok().build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	
	}
	
	@Override
	public Response registerUser(RegisterDAO dao) {
	
		try {
			userEJB.createUser(dao.getUsername(), dao.getPassword());
			return Response.ok(new APIResponse(false,"",null)).build();
		}catch(Exception e) {
			return Response.ok(new APIResponse(true,e.getMessage(),null)).build();
		}	
	}
	
	
	
	@Override
	public Response addGifToFavorites(SecurityContext securityContext, String idGif) {
		
		ResponseBuilder builder;
		
		if(userEJB.addGifLike(securityContext.getUserPrincipal().getName(), idGif))
			builder = Response.ok(new APIResponse(false, "", null));
		else
			builder = Response.ok(new APIResponse(true,"User dosen't exist",null));
		
		return builder.build();
	}
	
	


	@Override
	public Response getFavorites(SecurityContext securityContext) {
		
		Set<String> gifs = userEJB.getJPALikes(securityContext.getUserPrincipal().getName());
		
		FavoritesDataResponse data = new FavoritesDataResponse(gifs);
		APIResponse response = new APIResponse(false,"",data);		
		return Response.ok(response).build();
	}
	
	


	private void authenticate(String username, String pwd) throws Exception {	
		if(!userEJB.authenticate(username, pwd))
			throw new Exception("authentication failed");
	}


	@Override
	public Response removeGifFromFavorites(SecurityContext securityContext, String idGif) {
		
		userEJB.removeGifLike(securityContext.getUserPrincipal().getName(), idGif);
		return Response.status(Response.Status.NO_CONTENT).build();
		
	}

	
}
