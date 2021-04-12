package cat.marcsicr.gifty.jpa.id;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Likeid implements Serializable{


	private static final long serialVersionUID = 3751435809918779596L;
	
	@Column(name="username")
	private String username;
	
	@Column(name="gif")
	private String zgif;

	
	public Likeid() {}
	
	public Likeid(String user,String gif) {
		
		this.username = user;
		this.zgif = gif;
	}
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getZgif() {
		return zgif;
	}

	public void setZgif(String zgif) {
		this.zgif = zgif;
	}

	@Override
	public int hashCode() {
		return Objects.hash(username,zgif);
	}

	@Override
	public boolean equals(Object obj) {
	
		if(this == obj)
			return true;
		
		if((obj == null) || getClass() != obj.getClass())
				return false;
				
		Likeid lid = (Likeid) obj;
		
		return username.equals(lid.username) && 
				zgif.equals(lid.zgif);
	}
	
	

}
