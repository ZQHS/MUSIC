package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import Entity.Music;
import Entity.PageBean;
import Exception.DaoException;
import Jdbc.JdbcUtils;

public class MusicDao {
	
	QueryRunner qRunner = new QueryRunner();
	
	public Void insertMusic(String userid,String listName,String musicid) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Select musiclistid From musiclist Where musiclistname=? and userid=?";
			Object[] params1 = {listName , userid };
			int musicListId = qRunner.query(conn, sql,new ScalarHandler<>(), params1);
			Object[] params2 = {musicListId , musicid};
			sql = "insert into list_music Values(?,?)";
			qRunner.update(conn, sql, params2);
			conn.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public Void deleteMusic(String userid,String listName,String musicid) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Select musiclistid From musiclist Where musiclistname=? and userid=?";
			Object[] params1 = {listName , userid };
			int musicListId = qRunner.query(conn, sql,new ScalarHandler<>(), params1);
			Object[] params2 = {musicListId , musicid};
			sql = "Delete From list_music Where musiclistid=? and musicid=?";
			qRunner.update(conn, sql, params2);
			conn.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public Void deleteMusicList(String userid,String listName) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Delete From musiclist Where musiclistname=? and userid=?";
			Object[] params = {listName, userid };
			qRunner.update(conn, sql, params);
			conn.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public Void insertMusicList(String userid,String listName) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Select max(musiclistid) From musiclist";
			String sql2 = "Insert Into musiclist Values(?,?,?)";
			int musicListId = qRunner.query(conn, sql, new ScalarHandler<>());
			musicListId++;
			Object[] params = { musicListId, listName, userid };
			qRunner.update(conn, sql2, params);
			conn.close();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
	
	public List<String> searchMusicListByUserid(String userid) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Select musiclistname From musiclist where userid=?";
			Object[] params = { userid };
			List<String> rs = qRunner.query(conn, sql, new ColumnListHandler<>("musiclistname"), params);
			conn.close();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}	
	}
	
	public List<Music> searchMusicList(String listName, String userid ) {
		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Select music.musicid,music,authorname,musicadd From musiclist,myuser,music,list_music,author Where "
					+ "myuser.userid=musiclist.userid and musiclist.musiclistid=list_music.musiclistid and "
					+ "list_music.musicid=music.musicid and music.authorid=author.authorid and myuser.userid=? and musiclistname=?";
			Object[] params = { userid, listName };
			List<Music> rs = qRunner.query(conn, sql, new BeanListHandler<Music>(Music.class), params);
			conn.close();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}	
	}
	
	public PageBean<Music> search(String search, int pageIndex) {
		String music = "%" + search + "%";
		String author = "%" + search + "%";
		int pageSize = 10;
		int start = (pageIndex - 1) * pageSize;

		try {
			Connection conn = JdbcUtils.getConnection();
			String sql = "Select musicid,music,authorname,musicadd From music,author where music.authorid=author.authorid and (music like ? or authorname like ?)"
					+ "ORDER BY music offset ? rows fetch next ? rows only";
			Object[] params = { music, author, start, pageSize};
			List<Music> rs = qRunner.query(conn, sql, new BeanListHandler<Music>(Music.class), params);
			
			sql = "Select musicid,music,authorname,musicadd From music,author where music.authorid=author.authorid and (music like ? or authorname like ?)";
			Object[] params2 = { music, author};
			qRunner.query(conn, sql, new BeanListHandler<Music>(Music.class), params2);
			sql = "select @@rowcount";
			int total = qRunner.query(conn, sql, new ScalarHandler<>());
			int totalPage = (total%pageSize==0) ? total/pageSize : total/pageSize + 1 ;
			PageBean<Music> pageBean = new PageBean<Music>();
			pageBean.setBeanList(rs);
			pageBean.setPageIndex(pageIndex);
			pageBean.setPageSize(pageSize);
			pageBean.setTotal(total);
			pageBean.setTotalPage(totalPage);
			System.out.print(total);
			System.out.print(totalPage);
			conn.close();
			return pageBean;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
}
