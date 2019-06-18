<%@page import="kr.or.yhs.paging.model.PageVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>게시글 상세조회</title>

<!-- css,js -->
<%@include file="/common/basicLib.jsp" %>
<script>
$(document).ready(function(){
	// 수정하기 버튼
	$("#editBtn").on("click",function(){
		$("#frm").attr("action","${pageContext.request.contextPath }/postModify");
		$("#frm").attr("method","get");
		$("#frm").submit();
	})
	
	// 댓글버튼
	$("#replyBtn").on("click",function(){
		$("#frm").submit();
	})
	
	// 답글버튼
	$("#commentBtn").on("click",function(){
		$("#frm").attr("action","${pageContext.request.contextPath }/postCommentForm");
		$("#frm").attr("method","get");
		$("#frm").submit();
	})
	
	// 삭제버튼
	$("#deleteBtn").on("click",function(){
		if(confirm("정말 삭제할거에요?")== true){
			
			$("#frm").attr("action","${pageContext.request.contextPath }/postDelete");
			$("#frm").attr("method","post");
			$("#frm").submit();
		}else{
			return false;
		}
	})
	
	
})

</script>
</head>
<body>
	<!-- header -->
	<%@include file="/common/header.jsp" %>
	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@include file="/common/left.jsp" %>
			
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">게시글 상세조회</h2>
						
						<form class="form-horizontal" role="form" id="frm" action="${pageContext.request.contextPath }/post"
							  method="post">
								<input type="hidden" id="userId" name="userId" value="${postInfo.postnum}"/>
								<input type="hidden" id="postnum2" name="postnum2" value="${postInfo.postnum }"/>
								<input type="hidden" id="boardnum" name="boardnum" value="${postInfo.boardnum }"/>
								<input type="hidden" id="userid2" name="userid2" value="${postInfo.userid }"/>
<%-- 								<input type="text" id="replynum" name="replynum" value="${replyList.replynum }"> --%>
								
								
						
		
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">제목</label>
								<div class="col-sm-10">
									<label class="control-label">${postInfo.post_title }</label>
								</div>
							</div>
		
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">글 내용</label>
								<div class="col-sm-10">
									<label class="control-label">${postInfo.p_content }</label>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">첨부파일</label>
								<div class="col-sm-10">
										<c:forEach items="${fileList}" var="file">
												<a id="fileDown" href="${pageContext.request.contextPath}/fileDown?attachmentid=${file.attachmentid}" class="btn btn-default"> ${file.attach_name}</a> 
										</c:forEach>
								</div>
							</div>
							
							<div class="form-group">
								<label for="userNm" class="col-sm-2 control-label">댓글</label>
								<div class="col-sm-10">
									<table class="table table-striped">
										<tr>
											<td>작성자 아이디</td>
											<td>내용</td>
											<td>작성일시</td>
											<td></td>
										</tr>
										<c:forEach items="${replyList }" var="reply">
											<c:choose>
												<c:when test="${reply.use_yn == 'yes' }">
													<tr>
														<td>${reply.userid }</td>
														<td>${reply.r_content }</td>
														<td>${reply.replydt }</td>
														<td><a id="replyDelete"  onclick="return confirm('정말로 삭제하시겠습니까?')" href="${pageContext.request.contextPath}/replyDelete?replynum=${reply.replynum}&postnum=${reply.postnum}" class="btn btn-default pull-right"> 삭제</a></td>
													</tr>
												</c:when>
												<c:otherwise>
													<tr>
														<td colspan='4'>삭제된 댓글입니다.</td>
													</tr>
												</c:otherwise>
											</c:choose>
											
										</c:forEach>	
										
									</table>
									<textarea rows="1" cols="60" name="r_content"></textarea>
									<button type="button" name="replyBtn" id="replyBtn"> 댓글등록 </button>
									<button type="button" name="commentBtn" id="commentBtn"> 답글등록 </button>
									<button type="button" name="deleteBtn" id="deleteBtn"> 게시글삭제 </button>
								</div>
							</div>
		
							
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									
									<button type="button" name="editBtn" id="editBtn" class="btn btn-default">게시글 수정</button>
								</div>
							</div>
							
							
							
						</form>

						
						
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>