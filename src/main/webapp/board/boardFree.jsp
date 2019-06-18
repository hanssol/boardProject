<%@page import="kr.or.yhs.paging.model.PageVo"%>
<%@page import="kr.or.yhs.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

<title>자유게시판</title>

<!-- css,js -->
<%@include file="/common/basicLib.jsp" %>
<style>
	.postTr:hover{
		cursor: pointer;
	}
</style>
<script>
$(document).ready(function(){
	$("#postRegBtn").on("click",function(){
		$("#frm").attr("action","${pageContext.request.contextPath }/postForm");
		$("#frm").attr("method","get");
		$("#frm").submit();
	})
	
	
	$(".postTr").on("click",function(){
		console.log("postTr click");
		var postnum = $(this).find(".postnum").text();
		$("#postnum").val(postnum);
		
		$("#frm").submit();
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
						<h2 class="sub-header">자유게시판</h2>
						
						<form id="frm" action="${pageContext.request.contextPath }/post" method="get"> 
							<input type="hidden" id="postnum" name="postnum"/>
							<input type="hidden" id="boardnum" name="boardnum" value="${pageVo.boardnum }"/>
							
						</form>
						
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<td>게시글 번호</td>
									<td>제목</td>
									<td>작성자 아이디</td>
									<td>작성일시</td>
								</tr>	
								
								<c:forEach items="${postList}" var="post">
									<c:choose>
										<c:when test="${post.use_yn == 'yes' }">
											<tr class="postTr">
												<td class="postnum">${post.postnum}</td>
												<td style="padding-left:${post.lv}9px">${post.post_title}</td>
												<td>${post.userid}</td>
												<td><fmt:formatDate value="${post.postdt}" pattern="yyyy-MM-dd" /></td>
											</tr>
										</c:when>
										<c:otherwise>
											<tr>
												<td colspan='4'>삭제된 게시글입니다.</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</c:forEach>
									
							</table>
						</div>

<%-- 						<a href="${pageContext.request.contextPath }/postForm" class="btn btn-default pull-right">게시글 등록</a> --%>
						<button id="postRegBtn" type="button" class="btn btn-default pull-right">게시글등록</button>

						<div class="text-center">
							<ul class="pagination">
							
							
							<%
								// 내가 현제 몇번째 페이지에 있는가?
								PageVo pageVo = (PageVo)request.getAttribute("pageVo");
							%>
							
							<c:choose>
								<c:when test="${pageVo.page == 1 }">	<%-- <c:when test="${pageVo.getPage() == 1 }"> --%>
									<li class="disabled"><span>«</span></li>
								</c:when>
								<c:otherwise>
									<li>
										<a href="${pageContext.request.contextPath}/board?boardnum=${pageVo.boardnum }&page=${pageVo.page - 1}&pageSize=${pageVo.pageSize}">«</a>
									</li>
								</c:otherwise>
							</c:choose>

								<c:forEach begin="1" end="${paginationSize}" var="i">
									<c:choose>
										<c:when test="${pageVo.page == i}">
											<li class="active"><span>${i}</span></li>
										</c:when>
										<c:otherwise>
											<li><a href="${pageContext.request.contextPath}/board?boardnum=${pageVo.boardnum }&page=${i}&pageSize=${pageVo.pageSize}">${i}</a></li>
										</c:otherwise>
									</c:choose>

								</c:forEach>
								
								<c:choose>
									<c:when test="${pageVo.page == paginationSize}">
										<li class="disabled"><span>»</span></li>
									</c:when>
									<c:otherwise>
										<li>
											<a href="${pageContext.request.contextPath}/board?boardnum=${pageVo.boardnum }&page=${pageVo.page + 1}&pageSize=${pageVo.pageSize}">»</a>
										</li>
									
									</c:otherwise>
								</c:choose>
														
							</ul>
						</div>
						
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>
