<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">   
</head>
<body>
	<div class="container">
		<div class="text-center p-3">
			<img src='<c:url value="resources/img/sunki.png"></c:url>' style="width:250px;"/>
			<h5 class="p-3">❝ ${errorMessage} ❞</h5>
		</div>
	 </div>
 </body>
 </html>