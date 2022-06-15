<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>강의 구매 내역</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/favicon.ico'/>">

	<!-- 아이콘 -->
	<link rel="stylesheet" href="https://fonts.sandbox.google.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<link rel="stylesheet" href="https://fonts.sandbox.google.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

	<!-- CSS here -->
	<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/owl.carousel.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/flaticon.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/price_rangs.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/slicknav.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/animate.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/magnific-popup.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/fontawesome-all.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/themify-icons.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/slick.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/nice-select.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
</head>
<style>
	a {
		color: orange;
	}
	
	.sidebar {
	    width: 400px;
	    height: 500px;
	    overflow-y: auto;
	    background: var(--light);
	    transition: 0.5s;
	    z-index: 999;
	}
	
	@media (min-width: 992px) {
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
	
	@media (max-width: 991.98px) {
	    .sidebar {
	        margin-left: -250px;
	    }
	
	    .sidebar.open {
	        margin-left: 0;
	    }
	}
	
	.sidebar .navbar .navbar-nav .nav-link {
	    padding: 7px 10px;
	    color: var(--dark);
	    font-weight: 500;
	    border-left: 3px solid var(--light);
	    border-radius: 0 30px 30px 0;
	    outline: none;
	}
	
	.sidebar .navbar .navbar-nav .nav-link:hover,
	.sidebar .navbar .navbar-nav .nav-link.active {
	    color: var(--primary);
	    background: #FFFFFF;
	    border-color: var(--primary);
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
	
	.sidebar .navbar .navbar-nav .nav-link:hover i,
	.sidebar .navbar .navbar-nav .nav-link.active i {
	    background: var(--light);
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
            
            .text-align {
            	text-align: center;
            }
</style>
<body>
	<%-- Preloader --%>
	<jsp:include page="../fix/preloader.jsp" />

	<%------------ header section  ------------%>
	<jsp:include page="../fix/header.jsp" />

	<div class="container">
		<div class="row">
			<!-- Left content -->
			<div class="col-lg-3 sidebar pe-4 pb-3">
				<aside class="single_sidebar_widget post_category_widget">
					<nav class="navbar bg-light navbar-light">
						<div class="navbar-nav w-100">
							<div class="nav-item dropdown">
								<a href="#" class="nav-link dropdown-toggle"
									data-bs-toggle="dropdown"> <i class="fa fa-laptop me-2"></i>회원정보
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
			<div class="col-lg-9">
				<section class="featured-job-area">
					<div class="container">
						<%-- 강의 검색 입력 폼 --%>
						<form action="${nowURL}" class="search-box mb-5">
							<div class="input-form item">
								<input type="text" name="keyword" value="${keyword}" placeholder="강의 제목 검색" tabindex="0">
							</div>
							<div class="search-form item">
								<button type="submit" class="btn w-100 h-100">검색</button>
							</div>
						</form>

						<%-- 강의 리스트 출력 부분 --%>
						<div class="row d-flex justify-contnet-center">
							<c:forEach var="list" items="${ocList}">
							<div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
						    	<div class="card shadow-sm mb-3">
							        <img src="<c:url value='${list.img_path}'/>" style="height:150px"/>
							        <div class="card-body">
									    <div id="course-title" class="card-text" style="display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; height:50px; overflow:hidden; text-overflow:ellipsis"><a href="/courses/${list.oli_no}">${list.title}</a></div>
									    <div style="font-size: 10px;">${list.reg_date}</div>
									    <div id="teacher-name" class="card-text">${list.name}</div>
									    <div class="stars-outer">
									    		<div class="stars-inner" style="width:${list.star_avg*20}%"></div>
									    </div>
									    <span class="number-rating">(${list.star_avg})</span>
									    <div id="course-price" class="card-text">₩${list.price}</div>
							        </div>
						        </div>
						    </div>
			               	</c:forEach>
						</div>
					</div>
				</section>
				<%-- 페이지 번호 --%>
				<div class="pagination-area pb-115 text-center mt-5">
					<div class="container">
						<div class="row">
							<div class="col-xl-12">
								<div class="single-wrap d-flex justify-content-center">
									<nav aria-label="Page navigation example">
										<ul class="pagination justify-content-start">
											<li class="page-item active"><a class="page-link"
												href="#">01</a></li>
										</ul>
									</nav>
								</div>
							</div>
						</div>
					</div>
				</div>
				<%-- 페이지 번호 끝 --%>
			</div>
		</div>
	</div>

	<%------------ footer section  ------------%>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>