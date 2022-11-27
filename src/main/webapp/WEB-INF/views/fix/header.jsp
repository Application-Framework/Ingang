<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="https://use.fontawesome.com/releases/v6.2.0/js/all.js"></script>

<style>
	<!-- 드롭다운 CSS -->
	.dropdown{
	  position : relative;
	  display : inline-block;
	}
	
	.dropbtn_icon{
	  font-family : 'Material Icons';
	}
	.dropbtn{
	  border : 1px #9c9c9c;
	  border-radius : 4px;
	  background-color: #ededeb;
	  font-weight: 400;
	  color : white;
	  padding : 12px;
	  /*width :200px;*/
	  text-align: left;
	  cursor : pointer;
	  font-size : 12px;
	}
	.dropbtn:hover{
		color : #30c000;
	}
	.dropdown-content{
	  box-shadow: 1px 1px 1px 1px #9c9c9c;
	  border-radius : 8px;
	  display : none;
	  position : absolute;
	  z-index : 999; /*다른 요소들보다 앞에 배치*/
	  font-weight: 400;
	  background-color: #f9f9f9;
	  min-width : 400px;
	}
	
	.dropdown-content a{
	  display : block;
	  text-decoration : none;
	  color : rgb(37, 37, 37);
	  font-size: 16px;
	  padding : 12px 20px;
	}
	
	.dropdown-content a:hover{
	  background-color : #ececec
	}
	
	.dropdown:hover .dropdown-content {
	  display: block;
	}
</style>
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
                                            	<!-- <ul class="submenu">
                                                    <li><a href="/courses">강의전체</a></li>
                                                    <li><a href="/courses/web-dev">웹 개발</a></li>
                                                    <li><a href="/courses/front-end">프론트엔드</a></li>
                                                    <li><a href="/courses/back-end">백엔드</a></li>
                                                    <li><a href="/courses/programming-lang">프로그래밍 언어</a></li>
                                                    <li><a href="/courses/database-dev">데이터베이스</a></li>
                                                    <li><a href="/courses/algorithm">알고리즘ㆍ자료구조</a></li>
                                                    <li><a href="/courses/mobile-app">모바일 앱 개발</a></li>
                                                    <li><a href="/courses/artificial-intelligence">A.I</a></li>
                                                    <li><a href="/courses/security">보안</a></li>
                                                </ul> -->
                                            </li>
                                            <li><a href="/notes">노트</a>
                                            	<ul class="submenu">
                                                    <li><a href="/notes">노트전체</a></li>
                                                    <li><a href="/">팀노트</a></li>
                                                    <li><a href="/">개인노트</a></li>
                                                </ul>
                                            </li>
                                            <li><a href="/communityChats">커뮤니티</a>
                                                <ul class="submenu">
                                                    <li><a href="/communityChats">자유게시판</a></li>
                                                    <li><a href="/communityQuestions">질문&답변</a></li>
                                                    <li><a href="/communityReviews">수강후기</a></li>
                                                    <li><a href="/communityStudies">스터디</a></li>
                                                </ul>
                                            </li>
                                            <li>
                                            	<a href="/open-knowledge">지식공유 참여</a>
                                            </li>
                                            
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
                                	<div class="row">
	                              		<!-- 수강바구니(찜한목록) -->
	                              		<div class="dropdown col-lg-4">
									    	<button class="dropbtn"> 
									      		<span class="dropbtn_icon"><i class="fa-sharp fa fa-cart-shopping"></i></span>
									    		
									    	</button>
									    	<div class="dropdown-content">
									    		<div class="container" style="margin-top: 30px; margin-bottom: 30px;">
									    			<div class="row">
									    			
										    			<!-- 관심 강의 총액 구하기 -->
										    			<c:set var="sum" value="0" />
										    			<c:set var="count" value="0" />
												    	<c:forEach var="dropInterlist" items="${dropInterList}">
												    		<c:set var="sum" value="${sum + (dropInterlist.price)}" />
												    	</c:forEach>
												    	<%-- <c:forEach var="dropInterListCnt" items="${dropInterestedListCount}">
												    		<c:set var="count" value="${count + (dropInterListCnt)}" />
												    	</c:forEach> --%>
									    			
										    			<div class="col-lg-6" style="font-size:18px; font-weight: 800; margin-bottom: 30px;">
											    			수강바구니 ${dropInterListCnt} 개
											    		</div>
											    		<div class="col-lg-6" style="font-size:18px; font-weight: 800;">
											    			결제금액 : <fmt:formatNumber pattern="###,###,###" value="${sum}" /> 원
											    		</div>
											    		<hr>
											    		<div class="col-lg-12" style="font-size:18px; font-weight: 800;">
											    			<c:forEach var="dropInterlist" items="${dropInterList}">
											    				<div class="container">
											    					<div class="row">
												    					<a href="/course/${dropInterlist.oli_no}">
													    					<div class="col-lg-12" style="font-size: 12px;">
													    						<table>
													    							<tr>
														    							<td rowspan="2"><img src="<c:url value='${dropInterlist.img_path}'/>" style="width:80px; height:40px; margin-left:-20px; margin-right: 20px;"/></td>
														    							<td>${dropInterlist.title}</td>
													    							</tr>
													    							<tr>
														    							<td>${dropInterlist.price} 원</td>
													    							</tr>
													    						</table>
														    				</div>
													    				</a>
																	</div>
											    				</div>
											    			</c:forEach>
											    		</div>
										    		</div>
									    		</div>
									    		<hr>
										        <a href="#" style="text-align:center; font-size: 20px; color:white; background-color: #30c000;">수강바구니에서 전체보기</a>
									    	</div>
									  	</div>
									  	
									  	<!-- 알람 -->
	                              		<div class="dropdown col-lg-4">
									    	<button class="dropbtn"> 
									      		<span class="dropbtn_icon"><i class="fa-sharp fa fa-bell"></i></span>
									    		
									    	</button>
									    	<div class="dropdown-content">
									    		<div class="container" style="margin-top: 30px; margin-bottom: 30px;">
									    			<div class="row">
										    			<div class="col-lg-12" style="font-size:18px; text-align: center; font-weight: 800;">
											    			읽지 않은 알람 00건
											    		</div>
										    		</div>
									    		</div>
									    		<hr>
										        <a href="#" style="text-align:center; font-size: 20px; color:white; background-color: #30c000;">더 많은 알람 보기</a>
									    	</div>
									  	</div>
									  	
									  	<!-- 마이페이지 -->
	                              		<div class="dropdown col-lg-4">
									    	<button class="dropbtn"> 
									      		<span class="dropbtn_icon"><i class="fa-sharp fa fa-user"></i></span>
									    		
									    	</button>
									    	<div class="dropdown-content">
									    		<div class="container" style="margin-top: 30px; margin-bottom: 30px;">
									    			<div class="row">
										    			<div class="col-lg-3">
											    			<img src="${member.img_path}" class="avatar img-circle img-thumbnail" alt="avatar">
											    		</div>
											    		<div class="col-lg-9">
											    			<a href="/mypage">${member.m_name}</a>
											    		</div>
										    		</div>
									    		</div>
									    		<hr>
										      	<a href="/mypage">대시보드</a>
										      	<a href="/my_course">내 강의</a>
										      	<a href="/my_note">내 노트</a>
										      	<a href="/my_post">작성한 글</a>
										      	<hr>
										      	<div class="row">
										      		<div class="col-lg-6">
												    	<a href="/logOut">로그아웃</a>
												    </div>
												    <div class="col-lg-6" style="text-align: right;">
												    	<a href="/mypage">마이페이지</a>
												    </div>
										      	</div>
										      	
									    	</div>
									  	</div>
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