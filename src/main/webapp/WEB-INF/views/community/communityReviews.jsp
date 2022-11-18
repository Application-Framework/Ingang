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
<style type="text/css">
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
			
			<div class="col-lg-10 mb-5 mb-lg-0">
			<br>
				<div class="blog_left_sidebar">
					<article class="blog_item">
						<aside class="single_sidebar_widget search_widget">
							<form action="communityReviews">
								<div class="row">
									<div class="col-lg-10" align="left">
										<input type="text" class="form-control" name="searchKeyword" placeholder='내용을 검색해보세요!' <c:if test="${searchKeyword ne null}"> value = "${searchKeyword}" </c:if>>
									</div>
									<div class="col-lg-2" align="left" style="padding-left: 7px;">
										<input type="submit" class="genric-btn danger-border radius" value="검색" style="width: 100%;">
									</div>
								</div>
							</form>
						</aside>
					</article>

					<div class="container">
						<div class="row">
							<c:forEach var="cbList" items="${cbReviewList}">
								<article class="blog_item" style="width: 100%;">
									<div class="blog_details" style="padding: 10px 10px 10px 10px;">
										<table>
											<tr>
												<td>
													<div>
														<c:choose>
															<c:when test="${cbList.img_path eq null}">
																<img alt="" src="<c:out value='/resources/img/logo/logo5.png'></c:out>" width="80px;">
															</c:when>
															<c:otherwise>
																<img alt="" src="<c:out value='${cbList.img_path}'></c:out>" width="80px;">
															</c:otherwise>
														</c:choose>
													</div>
												</td>
												<td>
													<div class="stars-outer">
														<div class="stars-inner" style="width:${cbList.star_rating*20}%"></div>
													</div>
													<p style="margin: 0 0px;"><c:url value="${cbList.content}"/></p>
													<ul class="blog-info-link">
														<li><a href="#"><i class="fa fa-user"></i> <font size="1" color="#848484"><c:url value="${cbList.m_id}"/></font></a> </li>
														<li><i class="fa fa-clock-o"> </i><font size="1" color="#848484"><c:url value="${cbList.reg_date}" /></font></li>
														<li><font size="1" color="#848484">강의명 : <c:url value="${cbList.title}" /></font></li>
													</ul>
												</td>
											</tr>
										</table>
									</div>
								</article>
							</c:forEach>
						</div>

						<c:if test="${Paging.totalCount > 10}">
							<nav class="blog-pagination justify-content-center d-flex" style="margin: 0px;">
								<ul class="pagination" id="pagingDiv">
									<!-- 첫 페이지면 Disabled 아니라면 Enabled -->
									<c:choose>
										<c:when test="${Paging.pageNo eq Paging.firstPageNo }">
										</c:when>
										<c:otherwise>
											<li class="page-item"><a href="communityReviews?page=${Paging.prevPageNo}" class="page-link" aria-label="Previous"> <i class="ti-angle-left"></i> </a></li>
										</c:otherwise>
									</c:choose>
									<!-- 페이지 갯수만큼 버튼 생성 -->
									<c:forEach var="i" begin="${Paging.startPageNo }" end="${Paging.endPageNo }" step="1">
										<c:choose>
											<c:when test="${i eq Paging.pageNo }">
												<li class="page-item  active disabled"> <a href="communityReviews?page=${i}" class="page-link"><c:out value="${i }"/></a> </li>
											</c:when>
											<c:otherwise>
												<li class="page-item"> <a href="communityReviews?page=${i}" class="page-link"><c:out value="${i }"/></a> </li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<!-- 마지막 페이지면 Disabled 아니라면 Enabled -->
									<c:choose>
										<c:when test="${Paging.pageNo eq Paging.finalPageNo }">
										</c:when>
										<c:otherwise>
											<li class="page-item"><a href="communityReviews?page=${Paging.nextPageNo}" class="page-link" aria-label="Next"> <i class="ti-angle-right"></i></a></li>
										</c:otherwise>
									</c:choose>
								</ul>
							</nav>
						</c:if>
					</div><br>
				</div>
			</div>
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