package cat.marcsicr.pojo.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("favs")
public class FavoritesDataResponse {

	@JsonProperty
	public Set<String> gifs;
	
	@JsonCreator
	public FavoritesDataResponse(@JsonProperty(value = "gifs") Set<String> gifs) {this.gifs = gifs;}

	
}
