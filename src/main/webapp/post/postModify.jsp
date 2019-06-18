<%@page import="kr.or.yhs.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>

<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico" />
<%@include file="/common/basicLib.jsp"%>

<!-- jQuery -->
<!-- <script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>-->

<!-- <script type="text/javascript" src="/js/jquery/jquery-3.2.1.js"></script> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<script src="${pageContext.request.contextPath}/SE2/js/HuskyEZCreator.js"></script>
<script>
	var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.
	
	$(document).ready(function() {
		
		var cnt=0;
		$("#addFileBtn").on("click",function(){
			if($('.file').length != 5){
				cnt = $(".filDiv input").length+1;
				$(".filDiv").append("<input type='file' class='file' name='profile"+cnt+"'/>");
			}else{
				alert("첨부파일은 5개만 해요.")
			}
		})
		
		
		// 사용자 등록 버튼 클릭 이벤트 핸들러
	
		//개발용 데이타 초기화 함수 ****** 추후 지울것
		//dataInit();
	


	
	// Editor Setting
	nhn.husky.EZCreator.createInIFrame({
				oAppRef : oEditors, // 전역변수 명과 동일해야 함.
				elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
				sSkinURI : "${pageContext.request.contextPath}/SE2/SmartEditor2Skin.html", // Editor HTML
				fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
				htParams : {
					// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseToolbar : true,
					// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseVerticalResizer : true,
					// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
					bUseModeChanger : true,
				}
			});

	$("#postRegBtn").on("click", function() {
				if (confirm("저장하시겠습니까?")) {
					// id가 smarteditor인 textarea에 에디터에서 대입
					oEditors.getById["smarteditor"].exec(
							"UPDATE_CONTENTS_FIELD", []);

					// 이부분에 에디터 validation 검증
					if (validation()) {
						$("#frm").submit();
					}
				}
			})
});

	// 필수값 Check
	function validation() {
		var contents = $.trim(oEditors[0].getContents());
		if (contents === '&nbsp;' || contents === '') { // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
			alert("내용을 입력하세요.");
			oEditors.getById['smarteditor'].exec('FOCUS');
			return false;
		}
		return true;
	}
</script>
<style>
#smarteditor {
	margin: auto;
}
</style>
</head>
<body>
	<!-- header -->
	<%@include file="/common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">

			<!-- left -->
			<%@include file="/common/left.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">게시판 생성</h2>


						<div class="table-responsive">
							<form action="${pageContext.request.contextPath }/postForm"
								method="post" id="frm" class="form-horizontal" role="form"
								enctype="multipart/form-data">


								<input type="hidden" name="boardnum" value="${boardnum }">
								<input type="hidden" name="postnum" value="${postInfo.postnum }">
								

								<div class="form-group">
									<label for="post_title" class="col-sm-2 control-label">제목</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="post_title"
											name="post_title" placeholder="제목"
											value="${postInfo.post_title }">
									</div>
								</div>

								<div class="form-group">
									<label for="userid" class="col-sm-2 control-label">작성자</label>
									<div class="col-sm-10">
										<input type="hidden" class="form-control" id="userid"
											name="userid" placeholder="작성자" value=${USER_INFO.userId }>
										<textarea name="smarteditor" id="smarteditor" rows="10"
											cols="100" style="width: 766px; height: 412px;">${postInfo.p_content }</textarea>
									</div>
								</div>
								
								<c:forEach items="${fileList}" var="file">
						<div class="col-sm-10">
							<ul class="fileTr">
								<li>
									<a class="file" id="fileDown" href="">${file.attach_name}</a>
									<a id="fileDelete" onclick="return confirm('정말로 삭제하시겠습니까?')" href="${pageContext.request.contextPath}/fileDelete?attachmentid=${file.attachmentid}&postnum2=${postInfo.postnum}&boardnum=${postInfo.boardnum}" class="btn btn-default pull-right">삭제</a>
								</li>
							</ul>
						</div>
					</c:forEach>

								<div class="form-group">
									<label for="userid" class="col-sm-2 control-label">첨부파일</label>
									<div class="col-sm-8 filDiv">
										<input multiple="multiple" type="file" class="file" id="profile"
											name="profile1" placeholder="첨부파일">
									</div>
									<div class="col-sm-2">
										<button id="addFileBtn" type="button" class="btn btn-default pull-right">파일추가</button>
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button id="postRegBtn" type="button" class="btn btn-default">게시글등록</button>
									</div>
								</div>



							</form>




						</div>

					</div>
				</div>
			</div>
</body>
</html>
