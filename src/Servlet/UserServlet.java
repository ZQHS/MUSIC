package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.MusicDao;
import Dao.UserDao;
import Entity.User;
import Exception.MusicException;
import Exception.UserException;
import Service.UserService;
import cn.itcast.commons.CommonUtils;

@WebServlet(urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	UserService userService = new UserService();
	MusicDao musicDao = new MusicDao();
	UserDao userDao = new UserDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getParameter("method");
		if ("loginByUserid".equals(method)) {
			loginByUserid(request, response);
		}
		if ("loginByEmail".equals(method)) {
			loginByEmail(request, response);
		}
		if ("cancel".equals(method)) {
			cancel(request, response);
		}
		if ("register".equals(method)) {
			register(request, response);
		}
		if ("alterUserName".equals(method)) {
			alterUserName(request, response);
		}
		if ("alterPassword".equals(method)) {
			alterPassword(request, response);
		}
		if ("alterUserid".equals(method)) {
			alterUserid(request, response);
		}
		if ("alterEmail".equals(method)) {
			alterEmail(request, response);
		}
		if ("manageLogin".equals(method)) {
			manageLogin(request, response);
		}
		if ("deleteUser".equals(method)) {
			deleteUser(request, response);
		}
		if ("manageRegister".equals(method)) {
			manageRegister(request, response);
		}
	}
	
	private void manageRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			User getUser = CommonUtils.toBean(request.getParameterMap(), User.class);
			String userid = getUser.getUserid();
			String username = getUser.getUsername();
			String password = getUser.getPassword();
			String email = getUser.getEmail();
			userService.register(userid, username, password, email);
			List<User> findUser = userDao.manageLogin();
			request.getSession().setAttribute("alluser", findUser);
			response.sendRedirect("/MUSIC/manage.jsp");
		} catch (UserException e) {
			request.setAttribute("regMsg", e.getMessage());
			request.getRequestDispatcher("/manage.jsp").forward(request, response);
		}
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String userid = request.getParameter("userid");
		try {
			userDao.deleteUser(userid);
			List<User> findUser = userDao.manageLogin();
			request.getSession().setAttribute("alluser", findUser);
			response.sendRedirect("/MUSIC/manage.jsp");
		} catch (UserException e) {
			e.printStackTrace();
			throw new MusicException("系统错误");
		}		
	}

	private void manageLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");

		try {
			List<User> findUser = userService.manageLogin(userid, password);
			request.getSession().setAttribute("alluser", findUser);
			response.sendRedirect("/MUSIC/manage.jsp");
		} catch (UserException e) {
			request.setAttribute("manageLoginMsg", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}		
	}

	private void alterEmail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		try {
			User getUser = CommonUtils.toBean(request.getParameterMap(), User.class);
			String userid = getUser.getUserid();
			String username = getUser.getUsername();
			String password = getUser.getPassword();
			String email = getUser.getEmail();
			User findUser = userService.alterEmail(userid, username, password, email);
			request.getSession().setAttribute("user", findUser);
			response.sendRedirect("/MUSIC/index.jsp");
		} catch (UserException e) {
			request.setAttribute("alterMsg", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}		
	}

	private void alterUserid(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		try {
			User getUser = CommonUtils.toBean(request.getParameterMap(), User.class);
			String userid = getUser.getUserid();
			String username = getUser.getUsername();
			String password = getUser.getPassword();
			String newuserid = request.getParameter("newuserid");
			User findUser = userService.alterUserid(userid, username, password, newuserid);
			request.getSession().setAttribute("user", findUser);
			response.sendRedirect("/MUSIC/index.jsp");
		} catch (UserException e) {
			request.setAttribute("alterMsg", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}		
	}

	
	private void alterPassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		try {
			User getUser = CommonUtils.toBean(request.getParameterMap(), User.class);
			String userid = getUser.getUserid();
			String username = getUser.getUsername();
			String password = getUser.getPassword();
			String newpassword = request.getParameter("newpassword");
			User findUser = userService.alterPassword(userid, username, password, newpassword);
			request.getSession().setAttribute("user", findUser);
			response.sendRedirect("/MUSIC/index.jsp");
		} catch (UserException e) {
			request.setAttribute("alterMsg", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}		
	}

	private void alterUserName(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		try {
			User getUser = CommonUtils.toBean(request.getParameterMap(), User.class);
			String userid = getUser.getUserid();
			String username = getUser.getUsername();
			String password = getUser.getPassword();
			User findUser = userService.alterUserName(userid, username, password);
			request.getSession().setAttribute("user", findUser);
			response.sendRedirect("/MUSIC/index.jsp");
		} catch (UserException e) {
			request.setAttribute("alterMsg", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}		
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			User getUser = CommonUtils.toBean(request.getParameterMap(), User.class);
			String userid = getUser.getUserid();
			String username = getUser.getUsername();
			String password = getUser.getPassword();
			String email = getUser.getEmail();
			userService.register(userid, username, password, email);
			request.getSession().setAttribute("user", getUser);
			response.sendRedirect("/MUSIC/index.jsp");
		} catch (UserException e) {
			request.setAttribute("regMsg", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	private void loginByUserid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User getUser = CommonUtils.toBean(request.getParameterMap(), User.class);
		String userid = getUser.getUserid();
		String password = getUser.getPassword();

		try {
			User findUser = userService.loginByUserid(userid, password);
			request.getSession().setAttribute("user", findUser);
			List<String> musicListName = musicDao.searchMusicListByUserid(userid);
			request.getSession().setAttribute("musicListName", musicListName);
			response.sendRedirect("/MUSIC/index.jsp");
		} catch (UserException e) {
			request.setAttribute("loginMsg", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
	
	private void loginByEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User getUser = CommonUtils.toBean(request.getParameterMap(), User.class);
		String email = getUser.getUserid();
		String password = getUser.getPassword();

		try {
			User findUser = userService.loginByUserEmail(email, password);
			String userid = findUser.getUserid();
			request.getSession().setAttribute("user", findUser);
			List<String> musicListName = musicDao.searchMusicListByUserid(userid);
			request.getSession().setAttribute("musicListName", musicListName);
			response.sendRedirect("/MUSIC/index.jsp");
		} catch (UserException e) {
			request.setAttribute("loginMsg", e.getMessage());
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	private void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getSession().removeAttribute("user");
			request.getSession().removeAttribute("msg");
			request.getSession().removeAttribute("musicListName");
			request.getSession().removeAttribute("musicList");
			
			response.sendRedirect("/MUSIC/index.jsp");
		} catch (UserException e) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}
