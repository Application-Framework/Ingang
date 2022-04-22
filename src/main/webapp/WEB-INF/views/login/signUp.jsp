<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>회원가입</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/favicon.ico'/>">
	
	<!-- 아이콘 -->
	<link rel="stylesheet" href="https://fonts.sandbox.google.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<link rel="stylesheet" href="https://fonts.sandbox.google.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<link rel="stylesheet" href="https://fonts.sandbox.google.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
	<link rel="stylesheet" href="https://fonts.sandbox.google.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
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
<body>
	<%-- Preloader --%>
	<jsp:include page="../fix/preloader.jsp" />
	   
	<%------------ header section  ------------%>
	<jsp:include page="../fix/header.jsp" />

	<!-- Signup Section Begin -->
    <section class="signup spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div class="login__form">
                        <h3>회원가입</h3>
                        <form role="form" method="post" autocomplete="off">
                            <div class="input__item">
                                <input type="text" class="form-control" name="m_id" placeholder="id를 입력해 주세요">
                                <span class="material-symbols-outlined">
									account_circle 
								</span>
							</div>
                            <div class="input__item">
                                <input type="password" class="form-control" name="m_pw" placeholder="pw를 입력해 주세요">
                                <span class="material-symbols-outlined">
									lock
								</span>
                            </div>
                            <div class="input__item">
                                <input type="text" class="form-control" name="m_name" placeholder="이름을 입력해 주세요">
                                <span class="material-symbols-outlined">
									badge
								</span>
                            </div>
                            <div class="input__item">
                                <input type="date" class="form-control" name="m_birth" placeholder="생일을 입력해 주세요">
                                <span class="material-symbols-outlined">
									celebration
								</span>
                            </div>
                            <div class="gender">
                                <input type="radio" name="m_sex" value="1" checked>남성
								<input type="radio" name="m_sex" value="2">여성
                                <span class="material-symbols-outlined">
									person
								</span>
                            </div>
                            <div class="input__item">
                                <input type="text" class="form-control" name="m_phone" placeholder="전화번호를 입력해 주세요"  oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                                <span class="material-symbols-outlined">
									call
								</span>
                            </div>
                            <button type="submit" class="btn head-btn2">회원가입</button>
                        </form>
                        <br/>
                        <h5>이미 계정이 있으신가요?</h5>
                        <a href="/login" class="forget_pass">로그인하러 가기</a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Signup Section End -->
    
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