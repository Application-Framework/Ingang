<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link href='<c:url value="/resources/css/sb-admin-2.min.css"/>' rel="stylesheet">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<link href='<c:url value="/resources/css/sb-admin-2.min.css"/>' rel="stylesheet">
<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
	<ul class="navbar-nav ml-auto">
		<li class="nav-item dropdown no-arrow">
			<a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				<span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.member.getM_id() }</span>
				<img class="img-profile rounded-circle" src='<c:url value="/resources/img/profile.svg"/>'>
			 </a>
			<div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
				<a class="dropdown-item" href="#" data-toggle="modal" data-target="#profileEditModal">
					<i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>내 정보
				</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="/ex/main">
					<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>메인화면
				</a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="logout">
					<i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>로그아웃
				</a>
			</div>
		</li>
	</ul>
</nav>