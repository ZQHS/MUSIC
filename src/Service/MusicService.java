package Service;

import Dao.MusicDao;
import Entity.Music;
import Entity.PageBean;
import Exception.DaoException;
import Exception.MusicException;

public class MusicService {

	MusicDao musicDao = new MusicDao();
	
	public Void insertMusic(String userid,String listName,String musicid) {
		try {
			musicDao.insertMusic(userid, listName, musicid);
			return null;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new MusicException("系统错误");
		}
	}
	
	public Void insertMusicList(String userid,String listName) {
		try {
			musicDao.insertMusicList(userid, listName);
			return null;
		} catch (DaoException e) {
			e.printStackTrace();
			throw new MusicException("系统错误");
		}
	}

	public PageBean<Music> getSearchedSong(String search, int pageIndex) {
		try {
			return musicDao.search(search, pageIndex);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new MusicException("系统错误");
		}
	}
}
