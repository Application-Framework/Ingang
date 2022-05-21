<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
    <footer>
        <!-- Footer Start-->
        <div class="footer-area footer-bg footer-padding">
            <div class="container">
                <div class="row d-flex justify-content-between">
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
                       <div class="single-footer-caption mb-50">
                         <div class="single-footer-caption mb-30">
                             <div class="footer-tittle">
                                 <h4>About Us</h4>
                                 <div class="footer-pera">
                                 	<ul>
	                                    <li><a href="#">회사소개</a></li>
	                                    <li><a href="#">CI</a></li>
	                                    <li><a href="#">찾아오시는 길</a></li>
                                	</ul>
                                </div>
                             </div>
                         </div>

                       </div>
                    </div>
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-5">
                        <div class="single-footer-caption mb-50">
                            <div class="footer-tittle">
                                <h4>Contact Info</h4>
                                <ul>
                                    <li>
                                    <p>Address :Your address goes
                                        here, your demo address.</p>
                                    </li>
                                    <li><a href="#">Phone : +8880 44338899</a></li>
                                    <li><a href="#">Email : info@colorlib.com</a></li>
                                </ul>
                            </div>

                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-5">
                        <div class="single-footer-caption mb-50">
                            <div class="footer-tittle">
                                <h4>Important Link</h4>
                                <ul>
                                    <li><a href="#"> View Project</a></li>
                                    <li><a href="#">Contact Us</a></li>
                                    <li><a href="#">Testimonial</a></li>
                                    <li><a href="#">Proparties</a></li>
                                    <li><a href="#">Support</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-5">
                        <div class="single-footer-caption mb-50">
                            <div class="footer-tittle">
                                <h4>Newsletter</h4>
                                <div class="footer-pera footer-pera2">
                                 <p>Heaven fruitful doesn't over lesser in days. Appear creeping.</p>
                             </div>
                             <!-- Form -->
                             <div class="footer-form" >
                                 <div id="mc_embed_signup">
                                     <form target="_blank" action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
                                     method="get" class="subscribe_form relative mail_part">
                                         <input type="email" name="email" id="newsletter-form-email" placeholder="Email Address"
                                         class="placeholder hide-on-focus" onfocus="this.placeholder = ''"
                                         onblur="this.placeholder = ' Email Address '">
                                         <div class="form-icon">
                                             <button type="submit" name="submit" id="newsletter-submit"
                                             class="email_icon newsletter-submit button-contactForm"><img src="" alt=""></button>
                                         </div>
                                         <div class="mt-10 info"></div>
                                     </form>
                                 </div>
                             </div>
                            </div>
                        </div>
                    </div>
                </div>
               <!--  -->
               <div class="row footer-wejed justify-content-between">
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-6">
                        <!-- logo -->
                        <div class="footer-logo mb-20">
                        <a href="/"><img src="<c:url value=''/>" alt=""></a>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-5">
                    <div class="footer-tittle-bottom">
                        <span>5000+</span>
                        <p>Talented Hunter</p>
                    </div>
                    </div>
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-5">
                        <div class="footer-tittle-bottom">
                            <span>451</span>
                            <p>Talented Hunter</p>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-3 col-md-4 col-sm-5">
                        <!-- Footer Bottom Tittle -->
                        <div class="footer-tittle-bottom">
                            <span>568</span>
                            <p>Talented Hunter</p>
                        </div>
                    </div>
               </div>
            </div>
        </div>
        <!-- footer-bottom area -->
        <div class="footer-bottom-area footer-bg">
            <div class="container">
                <div class="footer-border">
                     <div class="row d-flex justify-content-between align-items-center">
                         <div class="col-xl-10 col-lg-10 ">
                             <div class="footer-copy-right">
                                 <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                    Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
                             </div>
                         </div>
                     </div>
                </div>
            </div>
        </div>
        <!-- Footer End-->
    </footer>

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