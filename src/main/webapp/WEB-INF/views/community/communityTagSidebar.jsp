<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
</head>
<body>
	
<div class="col-lg-3">
    <div class="blog_right_sidebar">
        <aside class="single_sidebar_widget tag_cloud_widget">
			<h2 class="widget_title">인기 태그</h2>
			<ul class="list">
				<c:if test="${cbtList ne null and cbtList ne 'Empty'}">
					<c:forEach var="cbTagList" items="${cbtList}">
						<li> <a href="#">${cbTagList.ctl_name}</a></li>
					</c:forEach>
				</c:if>
				<c:if test="${cbtList eq 'Empty'}">
					<div align="center"><h6>최근 검색이 없습니다.</h6><br>
					<font size="2px;">첫 검색의 주인공이 되어주세요!</font></div>
				</c:if>
			</ul>
		</aside>
	</div>
</div>
</body>
</html>