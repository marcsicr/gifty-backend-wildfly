package cat.marcsicr.gifty.filter;

import java.io.IOException;
import java.security.Principal;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.net.HttpHeaders;

import cat.marcsicr.gifty.jwt.GiftyJWT;



@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

	private static final String AUTHENTICATION_SCHEME = "Bearer";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
	

		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		
		if(!isTokenBasedAuthentication(authorizationHeader)) {
			abortWithUnauthorized(requestContext);
			return;
		}
		
	
		//Extract token from the Autherization header
		String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();
		
		
		try {
			
			// Validate token
			DecodedJWT jwt = validateToken(token);
			
			//Create user security context
			createUserSeucirtyContxt(requestContext,jwt.getSubject());
			
			
		}catch (Exception e) {
			abortWithUnauthorized(requestContext);
		}
	}

	
	private boolean isTokenBasedAuthentication(String authorizationHeader) {
		
		//Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // Authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                    .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
	}
	
	private void abortWithUnauthorized(ContainerRequestContext requestContext) {
		
		// Abort the filter chain with a 401 status code
        // The "WWW-Authenticate" is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME)
                        .build());
	}
	
	private DecodedJWT validateToken(String token) throws Exception{
		return GiftyJWT.validateToken(token);	
	}
	
	private void createUserSeucirtyContxt(ContainerRequestContext requestContext, String username) throws Exception {
		
		if(username.equals("")) {
			throw new Exception("Empty username");
		}
		
		final SecurityContext currentSecurityContext = requestContext.getSecurityContext();
		requestContext.setSecurityContext(new SecurityContext() {
			
			@Override
			public boolean isUserInRole(String role) {
				return true;
			}
			
			@Override
			public boolean isSecure() {
				return currentSecurityContext.isSecure();
			}
			
			@Override
			public Principal getUserPrincipal() {

				return () -> username;
			}
			
			@Override
			public String getAuthenticationScheme() {
				return AUTHENTICATION_SCHEME;
			}
		});
	}
}
