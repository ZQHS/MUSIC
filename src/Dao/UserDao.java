package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import Entity.User;
import Exception.DaoException;
import Jdbc.JdbcUtils;

public class UserDao {

	QueryRunner qRunner = new QueryRunner();

	public Void deleteUser(String userid) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Delete From myuser Where userid=?";
			Object[] params = { userid };
			qRunner.update(conn, sql, params);
			sql = "Delete From musiclist Where userid=?";
			qRunner.update(conn, sql, params);
			conn.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public List<User> manageLogin() {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Select * From myuser";
			List<User> rs = qRunner.query(conn, sql,new BeanListHandler<User>(User.class));
			conn.close();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public void alterEmail(String userid, String email) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Update myuser Set email=? where userid=?";
			Object[] params = { email, userid };
			qRunner.update(conn, sql, params);
			conn.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public void alterUserid(String userid, String newuserid) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Update myuser Set userid=? where userid=?";
			Object[] params = { newuserid, userid };
			qRunner.update(conn, sql, params);
			conn.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public void alterPassword(String userid, String password) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Update myuser Set password=? where userid=?";
			Object[] params = { password, userid };
			qRunner.update(conn, sql, params);
			conn.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public void alterUserName(String userid, String username) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Update myuser Set username=? where userid=?";
			Object[] params = { username, userid };
			qRunner.update(conn, sql, params);
			conn.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	public User selectUserByUserid(String userid) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Select * From myuser where userid=?";
			Object[] params = { userid };
			User rs = qRunner.query(conn, sql, new BeanHandler<User>(User.class), params);
			conn.close();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public User selectUserByEmail(String email) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Select * From myuser where email=?";
			Object[] params = { email };
			User rs = qRunner.query(conn, sql, new BeanHandler<User>(User.class), params);
			conn.close();
			System.out.print(email);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	public void createUser(String userid, String username,String password,String email) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "INSERT INTO myuser VALUES(?,?,?,?)";
			Object[] params = { userid, username, password, email};
			qRunner.update(conn, sql, params);
			conn.close();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
}
