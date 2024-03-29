<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title> 인강인강 </title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/favicon.ico'/>">
		
		<!-- 부트스트랩 -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
		
		<!-- CSS here -->
        <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/owl.carousel.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/flaticon.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/price_rangs.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/slicknav.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/animate.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/magnific-popup.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/fontawesome-all.min.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/themify-icons.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/slick.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/nice-select.css'/>">
        <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
        
        <!-- swiper-slide -->
        <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
		<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
		<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
</head>
	<style>
		.left-box {
			width: 300px;
		    float: left;
		}
		
		a {
			color: #ff9100;
			font-weight: bold;
		}
		
		a:hover {
			color : #b17322;
		}
		
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
		
		.white-color {
			color: white;
		}
		
		#carouselExampleIndicators {
			margin: 30px;
		}
		
		.left-text {
			padding-left: 40px;
		}
		
		.tagList {
			margin: 5px;
		}
	</style>
   <body>
	   	<%-- Preloader --%>
	    <jsp:include page="./fix/preloader.jsp" />
	   
	    <%------------ header section  ------------%>
	    <jsp:include page="./fix/header.jsp" />
     	
     	<%------------ main section  ------------%>
     	<main>
     	
        <!-- slider Area Start-->
		<!-- Mobile Menu -->
		<div class="slider-active">
				<div class="single-slider slider-height d-flex align-items-center"
				data-background="<c:url value='/resources/img/main_background.jpg'/>">
				<%-- <div class="single-slider slider-height d-flex align-items-center" style="background-color:rgb(95, 204, 255, 0.8);"> --%>
				<div class="container">
					<div class="row">
						<div class="col-xl-6 col-lg-9 col-md-10">
							<div class="hero__caption">
								<h1 class="white-color">인강인강</h1>
								<p class="white-color">인강인강이 제공하는 고퀄리티 강의영상을 경험해 보세요. <br/>
								인강인강은 오직 수강생 여러분의 편의만을 생각합니다.</p>
							</div>
						</div>
					</div>
					<!-- Search Box -->
					<div class="row">
						<div class="col-xl-8">
							<!-- form -->
							<form class="search-box" action="/mainSearch">
								<div class="select-form">
									<div class="select-itms">
										<select name="select1" >
											<option value="courses">강의</option>
											<option value="notes">노트</option>
										</select>
									</div>
								</div>
								<div class="input-form">
									<input type="text" name="keyword" placeholder="검색어를 입력해 주세요">
								</div>
								<div class="search-form">
									<button type="submit" id="searchBtn" class="btn w-100 h-100">검색</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- slider Area End-->
		<br/><br/>

		<!-- 좌측 사이드 영역 start -->
		<div class="left-box">
			<!-- Testimonial Start -->
			<div class="testimonial-area testimonial-padding">
				<div class="container">
				<center><h3>실시간 강의 평가</h3></center>
					<!-- Testimonial contents -->
					<div class="row d-flex justify-content-center">
						<div class="col-xl-8 col-lg-8 col-md-10">
							<div class="h1-testimonial-active dot-style">
							<c:forEach var="list" items="${courseReplylist}">
								<!-- Single Testimonial -->
								<div class="single-testimonial text-center">
									<!-- Testimonial Content -->
									<div class="testimonial-caption ">
										<!-- founder -->
										<div class="testimonial-founder  ">
											<div class="founder-img mb-30">
												<img src="<c:url value='/resources/img/testmonial/testimonial-founder.png'/>" alt=""> 
												<span>${list.m_id}</span>
												<p>${list.m_name}</p>
												<div class="stars-outer">
									                <div class="stars-inner" style="width:${list.star_rating*20}%"></div>
									            </div>
												<p>${list.title}</p>
												<p>${list.reg_date}</p>
											</div>
										</div>
										<div class="testimonial-top-cap">
											<p>“${list.content}”</p>
										</div>
									</div>
								</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Testimonial End -->
			<center>
				<h3>핫 트랜드 검색어</h3>
			</center>
			<div class="container">
				<div class="row">
					<div class="left-text">
						<c:forEach var="tagList" items="${tagList}" varStatus="status">
						<div class="list-group">
							<a href="/courses?tag=${tagList.t_name}" class="list-group-item list-group-item-action list-group-item-light">${status.count}. ${tagList.t_name}(${tagList.t_viewCnt})</a>
						</div>
					</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<!-- 좌측 사이드 영역 end -->

		<!-- 이 주의 강의 Start -->
        <div class="our-services section-pad-t30">
            <div class="container">
                <!-- Section Tittle -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-tittle text-center">
                        	<span>인강인강 수강생들이 선택한</span>
                            <h2>이 주의 강의</h2>
                        </div>
                    </div>
                </div>
                <!-- 이 주의 강의 등록 기준 : 등록일부터 7일이내 추천수 20 이상 -->
                <div class="row d-flex justify-contnet-center">
                	<c:forEach var="list" items="${crbList}">
		                <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
			                <div class="card shadow-sm mb-3">
				                <img src="<c:url value='${list.img_path}'/>" style="height:150px"/>
				                <div class="card-body">
					                <div id="course-title" class="card-text" style="display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; height:50px; overflow:hidden; text-overflow:ellipsis"><a href="/course/${list.oli_no}">${list.title}</a></div>
					                <div style="font-size: 10px;">${list.reg_date}</div>
					                <div id="teacher-name" class="card-text">${list.name}</div>
					                <div class="stars-outer">
					               		<div class="stars-inner" style="width:${list.star_avg*20}%"></div>
					                </div>
					                <span class="number-rating">(${list.star_avg})</span>
					                <div id="course-price" class="card-text">₩${list.price}</div>
				                </div>
			                </div>
		                </div>
                	</c:forEach>
                </div>
                <!-- More Btn -->
                <!-- Section Button -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="browse-btn2 text-center mt-50">
                            <a href="/courses" class="border-btn2">강의 전체보기</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 이 주의 강의 end -->
        
        <!-- 신규 강의 Start -->
        <div class="our-services section-pad-t30">
            <div class="container">
                <!-- Section Tittle -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-tittle text-center">
                        	<span>따끈따끈한</span>
                            <h2>신규 강의</h2>
                        </div>
                    </div>
                </div>
                <!-- 신규 강의 등록 기준 : 등록일부터 7일이내 -->
                <div class="row d-flex justify-contnet-center">
                	<c:forEach var="list" items="${newList}">
		                <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
			                <div class="card shadow-sm mb-3">
				                <img src="<c:url value='${list.img_path}'/>" style="height:150px"/>
				                <div class="card-body">
					                <div id="course-title" class="card-text" style="display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; height:50px; overflow:hidden; text-overflow:ellipsis"><a href="/course/${list.oli_no}">${list.title}</a></div>
					                <div style="font-size: 10px;">${list.reg_date}</div>
					                <div id="teacher-name" class="card-text">${list.name}</div>
					                <div class="stars-outer">
					               		<div class="stars-inner" style="width:${list.star_avg*20}%"></div>
					                </div>
					                <span class="number-rating">(${list.star_avg})</span>
					                <div id="course-price" class="card-text">₩${list.price}</div>
				                </div>
			                </div>
		                </div>
                	</c:forEach>
                </div>
                <!-- More Btn -->
                <!-- Section Button -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="browse-btn2 text-center mt-50">
                            <a href="/courses" class="border-btn2">신규 강의 전체보기</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 신규 강의 end -->
        
        <!-- 입문자용 강의 Start -->
        <!-- 입문자용 강의 end -->
        
        <!-- 이 주의 노트 start -->
        <section class="featured-job-area feature-padding">
            <div class="container">
                <!-- Section Tittle -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-tittle text-center">
                            <span>강추!!</span>
                            <h2>이 주의 노트</h2>
                        </div>
                    </div>
                </div>
                <c:forEach var="list" items="${ntbList}">
                <div class="row justify-content-center">
                    <div class="col-xl-10">
                        <!-- single-job-content -->
                        <div class="single-job-items mb-30">
                            <div class="job-items">
                                <div class="company-img">
                                    <a href="/notes/${list.n_no}"><img src="<c:url value='${courseService.getCourseDetail(list.oli_no).img_path}'/>" style="height:150px"/></a>
                                </div>
                                <div class="job-tittle">
                                    <a href="/notes/${list.n_no}"><h4>${list.title}</h4></a>
                                    <ul>
                                        <li>${list.m_name}</li>
                                        <li>${list.price} 원</li>
                                    </ul>
                                </div>
                            </div>
                            <div class="items-link f-right">
                                <a href="/notes/${list.n_no}">상세보기</a>
                                <span>${list.reg_date}</span>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="browse-btn2 text-center mt-50">
                            <a href="/notes" class="border-btn2">노트 전체보기</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- 이 주의 노트 end -->
        
        

		<div class="container">
			<div class="text-center">
            	<h2>이벤트</h2>
            </div>
			<!-- 이벤트 슬라이드 start -->
			<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
					<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
				</div>
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img src="../resources/img/event/hongil.jpg" height="300px;" onclick="location.href='/'" style="width: 100px;" class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item">
						<img src="../resources/img/event/hongil.jpg" height="300px;" onclick="location.href='/'" style="width: 100px;" class="d-block w-100" alt="...">
					</div>
					<div class="carousel-item">
						<img src="../resources/img/event/hongil.jpg" height="300px;" onclick="location.href='/'" style="width: 100px;" class="d-block w-100" alt="...">
					</div>
				</div>
				<button class="carousel-control-prev" type="button"	data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
			<!-- 이벤트 슬라이드 end -->
		</div>


	</main>
     	
     	<%------------ footer section  ------------%>
     	<jsp:include page="./fix/footer.jsp" />
     
     	<%------------ All JS Custom Plugins Link Here here -----------%>
        <script src="<c:url value='/resources/js/vendor/modernizr-3.5.0.min.js'/>"></script>
		<%-- Jquery, Popper, Bootstrap --%>
		<script src="<c:url value='/resources/js/vendor/jquery-1.12.4.min.js'/>"></script>
        <script src="<c:url value='/resources/js/popper.min.js'/>"></script>
        <script src="<c:url value='/resources/js/bootstrap.min.js'/>"></script>
	    <%-- Jquery Mobile Menu --%>
        <script src="<c:url value='/resources/js/jquery.slicknav.min.js'/>"></script>

		<%-- Jquery Slick , Owl-Carousel Plugins --%>
        <script src="<c:url value='/resources/js/owl.carousel.min.js'/>"></script>
        <script src="<c:url value='/resources/js/slick.min.js'/>"></script>
        <script src="<c:url value='/resources/js/price_rangs.js'/>"></script>
        
		<%-- One Page, Animated-HeadLin --%>
        <script src="<c:url value='/resources/js/wow.min.js'/>"></script>
		<script src="<c:url value='/resources/js/animated.headline.js'/>"></script>
        <script src="<c:url value='/resources/js/jquery.magnific-popup.js'/>"></script>

		<%-- Scrollup, nice-select, sticky --%>
        <script src="<c:url value='/resources/js/jquery.scrollUp.min.js'/>"></script>
        <script src="<c:url value='/resources/js/jquery.nice-select.min.js'/>"></script>
		<script src="<c:url value='/resources/js/jquery.sticky.js'/>"></script>
        
        <%-- contact js --%>
        <script src="<c:url value='/resources/js/contact.js'/>"></script>
        <script src="<c:url value='/resources/js/jquery.form.js'/>"></script>
        <script src="<c:url value='/resources/js/jquery.validate.min.js'/>"></script>
        <script src="<c:url value='/resources/js/mail-script.js'/>"></script>
        <script src="<c:url value='/resources/js/jquery.ajaxchimp.min.js'/>"></script>
        
		<%-- Jquery Plugins, main Jquery --%>
        <script src="<c:url value='/resources/js/plugins.js'/>"></script>
        <script src="<c:url value='/resources/js/main.js'/>"></script>
       
        <script>
			var target = document.getElementById("select1");
			//target.options[target.selectedIndex].text
			
			$('#searchBtn').click(function() {
				var select1 = $('#select1').val();
				var urlSelect = "";
				
				if(select1 == "courses") {
					urlSelect : "/courses";
				} else {
					urlSelect : "/notes";
				}
				
				$.ajax({
					url : urlSelect,
					type : "get",
					data : select1,
					success : function(data) {
						if (data != 1) {
							swal({
								title : "오류",
								text : "오류",
								icon : "error" ,
								timer : 3000
							})
						} else {
							window.location.href=urlSelect+"?keyword";
						}
						
					}
				}); // ajax 끝
			});
		</script>
    </body>
</html>