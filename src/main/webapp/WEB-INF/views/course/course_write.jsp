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
	<link rel="stylesheet" href="<c:url value='/resources/css/animate.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/fontawesome-all.min.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/themify-icons.css'/>">
	<link rel="stylesheet" href="<c:url value='/resources/css/style.css'/>">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<style>
		a {
			color: #635c5c;
			text-decoration: none;
		}
	</style>
</head>
<body>
	 <%------------ header section  ------------%>
    <jsp:include page="../fix/header.jsp" />
    
    <div class="container">
    	<form action="/course/submitCourse" method="post">
    		<div class="row mb-1">
   				<label class="col-sm-2 col-form-label fs-5">강의명</label>
   				<div class="col-sm-10">
    				<input type="text" class="form-control" name="title" required/>
    			</div>
    		</div>
    		
    		<div class="row mb-1">
   				<label class="col-sm-2 col-form-label fs-5">강의 분야</label>
   				<div class="col-sm-10">
    				<input type="text" class="form-control" name="tag" placeholder="모달창으로 넣을 수 있게 수정해야 함" required/>
    			</div>
    		</div>
    		
    		<div class="row mb-1">
   				<label class="col-sm-2 col-form-label fs-5">가격</label>
   				<div class="col-sm-2">
    				<input type="text" class="form-control" name="price" required/>
    			</div>
    		</div>
    		
    		<div class="row mb-1">
   				<label class="col-sm-2 col-form-label fs-5">표지</label>
   				<div class="col-sm-10">
		    		<input type="file" class="form-control" name="thumbnail" required/>
    			</div>
    		</div>
    		
    		<div class="row mb-4">
    			<textarea class="form-control fs-5" name="content" placeholder="강의 소개 내용" rows="10"></textarea>
    		</div>
    		
    		<div class="row mb-4">
    			<div class="fs-4 mb-2">강의 동영상</div>
    			<input type="file" multiple class="form-control" name="videoList" required/>
    		</div>
    		
    		<div class="row mb-3 d-flex flex-row-reverse">
    			<button type="submit" class="col-sm-2 btn head-btn1">저장</button>
    		</div>
    	</form>
    </div>
    
    <jsp:include page="../fix/footer.jsp" />
</body>
</html>