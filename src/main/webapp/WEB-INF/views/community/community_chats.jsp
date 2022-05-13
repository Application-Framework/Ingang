<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    
    <div class="container-flex" style="background-color: #000a12; width: 100%; height: 100px;">
    	<div class="container">
	    	<div style="padding: 20px;">
	    		<h4 class="font-weight-bold"><font color="#FFFFFF" style="font-family:; ">이야기를 나눠요</font></h4>
	    		<font color="#FFFFFF">500만의 커뮤니티!! 함께 토론해봐요</font> 
	    	</div>
    	</div>
    </div><br>
   
    <!--================Blog Area =================-->
    <div class="container">
    	<div class="row">
			<jsp:include page="community_left_sidebar.jsp" />
			
			<div class="col-lg-7 mb-5 mb-lg-0">
			<br>
				<div class="blog_left_sidebar">
					<article class="blog_item">
						<aside class="single_sidebar_widget search_widget">
							<form action="#">
								<div class="row" style="">
									<div class="col-lg-10" >
										<input type="text" class="form-control" placeholder='내용을 검색해보세요!'>
										<input type="text" class="form-control" placeholder='태그로 검색해보세요!' style="margin-top: 10px;">
									</div>
									<div class="col-lg-2" style="padding-left: 7px;">
									
									<input type="button" class="genric-btn danger-border radius" value="검색" style="width: 100%;" >
									</div>
								</div>
							</form>
						</aside>
					</article>

					<div class="container">
						<div class="row">
							<ul class="nav nav-tabs">
								<li class="nav-item"><a class="nav-link active" data-toggle="tab" href="#qwe"><h6 style="color: #5D5D5D;"> 최신순</h6></a></li>
								<li class="nav-item"><a class="nav-link" data-toggle="tab" href="#asd"><h6 style="color: #5D5D5D;">좋아요순</h6></a></li>
									&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
									&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&nbsp;&nbsp;
								<li class="nav-item">
									<button type="button" onclick="location.href='/'"
										class="genric-btn danger radius">
										<font size="1px;">글쓰기</font>
									</button>
								</li>
							</ul>

						
							<div class="tab-content">
								<div class="tab-pane fade show active" id="qwe">
									<c:forEach var="cbList" items="${cbRegDateList}">
										<article class="blog_item">
											<div class="blog_details">
												<a class="d-inline-block" href="single-blog.html">
													번호 : <c:url value="${cbList.cb_no}"/>
													<h2><c:url value="${fn:substring(cbList.title, 0, 35)}"/></h2>
												</a>
												<p><c:url value="${fn:substring(cbList.content,0,200)}"/></p>
												<ul class="blog-info-link">
													<li><a href="#"><i class="fa fa-user"></i> <c:url value="${cbList.m_id}"/></a> </li>
													<li><a href="#"><i class="fa fa-comments"></i> <c:url value="${cbList.reply}"/> </a></li>
													<li><a href="#"><i class="fa fa-heart"></i> <c:url value="${cbList.good}"/></a></li>
													<li><i class="fa fa-clock-o"> </i><font size="2" color="#848484"><c:url value="${cbList.reg_date}"/></font></li>
												</ul>
											</div>
										</article>
									</c:forEach>
								</div>
								<div class="tab-pane fade" id="asd">
									<div class="tab-pane fade show active" id="qwe">
										<c:forEach var="cbGoodShowList" items="${cbGoodShowList}">
											<article class="blog_item">
												<div class="blog_details">
													<a class="d-inline-block" href="single-blog.html">
														<h2><c:url value="${fn:substring(cbGoodShowList.title, 0, 30)}"/></h2>
													</a>
													<p><c:url value="${fn:substring(cbGoodShowList.content,0,200)}"/></p>
													<ul class="blog-info-link">
														<li><a href="#"><i class="fa fa-user"></i> <c:url value="${cbGoodShowList.m_id}"/></a></li>
														<li><a href="#"><i class="fa fa-comments"></i> <c:url value="${cbGoodShowList.reply}"/> </a></li>
														<li><a href="#"><i class="fa fa-heart"></i> <c:url value="${cbGoodShowList.good}"/></a></li>
														<li><i class="fa fa-clock-o"> </i><font size="2" color="#848484"><c:url value="${cbGoodShowList.reg_date}"/></font></li>
													</ul>
												</div>
											</article>
										</c:forEach>
									</div>
								
								</div>

							</div>
						</div>
					</div>


					<nav class="blog-pagination justify-content-center d-flex">
						<ul class="pagination">
							<!-- 첫 페이지면 Disabled 아니라면 Enabled -->
							<c:choose>
								<c:when test="${Paging.pageNo eq Paging.firstPageNo }">
								</c:when>
								<c:otherwise>
									<li class="page-item"><a href="chats?page=${Paging.prevPageNo}" class="page-link" aria-label="Previous"> <i class="ti-angle-left"></i> </a></li>
								</c:otherwise>
							</c:choose>
							<!-- 페이지 갯수만큼 버튼 생성 -->
							<c:forEach var="i" begin="${Paging.startPageNo }" end="${Paging.endPageNo }" step="1">
								<c:choose>
									<c:when test="${i eq Paging.pageNo }">
										<li class="page-item  active"> <a href="chats?page=${i}" class="page-link"><c:out value="${i }"/></a> </li>
									</c:when>
									<c:otherwise>
										<li class="page-item"> <a href="chats?page=${i}" class="page-link"><c:out value="${i }"/></a> </li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<!-- 마지막 페이지면 Disabled 아니라면 Enabled -->
							<c:choose>
								<c:when test="${Paging.pageNo eq Paging.finalPageNo }">
								</c:when>
								<c:otherwise>
									<li class="page-item"><a href="chats?page=${Paging.nextPageNo}" class="page-link" aria-label="Next"> <i class="ti-angle-right"></i></a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</nav>
				</div><br>
			</div>
			<jsp:include page="community_tag_sidebar.jsp" />
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