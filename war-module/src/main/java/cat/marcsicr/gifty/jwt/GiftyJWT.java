package cat.marcsicr.gifty.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class GiftyJWT {

	 static final String SECRET_STRING = "SECRET";
	 static final  Algorithm JWT_SIGN_ALGORITHM = Algorithm.HMAC256(SECRET_STRING);
	 static final String JWT_TOKEN_ISSUER = "Gifty";
	
	public static DecodedJWT validateToken(String token) throws Exception{
		
		JWTVerifier verifier = JWT.require(JWT_SIGN_ALGORITHM).withIssuer(JWT_TOKEN_ISSUER).build();
		DecodedJWT jwt = verifier.verify(token);
		return jwt;
	}
	
	public static String issueToken(String username) throws Exception {
		
		String token = JWT.create()
				.withIssuer(JWT_TOKEN_ISSUER)
				.withSubject(username).sign(JWT_SIGN_ALGORITHM);
		
		return token;
	}
}
