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
	
<link rel="stylesheet" href="<c:url value='/resources/css/community/tag.css'/>">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.10/dist/sweetalert2.min.js"></script>

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
		<!-- Hero Area End -->
		<!--================Blog Area =================-->
	<section >
		<div class="container">
			<div class="row">
				<div class="col-lg-10 posts-list">
					<div class="single-post">
						<div class="blog_details">
							<h2>${cbReadPage.title}</h2>
							<ul class="blog-info-link mt-3 mb-4">
								<li><a href="#"><i class="fa fa-user"></i> ${cbReadPage.m_id}</a></li>
								<c:choose>
									<c:when test="${hitReadPage eq 1}"> 
										<li><a  href="#">조회수 ${cbReadPage.hit+1}</a></li>
									</c:when>
									<c:otherwise> 
										<li><a  href="#">조회수 ${cbReadPage.hit}</a></li>
									 </c:otherwise>
								</c:choose>
								
								<li><a href="#"><i class="fa fa-clock"></i> ${cbReadPage.reg_date}</a></li>
								
								<c:if test="${sessionScope.member.getM_no() eq cbReadPage.m_no}">
									<li><a href="javascript:void(0)" onclick="buttonModify(${cbReadPage.cb_no}, ${classify})"><i class="far fa-edit"></i> 수정</a></li>
									<li><a href="javascript:void(0)" onclick="boardDelete(${cbReadPage.cb_no})"><i class="fas fa-trash-alt"></i> 삭제</a></li>
								</c:if>
							</ul>
							<P>
								${cbReadPage.content}
							</P>
							<ul id="tag-list" style=""> 
								<c:forEach var="cbTag" items="${cbTag}">
									<li class="tag-item">#${cbTag.tag_name}</li>
								</c:forEach>
							</ul>
							
							<div >
							<hr>
							<c:if test="${cbReadPage.oli_no ne 0}">
								관련강의
								<div class="user justify-content-start d-flex">
									<div class="thumb">
										<c:if test="${cbReadPage.oli_img_path ne null}">
											<img alt="" width="100px;" src="${cbReadPage.oli_img_path}">
										</c:if>
										<c:if test="${cbReadPage.oli_img_path eq null}">
											<img alt="" width="100px;" src="<c:out value='/resources/img/logo/logo5.png'></c:out>">
										</c:if>
									</div>
									<div class="desc">
										<div>
											<div class="d-flex align-items-center">
												<ul class="blog-info-link mt-3 mb-4">
													<li><a href="#"><i class="fa fa-tablet"></i> <c:out value="${cbReadPage.oli_title}"/></a></li>
													<li><a href="#"><i class="fa fa-user"></i> <c:out value="${cbReadPage.olt_name}"/></a> </li>
												</ul>
											</div>
										</div>
										<p class="comment">
											${fn:substring(cbReadPage.oli_content, 0 ,170)}
										</p>
									</div>
								</div>
							</c:if>
							</div>
						</div>
					</div>
					
					<div class="comments-area" style="padding: 20px 0px" id="replyList">
						<h4><a name="target"><c:out value="${cbReadPage.cbr.conunt}"/>개의 댓글이 달렸습니다.</a></h4>
						<div class="comment-list">
							<div >
							<c:forEach var="cbrList" items="${cbrList}">
								<div class="user justify-content-start d-flex">
									<div class="thumb">
										<img alt="" src="<c:out value='/resources/img/logo/logo5.png'></c:out>">
									</div>
									<div class="desc">
										<div>
											<div class="d-flex align-items-center">
												<ul class="blog-info-link mt-3 mb-4">
													<li><a href="#"><i class="fa fa-user"></i> <c:out value="${cbrList.m_id}"/></a></li>
													<li><a href="#"><i class="fa fa-clock"></i> <c:out value="${cbrList.reg_date}"/></a></li>
													<c:if test="${sessionScope.member.getM_no() eq cbrList.m_no}">
														<li><a href="javascript:void(0)" onclick="replyEdit(${cbrList.cbr_no}, '${cbrList.content}')"><i class="far fa-edit"></i> 수정</a></li>
														<li><a href="javascript:void(0)" onclick="replyDelete(${cbrList.cbr_no})"><i class="fas fa-trash-alt"></i> 삭제</a></li>
													</c:if>
												</ul>
											</div>
										</div>
										<p class="comment">
											<c:out value="${cbrList.content}"/>
										</p>
									</div>
								</div>
							</c:forEach>
							</div>
						</div>
					</div>
					<div class="comment-form" id="replyTop">
						<c:choose>
							<c:when test="${member ne null}"> 
							<h4>댓글</h4>
								<form class="form-contact comment_form" id="commentForm" method="POST">
									<div class="row">
										<div class="col-12">
											<input type="hidden" id="cb_no" name="cb_no" value="${cbReadPage.cb_no}">
											<input type="hidden" id="m_no" name="m_no" value="${sessionScope.member.getM_no()}">
											<div class="form-group">
												<textarea class="form-control w-100" name="content" id="content" cols="30" rows="5" placeholder="의견을 남겨주세요"></textarea>
											</div>
										</div>
									</div>
									<div class="form-group" align="right">
										<button type="button"  id="btnReplyWrite" class="button button-contactForm btn_1 boxed-btn">작성</button>
									</div>
								</form>
							</c:when>
							<c:otherwise>
								<div align="center"><a href="loginPageView">
									<button type="button"  class="button button-contactForm btn_1 boxed-btn" style="width: 80%;">로그인 후, 댓글 작성이 가능합니다!</button>
									</a>
								</div><br>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</section>
			<!--================ Blog Area end =================-->
	<%------------ footer section  ------------%>
	<jsp:include page="../fix/footer.jsp" />
	<script src="<c:url value='/resources/js/vendor/jquery-1.12.4.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
<%-- Jquery Plugins, main Jquery --%>
<script src="<c:url value='/resources/js/plugins.js'/>"></script>
<script src="<c:url value='/resources/js/main.js'/>"></script>

</body>
</html>