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

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<!--코드미러 -->
<script src="<c:url value="/resources/js/codemirror.js"/>"></script>
<link href='<c:url value="/resources/css/codemirror.css"/>' rel='stylesheet' />
<style type="text/css">

.containerTop {
	display: flex;
	justify-content: space-between;
	align-items: flex-end
}
</style>
<title>게시글 작성</title>
</head>

<body>
	<div class="container-fluid">
		<div>
			<div class="form-title text-center">
			<br>
				<h4>일정확인</h4><br>
			</div>
			
			
			
			<hr style=background-color:#368AFF;>
			<div class="d-flex flex-column">
			
				<form>
					기본정보
					<div class="form-group row">
						<div class="col-xs-6 col-md-6">
							<div class="input-group my-2 mb-1">
								<div class="input-group-prepend">
									<span class="input-group-text"><b>제목</b></span>
								</div>
								<input type="text" value="${scheduleDetailView.getSchedule_title()}" class="form-control" >
							</div>
						</div>
						<div class="col-xs-6 col-md-6">
							<div class="input-group my-2 mb-1">
								<div class="input-group-prepend">
									<span class="input-group-text"><b>작성자</b></span>
								</div>
								<input type="text" value="${scheduleDetailView.getMember_id()}" class="form-control" >
							</div>
						</div>
						
					</div>
					
					<!-- 개요 -->
					내용
					<div class="form-group">
						<textarea class="form-control" rows="5" id="content" name="content">${scheduleDetailView.schedule_content}</textarea>	
					</div>
					
					<hr style=background-color:#368AFF;>
					<!-- 이전, 수정 버튼 -->
					<div align="right">
						<button type="button" class="btn btn-primary" onclick="scheduleDetailGo(${scheduleDetailView.schedule_id});">등록하기</button>
						<button type="button" class="btn btn-secondary" onclick="self.close();">취소</button>
			        </div>
			        <br>
				</form>
			</div>
		</div>
	</div>


	<script> 
	  var editor = CodeMirror.fromTextArea(myTextarea, {
	    lineNumbers: true
	  });

	  function scheduleDetailGo(sId) { 
		  var popup = window.open('ScheduleDetailView?schedule_id='+sId , 'a', 'width=800px,height=840px,left=300,top=100');
	  }

	 
	</script>



<!-- Modal -->
<!--
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			
			<div class="modal-body">
			
				<ul class="nav nav-pills" id="myTab" style="width: 100%;">
					<li class="nav-item" id="myTabActive1" style="width: 15%;"><a class="nav-link active" data-toggle="tab" href="#qwe"><h6 style="color: #5D5D5D;" align="center"> 자유주제</h6></a></li>
					<li class="nav-item" id="myTabActive2" style="width: 15%;"><a class="nav-link" data-toggle="tab" href="#asd" ><h6 style="color: #5D5D5D;" align="center">질문</h6></a></li>
					<li class="nav-item" id="myTabActive2" style="width: 15%;"><a class="nav-link" data-toggle="tab" href="#asd" ><h6 style="color: #5D5D5D;" align="center">스터디</h6></a></li>
				</ul>
				
				<div class="tab-content">
					<div class="tab-pane fade show active" id="qwe">
						<article class="blog_item">
							<div class="blog_details" style="padding: 10px 10px 10px 10px;">
							
								<a class="d-inline-block" href="single-blog.html">
								</a>
							</div>
						</article>
					</div>
					<div class="tab-pane fade" id="asd">
						<div class="tab-pane fade show active" id="qwe">
							<article class="blog_item">
								<div class="blog_details" style="padding: 10px 10px 10px 10px;">
									<a class="d-inline-block" href="single-blog.html">
									</a>
								</div>
							</article>
						</div>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">작성</button>
			</div>
		</div>
	</div>
</div>
-->


</body>
</html>