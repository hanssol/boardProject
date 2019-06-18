package kr.or.yhs.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.yhs.board.model.PostVo;
import kr.or.yhs.board.service.BoardService;
import kr.or.yhs.board.service.IBoardService;

/**
 * Servlet implementation class PostDelteController
 */
@WebServlet("/postDelete")
public class PostDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IBoardService boardService;
	
	
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postNM = request.getParameter("postnum2");
		int postnum = Integer.parseInt(postNM);
		PostVo postVo = new PostVo();
		postVo.setPostnum(postnum);
		
		String boardNM = request.getParameter("boardnum");
		int boardnum = Integer.parseInt(boardNM);
		
		int postCnt = boardService.deletePost(postVo);
		
		if(postCnt == 1){
			response.sendRedirect(request.getContextPath()+"/board?boardnum="+boardnum);
		}
	}

}
