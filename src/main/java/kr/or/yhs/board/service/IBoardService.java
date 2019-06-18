package kr.or.yhs.board.service;

import java.util.List;
import java.util.Map;

import kr.or.yhs.board.model.AttachmentVo;
import kr.or.yhs.board.model.BoardVo;
import kr.or.yhs.board.model.PostVo;
import kr.or.yhs.board.model.ReplyVo;
import kr.or.yhs.paging.model.PageVo;

public interface IBoardService {
	int insertPost(PostVo postVo);
	
	List<PostVo> postList();
	
	int insertBoard(BoardVo boardVo);
	
	PostVo postInfo(int postnum);
	
	Map<String, Object> postPagingList(PageVo pageVo);
	Map<String, Object> boardPostPagingList(PageVo pageVo);
	
	List<BoardVo> boardList();
	
	List<BoardVo> boardListYes(String use_yn);
	
	BoardVo boardInfo(int boardInfo);
	
	int updateBoard(BoardVo boardVo);
	
	int updatePost(PostVo postVo);
	
	int insertReply(ReplyVo replyVo);
	
	List<ReplyVo> replyList(int postnum);
	
	int deletePost(PostVo postVo);
	
	int deleteReply(int replynum);
	
	
	
	///////////////////////////////////////////////파일/////////////////////////////////////////
	List<AttachmentVo> fileList(int postnum);
	
	AttachmentVo fileDown(int attachmentid);
	
	int fileCnt();
	
	int insertFile(AttachmentVo fileVo);
	
	int insertFileEdit(AttachmentVo fileVo);
	
	int deleteFile(int attachmentid);
}
