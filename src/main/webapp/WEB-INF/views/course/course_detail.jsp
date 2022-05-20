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
				<textarea readonly class="fs-5 w-100" style="overflow:hidden; resize:none; border-style: none; outline: none;">
이미 8000명 이상이 학습하고 만족한 최고의 프로그래밍 입문 강의. 
비전공자 위치에서 직접 기획하고 준비한 프로그래밍 입문 강의로, 프로그래밍을 전혀 접해보지 못한 사람부터 실제 활용 가능한 프로그래밍 능력까지 갈 수 있도록 도와주는 강의입니다.
					<%--${pageDetail.content}  --%>
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
				
	    		<div class="stars-outer">
	                <div class="stars-inner" style="width:100%"></div>
	            </div>
	            <span class="pr-5 number-rating">(5)</span><br/>
	    		<span class="fw-bold">김홍일</span> <span>2022-05-20</span>
	    		<p>감사합니다</p>
		    	<hr>
		    	
	    		<div class="stars-outer">
	                <div class="stars-inner" style="width:80%"></div>
	            </div>
	            <span class="pr-5 number-rating">(4)</span><br>
	    		<span class="fw-bold">김요한</span> <span>2022-05-20</span>
	    		<p>괜찮았음</p>
			</div>
			
			<%-- 가격, 수강신청 패널 --%>
			<div class="col-lg-4">
			    <div class="card">
			      <div class="card-body p-4">
			        <h5 class="card-title mb-4 fw-200">33,000원</h5>
			        <a href="#" class="btn btn-primary mb-3" style="min-width:100%;">수강신청 하기</a>
			        <span class="d-flex justify-content-center" data-cnt="398" data-target="PC">
						<i onclick="clickedHeart(this)" class="bi bi-heart me-1" style="font-size:25px;"></i>
						<span style="font-size:23px">398</span>
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
    	
    	// 좋아요 클릭 이벤트
        function clickedHeart(x) {
            var status;
            if(x.classList.contains("bi-heart-fill")) {
                status = false;
            }
            else {
                status = true;
            }
            
            console.log(status);
            x.classList.toggle("bi-heart");
            x.classList.toggle("bi-heart-fill");
            x.classList.toggle("text-danger");
            // $.ajax({
            //     url: '데이터를 보낼 곳 url',
            //     type: 'form 태그의 method 속성(post 또는 get)',
            //     data: {"폼 데이터 변수 이름": status},
            //     success: function (data) {
            //             alert("데이터 전송이 성공적으로 끝났을 때 실행");
            //         }
            // });
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