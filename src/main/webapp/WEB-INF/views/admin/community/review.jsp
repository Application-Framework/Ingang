<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 수강후기</title>
</head>
<style>
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
<body id="page-top">
	<div id="wrapper">
		<!-- 좌측 배너 부분 -->
		<jsp:include page="../layout/banner.jsp" />
		<!-- 좌측 배너 부분 -->
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<!-- 상단 헤더 부분 -->
				<jsp:include page="../layout/header.jsp" />
				<!-- 상단 헤더 부분 -->

				<!-- 본문 -->
				<div class="container-fluid">
					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">수강후기 관리</h1>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-8">
							<form role="form" class="form-inline">
								<select class="form-control" onchange="refreshPage()" id="order" name="order">
					                  <option <c:if test="${order == 'new'}">selected</c:if> value="new">최신순</option>
					                  <option <c:if test="${order == 'rating'}">selected</c:if> value="rating">평점순</option>
								</select>
								<div class="col-sm-4">
									<input type="text" class="form-control" name="s" id="s" value="${s}">
									<button class="btn px-3 btn-primary" id="search" type="submit">
										<i class="fas fa-search"></i>
									</button>
								</div>
							</form>
						</div>
						
						<div class="col-sm-4">
							<div class="d-flex">
								<div class="ml-auto">
									<input type="button" class="btn btn-primary" value="선택삭제" onclick="deleteValue();">
								</div>
							</div>
						</div>
                    </div>
                    <br>
					<table class="table table-hover table-white">
						<thead>
							<tr>
								<th>
									<input id="allCheck" type="checkbox" name="allCheck">
								</th>
								<th><font size="3">No</font></th>
								<th><font size="3">제목</font></th>
								<th><font size="3">작성자(ID)</font></th>
								<th><font size="3">평점</font></th>
								<th><font size="3">댓글</font></th>
								<th><font size="3">작성일</font></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${olrList}" var="olrList">
								<tr>
									<td>
										<input name="RowCheck" type="checkbox" value="${olrList.olr_no}">			
									</td>
									<td><font size="3">${olrList.olr_no}</font></td>
									<td><font size="3"><a class="d-inline-block" href="/course/${olrList.oli_no}">${olrList.title}</a></font></td>
									<td><font size="3">${olrList.m_name}(${olrList.m_id})</font></td>
									<td><div class="stars-outer">
											<div class="stars-inner" style="width:${courseService.getCourseStarAvg(olrList.oli_no)*20}%"></div>
										</div>
									</td>
									<td><font size="3">${olrList.content}</font></td>
									<td><font size="3"><fmt:formatDate value="${olrList.reg_date}" pattern="yy-MM-dd" /></font></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<%-- 페이지 번호 --%>
                    <div id="pagination" class="pagination-area pb-115 text-center mt-5">
                    	<div class="container">
                        	<div class="row">
                            	<div class="col-xl-12">
                                	<div class="single-wrap d-flex justify-content-center">
                                    	<nav aria-label="Page navigation example">
	                                        <ul class="pagination justify-content-start">
	                                        	<c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
	                                            	<c:choose>
	                                                	<c:when test="${i == paging.pageNo}">
	                                                    	<li class="page-item active"><a class="page-link" style="pointer-events: none;">${i}</a></li>
	                                                    </c:when>
	                                             		<c:otherwise>
	                                                    	<li class="page-item"><a class="page-link" href="${nowURL}?${keywordParam}${orderParam}page=${i}">${i}</a></li>
	                                                    </c:otherwise>
	                                                </c:choose>
	                                            </c:forEach>
	                                        </ul>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%-- 페이지 번호 끝 --%>
				</div>
			</div>


			<!-- 본문 -->
			<!-- 하단 푸터 부분 -->
			<jsp:include page="../layout/footer.jsp" />
			<!-- 하단 푸터 부분 -->
		</div>
	</div>
</body>

<!-- 최신순, 평점순 정렬 -->
<script>
var url = new URL(location.origin + location.pathname);
var search = "";
$(function() {
	// select option drop box 옵션 설정
	var multipleCancelButton = new Choices('#tags', {
        removeItemButton: true,
        maxItemCount:5,
        searchResultLimit:10,
        //renderChoiceLimit:10
    }); 
	
	$('#search').on("click", function() {
		search = $("#s").val();
		refreshPage();
	});
});

function refreshPage() {
	url = new URL(location.origin + location.pathname);
	var s = search;
	var order = $("#order").val();
	
	if(s != "")
		url.searchParams.set('s', s);
	if(order.length != 0)
		url.searchParams.set('order', order);
	url.searchParams.set('page', ${paging.pageNo});
	$.ajax({
		url: url.toString(),
		type: 'get',
		dataType: 'html',
		success: function(html) {
			
			var content = $(html).find("#content>*");
			var pagination = $(html).find("#pagination>*");
			console.log(content);
			console.log(pagination);
			
			$("#content").html(content);
			$("#pagination").html(pagination);
			history.pushState(null, null, url);
		}
	});
}
</script>

<!-- 삭제 -->
<script>
//삭제버튼 눌렀을 때 실행 
function deleteValue(){
	var url = "/admin/reviewSelectDelete";    // Controller로 보내고자 하는 URL
	var valueArr = new Array();
    var list = $("input[name='RowCheck']");
    for(var i = 0; i < list.length; i++){
        if(list[i].checked){ //선택되어 있으면 배열에 값을 저장함
            valueArr.push(list[i].value);
        }
    }
    if (valueArr.length == 0){
		Swal.fire({
  			title: '선택된 수강후기가 없습니다.',
	  		text: "삭제하실 수항후기를 선택해주세요.",
	  		icon: 'warning',
	  		confirmButtonColor: '#3085d6',
	  		confirmButtonText: '확인',
	  	})
    }else{
    	Swal.fire({
  		  	title: '수강후기를 삭제하시겠습니까?',
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
	                    	location.reload(); // 메인 -> 회원페이지 -> 삭제 -> 뒤로가기해도 메인으로 감
	                    }
	                }
				});
  		  }
  		})
	}
}
</script>
</html>