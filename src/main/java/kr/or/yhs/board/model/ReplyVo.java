package kr.or.yhs.board.model;

public class ReplyVo {
	private int replynum;		// 댓글번호
	private String r_content;	// 내용
	private String replydt;		// 작성일시
	private String userid;		// 작성자
	private int postnum;		// 게시글번호
	private String use_yn;		// 댓글 사용유무
	
	
	
	
	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	@Override
	public String toString() {
		return "ReplyVo [replynum=" + replynum + ", r_content=" + r_content
				+ ", replydt=" + replydt + ", userid=" + userid + ", postnum="
				+ postnum + "]";
	}

	public ReplyVo(String r_content, String userid, int postnum) {
		super();
		this.r_content = r_content;
		this.userid = userid;
		this.postnum = postnum;
	}

	public ReplyVo() {
		
	}
	
	public int getReplynum() {
		return replynum;
	}
	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public String getReplydt() {
		return replydt;
	}
	public void setReplydt(String replydt) {
		this.replydt = replydt;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getPostnum() {
		return postnum;
	}
	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}
	
	
}




