package cat.marcsicr.gifty.jpa;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import cat.marcsicr.gifty.ejb.pojo.Gif;


@TypeDefs({
	@TypeDef(name="jsonb",typeClass = JsonBinaryType.class) //Both PostgreSQL JSON types need to be materialized using a binary data format (JsonBinaryType)	
})

@Entity
@Table(name="users")
public class User {

	@Id
	@Column(name="username")
	private String username;
	
	@Column(name="pwd",nullable = false)
	private String pwd;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user")
	Set<Likes> likes;
	
	public User(String username, String pwd) {
		this.username = username;
		this.pwd = pwd;

	}
	
	public User() {}
	
	public Set<Likes> getLikes() {
		return likes;
	}

	public void setLikes(Set<Likes> likes) {
		this.likes = likes;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}
	

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
