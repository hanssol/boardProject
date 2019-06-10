package kr.or.yhs.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.yhs.board.model.PostVo;
import kr.or.yhs.board.service.BoardService;
import kr.or.yhs.board.service.IBoardService;

/**
 * Servlet implementation class PostForm
 */
@WebServlet("/postForm")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 15)
public class PostFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	IBoardService boardService;
	
   
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardnum = request.getParameter("boardnum");
		
		request.setAttribute("boardnum", boardnum);
		request.getRequestDispatcher("/post/postForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String post_title = request.getParameter("post_title");
		String p_content = request.getParameter("smarteditor");
		String boardnNM = request.getParameter("boardnum");
		int boardnum = Integer.parseInt(boardnNM);
		
		String userid = request.getParameter("userid");
		
		PostVo postVo = null;
		
		postVo = new PostVo(boardnum, post_title, p_content, userid);
		
		int postCnt = boardService.insertPost(postVo);
		
		if(postCnt == 1){
			response.sendRedirect(request.getContextPath()+"/board?boardnum="+boardnum);
		}
				
	}

}
