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
		<!-- Hero Area End -->
		<!--================Blog Area =================-->
	<section >
		
		<div class="container">
			<h1>1:1 문의</h1>
			<hr>
			<div class="card">
				<div class="card-body">
					<h3><i class="fas fa-battery-quarter"></i> 상담내용</h3>
					<table class="table">
						<tbody>
							<tr>
								<td id="stresstable" style="background-color: #EAEAEA;" align="center">이름</td>
								<td>${cbReadPage.m_id }</td>
								<td id="stresstable" style="background-color: #EAEAEA;" align="center">등록일</td>
								<td><fmt:formatDate value="${cbReadPage.reg_date }" pattern="yyyy-MM-dd a h:mm"/></td>
							</tr>
							<tr>
								<td id="stresstable" style="background-color: #EAEAEA;" align="center">카테고리</td>
								<td>${cbReadPage.category}</td>
								<td id="stresstable" style="background-color: #EAEAEA;" align="center">답변여부</td>
						<c:choose>
							<c:when test="${cbReadPage.statement eq 0}">
								<td><span class="inquiry-status status-0">답변대기</span></td>
							</c:when>
							<c:when test="${cbReadPage.statement eq 1}">
								<td><span class="inquiry-status status-1">답변보류</span></td>
							</c:when>
							<c:otherwise>
								<td><span class="inquiry-status status-2">답변완료</span></td>
							</c:otherwise>
						</c:choose>
							</tr>
							<tr>
								<td id="stresstable" style="background-color: #EAEAEA;" align="center">제목</td>
								<td colspan="3"><c:out escapeXml="false" value="${cbReadPage.title }"/></td>
							</tr>
							<tr>
								<td id="stresstable" style="background-color: #EAEAEA;" align="center">내용</td>
								<td colspan="3">
									<div style="width:500px; height: 300px;">
										<c:out escapeXml="false" value="${fn:replace(cbReadPage.content, crlf, '<br>')}"/>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<br>
			<c:if test="${cbReadPage.ia_no ne null}">
				<h6 class="border-bottom pb-2 mb-0"></h6><br>
				<div class="card">
					<div class="card-body">
						<div class="d-flex">
							<h3><i class="fas fa-battery-three-quarters"></i> 답변내용</h3>
							<div class="ml-auto">
								<small class="text-muted">답변시각 : <fmt:formatDate value="${cbReadPage.ia_reg_date }" pattern="yyyy-MM-dd a h:mm"/></small>
							</div>
						</div>
						<table class="table">
							<tbody>
								<tr>
									<td id="stresstable">내용</td>
									<td colspan="3">
										<div style="width:500px; height: 300px;">
											<c:out escapeXml="false" value="${fn:replace(cbReadPage.ia_content, crlf, '<br>')}"/>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div><br>
			</c:if>
			
			<div class="d-flex">
				<div class="ml-auto">
				<c:if test="${cbReadPage.ia_no  ne null && sessionScope.member ne null && sessionScope.member.getM_authority() eq 1}">
					<button class="thm-btn-psd" type="button" data-toggle="modal" data-target="#AnswerEditModal">수정</button>
					<button class="thm-btn-psd" type="button" onclick="boardDelete(${cbReadPage.inq_no})">삭제</button>
				</c:if>
				<c:if test="${cbReadPage.ia_no  ne null}">
					<button class="thm-btn-psd" type="button" onclick="location.href='inquiry'">목록</button>
				</c:if>
				</div>
			</div>
			
			<br>
			<div class="d-flex">
				<div class="ml-auto">
				<c:if test="${cbReadPage.ia_no eq null && cbReadPage.m_no eq sessionScope.member.m_no}">
					<button class="genric-btn danger-border radius" type="button"  onclick="(${cbReadPage.inq_no})">수정</button>
					<button class="genric-btn danger-border radius" type="button"  onclick="boardDelete(${cbReadPage.inq_no})">삭제</button>
				</c:if>
					<button class="genric-btn danger-border radius" type="button" onclick="javascript:history.back();">목록</button>
				</div>
			</div>
			<br>
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
<script type="text/javascript">
//게시글 삭제
function boardDelete(inq_no) {
	$.ajax({
		url: "deleteInquiry",
		type: "GET",
		data:  {'inq_no': inq_no},
		success: function(data) {
			if (data != 1) {
				swal({
					title: "1:1문의 삭제",
					text: "1:1문의 삭제가 실패하였습니다.",
					icon: "error",
					timer: 3000
				});
			}
			else {
				var reLoadUrl = "/communityInquiry";
				location.href = reLoadUrl;
			}
		},
		error: function() {
			swal({
				title: "인강인강",
				text: "문제가 발생하였습니다.\n잠시 후 다시 시도해주세요.",
				icon: "error",
				timer: 3000
			});
		}
	});
}
</script>

</body>
</html>