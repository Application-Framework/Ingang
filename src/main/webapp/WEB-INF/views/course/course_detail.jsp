<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    	<title> 강의 상세 </title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/favicon.ico'/>">
    
    <!-- CSS here -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
	<link rel="stylesheet" href="<c:url value='/resources/css/animate.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/fontawesome-all.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/themify-icons.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style>
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
		a {
			color: #635c5c;
			text-decoration: none;
		}
	</style>
</head>

<body>
      <%-- Preloader --%>
    <jsp:include page="../fix/preloader.jsp" />
   
    <%------------ header section  ------------%>
    <jsp:include page="../fix/header.jsp" />
    
    <%-- 상단 강의 정보 부분 --%>
    <div class="container-flex" style="background-color:#000a12">
    	<div class="container p-5">
    		<div class="row">
	    		<%-- 강의 표지 --%>
	    		<div class="col-xl-5">
	    			<img src="<c:url value='/resources/img/course/sample.jpg'/>" style="width:440px; height:286px;"/>
	    		</div>
		    	<div class="col-xl-7 card-body text-white">
			    	<h3 class="fw-bold text-white mb-5">${course.title}</h3>
			    	<div class="stars-outer">
		                <div class="stars-inner" style="width:${starAvg*20}%"></div>
		            </div>
		            <span class="pr-5 number-rating">(${starAvg})</span>
		            <span>${replys.size()}개의 수강평 ∙ </span> <span>${stdCnt}명의 수강생</span>
		            <p class="text-white">${teacher.name}</p>
			    </div>
		    </div>
	    </div>
    </div>
    
    <!--================Blog Area =================-->
    <div class="container-flex border-bottom">
    	<div class="container p-0">
    		<nav class="navbar navbar-expand-lg navbar-light">
				<div class="px-5">
					<ul class="navbar-nav">
						<li class="nav-item me-3"><a class="nav-link active fw-bold" href="#">강의소개</a></li>
						<li class="nav-item me-3"><a class="nav-link fw-bold" href="#curriculum">커리큘럼</a></li>
						<li class="nav-item me-3"><a class="nav-link fw-bold" href="#reviews">수강평</a></li>
						<li class="nav-item me-3"><a class="nav-link fw-bold" href="#community">커뮤니티</a></li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
	
	<div class="container p-5">
		<div class="row">
			<div class="col-lg-8">
				<textarea readonly class="fs-5 w-100" style="overflow:hidden; resize:none; border-style: none; outline: none;">
${course.content}
				</textarea>
				
				<%-- 커리큘럼 --%>
				<div class="mb-3 fs-3 fw-bold">
					커리큘럼
				</div>
				<div class="mb-4">
				</div>
				
				<%-- 수강평 --%>
				<div class="mb-3 fs-3 fw-bold">
					수강평
				</div>
				
				<c:forEach var="reply" items="${replys}">
					<div class="stars-outer">
		                <div class="stars-inner" style="width:${reply.star_rating*20}%"></div>
		            </div>
		            <span class="pr-5 number-rating">(${reply.star_rating})</span><br/>
		    		<span class="fw-bold">${memberSerivce.getNameByM_no(reply.m_no)}</span> <span>${reply.reg_date}</span>
		    		<p>${reply.content}</p>
			    	<hr>
				</c:forEach>
			</div>
			
			<%-- 가격, 수강신청 패널 --%>
			<div class="col-lg-4">
			    <div class="card">
			      <div class="card-body p-4">
			        <h5 class="card-title mb-4 fw-200">${course.price}원</h5>
			        <a href="#" class="btn btn-primary mb-3" style="min-width:100%;">수강신청 하기</a>
			        <span class="d-flex justify-content-center" data-cnt="398" data-target="PC">
						<i onclick="clickedHeart(this)" id="like" class="bi bi-heart me-1" style="font-size:25px;"></i>
						<span id="likeCnt" style="font-size:23px">${likeCnt}</span>
					</span>
			      </div>
			    </div>
			</div>
		</div>
    </div>
    <%------------ footer section  ------------%>
    
    <script>
    	// textarea 자동 늘리기
	    $(function() {
		    $('textarea').each(function() {
		        $(this).height($(this).prop('scrollHeight'));
		        console.log("늘리기");
		    });
		});
    	
		// 좋아요를 이미 클릭했다면 채우기
		$(function() {
			if(${existLike}) {
		 		var x = $('#like')[0]; 
		 		x.classList.toggle("bi-heart");
		        x.classList.toggle("bi-heart-fill");
		        x.classList.toggle("text-danger");
				}
		});
		
		// 좋아요 클릭 이벤트
		function clickedHeart(x) {
			var status;
			var likeCnt = parseInt($('#likeCnt').html());
			if(x.classList.contains("bi-heart-fill")) {
				status = false;
				likeCnt = likeCnt - 1; 
			}
			else {
				status = true;
				likeCnt = likeCnt + 1; 
			}
		       
			$('#likeCnt').text(likeCnt);
		       
			x.classList.toggle("bi-heart");
			x.classList.toggle("bi-heart-fill");
			x.classList.toggle("text-danger");
			$.ajax({
				url: '/course/courseClickedLike',
				type: 'post',
				data: {
					status: status,
					oli_no: ${oli_no}
				}
			});
		}
    </script>
    
    <jsp:include page="../fix/footer.jsp" />
    <%-- Jquery, Popper, Bootstrap --%>
   	<script src="<c:url value='/resources/js/vendor/jquery-1.12.4.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

    <%-- Jquery Plugins, main Jquery --%>
	<script src="<c:url value='/resources/js/plugins.js'/>"></script>
    <script src="<c:url value='/resources/js/main.js'/>"></script>
</body>
</html>