<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.css">

	<title>인강인강 노트 관리</title>
	<style>
		.table td {
			vertical-align: middle!important;
		    font-size: medium;
		}
		
		.note-editable {
			background-color: #fff;
		}
		
		.choices {
			flex-grow: 1;
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
						<h1 class="h3 mb-0 text-gray-800">노트 관리</h1>
					</div>
					<hr>
					
					<div class="row mb-3 pl-3">
						<form action="/admin/note/notes-management" role="form" method="GET" class="form-inline">
							<select class="form-control" id="searchCategory" name="searchCategory">
								<option value="n_no" <c:if test="${searchCategory == 'n_no'}">selected</c:if>>노트번호</option>
								<option value="oli_no" <c:if test="${searchCategory == 'oli_no'}">selected</c:if>>강의번호</option>
								<option value="title" <c:if test="${searchCategory == 'title'}">selected</c:if>>노트명</option>
								<option value="m_name" <c:if test="${searchCategory == 'm_name'}">selected</c:if>>작성자명</option>
								<option value="m_id" <c:if test="${searchCategory == 'm_id'}">selected</c:if>>작성자ID</option>
							</select>
							<div class="ml-2">
								<input value="${searchKeyword}" type="text" id="searchKeyword" name="searchKeyword" placeholder="검색어를 입력하세요." class="form-control">
								<button type="submit" class="btn px-3 btn-primary">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</form>
						
						<div class="ml-auto">
							<button class="btn btn-primary" data-toggle="modal" data-target="#create_note_modal">노트 생성</button>
							<button onclick="deleteNotes()" class="btn btn-danger">노트 선택 삭제</button>
						</div>
					</div>
					
					<div>
						<table class="table table-hover table-white text-center">
							<thead>
								<tr>
									<th>
										<input id="allCheck" type="checkbox" name="allCheck">
									</th>
									<th>노트No</th>
									<th>강의No</th>
									<th>작성자명</th>
									<th>노트명</th>
									<th>가격</th>
									<th>생성일</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="note" items="${noteList}">
									<tr>
										<td>
											<input name="rowCheck" type="checkbox" value="${note.n_no}">			
										</td>
										<td>${note.n_no}</td>
										<c:if test="${note.oli_no == 0}"><td>${note.oli_no}</td></c:if>
										<c:if test="${note.oli_no != 0}"><td><a onclick="openCourseDetail(${note.oli_no});" href="javascript:;">${note.oli_no}</a></td></c:if>
										<c:if test="${note.m_name == null}"><td>관리자</td></c:if>
										<c:if test="${note.m_name != null}"><td><a href="javascript:void(window.open('/admin/memberDetail?m_no=${note.m_no}', '상세페이지' , 'width=1280px,height=840px,left=300,top=100, scrollbars=yes, resizable=no'));">${note.m_name}</a></td></c:if>
										<td><a onclick="openNoteDetail(${note.n_no});" href="javascript:;">${note.title}</a></td>
										<td>${note.price}</td>
										<td>${note.reg_date}</td>
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
    		
    		
    		<%-- 노트 생성 모달 --%>
		    <div class="modal fade" id="create_note_modal" tabindex="-1" role="dialog">
			  <div class="modal-dialog modal-lg" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">노트 생성</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
		       	  <form id="create_note_form" action="/admin/note/createNote" method="post">
		      	    <div class="modal-body">
			        	<div class="row mb-1">
			        		<label class="col-2 col-form-label fs-5 text-start">참조 강의</label>
			        		<select name="oli_no" class="col-10 choices px-3" required>
			        			<option value="0">0 : 임시</option>
			        			<c:forEach var="course" items="${courseList}">
			        				<option value="${course.oli_no}">${course.oli_no} : ${course.title}</option>
			        			</c:forEach>
			        		</select>
			        	</div>
			        	<div class="row mb-1">
			        		<label class="col-2 col-form-label fs-5 text-start">작성자</label>
			        		<select name="m_no" class="col-10 choices" required>
			        			<option value="0">0 : 관리자</option>
			        			<c:forEach var="member" items="${memberList}">
			        				<option value="${member.m_no}">${member.m_no} : ${member.m_name}(${member.m_id })</option>
			        			</c:forEach>
			        		</select>
			        	</div>
			        	
			    		<div class="row mb-1">
			   				<label class="col-sm-2 col-form-label fs-5 text-start">노트명</label>
			   				<div class="col-sm-10">
			    				<input type="text" class="form-control" name="title" required/>
			    			</div>
			    		</div>
			    		
			    		<div class="row mb-1">
			   				<label class="col-sm-2 col-form-label fs-5 text-start">가격</label>
			   				<div class="col-sm-2">
			    				<input type="number" class="form-control" name="price" min="0" required/>
			    			</div>
			    		</div>
			    		
			    		<div class="row mb-1">
							<label class="col-sm-2 col-form-label fs-5 text-start">노트 공개</label>
							<div class="col-sm-2 d-flex align-items-center">
			    				<input class="form-check-input" type="checkbox" name="enable" style="margin:0; transform:scale(1.5)">
			    			</div>
			    		</div>
			    		
			    		<div class="row mb-4">
			    			<textarea id="summernote" name="content"></textarea>
			    		</div>
		              </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
				        <button id="create_note_btn" type="submit" class="btn btn-primary">노트 생성</button>
				      </div>
			      </form>
			    </div>
			  </div>
			</div>
		</div>
	</div>
    
    
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
    <script src="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.js"></script>
    
    <script>
   		// 전체 체크박스 클릭 이벤트
	    $("#allCheck").click(function () {
	        $("[name='rowCheck']").prop('checked', $(this).prop('checked'));
	    });
   		
   		/* $("#create_note_btn").click(function () {
   			$.ajax({
   				url: "/admin/note/createNote",
   				type: "post",
   				data: $("#create_note_form").serialize(),
   				success: function() {
   					window.location.reload();
   				}
   			})
   		}); */
   		 
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
		function deleteNotes() {
    		var n_noList = [];
			$('input:checkbox[name=rowCheck]').each(function() {
				if($(this).is(":checked")==true){
					n_noList.push($(this).val());
			    }
			});
			
			if(n_noList.length == 0) return;
			
			if(confirm("정말 " + n_noList.join(",") + " 노트를 삭제하시겠습니까?")) {
				$.ajax({
					url: "/admin/note/deleteNotes",
					type: "post",
					traditional: true,
					data: {
						n_noList: n_noList
					},
					success: function() {
						location.reload();
					}
				});
			}
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
	 	
		// 강의 상세 창 띄우기
	 	function openNoteDetail(n_no) {
	 		// 창 크기 지정
			var width = window.screen.width * 55 / 100;
			var height = window.screen.height * 85 / 100;

			// pc화면기준 가운데 정렬
			var left = (window.screen.width / 2) - (width/2);
			var top = (window.screen.height / 2) - (height/2);

			var url = "/admin/note/"+n_no;
			var option = "width = " + width + ", height = " + height + ", left=" + left + ", top = " + top;
			console.log(option);
			window.open(url, "_blank", option);
	 	}
	 	
	 	$(function() {
			// summernote 옵션 설정
			$('#summernote').summernote({
				placeholder: '노트를 소개할 내용을 적으세요',
				tabsize: 4,
				height: 335,
				width: '100%',
				toolbar: [
					['style', ['style']],
					['font', ['bold', 'underline', 'clear']],
					['color', ['color']],
					['para', ['ul', 'ol', 'paragraph']],
					['table', ['table']],
					['insert', ['link', 'picture', 'video']],
					['view', ['fullscreen', 'codeview', 'help']]
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
							uploadSummernoteImageFileOfNote(files[i], this);
						}
					}
				}
			});
			
			// choices.js 적용
			new Choices('.choices', {
			    removeItemButton: true,
			    maxItemCount:1,
			    searchResultLimit:10
			    //renderChoiceLimit:10
			});
		});
	 	
	 	// 임시 이미지 등록
		function uploadSummernoteImageFileOfNote(file, editor) {
			data = new FormData();
			data.append("file", file);
			$.ajax({
				data : data,
				type : "POST",
				url : "/uploadSummernoteImageFileOfNote",
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
				success : function(data) {
					$(editor).summernote('insertImage', data.url);
				}
			});
		}
    </script>
    
	<!-- Chart -->
	<script src='<c:url value="/resources/js/Chart.min.js"/>'></script>
	
</body>
</html>