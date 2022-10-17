<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>케어핀투어 관리자</title>
</head>
<body id="page-top">
    <div id="wrapper">
		<!-- 좌측 배너 부분 -->
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<!-- 상단 헤더 부분 -->
				<jsp:include page="../layout/header.jsp"/>
				<script src='<c:url value="/resources/js/Board.js"/>'></script>
				<!-- 상단 헤더 부분 -->
				
				<!-- 본문 -->
				<div class="container-fluid" >
					<div class="form-title text-center">
						<h4>회원 상세정보</h4>
						<hr>
					</div>
					<div class="d-flex flex-column">
						<form role="form" name="memberUpdate" id="memberUpdate" action="memberUpdate" method="POST">
							<div class="row">
								<div class="col-xs-4 col-md-4">
									<div class="form-group row">
										<div class="col-xs-12 col-md-12">
											<img alt="" width="70%;" src="<c:out value='/resources/img/logo/logo5.png'></c:out>">
										</div>
									</div>
								</div>
								
								<div class="col-xs-8 col-md-8">
									<!-- 아이디 & 비밀번호 -->
									<div class="form-group row">
										<div class="col-xs-6 col-md-6">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">No</span>
												</div>
												<input type="text" name="userID" id="userID" class="form-control" value="${mDetail.getUserID()}" readonly>
											</div>
										</div>
										<div class="col-xs-6 col-md-6">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">이름</span>
												</div>
												<input type="text" name="name" id="name" class="form-control" value="${mDetail.getName()}" readonly>
											</div>
										</div>
									</div>
									<!-- 이름 & 마일리지 -->
									<div class="form-group row">
										<div class="col-xs-6 col-md-6">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">아이디</span>
												</div>
												<input type="text" name="userID" id="userID" class="form-control" value="${mDetail.getUserID()}" readonly>
											</div>
										</div>
										<div class="col-xs-6 col-md-6">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">비밀번호</span>
												</div>
												<input type="password" name="password" id="password" class="form-control" value="${mDetail.getPassword()}">
											</div>
										</div>
									</div>
									
									<div class="form-group row">
										<div class="col-xs-6 col-md-6">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">권한</span>
												</div>
												<select id="grade" name="grade" class="form-control">
													<option value="User">일반</option>
													<option value="VIP">VIP</option>
													<option value="BlackConsumer">블랙리스트</option>
													<option value="Admin">관리자</option>
												</select>
											</div>
										</div>
										<div class="col-xs-6 col-md-6">
											<div class="input-group my-2 mb-1">
												<div class="input-group-prepend">
													<span class="input-group-text">가입일</span>
												</div>
												<input type="text" name="name" id="name" class="form-control" value="${mDetail.getName()}" readonly>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- 특이사항 -->
							<div class="form-group">
								<div class="input-group my-2 mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text">특이사항</span>
									</div>
									<textarea rows="5" cols="25" name="comment" id="comment" class="form-control" >${mDetail.getComment()}</textarea>
								</div>
							</div>
							<!-- 회원 주문내역 Table -->
							<hr>
							<h4>회원 주문내역</h4>
							<table class="table table-hover table-white">
								<thead>
									<tr>
										<th>주문번호</th>
										<th>종류</th>
										<th>상품명</th>
										<th>결제금액</th>
										<th>결제일자</th>
										<th>결제상태</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${morder}" var="OrderVO">
									<tr>
										<td>${OrderVO.oId }</td>
										<td>${OrderVO.pId }</td>
										<td><a href="/ex/detailInfo?PID=${OrderVO.pId }" target="_blank">${OrderVO.productname }</a></td>
										<td>${OrderVO.payment }</td>
										<td>${OrderVO.accumlatemileage }</td>
										<td>${OrderVO.paymentdate }</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							<hr>
							<!-- 회원 문의내역 Table -->
							<h4>회원 문의내역</h4>
							<table class="table table-hover table-white">
								<thead>
									<tr>
										<th>No</th>
										<th>제목</th>
										<th>작성일</th>
										<th>답변상태</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${minquiry}" var="InquiryVO">
									<tr>
										<td>${InquiryVO.iId }</td>
										<td><a href="inquiryView?iId=${InquiryVO.iId }" target="_blank">${InquiryVO.title }</a></td>
										<td>${InquiryVO.reDate }</td>
										<td>${InquiryVO.status }</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							<button type="submit" class="btn btn-primary btn-block btn-round">수정</button>
						</form>
					</div>
				</div>
			</div>
			<!-- 본문 -->
		</div>
	<!-- 하단 푸터 부분 -->
	</div>
	<jsp:include page="../layout/footer.jsp"/>
  		<!-- 하단 푸터 부분 -->
	<script type="text/javascript">
	$(document).ready(function(){
		$("#grade").val("${mDetail.getGrade()}").prop("selected", true);
		$("#sex").val("${mDetail.getSex()}").prop("selected", true);
	})
	</script>
</body>
</html>