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
	
	<script src="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	
</head>
<body>
	<div class="container-fluid">
		<div class="pt-3 text-center">
			<h4>강의 상세정보</h4>
		</div>
		<hr>
		
		<div class="row mb-3">
			<div class="col-5">
				<div class="input-group">
					<span class="input-group-text">강의 표지</span>
					<img id="thumbnail" src="<c:url value='${course.img_path}'/>" width="200px"/>
				</div>
			</div>
			
			<div class="col-7">
				<div class="row mb-3">
					<div class="col">
						<div class="input-group">
							<span class="input-group-text" id="basic-addon1">강의명</span>
							<input type="text" class="form-control" name="title" value="${course.title}">
						</div>
					</div>
					<div class="col">
						<div class="input-group">
							<span class="input-group-text">강사명</span>
							<span class="input-group-text"><a href="">${teacherService.getTeacherInfo(course.olt_no).name}</a></span>
						</div>
					</div>
				</div>
				
				<div class="row mb-3">
					<div class="input-group">
						<span class="input-group-text">강의 소개</span>
						<textarea class="form-control">${course.introduction}</textarea>
					</div>
				</div>
				
				<div class="row mb-3">
					
					<%-- 좌측정렬하려했는데 안먹힘 --%>
					<div class="col">
						<div class="input-group ms-auto">
							<span class="input-group-text">난이도</span>
							<input type="text" class="form-control" name="level" value="${course.level}">
						</div>
					</div>
					
					<div class="col">
						<div class="input-group">
							<span class="input-group-text">강의 가격</span>
							<input type="number" class="form-control" name="price" value="${course.price}">
						</div>
					</div>
				</div>
				
				<div class="row mb-3">
					<div class="col">
						<div class="input-group">
							<span class="input-group-text">강의 번호</span>
							<span class="input-group-text">${course.oli_no}</span>
						</div>
					</div>
					<div class="col">
						<div class="input-group">
							<span class="input-group-text">원본 번호</span>
							<span class="input-group-text">${origin_oli_no}</span>
						</div>
					</div>
				</div>
				
				<div class="row mb-3">
					<div class="col">
						<div class="input-group">
							<span class="input-group-text">생성일</span>
							<span class="input-group-text">${course.reg_date}</span>
						</div>
					</div>
					<div class="col">
						<div class="input-group">
							<span class="input-group-text">수정일</span>
							<c:if test="${course.update_date != null}">
								<span class="input-group-text">${course.update_date}</span>
							</c:if>
							<c:if test="${course.update_date == null}">
								<span class="input-group-text">NULL</span>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<%-- 메인 카테고리 --%>
   		<div class="row mb-3">
  				<label class="col-sm-2 col-form-label fs-5">메인 카테고리</label>
  				<div class="col-sm-10">
				<select name="mainCategory" id="mainCategory" onchange="changeMainCategory()" required>
					<option value="">Select Main Category</option>
					<c:forEach var="category" items="${allMainCategoryList}">
					  	<option <c:if test="${courseService.getMainTypeOfCourse(course.oli_no) != null}">selected</c:if> value="${category.main_type_no}">${category.main_type_name}</option>
					</c:forEach>
				</select>
   			</div>
   		</div>
		
		<%-- 서브 카테고리 --%>
		<div class="row mb-3">
			<label class="col-sm-2 col-form-label fs-5">서브 카테고리</label>
			<div class="col-sm-10" id="subCategoryParents">
				<select name="subCategorys" id="subCategorys" multiple required>
					<c:forEach var="category" items="${allSubCategoryList}">
					  	<option <c:if test="${courseService.containsInCategoryList(myCategoryList, category.sub_type_abbr) == true}">selected</c:if> value="${category.sub_type_abbr}">${category.sub_type_name}</option>
					</c:forEach>
				</select>
   			</div>
		</div>
		
		<%-- 강의 태그 --%>
   		<div class="row mb-3">
  				<label class="col-sm-2 col-form-label fs-5">강의 태그</label>
  				<div class="col-sm-10">
				<select name="tags" id="tags" multiple required>
					<c:forEach var="tag" items="${allTagList}">
					  	<option <c:if test="${courseService.containsInTagList(myTagList, tag.tag_abbr) == true}">selected</c:if> value="${tag.tag_abbr}">${tag.tag_name}</option>
					</c:forEach>
				  
				</select>
   			</div>
   		</div>
		
		<%-- 강의 내용 --%>
		<div class="row mb-3">
			<div>
				<textarea class="summernote" id="content" name="content" rows="10">${course.content}</textarea>
			</div>
		</div>
		
		<%-- 판매 내역 --%>
		<hr>
		<div class="text-center">
			<h4>강의 판매 내역</h4>
		</div>
		<hr>
		<table class="table table-hover text-center mb-4">
			<thead>
				<tr>
				  <th scope="col">#</th>
				  <th scope="col">강의명</th>
				  <th scope="col">회원</th>
				  <th scope="col">결제금액</th>
				  <th scope="col">결제일</th>
				  <th scope="col">결제상태</th>
				</tr>
			</thead>
			<tbody>
	  			<c:forEach var="oh" items="${orderHistoryList}">
		  			<tr>
				    	<th scope="row">${oh.hol_no}</th>
					    <td><a href="">${courseService.getCourseDetail(oh.oli_no).title}</a></td>
					    <td><a href="">${memberService.getMemberByM_no(oh.m_no).m_name}</a></td>
					    <td>${oh.payment}</td>
					    <td>${oh.payment_date}</td>
					    <td><c:if test="${oh.payment_status == 0}">미완료</c:if><c:if test="${oh.payment_status == 1}">결제완료</c:if></td>
			  		</tr>
	  			</c:forEach>
			</tbody>
		</table>
		
		<%-- 강의 수강생 목록 --%>
		<%-- <hr>
		<div class="text-center">
			<h4>강의 수강생 목록</h4>
		</div>
		<hr>
		<table class="table table-hover text-center mb-4">
			<thead>
				<tr>
				  <th scope="col">#</th>
				  <th scope="col">ID</th>
				  <th scope="col">이름</th>
				  <th scope="col">가입날짜</th>
				</tr>
			</thead>
			<tbody>
	  			<c:forEach var="oh" items="${orderHistoryList}">
		  			<tr>
				    	<th scope="row">${oh.m_no}</th>
					    <td><a href="">${memberService.getMemberByM_no(oh.m_no).m_id}</a></td>
					    <td><a href="">${memberService.getMemberByM_no(oh.m_no).m_name}</a></td>
					    <td>${memberService.getMemberByM_no(oh.m_no).reg_date}</td>
			  		</tr>
	  			</c:forEach>
			</tbody>
		</table> --%>
		
		<%-- 수강생 목록 --%>
		
		<div class="float-end mb-3">
			<button class="btn btn-primary" style="width:5rem;">수정</button>
		</div>
	</div>
	
	<script>
	
		$(function() {
			// select option drop box 옵션 설정
	    	choicesMainCategory = new Choices('#mainCategory', {
	            removeItemButton: true,
	            maxItemCount:1,
	            searchResultLimit:10
	            //renderChoiceLimit:10
	        }); 
	    	
	    	choicesSubCategorys = new Choices('#subCategorys', {
	            removeItemButton: true,
	            maxItemCount:5,
	            searchResultLimit:10
	            //renderChoiceLimit:10
	        }); 
	    	
	    	choicesTags = new Choices('#tags', {
	            removeItemButton: true,
	            maxItemCount:5,
	            searchResultLimit:10
	            //renderChoiceLimit:10
	        }); 
		
			// summernote 옵션 설정
			$('.summernote').summernote({
				placeholder: 'write the text',
				tabsize: 4,
				height: 500,
				toolbar: [
					['style', ['style']],
					['font', ['bold', 'underline', 'clear']],
					['color', ['color']],
					['para', ['ul', 'ol', 'paragraph']],
					['table', ['table']],
					['insert', ['link', 'picture', 'video']],
					['view', ['codeview', 'help']]
				],
				codemirror: { // codemirror options 
			    	theme: 'monokai',
					mode: 'htmlmixed',
					lineNumbers: 'true'
			  	},
				callbacks : { 
					onImageUpload : function(files, editor, welEditable) {
						// 파일 업로드(다중업로드를 위해 반복문 사용)
						for (var i = files.length - 1; i >= 0; i--) {
							uploadSummernoteImageFile(files[i], this);
						}
					},
					onPaste: function (e) {
						var clipboardData = e.originalEvent.clipboardData;
						if (clipboardData && clipboardData.items && clipboardData.items.length) {
							var item = clipboardData.items[0];
							if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
								e.preventDefault();
							}
						}
					}
				}
			});
		});
		// 메인 카테고리가 바뀌었을 때 서브 카테고리 불러오기
	   	function changeMainCategory() {
			var mainCategory = 0;
	   		if($("#mainCategory").val() != null && $("#mainCategory").val() != "") {
	   			mainCategory = $("#mainCategory").val();
	   		}
			
	   		console.log("mainCategory : " + mainCategory);
	   		
	   		$.ajax({
	   			url: '/writeCourse',
	   			type: 'post',
	   			dataType: 'html',
	   			data: {
	   				main_type_no: mainCategory
	   			},
	   			success: function(html) {
	   				subCategoryParents = $(html).find('#subCategoryParents>*');
	   				console.log(subCategoryParents);
	   				$('#subCategoryParents').html(subCategoryParents);
	   				choicesSubCategorys = new Choices('#subCategorys', {
	   		            removeItemButton: true,
	   		            maxItemCount:5,
	   		            searchResultLimit:10
	   		        }); 
	   			}
	   		});
	   	}
	</script>
</body>
</html>