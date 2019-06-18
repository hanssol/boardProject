package kr.or.yhs.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.yhs.board.service.BoardService;
import kr.or.yhs.board.service.IBoardService;

@WebServlet("/fileDelete")
public class AttachmentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	IBoardService boardService;
	
	
   
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String attachmentidNM = request.getParameter("attachmentid");
		int attachmentid = Integer.parseInt(attachmentidNM);
		String boardNM = request.getParameter("boardnum");
		int boardnum = Integer.parseInt(boardNM);
		
		
		String postNM = request.getParameter("postnum2");
		int postnum = Integer.parseInt(postNM);
		int attachmentDeleteCnt = boardService.deleteFile(attachmentid);
		
		if(attachmentDeleteCnt == 1){
			response.sendRedirect(request.getContextPath()+"/postModify?postnum2="+postnum+"&boardnum="+boardnum);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
