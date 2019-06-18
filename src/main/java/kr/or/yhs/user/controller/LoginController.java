package kr.or.yhs.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.yhs.board.model.BoardVo;
import kr.or.yhs.board.service.BoardService;
import kr.or.yhs.board.service.IBoardService;
import kr.or.yhs.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.yhs.user.model.UserVo;
import kr.or.yhs.user.service.IuserService;
import kr.or.yhs.user.service.UserService;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	
	private static final long serialVersionUID = 1L;
	
	IuserService userService;
	IBoardService boardService;
	
	
       
    
	@Override
	public void init() throws ServletException {
		userService = new UserService();
		boardService = new BoardService();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserVo user = (UserVo) request.getSession().getAttribute("USER_INFO");
		
		if(user != null){
			request.getRequestDispatcher("/board/main.jsp").forward(request, response);
		}else{
			// session에 사용자 정보가 없을 경우 --> 기존 로직으로 이동
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자 파라미터 userId, password
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String encryptPassword = KISA_SHA256.encrypt(password);
		
		UserVo uservo = userService.getUser(userId);
		
		if(uservo!=null && userId.equals(uservo.getUserId()) && password.equals(uservo.getPass())){
//			int cookieMaxAge=0;
//			if(request.getParameter("rememberme")!=null){
//				cookieMaxAge =60*60*24*30;
//			}	
//			Cookie userIdCookie = new Cookie("userId",userId);
//			userIdCookie.setMaxAge(cookieMaxAge);
//			
//			Cookie rememberMeCookie = new Cookie("rememberme","true");
//			rememberMeCookie.setMaxAge(cookieMaxAge);
//			
//			response.addCookie(userIdCookie);
//			response.addCookie(rememberMeCookie);
			
			
			
			HttpSession session =request.getSession();
			session.setAttribute("USER_INFO", uservo);
			
			String use_yn = "yes";
			List<BoardVo> boardListY = boardService.boardListYes(use_yn);
			ServletContext sc = request.getServletContext();
			sc.setAttribute("boardListY", boardListY);
			
			RequestDispatcher rd = request.getRequestDispatcher("/board/main.jsp");
			rd.forward(request, response);
		}else{
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		}
		
	}

}
