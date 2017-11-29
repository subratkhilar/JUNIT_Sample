package net.atos.Codex_IOT.service;

import java.util.List;

import net.atos.Codex_IOT.model.UserData;
import net.atos.Codex_IOT.pojo.Role;
import net.atos.Codex_IOT.pojo.User;

public interface UserService {
	
	/**
	 * @param user
	 * @return User
	 */
	User getValidateUser(User user);

	User getUserByID(long id);
		
	public UserData addUser(UserData userdata);

	User getUserbyId(long userId);

	boolean deleteUsrbyId(long userId);

	List<User> getusersData();

	public void updateuser(User user);

	boolean saveDeviceIdForSpecificUser(User dummyUser);
	
	Role getRolebyid(long roleId);
	
}