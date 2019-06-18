package kr.or.yhs.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.yhs.board.model.AttachmentVo;
import kr.or.yhs.board.model.PostVo;
import kr.or.yhs.board.model.ReplyVo;
import kr.or.yhs.board.service.BoardService;
import kr.or.yhs.board.service.IBoardService;
import kr.or.yhs.user.service.UserService;

/**
 * Servlet implementation class PostController
 */
@WebServlet("/post")
public class PostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory
			.getLogger(PostController.class);
	
	IBoardService boardService;
	
	
   
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int postnum = Integer.parseInt(request.getParameter("postnum"));
		logger.debug("postnum : {}", postnum);
		PostVo postInfo = boardService.postInfo(postnum);
		List<ReplyVo> replyList = boardService.replyList(postnum);
		List<AttachmentVo> fileList = boardService.fileList(postnum);
		
		request.setAttribute("postInfo", postInfo);
		request.setAttribute("replyList", replyList);
		request.setAttribute("fileList", fileList);
		
		request.getRequestDispatcher("/post/post.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String r_content = request.getParameter("r_content");
		String userid = request.getParameter("userid2");
		String postNM = request.getParameter("postnum2");
		int postnum = Integer.parseInt(postNM);
		String boardNM = request.getParameter("boardnum");
		int boardnum = Integer.parseInt(boardNM);
		
		ReplyVo replyVo = null;
		replyVo = new ReplyVo(r_content, userid, postnum);
		
		int replyCnt = boardService.insertReply(replyVo);
		
		if(replyCnt == 1){
			response.sendRedirect(request.getContextPath()+"/post?postnum="+postnum+"&boardnum="+boardnum);
		}
		
	}

}
