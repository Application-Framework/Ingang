<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
	<li>
		<hr class="sidebar-divider my-0">
	</li>
	<li class="nav-item active">
		<a class="nav-link" href="/admin/main">
			<i class="fas fa-fw fa-tachometer-alt"></i>
			<span>Home</span>
		</a>
	</li>
	<li>
		<hr class="sidebar-divider">
		<div class="sidebar-heading">강의 및 노트 관리</div>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="/admin/course">
			<i class="fas fa-fw fa-table"></i>
			<span>강의</span>
		</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="/admin/note">
			<i class="fas fa-fw fa-table"></i>
			<span>노트</span>
		</a>
	</li>
	<li>
		<hr class="sidebar-divider">
		<div class="sidebar-heading">회원 관리</div>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="/admin/memberList">
			<i class="fas fa-fw fa-table"></i>
			<span>회원 목록</span>
		</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="/admin/teacher">
			<i class="fas fa-fw fa-table"></i>
			<span>강사 목록</span>
		</a>
	</li>
	<li>
		<hr class="sidebar-divider">
		<div class="sidebar-heading">커뮤니티 관리</div>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="/admin/freeboard">
			<i class="fas fa-fw fa-table"></i>
			<span>자유게시판</span>
		</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="/admin/QnA">
			<i class="fas fa-fw fa-table"></i>
			<span>질문&답변</span>
		</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="/admin/review">
			<i class="fas fa-fw fa-table"></i>
			<span>수강후기</span>
		</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="/admin/study">
			<i class="fas fa-fw fa-table"></i>
			<span>스터디</span>
		</a>
	</li>
	<li>
		<hr class="sidebar-divider">
		<div class="sidebar-heading">통계</div>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="visit">
			<i class="fas fa-fw fa-table"></i>
			<span>방문자 수</span>
		</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="board">
			<i class="fas fa-fw fa-table"></i>
			<span>게시글 작성 수</span>
		</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="period">
			<i class="fas fa-fw fa-table"></i>
			<span>기간별  분양 수</span>
		</a>
	</li>
	<li>
		<hr class="sidebar-divider">
		<div class="sidebar-heading">고객센터</div>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="inquiry">
			<i class="fas fa-fw fa-table"></i>
			<span>1:1 문의</span>
		</a>
	</li>
	<li class="nav-item">
		<a class="nav-link" href="faq">
			<i class="fas fa-fw fa-table"></i>
			<span>FAQ 목록</span>
		</a>
	</li>
</ul>