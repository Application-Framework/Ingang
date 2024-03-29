<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>마이페이지</title>
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

.text-align {
	text-align: center;
}

.myPurchase {
	font-size: 20px;
}

a {
	color: #635c5c;
	text-decoration: none;
}

a:hover {
	color: black;
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
</style>
<body>
	<%-- Preloader --%>
	<jsp:include page="./fix/preloader.jsp" />

	<%------------ header section  ------------%>
	<jsp:include page="./fix/header.jsp" />
	<main>
		<br /> <br />

		<div class="container">
			<div class="row">
				<!-- Left content -->
				<div class="col-lg-2 sidebar pe-4 pb-3">
					<aside class="single_sidebar_widget post_category_widget">
						<nav class="navbar bg-light navbar-light">
							<div class="navbar-nav w-100">
								<div class="nav-item dropdown">
									<a href="#" class="nav-link dropdown-toggle"
										data-bs-toggle="dropdown"> <i class="fa fa-laptop me-2"></i>회원정보
									</a>
									<div class="dropdown-menu bg-transparent border-0">
										<a href="/my_checkPW" class="dropdown-item">정보수정</a> <a
											href="/my_delete" class="dropdown-item">회원탈퇴</a>
									</div>
								</div>
								<div class="nav-item dropdown">
									<a href="#" class="nav-link dropdown-toggle"
										data-bs-toggle="dropdown"> <i class="far fa-file-alt me-2"></i>구매내역
									</a>
									<div class="dropdown-menu bg-transparent border-0">
										<a href="/courses_history" class="dropdown-item">강의내역</a> <a
											href="/notes_history" class="dropdown-item">노트내역</a>
									</div>
								</div>
								<div class="nav-item dropdown">
									<a href="#" class="nav-link dropdown-toggle"
										data-bs-toggle="dropdown"> <i class="far fa-file-alt me-2"></i>
										My
									</a>
									<div class="dropdown-menu bg-transparent border-0">
										<a href="/my_course" class="dropdown-item">내 강의</a> <a
											href="/my_note" class="dropdown-item">내 노트</a> <a
											href="/my_post" class="dropdown-item">내 게시글</a>
									</div>
								</div>
							</div>
						</nav>
					</aside>
				</div>
				<!-- Right content -->
				<div class="col-lg-10" style="margin-bottom: 30px;">
					<div class="container">
						<!-- profile start -->
						<div class="bootstrap snippets bootdey">
							<h4 class="m-0 text-uppercase font-weight-bold">회원 정보</h4>
							<hr>
							<div class="row">

								<!-- left column -->
								<div class="col-md-3">
									<div class="text-center">
										<img src="${member.img_path}"
											class="avatar img-circle img-thumbnail" alt="avatar">
									</div>
								</div>

								<!-- edit form column -->
								<div class="col-md-9 personal-info">
									<div class="form-group">
										<label class="col-lg-3 control-label">ID:</label>
										<div class="col-lg-8">
											<label class="form-control">${member.m_id}</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-3 control-label">이름:</label>
										<div class="col-lg-8">
											<label class="form-control">${member.m_name}</label>
										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-3 control-label">가입날짜:</label>
										<div class="col-lg-8">
											<label class="form-control">${member.reg_date}</label>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<!-- profile end -->
			</div>

			<c:if test="${checkTeacher == 1}">
				<!-- 강사 소개 -->
				<div class="container" style="margin-bottom: 60px;">
					<h3 style="margin-bottom: 30px;">강사 소개</h3>
					<div class="col" style="font-size: 20px; border: 1px solid;">
						<c:forEach var="listTeacherIntro" items="${listTeacherIntro}">
							<div style="margin-top: 30px; margin-bottom: 60px;">
								<i class="fa-solid fa-message" style="margin-right: 10px;"></i>${listTeacherIntro.introduction}</div>
							<div style="margin-bottom: 10px;">
								<i class="fa-solid fa-signature" style="margin-right: 10px;"></i>${listTeacherIntro.name}</div>
							<div style="margin-bottom: 10px;">
								<i class="fa-solid fa-envelope" style="margin-right: 10px;"></i>${listTeacherIntro.email}</div>
							<div style="margin-bottom: 10px;">
								<i class="fa-solid fa-phone" style="margin-right: 10px;"></i>${listTeacherIntro.phone}</div>
							<div style="margin-bottom: 10px;">
								<i class="fa-solid fa-link" style="margin-right: 10px;"></i><a
									href="${listTeacherIntro.link}">${listTeacherIntro.link}</a>
							</div>
							<div style="margin-bottom: 10px;">
								<i class="fa-solid fa-calendar-days" style="margin-right: 10px;"></i>${listTeacherIntro.reg_date}</div>
						</c:forEach>
					</div>
				</div>

				<%-- 강의 리스트 출력 부분 --%>
				<div id="content" class="row row-cols-4 mb-3"
					style="margin-bottom: 60px;">
					<div class="container" style="margin-bottom: 30px;">
						<h3>올린 강의(${teacherCourseCount})</h3>
					</div>
					<c:forEach var="listTeacher" items="${listTeacher}">
						<div class="col">
							<div class="card shadow-sm mb-3 d-flex justify-content-center">
								<img src="<c:url value='${listTeacher.img_path}'/>"
									style="padding: 5px; height: 150px; object-fit: contain;" />
								<div class="card-body">
									<div id="course-title" class="card-text"
										style="display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; height: 50px; overflow: hidden; text-overflow: ellipsis">
										<a href="/course/${listTeacher.oli_no}" class="stretched-link">${listTeacher.title}</a>
									</div>
									<div id="teacher-name" class="card-text">${listTeacher.m_name}</div>
									<div class="stars-outer">
										<div class="stars-inner"
											style="width:${courseService.getCourseStarAvg(listTeacher.oli_no)*20}%"></div>
									</div>
									<span class="number-rating">(${courseService.getCourseReplys(listTeacher.oli_no).size()})</span>
									<div id="course-price" class="card-text">₩${listTeacher.price}</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>

				<!-- 수강평 -->
				<h3 style="margin-bottom: 30px;">수강평</h3>
				<div class="col" style="font-size: 20px; border: 1px solid; margin-bottom: 30px;">
					<c:forEach var="listTeacherCourseReply" items="${listTeacherCourseReply}">
						<div>${listTeacherCourseReply.title}</div>
						<div class="stars-outer">
							<div class="stars-inner" style="width:${courseService.getCourseStarAvg(listTeacherCourseReply.oli_no)*20}%"></div>
						</div>
						<div>${listTeacherCourseReply.content}</div>
						<div>${listTeacherCourseReply.reg_date}</div>
					</c:forEach>
				</div>


			</c:if>


			<!-- 구매내역 start -->
			<div class="container">
				<div class="text-center">
					<h3>구매내역</h3>
					<br />
				</div>
				<div class="row">
					<div class="col-lg-6 col-md-8">
						<div class="single-process text-center mb-30">
							<div class="process-cap">
								<h4>
									<strong>강의 구매 건수</strong>
								</h4>
								<p class="myPurchase">${countMyCourse}건</p>
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-md-8">
						<div class="single-process text-center mb-30">
							<div class="process-cap">
								<h4>
									<strong>노트 구매 건수</strong>
								</h4>
								<p class="myPurchase">${countMyNote}건</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 구매내역 end -->
			<br /> <br /> <br />

			<!-- My start -->
			<div class="container">
				<div class="text-center">
					<h3>My</h3>
					<br />
				</div>
				<!-- Apply Process Caption -->
				<div class="row">
					<div class="col-lg-4 col-md-6">
						<div class="single-process text-center mb-30">
							<div class="process-cap">
								<h4>
									<strong>내 강의</strong>
								</h4>
								<p class="myPurchase">${countMyCourse}건</p>
							</div>
						</div>
					</div>
					<div class="col-lg-4 col-md-6">
						<div class="single-process text-center mb-30">
							<div class="process-cap">
								<h4>
									<strong>내 노트</strong>
								</h4>
								<p class="myPurchase">${countMyNote}건</p>
							</div>
						</div>
					</div>
					<div class="col-lg-4 col-md-6">
						<div class="single-process text-center mb-30">
							<div class="process-cap">
								<h4>
									<strong>내 게시글</strong>
								</h4>
								<p class="myPurchase">${countMyPost}건</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- My end -->
			<br /> <br /> <br />

			<div class="container">
				<div class="text-center">
					<h3>문의하기</h3>
					<br />
					<button type="button" class="btn head-btn1" name="btn_inquire"
						onclick="location.href=''">1:1 문의하기</button>
				</div>
			</div>
			<br /> <br /> <br />
		</div>

	</main>
	<jsp:include page="./fix/footer.jsp" />

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