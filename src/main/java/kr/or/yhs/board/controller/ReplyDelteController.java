package kr.or.yhs.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.yhs.board.service.BoardService;
import kr.or.yhs.board.service.IBoardService;

/**
 * Servlet implementation class ReplyDelteController
 */
@WebServlet("/replyDelete")
public class ReplyDelteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(ReplyDelteController.class);
	
    IBoardService boardService;
    
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String replyNM = request.getParameter("replynum");
		int replynum = Integer.parseInt(replyNM);
		
		String postNM = request.getParameter("postnum");
		logger.debug("postnum : {}",postNM);
		
		int postnum = Integer.parseInt(postNM);
		int replyDeleteCnt = boardService.deleteReply(replynum);
		
		if(replyDeleteCnt == 1){
			response.sendRedirect(request.getContextPath()+"/post?postnum="+postnum);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
