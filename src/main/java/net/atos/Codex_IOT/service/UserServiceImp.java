package net.atos.Codex_IOT.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.atos.Codex_IOT.dao.UserDao;
import net.atos.Codex_IOT.mapper.Mapper;
import net.atos.Codex_IOT.model.UserData;
import net.atos.Codex_IOT.pojo.Role;
import net.atos.Codex_IOT.pojo.User;

/**
 * @author a622828
 *
 */

@Transactional
@Repository
public class UserServiceImp implements UserService {
	private static final Logger logger = Logger.getLogger(UserServiceImp.class); // Apache
																					// logger
	/**
	 * UserDao interface reference
	 */
	@Autowired
	private UserDao userDao;

	@Autowired
	private Mapper mapper;

	public UserServiceImp() {
		logger.info("Inside User ServiceImp");
	}
	
	@Override
	public User getValidateUser(User user) {

		System.out.println("----------------------");
		logger.info("This is User service Impl");
		return userDao.validateUser(user);

	}
	@Override
	public User getUserByID(long id) {

		return userDao.getUserById(id);
	}

	@Override
	public UserData addUser(UserData user) {
		return mapper.mapUserToUserModel(this.userDao.addUser(this.mapper
				.mapUserModelToUserPojo(user)));
	}

	@Override
	public User getUserbyId(long userId) {
		logger.info("in user service dao impl");
		return userDao.getUserbyId(userId);
	}

	@Override
	public boolean deleteUsrbyId(long userId) {
		logger.info("in user service dao impl");
		User user=userDao.getUserById(userId);
		if(user!=null)
			return userDao.deleteUsrbyId(user);
		return false;
	}

	@Override
	public List<User> getusersData() {
		logger.info("in user service dao impl");
		return userDao.getusersData();
	}

	@Override
	public void updateuser(User user) {
		this.userDao.updateuser(user);

	}

	@Override
	public boolean saveDeviceIdForSpecificUser(User dummyUser) {
		return userDao.saveDeviceIdForSpecificUser(dummyUser);	
	}

	@Override
	public Role getRolebyid(long roleId) {
	return userDao.getRolebyid(roleId);
	}

}
