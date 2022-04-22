<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
    <header>
        <div class="header-area header-transparrent">
            <div class="headder-top header-sticky">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-lg-3 col-md-2">
                            <!-- Logo -->
                            <div class="logo">
                                <a href="<c:url value='/'/>"><img src="<c:url value='/resources/img/logo/logo.png'/>" alt=""></a>
                            </div>  
                        </div>
                        <div class="col-lg-9 col-md-9">
                            <div class="menu-wrapper">
                                <!-- Main-menu -->
                                <div class="main-menu">
                                    <nav class="d-none d-lg-block">
                                        <ul id="navigation">
                                            <li><a href="/courses">강의</a></li>
                                            <li><a href="/notes">노트</a></li>
                                            <li><a href="/community/chats">커뮤니티</a>
                                                <ul class="submenu">
                                                    <li><a href="/community/chats">자유게시판</a></li>
                                                    <li><a href="/community/questions">질문&답변</a></li>
                                                    <li><a href="/community/reviews">수강후기</a></li>
                                                    <li><a href="/community/studies">스터디</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="/mypage">마이페이지</a></li>
                                        </ul>
                                    </nav>
                                </div>          
                                <!-- Header-btn -->
                                <div class="header-btn d-none f-right d-lg-block">
                                    <a href="/signUp" class="btn head-btn1">Register</a>
                                    <a href="/login" class="btn head-btn2">Login</a>
                                </div>
                            </div>
                        </div>
                        <!-- Mobile Menu -->
                        <div class="col-12">
                            <div class="mobile_menu d-block d-lg-none"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
</body>
</html>