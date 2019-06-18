package kr.or.yhs.board.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.db.dialect.MySQLDialect;
import kr.or.yhs.board.model.AttachmentVo;
import kr.or.yhs.board.model.BoardVo;
import kr.or.yhs.board.model.PostVo;
import kr.or.yhs.board.model.ReplyVo;
import kr.or.yhs.mybatis.MyBatisUtil;
import kr.or.yhs.paging.model.PageVo;

public class BoardDao implements IBoardDao{
	private static final Logger logger = LoggerFactory
			.getLogger(BoardDao.class);
	
	public static void main(String[] args) {
		IBoardDao dao;
		dao = new BoardDao();
		
		/***Given***/
		BoardVo boardVo = null;
		
		
		boardVo = new BoardVo(1,"자유게시판" ,"no");
		
		logger.debug("boardCnt : {}",boardVo);

		/***When***/
		int boardCnt = dao.updateBoard(boardVo);
		/***Then***/
		logger.debug("dddd ::::: {} ", boardCnt);
		
	}
	
	@Override
	public int insertPost(PostVo postVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int postCnt = sqlSession.insert("board.insertPost",postVo);
		sqlSession.commit();
		sqlSession.close();
		return postCnt;
	}

	@Override
	public List<PostVo> postList() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<PostVo> postList = sqlSession.selectList("board.postList");
		sqlSession.close();
		return postList;
	}

	@Override
	public int insertBoard(BoardVo boardVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int boardCnt = sqlSession.insert("board.inserBoard",boardVo);
		sqlSession.commit();
		sqlSession.close();
		return boardCnt;
	}

	@Override
	public List<BoardVo> boardList() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardVo> boardList = sqlSession.selectList("board.boardList");
		sqlSession.close();
		return boardList;
	}

	@Override
	public PostVo postInfo(int postnum) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		PostVo postVo = sqlSession.selectOne("board.postInfo",postnum);
		sqlSession.close();
		return postVo;
	}

	@Override
	public List<PostVo> postPagingList(PageVo pageVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<PostVo> postList = sqlSession.selectList("board.postPagingList",pageVo);
		sqlSession.close();
		return postList;
	}

	@Override
	public int postCnt() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int postCnt = (Integer)sqlSession.selectOne("board.postCnt");
		sqlSession.close();
		return postCnt;
	}

	@Override
	public BoardVo boardInfo(int boardnum) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		BoardVo boardInfo = sqlSession.selectOne("board.boardInfo",boardnum);
		sqlSession.close();
		return boardInfo;
	}

	@Override
	public List<BoardVo> boardListYes(String use_yn) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<BoardVo> boardList = sqlSession.selectList("board.boardListYes",use_yn);
		sqlSession.close();
		return boardList;
	}

	@Override
	public int updateBoard(BoardVo boardVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int boardCnt = sqlSession.update("board.updateBoard",boardVo);
		sqlSession.commit();
		sqlSession.close();
		return boardCnt;
	}

	@Override
	public int updatePost(PostVo postVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int postCnt = sqlSession.update("board.updatePost",postVo);
		sqlSession.commit();
		sqlSession.close();
		return postCnt;
	}

	@Override
	public List<PostVo> boardPostPagingList(PageVo pageVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<PostVo> boardPostList = sqlSession.selectList("board.boardPostPagingList",pageVo);
		sqlSession.close();
		return boardPostList;
	}

	@Override
	public int insertReply(ReplyVo replyVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int replyCnt = sqlSession.update("board.insertReply",replyVo);
		sqlSession.commit();
		sqlSession.close();
		return replyCnt;
	}

	@Override
	public List<ReplyVo> replyList(int postnum) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<ReplyVo> replyList = sqlSession.selectList("board.replyList",postnum);
		sqlSession.close();
		return replyList;
	}

	@Override
	public int deletePost(PostVo postVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int deleteCnt = sqlSession.update("board.deletPost",postVo);
		sqlSession.commit();
		sqlSession.close();
		return deleteCnt;
	}

	@Override
	public int deleteReply(int replynum) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int deleteCnt = sqlSession.update("board.deleteReply",replynum);
		sqlSession.commit();
		sqlSession.close();
		return deleteCnt;
	}

	@Override
	public List<AttachmentVo> fileList(int postnum) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		List<AttachmentVo> fileList = sqlSession.selectList("board.fileList",postnum);
		sqlSession.close();
		return fileList;
	}

	@Override
	public AttachmentVo fileDown(int attachmentid) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		AttachmentVo filedown = sqlSession.selectOne("board.FileDown",attachmentid);
		sqlSession.close();
		return filedown;
	}

	@Override
	public int fileCnt() {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int fileCnt = (Integer)sqlSession.selectOne("board.fileCnt");
		sqlSession.close();
		return fileCnt;
	}

	@Override
	public int insertFile(AttachmentVo fileVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int fileCnt = sqlSession.update("board.insertFile",fileVo);
		sqlSession.commit();
		sqlSession.close();
		return fileCnt;
	}

	@Override
	public int insertFileEdit(AttachmentVo fileVo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int fileCnt = sqlSession.update("board.insertFileEdit",fileVo);
		sqlSession.commit();
		sqlSession.close();
		return fileCnt;
	}

	@Override
	public int deleteFile(int attachmentid) {
		SqlSession sqlSession = MyBatisUtil.getSqlSession();
		int deleteCnt=sqlSession.delete("board.deleteFile",attachmentid);
		sqlSession.commit();
		sqlSession.close();
		return deleteCnt;
	}
	
	
	
}
