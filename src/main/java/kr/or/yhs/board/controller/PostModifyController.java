package kr.or.yhs.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.yhs.board.model.AttachmentVo;
import kr.or.yhs.board.model.PostVo;
import kr.or.yhs.board.service.BoardService;
import kr.or.yhs.board.service.IBoardService;
import kr.or.yhs.util.PartUtil;

/**
 * Servlet implementation class PostModifyController
 */
@WebServlet("/postModify")
public class PostModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IBoardService boardService;
	
	

	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardnNM = request.getParameter("boardnum");
		int boardnum = Integer.parseInt(boardnNM);
		String postNM = request.getParameter("postnum2");
		int postnum = Integer.parseInt(postNM);
		PostVo postInfo = boardService.postInfo(postnum);
		List<AttachmentVo> fileList = boardService.fileList(postnum);
		
		request.setAttribute("postInfo", postInfo);
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("fileList", fileList);
		
		request.getRequestDispatcher("/post/postModify.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String postTitle = request.getParameter("post_title");
		String postContent = request.getParameter("smarteditor");
		String boardnNM = request.getParameter("boardnum");
		int boardnum = Integer.parseInt(boardnNM);
		String postNM = request.getParameter("postnum");
		int postnum = Integer.parseInt(postNM);
		
		PostVo postEdit = new PostVo(postTitle, postContent);
		
		int editCnt = boardService.updatePost(postEdit);
		
Part part = request.getPart("profile");
		
		if(part.getSize() > 0){
			String contentDisposition = part.getHeader("content-disposition");
			String fileName = PartUtil.getFileName(contentDisposition);
			String ext = PartUtil.getExt(fileName);
			ext = ext.equals("") ? "" : "." + ext;
			
			String uploadPath = "c:\\uploadTest";
			File uploadFolder = new File(uploadPath);
			
			if(uploadFolder.exists()){
				String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
				AttachmentVo attachmentVo = new AttachmentVo(filePath, fileName);
				int insertFileCnt = boardService.insertFileEdit(attachmentVo);
				
				part.write(filePath);
				part.delete();
			}
		}
		
		if(request.getPart("profile2")!=null){
			Part profile2 = request.getPart("profile2");
			
			if(profile2.getSize() > 0){
				String contentDisposition = profile2.getHeader("content-disposition");
				String fileName = PartUtil.getFileName(contentDisposition);
				String ext = PartUtil.getExt(fileName);
				ext = ext.equals("") ? "" : "." + ext;
				
				String uploadPath = "c:\\uploadTest";
				File uploadFolder = new File(uploadPath);
				
				if(uploadFolder.exists()){
					String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
					AttachmentVo attachmentVo = new AttachmentVo(filePath, fileName);
					int insertFileCnt = boardService.insertFileEdit(attachmentVo);
					
					profile2.write(filePath);
					profile2.delete();
				}
			}
			
		}
		
		if(request.getPart("profile3")!=null){
			Part profile3 = request.getPart("profile3");
			
			if(profile3.getSize() > 0){
				String contentDisposition = profile3.getHeader("content-disposition");
				String fileName = PartUtil.getFileName(contentDisposition);
				String ext = PartUtil.getExt(fileName);
				ext = ext.equals("") ? "" : "." + ext;
				
				String uploadPath = "c:\\uploadTest";
				File uploadFolder = new File(uploadPath);
				
				if(uploadFolder.exists()){
					String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
					AttachmentVo attachmentVo = new AttachmentVo(filePath, fileName);
					int insertFileCnt = boardService.insertFileEdit(attachmentVo);
					
					profile3.write(filePath);
					profile3.delete();
				}
			}
			
		}
		
		if(request.getPart("profile4")!=null){
			Part profile4 = request.getPart("profile4");
			
			if(profile4.getSize() > 0){
				String contentDisposition = profile4.getHeader("content-disposition");
				String fileName = PartUtil.getFileName(contentDisposition);
				String ext = PartUtil.getExt(fileName);
				ext = ext.equals("") ? "" : "." + ext;
				
				String uploadPath = "c:\\uploadTest";
				File uploadFolder = new File(uploadPath);
				
				if(uploadFolder.exists()){
					String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
					AttachmentVo attachmentVo = new AttachmentVo(filePath, fileName);
					int insertFileCnt = boardService.insertFileEdit(attachmentVo);
					
					profile4.write(filePath);
					profile4.delete();
				}
			}
			
		}
		
		if(request.getPart("profile5")!=null){
			Part profile5 = request.getPart("profile5");
			
			if(profile5.getSize() > 0){
				String contentDisposition = profile5.getHeader("content-disposition");
				String fileName = PartUtil.getFileName(contentDisposition);
				String ext = PartUtil.getExt(fileName);
				ext = ext.equals("") ? "" : "." + ext;
				
				String uploadPath = "c:\\uploadTest";
				File uploadFolder = new File(uploadPath);
				
				if(uploadFolder.exists()){
					String filePath = uploadPath + File.separator + UUID.randomUUID().toString() + ext;
					AttachmentVo attachmentVo = new AttachmentVo(filePath, fileName);
					int insertFileCnt = boardService.insertFileEdit(attachmentVo);
					
					profile5.write(filePath);
					profile5.delete();
				}
			}
			
		}
		
		if(editCnt == 1){
			response.sendRedirect(request.getContextPath()+"/post?postnum="+postnum+"&boardnum="+boardnum);
		}
		
	}

}
