package kr.or.yhs.board.dao;

import java.util.List;

import kr.or.yhs.board.model.AttachmentVo;
import kr.or.yhs.board.model.BoardVo;
import kr.or.yhs.board.model.PostVo;
import kr.or.yhs.board.model.ReplyVo;
import kr.or.yhs.paging.model.PageVo;

public interface IBoardDao {
	
	/**
	 * 
	 * Method : inserPost
	 * 작성자 : PC03
	 * 변경이력 :
	 * @param postVo
	 * @return
	 * Method 설명 : 게시글 추가
	 */
	int insertPost(PostVo postVo);
	
	/**
	 * 
	 * Method : postList
	 * 작성자 : yang1
	 * 변경이력 : 
	 * @return
	 * Method 설명 : 전체 게시글 조회
	 */
	List<PostVo> postList();
	
	/**
	 * 
	 * Method : postInfo
	 * 작성자 : yang1
	 * 변경이력 : 
	 * @param postnum
	 * @return
	 * Method 설명 : 게시글 정보 조회
	 */
	PostVo postInfo(int postnum);
	
	/**
	 * 
	 * Method : postPagingList
	 * 작성자 : yang1
	 * 변경이력 : 
	 * @param pageVo
	 * @return
	 * Method 설명 : 게시글 페이징리스트 조회
	 */
	List<PostVo> postPagingList(PageVo pageVo);
	
	List<PostVo> boardPostPagingList(PageVo pageVo);
	
	/**
	 * 
	 * Method : postCnt
	 * 작성자 : yang1
	 * 변경이력 : 
	 * @return
	 * Method 설명 : 게시글 전체 갯수 조회
	 */
	int postCnt();
	
	int updatePost(PostVo postVo);
	
	int insertReply(ReplyVo replyVo);
	
	List<ReplyVo> replyList(int postnum);
	
	int deletePost(PostVo postVo);
	
	int deleteReply(int replynum);
	
	//////////////////////////////////////////파일//////////////////////////////////////////////////////////
	List<AttachmentVo> fileList(int postnum);
	
	AttachmentVo fileDown(int attachmentid);
	
	int fileCnt();
	
	int insertFile(AttachmentVo fileVo);
	
	int insertFileEdit(AttachmentVo fileVo);
	
	int deleteFile(int attachmentid);
	
	////////////////////////////////////////// 게시판 //////////////////////////////////////////////////////////
	
	/**
	 * 
	 * Method : insertBoard
	 * 작성자 : yang1
	 * 변경이력 : 
	 * @param boardVo
	 * @return
	 * Method 설명 : 게시판 등록
	 */
	int insertBoard(BoardVo boardVo);
	
	/**
	 * 
	 * Method : boardList
	 * 작성자 : yang1
	 * 변경이력 : 
	 * @return
	 * Method 설명 : 전체 게시판 조회
	 */
	List<BoardVo> boardList();
	
	List<BoardVo> boardListYes(String use_yn);
	
	BoardVo boardInfo(int boardnum);
	
	int updateBoard(BoardVo boardVo);
	
	
	
}
