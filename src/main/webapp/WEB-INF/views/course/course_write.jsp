<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">	
	<title>강의 소개 작성</title>
	<link rel="shortcut icon" type="image/x-icon" href="<c:url value='/resources/img/favicon.ico'/>">
	
	<%-- 부트스트랩 아이콘 --%>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
	
	<%-- 템플릿에 사용되는 css --%>
	<link rel="stylesheet" href="<c:url value='/resources/css/animate.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/fontawesome-all.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/themify-icons.css'/>"> 
	
	<%-- for select option drop box --%>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.css">
	<script src="https://cdn.jsdelivr.net/gh/bbbootstrap/libraries@main/choices.min.js"></script>
	
	<%-- summernote section --%>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
	<%-- popper.js는 항상 bootstrap 위에 와야 함 --%>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	
	<%-- Bootstrap css, js --%>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

	<%-- summernote bootsrap 필요 없는 버전 --%>
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
    <%-- summernote end --%>
    
    <%-- 템플릿 css / Boostrap css 아래에 있어야 적용됨 --%>
    <link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
	<style>
		a {
			color: #635c5c;
			text-decoration: none;
		}
	</style>
	<script>
		// 강의 동영상 슬롯 동적 추가
	    var cnt = 0;
	    var changedForm = false;
	    
	    function addVideoSlot(title, file_name) {
	    	cnt = cnt + 1;
	    	$('#videoSection').append("<div class='row ps-4 pb-2' id='v_" + cnt + "'><div class='col-2'><input type='text' class='form-control' name='video_titles' value='" + title + "' required/></div><div class='col-10'><input type='text' class='form-control' name='video_paths' value='" + file_name + "' required/></div></div>");
	    	changedForm = true;
	    }
	    
	    function removeVideoSlot() {
	    	if(cnt == 1) return;
	    	$('#v_'+cnt).remove();
	    	cnt = cnt - 1;
	    	changedForm = true;
	    }
	</script>
</head>
<body>
	 <%------------ header section  ------------%>
    <jsp:include page="../fix/header.jsp" />
    
    <div class="container">
    	<form id="frmCourse" action="${actionURL}" method="post" enctype="multipart/form-data">
    		<input type="hidden" name="pageNo" value="${course.oli_no}"/>
    		<div class="row mb-1">
   				<label class="col-sm-2 col-form-label fs-5">강의명</label>
   				<div class="col-sm-10">
    				<input type="text" class="form-control" name="title" value="${course.title}" required/>
    			</div>
    		</div>
    		
    		<div class="row mb-2">
   				<label class="col-sm-2 col-form-label fs-5">강의 분야</label>
   				<div class="col-sm-10">
					<select name="tags" id="choices-multiple-remove-button" multiple required>
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
    		
    		<div class="row mb-1">
   				<label class="col-sm-2 col-form-label fs-5">표지</label>
   				<div class="col-sm-10">
		    		<input type="file" class="form-control" name="thumbnail" accept="image/*" onchange="loadFile(event)" <c:if test="${course.img_path == null}">required</c:if>/>
	    		 	<img id="thumbnail" src="<c:url value='${course.img_path}'/>" width="200px"/>
    			</div>
    		</div>
	    		
    		<div class="row mb-4">
    			<textarea class="summernote" id="content" name="content" rows="10">${course.content}</textarea>
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
    				<script>
	    				addVideoSlot('', '');
	    			</script>
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
    			<input type="button" id="btnSubmit" class="col-sm-2 btn head-btn1" value="저장"/>
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
    
    <script>
	   	var submitted = false;
	    
	   	// document start
	   	$(function() {
	    	// select option drop box 옵션 설정
	    	var multipleCancelButton = new Choices('#choices-multiple-remove-button', {
	            removeItemButton: true,
	            maxItemCount:5,
	            searchResultLimit:10,
	            //renderChoiceLimit:10
	        }); 
	    	
	    	// summernote 옵션 설정
			$('.summernote').summernote({
				placeholder: 'write the text',
				tabsize: 4,
				height: 500,
				toolbar: [
					['style', ['style']],
					['font', ['bold', 'underline', 'clear']],
					['color', ['color']],
					['para', ['ul', 'ol', 'paragraph']],
					['table', ['table']],
					['insert', ['link', 'picture', 'video']],
					['view', ['codeview', 'help']]
				],
				codemirror: { // codemirror options 
			    	theme: 'monokai',
					mode: 'htmlmixed',
					lineNumbers: 'true'
			  	},
				callbacks : { 
					onImageUpload : function(files, editor, welEditable) {
						// 파일 업로드(다중업로드를 위해 반복문 사용)
						for (var i = files.length - 1; i >= 0; i--) {
							uploadSummernoteImageFile(files[i], this);
						}
					},
					onPaste: function (e) {
						var clipboardData = e.originalEvent.clipboardData;
						if (clipboardData && clipboardData.items && clipboardData.items.length) {
							var item = clipboardData.items[0];
							if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
								e.preventDefault();
							}
						}
					}
				}
			});
			
			// input, select에 change event가 일어날 경우
		    $(document).on("change", "input, select, textarea, summernote.change", function() {
		    	console.log("변경");
		    	changedForm  = true;
	    	});
			
			// summernote가 변경된 경우
		    $(document).on("summernote.change", ".summernote", function() {
		    	console.log("변경");
		    	changedForm  = true;
	    	});
			
		});
	   	
	    // 등록 버튼 눌렀을 때 폼 검증
	    $("#btnSubmit").click(function() {
	    	var form = document.getElementById('frmCourse');
	    	for(var i=0; i < form.elements.length; i++){
    	      if(form.elements[i].value === '' && form.elements[i].hasAttribute('required')){
    	        alert('There are some required fields!');
    	        return false;
    	      }
    	    }
	    	
	    	if ($('.summernote').summernote('isEmpty')) {
	    		alert('editor content is empty');
	    		return false;
	    	}
	    	submitted = true;
	    	form.submit();
	    });
	    
	 	
	    
	    // 임시 이미지 등록
		function uploadSummernoteImageFile(file, editor) {
			data = new FormData();
			data.append("file", file);
			$.ajax({
				data : data,
				type : "POST",
				url : "/courseUploadSummernoteImageFile",
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
				success : function(data) {
					$(editor).summernote('insertImage', data.url);
				}
			});
		}
		
		// 썸네일 미리보기
		var loadFile = function(event) {
		    var thumbnail = document.getElementById('thumbnail');
		    thumbnail.src = URL.createObjectURL(event.target.files[0]);
		    thumbnail.onload = function() {
		      URL.revokeObjectURL(thumbnail.src) // free memory
		    }
	  	};
	  	
		// 페이지가 unload 되기 전에 실행되는 이벤트
		$(window).on("beforeunload", function() {
			if(!submitted && changedForm)
			{
				return "저장하지 않고 페이지를 떠나시겠습니까";
			}
		});
		
		// Back Forward Cache로 브라우저가 로딩될 경우 혹은 브라우저 뒤로가기 했을 경우 폼 비우기
	    $(window).bind("pageshow", function(event) {
	    	if (event.persisted || (window.performance && window.performance.navigation.type == 2)) {
	    		// rewriteCourse라면 reload 안하기
	    		<c:if test="${course != null}">
	    			location.reload();
	    		</c:if>
       		}
	    });
    </script>
    
</body>
</html>