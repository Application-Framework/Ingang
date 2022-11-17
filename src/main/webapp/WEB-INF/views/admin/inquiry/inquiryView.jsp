<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='<c:url value="/resources/css/inquiry.css"/>' rel="stylesheet">
<style>#stresstable { background: rgb(245,245,245); }</style>
<title>케어핀투어 관리자</title>
</head>
<body id="page-top">
    <div id="wrapper">
    	<!-- 좌측 배너 부분 -->
		<jsp:include page="../layout/banner.jsp"/>
		<!-- 좌측 배너 부분 -->
		<div id="content-wrapper" class="d-flex flex-column">
			<!-- 상단 헤더 부분 -->
			<jsp:include page="../layout/header.jsp"/>
			<!-- 상단 헤더 부분 -->
			<div class="container-fluid">
				<h1>1:1 문의</h1>
				<hr>
				<div class="card">
					<div class="card-body">
						<h3><i class="fas fa-battery-quarter"></i> 상담내용</h3>
						<table class="table">
							<tbody>
								<tr>
									<td id="stresstable">이름 / ID</td>
									<td>${cbReadPage.m_name } / ${cbReadPage.m_id }</td>
									<td id="stresstable">등록일</td>
									<td><fmt:formatDate value="${cbReadPage.reg_date }" pattern="yyyy-MM-dd a h:mm"/></td>
								</tr>
								<tr>
									<td id="stresstable">카테고리</td>
									<td>${cbReadPage.category }</td>
									<td id="stresstable">답변여부</td>
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
									<td id="stresstable">제목</td>
									<td colspan="3"><c:out escapeXml="false" value="${cbReadPage.title }"/></td>
								</tr>
								<tr>
									<td id="stresstable">내용</td>
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
				<h6 class="border-bottom pb-2 mb-0"></h6>
				<br>
				<div class="card">
					<div class="card-body">
						<div class="d-flex">
							<h3><i class="fas fa-battery-three-quarters"></i> 답변내용</h3>
							<div class="ml-auto">
								<small class="text-muted">답변시각 : <fmt:formatDate value="${cbReadPage.reg_date }" pattern="yyyy-MM-dd a h:mm"/></small>
							</div>
						</div>
						<table class="table">
							<tbody>
								<tr>
									<td id="stresstable">내용</td>
									<td colspan="3">
										<div style="width:500px; height: 300px;">
											<c:out escapeXml="false" value="${fn:replace(cbReadPage.ia_content , crlf, '<br>')}"/>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<br>
				</c:if>
				
				<c:if test="${sessionScope.member ne null && cbReadPage.ia_no eq null }">
					<h6 class="border-bottom pb-2 mb-0"></h6>
					<br>
					<form name="inquiryAnswerWrite" method="POST">
						<div class="bg-white rounded shadow-sm">
							<textarea id="ia_content" name="ia_content" class="form-control" rows="3" placeholder="답글을 입력해 주세요" required></textarea>
							<input type="hidden" id="inq_no" name="inq_no" value="${cbReadPage.inq_no}">
							<button type="button" class="btn btn-primary" id="btnInquiryAnswerWrite" name="btnInquiryAnswerWrite" style="width: 100%;">등 록</button>
						</div>
					</form>
				</c:if>
				<br>
				<div class="d-flex">
					<div class="ml-auto">
					<c:if test="${cbReadPage.ia_no ne null && sessionScope.member ne null}">
						<button class="btn btn-primary" type="button" data-toggle="modal" data-target="#AnswerEditModal">수정</button>
						<button class="btn btn-danger" type="button" id="deleteInquiryAnswer">삭제</button>
					</c:if>
					<c:if test="${cbReadPage.ia_no eq null && sessionScope.member ne null}">
						<button class="btn btn-primary" type="button" id="updateStatementAnswerDelay"> 보류</button>
					</c:if>
						<button class="btn btn-primary" type="button" onclick="location.href='inquiry'">목록</button>
					</div>
				</div>
				<br>
			</div>
		
			<!-- 답변 수정 Modal -->
			<div class="modal fade" id="AnswerEditModal" tabindex="-1" role="dialog" aria-labelledby="AnswerEditModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered" role="document">
					<div class="modal-content">
						<div class="modal-header border-bottom-0">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form-title text-center">
								<h4>답변내용 수정</h4>
							</div>
							<div align="right">작성일. ${cbReadPage.ia_reg_date}</div>
							<div class="d-flex flex-column text-center">
								<form id="inquiryModify" name="inquiryModify"  method="POST">
									<div class="form-group">
										<textarea id="content" name="content" class="form-control" rows="20" style="width: 100%;"><c:out escapeXml="false" value="${fn:replace(cbReadPage.ia_content, '<br>', crlf)}"/></textarea>
									</div>
									<input type="hidden" id="ia_no" name="ia_no" value="${cbReadPage.ia_no}">
									<button type="button" class="btn btn-primary btn-block btn-round" id="btnInquiryAnswerUpdate">수정하기</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>		
		</div>
		<!-- 본문 -->
	</div>
	<jsp:include page="../layout/footer.jsp"/>
<script type="text/javascript">
//답변삭제
<c:if test="${cbReadPage.ia_no ne null}">
$('#deleteInquiryAnswer').click(function() {	
	var inq_no = ${cbReadPage.inq_no};
	//var ia_no = ${cbReadPage.ia_no};
	
	$.ajax({
		url: "deleteInquiryAnswer",
		type: "GET",
		data:  {'inq_no': inq_no, 'ia_no':${cbReadPage.ia_no}},
		success: function(data) {
			if (data != 2) {
				swal({
					title: "답변삭제",
					text: "답변 삭제가 실패하였습니다.",
					icon: "error",
					timer: 3000
				});
			}
			else {
				location.reload();
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
})
</c:if>
//답변보류
$('#updateStatementAnswerDelay').click(function() {
	var inq_no = ${cbReadPage.inq_no};	
	$.ajax({
		url: "updateStatementAnswerDelay",
		type: "GET",
		data:  {'inq_no': inq_no},
		success: function(data) {
			if (data != 1) {
				swal({
					title: "답변보류",
					text: "답변 보류가 실패하였습니다.",
					icon: "error",
					timer: 3000
				});
			}
			else {
				location.reload();
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
})
//답변작성
$('#btnInquiryAnswerWrite').click(function() {
	var inq_no = $("#inq_no").val();
	var ia_content = $("#ia_content").val();
	var param = {'inq_no': inq_no , 'ia_content': ia_content};
	
	if(!ia_content) {
		swal({
			title: "답변작성",
			text: "내용이 입력되지 않았습니다.",
			icon: "warning",
			timer: 3000
		});
		return false;
	}
	else {
		$.ajax({
			url: "writeInquiryAnswer",
			type: "POST",
			data: param,
			success: function(data) {
				if (data != 2) {
					swal({
						title: "답변작성",
						text: "답글 등록이 실패하였습니다.",
						icon: "error",
						timer: 3000
					});
				}
				else {
					location.reload();
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
})

//답변수정
$('#btnInquiryAnswerUpdate').click(function() {
	var ia_no = $("#ia_no").val();
	var content = $("#content").val();
	var param = {'ia_no': ia_no , 'ia_content': content};
	
	if(!content) {
		swal({
			title: "답변수정",
			text: "내용이 입력되지 않았습니다.",
			icon: "warning",
			timer: 3000
		});
		return false;
	}
	else {
		$.ajax({
			url: "updateInquiryAnswer",
			type: "POST",
			data: param,
			success: function(data) {
				if (data != 1) {
					swal({
						title: "답변수정",
						text: "답글 수정이 실패하였습니다.",
						icon: "error",
						timer: 3000
					});
				}
				else {
					location.reload();
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
})
</script>

</body>
</html>