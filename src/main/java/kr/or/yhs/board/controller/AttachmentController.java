package kr.or.yhs.board.controller;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.yhs.board.model.AttachmentVo;
import kr.or.yhs.board.service.BoardService;
import kr.or.yhs.board.service.IBoardService;

/**
 * Servlet implementation class AttachmentController
 */
@WebServlet("/fileDown")
@MultipartConfig(maxFileSize = 1024 * 1024 * 3, maxRequestSize = 1024 * 1024 * 15)
public class AttachmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    IBoardService boardService;
    
    
    
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String attachmentID = request.getParameter("attachmentid");
		int attachmentid = Integer.parseInt(attachmentID);
		AttachmentVo attachmentVo = boardService.fileDown(attachmentid);
		
		request.setAttribute("attachmentVo",attachmentVo );
		
		String filePath = attachmentVo.getAttach_path();
		String fileName = attachmentVo.getAttach_name();
		
	
		// 서버에 올라간 경로를 가져옴
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath("filefolder");
		
		System.out.println(" 업로드된 파일 경로 : " + uploadFilePath);
		System.out.println(" 파일 전체 경로 : " + filePath);
		
		byte[] b = new byte[4096];
		FileInputStream fileInputStream = new FileInputStream(filePath);
		
		String mimeType = getServletContext().getMimeType(filePath);
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		
        // 파일명 UTF-8로 인코딩(한글일 경우를 대비)
        String sEncoding = new String(fileName.getBytes("UTF-8"));
        response.setHeader("Content-Disposition", "attachment; fileName= " + sEncoding);
        
        // 파일 쓰기 OutputStream
        ServletOutputStream servletOutStream = response.getOutputStream();
        
        int read;
        while((read = fileInputStream.read(b,0,b.length))!= -1){
            servletOutStream.write(b,0,read);            
        }
        
        servletOutStream.flush();
        servletOutStream.close();
        fileInputStream.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
