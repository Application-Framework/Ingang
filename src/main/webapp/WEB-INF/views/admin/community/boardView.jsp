<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 관리</title>
</head>
<body id="page-top">
    <div id="wrapper">
		<!-- 좌측 배너 부분 -->
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<!-- 상단 헤더 부분 -->
				<jsp:include page="../layout/header.jsp"/>
				<script src='<c:url value="/resources/js/Board.js"/>'></script>
				<!-- 상단 헤더 부분 -->
				
				<!-- 본문 -->
				<div class="container-fluid" >
					<div class="form-title text-center">
						<h4>게시글 상세정보</h4>
						<hr>
					</div>
					<div class="d-flex flex-column">
						<form role="form" name="boardUpdate" id="boardUpdate" method="POST"  enctype="multipart/form-data">
							<div class="row">
								
								<div class="col-xs-8 col-md-12">
									<!-- 아이디 & 비밀번호 -->
									<div class="form-group row">
										<div class="col-xs-6 col-md-4">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">No</span>
												</div>
												<input type="text" name="cb_no" id="cb_no" class="form-control" value="${cbDetail.cb_no}" readonly>
											</div>
										</div>
										<div class="col-xs-6 col-md-4">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">이름</span>
												</div>
												<input type="text" name="m_name" id="m_name" class="form-control" value="${cbDetail.m_name}" readonly>
											</div>
										</div>
										<div class="col-xs-6 col-md-4">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">아이디</span>
												</div>
												<input type="text" name="m_id" id="m_id" class="form-control" value="${cbDetail.m_id}" readonly>
											</div>
										</div>
									</div>
									<!-- 이름 & 마일리지 -->
									<div class="form-group row">
										<div class="col-xs-6 col-md-4">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">분류</span>
												</div>
												<c:if test="${cbDetail.classify == 1}">
													<input type="text" name="classify" id="classify" class="form-control" value="자유게시판" readonly>
												</c:if>
												<c:if test="${cbDetail.classify == 2}">
													<input type="text" name="classify" id="classify" class="form-control" value="질문답변 미해결" readonly>
												</c:if>
												<c:if test="${cbDetail.classify == 3}">
													<input type="text" name="classify" id="classify" class="form-control" value="질문답변 해결" readonly>
												</c:if>
												<c:if test="${cbDetail.classify == 4}">
													<input type="text" name="classify" id="classify" class="form-control" value="스터디 모집중" readonly>
												</c:if>
												<c:if test="${cbDetail.classify == 5}">
													<input type="text" name="classify" id="classify" class="form-control" value="스터디 모집완료" readonly>
												</c:if>
											</div>
										</div>
										<div class="col-xs-6 col-md-4">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">강의</span>
												</div>
												<c:if test="${cbDetail.oli_no == 0}">
													<input type="text" name="oli_title" id="oli_title" class="form-control" value="없음" readonly>
												</c:if>
												<c:if test="${cbDetail.oli_no != 0}">
													<input type="text" name="oli_title" id="oli_title" class="form-control" value="${cbDetail.oli_title}" readonly>
												</c:if>
											</div>
										</div>
										<div class="col-xs-6 col-md-4">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">작성일</span>
												</div>
												<input type="text" name="reg_date" id="reg_date" class="form-control" value="${cbDetail.reg_date}" readonly>
											</div>
										</div>
									</div>
								</div>
							</div>
							<hr>
							
							<!-- 제목 -->
							<div class="row">
								<div class="col-xs-12 col-md-12">
									<div class="form-group row">
										<div class="col-xs-12 col-md-12">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">제목</span>
												</div>
												<input type="text" name="title" id="title" class="form-control" value="${cbDetail.title}">
											</div>
										</div>
									</div>
								</div>
							</div>
							
							<!-- 내용 -->
							<div class="form-group">
								<div class="input-group my-2 mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text">내용</span>
									</div>
									<textarea rows="5" cols="25" name="content" id="content" class="form-control">${cbDetail.content}</textarea>
								</div>
							</div>
							
							<button type="button" class="btn btn-primary btn-block btn-round" id="btnAdminModify">수정</button>
						</form>
					</div>
				</div>
			</div>
			<!-- 본문 -->
		</div>
	<!-- 하단 푸터 부분 -->
	</div>
	<jsp:include page="../layout/footer.jsp"/>
  		<!-- 하단 푸터 부분 -->
<script type="text/javascript">
$('#btnAdminModify').click(function() {
	var form = $("form")[0];
	var formData = new FormData(form);
	
	$.ajax({
		cache : false,
		url : "updateAdminBoard", 
		processData: false,
		contentType: false,
		type : 'POST', 
		data : formData, 
		success : function(data = 1) {
			opener.location.reload();
			window.close();
			
		},
		error : function(xhr, status) {
			alert(xhr + " : " + status);
		}
	});
	
})
</script>
</body>
</html>