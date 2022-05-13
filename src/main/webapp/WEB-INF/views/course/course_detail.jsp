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
	<link rel="stylesheet" href="<c:url value='/resources/css/animate.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/fontawesome-all.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/themify-icons.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
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
			    	<h3 class="fw-bold text-white mb-5">비전공자를 위한 개발자 취업 올인원 가이드 [통합편]</h3>
			    	<div class="stars-outer">
		                <div class="stars-inner" style="width:99%"></div>
		            </div>
		            <span class="pr-5 number-rating">(4.9)</span>
		            <span>106개의 수강평 ∙ </span>
		            <span>1984명의 수강생</span>
		            
		            <p class="text-white">강사 이름</p>
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
				<p>입문자를 위해 준비한
				[취업 · 이직, 개발 · 프로그래밍] 강의입니다.</p>
			</div>
			
			<%-- 가격, 수강신청 패널 --%>
			<div class="col-lg-4">
			    <div class="card">
			      <div class="card-body p-4">
			        <h5 class="card-title mb-4 fw-200">33,000원</h5>
			        <a href="#" class="btn btn-primary" style="min-width:100%;">수강신청 하기</a>
			        <span class="d-flex justify-content-center" data-cnt="398" data-target="PC">
						<span class="infd-icon"><svg class="h-100" width="30" height="30" viewBox="0 0 25 25" xmlns="http://www.w3.org/2000/svg"><path fill="#212529" fill-rule="evenodd" clip-rule="evenodd" d="M4.49095 2.66666C3.10493 2.66666 1.66663 3.92028 1.66663 5.67567C1.66663 7.74725 3.21569 9.64919 4.90742 11.0894C5.73796 11.7965 6.571 12.3653 7.19759 12.7576C7.51037 12.9534 7.7704 13.1045 7.95123 13.2061C7.96818 13.2156 7.98443 13.2247 7.99996 13.2333C8.01549 13.2247 8.03174 13.2156 8.04869 13.2061C8.22952 13.1045 8.48955 12.9534 8.80233 12.7576C9.42892 12.3653 10.262 11.7965 11.0925 11.0894C12.7842 9.64919 14.3333 7.74725 14.3333 5.67567C14.3333 3.92028 12.895 2.66666 11.509 2.66666C10.1054 2.66666 8.9751 3.59266 8.4743 5.09505C8.40624 5.29922 8.21518 5.43693 7.99996 5.43693C7.78474 5.43693 7.59368 5.29922 7.52562 5.09505C7.02482 3.59266 5.89453 2.66666 4.49095 2.66666ZM7.99996 13.8018L8.22836 14.2466C8.08499 14.3202 7.91493 14.3202 7.77156 14.2466L7.99996 13.8018ZM0.666626 5.67567C0.666626 3.368 2.55265 1.66666 4.49095 1.66666C6.01983 1.66666 7.25381 2.48414 7.99996 3.73655C8.74611 2.48414 9.98009 1.66666 11.509 1.66666C13.4473 1.66666 15.3333 3.368 15.3333 5.67567C15.3333 8.22121 13.4657 10.3823 11.7407 11.8509C10.863 12.5982 9.98767 13.1953 9.33301 13.6052C9.00516 13.8104 8.73133 13.9696 8.53847 14.0779C8.44201 14.1321 8.36571 14.1737 8.31292 14.2019C8.28653 14.2161 8.26601 14.2269 8.25177 14.2344L8.2352 14.2431L8.23054 14.2455L8.22914 14.2462C8.22897 14.2463 8.22836 14.2466 7.99996 13.8018C7.77156 14.2466 7.77173 14.2467 7.77156 14.2466L7.76938 14.2455L7.76472 14.2431L7.74815 14.2344C7.73391 14.2269 7.71339 14.2161 7.687 14.2019C7.63421 14.1737 7.55791 14.1321 7.46145 14.0779C7.26858 13.9696 6.99476 13.8104 6.66691 13.6052C6.01225 13.1953 5.13695 12.5982 4.25917 11.8509C2.53423 10.3823 0.666626 8.22121 0.666626 5.67567Z"></path></svg></span>
						<span style="font-size:23px">398</span>
					</span>
			      </div>
			    </div>
			</div>
		</div>
    </div>
    <%------------ footer section  ------------%>
    <jsp:include page="../fix/footer.jsp" />
    <%-- Jquery, Popper, Bootstrap --%>
   	<script src="<c:url value='/resources/js/vendor/jquery-1.12.4.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

    <%-- Jquery Plugins, main Jquery --%>
	<script src="<c:url value='/resources/js/plugins.js'/>"></script>
    <script src="<c:url value='/resources/js/main.js'/>"></script>
</body>
</html>