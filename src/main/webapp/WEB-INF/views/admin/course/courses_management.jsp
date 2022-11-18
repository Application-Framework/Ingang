<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인강인강 강의 관리</title>
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
						<h1 class="h3 mb-0 text-gray-800">강의 관리</h1>
					</div>
					<hr>
					
					<div class="row mb-3 pl-3">
						<form action="/admin/course/courses-management" role="form" method="GET" class="form-inline">
							<select class="form-control" id="searchCategory" name="searchCategory">
								<option value="course_title" <c:if test="${searchCategory == 'course_title'}">selected</c:if>>강의명</option>
								<option value="teacher_name" <c:if test="${searchCategory == 'teacher_name'}">selected</c:if>>강사명</option>
								<option value="teacher_email" <c:if test="${searchCategory == 'teacher_email'}">selected</c:if>>이메일</option>
							</select>
							<div class="ml-2">
								<input value="${searchKeyword}" type="text" id="searchKeyword" name="searchKeyword" placeholder="검색어를 입력하세요." class="form-control">
								<button type="submit" class="btn px-3 btn-primary">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</form>
						
						<div class="ml-auto">
							<button id="create_course" class="btn btn-primary">강의 생성</button>
							<button onclick="deletePosts()" class="btn btn-danger">강의 선택 삭제</button>
						</div>
					</div>
					
					<div>
						<table class="table table-hover table-white text-center">
							<thead>
								<tr>
									<th>
										<input id="allCheck" type="checkbox" name="allCheck">
									</th>
									<th>강의No</th>
									<th>강의명</th>
									<th>강사명</th>
									<th>가격</th>
									<th>난이도</th>
									<th>생성일</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="course" items="${courseList}">
									<tr>
										<td>
											<input name="rowCheck" type="checkbox" value="${course.oli_no}">			
										</td>
										<td>${course.oli_no}</td>
										<td><a onclick="openCourseDetail(${course.oli_no});" href="javascript:;">${course.title}</a></td>
										<c:choose>
											<%-- 강사일 때 --%>
											<c:when test="${course.olt_no != 0}">
												<td><a onclick="openTeacherDetail(${course.olt_no});" href="javascript:;">${course.teacher_name}</a></td>
											</c:when>
											<%-- 관리자 일 때 --%>
											<c:otherwise>
												<td>${course.teacher_name}</td>
											</c:otherwise>
										</c:choose>
										<td>${course.price}</td>
										<td>${course.level}</td>
										<td>${course.reg_date}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					
					<c:if test="${paging.totalCount > 10}">
						<!-- 게시글 페이징 처리(기준 10개) -->
						<nav aria-label="Page navigation">
							<ul class="pagination justify-content-center">
						
								<!-- 첫 페이지면 Disabled 아니라면 Enabled -->
								<c:choose>
									<c:when test="${paging.pageNo eq paging.firstPageNo }">
										<li class="page-item disabled">
											<a class="page-link" onclick="redirectPage(${paging.prevPageNo})">Previus</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
											<a class="page-link" onclick="redirectPage(${paging.prevPageNo})">Previus</a>
										</li>
									</c:otherwise>
								</c:choose>
								<!-- 페이지 갯수만큼 버튼 생성 -->
								<c:forEach var="i" begin="${paging.startPageNo }" end="${paging.endPageNo }" step="1">
									<c:choose>
										<c:when test="${i eq paging.pageNo }">
											<li class="page-item disabled">
												<a class="page-link" onclick="redirectPage(${i})"><c:out value="${i }"/></a>
											</li>
										</c:when>
										<c:otherwise>
											<li class="page-item">
												<a class="page-link" onclick="redirectPage(${i})"><c:out value="${i }"/></a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<!-- 마지막 페이지면 Disabled 아니라면 Enabled -->
								<c:choose>
									<c:when test="${paging.pageNo eq paging.finalPageNo }">
										<li class="page-item disabled">
											<a class="page-link" onclick="redirectPage(${paging.nextPageNo})">Next</a>
										</li>
									</c:when>
									<c:otherwise>
										<li class="page-item">
											<a class="page-link" onclick="redirectPage(${paging.nextPageNo})">Next</a>
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
   		
   		
	    function redirectPage(pageNo) {
	    	url = new URL(location.origin + location.pathname);
	    	
	    	<c:if test="${searchCategory != null}">
	    		url.searchParams.set('searchCategory', "${searchCategory}");
	    	</c:if>
	    	<c:if test="${searchKeyword != null}">
	    		url.searchParams.set('searchKeyword', "${searchKeyword}");
	    	</c:if>
	    	
	    	url.searchParams.set('page', pageNo);
	    	location.href = url;
	    }
	    
	 	// 게시물 선택 삭제
		function deletePosts() {
    		var oli_noList = [];
			$('input:checkbox[name=rowCheck]').each(function() {
				if($(this).is(":checked")==true){
					oli_noList.push($(this).val());
			    }
			});
			
			if(oli_noList.length == 0) return;
			
			if(confirm("정말 " + oli_noList.join(",") + " 강의를 삭제하시겠습니까?")) {
				$.ajax({
					url: "/admin/course/deleteCourses",
					type: "post",
					traditional: true,
					data: {
						oli_noList: oli_noList
					},
					success: function() {
						location.reload();
					}
				});
			}
		}
    	
	 	$("#create_course").click(function() {
	 		// 창 크기 지정
			var width = window.screen.width * 3 / 4;
			var height = window.screen.height * 85 / 100;

			// pc화면기준 가운데 정렬
			var left = (window.screen.width / 2) - (width/2);
			var top = (window.screen.height / 2) - (height/2);

			var url = "/writeCourse";
			var option = "width = " + width + ", height = " + height + ", left=" + left + ", top = " + top;
			console.log(option);
			window.open(url, "_blank", option);
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
	 	
	 	// 강사 상세 창 띄우기
	 	function openTeacherDetail(olt_no) {
	 		// 창 크기 지정
			var width = window.screen.width * 55 / 100;
			var height = window.screen.height * 85 / 100;
	
			// pc화면기준 가운데 정렬
			var left = (window.screen.width / 2) - (width/2);
			var top = (window.screen.height / 2) - (height/2);
	
			var url = "/admin/teacher/teacherDetail?olt_no="+olt_no;
			var option = "width = " + width + ", height = " + height + ", left=" + left + ", top = " + top;
			console.log(option);
			window.open(url, "_blank", option);
	 	}
    </script>
    
	<!-- Chart -->
	<script src='<c:url value="/resources/js/Chart.min.js"/>'></script>
	
</body>
</html>