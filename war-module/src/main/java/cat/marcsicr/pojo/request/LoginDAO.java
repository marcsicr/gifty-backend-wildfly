package cat.marcsicr.pojo.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "")
public class LoginDAO {
	
	@JsonProperty
	private String user;
	
	@JsonProperty
	private String pwd;
	

	
	@JsonCreator
	public LoginDAO() {}
	
	@JsonCreator
	public LoginDAO(@JsonProperty("username") String user, @JsonProperty("pwd") String pwd) {this.pwd = pwd; this.user = user;}
	

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
}
