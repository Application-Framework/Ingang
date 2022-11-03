<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>케어핀투어 관리자</title>
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
						<h4>회원 상세정보</h4>
						<hr>
					</div>
					<div class="d-flex flex-column">
						<form role="form" name="memberUpdate" id="memberUpdate" method="POST"  enctype="multipart/form-data">
							<div class="row">
								<div class="col-xs-4 col-md-4">
									<div class="form-group row">
										<div class="col-xs-12 col-md-12">
										
											<c:if test="${mDetail.img_path ne null}">
													<img alt="" width="70%;" src="${mDetail.img_path}">
											</c:if>
											<c:if test="${mDetail.img_path eq null}">
												<img alt="" width="70%;" src="<c:out value='/resources/img/logo/logo5.png'></c:out>">
											</c:if>
										</div>
										<div class="col-xs-12 col-md-12">
											<input type="file" class="form-control" id="file" name="file" style="border:0 solid black;" >
										</div>
									</div>
								</div>
								
								<div class="col-xs-8 col-md-8">
									<!-- 아이디 & 비밀번호 -->
									<div class="form-group row">
										<div class="col-xs-6 col-md-6">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">No</span>
												</div>
												<input type="text" name="m_no" id="m_no" class="form-control" value="${mDetail.m_no}" readonly>
											</div>
										</div>
										<div class="col-xs-6 col-md-6">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">이름</span>
												</div>
												<input type="text" name="m_name" id="m_name" class="form-control" value="${mDetail.m_name}" readonly>
											</div>
										</div>
									</div>
									<!-- 이름 & 마일리지 -->
									<div class="form-group row">
										<div class="col-xs-6 col-md-6">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">아이디</span>
												</div>
												<input type="text" name="m_id" id="m_id" class="form-control" value="${mDetail.m_id}" readonly>
											</div>
										</div>
										<div class="col-xs-6 col-md-6">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">비밀번호</span>
												</div>
												<input type="password" name="m_pw" id="m_pw" class="form-control" value="${mDetail.m_pw}">
											</div>
										</div>
									</div>
									
									<div class="form-group row">
										<div class="col-xs-6 col-md-6">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">권한</span>
												</div>
												<select id="m_authority" name="m_authority" class="form-control">
													<option value="2">회원</option>
													<option value="3">VIP</option>
													<option value="4">블랙리스트</option>
													<option value="1">관리자</option>
												</select>
											</div>
										</div>
										<div class="col-xs-6 col-md-6">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">가입일</span>
												</div>
												<input type="text" name="name" id="name" class="form-control" value="${mDetail.reg_date}" readonly>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 특이사항 -->
							<div class="form-group">
								<div class="input-group my-2 mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text">특이사항</span>
									</div>
									<textarea rows="5" cols="25" name="m_comment" id="m_comment" class="form-control">${mDetail.m_comment}</textarea>
								</div>
							</div>
							
							<button type="button" class="btn btn-primary btn-block btn-round" id="btnAdminModify">수정</button>
							<br>
							<!-- 강의 -->
							<h4>강의 구매 내역</h4>
							<table class="table table-hover table-white">
								<thead>
									<tr>
										<th>주문번호</th>
										<th>강의명</th>
										<th>결제금액</th>
										<th>결제일자</th>
										<th>결제상태</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${adminMemberLectureList}" var="LectureList">
									<tr>
										<td>L-${LectureList.hol_no }</td>
										<td><a href="/ex/detailInfo?PID=${LectureList.oli_no}" target="_blank">${LectureList.title}</a></td>
										<td>${LectureList.payment }</td>
										<td>${LectureList.payment_date }</td>
										<c:choose>
											<c:when test="${LectureList.payment_status eq 0}">
												<td><font size="3">결제보류</font></td>
											</c:when>
											<c:when test="${LectureList.payment_status eq 1}">
												<td><font size="3">결제완료</font></td>
											</c:when>
										</c:choose>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							<c:if test="${ Paging.totalCount > 5}">
								<nav aria-label="Page navigation">
									<ul class="pagination justify-content-center">
										<!-- 페이지 갯수만큼 버튼 생성 -->
										<c:forEach var="i" begin="${Paging.startPageNo }" end="${Paging.endPageNo }" step="1">
											<c:choose>
												<c:when test="${i eq Paging.pageNo }">
													<li class="page-item disabled">
														<a class="page-link" href="memberDetail?m_no=${mDetail.m_no}&page=${i}&nPage=${Paging2.getPageNo()}&cPage=${Paging3.getPageNo()}"><c:out value="${i }"/></a>
													</li>
												</c:when>
												<c:otherwise>
													<li class="page-item">
														<a class="page-link" href="memberDetail?m_no=${mDetail.m_no}&page=${i}&nPage=${Paging2.getPageNo()}&cPage=${Paging3.getPageNo()}"><c:out value="${i }"/></a>
													</li>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</ul>
								</nav>
							</c:if>
							<c:if  test="${Paging.totalCount <= 0 }">
								<div align="center">강의 구매 이력이 없습니다.</div>
							</c:if>
							<hr>
							<!--노트  -->
							<h4>노트 구매 내역</h4>
							<table class="table table-hover table-white">
								<thead>
									<tr>
										<th>주문번호</th>
										<th>노트명</th>
										<th>결제금액</th>
										<th>결제일자</th>
										<th>결제상태</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${adminMemberNoteList}" var="NoteList">
									<tr>
										<td>N-${NoteList.hon_no }</td>
										<td><a href="/ex/detailInfo?PID=${NoteList.n_no}" target="_blank">${NoteList.title}</a></td>
										<td>${NoteList.payment }</td>
										<td>${NoteList.payment_date }</td>
										<c:choose>
											<c:when test="${NoteList.payment_status eq 0}">
												<td><font size="3">결제보류</font></td>
											</c:when>
											<c:when test="${NoteList.payment_status eq 1}">
												<td><font size="3">결제완료</font></td>
											</c:when>
										</c:choose>
										
									</tr>
									</c:forEach>
								</tbody>
							</table>
							<c:if test="${ Paging2.totalCount > 5}">
								<nav aria-label="Page navigation">
									<ul class="pagination justify-content-center">
										
										<!-- 페이지 갯수만큼 버튼 생성 -->
										<c:forEach var="j" begin="${Paging2.startPageNo }" end="${Paging2.endPageNo }" step="1">
											<c:choose>
												<c:when test="${j eq Paging2.pageNo }">
													<li class="page-item disabled">
														<a class="page-link" href="memberDetail?m_no=${mDetail.m_no}&page=${Paging.getPageNo()}&nPage=${j}&cPage=${Paging3.getPageNo()}"><c:out value="${j }"/></a>
													</li>
												</c:when>
												<c:otherwise>
													<li class="page-item">
														<a class="page-link" href="memberDetail?m_no=${mDetail.m_no}&page=${Paging.getPageNo()}&nPage=${j}&cPage=${Paging3.getPageNo()}"><c:out value="${j }"/></a>
													</li>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										
									</ul>
								</nav>
							</c:if>
							<c:if  test="${Paging2.totalCount <= 0 }">
								<div align="center">노트 구매 이력이 없습니다.</div><br>
							</c:if>
							<!-- 커뮤니티 -->
							<h4>커뮤니티 게시글 작성 내역</h4>
							<table class="table table-hover table-white">
								<thead>
									<tr>
										<th>게시글번호</th>
										<th>종류</th>
										<th>제목</th>
										<th>조회수</th>
										<th>작성일</th>
										<th>게시여부</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${adminMemberCommunityList}" var="cList">
									<tr>
										<td>C-${cList.cb_no}</td>
										<c:choose>
											<c:when test="${cList.classify eq 1}">
												<td><font size="3">자유게시판</font></td>
											</c:when>
											<c:when test="${cList.classify eq 2}">
												<td><font size="3">질문답변(미해결)</font></td>
											</c:when>
											<c:when test="${cList.classify eq 3}">
												<td><font size="3">질문답변(해결)</font></td>
											</c:when>
											<c:when test="${cList.classify eq 4}">
												<td><font size="3">스터디(모집중)</font></td>
											</c:when>
											<c:when test="${cList.classify eq 5}">
												<td><font size="3">스터디(모집완료)</font></td>
											</c:when>
										</c:choose>
										<td><a href="/ex/detailInfo?PID=${cList.cb_no}" target="_blank">${cList.title}</a></td>
										<td>${cList.hit }</td>
										<td>${cList.reg_date }</td>
										<c:choose>
											<c:when test="${cList.flag eq 0}">
												<td><font size="3">x</font></td>
											</c:when>
											<c:when test="${cList.flag eq 1}">
												<td><font size="3">O</font></td>
											</c:when>
										</c:choose>
										
									</tr>
									</c:forEach>
								</tbody>
							</table>
							<c:if test="${ Paging3.totalCount > 5}">
								<nav aria-label="Page navigation">
									<ul class="pagination justify-content-center">
										
										<!-- 페이지 갯수만큼 버튼 생성 -->
										<c:forEach var="j" begin="${Paging3.startPageNo }" end="${Paging3.endPageNo }" step="1">
											<c:choose>
												<c:when test="${j eq Paging3.pageNo }">
													<li class="page-item disabled">
														<a class="page-link" href="memberDetail?m_no=${mDetail.m_no}&page=${Paging.getPageNo()}&nPage=${Paging2.getPageNo()}&cPage=${j}"><c:out value="${j }"/></a>
													</li>
												</c:when>
												<c:otherwise>
													<li class="page-item">
														<a class="page-link" href="memberDetail?m_no=${mDetail.m_no}&page=${Paging.getPageNo()}&nPage=${Paging2.getPageNo()}&cPage=${j}"><c:out value="${j }"/></a>
													</li>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										
									</ul>
								</nav>
							</c:if>
							<c:if  test="${Paging3.totalCount <= 0 }">
								<div align="center">작성 게시글 이력이 없습니다.</div><br>
							</c:if>
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
$(document).ready(function(){
	$("#m_authority").val("${mDetail.m_authority}").prop("selected", true);
})


$('#btnAdminModify').click(function() {
	var form = $("form")[0];
	var formData = new FormData(form);
	
	$.ajax({
		cache : false,
		url : "updateAdminMember", 
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