package cat.marcsicr.gifty.ejb;


import java.util.Set;

import javax.ejb.Local;

import cat.marcsicr.gifty.ejb.pojo.Gif;

@Local
public interface IUserLocal {
	
	public boolean existsUser(String username);
	public void createUser(String username, String pwd) throws Exception;
	public boolean authenticate(String username, String pwd);
	public boolean addGifLike(String username,String gifId);
	public void removeGifLike(String username, String gifId);
	
	public Set<String> getJPALikes(String username);
}
