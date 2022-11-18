<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${course.title}</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
	
	<script src="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	
	<style>
	
		/* select option css 수정 */
		.choices {
			flex-grow: 1;
			
		} 
		/* .choices[data-type*=select-one] > .choices__list > .choices__list > .choices__item--selectable
		{
			padding-right: 0px;
		}
		.choices[data-type*=select-one] > .choices__list > .choices__list > .choices__item--selectable::after
		{
			display: none;
		} */
	</style>
</head>
<body>
	<div class="container-fluid">
		<div class="pt-3 text-center">
			<h4>강사 상세정보</h4>
		</div>
		<hr>
		<form id="teacher_form">
			<input type="hidden" name="olt_no" value="${teacher.olt_no}"/>
			<input type="hidden" name="m_no" value="${teacher.m_no}"/>
			<div class="row mb-3">
				<div class="row mb-3">
					<div class="col-3">
						<div class="input-group">
							<span class="input-group-text">강사No</span>
							<span class="input-group-text">${teacher.olt_no}</span>
						</div>
					</div>
					<div class="col-4">
						<div class="input-group">
							<span class="input-group-text">강사명</span>
							<input type="text" class="form-control" name="name" value="${teacher.name}">
						</div>
					</div>
					
				</div>
				
				<div class="row mb-3">
					<div class="col-3">
						<div class="input-group">
							<span class="input-group-text">등록일</span>
							<span class="input-group-text">${teacher.reg_date}</span>
						</div>
					</div>
					<div class="col-4">
						<div class="input-group">
							<span class="input-group-text">이메일</span>
							<input type="text" class="form-control" name="email" value="${teacher.email}">
						</div>
					</div>
					<div class="col-4">
						<div class="input-group">
							<span class="input-group-text">핸드폰 번호</span>
							<input type="text" class="form-control" name="phone" value="${teacher.phone}">
						</div>
					</div>
				</div>
				
				<%-- 메인 카테고리 --%>
		   		<div class="row mb-3">
		   			<div class="input-group">
		   				<span class="input-group-text">메인 분야</span>
		   				<select name="main_type_no" class="choices" required>
							<c:forEach var="category" items="${allMainCategoryList}">
							  	<option <c:if test="${teacher.main_type_no == category.main_type_no}">selected</c:if> value="${category.main_type_no}">${category.main_type_name}</option>
							</c:forEach>
						</select>
		   			</div>
		   		</div>
				
				<div class="row mb-3">
					<div class="input-group">
						<span class="input-group-text">강사 소개</span>
						<textarea name="introduction" class="form-control" required>${teacher.introduction}</textarea>
					</div>
				</div>
				
				<div class="row">
					<div class="input-group">
						<span class="input-group-text">Link</span>
						<input type="text" class="form-control" name="link" value="${teacher.link}">
					</div>					
				</div>
				
				<div class="d-flex justify-content-end mt-2">
			        <button type="button" class="btn btn-secondary me-2" onclick="window.close()">Close</button>
			        <button type="button" id="teacher_update" class="btn btn-primary">수정</button>
		    	</div>
		    </div>
    	</form>
    	
    	<hr>
		<div class="text-center">
			<h4>강사의 강의 목록</h4>
		</div>
		<hr>
		<%-- 강사 동영상 목록 --%>
		<c:choose>
			<c:when test="${teacherCourseBoard.size() != 0}">
				<div class="row mb-3 ps-3">
					<form role="form" method="GET" class="d-flex flex-row align-items-center flex-wrap">
						<input type="hidden" name="olt_no" value="${teacher.olt_no}">
						<div class="col-2">
						<select class="form-select" id="searchCategory" name="searchCategory">
							<option value="oli_no" <c:if test="${searchCategory == 'oli_no'}">selected</c:if>>강의No</option>
							<option value="title" <c:if test="${searchCategory == 'title'}">selected</c:if>>강의명</option>
							<option value="price" <c:if test="${searchCategory == 'price'}">selected</c:if>>가격</option>
						</select>
						</div>
						<div class="col-3">
							<div class="ms-2">
								<input value="${searchKeyword}" type="text" id="searchKeyword" name="searchKeyword" placeholder="검색어를 입력하세요." class="form-control">
							</div>
						</div>
						<div class="col-1">
							<button type="button" class="btn btn-primary" onclick="searchTeacherCourse()">
								<i class="bi bi-search"></i>
							</button>
						</div>
					</form>
				</div>
				
				<table class="table table-hover table-white text-center">
					<thead>
						<tr>
							<th>
								<input id="allCheck" type="checkbox" name="allCheck">
							</th>
							<th>강의No</th>
							<th style="width:400px;">강의명</th>
							<th>가격</th>
							<th>난이도</th>
							<th>생성일</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="teacher_course_list">
						<c:forEach var="course" items="${teacherCourseBoard}">
							<tr>
								<td>
									<input name="rowCheck" type="checkbox" value="${course.oli_no}">			
								</td>
								<td>${course.oli_no}</td>
								<td class="text-truncate" style="max-width:300px"><a onclick="openCourseDetail(${course.oli_no});" href="javascript:;">${course.title}</a></td>
								<td>${course.price}</td>
								<td>${course.level}</td>
								<td>${course.reg_date}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<h5 class="text-center">등록된 강의가 없습니다.</h5>
			</c:otherwise>
		</c:choose>
		
		<div id="pagination">
			<c:if test="${paging.totalCount > 10}">
				<!-- 게시글 페이징 처리(기준 10개) -->
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center">
				
						<!-- 첫 페이지면 Disabled 아니라면 Enabled -->
						<c:choose>
							<c:when test="${paging.pageNo eq paging.firstPageNo }">
								<li class="page-item disabled">
									<a class="page-link" onclick="outputCourseList(${paging.prevPageNo})">Previus</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a class="page-link" onclick="outputCourseList(${paging.prevPageNo})">Previus</a>
								</li>
							</c:otherwise>
						</c:choose>
						<!-- 페이지 갯수만큼 버튼 생성 -->
						<c:forEach var="i" begin="${paging.startPageNo }" end="${paging.endPageNo }" step="1">
							<c:choose>
								<c:when test="${i eq paging.pageNo }">
									<li class="page-item disabled">
										<a class="page-link" onclick="outputCourseList(${i})"><c:out value="${i }"/></a>
									</li>
								</c:when>
								<c:otherwise>
									<li class="page-item">
										<a class="page-link" onclick="outputCourseList(${i})"><c:out value="${i }"/></a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<!-- 마지막 페이지면 Disabled 아니라면 Enabled -->
						<c:choose>
							<c:when test="${paging.pageNo eq paging.finalPageNo }">
								<li class="page-item disabled">
									<a class="page-link" onclick="outputCourseList(${paging.nextPageNo})">Next</a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a class="page-link" onclick="outputCourseList(${paging.nextPageNo})">Next</a>
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</nav>
			</c:if>
		</div>
	</div>
	
	<script>
		// 전체 체크박스 클릭 이벤트
	    $("#allCheck").click(function () {
	        $("[name='rowCheck']").prop('checked', $(this).prop('checked'));
	    });	
	
		// 강사 수정
		$("#teacher_update").click(function () {
			<c:if test="${member == null}">
				alert("로그인이 필요합니다");
				return;
			</c:if>
			<c:if test="${member.m_authority == 0}">
				alert("관리자 권한이 없습니다.");
				return;
			</c:if>
			
			
			var form = document.getElementById('teacher_form');
	    	for(var i=0; i < form.elements.length; i++){
    	      if(form.elements[i].value === '' && form.elements[i].hasAttribute('required')){
    	        alert('There are some required fields!');
    	        return false;
    	      }
    	    }
	    	
	    	$.ajax({
	    		url: "/admin/teacher/updateTeacher",
	    		data: $('#teacher_form').serialize(),
	    		type: "post",
	    		success: function() {
	    			alert("수정 성공");
	    			window.opener.location.reload();
	    		}
	    	});
		});
	
		var searchCategory, searchKeyword;
		function searchTeacherCourse() {
			searchCategory = $("#searchCategory").val();
			searchKeyword = $("#searchKeyword").val();
			console.log(searchCategory);
			console.log(searchKeyword);
			outputCourseList(1);
		}
		
		function outputCourseList(pageNo) {
			$.ajax({
				url: "/admin/teacher/teacherDetail",
				type: "post",
				dataType: "html",
				data: {
					olt_no: ${teacher.olt_no},
					searchCategory: searchCategory,
					searchKeyword: searchKeyword,
					page: pageNo
				},
				success: function(html) {
					var list = $(html).find("#teacher_course_list>*");
					var pagination = $(html).find("#pagination>*");
					$("#teacher_course_list").html(list);
					$("#pagination").html(pagination);
				},
				error: function() {
					alert("error");					
				}
			});
		}
		
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
		
		$(function() {
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