<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>회원탈퇴</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/x-icon"
	href="<c:url value='/resources/img/favicon.ico'/>">

<!-- CSS here -->
<link rel="stylesheet"
	href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/owl.carousel.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/flaticon.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/price_rangs.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/slicknav.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/animate.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/magnific-popup.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/fontawesome-all.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/themify-icons.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/slick.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/css/nice-select.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
</head>
<style>
	.sidebar {
		width: 400px;
		height: 500px;
		overflow-y: auto;
		background: var(- -light);
		transition: 0.5s;
		z-index: 999;
	}
	
	@media ( min-width : 992px) {
		.sidebar {
			margin-left: 0;
		}
		.sidebar.open {
			margin-left: -250px;
		}
		.content {
			width: calc(100% - 250px);
		}
	}
	
	@media ( max-width : 991.98px) {
		.sidebar {
			margin-left: -250px;
		}
		.sidebar.open {
			margin-left: 0;
		}
	}
	
	.sidebar .navbar .navbar-nav .nav-link {
		padding: 7px 10px;
		color: var(- -dark);
		font-weight: 500;
		border-left: 3px solid var(- -light);
		border-radius: 0 30px 30px 0;
		outline: none;
	}
	
	.sidebar .navbar .navbar-nav .nav-link:hover, .sidebar .navbar .navbar-nav .nav-link.active
		{
		color: var(- -primary);
		background: #FFFFFF;
		border-color: var(- -primary);
	}
	
	.sidebar .navbar .navbar-nav .nav-link i {
		width: 40px;
		height: 40px;
		display: inline-flex;
		align-items: center;
		justify-content: center;
		background: #FFFFFF;
		border-radius: 40px;
	}
	
	.sidebar .navbar .navbar-nav .nav-link:hover i, .sidebar .navbar .navbar-nav .nav-link.active i
		{
		background: var(- -light);
	}
	
	.sidebar .navbar .dropdown-toggle::after {
		position: absolute;
		top: 15px;
		right: 15px;
		border: none;
		content: "\f107";
		font-family: "Font Awesome 5 Free";
		font-weight: 900;
		transition: .5s;
	}
	
	.sidebar .navbar .dropdown-toggle[aria-expanded=true]::after {
		transform: rotate(-180deg);
	}
	
	.sidebar .navbar .dropdown-item {
		padding-left: 25px;
		border-radius: 0 30px 30px 0;
	}
</style>
<body>
	<%-- Preloader --%>
	<jsp:include page="../fix/preloader.jsp" />

	<%------------ header section  ------------%>
	<jsp:include page="../fix/header.jsp" />
	<main>

		<div class="container">
			<div class="row">
				<!-- Left content -->
				<div class="col-lg-2 sidebar pe-4 pb-3">
					<aside class="single_sidebar_widget post_category_widget">
						<nav class="navbar bg-light navbar-light">
							<div class="navbar-nav w-100">
								<div class="nav-item dropdown">
									<a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
										<i class="fa fa-laptop me-2"></i>회원정보
									</a>
									<div class="dropdown-menu bg-transparent border-0">
										<a href="/my_update" class="dropdown-item">정보수정</a> 
									<a href="/my_delete" class="dropdown-item">회원탈퇴</a>
									</div>
								</div>
								<div class="nav-item dropdown">
								<a href="#" class="nav-link dropdown-toggle"
									data-bs-toggle="dropdown"> <i class="far fa-file-alt me-2"></i>구매내역
								</a>
								<div class="dropdown-menu bg-transparent border-0">
									<a href="/courses_history" class="dropdown-item">강의내역</a> 
									<a href="/notes_history" class="dropdown-item">노트내역</a>
								</div>
							</div>
							<div class="nav-item dropdown">
								<a href="#" class="nav-link dropdown-toggle"
									data-bs-toggle="dropdown"> <i class="far fa-file-alt me-2"></i>My
								</a>
								<div class="dropdown-menu bg-transparent border-0">
									<a href="/my_course" class="dropdown-item">내 강의</a> 
									<a href="/my_note" class="dropdown-item">내 노트</a> 
									<a href="/my_post" class="dropdown-item">내 게시글</a>
								</div>
							</div>
							</div>
						</nav>
					</aside>
				</div>
				<!-- profile start -->
				<div class="col-lg-9 bootstrap snippets bootdey">
					<h4 class="m-0 text-uppercase font-weight-bold">회원 탈퇴</h4>
					<hr>
					<div class="row">
						<!-- left column -->
						<div class="col-md-3">
							<div class="text-center">
								<img src="${member.img_path}" class="avatar img-circle img-thumbnail" alt="avatar">
							</div>
						</div>

						<!-- edit form column -->
						<div class="col-md-9 personal-info">
							<form class="form-horizontal" role="form" method="post">
								<div class="form-group">
									<label class="col-lg-3 control-label">비밀번호 확인:</label>
									<div class="col-lg-8">
										<input class="form-control" type="password" name="m_pw" value="">
									</div>
								</div>
								
								<input type="hidden" name="m_id" value="${member.m_id}">
								
								&nbsp;&nbsp;&nbsp;
								<button type="submit" class="btn head-btn2">회원탈퇴</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="btn head-btn1" onclick="location.href='/mypage'">취소하기</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<hr>
		<!-- profile end -->

		
	</main>
	<jsp:include page="../fix/footer.jsp" />

	<%------------ All JS Custom Plugins Link Here here -----------%>
	<script
		src="<c:url value='/resources/js/vendor/modernizr-3.5.0.min.js'/>"></script>
	<%-- Jquery, Popper, Bootstrap --%>
	<script
		src="<c:url value='/resources/js/vendor/jquery-1.12.4.min.js'/>"></script>
	<script src="<c:url value='/resources/js/popper.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	<%-- Jquery Mobile Menu --%>
	<script src="<c:url value='/resources/js/jquery.slicknav.min.js'/>"></script>

	<%-- Jquery Slick , Owl-Carousel Plugins --%>
	<script src="<c:url value='/resources/js/owl.carousel.min.js'/>"></script>
	<script src="<c:url value='/resources/js/slick.min.js'/>"></script>
	<script src="<c:url value='/resources/js/price_rangs.js'/>"></script>

	<%-- One Page, Animated-HeadLin --%>
	<script src="<c:url value='/resources/js/wow.min.js'/>"></script>
	<script src="<c:url value='/resources/js/animated.headline.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.magnific-popup.js'/>"></script>

	<%-- Scrollup, nice-select, sticky --%>
	<script src="<c:url value='/resources/js/jquery.scrollUp.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.nice-select.min.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.sticky.js'/>"></script>

	<%-- contact js --%>
	<script src="<c:url value='/resources/js/contact.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.form.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.validate.min.js'/>"></script>
	<script src="<c:url value='/resources/js/mail-script.js'/>"></script>
	<script src="<c:url value='/resources/js/jquery.ajaxchimp.min.js'/>"></script>

	<%-- Jquery Plugins, main Jquery --%>
	<script src="<c:url value='/resources/js/plugins.js'/>"></script>
	<script src="<c:url value='/resources/js/main.js'/>"></script>

</body>
</html>

<!-- 드롭박스 기능 -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>