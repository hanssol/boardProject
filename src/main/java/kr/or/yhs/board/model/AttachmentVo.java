package kr.or.yhs.board.model;

public class AttachmentVo {
	private int attachmentid;	// 첨부파일 번호
	private int postnum;		// 게시글 번호
	private String attach_path;	// 첨부파일경로
	private String attach_name;	// 원래파일명
	
	
	@Override
	public String toString() {
		return "AttachmentVo [attachmentid=" + attachmentid + ", postnum="
				+ postnum + ", attach_path=" + attach_path + ", attach_name="
				+ attach_name + "]";
	}

	
	public AttachmentVo() {
		
	}


	public AttachmentVo(int postnum, String attach_path, String attach_name) {
		super();
		this.postnum = postnum;
		this.attach_path = attach_path;
		this.attach_name = attach_name;
	}

	public AttachmentVo(String attach_path, String attach_name) {
		super();
		this.attach_path = attach_path;
		this.attach_name = attach_name;
	}
	
	public int getAttachmentid() {
		return attachmentid;
	}
	public void setAttachmentid(int attachmentid) {
		this.attachmentid = attachmentid;
	}
	public int getPostnum() {
		return postnum;
	}
	public void setPostnum(int postnum) {
		this.postnum = postnum;
	}
	public String getAttach_path() {
		return attach_path;
	}
	public void setAttach_path(String attach_path) {
		this.attach_path = attach_path;
	}
	public String getAttach_name() {
		return attach_name;
	}
	public void setAttach_name(String attach_name) {
		this.attach_name = attach_name;
	}
	
	
}
