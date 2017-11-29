package net.atos.Codex_IOT.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import net.atos.Codex_IOT.dao.UserDao;
import net.atos.Codex_IOT.mapper.Mapper;
import net.atos.Codex_IOT.model.UserData;
import net.atos.Codex_IOT.pojo.Asset;
import net.atos.Codex_IOT.pojo.Customer;
import net.atos.Codex_IOT.pojo.Role;
import net.atos.Codex_IOT.pojo.User;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImpTest {
	@Mock
	private UserDao userDao;

	@Mock
	private Mapper mapper;
	@InjectMocks
	private UserService userService = new UserServiceImp();
	private User user = new User();
	private Role role = new Role();

	@Before
	public void beforeTest() {
		user.setUserId(123L);
		Customer customer = new Customer();
		customer.setCustomerId(21L);
		customer.setCustomerName("Shaym");
		user.setCustomer(customer);
		role.setRoleId(1L);
		role.setRolename("admin");
		role.setRoletype("Type1");
		user.setRole(role);
	}

	@Test
	public void testUserServiceImp() {

	}

	@Test
	public void testGetValidateUser() {

		when(userDao.validateUser(user)).thenReturn(user);
		User result = userService.getValidateUser(user);
		// Validation
		assertNotNull(result);
		assertEquals(user.getUserId(), result.getUserId());
	}

	@Test
	public void testGetUserByID() {
		when(userDao.getUserById(user.getUserId())).thenReturn(user);
		User result = userService.getUserByID(user.getUserId());
		// Validation
		assertNotNull(result);
		assertEquals(user.getUserId(), result.getUserId());

	}

	@Test
	public void testAddUser() {
		UserData userData = new UserData();
		userData.setUserId(1L);
		userData.setUsername("ram");
		userData.setFirstname("Shyam");
		userData.setLastname("K");
		when(mapper.mapUserToUserModel(userDao.addUser(mapper.mapUserModelToUserPojo(userData)))).thenReturn(userData);
		UserData result = userService.addUser(userData);
		// Validation
		assertNotNull(result);
		// assertEquals(id, result.getUserId());
		
	}

	@Test
	public void testGetUserbyId() {
		when(userDao.getUserbyId(user.getUserId())).thenReturn(user);
		User result = userService.getUserbyId(user.getUserId());
		// Validation
		assertNotNull(result);
		assertEquals(user.getUserId(), result.getUserId());
	}

	@Test
	public void testDeleteUsrbyId() {
		when(userDao.getUserById(user.getUserId())).thenReturn(user);
		when(userDao.deleteUsrbyId(user)).thenReturn(true);
		boolean result = userService.deleteUsrbyId(user.getUserId());
		// Validation
		assertNotNull(result);

	}

	@Test
	public void testGetusersData() {
		List<User> userList = new ArrayList<User>();
		userList.add(user);
		when(userDao.getusersData()).thenReturn(userList);

		List<User> results = userService.getusersData();
		assertNotNull(results);
		assertEquals(userList.size(), results.size());
	}

	@Test
	public void testUpdateuser() {
		doNothing().when(userDao).updateuser(user);
		userService.updateuser(user);

	}

	@Test
	public void testSaveDeviceIdForSpecificUser() {
		when(userDao.saveDeviceIdForSpecificUser(user)).thenReturn(true);
		boolean result = userService.saveDeviceIdForSpecificUser(user);
		// Validation
		assertNotNull(result);
	}

	@Test
	public void testGetRolebyid() {
		when(userDao.getRolebyid(user.getRole().getRoleId())).thenReturn(role);
		Role result = userService.getRolebyid(user.getRole().getRoleId());
		// Validation
		assertNotNull(result);
		assertEquals(user.getRole().getRoleId(), result.getRoleId());
	}

}
