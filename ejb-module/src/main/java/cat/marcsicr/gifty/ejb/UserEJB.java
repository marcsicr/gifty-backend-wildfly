package cat.marcsicr.gifty.ejb;



import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cat.marcsicr.gifty.ejb.pojo.Gif;
import cat.marcsicr.gifty.jpa.Likes;
import cat.marcsicr.gifty.jpa.User;
import cat.marcsicr.gifty.jpa.id.Likeid;


@Stateless
@Default
public class UserEJB implements IUserLocal{


	@PersistenceContext(unitName="pu-gifty")
	private EntityManager entMan;
	
	@Override
	public boolean authenticate(String user, String pwd) {	
		User dbUser = entMan.find(User.class, user);
		if(dbUser == null)
			return false;
					
		if(dbUser.getPwd().equals(pwd))	
			return true;
		
		return false;
		
	}


	@Override 
	public Set<String> getJPALikes(String username){
		
		User dbUser = entMan.find(User.class, username);
		if(dbUser == null)
			return null;
		
		Set<Likes> likes = dbUser.getLikes();
		Set<String> gifsID = new HashSet<String>();
		for(Likes like: likes) {
			
			gifsID.add(like.getGif());
		}
		
		return gifsID;
	}
	
	@Override
	public boolean addGifLike(String username,String gifId) {
	
		User dbUser = entMan.find(User.class, username);
		Likes like = entMan.find(Likes.class, new Likeid(username,gifId));
		
		
		if(dbUser == null)
			return false;
		
		if(like != null)
			return true;
		
		Set<Likes> likes = dbUser.getLikes();
		like = new Likes(dbUser,gifId);
		
		likes.add(like);
		dbUser.setLikes(likes);
		entMan.persist(dbUser);
	
		return true;

	}
	

	@Override
	public void removeGifLike(String username, String gifId) {
		
		
		Likes like = entMan.find(Likes.class, new Likeid(username,gifId));
		
		if(like != null)
			entMan.remove(like);
		
	}

	/*@Override
	public boolean addGifLike(String username,String gifId) {
	
		User dbUser = entMan.find(User.class, username);
		
		
		if(dbUser == null)
			return false;
		
		Set<Gif> likes = dbUser.getGifLikes();
		
		likes.add(new Gif(gifId));
		dbUser.setGifLikes(likes);
		entMan.persist(dbUser);
	
		return true;

	}*/
	
	@Override
	public boolean existsUser(String username) {
		User user = entMan.find(User.class, username);
		return user != null;
	}
	

	@Override
	public void createUser(String username, String pwd) throws Exception {
		
		
		User user = entMan.find(User.class, username);
		if(user != null) {
			throw new Exception("This username already exists");
		}else {
			
			User dbUser= new User(username,pwd);
			entMan.persist(dbUser);
		}
		
	
	}
	
	
	

}
