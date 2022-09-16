<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
         <title> 강의 검색 </title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/favicon.ico'/>">
	
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<!-- CSS here -->
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <%-- <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>"> --%>
        <link rel="stylesheet" href="<c:url value='/resources/css/owl.carousel.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/flaticon.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/price_rangs.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/slicknav.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/animate.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/price_rangs.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/magnific-popup.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/fontawesome-all.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/themify-icons.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/slick.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/nice-select.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/responsive.css'/>">
        <%-- <link rel="stylesheet" href="<c:url value='/resources/css/sidebars.css'/>"> --%>

        <style>
            .btn:hover::before {
                /* transform: translate(-50%,-50%); */
            }

            .btn-toggle::before {
                /* position:absolute;
                top:50%;
                left:10%;
                background: none;
                transform:translate(-50%,-50%);
                width: 1.25em;
                height: auto;
                line-height: 0;
                content: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16'%3e%3cpath fill='none' stroke='rgba%280,0,0,.5%29' stroke-linecap='round' stroke-linejoin='round' stroke-width='2' d='M5 14l6-6-6-6'/%3e%3c/svg%3e");
                transition: transform .35s ease;
                transform-origin: 50% 50%; */
            }

            .btn-toggle[aria-expanded="true"] {
                color: rgba(0, 0, 0, .85);
            }

            .btn-toggle[aria-expanded="true"]::before {
                /* transform: rotate(45deg); */
            }

            .link-dark {
                color: #212529;
            }

            .btn-toggle-nav a {
                display: inline-flex;
                padding: 0.1875rem 0.5rem;
                margin-top: 0.125rem;
                margin-left: 1.25rem;
                text-decoration: none;
            }

            a {
                color: #635c5c;
                text-decoration: none;
            }

            a:hover {
                color:black;
            }

            .d-flex {
                font-weight: bolder;
            }

            form.search-box .input-form::before {
                content: unset;
            }

            .item:nth-child(1) { flex-grow: 12; }
			.item:nth-child(2) { flex-grow: 1; }

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

            form.search-box .search-form {
                width: auto;
            }
        </style>
   </head>

   <body>	   
	    <%------------ header section  ------------%>
	    <jsp:include page="../fix/header.jsp" />
    
        <!-- 메인 -->
        <main>
            <%-- 본문의 상단 --%>
            <div class="container">
            	<c:if test="${isTeacher == true}">
	            	<div class="d-flex flex-row-reverse pt-10">
	            		<a class="btn head-btn2" href="/writeCourse">강의 생성</a>
	            	</div>
	            	<div class="job-listing-area pt-40 pb-120">
	            </c:if>
            	<c:if test="${isTeacher == false}">
            		<div class="job-listing-area pt-120 pb-120">
            	</c:if>
                    <div class="row">
                        <!-- Left content -->
                        <div class="col-lg-3 blog_right_sidebar">
                            <aside class="single_sidebar_widget post_category_widget">
                                <ul class="list cat-list">
                                	<li>
                                        <a href="/courses/it-programming">
                                            <p class="d-flex">ALL</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/courses/it-programming/web-dev">
                                            <p class="d-flex">웹 개발</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/courses/it-programming/front-end" class="d-flex">
                                            <p class="d-flex">프론트엔드</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/courses/it-programming/back-end" class="d-flex">
                                            <p class="d-flex">백엔드</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/courses/it-programming/mobile-app" class="d-flex">
                                            <p class="d-flex">모바일 앱 개발</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/courses/it-programming/game-dev" class="d-flex">
                                            <p class="d-flex">게임 개발</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/courses/it-programming/programming-lang" class="d-flex">
                                            <p class="d-flex">프로그래밍 언어</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/courses/it-programming/algorithm" class="d-flex">
                                            <p class="d-flex">알고리즘·자료구조</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/courses/it-programming/database-dev" class="d-flex">
                                            <p class="d-flex">데이터베이스</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/courses/it-programming/devops-infra" class="d-flex">
                                            <p class="d-flex">데브옵스-인프라</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/courses/it-programming/certificate-programming" class="d-flex">
                                            <p class="d-flex">자격증</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/courses/it-programming/programming-tool" class="d-flex">
                                            <p class="d-flex">개발 도구</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/courses/it-programming/embedded-iot" class="d-flex">
                                            <p class="d-flex">임베디드-IoT</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/courses/it-programming/dev-data-science" class="d-flex">
                                            <p class="d-flex">데이터 사이언스</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/courses/it-programming/desktop-application" class="d-flex">
                                            <p class="d-flex">데스크톱 앱 개발</p>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="/courses/it-programming/dev-besides" class="d-flex">
                                            <p class="d-flex">교양-기타</p>
                                        </a>
                                    </li>
                                </ul>
                            </aside>
                        </div>
                        <!-- Right content -->
                        <div class="col-lg-9">
                            <section class="featured-job-area">
                                <div class="container">
                                    <!-- Count of Job list Start -->
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="count-job mb-35">
                                                <span>${totalCount} course found</span>
                                                
                                                <div class="select-job-items">
                                                    <span>filter</span>
                                                    <form>
	                                                    <select onchange="changeOrder()" name="order">
	                                                        <option <c:if test="${order == 'none'}">selected</c:if> value="none">입문자용</option>
	                                                        <option <c:if test="${order == 'like'}">selected</c:if> value="like">초급</option>
	                                                        <option <c:if test="${order == 'reply'}">selected</c:if> value="reply">중급이상</option>
	                                                    </select>
                                                    </form>
                                                </div>
                                                
                                                <div class="select-job-items">
                                                    <span>Sort by</span>
                                                    <form>
	                                                    <select onchange="changeOrder()" name="order">
	                                                        <option <c:if test="${order == 'none'}">selected</c:if> value="none">None</option>
	                                                        <option <c:if test="${order == 'like'}">selected</c:if> value="like">좋아요순</option>
	                                                        <option <c:if test="${order == 'reply'}">selected</c:if> value="reply">리뷰순</option>
	                                                        <option <c:if test="${order == 'star'}">selected</c:if> value="star">평점순</option>
	                                                        <option <c:if test="${order == 'price'}">selected</c:if> value="price">가격순</option>
	                                                    </select>
                                                    </form>
                                                </div>
                                                <!--  Select job items End-->
                                            </div>
                                        </div>
                                    </div>

                                    <%-- 강의 검색 입력 폼 --%>
                                    <form class="search-box mb-5">
                                        <div class="input-form item" >
                                            <input type="text" name="title" value="${title}" placeholder="강의 제목 검색" tabindex="0">
                                        </div>
                                        <div class="search-form item">
                                        	<button onlick="searchByCourseTitle()" class="btn w-100 h-100">검색</button>
                                        </div>	
                                    </form>

                                    <%-- 강의 리스트 출력 부분 --%>
                                    <div class="row row-cols-4 mb-3">
                                    	<c:forEach var="list" items="${clist}">
	                                        <div class="col">
	                                            <div class="card shadow-sm mb-3 d-flex justify-content-center">
	                                            	<img src="<c:url value='${list.img_path}'/>" style="padding:5px; height:150px; object-fit: contain;"/>
	                                                <div class="card-body">
	                                                    <div id="course-title" class="card-text" style="display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; height:50px; overflow:hidden; text-overflow:ellipsis">
	                                                    	<a href="/courses/${list.oli_no}" class="stretched-link">${list.title}</a>
	                                                    </div>
	                                                    <div id="teacher-name" class="card-text">${courseService.getTeacherInfo(list.olt_no).name}</div>
	                                                    <div class="stars-outer">
	                                                        <div class="stars-inner" style="width:${courseService.getCourseStarAvg(list.oli_no)*20}%"></div>
	                                                    </div>
	                                                    <span class="number-rating">${courseService.getCourseReplys(list.oli_no).size()}</span>
	                                                    <div id="course-price" class="card-text">₩${list.price}</div>
	                                                </div>
	                                            </div>
	                                        </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </section>
                            
                            <%-- 페이지 번호 --%>
                            <div class="pagination-area pb-115 text-center mt-5">
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
                </div>
            </div>
        </main>
    	
    	<script>
    		var url = new URL(window.location);
    		
	       	
	        $(function() {
	        	
	        });
	        
    		function changeOrder() {
    			
    			console.log($("#level").val())
    			console.log($("#charge").val())
    			console.log($("#s").val())
    			console.log($("#order").val())
    			console.log($("#tags").val())
    			
				url.searchParams.set('level', level);
				url.searchParams.set('charge', charge);
				url.searchParams.set('s', search);
				url.searchParams.set('order', order);
				url.searchParams.set('tags', tags);
				url.searchParams.set('page', page);
    			$.ajax({
    				url: url.toString(),
    				data: 'get',
    				dataType: 'html',
    				success: function(html) {
    					var content = $(html).find("#content");
    					var pagination = $(html).find("#pagination");
    					$("#content").html(content);
    					$("#pagination").html(pagination);
    					history.pushState(null, null, url);
    				}
    			});
    		}
    	</script>
		<jsp:include page="../fix/footer.jsp" />
        <!-- JavaScript Bundle with Popper -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </body>
</html>