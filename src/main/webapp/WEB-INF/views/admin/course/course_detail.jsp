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
		.choices[data-type*=select-one] > .choices__list > .choices__list > .choices__item--selectable
		{
			padding-right: 0px;
		}
		.choices[data-type*=select-one] > .choices__list > .choices__list > .choices__item--selectable::after
		{
			display: none;
		}
	</style>
</head>
<body>
	<div class="container-fluid">
		<div class="pt-3 text-center">
			<h4>강의 상세정보</h4>
		</div>
		<hr>
		<form id="frmCourse">
			<input type="hidden" name="oli_no" value="${course.oli_no}"/>
			<div class="row mb-3">
				<div class="col-5">
					<div class="input-group">
						<input type="file" class="input-group-text" name="thumbnail" accept="image/*" onchange="loadFile(event)"/><br>
						<img id="thumbnail" src="<c:url value='${course.img_path}'/>" style="padding-top:5px; width:100%; max-height:250px; object-fit: contain;"/>
					</div>
				</div>
				
				<div class="col-7">
					<div class="row mb-3">
						<div class="col">
							<div class="input-group">
								<span class="input-group-text" id="basic-addon1">강의명</span>
								<input type="text" class="form-control" name="title" value="${course.title}" required>
							</div>
						</div>
						<div class="col">
							<div class="input-group">
								<span class="input-group-text">강사명</span>
								<select name="olt_no" id="teacher" required>
									<option value="0" <c:if test="${course.olt_no == 0}">selected</c:if>>0 : 관리자</option>
									<c:forEach var="teacher" items="${teacherList}">
									  	<option <c:if test="${course.olt_no == teacher.olt_no}">selected</c:if> value="${teacher.olt_no}">${teacher.olt_no} : ${teacher.name}</option>
									</c:forEach>
								</select>
								
								<%-- <c:if test="${course.olt_no == 0}">
									<span class="input-group-text">관리자</span>
								</c:if>
								<c:if test="${course.olt_no != 0}">
									<span class="input-group-text"><a href="">${teacherService.getTeacherInfo(course.olt_no).name}</a></span>
								</c:if> --%>
							</div>
						</div>
					</div>
					
					<div class="row mb-3">
						<div class="input-group">
							<span class="input-group-text">강의 소개</span>
							<textarea name="introduction" class="form-control" required>${course.introduction}</textarea>
						</div>
					</div>
					
					<div class="row mb-3">
						
						<%-- 좌측정렬하려했는데 안먹힘 --%>
						<div class="col">
							<div class="input-group ms-auto">
								<span class="input-group-text">난이도</span>
								<input type="text" class="form-control" name="level" value="${course.level}" required>
							</div>
						</div>
						
						<div class="col">
							<div class="input-group">
								<span class="input-group-text">강의 가격</span>
								<input type="number" class="form-control" name="price" value="${course.price}" required>
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
						<c:if test="${course.origin == 0}">
							<div class="col">
								<div class="input-group">
									<span class="input-group-text">원본 번호</span>
									<span class="input-group-text">${origin_oli_no}</span>
								</div>
							</div>
						</c:if>
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
						  	<option <c:if test="${courseService.containsInCategoryList(myCategoryList, category.sub_type_abbr) == true}">selected</c:if> value="${category.sub_type_no}">${category.sub_type_name}</option>
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
						  	<option <c:if test="${courseService.containsInTagList(myTagList, tag.tag_abbr) == true}">selected</c:if> value="${tag.tag_no}">${tag.tag_name}</option>
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
			
			<%-- 강의 동영상 --%>
			<div class="row mb-3" id="videoSection">
				<div class="fs-4 mb-2">강의 동영상</div>
    			<div class="row ps-4 pb-2">
    				<div class="col-1 d-flex align-items-center">
    					#
    				</div>
    				<div class="col-2 d-flex align-items-center">
    					<label>동영상 제목</label>
    				</div>
    				<div class="col-7 d-flex align-items-center">
    					<label>주소</label>
    				</div>
    				<div class="col-1 fs-2">
    					<a href="javascript:;" onclick="addVideoSlot('신규', '', '')"><i class="bi bi-plus-circle"></i></a>
    				</div>
    				<div class="col-1 fs-2">
    					<a href="javascript:;" onclick="removeVideoSlot()"><i class="bi bi-dash-circle"></i></a>
    				</div>
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
						    <td><a href="">${memberService.getMemberByM_no(oh.m_no).m_name}</a></td>
						    <td>${oh.payment}</td>
						    <td>${oh.payment_date}</td>
						    <td><c:if test="${oh.payment_status == 0}">미완료</c:if><c:if test="${oh.payment_status == 1}">결제완료</c:if></td>
				  		</tr>
		  			</c:forEach>
				</tbody>
			</table>
		
			<div class="float-end mb-3">
				<button id="course_update" class="btn btn-primary" type="button" style="width:5rem;">수정</button>
			</div>
		</form>
	</div>
	
	<script>
		
		// 강의 수정
		$("#course_update").click(function () {
			<c:if test="${member == null}">
				alert("로그인이 필요합니다");
				return;
			</c:if>
			<c:if test="${member.m_authority != 1}">
				alert("관리자 권한이 없습니다.");
				return;
			</c:if>
			
			
			var form = document.getElementById('frmCourse');
	    	for(var i=0; i < form.elements.length; i++){
    	      if(form.elements[i].value === '' && form.elements[i].hasAttribute('required')){
    	        alert('There are some required fields!');
    	        return false;
    	      }
    	    }
	    	
	    	if ($('.summernote').summernote('isEmpty')) {
	    		alert('editor content is empty');
	    		return false;
	    	}
	    	
	    	var form = $('#frmCourse')[0];
	        var formData = new FormData(form);
	    	$.ajax({
		 		cache: false,
		        contentType: false,
		        processData: false,
	    		url: "/admin/course/updateCourse",
	    		data: formData,
	    		type: "post",
	    		success: function() {
	    			alert("수정 성공");
	    			window.opener.location.reload();
	    		}
	    	});
		});
		
		// 썸네일 미리보기
		var loadFile = function(event) {
		    var thumbnail = document.getElementById('thumbnail');
		    thumbnail.src = URL.createObjectURL(event.target.files[0]);
		    thumbnail.onload = function() {
		      URL.revokeObjectURL(thumbnail.src) // free memory
		    }
	  	};
	
		$(function() {
			new Choices('#teacher', {
	            removeItemButton: true,
	            maxItemCount:1,
	            searchResultLimit:10
	            //renderChoiceLimit:10
	        }); 
			
			// select option drop box 옵션 설정
	    	new Choices('#mainCategory', {
	            removeItemButton: true,
	            maxItemCount:1,
	            searchResultLimit:10
	            //renderChoiceLimit:10
	        }); 
	    	
	    	new Choices('#subCategorys', {
	            removeItemButton: true,
	            maxItemCount:5,
	            searchResultLimit:10
	            //renderChoiceLimit:10
	        }); 
	    	
	    	new Choices('#tags', {
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
			
	   		$.ajax({
	   			url: '/writeCourse',
	   			type: 'post',
	   			dataType: 'html',
	   			data: {
	   				main_type_no: mainCategory
	   			},
	   			success: function(html) {
	   				subCategoryParents = $(html).find('#subCategoryParents>*');
	   				$('#subCategoryParents').html(subCategoryParents);
	   				new Choices('#subCategorys', {
	   		            removeItemButton: true,
	   		            maxItemCount:5,
	   		            searchResultLimit:10
	   		        }); 
	   			}
	   		});
	   	}
		
	    // 임시 이미지 등록
		function uploadSummernoteImageFile(file, editor) {
			data = new FormData();
			data.append("file", file);
			$.ajax({
				data : data,
				type : "POST",
				url : "/courseUploadSummernoteImageFile",
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
				success : function(data) {
					$(editor).summernote('insertImage', data.url);
				}
			});
		}
		
	 	// 강의 동영상 슬롯 동적 추가
	    var cnt = 0;
	    
	    function addVideoSlot(olv_no, title, file_name) {
	    	cnt = cnt + 1;
	    	var tags = "<div class='row ps-4 pb-2' id='v_"+cnt+"'>";
	    	tags += "<div class='col-1'>No."+olv_no+"</div>";
			tags += "<div class='col-2'><input type='text' class='form-control' name='video_titles' value='"+title+"' required/></div>";
			tags += "<div class='col-7'><input type='text' class='form-control' name='video_paths' value='"+file_name+"' required/></div>";
			tags += "<div class='col-1 fs-2'><a href='javascript:;' id='up_slot'><i class='bi bi-arrow-up-circle'></i></a></div>";
			tags += "<div class='col-1 fs-2'><a href='javascript:;' id='down_slot'><i class='bi bi-arrow-down-circle'></i></a></div>";
			tags += "<input type='hidden' name='olv_no[]' value='"+olv_no+"'/>";
			tags += "</div>";
			
			
	    	$('#videoSection').append(tags);
	    	changedForm = true;
	    }
	    
	    function removeVideoSlot() {
	    	if(cnt == 1) return;
	    	$('#v_'+cnt).remove();
	    	cnt = cnt - 1;
	    	changedForm = true;
	    }
		
	    $(document).on('click', '#up_slot', function() {
	    	var _idx = $(this).parent().parent().attr('id');
	    	var idx = parseInt(_idx.substring(2));
	    	if(idx == 1) return;
	    	
	    	$("#v_"+idx).insertBefore("#v_"+(idx-1));
	    	$("#v_"+idx).attr("id", "v_t")
	    	$("#v_"+(idx-1)).attr("id", "v_"+idx);
	    	$("#v_t").attr("id", "v_"+(idx-1));
	    });
	    
	    $(document).on('click', '#down_slot', function() {
	    	var _idx = $(this).parent().parent().attr('id');
	    	var idx = parseInt(_idx.substring(2));
	    	if(idx == cnt) return;
	    	
	    	$("#v_"+idx).insertAfter("#v_"+(idx+1));
	    	$("#v_"+idx).attr("id", "v_t")
	    	$("#v_"+(idx+1)).attr("id", "v_"+idx);
	    	$("#v_t").attr("id", "v_"+(idx+1));
	    });
	    
		<c:choose>
			<c:when test="${videoList != null}">
				<c:forEach var="video" items="${videoList}">
					addVideoSlot('${video.olv_no}', '${video.title}', '${video.s_file_name}');
				</c:forEach>
			</c:when>
			
			<c:otherwise>
				addVideoSlot('', '');
			</c:otherwise>
		</c:choose>
	</script>
</body>
</html>