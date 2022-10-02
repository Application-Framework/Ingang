<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>${video.title}</title>
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/favicon.ico'/>">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.2/font/bootstrap-icons.css">
	<link rel="stylesheet" href="<c:url value='/resources/css/course/course_play.css'/>">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	
	<%-- 탭 , 모달기능 사용하기 위해 import --%>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
  	<%-- summernote bootsrap 필요 없는 버전 --%>
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
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
	</style>
</head>
<body>
	<div class="bg-dark d-flex vh-100" id="main">
        <div class="d-flex flex-grow-1 flex-column">
            <iframe class="w-100 h-100" width="1120" height="630" src="${videoPath}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            <div class="footer" style="height: 50px;">
            	<div class="h-100 d-flex justify-content-evenly align-items-center">
            		<div class="col-3 text-center">
		           		<c:choose>
			            	<c:when test="${olv_no == videoList[0].olv_no}">
			            		<a class="link-light">첫번째수업</a>
			            	</c:when>
			            	<c:otherwise>
			            		<a class="link-light" href="${olv_no-1}">이전 수업</a>
			            	</c:otherwise>
		            	</c:choose>
	            	</div>
	            	<div class="col-3 text-center">
		            	<c:choose>
			            	<c:when test="${olv_no == videoList[videoList.size()-1].olv_no}">
			            		<a class="link-light">마지막수업</a>
			            	</c:when>
		            		<c:otherwise>
		            			<a class="link-light" href="${olv_no+1}">다음 수업</a>
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
			        	<div class="content d-flex align-items-center px-3 py-3 <c:choose><c:when test='${olv_no == video.olv_no}'>selected</c:when><c:otherwise>notSelected</c:otherwise></c:choose>">
			        		<i class="fs-5 bi bi-play-circle me-2"></i>
				       		<div class="fs-5">${video.title}</div>
				       		<a class="stretched-link" href="${video.olv_no}"></a>
			       		</div>
		       		</c:forEach>
	       		</div> 
	       		
	       		<%-- 커뮤니티 불러오기 --%>
	       		<div id="community" class="tab-pane">
		        	<div class="p-3 d-flex justify-content-between">
			        	<span class="fs-4 fw-bold">커뮤니티</span>
			        	<a href="#" onclick="closeContents()" id="closeBtn"><i class="fs-4 bi bi-x-lg" style="-webkit-text-stroke: 1px;"></i></a>
		        	</div>
		        	
		        	<c:forEach var="video" items="${videoList}">
			        	<div class="content d-flex align-items-center px-3 py-3 <c:choose><c:when test='${olv_no == video.olv_no}'>selected</c:when><c:otherwise>notSelected</c:otherwise></c:choose>">
			        		<i class="fs-5 bi bi-play-circle me-2"></i>
				       		<div class="fs-5">${video.title}</div>
				       		<a class="stretched-link" href="${video.olv_no}"></a>
			       		</div>
		       		</c:forEach>
	       		</div>
	       		
	       		<%-- 노트 불러오기 --%>
	       		<div id="note" class="tab-pane">
		        	<div class="p-3 d-flex justify-content-between">
			        	<span class="fs-4 fw-bold">노트</span>
			        	<a href="#" onclick="closeContents()" id="closeBtn"><i class="fs-4 bi bi-x-lg" style="-webkit-text-stroke: 1px;"></i></a>
		        	</div>
		        	<%-- 생성한 노트가 있을 때 --%>
		        	<c:if test="${note != null}">
		        		<%-- 새로고침 없이 저장하기 위해 더미 iframe 생성 --%>
			        	<iframe name="dummyframe" id="dummyframe" style="display: none"></iframe>
			        	<form action="/course/saveNote" target="dummyframe" method="post">
			        		<input type="hidden" name="oli_no" value="${pageNo}"/>
			        		<input type="hidden" name="olv_no" value="${olv_no}"/>
			        		<input type="hidden" name="na_no" value="${noteArticle.na_no}"/>
			        		
				        	<input class="form-control mb-1" type="text" name="title" value="${noteArticle.title}" placeholder="title" />
				        	<textarea class="form-control mb-2" name="content" rows="5" placeholder="content">${noteArticle.content}</textarea>
				        	<div class="d-flex flex-row-reverse">
				        		<input type="submit" value="저장" class="btn btn-dark text-end"/>
				        	</div>
			        	</form>
		        	</c:if>
		        	
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
					                	<input type="hidden" name="oli_no" value="${pageNo}"/>
					                	<input type="hidden" name="olv_no" value="${olv_no}"/>
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
							    			<textarea class="summernote" id="content" name="content" placeholder="노트 소개 내용"></textarea>
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
        <div class="rightMenubar">   
            <a data-toggle="tab" href="#content" class="toggleButton" onclick="openContents()"><i class="bi bi-list-ul"></i></a>
            <a data-toggle="tab" href="#community" class="toggleButton" onclick="openContents()"><i class="bi bi-chat-square-dots-fill"></i></a>
            <a data-toggle="tab" href="#note" class="toggleButton" onclick="openContents()"><i class="bi bi-sticky-fill"></i></a>
        </div>
    </div>
    
    <script>
        var openFlag = false;
		
        function openContents() {
            document.getElementById("mySlidebar").style.width = "250px";
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
		
		$(function(){ 
			// 외부영역 클릭 시 팝업 닫기
			$(document).mouseup(function (e){
				var modal_content = $(".modal_content");
				if(modal_content.has(e.target).length === 0){
					closeCreateCourseModal();
				}
			});
			
			// summernote 옵션 설정
			$('.summernote').summernote({
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
			
			$('.note-statusbar').hide(); 
		});
        
		// 임시 이미지 등록
		function uploadSummernoteImageFile(file, editor) {
			data = new FormData();
			data.append("file", file);
			$.ajax({
				data : data,
				type : "POST",
				url : "/noteUploadSummernoteImageFile",
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
				success : function(data) {
					$(editor).summernote('insertImage', data.url);
				}
			});
		}
    </script>
</body>
</html>