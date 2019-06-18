package kr.or.yhs.board.model;

import java.util.Date;

public class PostVo {
	private int postnum;		//	게시글번호
	private Date postdt;		//	작성일시
	private String post_title;	//	제목
	private String p_content;	//	내용
	private int boardnum;		//	게시판번호
	private String userid;		//	작성자
	private int postnum2;	//	부모게시글
	private String use_yn;		//  게시글 사용여부
	private int group_num;   //  그룹번호
	private int lv;
	
	public int getLv() {
		return lv;
	}

	public void setLv(int lv) {
		this.lv = lv;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}
	private int rn;
	
	
	public String getUse_yn() {
		return use_yn;
	}

	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	public int getGroup_num() {
		return group_num;
	}

	public void setGroup_num(int group_num) {
		this.group_num = group_num;
	}

	@Override
	public String toString() {
		return "PostVo [postnum=" + postnum + ", postdt=" + postdt
				+ ", post_title=" + post_title + ", p_content=" + p_content
				+ ", boardnum=" + boardnum + ", userid=" + userid
				+ ", postnum2=" + postnum2 + "]";
	}
	
	public PostVo(){
		
	}
	
	
	
	public PostVo(String post_title, String p_content) {
		super();
		this.post_title = post_title;
		this.p_content = p_content;
	}

	public PostVo(int boardnum, String post_title, String p_content,
			String userid) {
		super();
		this.boardnum = boardnum;
		this.post_title = post_title;
		this.p_content = p_content;
		this.userid = userid;
	}



	public int getPostnum() {
		return postnum;
	}
	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}
	public Date getPostdt() {
		return postdt;
	}
	public void setPostdt(Date postdt) {
		this.postdt = postdt;
	}
	public String getPost_title() {
		return post_title;
	}
	public void setPost_title(String post_title) {
		this.post_title = post_title;
	}
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}
	public int getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getPostnum2() {
		return postnum2;
	}
	public void setPostnum2(int postnum2) {
		this.postnum2 = postnum2;
	}
	

	
}
