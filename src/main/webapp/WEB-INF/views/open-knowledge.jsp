<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">	
	<title>인강인강 지식공유</title>
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/favicon.ico'/>">
	
	<!-- CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<%-- 부트스트랩 아이콘 --%>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
	
	<%-- for select option drop box --%>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.css">
	
	<%-- 템플릿에 사용되는 css --%>
	<link rel="stylesheet" href="<c:url value='/resources/css/animate.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/fontawesome-all.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/themify-icons.css'/>"> 
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
    <link rel="stylesheet" href="<c:url value='/resources/css/nice-select.css'/>">
	
	<!-- Javascript -->
	<script src="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
	<%-- popper.js는 항상 bootstrap 위에 와야 함 --%>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<%-- Bootstrap js --%>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    
	<style>
		a {
			color: #635c5c;
			text-decoration: none;
		} 
		
		/* 간판 컬러 없애기 */
		.section-overly::before {
			background-color: inherit;
		}
		
		/* 나의 지식에 가치를 부여하세요 */
		h1.title {
			font-size: 2.25rem;
		    font-weight: 700;
		    margin-bottom: 1rem;
		    color: #fff;
		}
		
		/* 지식 공유 설명 부분 */
		.subtitle {
			font-size: 1rem;
		    line-height: 1.5;
		    margin-top: 0;
		    margin-bottom: 1.625rem;
		    color: #fff;
		}
		
		/* 폼 라벨 옆에 * 필수 표시 */
		.modal_label--essential {
			--bs-text-opacity: 1;
    		color: rgba(var(--bs-danger-rgb),var(--bs-text-opacity))!important;
    		margin-left: 0.25rem!important;
    		vertical-align: middle!important;
		}
		
		.modal{ 
            position:absolu; 
            width:100%; height:100%; 
            background: rgba(0,0,0,0.1); 
            top:0; 
            left:0; 
            display:none;
        }

        .modal_content{
            background:#fff;
            position: fixed; 
            width: 480px;
            height: 100vh;
            top:50%; 
            left:50%;
            transform : translate(-50%, -50%);
            /* text-align:center; */
            box-sizing:border-box; 
            line-height:23px;
            border-style: solid;
            border-radius: 10px;
        }
	</style>
</head>
<body>
	 <%------------ header section  ------------%>
    <jsp:include page="fix/header.jsp" />
    
    <div class="slider-area ">
		<div class="single-slider slider-height2 section-overly d-flex align-items-center" 
		data-background=""
		style="background-image: url(https://cdn.inflearn.com/assets/images/become_instructor/header_background.jpg); 
		min-height:0px; padding: 5rem 1rem;">
			<div class="container">
				<div class="row">
					<div class="col-xl-12">
						<h1 class="title">나의 지식에 가치를 부여하세요</h3>
						<p class="subtitle">전체 지식공유자 평균 수익 3025만원!!<br>
						나의 지식을 나눠 사람들에게 배움의 기회를 주고, 의미있는 대가를 가져가세요.<br>
						인강인강은 지식으로 의미있는 수익과 공유가 가능한 한국 유일한 플랫폼 입니다.</p>
					</div>
					
					<div>
						<a id="open_knowledge_btn" href="javascript:;" class="genric-btn primary e-large">지식 공유 참여</a>
						<a href="https://drive.google.com/file/d/1W0FBBbalm8nYo2zrLHpdIbE2rGo2L7Uz/view" target="blank" class="genric-btn primary-border e-large">지식 공유자 안내서</a>
					</div>
				</div>
			</div>
		</div>
	</div>
    
    <form class="h-100" action="/submitOpenKnowledgeApplication" method="post">
	    <div class="modal" id="open_knowledge_modal">
	        <div class="modal_content card card-body d-flex" id="open_knowledge_modal_content" title="클릭하면 창이 닫힙니다.">
	        	
	        </div>
	    </div>
    </form>
    <jsp:include page="fix/footer.jsp" />
    
    <script>
        $(function(){ 
            $("#open_knowledge_btn").click(function(){
            	getOpenKnowledgeGuideForm();
                $("#open_knowledge_modal").fadeIn();
            });

            $(document).on("click", "#close_btn", function() {
            	$("#open_knowledge_modal").fadeOut();
            });
        });
        
        // 지식 공유 안내 양식 불러오기
        function getOpenKnowledgeGuideForm() {
        	$.ajax({
        		url: "/getOpenKnowledgeGuideForm",
        		type: "post",
        		dataType: "html",
        		success: function(html) {
        			var content = $(html).find("#open_knowledge_modal_content>*");
        			$("#open_knowledge_modal_content").html(content);
        		}
        	});
        }
        
        // 지식 공유 신청 양식 불러오기
        function getOpenKnowledgeApplicationForm() {
        	$.ajax({
        		url: "/getOpenKnowledgeApplicationForm",
        		type: "post",
        		dataType: "html",
        		success: function(html) {
        			var content = $(html).find("#open_knowledge_modal_content>*");
        			$("#open_knowledge_modal_content").html(content);
        		}
        	});
        }
    </script>
</body>
</html>