package cat.marcsicr.pojo.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("")
public class APIResponse {

	@JsonProperty
	private boolean error;
	
	@JsonProperty
	private String errorMsg;
	
	@JsonProperty
	private Object data;
	
	@JsonCreator
	public APIResponse(boolean error, String errorMsg, Object data) {
		this.error = error; 
		this.errorMsg = errorMsg; 
		this.data = data;
	}
	
}
