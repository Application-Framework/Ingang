<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.css">
	<title>인강인강 강사 관리</title>
	<style>
		/* 폼 라벨 옆에 * 필수 표시 */
		.modal_label--essential {
    		color: #e74a3b!important;
    		margin-left: 0.25rem!important;
    		vertical-align: middle!important;
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
						<h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
					</div>
					<div class="row">
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-primary shadow h-100 py-2">
								<div class="card-body">
									<a href="/admin/teacher/pending-teachers" class="stretched-link"></a>
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div class="text-xs font-weight-bold text-primary text-uppercase mb-1">승인 대기중인 강사</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800">${pendingTotalCount}개</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row mb-3 pl-3">
						<form action="/admin/teacher" role="form" method="GET" class="form-inline">
							<select class="form-control" id="searchCategory" name="searchCategory">
								<option value="olt_no" <c:if test="${searchCategory == 'olt_no'}">selected</c:if>>강사No</option>
								<option value="m_no" <c:if test="${searchCategory == 'm_no'}">selected</c:if>>회원No</option>
								<option value="name" <c:if test="${searchCategory == 'name'}">selected</c:if>>이름</option>
								<option value="email" <c:if test="${searchCategory == 'email'}">selected</c:if>>이메일</option>
								<option value="phone" <c:if test="${searchCategory == 'phone'}">selected</c:if>>phone</option>
							</select>
							<div class="ml-2">
								<input value="${searchKeyword}" type="text" id="searchKeyword" name="searchKeyword" placeholder="검색어를 입력하세요." class="form-control">
								<button type="submit" class="btn px-3 btn-primary">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</form>
						
						<div class="ml-auto">
							<button class="btn btn-primary" data-toggle="modal" data-target="#create_teacher_modal">강사 생성</button>
							<button onclick="deleteTeachers()" class="btn btn-danger">강사 선택 삭제</button>
						</div>
					</div>
					
					<div>
						<table class="table table-hover table-white text-center">
							<thead>
								<tr>
									<th>
										<input id="allCheck" type="checkbox" name="allCheck">
									</th>
									<th>강사No</th>
									<th>회원No</th>
									<th>이름</th>
									<th>이메일</th>
									<th>등록일</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="tb" items="${teacherBoard}">
									<tr>
										<td>
											<input name="rowCheck" type="checkbox" value="${tb.olt_no}">			
										</td>
										<td>${tb.olt_no}</td>
										<td><a href="javascript:void(window.open('/admin/memberDetail?m_no=${tb.m_no}', '상세페이지' , 'width=1280px,height=840px,left=300,top=100, scrollbars=yes, resizable=no'));">${tb.m_no}</a></td>
										<td><a onclick="openTeacherDetail(${tb.olt_no});" href="javascript:;">${tb.name}</a></td>
										<td>${tb.email}</td>
										<td>${tb.reg_date}</td>
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
    		
    		<%-- 강사 생성 모달 --%>
    		<div class="modal fade" id="create_teacher_modal" tabindex="-1" role="dialog">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">강사 생성</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <form action="/admin/teacher/insertTeacher" method="post">
				      <div class="modal-body d-flex">
				          	<div class="flex-glow-1 mb-3 pe-2" style="overflow-y: auto;">
						       	<div class="mb-4">
						       		<div>
						       			<label class="form-label">인강인강 아이디</label><span class="modal_label--essential">*</span>
						       		</div>
						       		<select name="m_no" class="choices" style="float:none;" required>
						        		<c:forEach var="member" items="${memberList}">
						        			<option value="${member.m_no}">${member.m_no} : ${member.m_id}(${member.m_name})</option>
						        		</c:forEach>
						        	</select>
								</div>
								
								<div class="mb-4">
									<div>
										<label class="form-label">이메일</label><span class="modal_label--essential">*</span>
									</div>
						        	<input type="text" name="email" placeholder="자주 사용하는 이메일을 입력해주세요"  class="form-control" required>
								</div>
								
								<div class="mb-4">
									<div>
										<label class="form-label">이름 (실명)</label><span class="modal_label--essential">*</span>
									</div>
						        	<input type="text" name="name" placeholder="실명을 입력해주세요"  class="form-control" required>
								</div>
								
								<div class="mb-4">
									<div>
										<label class="form-label">연락처</label><span class="modal_label--essential">*</span>
									</div>
						        	<input type="text" name="phone" placeholder="010-0000-0000"  class="form-control" required>
								</div>
								
								<div class="mb-4">
									<label class="form-label">희망분야</label><span class="modal_label--essential">*</span>
									<div class="form-text">아직 계획 중인 강의가 없으시다면 지식공유자의 직무와 연관된 분야를 골라주세요.</div>
						        	<select name="main_type_no" class="form-control" style="float:none;" required>
						        		<c:forEach var="category" items="${mainCategoryList}">
						        			<option value="${category.main_type_no}">${category.main_type_name}</option>
						        		</c:forEach>
						        	</select>
								</div>
								
								<div class="mb-4">
									<label class="form-label">나를 소개하는 글</label><span class="modal_label--essential">*</span>
									<div class="form-text">지식공유자님에 대한 소개와, 제작할 강의에 관련된 내용을 적어주세요. 가능한 상세하게 적어주시면 구체적인 안내를 받을 수 있습니다.</div>
									<textarea name="introduction" class="form-control" required></textarea>
								</div>
								
								<div class="mb-4">
									<label class="form-label">나를 표현할 수 있는 링크</label>
									<input type="text" name="link" placeholder="github 링크 or 블로그 링크"  class="form-control" required>
								</div>
							</div>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				        <button type="submit" class="btn btn-primary">제출</button>
				      </div>
			      </form>
			    </div>
			  </div>
			</div>
		</div>
	</div>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
    <script src="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.js"></script>
    
    <script>
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
		function deleteTeachers() {
			var olt_noList = [];
			$('input:checkbox[name=rowCheck]').each(function() {
				if($(this).is(":checked")==true){
					olt_noList.push($(this).val());
			    }
			});
			
			if(olt_noList.length == 0) return;
			
			if(confirm("정말 " + olt_noList.join(",") + " 강사를 삭제하시겠습니까?")) {
				$.ajax({
					url: "/admin/teacher/deleteTeachers",
					type: "post",
					traditional: true,
					data: {
						olt_noList: olt_noList
					},
					success: function() {
						location.reload();
					}
				});
			}
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
	 	
	 	$(function() {
	 		// select option drop box 옵션 설정
	 		new Choices('.choices', {
	 		    removeItemButton: true,
	 		    maxItemCount:1,
	 		    searchResultLimit:10
	 		    //renderChoiceLimit:10
	 		});
	 	});
    </script>
	
</body>
</html>