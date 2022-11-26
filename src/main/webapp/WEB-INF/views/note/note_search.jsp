<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
         <title> 노트 검색 </title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/favicon.ico'/>">

		<!-- CSS here -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.css">
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

		<script src="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
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
            .choices__list--dropdown {
            	z-index: 999;
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
           		<div class="job-listing-area pt-120 pb-120">
                    <div class="row">
	                    <%-- 강의 카테고리 --%>
	                    <div class="col-lg-3 sidebar">
							<aside class="single_sidebar_widget post_category_widget">
								<nav class="navbar bg-light navbar-light">
									<div class="navbar-nav w-100">
										<div class="nav-item dropdown">
											<a href="/notes" class="dropdown-item">전체 노트</a>
										</div>
										<%-- 메인 카테고리 --%>
										<c:forEach var="mainType" items="${typeService.getMainTypeList()}" >
											<div class="nav-item dropdown">
												<a href="#" class="ps-3 nav-link dropdown-toggle" 
												data-bs-toggle="dropdown">${mainType.main_type_name}</a>
												<div class="dropdown-menu bg-transparent border-0">
													<a href="/notes/${mainType.main_type_abbr}" class="dropdown-item">ALL</a>
													<%-- 서브 카테고리 --%>
													<c:forEach var="subType" items="${typeService.getSubTypeListOfMainType(mainType.main_type_no)}">
														<a href="/notes/${mainType.main_type_abbr}/${subType.sub_type_abbr}" class="dropdown-item">${subType.sub_type_name}</a>
													</c:forEach>
												</div>
											</div>
										</c:forEach>
									</div>
								</nav>
							</aside>
                        </div>
                        
                        
                        <div class="col-lg-9">
                            <section class="featured-job-area">
                                <div class="container">
                                    <%-- 검색 필터 --%>
									<div class="d-flex align-items-center mb-3">
	                                	<div class="w-100">
	                                        <span>${totalCount} note found</span>
	                                    </div>
	                                    
	                                    <div class="flex-shrink-1 pe-2 text-center">
	                                        <span>요금</span>
	                                        <select onchange="refreshPage()" id="charge" name="charge" class="nice-select" multiple>
	                                            <option <c:if test="${order == 'free'}">selected</c:if> value="free">무료</option>
	                                            <option <c:if test="${order == 'paid'}">selected</c:if> value="paid">유료</option>
	                                        </select>
	                                    </div>
	                                   
	                                    <div class="flex-shrink-1 text-center">
	                                        <span>Sort by</span>
	                                         <select onchange="refreshPage()" id="order" name="order" class="nice-select">
	                                             <option <c:if test="${order == 'none'}">selected</c:if> value="none">None</option>
	                                             <option <c:if test="${order == 'like'}">selected</c:if> value="like">좋아요순</option>
	                                             <option <c:if test="${order == 'reply'}">selected</c:if> value="reply">리뷰순</option>
	                                             <option <c:if test="${order == 'star'}">selected</c:if> value="star">평점순</option>
	                                             <option <c:if test="${order == 'price'}">selected</c:if> value="price">가격순</option>
	                                         </select>
	                                    </div>
	                                    <!--  Select job items End-->
                                    </div>
									
									
                                    <%-- 노트 검색 입력 폼 --%>
                                    <form action="${nowURL}" class="search-box mb-3">
                                        <div class="input-form item" >
                                            <input type="text" name="s" id="s" value="${s}" placeholder="노트 제목 검색" tabindex="0">
                                        </div>
                                        <div class="search-form item">
                                        	<button type="submit" class="btn w-100 h-100">검색</button>
                                        </div>
                                    </form>
									
									<%-- 태그 --%>
									<div class="row mb-5">
										<select onchange="refreshPage()" id="tags" placeholder="태그 검색" multiple required>
										  <c:forEach var="tag" items="${tagList}">
											  <option value="${tag.tag_abbr}">${tag.tag_name}</option>
										  </c:forEach>
										</select>
					    			</div>
									
                                    <%-- 노트 리스트 출력 부분 --%>
                                    <div id="content" class="row row-cols-4 mb-3">
                                    	<c:forEach var="list" items="${nlist}">
	                                        <div class="col">
	                                            <div class="card shadow-sm mb-3">
	                                            	<img src="<c:url value='${courseService.getCourseDetail(list.oli_no).img_path}'/>" style="height:150px"/>
	                                                <div class="card-body">
	                                                    <div id="course-title" class="card-text" style="display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; height:50px; overflow:hidden; text-overflow:ellipsis">
	                                                    	<a href="/note/${list.n_no}" class="stretched-link">${list.title}</a>
	                                                    </div>
	                                                    <div id="teacher-name" class="card-text">${memberService.getNameByM_no(list.m_no)}</div>
	                                                    <div class="stars-outer">
	                                                        <div class="stars-inner" style="width:${list.star_avg*20}%"></div>
	                                                    </div>
	                                                    <span class="number-rating">${list.star_avg}</span>
	                                                    <div id="course-price" class="card-text">₩${list.price}</div>
	                                                </div>
	                                            </div>
	                                        </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </section>
                            
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
                </div>
            </div>
        </main>
    	
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
				var charge = $("#charge").val();
				var s = search;
				var order = $("#order").val();
				var tags = $("#tags").val()
				/* console.log(level);
				console.log(charge);
				console.log(s);
				console.log(order);
				console.log(tags);
				console.log(${paging.pageNo}); */
				
				if(charge.length != 0)
					url.searchParams.set('charge', charge);
				if(s != "")
					url.searchParams.set('s', s);
				if(order.length != 0)
					url.searchParams.set('order', order);
				if(tags.length != 0)
					url.searchParams.set('tags', tags);
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
		<jsp:include page="../fix/footer.jsp" />
    </body>
</html>