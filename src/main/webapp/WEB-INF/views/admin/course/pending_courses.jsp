<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인강인강 승인 대기중인 강의 관리</title>
	<style>
		.table td {
			vertical-align: middle!important;
		    font-size: medium;
		}
	</style>
</head>
<body id="page-top">
    <div id="wrapper">
    	<!-- 좌측 배너 부분 -->
		<jsp:include page="../layout/banner.jsp"/>
		<!-- 좌측 배너 부분 -->
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<!-- 상단 헤더 부분 -->
				<jsp:include page="../layout/header.jsp"/>
				<!-- 상단 헤더 부분 -->
				
				<!-- 본문 -->
				<div class="container-fluid">
					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">승인 대기중인 강의 관리</h1>
					</div>
					<hr>
					
					<div class="row mb-3 pl-3">
						<form action="/admin/course/pending-courses" role="form" method="GET" class="form-inline">
							<select class="form-control" id="searchCategory" name="searchCategory">
								<option value="course_title" <c:if test="${searchCategory == 'course_title'}">selected</c:if>>강의명</option>
								<option value="teacher_name" <c:if test="${searchCategory == 'teacher_name'}">selected</c:if>>강사명</option>
								<option value="teacher_email" <c:if test="${searchCategory == 'teacher_email'}">selected</c:if>>이메일</option>
							</select>
							<div class="ml-3">
								<input value="${search}" type="text" id="searchKeyword" name="search" placeholder="검색어를 입력하세요." class="form-control">
								<button type="submit" class="btn px-3 btn-primary">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</form>
					</div>
					
					<div>
						<table class="table table-hover table-white text-center">
							<thead>
								<tr>
									<th>
										<input id="allCheck" type="checkbox" name="allCheck">
									</th>
									<th>강의No</th>
									<th>원본No</th>
									<th>강의명</th>
									<th>강사명</th>
									<th>요청날짜</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="cr" items="${courseRequestList}">
									<tr>
										<td>
											<input name="rowCheck" type="checkbox" value="${cr.olr_no}">			
										</td>
										<td>${cr.oli_no}</td>
										<td><a href="">${cr.origin_oli_no}</a></td>
										<td><a onclick="openCourseDetail(${cr.oli_no})" href="javascript:;">${cr.course_title}</a></td>
										<td><a href="" target="_blank">${cr.teacher_name}</a></td>
										<td>${cr.request_datetime}</td>
										<td>
											<input type="button" class="btn btn-info" value="승인" onclick="approvalCourseRequest(${cr.olr_no});">
											<button type="button" class="btn btn-secondary" onclick="rejectCourseRequest(${cr.olr_no});">거절</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<c:if test="${courseRequestList.size() == 0}">
							<h5 class="text-center">승인 대기중인 강의가 없습니다.</h5>
						</c:if>
					</div>
					
					<c:if test="${paging.totalCount > 10}">
						<!-- 게시글 페이징 처리(기준 10개) -->
						<nav aria-label="Page navigation">
							<ul class="pagination justify-content-center">
						
								<!-- 첫 페이지면 Disabled 아니라면 Enabled -->
								<c:choose>
									<c:when test="${paging.pageNo eq paging.firstPageNo }">
										<li class="page-item disabled">
											<a class="page-link" href="/admin/course/pending-courses?page=${paging.prevPageNo}">Previus</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
											<a class="page-link" href="/admin/course/pending-courses?page=${paging.prevPageNo}">Previus</a>
										</li>
									</c:otherwise>
								</c:choose>
								<!-- 페이지 갯수만큼 버튼 생성 -->
								<c:forEach var="i" begin="${paging.startPageNo }" end="${paging.endPageNo }" step="1">
									<c:choose>
										<c:when test="${i eq paging.pageNo }">
											<li class="page-item disabled">
												<a class="page-link" href="/admin/course/pending-courses?page=${i}"><c:out value="${i }"/></a>
											</li>
										</c:when>
										<c:otherwise>
											<li class="page-item">
												<a class="page-link" href="/admin/course/pending-courses?page=${i}"><c:out value="${i }"/></a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<!-- 마지막 페이지면 Disabled 아니라면 Enabled -->
								<c:choose>
									<c:when test="${paging.pageNo eq paging.finalPageNo }">
										<li class="page-item disabled">
											<a class="page-link" href="/admin/course/pending-courses?page=${paging.nextPageNo}">Next</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
											<a class="page-link" href="/admin/course/pending-courses?page=${paging.nextPageNo}">Next</a>
										</li>
									</c:otherwise>
								</c:choose>
							</ul>
						</nav>
					</c:if>
				</div>
				<!-- 본문 -->
			</div>
			<!-- 하단 푸터 부분 -->
			<jsp:include page="../layout/footer.jsp"/>
    		<!-- 하단 푸터 부분 -->
    		
    		
    		<!-- 요청 거절 확인 Modal -->
			<div class="modal fade" id="rejectionModal" tabindex="-1" role="dialog">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">거절 사유를 입력하세요</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			      	<textarea class="form-control" rows="5" id="rejection_message"></textarea>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
			        <button id="rejection_btn" type="button" class="btn btn-primary">거절</button>
			      </div>
			    </div>
			  </div>
			</div>
		</div>
	</div>
    
    <script>
    	var rejection_olr_no = 0;
    
   		// 전체 체크박스 클릭 이벤트
	    $("#allCheck").click(function () {
	        $("[name='rowCheck']").prop('checked', $(this).prop('checked'));
	    });
   		
   		// 요청 승인
   		function approvalCourseRequest(olr_no) {
   			if(confirm("정말 승인하시겠습니까?")) {
   				$.ajax({
   					url: "/approvalCourseRequest",
   					type: "post",
   					data: {
	   					olr_no: olr_no
   					},
   					success: function() {
   						location.reload();
   					}
   				});
   			}
   		}
   	
   		
   		// 요청 거절
   		function rejectCourseRequest(olr_no) {
   			rejection_olr_no = olr_no;
   			$("#rejection_message").val('');
   			$("#rejectionModal").modal('show');
   		}
   		
   		$("#rejection_btn").click(function() {
   			if($("#rejection_message").val().trim() == '') {
   				alert("거절 사유를 입력하세요");
   				return;
   			}
   			$.ajax({
				url: "/rejectCourseRequest",
				type: "post",
				data: {
  						olr_no: rejection_olr_no,
  						rejection_message: $("#rejection_message").val()
				},
				success: function() {
					location.reload();
				}
			});
   		});
    
   		// 강의 상세 창 띄우기
	 	function openCourseDetail(oli_no) {
	 		// 창 크기 지정
			var width = window.screen.width * 55 / 100;
			var height = window.screen.height * 85 / 100;

			// pc화면기준 가운데 정렬
			var left = (window.screen.width / 2) - (width/2);
			var top = (window.screen.height / 2) - (height/2);

			var url = "/admin/course/"+oli_no;
			var option = "width = " + width + ", height = " + height + ", left=" + left + ", top = " + top;
			console.log(option);
			window.open(url, "_blank", option);
	 	}
    </script>
    
	<!-- Chart -->
	<script src='<c:url value="/resources/js/Chart.min.js"/>'></script>
	
</body>
</html>