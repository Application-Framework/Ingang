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
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" />
	
<link href='<c:url value="/resources/css/community/inquiry.css"/>' rel="stylesheet">
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
			<jsp:include page="communityLeftSidebar.jsp" />
			
			<div class="col-lg-10 mb-5 mb-lg-0" style="padding:0 0px;">
			<br>
				<div class="blog_left_sidebar">
					<article class="blog_item">
						<form id="searchForm" action="communityInquiry">
							<div class="row">
								<div class="col-lg-2" >
								<select class="form-control" id="searchType" name="searchType">
									<option value="title">제목</option>
									<option value="category">카테고리</option>
									<option value="m_id">작성자</option>
								</select>
								</div>
								<div class="col-lg-8" >
									<input type="text" id="searchKeyword " name="searchKeyword" placeholder="검색어를 입력하세요." class="form-control" required="required">
								</div>
								<div class="col-lg-2" style="padding:0 15px;">
									<input type="submit" class="genric-btn danger-border radius"  id="btnBoardSearch" value="검색" style="width: 100%;" >
								</div>
							</div>
						</form>
					</article>
					
					<div class="container">
						<div class="row">
						
								<div class="d-flex">
									<div class="mr-auto"></div>
								</div>
								<table class="table table-white">
									<colgroup>
										<col width="5%">
										<col width="15%">
										<col width="45%">
										<col width="10%">
										<col width="15%">
										<col width="10%">
									</colgroup>
									<thead>
										<tr>
											<th>No</th>
											<th>카테고리</th>
											<th>제목</th>
											<th>작성자</th>
											<th>작성일</th>
											<th>답변상태</th>
										</tr>
									</thead>
									<tbody>
									<c:if test="${Paging.totalCount > 0}">
										<c:forEach items="${cbInquiry}" var="cbInquiry">
											<tr>
												<td>${cbInquiry.inq_no}</td>
												<td><span class="inquiry-category">${cbInquiry.category }</span></td>
												<td><a href="inquiryView?inq_no=${cbInquiry.inq_no}"><font color="black">${cbInquiry.title }</font></a></td>
												<td>${cbInquiry.m_id}</td>
												<td><fmt:formatDate value="${cbInquiry.reg_date }" pattern="yyyy-MM-dd a h:mm"/><br>
												<fmt:formatDate value="${cbInquiry.reg_date }" pattern="a h:mm"/></td>
											<c:choose>
												<c:when test="${cbInquiry.statement eq 0}">
													<td><span class="inquiry-status status-0">답변대기</span></td>
												</c:when>
												<c:when test="${cbInquiry.statement eq 1}">
													<td><span class="inquiry-status status-1">답변보류</span></td>
												</c:when>
												<c:otherwise>
													<td><span class="inquiry-status status-2">답변완료</span></td>
												</c:otherwise>
											</c:choose>
											</tr>
										</c:forEach>
									</c:if>
									</tbody>
								</table>
							<c:if test="${Paging.totalCount < 1}">
								<div class="col-lg-12" align="center" style="margin: 25% 0;">등록된 문의내역이 없습니다</div>
							</c:if>
							<c:if test="${sessionScope.member ne null}">
								<div class="col-lg-12" align="right">
									<button class="genric-btn danger-border radius" id="buttonWrite">문의하기</button>
								</div><br><br>
							</c:if>
						</div>
					</div>

					<c:if test="${Paging.totalCount > 10}">
						<nav class="blog-pagination justify-content-center d-flex" style="margin: 0px;">
							<ul class="pagination" id="pagingDiv">
								<!-- 첫 페이지면 Disabled 아니라면 Enabled -->
								<c:choose>
									<c:when test="${Paging.pageNo eq Paging.firstPageNo }">
									</c:when>
									<c:when test="${Paging.pageNo ne Paging.firstPageNo && searchType ne 'no' && searchKeyword ne 'no'}">
										<li class="page-item"><a href="communityInquiry?page=${Paging.prevPageNo}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="page-link" aria-label="Previous"> <i class="ti-angle-left"></i> </a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a href="communityInquiry?page=${Paging.prevPageNo}" class="page-link" aria-label="Previous"> <i class="ti-angle-left"></i> </a></li>
									</c:otherwise>
								</c:choose>
								<!-- 페이지 갯수만큼 버튼 생성 -->
								<c:forEach var="i" begin="${Paging.startPageNo }" end="${Paging.endPageNo }" step="1">
									<c:choose>
										<c:when test="${i eq Paging.pageNo }">
											<li class="page-item active disabled"> <a href="" class="page-link"><c:out value="${i }"/></a> </li>
										</c:when>
										<c:when test="${i ne Paging.pageNo && searchType ne 'no' && searchKeyword ne 'no'}">
											<li class="page-item"> <a href="communityInquiry?page=${i}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="page-link"><c:out value="${i }"/></a> </li>
										</c:when>
										<c:otherwise>
											<li class="page-item"> <a href="communityInquiry?page=${i}" class="page-link"><c:out value="${i }"/></a> </li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<!-- 마지막 페이지면 Disabled 아니라면 Enabled -->
								<c:choose>
									<c:when test="${Paging.pageNo eq Paging.finalPageNo }">
									</c:when>
									<c:when test="${Paging.pageNo ne Paging.finalPageNo && searchType ne 'no' && searchKeyword ne 'no'}">
										<li class="page-item"><a href="communityInquiry?page=${Paging.nextPageNo}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="page-link" aria-label="Next"> <i class="ti-angle-right"></i></a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a href="communityInquiry?page=${Paging.nextPageNo}" class="page-link" aria-label="Next"> <i class="ti-angle-right"></i></a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</nav>
					</c:if>
					
				</div><br>
			</div>
		</div>
	</div>
    
    <%------------ footer section  ------------%>
    <jsp:include page="../fix/footer.jsp" />
    
	<script src="<c:url value='/resources/js/vendor/jquery-1.12.4.min.js'/>"></script>
	<script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>

    <%-- Jquery Plugins, main Jquery --%>
	<script src="<c:url value='/resources/js/plugins.js'/>"></script>
    <script src="<c:url value='/resources/js/main.js'/>"></script>
<script src="<c:url value='/resources/js/community/tag.js'/>"></script>
<script type="text/javascript">



$('#buttonWrite').click(function(){
	var popup = window.open('inquiryWritePage', '게시글작성' , 'width=930px,height=840px,left=300,top=100, scrollbars=yes, resizable=no');
});


</script>
</body>
</html>