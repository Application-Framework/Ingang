<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인강인강 관리자</title>
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
						<h1 class="h3 mb-0 text-gray-800">Member Management</h1>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-8">
							<form action="/admin/memberSearchList" role="form" method="GET" class="form-inline">
								<select class="form-control" id="searchCategory" name="searchCategory">
									<option value="m_no">회원번호</option>
									<option value="m_id">아이디</option>
									<option value="m_name">이름</option>
								</select>
								<div class="col-sm-4">
									<input type="text" id="searchKeyword " name="searchKeyword" placeholder="검색어를 입력하세요." class="form-control" required="required">
									<button type="submit" class="btn px-3 btn-primary">
										<i class="fas fa-search"></i>
									</button>
								</div>
							</form>
						</div>
						
						<div class="col-sm-4">
							<div class="d-flex">
								<div class="ml-auto">
									<button class="btn btn-primary" data-toggle="modal" data-target="#AdminSignUp">회원등록</button>
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
								<th><font size="3">ID</font></th>
								<th><font size="3">이름</font></th>
								<th><font size="3">권한</font></th>
								<th><font size="3">가입일자</font></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${adminMemberList}" var="memberData">
								<tr>
									<td>
										<input name="RowCheck" type="checkbox" value="${memberData.m_no}">			
									</td>
									<td><font size="3">${memberData.m_no}</font></td>
									<td><font size="3"><c:out value="${memberData.m_id}"></c:out></font></td>
									<td><font size="3"><a href="javascript:void(window.open('/admin/memberDetail?m_no=${memberData.m_no}', '상세페이지' , 'width=930px,height=840px,left=300,top=100, scrollbars=yes, resizable=no'));"><c:out value="${memberData.m_name}"></c:out></a></font></td>
									<c:choose>
										<c:when test="${memberData.m_authority eq 0}">
											<td><font size="3"></font></td>
										</c:when>
										<c:when test="${memberData.m_authority eq 1}">
											<td><font size="3">관리자</font></td>
										</c:when>
										<c:when test="${memberData.m_authority eq 2}">
											<td><font size="3">회원</font></td>
										</c:when>
									</c:choose>
									<td><font size="3"><c:out value="${memberData.reg_date}"></c:out></font></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
			</div>
			<!-- 게시글 페이징 처리(기준 10개) -->
			<nav aria-label="Page navigation">
				<ul class="pagination justify-content-center">
			
					<!-- 첫 페이지면 Disabled 아니라면 Enabled -->
					<c:choose>
						<c:when test="${Paging.pageNo eq Paging.firstPageNo }">
							<li class="page-item disabled">
								<a class="page-link" href="memberList?page=${Paging.prevPageNo}">Previus</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item">
								<a class="page-link" href="memberList?page=${Paging.prevPageNo}">Previus</a>
							</li>
						</c:otherwise>
					</c:choose>
					<!-- 페이지 갯수만큼 버튼 생성 -->
					<c:forEach var="i" begin="${Paging.startPageNo }" end="${Paging.endPageNo }" step="1">
						<c:choose>
							<c:when test="${i eq Paging.pageNo }">
								<li class="page-item disabled">
									<a class="page-link" href="memberList?page=${i}"><c:out value="${i }"/></a>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a class="page-link" href="memberList?page=${i}"><c:out value="${i }"/></a>
								</li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<!-- 마지막 페이지면 Disabled 아니라면 Enabled -->
					<c:choose>
						<c:when test="${Paging.pageNo eq Paging.finalPageNo }">
							<li class="page-item disabled">
								<a class="page-link" href="memberList?page=${Paging.nextPageNo}">Next</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="page-item">
								<a class="page-link" href="memberList?page=${Paging.nextPageNo}">Next</a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
			<!-- 하단 푸터 부분 -->
			<jsp:include page="../layout/footer.jsp"/>
    		<!-- 하단 푸터 부분 -->
    		
		</div>
	</div>
	
	<!-- 회원 등록 -->
	<div class="modal fade" id="AdminSignUp" tabindex="-1" role="dialog" aria-labelledby="memberModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-lg" role="document">
			<div class="modal-content">
				<div class="modal-header border-bottom-0">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-title text-center">
						<h4>회원 등록</h4>
						<hr>
					</div>
					<div class="d-flex flex-column">
						<form name="form" id="form" action="AdminSignUp" method="POST">
							<!-- 아이디 & 비밀번호 -->
							<div class="form-group row">
								<div class="col-xs-6 col-md-6">
									<div class="input-group my-2 mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text">아이디</span>
										</div>
										<input type="text" name="UserID" id="account" class="form-control" required>
									</div>
								</div>
								<div class="col-xs-6 col-md-6">
									<div class="input-group my-2 mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text">비밀번호</span>
										</div>
										<input type="password" name="Password" id="password" class="form-control">
									</div>
								</div>
							</div>
							<!-- 이름 & 마일리지 -->
							<div class="form-group row">
								<div class="col-xs-6 col-md-6">
									<div class="input-group my-2 mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text">이름</span>
										</div>
										<input type="text" id="Name" name="Name" class="form-control" required>
									</div>
								</div>
								<div class="col-xs-6 col-md-6">
									<div class="input-group my-2 mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text">성별</span>
										</div>
										<select class="form-control" id="Sex">
											<option value="1" >남성</option>
											<option value="0" >여성</option>
										</select>
									</div>
								</div>
							</div>
							<!-- 권한 & 성별 -->
							<div class="form-group row">
								<div class="col-xs-6 col-md-6">
									<div class="input-group my-2 mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text" id="Grade">권한</span>
										</div>
										<select class="form-control">
											<option value="일반">일반</option>
											<option value="VIP">VIP</option>
											<option value="블랙">블랙</option>
											<option value="관리자">관리자</option>
										</select>
									</div>
								</div>
								<div class="col-xs-6 col-md-6">
									<div class="input-group my-2 mb-1">
										<div class="input-group-prepend">
											<span class="input-group-text">휴대전화</span>
										</div>
										<input type="tel" name="Phone" id="Phone" class="form-control" required>
									</div>
								</div>
							</div>
							<!-- 특이사항 -->
							<div class="form-group">
								<div class="input-group my-2 mb-1">
									<div class="input-group-prepend">
										<span class="input-group-text">특이사항</span>
									</div>
									<textarea rows="5" cols="25" name="comment" id="comment" class="form-control"></textarea>
								</div>
							</div>
							
							<button type="submit" class="btn btn-primary btn-block btn-round" onclick="adminsubmit()">회원등록</button>
						</form>
					</div>
				</div>
			</div>
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
		var url = "/admin/memberSelectDelete";    // Controller로 보내고자 하는 URL
		var valueArr = new Array();
	    var list = $("input[name='RowCheck']");
	    for(var i = 0; i < list.length; i++){
	        if(list[i].checked){ //선택되어 있으면 배열에 값을 저장함
	            valueArr.push(list[i].value);
	        }
	    }
	    if (valueArr.length == 0){
    		Swal.fire({
	  			title: '선택된 회원이 없습니다.',
		  		text: "삭제하실 회원을 선택해주세요.",
		  		icon: 'warning',
		  		confirmButtonColor: '#3085d6',
		  		confirmButtonText: '확인',
		  	})
	    }else{
	    	Swal.fire({
	  		  	title: '회원을 삭제하시겠습니까?',
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
</body>
</html>