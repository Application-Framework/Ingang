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
                            <div class="logo" align="center">
                                <a href="<c:url value='/'/>"><img src="<c:url value='/resources/img/logo/logo7.png'/>" width="150px;" height="100px;" alt=""></a>
                            </div>  
                        </div>
                        <div class="col-lg-9 col-md-9">
                            <div class="menu-wrapper">
                                <!-- Main-menu -->
                                <div class="main-menu">
                                    <nav class="d-none d-lg-block">
                                        <ul id="navigation">
                                            <li><a href="/courses">강의</a>
                                            	<ul class="submenu">
                                                    <li><a href="/">강의전체</a></li>
                                                    <li><a href="/">웹 개발</a></li>
                                                    <li><a href="/">프론트엔드</a></li>
                                                    <li><a href="/">백엔드</a></li>
                                                    <li><a href="/">프로그래밍 언어</a></li>
                                                    <li><a href="/">데이터베이스</a></li>
                                                    <li><a href="/">알고리즘ㆍ자료구조</a></li>
                                                    <li><a href="/">모바일 앱 개발</a></li>
                                                    <li><a href="/">A.I</a></li>
                                                    <li><a href="/">보안</a></li>
                                                    <li><a href="/">기타</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="/notes">노트</a>
                                            	<ul class="submenu">
                                                    <li><a href="/">노트전체</a></li>
                                                    <li><a href="/">팀노트</a></li>
                                                    <li><a href="/">개인노트</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="/community/chats">커뮤니티</a>
                                                <ul class="submenu">
                                                    <li><a href="/community/chats">자유게시판</a></li>
                                                    <li><a href="/community/questions">질문&답변</a></li>
                                                    <li><a href="/community/reviews">수강후기</a></li>
                                                    <li><a href="/community/studies">스터디</a></li>
                                                </ul>
                                            </li>
                                            <!-- 로그인을 수행해야 마이페이지 보임 -->
                                            <c:if test="${member != null}">
                                            	<li><a href="/mypage">마이페이지</a></li>
                                            </c:if>
                                            
                                        </ul>
                                    </nav>
                                </div>          
                                <!-- Header-btn -->
                                <!-- 로그인에 성공한 경우 환영문구, 가입하기, 로그인 버튼 숨김 -->
                                <c:if test="${member == null}">
                                	<div class="header-btn d-none f-right d-lg-block">
	                                    <a href="/signUp" class="btn head-btn1">가입하기</a>
	                                    <a href="/loginPageView" class="btn head-btn2">로그인</a>
                                	</div>
                                </c:if>
                                
                                <!-- 로그인에 성공한 경우 로그아웃 버튼 보여줌 -->
                                <c:if test="${member != null}">
                              		${member.m_name}님 환영합니다!
                                	<div class="header-btn d-none f-right d-lg-block">
	                                    <a href="/logOut" class="btn head-btn2">로그아웃</a>
                                	</div>
                                </c:if>
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