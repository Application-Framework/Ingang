<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${note.title}</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
	<link rel="stylesheet" href="<c:url value='/resources/css/fontawesome-all.min.css'/>">
	
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
		
		.stars-outer {
			position: relative;
			display: inline-block;
		}
		
		.stars-inner {
			position: absolute;
			top: 0;
			left: 0;
			white-space: nowrap;
			overflow: hidden;
			width: 0;
		}
		.stars-outer::before {
			content: "\f005 \f005 \f005 \f005 \f005";
			font-family: "Font Awesome 5 Free";
			font-weight: 900;
			color: #ccc;
		}
		
		.stars-inner::before {
			content: "\f005 \f005 \f005 \f005 \f005";
			font-family: "Font Awesome 5 Free";
			font-weight: 900;
			color: #f8ce0b;
		}
		
		.modal{ 
            position:fixed; 
            width:100%; height:100%; 
            background: rgba(0,0,0,0.2); 
            top:0; 
            left:0; 
            display:none; 
        }

        .modal_content{
            background:#fff;
            position: fixed; 
            top:50%; 
            left:50%;
            transform : translate(-50%, -50%);
            /* text-align:center; */
            box-sizing:border-box; 
            line-height:23px;
            border-style: solid;
            border-radius: 10px;
        }
	</style>
</head>
<body>
	<div class="container-fluid">
		<div class="pt-3 text-center">
			<h4>노트 상세정보</h4>
		</div>
		<hr>
		<form id="note_form">
			<input type="hidden" name="n_no" value="${note.n_no}"/>
			<input type="hidden" name="oli_no" value="${note.oli_no}"/>
			<input type="hidden" name="m_no" value="${note.m_no}"/>
			<div class="row mb-3">
				<div class="col">
					<div class="input-group">
						<span class="input-group-text">노트No</span>
						<span class="input-group-text">${note.n_no}</span>
					</div>
				</div>
				<div class="col">
					<div class="input-group">
						<span class="input-group-text">강의No</span>
						<span class="input-group-text">${note.oli_no}</span>
					</div>
				</div>
				<div class="col">
					<div class="input-group">
						<span class="input-group-text">회원No</span>
						<span class="input-group-text">${note.m_no}</span>
					</div>
				</div>
			</div>
			<div class="row mb-3">
				<div class="col">
					<div class="input-group">
						<span class="input-group-text">가격</span>
						<input type="text" class="form-control" name="price" value="${note.price}" required>
					</div>
				</div>
				
				<div class="col">
					<div class="input-group">
						<span class="input-group-text">생성일</span>
						<span class="input-group-text">${note.reg_date}</span>
					</div>
				</div>
				
				<div class="col">
					<div class="input-group">
						<span class="input-group-text">좋아요 개수</span>
						<span class="input-group-text">${noteLikeCount}</span>
					</div>
				</div>
			</div>
					
			<div class="row mb-3">
				<div class="col">
					<div class="input-group">
						<span class="input-group-text">노트 제목</span>
						<input type="text" class="form-control" name="title" value="${note.title}" required>
					</div>
				</div>
			</div>
				
			<h4>노트 소개 내용</h4>
			<div class="row mb-3">
				<div>
					<textarea class="summernote" id="content" name="content" rows="10">${note.content}</textarea>
				</div>
			</div>
			<div class="float-end mb-3">
				<button type="button" id="note_update" class="btn btn-primary" style="width:5rem;">수정</button>
			</div><br><br>
			
			<hr>
			<div class="text-center">
				<h4>노트 글 목록</h4>
			</div>
			<hr>
			<div class="mb-3">
				<c:forEach var="article" items="${noteArticleList}">
					${article.olv_no} : <a onclick="openModal('#${article.na_no}')" href="javascript:;">${article.title}</a>
					<div class="float-end me-3">
						<button type="button" class="btn btn-danger" onclick="deleteNoteArticle(${note.oli_no}, ${article.olv_no})">삭제</button>
					</div>
					
					<%-- 노트 글 모달 --%>
					<div class="modal" id="${article.na_no}">
						<div class="modal_content card" style="width:35rem; height:37rem;">
							<div class="card-body p-4">
								<h5 class="card-title fw-bold mb-3 text-center">${article.title}</h5>
								<c:out value="${article.content}" escapeXml="false"/>
							</div>
						</div>
					</div>
				</c:forEach>
				<c:if test="${noteArticleList.size() == 0}"><p class="text-center">없음</p></c:if>
			</div>
			
			<hr>
			<div class="text-center">
				<h4>노트 수강평</h4> <span>평균 점수 : ${starAvg}</span>
			</div>
			<hr>
			<div class="mb-3">
				<c:forEach var="reply" items="${noteReplyList}" varStatus="status">
					<div class="stars-outer">
		                <div class="stars-inner" style="width:${reply.star_rating*20}%"></div>
		            </div>
		            <span class="pr-5 number-rating">(${reply.star_rating})</span>
		            <span class="ms-2"><button class="btn btn-danger" onclick="deleteNoteReply(${reply.nr_no})">삭제</button></span><br>
		            
		    		<span class="fw-bold"><a href="">${memberService.getMemberByM_no(reply.m_no).m_name}</a></span> <span>${reply.reg_date}</span>
		    		<p>${reply.content}</p>
		    		<c:if test="${status.last == false}">
		    			<hr>
		    		</c:if>
				</c:forEach>
				<c:if test="${noteReplyList.size() == 0}"><p class="text-center">없음</p></c:if>
			</div>
			
			<%-- 판매 내역 --%>
			<hr>
			<div class="text-center">
				<h4>노트 판매 내역</h4>
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
					    	<th scope="row">${oh.hon_no}</th>
						    <td><a href="">${memberService.getMemberByM_no(oh.m_no).m_name}</a></td>
						    <td>${oh.payment}</td>
						    <td>${oh.payment_date}</td>
						    <td><c:if test="${oh.payment_status == 0}">미완료</c:if><c:if test="${oh.payment_status == 1}">결제완료</c:if></td>
				  		</tr>
		  			</c:forEach>
				</tbody>
			</table>
			<c:if test="${orderHistoryList.size() == 0}"><p class="text-center">없음</p></c:if>
		</form>
	</div>
	
	<script>
		
		// 노트 수정
		$("#note_update").click(function () {
			<c:if test="${member == null}">
				alert("로그인이 필요합니다");
				return;
			</c:if>
			<c:if test="${member.m_authority == 0}">
				alert("관리자 권한이 없습니다.");
				return;
			</c:if>
			
			var form = document.getElementById('note_form');
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
	    	
	    	var form = $('#note_form')[0];
	        var formData = new FormData(form);
	    	$.ajax({
		 		cache: false,
		        contentType: false,
		        processData: false,
	    		url: "/admin/note/updateNote",
	    		data: formData,
	    		type: "post",
	    		success: function() {
	    			alert("수정 성공");
	    			window.opener.location.reload();
	    		},
	    		error: function() {
	    			alert("error");
	    		}
	    	});
		});
	
		function deleteNoteArticle(oli_no, olv_no) {
			if(confirm("정말 노트 글을 삭제하시겠습니까?")) {
				$.ajax({
					url: "/deleteNoteArticle",
					type: "POST",
					data: {
						oli_no: oli_no,
						olv_no: olv_no
					},
					success: function() {
						location.reload();
					}
				});
			}
		}
		
		function deleteNoteReply(nr_no){
			if(confirm("정말 수강평을 삭제하시겠습니까?")) {
				$.ajax({
					url: "/deleteNoteReply",
					type: "POST",
					data: {
						nr_no
					},
					success: function() {
						location.reload();
					}
				});
			}
		}
		
		var selected;
		// 노트글 모달창 열기
		function openModal(id) {
			$(id).fadeIn();
			selected = id;
			
			// 선택된 모달의 textarea의 높이 자동 늘리기
			var txtArea = $(id).find('textarea');
			txtArea.height(txtArea.prop('scrollHeight'));
		}
		
		// 노트글 모달창 닫기
		function closeModal(id) {
			$(id).fadeOut();
		}
		
		$(function(){ 
			// 외부영역 클릭 시 팝업 닫기
			$(document).mouseup(function (e){
				if($(".modal_content").has(e.target).length === 0)
					closeModal(selected);
			});
		});
		
		$(function() {
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
					}
				}
			});
		});
		
	    // 임시 이미지 등록
		function uploadSummernoteImageFile(file, editor) {
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
</body>
</html>