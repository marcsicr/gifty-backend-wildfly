package cat.marcsicr.pojo.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "")
public class LoginDataResponse {
	
	@JsonProperty
	private String username;
	
	@JsonProperty
	private String jwt;
	
		
	@JsonCreator
	public LoginDataResponse(@JsonProperty("username") String username, @JsonProperty("jwt") String jwt) {
		this.jwt = jwt; 
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	
}
