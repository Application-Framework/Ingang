<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    	<title> 커뮤니티 </title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/favicon.ico'/>">
    
    <!-- CSS here -->
	<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/animate.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/fontawesome-all.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/themify-icons.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
	
</head>

<body>
      <%-- Preloader --%>
    <jsp:include page="../fix/preloader.jsp" />
   
    <%------------ header section  ------------%>
    <jsp:include page="../fix/header.jsp" />
    
	<div class="container-flex">
    	<img src='<c:url value="/resources/img/community/community_reviews_banner.PNG" />' alt="" width="100%" height="100px">
    </div><br>
   
    <!--================Blog Area =================-->
    <div class="container">
    	<div class="row">
			<jsp:include page="communityLeftSidebar.jsp" />
			
			<div class="col-lg-7 mb-5 mb-lg-0">
			<br>
				<div class="blog_left_sidebar">
					<article class="blog_item">
						<aside class="single_sidebar_widget search_widget">
							<form action="#">
								<div class="row">
									<div class="col-lg-10" align="left">
										<input type="text" class="form-control" placeholder='내용을 검색해보세요!'>
									</div>
									<div class="col-lg-2" align="left" style="padding-left: 7px;">
									
									<input type="button" class="genric-btn danger-border radius" value="검색" style="width: 100%;">
									</div>
								</div>
							</form>
						</aside>
					</article>

					<div class="container">
						<div class="row">
							<ul class="nav nav-tabs">
								<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#qwe"><h6 style="color: #5D5D5D;"> 최신순</h6></a></li>
								<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#asd"><h6 style="color: #5D5D5D;">별점순</h6></a></li>
								&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;
								&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
							</ul>

							<div class="tab-content">
								<div class="tab-pane fade show active" id="qwe">
									<article class="blog_item">
										<div class="blog_details">
											<a class="d-inline-block" href="single-blog.html">
												<h2>제대로 이해한것인지 궁금합니다.</h2>
											</a>
											<p>5분대부터 헷갈리고 있습니다. 59번째 줄에 killKnight(knight); 가 남아있어서
												그런가? 싶은 생각이 듭니다. 1. 59번째 줄 이후로 knight.hp값이 0...</p>
											<ul class="blog-info-link">
												<li><a href="#"><i class="fa fa-user"></i> choum97</a></li>
												<li><a href="#"><i class="fa fa-comments"></i> 12 </a></li>
												<li><a href="#"><i class="fa fa-heart"></i> 3</a></li>
												<li><i class="fa fa-clock-o"> </i><font size="2" color="#848484">2022-04-12 09:30</font></li>
											</ul>
										</div>
									</article>

									<article class="blog_item">
										<div class="blog_details">
											<a class="d-inline-block" href="single-blog.html">
												<h2>이미지 출력 에러가 발생합니다</h2>
											</a>
											<p>이미지 출력하는 부분에서 에러가 발생합니다. 에러 내용은 이러합니다. 파일 경로에 이미지가
												uuid형식으로 저장되는 것은 확인 하였습니다. 어떻게 해결해야...</p>
											<ul class="blog-info-link">
												<li><a href="#"><i class="fa fa-user"></i> choum97</a></li>
												<li><a href="#"><i class="fa fa-comments"></i> 12 </a></li>
												<li><a href="#"><i class="fa fa-heart"></i> 3</a></li>
												<li><i class="fa fa-clock-o"> </i><font size="2" color="#848484">2022-04-12 09:30</font></li>
											</ul>
										</div>
									</article>
								</div>
								<div class="tab-pane fade" id="asd">
									<article class="blog_item">
										<div class="blog_details">
											<a class="d-inline-block" href="single-blog.html">
												<h2>터미널에 명령어</h2>
											</a>
											<p>5분대부터 헷갈리고 있습니다. 59번째 줄에 killKnight(knight); 가 남아있어서
												그런가? 싶은 생각이 듭니다. 1. 59번째 줄 이후로 knight.hp값이 0...</p>
											<ul class="blog-info-link">
												<li><a href="#"><i class="fa fa-user"></i> choum97</a></li>
												<li><a href="#"><i class="fa fa-comments"></i> 12 </a></li>
												<li><a href="#"><i class="fa fa-heart"></i> 3</a></li>
												<li><i class="fa fa-clock-o"> </i><font size="2" color="#848484">2022-04-12 09:30</font></li>
											</ul>
										</div>
									</article>

									<article class="blog_item">
										<div class="blog_details">
											<a class="d-inline-block" href="single-blog.html">
												<h2>spring boot 2.5 버전 이상일 경우 오류</h2>
											</a>
											<p>이미지 출력하는 부분에서 에러가 발생합니다. 에러 내용은 이러합니다. 파일 경로에 이미지가
												uuid형식으로 저장되는 것은 확인 하였습니다. 어떻게 해결해야...</p>
											<ul class="blog-info-link">
												<li><a href="#"><i class="fa fa-user"></i> choum97</a></li>
												<li><a href="#"><i class="fa fa-comments"></i> 12 </a></li>
												<li><a href="#"><i class="fa fa-heart"></i> 3</a></li>
												<li><i class="fa fa-clock-o"> </i><font size="2" color="#848484">2022-04-12 09:30</font></li>
											</ul>
										</div>
									</article>
								</div>

							</div>
						</div>
					</div>


				<nav class="blog-pagination justify-content-center d-flex">
					<ul class="pagination">
						<li class="page-item">
							<a href="#" class="page-link" aria-label="Previous"> <i class="ti-angle-left"></i> </a>
						</li>
						<li class="page-item">
							<a href="#" class="page-link">1</a>
						</li>
						<li class="page-item active">
							<a href="#" class="page-link">2</a>
						</li>
						<li class="page-item">
							<a href="#" class="page-link" aria-label="Next"> <i class="ti-angle-right"></i></a>
						</li>
					</ul>
				</nav>
				</div><br>
			</div>
			<jsp:include page="communityTagSidebar.jsp" />
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