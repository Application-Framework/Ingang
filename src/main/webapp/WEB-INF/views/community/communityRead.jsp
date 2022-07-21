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
			<div class="row">
				<div class="col-lg-10 posts-list">
					<div class="single-post">
						<div class="blog_details">
							<h2>${cbReadPage.title}</h2>
							<ul class="blog-info-link mt-3 mb-4">
								<li><a href="#"><i class="fa fa-user"></i> ${cbReadPage.m_id}</a></li>
								<c:choose>
									<c:when test="${hitReadPage eq 1}"> 
										<li><a  href="#">조회수 ${cbReadPage.hit+1}</a></li>
									</c:when>
									<c:otherwise> 
										<li><a  href="#">조회수 ${cbReadPage.hit}</a></li>
									 </c:otherwise>
								</c:choose>
								
								<li><a href="#"><i class="fa fa-clock"></i> ${cbReadPage.reg_date}</a></li>
								
								<c:if test="${sessionScope.member.getM_no() eq cbReadPage.m_no}">
									<li><a href="javascript:void(0)" onclick="buttonModify(${cbReadPage.cb_no}, ${classify})"><i class="far fa-edit"></i> 수정</a></li>
									<li><a href="javascript:void(0)" onclick="boardDelete(${cbReadPage.cb_no})"><i class="fas fa-trash-alt"></i> 삭제</a></li>
								</c:if>
							</ul>
							<P>
								${cbReadPage.content}
							</P>
							<ul id="tag-list" style=""> 
								<c:forEach var="cbTag" items="${cbTag}">
									<li class="tag-item">#${cbTag.tag_name}</li>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div class="comments-area" style="padding: 20px 0px" id="replyList">
						<h4><a name="target"><c:out value="${cbReadPage.cbr.conunt}"/>개의 댓글이 달렸습니다.</a></h4>
						<div class="comment-list">
							<div >
							<c:forEach var="cbrList" items="${cbrList}">
								<div class="user justify-content-start d-flex">
									<div class="thumb">
										<img alt="" src="<c:out value='/resources/img/logo/logo5.png'></c:out>">
									</div>
									<div class="desc">
										<div>
											<div class="d-flex align-items-center">
												<ul class="blog-info-link mt-3 mb-4">
													<li><a href="#"><i class="fa fa-user"></i> <c:out value="${cbrList.m_id}"/></a></li>
													<li><a href="#"><i class="fa fa-clock"></i> <c:out value="${cbrList.reg_date}"/></a></li>
													<c:if test="${sessionScope.member.getM_no() eq cbrList.m_no}">
														<li><a href="javascript:void(0)" onclick="replyEdit(${cbrList.cbr_no}, '${cbrList.content}')"><i class="far fa-edit"></i> 수정</a></li>
														<li><a href="javascript:void(0)" onclick="replyDelete(${cbrList.cbr_no})"><i class="fas fa-trash-alt"></i> 삭제</a></li>
													</c:if>
												</ul>
											</div>
										</div>
										<p class="comment">
											<c:out value="${cbrList.content}"/>
										</p>
									</div>
								</div>
							</c:forEach>
							</div>
						</div>
					</div>
					<div class="comment-form" id="replyTop">
						<c:choose>
							<c:when test="${member ne null}"> 
							<h4>댓글</h4>
								<form class="form-contact comment_form" id="commentForm" method="POST">
									<div class="row">
										<div class="col-12">
											<input type="hidden" id="cb_no" name="cb_no" value="${cbReadPage.cb_no}">
											<input type="hidden" id="m_no" name="m_no" value="${sessionScope.member.getM_no()}">
											<div class="form-group">
												<textarea class="form-control w-100" name="content" id="content" cols="30" rows="5" placeholder="의견을 남겨주세요"></textarea>
											</div>
										</div>
									</div>
									<div class="form-group" align="right">
										<button type="button"  id="btnReplyWrite" class="button button-contactForm btn_1 boxed-btn">작성</button>
									</div>
								</form>
							</c:when>
							<c:otherwise>
								<div align="center"><a href="loginPageView">
									<button type="button"  class="button button-contactForm btn_1 boxed-btn" style="width: 80%;">로그인 후, 댓글 작성이 가능합니다!</button>
									</a>
								</div><br>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="col-lg-2" id="sidebox">
					<div id="heartDiv">
						<ul>
							<c:choose>
								<c:when test="${member ne null && boardLikeCheck eq 1}">
									<li><button class="genric-btn danger-border radius" id="subtractGood"> <i class="fas fa-heart"></i> ${cbReadPage.good}</button></li>
								</c:when>
								<c:when test="${member ne null && boardLikeCheck ne 1}">
									<li><button class="genric-btn danger-border radius" id="addGood"> <i class="far fa-heart"></i> ${cbReadPage.good}</button></li>
								</c:when>
								<c:otherwise> 
									<li><button class="genric-btn danger-border radius" id="buttonNoLogin"> <i class="far fa-heart"></i> ${cbReadPage.good}</button></li>
								 </c:otherwise>
							</c:choose>
							<br>
							<c:choose>
								<c:when test="${classify eq 2 && sessionScope.member.getM_no() eq cbReadPage.m_no}">
									<li style="margin-top: 10px;"><button class="genric-btn danger-border radius" id="btnQuestionsCompleted" style="padding: 0 20px; width: 82px;"> 미해결</button></li><br>
								</c:when>
								<c:when test="${classify eq 2 && sessionScope.member.getM_no() ne cbReadPage.m_no}">
									<li style="margin-top: 10px;"><button class="genric-btn danger-border radius" id="btnNoWriter" style="padding: 0 20px; width: 82px;"> 미해결</button></li><br>
								</c:when>
								<c:when test="${classify eq 3}">
									<li style="margin-top: 10px;"><button class="genric-btn danger-border radius" id="" style="padding: 0 20px; width: 82px;"> 해결됨</button></li><br>
								</c:when>
								<c:when test="${classify eq 4 && sessionScope.member.getM_no() eq cbReadPage.m_no}">
									<li style="margin-top: 10px;"><button class="genric-btn danger-border radius" id="btnStudyCompleted" style="padding: 0 20px; width: 82px;"> 모집중</button></li><br>
								</c:when>
								<c:when test="${classify eq 4 && sessionScope.member.getM_no() ne cbReadPage.m_no}">
									<li style="margin-top: 10px;"><button class="genric-btn danger-border radius" id="btnNoWriter" style="padding: 0 20px; width: 82px;"> 모집중</button></li><br>
								</c:when>
								<c:when test="${classify eq 5}">
									<li style="margin-top: 10px;"><button class="genric-btn danger-border radius" id="" style="padding: 0 20px; width: 82px;">모집종료</button></li><br>
								</c:when>
								<c:otherwise> 
								</c:otherwise>
							</c:choose>
							<li style="margin-top: 10px;">
								<a href="javascript:pageReplyScroll();" target="_self"><button class="genric-btn danger-border radius">댓글</button></a>
							</li>
						</ul>
					</div>
				</div>
			</div>
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
<script>
$('#addGood').click(function(){
	console.log("asdsd");
	$.ajax({
		url: "addGoodCommunityBoard",
		type: "GET",
		data: {'cb_no':${cbReadPage.cb_no}},
		success: function() {
			var reLoadUrl = "/communityBoardRead?cb_no=" + ${cbReadPage.cb_no} + "&classify=" + ${classify};
			$("#heartDiv").load(reLoadUrl + " #heartDiv");
			//location.href = reLoadUrl;
		}
	});
});

$('#subtractGood').click(function(){
	console.log("asdsdsdsd");
	$.ajax({
		url: "subtractGoodCommunityBoard",
		type: "GET",
		data: {'cb_no':${cbReadPage.cb_no}},
		success: function() {
			var reLoadUrl = "/communityBoardRead?cb_no=" + ${cbReadPage.cb_no} + "&classify=" + ${classify};
			$("#heartDiv").load(reLoadUrl + " #heartDiv");
		}
	});
});

$('#buttonNoLogin').click(function(){
	swal({
		title: "로그인",
		text: "로그인이 되어야 좋아요가 가능합니다.",
		icon: "warning",
	});
});

$('#btnReplyWrite').click(function() {
	var m_no = $("#m_no").val();
	var content = $("#content").val();
	var param = {'m_no': m_no , 'content': content, 'cb_no': $("#cb_no").val()};
	
	if(!content) {
		swal({
			title: "댓글작성",
			text: "내용이 입력되지 않았습니다.",
			icon: "warning",
			timer: 3000
		});
		return false;
	}
	else {
		$.ajax({
			url: "writeReplyCommunityBoard",
			type: "POST",
			data: param,
			success: function(data) {
				if (data != 1) {
					swal({
						title: "댓글작성",
						text: "댓글 등록이 실패하였습니다.",
						icon: "error",
						timer: 3000
					});
				}
				else {
					var reLoadUrl = "/communityBoardRead?cb_no=" + ${cbReadPage.cb_no} + "&classify=" + ${classify};
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
})

// 게시글 댓글 삭제
function replyDelete(cbr_no) {
	$.ajax({
		url: "deleteReplyCommunityBoard",
		type: "POST",
		data:  {'cbr_no': cbr_no},
		success: function(data) {
			if (data != 1) {
				swal({
					title: "댓글삭제",
					text: "댓글 삭제가 실패하였습니다.",
					icon: "error",
					timer: 3000
				});
			}
			else {
				var reLoadUrl = "/communityBoardRead?cb_no=" + ${cbReadPage.cb_no} + "&classify=" + ${classify};
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

/* 댓글 수정 */
function replyEdit(cbr_no, reply) {
	var htmls = "";
	
	htmls += '<form action="travelreplyModify" method="POST" class="contact-one__form">';
	htmls += '<div class="input-group">';
	htmls += '<textarea id="replyEditContent" name="Content" placeholder="댓글을 입력하세요..." cols="100">'+reply+'</textarea>';
	htmls += '<button id="btnReplyModify" name="btnReplyModify" class="thm-btn-psd2" type="button" onclick="replySave('+cbr_no+')">등록</button>';
	htmls += '<button class="thm-btn-psd2" type="button" onclick="replyCancel(' + cbr_no + ', \'' + reply + '\')">취소</button>';
	htmls += '</div>';
	htmls += '</form>';
	
	$("#replyContentSection"+cbr_no).html(htmls);
	$('#replyEditContent').focus();
}

/* 댓글 수정 취소 */
function replyCancel(cbr_no, reply) {
	var htmls = "";
	
	htmls += reply;
	
	$("#replyContentSection"+cbr_no).html(htmls);
}

/* 댓글 수정 등록 */
function replySave(cbr_no) {
	var Content = $("#replyEditContent").val();
	var param = {'Content': Content, 'cbr_no': cbr_no};
	
	if(!Content) {
		swal({
			title: "여행포토",
			text: "댓글이 입력되지 않았습니다.",
			icon: "warning",
			timer: 3000
		});
		return false;
	}
	else {
		$.ajax({
			url: "travelreplyModify",
			type: "POST",
			data: param,
			success: function(data) {
				if (data != 1) {
					swal({
						title: "댓글수정",
						text: "댓글 수정이 실패하였습니다.",
						icon: "error",
						timer: 3000
					});
				}
				else {
					swal({
						title: "댓글수정",
						text: "댓글이 성공적으로 수정되었습니다.",
						icon: "success",
						buttons : {
							confirm : {
								value : true
							}
						}
					}).then((result) => {
						if(result) {
							location.reload();
						}
					});
				}
			},
			error: function() {
				swal({
					title: "댓글수정",
					text: "문제가 발생하였습니다.\n잠시 후 다시 시도해주세요.",
					icon: "error",
					timer: 3000
				});
			}
		});
	}
}

//게시글 삭제
function boardDelete(cb_no) {
	$.ajax({
		url: "deleteCommunityBoard",
		type: "POST",
		data:  {'cb_no': cb_no},
		success: function(data) {
			if (data != 1) {
				swal({
					title: "글삭제",
					text: "글 삭제가 실패하였습니다.",
					icon: "error",
					timer: 3000
				});
			}
			else {
				var reLoadUrl = "/communityChats";
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

//게시글 수정
function buttonModify(cb_no, classify) {
	var urlModify = "modfiyPageCommunityBoard?cb_no=" + cb_no+ "&classify=" + classify;
	var popup = window.open(urlModify , '게시글수정' , 'width=930px,height=840px,left=300,top=100, scrollbars=yes, resizable=no');
}

/*우측 사이드바*/
var target = document.getElementById("replyTop");
var abTop =  target.getBoundingClientRect().top;
//console.log(abTop);
function pageReplyScroll() {
	window.scrollTo( 0, abTop);
}

var currentPosition = parseInt($("#sidebox").css("top"));
$(window).scroll(function(){
	var position = $(window).scrollTop();
	$("#sidebox").stop().animate({"top":position+currentPosition+"px"},1000);
});

//미해결->해결됨
$('#btnQuestionsCompleted').click(function(){
	swal({
		title: '미해결',
		text: "해결됨으로 변경하게 되면 미해결로 변경이 불가합니다.",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: '확인',
		cancelButtonText: '취소'
	}).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: "",
				type: "GET",
				data: {'cb_no':${cbReadPage.cb_no}},
				success: function() {
					var reLoadUrl = "/communityBoardRead?cb_no=" + ${cbReadPage.cb_no} + "&classify=" + ${classify};
					$("#heartDiv").load(reLoadUrl + " #heartDiv");
				}
			});
		}
	})
	
});

//모집중 -> 모집종료
$('#btnStudyCompleted').click(function(){
	$.ajax({
		url: "",
		type: "GET",
		data: {'cb_no':${cbReadPage.cb_no}},
		success: function() {
			var reLoadUrl = "/communityBoardRead?cb_no=" + ${cbReadPage.cb_no} + "&classify=" + ${classify};
			$("#heartDiv").load(reLoadUrl + " #heartDiv");
		}
	});
});
</script>
</body>
</html>