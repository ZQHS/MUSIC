package Service;

import java.util.List;

import Dao.UserDao;
import Entity.User;
import Exception.DaoException;
import Exception.UserException;
public class UserService {
	
	UserDao userDao=new UserDao();
	
	public List<User> manageLogin(String userid,String password){

		try {
			if(!"123456".equals(userid)) {
				throw new UserException("账号错误");
			}
			if(!"123456".equals(password)) {
				throw new UserException("密码错误");
			}
			List<User> findUser = userDao.manageLogin();
			return findUser;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException("系统错误");
		}
	}

	public User alterEmail(String userid, String username, String password, String email){
		try {			
			if(email==null) {
				throw new UserException("请填写新的邮箱");
			}
			User findUser = userDao.selectUserByEmail(email);
			if(findUser!=null) {
				throw new UserException("该邮箱已被注册");
			}
			if(password==null) {
				throw new UserException("请填写密码");
			}
			findUser = userDao.selectUserByUserid(userid);	
			if(!findUser.getPassword().equals(password)) {
				throw new UserException("密码错误");
			}
			userDao.alterEmail(userid, email);
			findUser = userDao.selectUserByUserid(userid);	
			return findUser;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException("系统错误");
		}
	}
	
	public User alterUserid(String userid, String username, String password, String newuserid){
		try {			
			if(newuserid==null) {
				throw new UserException("请填写新的手机号");
			}
			User findUser = userDao.selectUserByUserid(newuserid);
			if(findUser!=null) {
				throw new UserException("该手机号已被注册");
			}
			if(password==null) {
				throw new UserException("请填写密码");
			}
			findUser = userDao.selectUserByUserid(userid);	
			if(!findUser.getPassword().equals(password)) {
				throw new UserException("密码错误");
			}
			userDao.alterUserid(userid,newuserid);
			findUser = userDao.selectUserByUserid(newuserid);	
			return findUser;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException("系统错误");
		}
	}
	
	public User alterPassword(String userid, String username, String password, String newpassword){
		try {			
			if(newpassword==null) {
				throw new UserException("请填写新的密码");
			}
			if(password==null) {
				throw new UserException("请填写密码");
			}
			User findUser = userDao.selectUserByUserid(userid);	
			if(!findUser.getPassword().equals(password)) {
				throw new UserException("密码错误");
			}
			userDao.alterPassword(userid,newpassword);
			findUser = userDao.selectUserByUserid(userid);	
			return findUser;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException("系统错误");
		}
	}
	
	public User alterUserName(String userid, String username, String password){
		try {			
			if(username==null) {
				throw new UserException("请填写新的昵称");
			}
			if(password==null) {
				throw new UserException("请填写密码");
			}
			User findUser = userDao.selectUserByUserid(userid);	
			if(!findUser.getPassword().equals(password)) {
				throw new UserException("密码错误");
			}
			userDao.alterUserName(userid,username);
			findUser = userDao.selectUserByUserid(userid);	
			return findUser;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException("系统错误");
		}
	}

	public User loginByUserid(String userid,String password){
		try {
			User findUser = userDao.selectUserByUserid(userid);	
			if(findUser==null) {
				throw new UserException("用户账号不存在");
			}
			if(!findUser.getPassword().equals(password)) {
				throw new UserException("密码错误");
			}
			return findUser;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException("系统错误");
		}
	}
	
	public User loginByUserEmail(String email,String password){
		try {
			User findUser = userDao.selectUserByEmail(email);	
			if(findUser==null) {
				throw new UserException("用户账号不存在");
			}
			if(!findUser.getPassword().equals(password)) {
				throw new UserException("密码错误");
			}
			return findUser;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException("系统错误");
		}
	}
	
	public void register(String userid, String username,String password,String email){
		try {
			if(userid==null) {
				throw new UserException("请填写手机号");
			}
			User findUser = userDao.selectUserByUserid(userid);	
			if(findUser!=null) {
				throw new UserException("该手机号已被注册");
			}
			
			if(email==null) {
				throw new UserException("请填写邮箱");
			}
			findUser = userDao.selectUserByEmail(email);
			
			if(findUser!=null) {
				throw new UserException("该邮箱已被注册");
			}
			
			if(username==null) {
				throw new UserException("请填写昵称");
			}
			if(password==null) {
				throw new UserException("请填写密码");
			}
			userDao.createUser(userid,username, password,email);
			return;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new UserException("系统错误");
		}
	}
}
