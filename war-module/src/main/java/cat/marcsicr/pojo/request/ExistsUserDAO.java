package cat.marcsicr.pojo.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="")
public class ExistsUserDAO {
	
	@JsonProperty
	private String username;

	
	@JsonCreator
	public ExistsUserDAO(@JsonProperty("username") String username){
		this.username = username;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
