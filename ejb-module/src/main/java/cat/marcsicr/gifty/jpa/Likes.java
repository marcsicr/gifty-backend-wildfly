package cat.marcsicr.gifty.jpa;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import cat.marcsicr.gifty.jpa.id.Likeid;


@Entity(name = "likes")
public class Likes {

	@EmbeddedId
	private Likeid id;
	
	
	@MapsId("username")
	@JoinColumn(name="username")
	@ManyToOne
	private User user;

	public Likeid getId() {
		return id;
	}

	public void setId(Likeid id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getGif() {
		return id.getZgif();
	}
	
	public Likes() {}
	
	public Likes(User user, String idGif) {
		
		this.id = new Likeid(user.getUsername(),idGif);
		this.user = user;
	}
	
}
