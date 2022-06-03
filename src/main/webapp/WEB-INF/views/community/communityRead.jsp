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
	
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	

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
		   <!-- Hero Area End -->
		   <!--================Blog Area =================-->
		   <section >
		      <div class="container">
		         <div class="row">
		            <div class="col-lg-10 posts-list">
		               <div class="single-post">
		                  <div class="blog_details">
		                     <h2>제목 </h2>
		                     <ul class="blog-info-link mt-3 mb-4">
		                        <li><a href="#"><i class="fa fa-user"></i> 작성자</a></li>
		                        <li><a href="#">조회수 7</a></li>
		                        <li><a href="#"><i class="fa fa-clock"></i> 2022-05-02</a></li>
		                     </ul>
							<P>
								내용
							</P>
		                  </div>
		               </div>
		               <div class="comments-area">
		                  <h4>05 댓글</h4>
		                  <div class="comment-list">
		                     <div class="single-comment justify-content-between d-flex">
		                     
								<c:forEach var="cbrList" items="${cbrList}">
			                        <div class="user justify-content-between d-flex">
			                           <div class="thumb">
			                           </div>
			                           <div class="desc">
			                              <div class="d-flex justify-content-between">
			                                 <div class="d-flex align-items-center">
			                                    <h5>
			                                       <a href="#"><c:out value="${cbrList.m_id}"/>작성자</a>
			                                    </h5>
			                                    <p class="date"><c:out value="${cbrList.reg_date}"/>작성일</p>
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
		               <div class="comment-form">
		                  <h4>댓글</h4>
		                  <form class="form-contact comment_form" action="#" id="commentForm">
		                     <div class="row">
		                        <div class="col-12">
		                           <div class="form-group">
		                              <textarea class="form-control w-100" name="comment" id="comment" cols="30" rows="5" placeholder="의견을 남겨주세요"></textarea>
		                           </div>
		                        </div>
		                     </div>
		                     <div class="form-group" align="right">
		                        <button type="submit" class="button button-contactForm btn_1 boxed-btn">작성</button>
		                     </div>
		                  </form>
		               </div>
						</div>
						<div class="col-lg-2">
							<ul>
								<li>
									<button class="genric-btn danger-border radius"> <i class="fa fa-heart"></i> 6</button>
								</li><br>
								<li>
									<button class="genric-btn danger-border radius"> 댓글</button>
								</li>
							</ul>
						</div>
		        	</div>
				</div>
			</section>
		   <!--================ Blog Area end =================-->
		</div>
	</div>
    
    <%------------ footer section  ------------%>
    <jsp:include page="../fix/footer.jsp" />

    <%-- Jquery Plugins, main Jquery --%>
	<script src="<c:url value='/resources/js/plugins.js'/>"></script>
    <script src="<c:url value='/resources/js/main.js'/>"></script>
</body>
</html>