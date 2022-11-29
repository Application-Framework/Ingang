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
				<form id="searchForm" action="doModfiyCommunityBoard" method="POST" >
					<div class="form-group row">
						<input type="hidden" id="cb_no" name="cb_no" value="${cbReadPage.cb_no}">
						<input type="hidden" id="m_no" name="m_no" value="${sessionScope.member.getM_no()}">
						<input type="hidden" id="title" name="title" value="${cbReadPage.title}">
						<input type="hidden" id="classify" name="classify" value="${cbReadPage.classify}">
						<div class="col-xs-12 col-md-12">
							제목
							<div class="input-group my-2 mb-1">
								<input type="text" id="title" class="form-control" placeholder="제목을 입력해 주세요." value="${cbReadPage.title}">
							</div>
						</div><br><br>
						
						<div class="col-xs-12 col-md-12">
							태그
							<div class="input-group my-2 mb-1">
								<input type="text"  name="tag"  id="tag" class="form-control" placeholder="태그를 설정해주세요.">
							</div>
							<ul id="tag-list"> </ul>
							<div id="sdata"></div>
						</div>
					</div>
					
					내용
					<div class="form-group">
						<textarea id="content" class="form-control" name="content">${cbReadPage.content}</textarea>
					</div>
					<div align="right">
						<button type="button" class="btn btn-danger" id="btnModify">수정</button>
						<button type="button" class="btn btn-outline-danger" onclick="self.close();">취소</button>
					</div>
				</form>
				
				
			</div>
		</div>
	</div>
<script src="<c:url value='/resources/js/community/tag.js'/>"></script>
<script type="text/javascript">
//Classify따라서 자동으로 바뀌도록, Modal에 하면 변경하고 수정페이지 재출력시 이전껄로 적용되어서
//현재 페이지 url에서 원하는 값 가져온 것
$(document).ready(function () {
	var counter = 0;
	<c:forEach var="searchTagList" items="${searchTagList}">
		$("#tag-list").append("<li class='tag-item'>" + "${searchTagList.ctl_name}" + "<span class='del-btn' idx='" + counter + "'>x</span></li>&nbsp;");
		var test = $("<input type='hidden' value='${searchTagList.ctl_name}' class='test_obj' name='searchTag' id='" + counter + "'>");
		$("#sdata").append(test);
		addTag("${searchTagList}");
		tagArray.push("${searchTagList.ctl_name}");
	</c:forEach>	
	
	const url = new URL( window.location.href);
	const urlParams = url.searchParams;
	const urlClassify = urlParams.get('classify');
	
	var modifyClassify = ${classify};
	if(urlClassify == 1){
		if( $('.button-class1').hasClass('btn btn-outline-danger') ) $('.button-class1').removeClass('btn btn-outline-danger');
		if( !$('.button-class1').hasClass('btn btn-danger') ) $('.button-class1').addClass('btn btn-danger');
		if( $('.button-class2').hasClass('btn btn-danger') ) $('.button-class2').removeClass('btn btn-danger');
		if( !$('.button-class2').hasClass('btn btn-outline-danger') ) $('.button-class2').addClass('btn btn-outline-danger');
		if( $('.button-class3').hasClass('btn btn-danger') ) $('.button-class3').removeClass('btn btn-danger');
		if( !$('.button-class3').hasClass('btn btn-outline-danger') ) $('.button-class3').addClass('btn btn-outline-danger');
		sessionStorage.setItem("classifyActive", modifyClassify); 
	} else if(urlClassify == 2 || modifyClassify == 3){
		if( $('.button-class2').hasClass('btn btn-outline-danger') ) $('.button-class2').removeClass('btn btn-outline-danger');
		if( !$('.button-class2').hasClass('btn btn-danger') ) $('.button-class2').addClass('btn btn-danger');
		if( $('.button-class1').hasClass('btn btn-danger') ) $('.button-class1').removeClass('btn btn-danger');
		if( !$('.button-class1').hasClass('btn btn-outline-danger') ) $('.button-class1').addClass('btn btn-outline-danger');
		if( $('.button-class3').hasClass('btn btn-danger') ) $('.button-class3').removeClass('btn btn-danger');
		if( !$('.button-class3').hasClass('btn btn-outline-danger') ) $('.button-class3').addClass('btn btn-outline-danger');
		sessionStorage.setItem("classifyActive", modifyClassify); 
	} else if(urlClassify == 4 || modifyClassify == 5){
		if( $('.button-class3').hasClass('btn btn-outline-danger') ) $('.button-class3').removeClass('btn btn-outline-danger');
		if( !$('.button-class3').hasClass('btn btn-danger') ) $('.button-class3').addClass('btn btn-danger');
		if( $('.button-class2').hasClass('btn btn-danger') ) $('.button-class2').removeClass('btn btn-danger');
		if( !$('.button-class2').hasClass('btn btn-outline-danger') ) $('.button-class2').addClass('btn btn-outline-danger');
		if( $('.button-class1').hasClass('btn btn-danger') ) $('.button-class1').removeClass('btn btn-danger');
		if( !$('.button-class1').hasClass('btn btn-outline-danger') ) $('.button-class1').addClass('btn btn-outline-danger');
		sessionStorage.setItem("classifyActive", modifyClassify); 
	}
	
})


$(function() {
	$('.button-class1').click(function(){
		var dfClassify = document.getElementById('classify');
		dfClassify.remove();
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


$('#btnModify').click(function() {
	var cb_no = $("#cb_no").val();
	var goint = sessionStorage.getItem("classifyActive");
	var classify = parseInt(goint); 
	var formData = $("#searchForm").serialize();
	sessionStorage.setItem("classifyActive", "4"); 
	
	var reLoadUrl = "/communityBoardRead?cb_no=" + cb_no + "&classify=" + classify + "&isOnlineLecture="+ ${cbReadPage.oli_no};
	$("#searchForm").submit();
	opener.reloadPage(reLoadUrl);
   // self.close();
    /*
	setTimeout(function() {
		//opener.location.replace(reLoadUrl);
		//window.close();
		opener.reloadPage();
	    self.close();
	}, 300);
    */
})
	/*
	var cb_no = $("#cb_no").val();
	var title = $("#title").val();
	//var searchTag = $(".searchTag").val();
	var content = $("#content").val();
	var goint = sessionStorage.getItem("classifyActive");
	var classify = parseInt(goint); 
	var param = {'cb_no': cb_no, 'title': title,'content': content, 'classify': classify, 'searchTag':tagArray};
	
	if(!title) {
		swal({
			title: "글수정",
			text: "제목이 입력되지 않았습니다.",
			icon: "warning",
			timer: 3000
		});
		return false;
	}
	if(!content) {
		swal({
			title: "글수정",
			text: "내용이 입력되지 않았습니다.",
			icon: "warning",
			timer: 3000
		});
		return false;
	}
	else {
		$.ajax({
			url: "doModfiyCommunityBoard",
			type: "POST",
			data: param,
			success: function(data) {
				if (data != 1) {
					swal({
						title: "글수정",
						text: "게시글 수정이 실패하였습니다.",
						icon: "error",
						timer: 3000
					});
				}
				else {
					console.log(tagArray);
					sessionStorage.setItem("classifyActive", "0"); 
					var reLoadUrl = "/communityBoardRead?cb_no=" + cb_no + "&classify=" + classify + "&isOnlineLecture="+ ${cbReadPage.oli_no};
					opener.location.replace(reLoadUrl);
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
})
*/
</script>
</body>
</html>