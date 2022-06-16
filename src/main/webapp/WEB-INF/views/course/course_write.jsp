<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>강의 소개 작성</title>
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/favicon.ico'/>">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.css">
	
	<link rel="stylesheet" href="<c:url value='/resources/css/animate.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/fontawesome-all.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/themify-icons.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.js"></script>
	
	<style>
		a {
			color: #635c5c;
			text-decoration: none;
		}
		
		a:hover {
			color: #000000; 
		}
		
	</style>
	
	<script>
	    $(document).ready(function(){
	        var multipleCancelButton = new Choices('#choices-multiple-remove-button', {
	            removeItemButton: true,
	            maxItemCount:5,
	            searchResultLimit:10,
	            //renderChoiceLimit:10
	        }); 
	    });
	    
	    var cnt;
	    <c:if test="${videoList == null}">
		    cnt = 1;
	    </c:if>
	    <c:if test="${videoList != null}">
		    cnt = 0;
	    </c:if>
	    
	    
	    function addVideoSlot(title, file_name) {
	    	cnt = cnt + 1;
	    	$('#videoSection').append("<div class='row ps-4 pb-2' id='v_" + cnt + "'><div class='col-2'><input type='text' class='form-control' name='video_titles' value='" + title + "' required/></div><div class='col-10'><input type='text' class='form-control' name='video_paths' value='" + file_name + "' required/></div></div>");
	    }
	    
	    function removeVideoSlot() {
	    	if(cnt == 1) return;
	    	$('#v_'+cnt).remove();
	    	cnt = cnt - 1;
	    }
	    
    </script>
</head>
<body>
	 <%------------ header section  ------------%>
    <jsp:include page="../fix/header.jsp" />
    
    <div class="container">
    	<form <c:if test="${course == null}">action="/courses/submitCourse"</c:if>
    		  <c:if test="${course != null}">action="/courses/updateCourse"</c:if> method="post" enctype="multipart/form-data">
    		<input type="hidden" name="pageNo" value="${course.oli_no}"/>
    		<div class="row mb-1">
   				<label class="col-sm-2 col-form-label fs-5">강의명</label>
   				<div class="col-sm-10">
    				<input type="text" class="form-control" name="title" value="${course.title}" equired/>
    			</div>
    		</div>
    		
    		<div class="row mb-2">
   				<label class="col-sm-2 col-form-label fs-5">강의 분야</label>
   				<div class="col-sm-10">
					<select name="tags" id="choices-multiple-remove-button" multiple>
					  <option <c:if test="${courseService.containsInTagList(tagList,'웹 개발') == true}">selected</c:if> value="웹 개발">웹 개발</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'프론트엔드') == true}">selected</c:if> value="프론트엔드">프론트엔드</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'백엔드') == true}">selected</c:if> value="백엔드">백엔드</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'C') == true}">selected</c:if> value="C">C</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'C++') == true}">selected</c:if> value="C++">C++</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'C#') == true}">selected</c:if> value="C#">C#</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'HTML') == true}">selected</c:if> value="HTML">HTML</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'Javascript') == true}">selected</c:if> value="Javascript">Javascript</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'Python') == true}">selected</c:if> value="Python">Python</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'CSS') == true}">selected</c:if> value="CSS">CSS</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'PHP') == true}">selected</c:if> value="PHP">PHP</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'Ruby') == true}">selected</c:if> value="Ruby">Ruby</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'데이터베이스') == true}">selected</c:if> value="데이터베이스">데이터베이스</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'알고리즘') == true}">selected</c:if> value="알고리즘">알고리즘</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'모바일 앱 개발') == true}">selected</c:if> value="모바일 앱 개발">모바일 앱 개발</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'AI') == true}">selected</c:if> value="AI">AI</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'보안') == true}">selected</c:if> value="보안">보안</option>
					  <option <c:if test="${courseService.containsInTagList(tagList,'기타') == true}">selected</c:if> value="기타">기타</option>
					</select>
    			</div>
    		</div>
    		
    		<div class="row mb-1">
   				<label class="col-sm-2 col-form-label fs-5">가격</label>
   				<div class="col-sm-2">
    				<input type="number" class="form-control" name="price" value="${course.price}" required/>
    			</div>
    		</div>
    		
    		<c:if test="${course == null}">
	    		<div class="row mb-1">
	   				<label class="col-sm-2 col-form-label fs-5">표지</label>
	   				<div class="col-sm-10">
			    		<input type="file" class="form-control" name="thumbnail" value="${course.img_path}" required/>
	    			</div>
	    		</div>
    		</c:if>
    		<div class="row mb-4">
    			<textarea class="form-control fs-5" name="content" placeholder="강의 소개 내용" rows="10">${course.content}</textarea>
    		</div>
    		
    		<div class="row mb-4" id="videoSection">
    			<div class="fs-4 mb-2">강의 동영상</div>
    			<div class="row ps-4 pb-2">
    				<div class="col-2 d-flex align-items-center">
    					<label>동영상 제목</label>
    				</div>
    				<div class="col-8 d-flex align-items-center">
    					<label>주소</label>
    				</div>
    				<div class="col-1 fs-2">
    					<a href="javascript:;" onclick="addVideoSlot('', '')"><i class="bi bi-plus-circle"></i></a>
    				</div>
    				<div class="col-1 fs-2">
    					<a href="javascript:;" onclick="removeVideoSlot()"><i class="bi bi-dash-circle"></i></a>
    				</div>
    			</div>
    			
    			<c:if test="${videoList == null}">
	    			<div class="row ps-4 pb-2">
	    				<div class="col-2">
	    					<input type="text" class="form-control" name="video_titles" required/>
	    				</div>
	    				<div class="col-10">
	    				 	<input type="text" class="form-control" name="video_paths" required/>
	    				 </div>
	    			</div>
    			</c:if>
    			
    			<c:if test="${videoList != null}">
    				<script>
	    				<c:forEach var="video" items="${videoList}">
		    					addVideoSlot('${video.title}', '${video.s_file_name}');
    					</c:forEach>
    				</script>
    			</c:if>
    		</div>
    		
    		<div class="row mb-3 d-flex flex-row-reverse">
    			<button type="submit" class="col-sm-2 btn head-btn1">저장</button>
    		</div>
    	</form>
    </div>
    
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
    
    
</body>
</html>