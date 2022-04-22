<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title> 마이페이지입니다 </title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/favicon.ico'/>">
		
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
   </head>

   <body>
       	<%-- Preloader --%>
	    <jsp:include page="./fix/preloader.jsp" />
	   
	    <%------------ header section  ------------%>
	    <jsp:include page="./fix/header.jsp" />
    <main>
        <!-- Hero Area Start-->
        <div class="slider-area ">
        <div class="single-slider section-overly slider-height2 d-flex align-items-center" data-background="assets/img/hero/about.jpg">
            <div class="container">
                <div class="row">
                    <div class="col-xl-12">
                        <div class="hero-cap text-center">
                            <h2>About us</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
        <!-- Hero Area End -->
        <!-- Support Company Start-->
        <div class="support-company-area fix section-padding2">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-xl-6 col-lg-6">
                        <div class="right-caption">
                            <!-- Section Tittle -->
                            <div class="section-tittle section-tittle2">
                                <span>What we are doing</span>
                                <h2>24k Talented people are getting Jobs</h2>
                            </div>
                            <div class="support-caption">
                                <p class="pera-top">Mollit anim laborum duis au dolor in voluptate velit ess cillum dolore eu lore dsu quality mollit anim laborumuis au dolor in voluptate velit cillum.</p>
                                <p>Mollit anim laborum.Duis aute irufg dhjkolohr in re voluptate velit esscillumlore eu quife nrulla parihatur. Excghcepteur signjnt occa cupidatat non inulpadeserunt mollit aboru. temnthp incididbnt ut labore mollit anim laborum suis aute.</p>
                                <a href="about.html" class="btn post-btn">Post a job</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6">
                        <div class="support-location-img">
                            <img src="assets/img/service/support-img.jpg" alt="">
                            <div class="support-img-cap text-center">
                                <p>Since</p>
                                <span>1994</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Support Company End-->
        <!-- How  Apply Process Start-->
        <div class="apply-process-area apply-bg pt-150 pb-150" data-background="assets/img/gallery/how-applybg.png">
            <div class="container">
                <!-- Section Tittle -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-tittle white-text text-center">
                            <span>Apply process</span>
                            <h2> How it works</h2>
                        </div>
                    </div>
                </div>
                <!-- Apply Process Caption -->
                <div class="row">
                    <div class="col-lg-4 col-md-6">
                        <div class="single-process text-center mb-30">
                            <div class="process-ion">
                                <span class="flaticon-search"></span>
                            </div>
                            <div class="process-cap">
                                <h5>1. Search a job</h5>
                            <p>Sorem spsum dolor sit amsectetur adipisclit, seddo eiusmod tempor incididunt ut laborea.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="single-process text-center mb-30">
                            <div class="process-ion">
                                <span class="flaticon-curriculum-vitae"></span>
                            </div>
                            <div class="process-cap">
                                <h5>2. Apply for job</h5>
                            <p>Sorem spsum dolor sit amsectetur adipisclit, seddo eiusmod tempor incididunt ut laborea.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="single-process text-center mb-30">
                            <div class="process-ion">
                                <span class="flaticon-tour"></span>
                            </div>
                            <div class="process-cap">
                                <h5>3. Get your job</h5>
                            <p>Sorem spsum dolor sit amsectetur adipisclit, seddo eiusmod tempor incididunt ut laborea.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Testimonial End -->
        <!-- Online CV Area Start -->
        <div class="online-cv cv-bg section-overly pt-90 pb-120"  data-background="assets/img/gallery/cv_bg.jpg">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-xl-10">
                        <div class="cv-caption text-center">
                            <p class="pera1">FEATURED TOURS Packages</p>
                            <p class="pera2"> Make a Difference with Your Online Resume!</p>
                            <a href="#" class="border-btn2 border-btn4">Upload your cv</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Online CV Area End-->
    
        <!-- Blog Area Start -->
        <div class="home-blog-area blog-h-padding">
            <div class="container">
                <!-- Section Tittle -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-tittle text-center">
                            <span>Our latest blog</span>
                            <h2>Our recent news</h2>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xl-6 col-lg-6 col-md-6">
                        <div class="home-blog-single mb-30">
                            <div class="blog-img-cap">
                                <div class="blog-img">
                                    <img src="assets/img/blog/home-blog1.jpg" alt="">
                                    <!-- Blog date -->
                                    <div class="blog-date text-center">
                                        <span>24</span>
                                        <p>Now</p>
                                    </div>
                                </div>
                                <div class="blog-cap">
                                    <p>|   Properties</p>
                                    <h3><a href="single-blog.html">Footprints in Time is perfect House in Kurashiki</a></h3>
                                    <a href="#" class="more-btn">Read more »</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6">
                        <div class="home-blog-single mb-30">
                            <div class="blog-img-cap">
                                <div class="blog-img">
                                    <img src="assets/img/blog/home-blog2.jpg" alt="">
                                    <!-- Blog date -->
                                    <div class="blog-date text-center">
                                        <span>24</span>
                                        <p>Now</p>
                                    </div>
                                </div>
                                <div class="blog-cap">
                                    <p>|   Properties</p>
                                    <h3><a href="single-blog.html">Footprints in Time is perfect House in Kurashiki</a></h3>
                                    <a href="#" class="more-btn">Read more »</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Blog Area End -->

    </main>
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
        
    </body>
</html>