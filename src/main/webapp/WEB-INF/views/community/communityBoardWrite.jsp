<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDateTime"%>
<%
	//현재시간 구해서 String으로 formating
	LocalDateTime nowTime = LocalDateTime.now();
	
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String now = nowTime.format(dateTimeFormatter);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">



  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
<script src="<c:url value='/resources/js/community/summernote.js'/>"></script>
<!-- 태그 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" />
<link rel="stylesheet" href="<c:url value='/resources/css/community/tag.css'/>">

<title>게시글 작성</title>
</head>

<body>
	<div class="container-fluid">
		<div>
			<div class="form-title text-left">
			<br>
				카테고리
				<div class="input-group my-2 mb-1">
					<input type="button"  class="btn btn-danger button-class1"  onclick="" value="자유주제">&nbsp;
					<input type="button" class="btn btn-outline-danger button-class2" onclick="" value="질문">&nbsp;
					<input type="button" class="btn btn-outline-danger button-class3" id="btn3" onclick="" value="스터디">
				</div>
			</div><br>
			<div class="d-flex flex-column">
				<form action="doWriteCommunityBoard" id="searchForm" name="searchForm" method="POST">
					<div class="form-group row">
						<input type="hidden" id="m_no" name="m_no" value="${sessionScope.member.getM_no()}">
						<!-- <input type="hidden" id="classify" name="classify" value="1"> -->
						<div class="col-xs-12 col-md-12">
							제목
							<div class="input-group my-2 mb-1">
								<input type="text" id="title" name="title" class="form-control" placeholder="제목을 입력해 주세요.">
							</div>
						</div><br><br>
						
						<div class="col-xs-12 col-md-12">
							태그
							<div class="input-group my-2 mb-1">
								<input type="text" name="tag" id="tag" class="form-control" placeholder="태그를 설정해주세요.">
							</div>
							<ul id="tag-list"> </ul>
							<div id="sdata"></div>
						</div>
					</div>
					
					내용
					<div class="form-group">
						<textarea id="content" class="form-control" name="content">
						</textarea>
					</div>
					<div align="right">
						<button type="button" class="btn btn-danger" id="btnWrite">작성</button>
						<button type="button" class="btn btn-outline-danger" onclick="self.close();">취소</button>
					</div>
				</form>
				
				
			</div>
		</div>
	</div>
<script src="<c:url value='/resources/js/community/tag.js'/>"></script>
<script type="text/javascript">

//자유게시판이 맨 처음에 선택되도록
$(document).ready(function () {
	if(sessionStorage.getItem("classifyActive")==null || sessionStorage.getItem("classifyActive")=="0"){
		sessionStorage.setItem("classifyActive", "1"); 
	    var fClassify = $("<input type='hidden' value='"+ 1 +"' id='classify' name='classify'>");
		$("#sdata").append(fClassify);
	}
	
})
//카테고리 변경시마다 css변경 및 작성될 카테고리 값 변경
$(function() {
	var counter = 0;
	<c:forEach var="searchTagList" items="${searchTagList}">
		$("#tag-list").append("<li class='tag-item'>" + "${searchTagList.ctl_name}" + "<span class='del-btn' idx='" + counter + "'>x</span></li>&nbsp;");
		var test = $("<input type='hidden' value='${searchTagList.ctl_name}' class='test_obj' name='searchTag' id='" + counter + "'>");
		$("#sdata").append(test);
		addTag("${searchTagList}");
		tagArray.push("${searchTagList.ctl_name}");
	</c:forEach>	
	
	$('.button-class1').click(function(){
		var div = document.getElementById('classify');
		div.remove();
	    if( $(this).hasClass('btn btn-outline-danger') ) $(this).removeClass('btn btn-outline-danger');
	    if( !$(this).hasClass('btn btn-danger') ) $(this).addClass('btn btn-danger');
	    if( $('.button-class2').hasClass('btn btn-danger') ) $('.button-class2').removeClass('btn btn-danger');
	    if( !$('.button-class2').hasClass('btn btn-outline-danger') ) $('.button-class2').addClass('btn btn-outline-danger');
	    if( $('.button-class3').hasClass('btn btn-danger') ) $('.button-class3').removeClass('btn btn-danger');
	    if( !$('.button-class3').hasClass('btn btn-outline-danger') ) $('.button-class3').addClass('btn btn-outline-danger');
	    sessionStorage.setItem("classifyActive", "1"); 
	    var fClassify = $("<input type='hidden' value='"+ 1 +"' id='classify' name='classify'>");
		$("#sdata").append(fClassify);
	});
	
	$('.button-class2').click(function(){
		var div = document.getElementById('classify');
		div.remove();
		if( $(this).hasClass('btn btn-outline-danger') ) $(this).removeClass('btn btn-outline-danger');
		if( !$(this).hasClass('btn btn-danger') ) $(this).addClass('btn btn-danger');
		if( $('.button-class1').hasClass('btn btn-danger') ) $('.button-class1').removeClass('btn btn-danger');
		if( !$('.button-class1').hasClass('btn btn-outline-danger') ) $('.button-class1').addClass('btn btn-outline-danger');
		if( $('.button-class3').hasClass('btn btn-danger') ) $('.button-class3').removeClass('btn btn-danger');
		if( !$('.button-class3').hasClass('btn btn-outline-danger') ) $('.button-class3').addClass('btn btn-outline-danger');
		sessionStorage.setItem("classifyActive", "2"); 
	    var fClassify = $("<input type='hidden' value='"+ 2 +"' id='classify' name='classify'>");
		$("#sdata").append(fClassify);
	});
	
	$('.button-class3').click(function(){
		var div = document.getElementById('classify');
		div.remove();
		if( $(this).hasClass('btn btn-outline-danger') ) $(this).removeClass('btn btn-outline-danger');
		if( !$(this).hasClass('btn btn-danger') ) $(this).addClass('btn btn-danger');
		if( $('.button-class1').hasClass('btn btn-danger') ) $('.button-class1').removeClass('btn btn-danger');
		if( !$('.button-class1').hasClass('btn btn-outline-danger') ) $('.button-class1').addClass('btn btn-outline-danger');
	    if( $('.button-class2').hasClass('btn btn-danger') ) $('.button-class2').removeClass('btn btn-danger');
	    if( !$('.button-class2').hasClass('btn btn-outline-danger') ) $('.button-class2').addClass('btn btn-outline-danger');
	    sessionStorage.setItem("classifyActive", "4"); 
	    var fClassify = $("<input type='hidden' value='"+ 4 +"' id='classify' name='classify'>");
		$("#sdata").append(fClassify);
	  });

});

//게시글 작성
$('#btnWrite').click(function() {
	var cb_no = $("#cb_no").val();
	var goint = sessionStorage.getItem("classifyActive");
	var classify = parseInt(goint); 
	var formData = $("#searchForm").serialize();
	
	sessionStorage.setItem("classifyActive", "0"); 
	$("#searchForm").submit();
	opener.reloadPage();
	/*
	if(!title) {
		swal({
			title: "글작성",
			text: "제목이 입력되지 않았습니다.",
			icon: "warning",
			timer: 3000
		});
		return false;
	}
	if(!content) {
		swal({
			title: "글작성",
			text: "내용이 입력되지 않았습니다.",
			icon: "warning",
			timer: 3000
		});
		return false;
	}
	else {
		$.ajax({
			url: "doWriteCommunityBoard",
			type: "POST",
			data: param,
			success: function(data) {
				if (data != 1) {
					swal({
						title: "글작성",
						text: "게시글 작성이 실패하였습니다.",
						icon: "error",
						timer: 3000
					});
				}
				else {
					sessionStorage.setItem("classifyActive", "0"); 
					opener.parent.location.reload();
					window.close();
				}
			},
			error: function() {
				swal({
					title: "인강인강",
					text: "문제가 발생하였습니다.\n잠시 후 다시 시도해주세요.",
					icon: "error",
					timer: 3000
				});
			}
		});
	}
	*/
	
})
</script>
</body>
</html>