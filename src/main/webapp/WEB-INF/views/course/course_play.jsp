<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>${video.title}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.2/font/bootstrap-icons.css">
	<link rel="stylesheet" href="<c:url value='/resources/css/course/course_play.css'/>">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
</head>
<body>
	<div class="bg-dark d-flex vh-100" id="main">
        <div class="d-flex flex-grow-1 flex-column">
            <iframe class="w-100 h-100" width="1120" height="630" src="${videoPath}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            <div class="footer" style="height: 50px;">
            	<div class="h-100 d-flex justify-content-evenly align-items-center">
            		<div class="col-3 text-center">
		           		<c:choose>
			            	<c:when test="${olv_no == videoList[0].olv_no}">
			            		<a class="link-light">첫번째수업</a>
			            	</c:when>
			            	<c:otherwise>
			            		<a class="link-light" href="${olv_no-1}">이전 수업</a>
			            	</c:otherwise>
		            	</c:choose>
	            	</div>
	            	<div class="col-3 text-center">
		            	<c:choose>
			            	<c:when test="${olv_no == videoList[videoList.size()-1].olv_no}">
			            		<a class="link-light">마지막수업</a>
			            	</c:when>
		            		<c:otherwise>
		            			<a class="link-light" href="${olv_no+1}">다음 수업</a>
		            		</c:otherwise>
		           		</c:choose>
	           		</div> 
           		</div>
            </div>
        </div>

        <div class="slidebar" id="mySlidebar">
        </div>

        <div class="rightMenubar">   
            <a class="toggleButton" onclick="clickNav()"><i class="bi bi-list-ul"></i></a>
            <a class="toggleButton" onclick="clickNav()"><i class="bi bi-chat-square-dots-fill"></i></a>
            <a class="toggleButton" onclick="clickNav()"><i class="bi bi-sticky-fill"></i></a>
        </div>
    </div>
    
    <script>
        var status1 = false;

        function clickNav() {
            if(status1 == false ){
                document.getElementById("mySlidebar").style.width = "250px";
                status1 = true;
            }
            else if(status1 == true) {
                document.getElementById("mySlidebar").style.width = "0";
                status1 = false;
            }
        }
    </script>
</body>
</html>