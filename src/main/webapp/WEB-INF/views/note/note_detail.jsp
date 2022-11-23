<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    	<title> 노트 상세 </title>
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
		a:hover {
			color: #fb246a;
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
            /* text-align:center; */
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
			    	<h3 class="fw-bold text-white mb-5">${note.title}</h3>
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
						<li class="nav-item me-3"><a class="nav-link active fw-bold" href="/notes/${pageNo}">노트 소개</a></li>
						<li class="nav-item me-3"><a class="nav-link fw-bold" href="#contents">노트 내용</a></li>
						<li class="nav-item me-3"><a class="nav-link fw-bold" href="#reviews">리뷰</a></li>
						<%-- <li class="nav-item me-3"><a class="nav-link fw-bold" href="/courses/${pageNo}/community">커뮤니티</a></li> --%>
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
					</c:forEach>] 노트입니다.</b></h4>
				
					<c:out value="${note.content}" escapeXml="false" />
					
					<div class="mb-4">
						<c:forEach var="article" items="${articles}">
							<div class="p-2">
								<a href="javascript:;" <c:if test="${purchased == true || note.m_no == member.m_no || member.m_authority == 1}">
									onclick="openModal('#${article.na_no}')"</c:if> class="link-secondary">${article.title}</a>
								<div class="float-end me-3">
									<span><a href="/course/${note.oli_no}/play/${article.order}">수정</a></span>
						    		<span>| <a href="javascript:;" onclick="deleteNoteArticle(${note.oli_no}, ${article.order})">삭제</a></span>
					    		</div>
							</div>
							
							<%-- 노트 글 모달 --%>
							<div class="modal" id="${article.na_no}">
								<div class="modal_content card" style="width:35rem; height:37rem;">
									<div class="card-body p-4">
										<h5 class="card-title fw-bold mb-3 text-center">${article.title}</h5>
										<c:out value="${article.content}" escapeXml="false"/>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
					
					
					
					<%-- 리뷰 --%>
					<div class="mb-3 fs-3 fw-bold">
						리뷰
					</div>
					
					<%-- 수강평 출력 --%>
					<c:forEach var="reply" items="${replys}">
						<div class="stars-outer">
			                <div class="stars-inner" style="width:${reply.star_rating*20}%"></div>
			            </div>
			            <span class="pr-5 number-rating">(${reply.star_rating})</span><br/>
			    		<span class="fw-bold">${memberService.getMemberByM_no(reply.m_no).m_name}</span> <span>${reply.reg_date}</span>
			    		<p>${reply.content}</p>
				    	<hr>
					</c:forEach>
					
					<%-- 리뷰 입력 --%>
					<c:if test="${purchased == true || member.m_authority == 1}">
						<form action="/notes/submitReply" method="post">
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
							<textarea class="form-control mb-2" name="content" rows="3" required></textarea>
							<div class="d-flex flex-row-reverse">
								<button class="btn head-btn1" type="submit">등록</button>
							</div>
						</form>
					</c:if>
				</c:if>
			</div>
			
			<%-- 가격, 수강신청 패널 --%>
			<div class="col-lg-4">
			    <div class="card">
			      <div class="card-body p-4">
			        <h5 class="card-title mb-4 fw-200">${note.price}원</h5>
			        <c:if test="${member != null}">
			        	<c:choose>
		        			<%-- 관리자일 때 --%>
			        		<c:when test="${member.m_authority == 1}">
			        			<a class="btn head-btn2 mb-3" style="min-width:100%;">관리자 계정</a>
			        		</c:when>
				        		<c:when test="${note.m_no == member.m_no}">
				        			<a class="btn head-btn2 mb-3" style="min-width:100%;">작성자의 노트</a>
				        		</c:when>
			        		<c:otherwise>
			        			<c:if test="${purchased == true}">
					        		<a class="btn head-btn2 mb-3" style="min-width:100%;">구매 완료</a>
					        	</c:if>
					        	<c:if test="${purchased == false}">
							        <a href="javascript:;" onclick="openModal('#purchaseModal')" class="btn btn-primary mb-3" style="min-width:100%;">구매 하기</a>
					        	</c:if>
			        		</c:otherwise>
			        	</c:choose>
			        </c:if>
			        <c:if test="${member == null}">
			        	<a href="/loginPageView" class="btn btn-primary mb-3" style="min-width:100%;">구매 하기</a>
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
		<div class="modal" id="purchaseModal">
        	<div class="modal_content card" style="width:20rem; height:16rem;">
	            <div class="card-body">
	                <h5 class="card-title">결제</h5>
	                <h6 class="card-subtitle mb-2 text-muted">안내사항</h6>
	                <p class="card-text">단순 변심으로 환불은 불가능합니다.</p>
	                <p class="card-text">정말 구매하시겠습니까?</p>
	                <a href="javascript:;" onclick="purchaseNote()" class="btn head-btn1">예</a>
	                <a href="javascript:;" onclick="closeModal('#purchaseModal')" class="btn head-btn2">아니오</a>
	            </div>
	        </div>
	    </div>
    </div>
    
    <jsp:include page="../fix/footer.jsp" />
    
    <%------------ footer section  ------------%>
    
    <script>
    	// textarea 자동 늘리기
	    $(function() {
	    	$('textarea').each(function() {
		        $(this).height($(this).prop('scrollHeight'));
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
				url: '/noteClickedLike',
				type: 'post',
				data: {
					status: status,
					n_no: ${pageNo}
				}
			});
		}
		
		var selected = "";
		// 수강신청 모달창 열기
		function openModal(id) {
			$(id).fadeIn();
			selected = id;
			
			// 선택된 모달의 textarea의 높이 자동 늘리기
			var txtArea = $(id).find('textarea');
			txtArea.height(txtArea.prop('scrollHeight'));
		}
		
		// 수강신청 모달창 닫기
		function closeModal(id) {
			$(id).fadeOut();
		}
		
		$(function(){ 
			// 외부영역 클릭 시 팝업 닫기
			$(document).mouseup(function (e){
				if($(".modal_content").has(e.target).length === 0)
					closeModal(selected);
			});
		});
		
		function purchaseNote() {
			$.ajax({
				url: '/purchaseNote',
				type: 'post',
				data: {
					n_no: ${pageNo}
				},
				success: function() {
					location.reload();
				}
			});
		}
		
		function deleteNoteArticle(oli_no, order) {
			if(confirm("정말 노트 글을 삭제하시겠습니까?")) {
				$.ajax({
					url: "/deleteNoteArticle",
					type: "POST",
					data: {
						oli_no: oli_no,
						order: order
					},
					success: function() {
						location.reload();
					}
				});
			}
		}
    </script>
    

</body>
</html>