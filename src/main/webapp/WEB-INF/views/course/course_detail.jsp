<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    	<title> 강의 상세 </title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/favicon.ico'/>">
    
    <!-- CSS here -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
	<link rel="stylesheet" href="<c:url value='/resources/css/animate.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/fontawesome-all.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/themify-icons.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/nice-select.css'/>">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="<c:url value='/resources/js/plugins.js'/>"></script>
    <%-- <script src="<c:url value='/resources/js/main.js'/>"></script> --%>
    
	<%-- 모달용 --%>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
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
		a {
			color: #635c5c;
			text-decoration: none;
		}
		
		.nice-select {
		    width: 50px;
		    height: 30px;
		    background: #fff;
		    border-radius: 0px;
		    padding: 10px 12px;
		    color: #5d5d5d;
		    line-height: 6px;
		    border: 1px solid #ededed;
		    border-radius: 5px;
		}
		
		.modal{ 
            position:fixed; 
            width:100%; height:100%; 
            background: rgba(0,0,0,0.2); 
            top:0; 
            left:0; 
            display:none;
        }

        .modal_content{
            background:#fff;
            position: fixed; 
            top:50%; 
            left:50%;
            transform : translate(-50%, -50%);
            text-align:center;
            box-sizing:border-box; 
            line-height:23px;
            border-style: solid;
            border-radius: 10px;
        }
        
        .items-link a {
        	margin-bottom: 0px;
        }
        
        hr {
        	margin: 2rem 0;
		    border-top: 1px solid #cbcbcb;
		    border-bottom: 1px solid #fff;
		    opacity: 50%;
        }
        
        b {
        	color: #000000
        }
	</style>
</head>

<body>
    <%------------ header section  ------------%>
    <jsp:include page="../fix/header.jsp" />
    
    <%-- 상단 강의 정보 부분 --%>
    <div class="container-flex" style="background-color:#000a12">
    	<div class="container p-5">
    		<div class="row">
	    		<%-- 강의 표지 --%>
	    		<div class="col-xl-5 d-flex justify-content-center">
	    			<img src="<c:url value='${course.img_path}'/>" style="width:440px; height:286px; object-fit: contain;"/>
	    		</div>
		    	<div class="col-xl-7 card-body text-white">
		    		<div>
		    			<span>${mainCategory}</span> 
		    			<span><i class="bi bi-chevron-right"></i></span>
		    			<span>${typeService.getSubTypeBySubTypeNo(subCategoryList[0].sub_type_no).sub_type_name}</span>
		    		</div>
			    	<h3 class="fw-bold text-white mb-5">${course.title}</h3>
			    	<div class="stars-outer">
		                <div class="stars-inner" style="width:${starAvg*20}%"></div>
		            </div>
		            <span class="pr-5 number-rating">(${starAvg})</span>
		            <span>${replys.size()}개의 수강평 ∙ </span> <span>${stdCnt}명의 수강생</span>
		            <p class="text-white">${teacher.name}</p>
		            <div class="d-flex align-items-center items-link ">
		            	<span class="fs-4 pe-2 text-white">#</span>
		            	<c:forEach var="tag" items="${tags}">
		            		<a>${tagService.getTagByTag_no(tag.tag_no).tag_name}</a>
		            	</c:forEach>
		            </div>
			    </div>
		    </div>
	    </div>
    </div>
    
    <!--================Blog Area =================-->
    <div class="container-flex border-bottom">
    	<div class="container p-0">
    		<nav class="navbar navbar-expand-lg navbar-light">
				<div class="px-5">
					<ul class="navbar-nav">
						<li class="nav-item me-3"><a class="nav-link active fw-bold" href="/course/${pageNo}">강의소개</a></li>
						<li class="nav-item me-3"><a class="nav-link fw-bold" href="#curriculum">커리큘럼</a></li>
						<li class="nav-item me-3"><a class="nav-link fw-bold" href="#reviews">수강평</a></li>
						<li class="nav-item me-3"><a class="nav-link fw-bold" href="/course/${pageNo}/community">커뮤니티</a></li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
	
	<div class="container p-5">
		<div class="row">
			<%-- content 영역 --%>
			<div class="col-lg-8">
				<%-- 강의 소개 출력 --%>
				<c:if test="${contentType == 'main'}">
					<h4 class="mb-3"><b><c:if test="${course.level == 1}">입문자</c:if><c:if test="${course.level == 2}">초급자</c:if><c:if test="${course.level == 3}">중급자</c:if></b>를 위해 준비한<br>
					<b>[<c:forEach var="category" items="${subCategoryList}" varStatus="status">
						${typeService.getSubTypeBySubTypeNo(category.sub_type_no).sub_type_name}<c:if test="${not status.last}">, </c:if>
					</c:forEach>] 강의입니다.</b></h4>
				
					<p class="fw-bold">${course.introduction}</p>
				
					<hr>
				
					<c:out value="${course.content}" escapeXml="false" />
				
					<%-- 커리큘럼 --%>
					<div id="curriculum" class="mt-4 mb-3 fs-3 fw-bold">
						커리큘럼
					</div>
					<div class="mb-4">
						<c:forEach var="video" items="${videos}">
							<div class="p-2">
								<a <c:if test="${purchased == true || isCurrentCourseTeacher == true}">href="/course/${pageNo}/play/${video.olv_no}"</c:if> class="link-secondary" target="_blank">${video.title}</a>
							</div>
						</c:forEach>
					</div>
					
					<%-- 관련 노트 --%>
					<div class="mb-3 fs-3 fw-bold">
						관련 노트
					</div>
					
					<div class="mb-4">
						<c:forEach var="note" items="${notes}" begin="0" end="10" step="1">
							<div class="p-2">
								<a href="/note/${note.n_no}">제목 : ${note.title} / 작성자:${memberService.getMemberByM_no(note.m_no).m_name}</a> 
							</div>
						</c:forEach>
					</div>
					<%-- 수강평 --%>
					<div id="reviews" class="mb-3 fs-3 fw-bold">
						수강평
					</div>
					
					<%-- 수강평 출력 --%>
					<c:forEach var="reply" items="${replys}">
						<div class="stars-outer">
			                <div class="stars-inner" style="width:${reply.star_rating*20}%"></div>
			            </div>
			            <span class="pr-5 number-rating">(${reply.star_rating})</span><br/>
			    		<span class="fw-bold">${memberService.getNameByM_no(reply.m_no)}</span> <span>${reply.reg_date}</span>
			    		<p>${reply.content}</p>
				    	<hr>
					</c:forEach>
					
					<%-- 수강평 입력 --%>
					<c:if test="${purchased == true}">
						<form action="/course/submitReply" method="post">
							<input type="hidden" name="pageNo" value="${pageNo}"/>
							<div class="d-flex align-items-center select-job-items mb-1">
							<span class="mr-5">평점</span>
								<select name="star_rating">
								  <option selected value="5">5</option>
								  <option value="4">4</option>
								  <option value="3">3</option>
								  <option value="2">2</option>
								  <option value="1">1</option>
								</select>
							</div>
							<textarea class="form-control mb-2" name="content" rows="3"></textarea>
							<div class="d-flex flex-row-reverse">
								<button class="btn head-btn1" type="submit">등록</button>
							</div>
						</form>
					</c:if>
				</c:if>
				
				<c:if test="${contentType == 'community'}">
					<%-- 질문, 자유주제, 스터디 버튼 생성해야 함 --%>
					<div id="classify">
						<a onclick="changeClassify(2)" class="btn head-btn<c:choose><c:when test="${classify == 2}">1</c:when><c:otherwise>2</c:otherwise></c:choose>" onclick="">질문</a>
						<a onclick="changeClassify(1)" class="btn head-btn<c:choose><c:when test="${classify == 1}">1</c:when><c:otherwise>2</c:otherwise></c:choose>" onclick="">자유주제</a>
						<a onclick="changeClassify(4)" class="btn head-btn<c:choose><c:when test="${classify == 4 || classify == 5}">1</c:when><c:otherwise>2</c:otherwise></c:choose>" onclick="">스터디</a>
					</div>
					<%-- search input 요소 생성해야 함 --%>
					<div class="input-group py-3">
					  <input id="search" type="text" class="form-control">
					  <div class="input-group-append">
					    <button onclick="searchCommunityBoard()" class="btn btn-outline-secondary" type="button"><i class="bi bi-search"></i></button>
					  </div>
					</div>
					
					<%-- 게시물 영역 --%>
					<div id="content" class="tab-content">
						<div class="tab-pane fade show active" id="qwe">
							<c:forEach var="list" items="${cbList}">
								<article class="blog_item">
									<div class="blog_details" style="padding: 10px 10px 10px 10px;">
									
										<a class="d-inline-block" href="/communityBoardRead?cb_no=${list.cb_no}&classify=1&isOnlineLecture=${pageNo}">
											<font size="1px;">NO. <c:url value="${list.cb_no}"/></font>
											<h2><c:url value="${fn:substring(list.title, 0, 35)}"/></h2>
										</a>
										<p><c:url value="${fn:substring(list.content,0,200)}"/></p>
										<ul class="blog-info-link">
											<li><a href="#"><i class="fa fa-user"></i> <c:url value="${list.m_no}"/></a> </li>
											<li><a href="#"><i class="fa fa-comments"></i> <c:url value="${cbService.selectCommunityBoardReplyCount(list.cb_no)}"/> </a></li>
											<li><a href="#"><i class="fa fa-heart"></i> <c:url value="${cbService.selectCommunityBoardGoodCount(list.cb_no)}"/></a></li>
											<li><i class="fa fa-clock-o"> </i><font size="2" color="#848484"><c:url value="${list.reg_date}"/></font></li>
										</ul>
									</div>
								</article>
							</c:forEach>
						</div>
					</div>
					<%-- 게시물 영역 끝 --%>
					
					<%-- Pagination 영역 --%>
					<c:if test="${paging.totalCount != 0}">
						<nav id="pagination" class="pagination-area justify-content-center d-flex" style="margin: 0px;">
							<ul class="pagination">
								<!-- 첫 페이지면 Disabled 아니라면 Enabled -->
								<c:choose>
									<c:when test="${paging.pageNo eq paging.firstPageNo }">
									</c:when>
									<c:otherwise>
										<li class="page-item"><a href="/course/${pageNo}/community?page=${paging.prevPageNo}&classify=${classify}" class="page-link" aria-label="Previous"> <i class="ti-angle-left"></i> </a></li>
									</c:otherwise>
								</c:choose>
								<!-- 페이지 갯수만큼 버튼 생성 -->
								<c:forEach var="i" begin="${paging.startPageNo }" end="${paging.endPageNo }" step="1">
									<c:choose>
										<c:when test="${i eq paging.pageNo}">
											<li class="page-item active"> <a href="" class="page-link" style="pointer-events: none;"><c:out value="${i }"/></a> </li>
										</c:when>
										<c:otherwise>
											<li class="page-item"> <a href="/course/${pageNo}/community?page=${i}&classify=${classify}" class="page-link"><c:out value="${i }"/></a> </li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<!-- 마지막 페이지면 Disabled 아니라면 Enabled -->
								<c:choose>
									<c:when test="${paging.pageNo eq paging.finalPageNo }">
									</c:when>
									<c:otherwise>
										<li class="page-item"><a href="/course/${pageNo}/community?page=${paging.nextPageNo}&classify=${classify}" class="page-link" aria-label="Next"> <i class="ti-angle-right"></i></a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</nav>
					</c:if>
					<%-- Pagination 영역 끝 --%>
				</c:if>
			</div>
			
			<%-- 가격, 수강신청 패널 --%>
			<div class="col-lg-4">
			    <div class="card">
			      <div class="card-body p-4">
			        <h5 class="card-title mb-4 fw-200">${course.price}원</h5>
			        <c:if test="${member != null}">
			        	<c:choose>
			        		<c:when test="${isCurrentCourseTeacher == true}">
		        				<a href="/rewriteCourse?no=${pageNo}" class="btn head-btn1 mb-3" style="min-width:100%;">강의 수정</a>
		        				<a onclick="deleteCourse();" class="btn head-btn1 mb-3" style="min-width:100%;">강의 삭제</a>
			        		</c:when>
			        		<c:when test="${purchased == true}">
			        			<a class="btn head-btn2 mb-3" style="min-width:100%;">수강신청 중</a>
			        		</c:when>
			        		<c:when test="${purchased == false}">
			        			<a href="javascript:;" onclick="openRegCoursesModal()" class="btn btn-primary mb-3" style="min-width:100%;">수강신청 하기</a>
			        		</c:when>
			        	</c:choose>
			        </c:if>
			        <c:if test="${member == null}">
			        	<a href="/loginPageView" class="btn btn-primary mb-3" style="min-width:100%;">수강신청 하기</a>
			        </c:if>
			        <span class="d-flex justify-content-center" data-cnt="398" data-target="PC">
			        	<c:if test="${member != null}">
							<i onclick="clickedHeart(this)" id="like" class="bi bi-heart me-1" style="font-size:25px;"></i>
						</c:if>
						<c:if test="${member == null}">
							<i class="bi bi-heart me-1" style="font-size:25px;"></i>
						</c:if>
						<span id="likeCnt" style="font-size:23px">${likeCnt}</span>
					</span>
			      </div>
			    </div>
			</div>
		</div>
		
		<%-- 구매 모달창 --%>
		<div class="modal" id="regCourseModal">
        	<div class="modal_content card" style="width:20rem; height:16rem;">
	            <div class="card-body">
	                <h5 class="card-title">결제</h5>
	                <h6 class="card-subtitle mb-2 text-muted">안내사항</h6>
	                <p class="card-text">단순 변심으로 환불은 불가능합니다.</p>
	                <p class="card-text">정말 구매하시겠습니까?</p>
	                <a href="javascript:;" onclick="purchaseCourse()" class="btn head-btn1">예</a>
	                <a href="javascript:;" onclick="closeRegCoursesModal()" class="btn head-btn2">아니오</a>
	            </div>
	        </div>
	    </div>
    </div>
    
    <jsp:include page="../fix/footer.jsp" />
    
    <%------------ footer section  ------------%>
    
    <script>
    	<c:if test="${classify != null}">
    	var classify = ${classify};
    	</c:if>
    	
    	// textarea 자동 늘리기
	    $(function() {
		    $('textarea').each(function() {
		        $(this).height($(this).prop('scrollHeight'));
		        console.log("늘리기");
		    });
		    
		 	// 외부영역 클릭 시 팝업 닫기
			$(document).mouseup(function (e){
				var modal_content = $(".modal_content");
				if(modal_content.has(e.target).length === 0){
					closeRegCoursesModal();
				}
			});
		});
    	
		// 좋아요를 이미 클릭했다면 채우기
		$(function() {
			if(${existLike}) {
		 		var x = $('#like')[0]; 
		 		x.classList.toggle("bi-heart");
		        x.classList.toggle("bi-heart-fill");
		        x.classList.toggle("text-danger");
				}
		});
		
		// 좋아요 클릭 이벤트
		function clickedHeart(x) {
			var status;
			var likeCnt = parseInt($('#likeCnt').html());
			if(x.classList.contains("bi-heart-fill")) {
				status = false;
				likeCnt = likeCnt - 1; 
			}
			else {
				status = true;
				likeCnt = likeCnt + 1; 
			}
		       
			$('#likeCnt').text(likeCnt);
		       
			x.classList.toggle("bi-heart");
			x.classList.toggle("bi-heart-fill");
			x.classList.toggle("text-danger");
			$.ajax({
				url: '/courseClickedLike',
				type: 'post',
				data: {
					status: status,
					oli_no: ${pageNo}
				}
			});
		}
		
		// 수강신청 모달창 열기
		function openRegCoursesModal() {
			$("#regCourseModal").fadeIn();
		}
		
		// 수강신청 모달창 닫기
		function closeRegCoursesModal() {
			$("#regCourseModal").fadeOut();
		}
		
		// 강의 구매
		function purchaseCourse() {
			$.ajax({
				url: '/purchaseCourse',
				type: 'post',
				data: {
					oli_no: ${pageNo}
				},
				success: function() {
					location.reload();
				},
				error: function() {
					alert("error");
				}
			});
		}
		
		function deleteCourse() {
			if(confirm("정말 강의를 삭제하시겠습니까?")) {
				location.href = "/deleteCourse?no=${pageNo}";
			}
		}
		
		function changeClassify(classify) {
			this.classify = classify;
			$.ajax({
				url: '/course/${pageNo}/community',
				type: 'post',
				data: {
					classify: classify
				},
				dataType: 'html',
				success : function(data) {
					var type = $(data).find("#classify");
					var content = $(data).find("#content");
					var pagination = $(data).find("#pagination");
					
					$("#classify").html(type);
					$("#content").html(content);
					$("#pagination").html(pagination);
					
					$("#search").val("");
				}
			});
		}
		
		function searchCommunityBoard() {
			$.ajax({
				url: '/course/${pageNo}/community',
				type: 'post',
				data: {
					classify: classify,
					search: $("#search").val()
				},
				dataType: 'html',
				success : function(data) {
					var content = $(data).find("#content");
					var pagination = $(data).find("#pagination");
					
					$("#content").html(content);
					$("#pagination").html(pagination);
				}
			});
		}
    </script>
    

</body>
</html>