<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 질문&답변</title>
</head>
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
						<h1 class="h3 mb-0 text-gray-800">질문 & 답변 게시판 관리</h1>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-8">
							<form role="form" class="form-inline">
								<select class="form-control" onchange="refreshPage()" id="order" name="order">
					                  <option <c:if test="${order == 'new'}">selected</c:if> value="new">최신순</option>
					                  <option <c:if test="${order == 'like'}">selected</c:if> value="like">좋아요순</option>
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
									<button class="btn btn-primary" data-toggle="modal" data-target="#insertAdminBoard">게시글 등록</button>
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
								<th><font size="3">조회수</font></th>
								<th><font size="3">추천수</font></th>
								<th><font size="3">작성일</font></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${cbList}" var="cbList">
								<tr>
									<td>
										<input name="RowCheck" type="checkbox" value="${cbList.cb_no}">			
									</td>
									<td><font size="3">${cbList.cb_no}</font></td>
									<td><font size="3">
									<a href="javascript:void(window.open('/admin/freeBoardDetail?cb_no=${cbList.cb_no}', '상세페이지' , 'width=1280px,height=840px,left=300,top=100, scrollbars=yes, resizable=no'));"><c:out value="${cbList.title}"></c:out></a></font></td>
									<td><font size="3">${cbList.m_name}(${cbList.m_id})</font></td>
									<td><font size="3">${cbList.hit}</font></td>
									<td><font size="3"><c:out value="${service.getFreeBoardLikeCount(cbList.cb_no)}"></c:out></font></td>
									<td><font size="3"><fmt:formatDate value="${cbList.reg_date}" pattern="yy-MM-dd" /></font></td>
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


<!-- 게시글 등록 -->
	<div class="modal fade" id="insertAdminBoard" tabindex="-1" role="dialog" aria-labelledby="memberModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header border-bottom-0">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-title text-center">
						<h4>게시글 등록</h4>
						<hr>
					</div>
					<div class="d-flex flex-column">
						<form id="myform" role="form" method="POST"  enctype="multipart/form-data">
							<!-- 카테고리 & show or hide -->
							<div class="form-group row">
								<div class="col-xs-12 col-md-12">
									<div class="input-group my-2 mb-1">
										<div class="input-group-prepend" style="margin-right: 10px;">
											<span class="input-group-text">카테고리</span>
										</div>
										<span class="input-group-btn">
											<select class="form-control" onchange="refreshPage()" id="classify" name="classify">
								                  <option <c:if test="${classify == 'free'}">selected</c:if> value="1">자유주제</option>
								                  <option <c:if test="${classify == 'questions'}">selected</c:if> value="2">질문</option>
								                  <option <c:if test="${classify == 'study'}">selected</c:if> value="4">스터디</option>
											</select>
										</span>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<div class="col-xs-12 col-md-12">
									<div class="input-group my-2 mb-1">
										<div class="input-group-prepend" style="margin-right: 10px;">
											<span class="input-group-text">공개여부</span>
										</div>
										<span class="input-group-btn">
											<select class="form-control" onchange="refreshPage()" id="flag" name="flag">
								                  <option <c:if test="${flag == 'show'}">selected</c:if> value="1">공개</option>
								                  <option <c:if test="${flag == 'hide'}">selected</c:if> value="0">비공개</option>
											</select>
										</span>
									</div>
								</div>
							</div>
							
							<!-- 제목 -->
							<div class="form-group row">
								<div class="col-xs-6 col-md-12">
									<div class="input-group my-2 mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text">제목</span>
										</div>
										<input type="text" name="title" id="title" class="form-control" required>
									</div>
								</div>
							</div>
							<!-- 내용 -->
							<div class="form-group">
								<div class="input-group my-2 mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text">내용</span>
									</div>
									<textarea rows="5" cols="25" id="content" class="form-control" name="content"></textarea>
								</div>
							</div>
							
							<button type="button" class="btn btn-primary btn-block btn-round" id="btnInsertAdminBoard">게시글 등록</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
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

<script>
//게시글 작성 실행 시
$('#btnInsertAdminBoard').click(function() {
	var title = $("#title").val();
	var content = $("#content").val();
	var classify = $("classify").val();
	var flag = $("flag").val();
	
	if(!title) {
		swal({
			title: "제목등록",
			text: "제목 입력되지 않았습니다.",
			icon: "warning",
			timer: 3000
		});
		return false;
	}
	else {
		var form = $("#myform")[0];
		var formData = new FormData(form);
		
		$.ajax({
			cache : false,
			url : "insertAdminBoard", 
			processData: false,
			contentType: false,
			type : 'POST', 
			data : formData, 
			success : function(data = 1) {
				location.reload();
				
			},
			error : function(xhr, status) {
				alert(xhr + " : " + status);
			}
		});
	}
})

//삭제버튼 눌렀을 때 실행 
function deleteValue(){
	var url = "/admin/boardSelectDelete";    // Controller로 보내고자 하는 URL
	var valueArr = new Array();
    var list = $("input[name='RowCheck']");
    for(var i = 0; i < list.length; i++){
        if(list[i].checked){ //선택되어 있으면 배열에 값을 저장함
            valueArr.push(list[i].value);
        }
    }
    if (valueArr.length == 0){
		Swal.fire({
  			title: '선택된 게시물이 없습니다.',
	  		text: "삭제하실 게시물을 선택해주세요.",
	  		icon: 'warning',
	  		confirmButtonColor: '#3085d6',
	  		confirmButtonText: '확인',
	  	})
    }else{
    	Swal.fire({
  		  	title: '게시물을 삭제하시겠습니까?',
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

//모달 닫힐때 초기화
$('.modal').on('hidden.bs.modal', function(){
	$('#IDCheck').attr('disabled', false);
	$('#m_id').prop('readonly', false);
	$(this).find('form')[0].reset();
})
</script>
</html>