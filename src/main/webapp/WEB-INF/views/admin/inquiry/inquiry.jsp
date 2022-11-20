<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인강인강 관리자</title>
<link href='<c:url value="/resources/css/community/inquiry.css"/>' rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/fontawesome-all.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resources/css/themify-icons.css'/>">
</head>
<body id="page-top">
    <div id="wrapper">
    	<!-- 좌측 배너 부분 -->
		<jsp:include page="../layout/banner.jsp"/>
		<!-- 좌측 배너 부분 -->
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<!-- 상단 헤더 부분 -->
				<jsp:include page="../layout/header.jsp"/>
				<!-- 상단 헤더 부분 -->
				
				<!-- 본문 -->
				<div class="container-fluid">
					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Inquiry Management</h1>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-8">
							<form action="inquiry" role="form" method="GET" class="form-inline">
								<select class="form-control" id="searchType" name="searchType">
									<option value="m_id"  <c:if test="${searchType eq 'm_id'}">selected="selected"</c:if>>작성자(ID)</option>
									<option value="category" <c:if test="${searchType eq 'category'}">selected="selected"</c:if>>카테고리</option>
									<option value="title" <c:if test="${searchType eq 'title'}">selected="selected"</c:if>>제목</option>
								</select>
								<input type="text"  id="searchKeyword" name="searchKeyword" <c:if test="${searchKeyword ne 'no'}"> value="${searchKeyword}"</c:if> class="form-control ml-1 mr-1" placeholder="검색어를 입력해주세요" required>
								<button type="submit" class="btn px-3 btn-primary">
									<i class="fas fa-search"></i>
								</button>
							</form>
						</div>	
						<div class="col-sm-4">
							<div class="d-flex">
								<div class="ml-auto">
									<button type="button"  class="btn btn-danger" onclick="deleteValue()">삭제</button>
								</div>
							</div>
						</div>
					</div>
					<br>
					<table class="table table-hover table-white">
						<colgroup>
							<col width="1%">
							<col width="1%">
							<col width="10%">
							<col width="50%">
						</colgroup>
						<thead>
							<tr>
								<th><input type="checkbox" name="allCheck"></th>
								<th>No</th>
								<th>카테고리</th>
								<th>제목</th>
								<th>작성자</th>
								<th>아이디</th>
								<th>작성일</th>
								<th>답변상태</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${cbInquiry}" var="cbInquiry">
								<tr>
									<th><input type="checkbox"name="RowCheck" value="${cbInquiry.inq_no }"></th>
									<td>${cbInquiry.inq_no }</td>
									<td><span class="inquiry-category">${cbInquiry.category }</span></td>
									<td><a href="inquiryView?inq_no=${cbInquiry.inq_no }">${cbInquiry.title }</a></td>
									<td>${cbInquiry.m_name}</td>
									<td>${cbInquiry.m_id}</td>
									<td><fmt:formatDate value="${cbInquiry.reg_date }" pattern="yyyy-MM-dd a h:mm"/></td>
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
							<c:if test="${Paging.totalCount < 1}">
								<tr align="center" >
									<td rowspan="10" colspan="10"><div align="center" class="col-sm-12" style="margin: 20% 0;">게시글이 없습니다.</div></td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<!-- 게시글 페이징 처리(기준 10개) -->
					<c:if test="${ Paging.totalCount > 10}">
						<!-- 게시글 페이징 처리(기준 10개) -->
						<nav aria-label="Page navigation">
							<ul class="pagination justify-content-center">
								<c:choose>
									<c:when test="${Paging.pageNo eq Paging.firstPageNo }">
									</c:when>
									<c:when test="${Paging.pageNo ne Paging.firstPageNo && searchType ne 'no' && searchKeyword ne 'no'}">
										<li class="page-item"><a href="inquiry?page=${Paging.prevPageNo}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="page-link" aria-label="Previous"> <i class="ti-angle-left"></i> </a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a href="inquiry?page=${Paging.prevPageNo}" class="page-link" aria-label="Previous"> <i class="ti-angle-left"></i> </a></li>
									</c:otherwise>
								</c:choose>
								<!-- 페이지 갯수만큼 버튼 생성 -->
								<c:forEach var="i" begin="${Paging.startPageNo }" end="${Paging.endPageNo }" step="1">
									<c:choose>
										<c:when test="${i eq Paging.pageNo }">
											<li class="page-item active disabled"> <a href="" class="page-link"><c:out value="${i }"/></a> </li>
										</c:when>
										<c:when test="${i ne Paging.pageNo && searchType ne 'no' && searchKeyword ne 'no'}">
											<li class="page-item"> <a href="inquiry?page=${i}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="page-link"><c:out value="${i }"/></a> </li>
										</c:when>
										<c:otherwise>
											<li class="page-item"> <a href="inquiry?page=${i}" class="page-link"><c:out value="${i }"/></a> </li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<!-- 마지막 페이지면 Disabled 아니라면 Enabled -->
								<c:choose>
									<c:when test="${Paging.pageNo eq Paging.finalPageNo }">
									</c:when>
									<c:when test="${Paging.pageNo ne Paging.finalPageNo && searchType ne 'no' && searchKeyword ne 'no'}">
										<li class="page-item"><a href="inquiry?page=${Paging.nextPageNo}&searchType=${searchType}&searchKeyword=${searchKeyword}" class="page-link" aria-label="Next"> <i class="ti-angle-right"></i></a></li>
									</c:when>
									<c:otherwise>
										<li class="page-item"><a href="inquiry?page=${Paging.nextPageNo}" class="page-link" aria-label="Next"> <i class="ti-angle-right"></i></a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</nav>
					</c:if>
				</div>
				<!-- 본문 -->
			</div>
		<!-- 하단 푸터 부분 -->
		<jsp:include page="../layout/footer.jsp"/>
		<!-- 하단 푸터 부분 -->
		</div>
	</div>
<script type="text/javascript">
//체크박스 선택 관련
$(function(){
	var chkObj = document.getElementsByName("RowCheck");
	var rowCnt = chkObj.length;
	
	$("input[name='allCheck']").click(function(){
		var chk_listArr = $("input[name='RowCheck']");
		for (var i=0; i<chk_listArr.length; i++){
			chk_listArr[i].checked = this.checked;
		}
	});
	$("input[name='RowCheck']").click(function(){
		if($("input[name='RowCheck']:checked").length == rowCnt){
			$("input[name='allCheck']")[0].checked = true;
		}
		else{
			$("input[name='allCheck']")[0].checked = false;
		}
	});
});
//삭제버튼 눌렀을 때 실행 
function deleteValue(){
	var url = "/admin/inquirySelectDelete";    // Controller로 보내고자 하는 URL
	var valueArr = new Array();
    var list = $("input[name='RowCheck']");
    for(var i = 0; i < list.length; i++){
        if(list[i].checked){ //선택되어 있으면 배열에 값을 저장함
            valueArr.push(list[i].value);
        }
    }
    if (valueArr.length == 0){
		Swal.fire({
  			title: '선택된 문의내역이 없습니다.',
	  		text: "삭제하실 회원을 선택해주세요.",
	  		icon: 'warning',
	  		confirmButtonColor: '#3085d6',
	  		confirmButtonText: '확인',
	  	})
    }else{
    	Swal.fire({
  		  	title: '문의내역을 삭제하시겠습니까?',
		  		text: "삭제하시면 다시 복구시킬 수 없습니다.",
		    	icon: 'warning',
		   		showCancelButton: true,
		   		confirmButtonColor: '#3085d6',
		   		cancelButtonColor: '#d33',
		  	 	confirmButtonText: '삭제',
		  	 	cancelButtonText: '취소'
  		}).then((result) => {
  		  if (result.value) {
	  			$.ajax({
				    url : url,                    // 전송 URL
				    type : 'GET',                // GET or POST 방식
				    traditional : true,
				    data : {
				    	valueArr : valueArr        // 보내고자 하는 data 변수 설정
				    },
	                success: function(jdata){
	                    if(jdata != 1) {
	                    	alert("삭제 실패(문의전화 : 010-9748-5575 or 010-3266-5702)");
	                    } else{
	                    	location.href = "inquiry";
	                    	//location.reload(); // 메인 -> 회원페이지 -> 삭제 -> 뒤로가기해도 메인으로 감
	                    }
	                }
				});
  		  }
  		})
	}
}
</script>
</body>
</html>