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
	
	<style type="text/css">
	[placeholder]:empty:before {
  display: block;
  content: attr(placeholder);
  color: #a6a6a6;
  
  
}
</style>
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
	    		<font color="#FFFFFF">10만의 커뮤니티!! 함께 토론해봐요</font> 
	    	</div>
    	</div>
    </div><br>
   
    <!--================Blog Area =================-->
    <div class="container">
    	<div class="row">
			<jsp:include page="communityLeftSidebar.jsp" />
			
			<div class="col-lg-7 mb-5 mb-lg-0" style="padding:0 0px;">
			<br>
				<div class="blog_left_sidebar">
					<article class="blog_item">
						<form id="searchForm" action="communityChats">
							<div class="row">
								<div class="col-lg-10" >
									<input type="text" class="form-control" id="searchKeyword"  name="searchKeyword" placeholder='내용을 검색해보세요!' <c:if test="${searchKeyword ne null}"> value = "${searchKeyword}" </c:if>>
									<input type="text" class="form-control" id="tag" name="tag" placeholder='태그로 검색해보세요!' style="margin-top: 10px;">
									<ul id="tag-list">
									</ul>
									<div id="sdata"></div>
								</div>
								<div class="col-lg-2" style="padding:0 15px;">
									<input type="button" class="genric-btn danger-border radius"  id="btnBoardSearch" value="검색" style="width: 100%;" >
								</div>
							</div>
						</form>
					</article>
					
					<div class="container" id="boardDiv">
						<div class="row">
							<ul class="nav nav-tabs" id="myTab" style="width: 100%;">
								<li class="nav-item" id="myTabActive1" style="width: 15%;"><a class="nav-link active" data-toggle="tab" href="#qwe"><h6 style="color: #5D5D5D;" align="center"> 최신순</h6></a></li>
								<li class="nav-item" id="myTabActive2" style="width: 15%;"><a class="nav-link" data-toggle="tab" href="#asd" ><h6 style="color: #5D5D5D;" align="center">좋아요순</h6></a></li>
								<li class="nav-item" id="myTabActive3" style="width: 58%; " ><a class="nav-link" data-toggle="tab" href="#asdff" style="display: none;" ></a></li>
									<c:choose>
										<c:when test="${sessionScope.member.getM_id() ne null }"> 
										<li class="nav-item" id="myTabActive6" style="width: 12%;">
											<button type="button" id="buttonWrite" class="genric-btn danger radius" style="padding:0px 20px; width: 100%;">
												<font size="1px;">글작성</font>
											</button>
											</li>
										</c:when>
										<c:otherwise> 
										<li class="nav-item" id="myTabActive6" style="width: 12%;">
											<button type="button" id="buttonNoLogin" class="genric-btn danger radius" style="padding:0px 20px; width: 100%;">
												<font size="1px;">글작성</font>
											</button>
											</li>
										 </c:otherwise>
									</c:choose>
							</ul>
							<c:if test="${Paging.totalCount > 0}">
								<div class="tab-content" id="contentDiv">
									<div class="tab-pane fade show active" id="qwe">
										<c:forEach var="cbList" items="${cbRegDateList}">
											<article class="blog_item">
												<div class="blog_details" style="padding: 10px 10px 10px 10px;">
												
													<a class="d-inline-block" href="communityBoardRead?cb_no=${cbList.cb_no}&classify=1&isOnlineLecture=${cbList.oli_no}">
														<font size="1px;">NO. <c:url value="${cbList.cb_no}"/></font>
														<h2><c:url value="${fn:substring(cbList.title, 0, 35)}"/></h2>
													</a>
													<p style="margin: 0 0px;"><c:url value="${fn:substring(cbList.content,0,200)}"/></p>
													<ul id="tag-list" style=""> 
														<c:forEach var="cbTag" items="${cbTag.getTagCommunityBoard(cbList.cb_no)}">
															<li class="tag-item">#${cbTag.ctl_name}</li>
														</c:forEach>
													</ul>
													<ul class="blog-info-link">
														<li><a href="#"><i class="fa fa-user"></i> <c:url value="${cbList.m_id}"/></a> </li>
														<li><a href="#"><i class="fa fa-comments"></i> <c:url value="${cbList.reply}"/> </a></li>
														<li><a href="#"><i class="fa fa-heart"></i> <c:url value="${cbList.good}"/></a></li>
														<li><i class="fa fa-clock-o"> </i><font size="2" color="#848484">
														<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${cbList.reg_date}" /></font></li>
													</ul>
												</div>
											</article>
										</c:forEach>
									</div>
									<div class="tab-pane fade" id="asd">
										<div class="tab-pane fade show active" id="qwe">
											<c:forEach var="cbGoodShowList" items="${cbGoodShowList}">
												<article class="blog_item">
													<div class="blog_details" style="padding: 10px 10px 10px 10px;">
														<a class="d-inline-block" href="communityBoardRead?cb_no=${cbGoodShowList.cb_no}&classify=1&isOnlineLecture=${cbGoodShowList.oli_no}">
															<font size="1px;">NO. <c:url value="${cbGoodShowList.cb_no}"/></font>
															<h2><c:url value="${fn:substring(cbGoodShowList.title, 0, 30)}"/></h2>
														</a>
														<p style="margin: 0 0px;"><c:url value="${fn:substring(cbGoodShowList.content,0,200)}"/></p>
														<ul id="tag-list" style=""> 
															<c:forEach var="cbTag" items="${cbTag.getTagCommunityBoard(cbGoodShowList.cb_no)}">
																<li class="tag-item">#${cbTag.ctl_name}</li>
															</c:forEach>
														</ul>
														<ul class="blog-info-link">
															<li><a href="#"><i class="fa fa-user"></i> <c:url value="${cbGoodShowList.m_id}"/></a></li>
															<li><a href="#"><i class="fa fa-comments"></i> <c:url value="${cbGoodShowList.reply}"/> </a></li>
															<li><a href="#"><i class="fa fa-heart"></i> <c:url value="${cbGoodShowList.good}"/></a></li>
															<li><i class="fa fa-clock-o"> </i><font size="2" color="#848484">
															<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${cbGoodShowList.reg_date}" /></font></li>
														</ul>
													</div>
												</article>
											</c:forEach>
										</div>
									
									</div>
	
								</div>
							</c:if>
							<c:if test="${Paging.totalCount < 1 }">
								<div align="center" class="col-lg-12" style="margin: 40% 0;">작성된 게시글이 없습니다.</div>
							</c:if>
							
						</div>
						<c:if test="${Paging.totalCount > 10}">
							<nav class="blog-pagination justify-content-center d-flex" style="margin: 0px;">
								<ul class="pagination" id="pagingDiv">
									<!-- 첫 페이지면 Disabled 아니라면 Enabled -->
									<c:choose>
										<c:when test="${Paging.pageNo eq Paging.firstPageNo }">
										</c:when>
										<c:otherwise>
											<li class="page-item"><a href="communityChats?page=${Paging.prevPageNo}" class="page-link" aria-label="Previous"> <i class="ti-angle-left"></i> </a></li>
										</c:otherwise>
									</c:choose>
									<!-- 페이지 갯수만큼 버튼 생성 -->
									<c:forEach var="i" begin="${Paging.startPageNo }" end="${Paging.endPageNo }" step="1">
										<c:choose>
											<c:when test="${i eq Paging.pageNo }">
												<li class="page-item  active disabled"> <a href="communityChats?page=${i}" class="page-link"><c:out value="${i }"/></a> </li>
											</c:when>
											<c:otherwise>
												<li class="page-item"> <a href="communityChats?page=${i}" class="page-link"><c:out value="${i }"/></a> </li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<!-- 마지막 페이지면 Disabled 아니라면 Enabled -->
									<c:choose>
										<c:when test="${Paging.pageNo eq Paging.finalPageNo }">
										</c:when>
										<c:otherwise>
											<li class="page-item"><a href="communityChats?page=${Paging.nextPageNo}" class="page-link" aria-label="Next"> <i class="ti-angle-right"></i></a></li>
										</c:otherwise>
									</c:choose>
								</ul>
							</nav>
						</c:if>
					</div>
				</div><br>
			</div>
			<jsp:include page="communityTagSidebar.jsp" />
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
var counter = 0;
<c:forEach var="searchTagList" items="${searchTagList}">
	$("#tag-list").append("<li class='tag-item'>" + "${searchTagList}" + "<span class='del-btn' idx='" + counter + "'>x</span></li>&nbsp;");
	var test = $("<input type='hidden' value="+ "${searchTagList}" +" class='test_obj' name='searchTag' id='" + counter + "'>");
	$("#sdata").append(test);
	addTag("${searchTagList}");
	tagArray.push("${searchTag}");
</c:forEach>


$(document).ready(function() {
	console.log("tab1: " + sessionStorage.getItem("myTabActive1"));
	console.log("tab2: " +sessionStorage.getItem("myTabActive2"));
	
	if(sessionStorage.getItem("myTabActive") == "a1"){
		$("#myTab a[href='#qwe']").tab("show"); 
	} if(sessionStorage.getItem("myTabActive") == "a2"){
		$("#myTab a[href='#asd']").tab("show"); 
	}
	$('#myTabActive1').click(function(){
		sessionStorage.setItem("myTabActive", "a1"); 
	});
	$('#myTabActive2').click(function(){
		sessionStorage.setItem("myTabActive", "a2"); 
	});
});

var popup;
$('#buttonWrite').click(function(){
	popup = window.open('communityBoardWrite', '게시글작성' , 'width=930px,height=840px,left=300,top=100, scrollbars=yes, resizable=no');
});


$('#buttonNoLogin').click(function(){
	console.log("asd");
	swal({
		title: "로그인",
		text: "로그인이 되어야 게시글 작성이 가능합니다.",
		icon: "warning",
	});
});

$('#btnBoardSearch').click(function(){
	
	var formData = $("#searchForm").serialize();
	$("#searchForm").submit();
	
})

function reloadPage() {
	setTimeout(function() {
		//opener.location.replace(reLoadUrl);
		//window.close();
		if(popup!= null) popup.close();
		location.reload();
	}, 500);
}

</script>
</body>
</html>