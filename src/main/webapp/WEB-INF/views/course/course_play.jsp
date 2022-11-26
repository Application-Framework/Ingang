<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>${video.title}</title>
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/favicon.ico'/>">
	
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
	<link rel="stylesheet" href="<c:url value='/resources/css/course/course_play.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/fontawesome-all.min.css'/>">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<%-- 탭 , 모달기능 사용하기 위해 import --%>
	<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> -->
  	<%-- summernote bootsrap 필요 없는 버전 --%>
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
    
	
  
	<style>
		.modal{ 
            position:fixed; 
            width:100%; height:100%; 
            background: rgba(0,0,0,0.1); 
            top:0; 
            left:0; 
            display:none;
        }

        .modal_content {
            background:#fff;
            position: fixed; 
            top:50%; 
            left:50%;
            transform : translate(-50%, -50%);
            box-sizing:border-box; 
            line-height:23px;
            border-style: solid;
            border-radius: 10px;
        }
        
        .note-editable { background-color: white !important;}
        
	</style>
</head>
<body>
	<div class="bg-dark d-flex vh-100" id="main">
        <div class="d-flex flex-grow-1 flex-column">
            <iframe class="w-100 h-100" width="1120" height="630" src="${video.s_file_name}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            <div class="footer" style="height: 50px;">
            	<div class="h-100 d-flex justify-content-evenly align-items-center">
            		<div class="col-3 text-center">
		           		<c:choose>
			            	<c:when test="${order == videoList[0].order}">
			            		<a class="link-light">첫번째수업</a>
			            	</c:when>
			            	<c:otherwise>
			            		<a class="link-light" href="${order-1}">이전 수업</a>
			            	</c:otherwise>
		            	</c:choose>
	            	</div>
	            	<div class="col-3 text-center">
		            	<c:choose>
			            	<c:when test="${order == videoList[videoList.size()-1].order}">
			            		<a class="link-light">마지막수업</a>
			            	</c:when>
		            		<c:otherwise>
		            			<a class="link-light" href="${order+1}">다음 수업</a>
		            		</c:otherwise>
		           		</c:choose>
	           		</div> 
           		</div>
            </div>
        </div>
		
		
		
		<%-- contents --%>
        <div class="tab-content slidebar" id="mySlidebar">
       		<div class="tab-content">
        		<%-- 목차 불러오기 --%>
        		<div id="content" class="tab-pane">
		        	<div class="p-3 d-flex justify-content-between">
			        	<span class="fs-4 fw-bold">목차</span>
			        	<a href="#" onclick="closeContents()" id="closeBtn"><i class="fs-4 bi bi-x-lg" style="-webkit-text-stroke: 1px;"></i></a>
		        	</div>
		        	
		        	<c:forEach var="video" items="${videoList}">
			        	<div class="content d-flex align-items-center px-3 py-3 <c:choose><c:when test='${order == video.order}'>selected</c:when><c:otherwise>notSelected</c:otherwise></c:choose>">
			        		<i class="fs-5 bi bi-play-circle me-2"></i>
				       		<div class="fs-5">${video.title}</div>
				       		<a class="stretched-link" href="${video.order}"></a>
			       		</div>
		       		</c:forEach>
	       		</div> 
	       		
	       		<%-- 커뮤니티 불러오기 --%>
	       		<div id="community" class="tab-pane">
		        	<div class="p-3 d-flex justify-content-between">
			        	<span class="fs-4 fw-bold">커뮤니티</span>
			        	<a href="#" onclick="closeContents()" id="closeBtn"><i class="fs-4 bi bi-x-lg" style="-webkit-text-stroke: 1px;"></i></a>
		        	</div>
		        	
		        	<%-- 커뮤니티 카테고리 선택 버튼 --%>
		        	<div class="px-3 pt-3">
		        		<input type="radio" class="btn-check" name="btn_classify" id="btn_classify_2" onclick="setClassify(this, 2);" checked />
						<label class="btn btn-outline-danger" for="btn_classify_2">질문</label>
						
						<input type="radio" class="btn-check" name="btn_classify" id="btn_classify_1" onclick="setClassify(this, 1);" />
						<label class="btn btn-outline-danger" for="btn_classify_1">자유주제</label>
						
						<input type="radio" class="btn-check" name="btn_classify" id="btn_classify_4" onclick="setClassify(this, 4);" />
						<label class="btn btn-outline-danger" for="btn_classify_4">커뮤니티</label>
		        	</div>
		        	
		        	<%-- 검색 필드 --%>
		        	<div class="input-group p-3">
					  <input type="text" class="form-control" id="search">
					  <button class="btn btn-outline-secondary" type="button" id="search_btn"><svg width="14" aria-hidden="true" focusable="false" data-prefix="far" data-icon="search" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><title>SearchIcon</title><path fill="#868E96" d="M508.5 468.9L387.1 347.5c-2.3-2.3-5.3-3.5-8.5-3.5h-13.2c31.5-36.5 50.6-84 50.6-136C416 93.1 322.9 0 208 0S0 93.1 0 208s93.1 208 208 208c52 0 99.5-19.1 136-50.6v13.2c0 3.2 1.3 6.2 3.5 8.5l121.4 121.4c4.7 4.7 12.3 4.7 17 0l22.6-22.6c4.7-4.7 4.7-12.3 0-17zM208 368c-88.4 0-160-71.6-160-160S119.6 48 208 48s160 71.6 160 160-71.6 160-160 160z"></path></svg></button>
					</div>
					
					<%-- 게시물 영역 --%>
					<div id="community_content" class="tab-content">
						<div class="tab-pane fade show active" id="qwe">
							<c:forEach var="list" items="${cbList}">
								<article class="blog_item m-3 border rounded">
									<div class="blog_details p-2">
										<a class="d-inline-block" href="/communityBoardRead?cb_no=${list.cb_no}&classify=1&isOnlineLecture=${pageNo}">
											<font size="1px;">NO. <c:url value="${list.cb_no}"/></font>
											<p class="fw-bold">${fn:substring(list.title, 0, 35)}</p>
										</a>
										<p style="font-size:14px"><c:url value="${fn:substring(list.content,0,200)}"/></p>
										<span><i class="fa fa-user me-2"></i>${memberService.getMemberByM_no(list.m_no).m_name}</span>
										<span class="float-end me-2"><i class="fa fa-comments me-2"></i>${cbService.selectCommunityBoardReplyCount(list.cb_no)}</span>
										<span class="float-end me-4"><i class="fa fa-heart me-2"></i>${cbService.selectCommunityBoardGoodCount(list.cb_no)}</span>
										<%-- <li><i class="fa fa-clock-o"> </i><font size="2" color="#848484"><c:url value="${list.reg_date}"/></font></li> --%>
									</div>
								</article>
							</c:forEach>
						</div>
					</div>
					<%-- 게시물 영역 끝 --%>
	       		</div>
	       		
	       		<%-- 노트 불러오기 --%>
	       		<div id="note" class="tab-pane">
		        	<div class="p-3 d-flex justify-content-between">
			        	<span class="fs-4 fw-bold">노트</span>
			        	<a href="#" onclick="closeContents()" id="closeBtn"><i class="fs-4 bi bi-x-lg" style="-webkit-text-stroke: 1px;"></i></a>
		        	</div>
		        	
		        	<div id="noteArticle" class="p-3">
			        	<%-- 생성한 노트가 있을 때 --%>
			        	<c:if test="${note != null}">
			        		<div id="noteArticleResult">
			        			<c:if test="${noteArticle != null}">
							  		<%-- 수정 | 삭제 버튼 --%>
							  		<div class="d-flex flex-row-reverse mb-2">
										<div class="collapse show" id="editNoteArticle">
								    		<span><a data-bs-toggle="collapse" href="#editNoteArticle">수정</a></span>
								    		<span>| <a href="javascript:;" onclick="deleteNoteArticle()">삭제</a></span>
							  			</div>
							  			<span class="collapse" id="editNoteArticle"><a data-bs-toggle="collapse" href="#editNoteArticle">닫기</a></span>
									</div>
						  			<%-- 노트 글 출력 --%>
							  		<div class="collapse show" id="editNoteArticle">
							  			<h5 class="fw-bold mb-3 text-center">${noteArticle.title}</h5>
							  			<c:out value="${noteArticle.content}" escapeXml="false"/>
							  		</div>
								</c:if>
					  		</div>
			        		
			        		<%-- 노트 글 수정 폼--%>
							<div class="collapse <c:if test="${noteArticle == null}">show</c:if>" id="editNoteArticle">
				        		<%-- 새로고침 없이 저장하기 위해 더미 iframe 생성 --%>
					        	<iframe name="dummyframe" id="dummyframe" style="display: none"></iframe>
					        	<form action="/saveNoteArticle" target="dummyframe" method="post">
					        		<input type="hidden" name="oli_no" value="${oli_no}"/>
					        		<input type="hidden" name="order" value="${order}"/>
						        	<input class="form-control mb-1" type="text" id="na_title" name="title" value="${noteArticle.title}" placeholder="노트 제목" />
					        		<textarea id="summernote_article" name="content" rows=5 placeholder="content">${noteArticle.content}</textarea>
						        	<div class="d-flex flex-row-reverse mt-2">
						        		<input type="button" data-bs-toggle="collapse" href="#editNoteArticle" onclick="saveNoteArticle()" value="저장" class="btn btn-dark text-end"/>
						        	</div>
					        	</form>
				        	</div>
			        	</c:if>
		        	</div>
		        	
		        	<%-- 해당 회원이 이 강의에서 생성한 노트가 없을 때 강의 생성 버튼 + 모달창 --%>
		        	<c:if test="${note == null}">
		        		<p class="fs-5 p-2">이 강의에서 생성된 노트가 없습니다</p>
		        		<div class="d-flex justify-content-center">
			        		<a href="javascript:;" onclick="openCreateCourseModal()" class="btn btn-warning">노트 생성</a>
			        	</div>
			        	
			        	<div class="modal">
					        <div class="modal_content card" style="width:50rem; height:40rem;">
					            <div class="card-body m-3">
					                <form action="/createNote" method="post">
					                	<input type="hidden" name="oli_no" value="${oli_no}"/>
					                	<input type="hidden" name="order" value="${order}"/>
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
											<div class="col-sm-2 form-check form-switch d-flex align-items-center" style="padding-left:1.5em">
							    				<input class="form-check-input" type="checkbox" name="enable" style="transform:scale(1.5); margin-left:0;">
							    			</div>
							    		</div>
							    		
							    		<div class="row mb-4">
							    			<textarea id="summernote" name="content"></textarea>
							    		</div>
							    		
							    		<div class="row mb-3 d-flex flex-row-reverse">
							    			<button type="submit" class="col-sm-2 btn btn-success">생성</button>
							    		</div>
					                </form>
					            </div>
					        </div>
					    </div>
		        	</c:if>
	       		</div>
       		</div>
        </div>
		
		<%-- 우측 메뉴 --%>
        <div class="rightMenubar nav">   
            <button data-bs-toggle="tab" data-bs-target="#content" onclick="openContents()"><i class="bi bi-list-ul"></i></button>
            <button data-bs-toggle="tab" data-bs-target="#community" onclick="openContents()"><i class="bi bi-chat-square-dots-fill"></i></button>
            <button data-bs-toggle="tab" data-bs-target="#note" onclick="openContents()"><i class="bi bi-sticky-fill"></i></button>
        </div>
    </div>
    
    <script>
        var openFlag = false;
        
        var classify = 2;
        
        function openContents() {
            document.getElementById("mySlidebar").style.width = "430px";
            openFlag = true;
        }
        
        function closeContents() {
            document.getElementById("mySlidebar").style.width = "0";
            openFlag = false;
        }
        
     	// 청 모달창 열기
		function openCreateCourseModal() {
			$(".modal").fadeIn();
		}
		
		// 모달창 닫기
		function closeCreateCourseModal() {
			$(".modal").fadeOut();
		}
		
		$(document).ready(function() {
			// 외부영역 클릭 시 팝업 닫기
			$(document).mouseup(function (e){
				var modal_content = $(".modal_content");
				if(modal_content.has(e.target).length === 0){
					closeCreateCourseModal();
				}
			});
			
			// 커뮤니티 검색 버튼 눌렀을 때
			$(document).on("click", "#search_btn", function() {
				var search = $("#search").val();
				loadCommunity(search, classify);
			});
			
			initSummernote();
		});
        
		function initSummernote() {
			// summernote 옵션 설정
			$('#summernote').summernote({
				placeholder: '노트를 소개할 내용을 적으세요',
				tabsize: 4,
				height: 335,
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
							uploadSummernoteImageFileOfNote(files[i], this);
						}
					}
				}
			});
			
			$('#summernote_article').summernote({
				placeholder: '글쓰기 에디터로 노트를 작성할 수 있어요.',
				tabsize: 4,
				height: 250,
				toolbar: [
					['font', ['bold', 'underline', 'clear']],
					['insert', ['picture']],
					['style', ['style']],
					['para', ['ul', 'ol', 'paragraph']],
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
							uploadSummernoteImageFileOfNoteArticle(files[i], this);
						}
					}
				}
			});
			
			$('.note-statusbar').hide(); 
		}
		
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
		
		// 임시 이미지 등록
		function uploadSummernoteImageFileOfNoteArticle(file, editor) {
			data = new FormData();
			data.append("file", file);
			$.ajax({
				data : data,
				type : "POST",
				url : "/uploadSummernoteImageFileOfNoteArticle",
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
				success : function(data) {
					$(editor).summernote('insertImage', data.url);
				}
			});
		}
		function saveNoteArticle() {
			$.ajax({
				url: "/saveNoteArticle",
				type: "POST",
				data: {
					oli_no: ${oli_no},
					order: ${order},
					title: $("#na_title").val(),
					content: $("#summernote_article").val()
				},
				success: function(data) {
					if(data.responseCode == "error") {
						alert(data.message);
					}
					else {
						$("#noteArticleResult").load(location.href + " #noteArticleResult>*", "");
					}
				},
				error: function() {
					alert("error");
				}
			});
		}
		
		function deleteNoteArticle(na_no) {
			if(confirm("정말 노트 글을 삭제하시겠습니까?")) {
				$.ajax({
					url: "/deleteNoteArticle",
					type: "POST",
					data: {
						oli_no: ${oli_no},
						order: ${order}
					},
					success: function(data) {
						if(data.responseCode == "error") {
							alert(data.message);
						}
						else {
							$("#noteArticle").load(location.href + " #noteArticle>*", function() {
								// 로드가 끝나고 실행
								initSummernote();
							});
						}
					},
					error: function() {
						alert("error");
					}
				});
			}
		}
		
		function loadCommunity(search, classify) {
			$.ajax({
				url: "/course/${oli_no}/play/${order}",
				type: "post",
				dataType: "html",
				data: {
					search: search,
					classify: classify
				},
				success: function(html) {
					var content = $(html).find("#community_content>*");
					$("#community_content").html(content);
				}
			});
		}
		
		function setClassify(btn, _classify) {
			btn.blur();
			classify = _classify;
			loadCommunity("", classify);
		}
    </script>
</body>
</html>