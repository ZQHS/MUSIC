package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.MusicDao;
import Entity.Music;
import Entity.PageBean;
import Exception.MusicException;
import Exception.UserException;
import Service.MusicService;

@WebServlet("/MusicServlet")
public class MusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	MusicDao musicDao = new MusicDao();
	MusicService musicService = new MusicService();
	
    public MusicServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");

		if ("search".equals(method)) {
			search(request, response);
		}
		if ("searchMusicListName".equals(method)) {
			searchMusicListName(request, response);
		}
		if ("searchMusicList".equals(method)) {
			searchMusicList(request, response);
		}
		if ("addMusicList".equals(method)) {
			addMusicList(request, response);
		}
		if ("deleteMusicList".equals(method)) {
			deleteMusicList(request, response);
		}
		if ("deleteMusic".equals(method)) {
			deleteMusic(request, response);
		}
		if ("addMusic".equals(method)) {
			addMusic(request, response);
		}
	}
	

	private void addMusic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String listName = request.getParameter("musicListName");
		String musicid = request.getParameter("musicid");
		try {
			musicService.insertMusic(userid, listName, musicid);
			response.sendRedirect("/MUSIC/search.jsp");
		} catch (UserException e) {
			e.printStackTrace();
			throw new MusicException("系统错误");
		}		
	}

	private void deleteMusic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String listName = request.getParameter("musicListName");
		String musicid = request.getParameter("musicid");
		try {
			musicDao.deleteMusic(userid, listName, musicid);
			searchMusicList(request, response);
		} catch (UserException e) {
			e.printStackTrace();
			throw new MusicException("系统错误");
		}		
	}

	private void deleteMusicList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String listName = request.getParameter("musicListName");
		try {
			musicDao.deleteMusicList(userid, listName);
			searchMusicListName(request, response);
		} catch (UserException e) {
			e.printStackTrace();
			throw new MusicException("系统错误");
		}		
	}

	private void addMusicList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String listName = request.getParameter("musicListName");
		try {
			musicService.insertMusicList(userid, listName);
			searchMusicListName(request, response);
		} catch (UserException e) {
			e.printStackTrace();
			throw new MusicException("系统错误");
		}			
	}

	private void searchMusicList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String listName = request.getParameter("musicListName");
		try {
			List<Music> musicList = musicDao.searchMusicList(listName, userid);
			request.getSession().setAttribute("nowList", listName);
			request.getSession().setAttribute("musicList", musicList);
			for (Music s : musicList) {
				System.out.println(s);
			}
			response.sendRedirect("/MUSIC/my.jsp");
		} catch (UserException e) {
			e.printStackTrace();
			throw new MusicException("系统错误");
		}			
	}

	private void searchMusicListName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		try {
			List<String> musicListName = musicDao.searchMusicListByUserid(userid);
			request.getSession().setAttribute("musicListName", musicListName);
			response.sendRedirect("/MUSIC/my.jsp");
		} catch (UserException e) {
			e.printStackTrace();
			throw new MusicException("系统错误");
		}	
	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int pageIndex = getPageIndex(request);
			String search = request.getParameter("search");
			PageBean<Music> pageBean = musicService.getSearchedSong(search, pageIndex);
			request.getSession().setAttribute("search", pageBean);
			request.getSession().setAttribute("nowSearch", search);
			response.sendRedirect("/MUSIC/search.jsp");
		} catch (MusicException e) {
			e.printStackTrace();
			throw new MusicException("系统错误");
		}
	}

	private int getPageIndex(HttpServletRequest request) {
		String pageIndex = request.getParameter("pageIndex");
		if (pageIndex == null || pageIndex.equals("")) {
			return 1;
		}
		return Integer.parseInt(pageIndex);
	}

}
