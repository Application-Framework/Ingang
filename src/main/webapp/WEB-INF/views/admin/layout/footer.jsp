<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<footer class="sticky-footer bg-white">
	<div class="container my-auto">
		<div class="copyright text-center my-auto">
			<span>인강인강</span>
		</div>
	</div>
</footer>

<a class="scroll-to-top rounded" href="#page-top">
	<i class="fas fa-angle-up"></i>
</a>

<!-- 관리자 정보 수정 Modal 부분 -->
<div class="modal fade" id="profileEditModal" tabindex="-1" role="dialog" aria-labelledby="profileEditModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header border-bottom-0">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>
			<div class="modal-body">
				<div class="form-title text-center">
					<h4>정보 수정</h4>
				</div>
				<div class="d-flex flex-column text-center">
					<form name="AdminProfileEditForm" id="AdminProfileEditForm" action="AdminProfileEdit" method="POST">
						<div class="input-group my-2 mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text">아이디　</span>
							</div>
							<input type="text" class="form-control" id="AdminID" name="UserID" value="${AdminProfile.getUserID() }" readonly>
						</div>
						<div class="input-group my-2 mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text">비밀번호</span>
							</div>
							<input type="Password" class="form-control" id="AdminPassword" name="Password" value="${AdminProfile.getPassword() }">
						</div>
						<div class="input-group my-2 mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text">이름　　</span>
							</div>
							<input type="text" class="form-control" id="AdminName" name="Name" value="${AdminProfile.getName() }" readonly>
						</div>
						<div class="input-group my-2 mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text">이메일　</span>
							</div>
							<input type="email" class="form-control" id="AdminEmail" name="Email" value="${AdminProfile.getEmail() }" required>
						</div>
						<div class="input-group my-2 mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text">휴대전화</span>
							</div>
							<input type="tel" class="form-control" id="AdminPhone" name="Phone" value="${AdminProfile.getPhone() }" maxlength="11">
						</div>
						<div class="input-group my-2 mb-1">
							<div class="input-group-prepend">
								<span class="input-group-text">생년월일</span>
							</div>
							<input type="date" class="form-control" id="AdminBirth" name="Birth" value="${AdminProfile.getBirth() }" readonly>
						</div>
						<button type="button" id="profileEdit" class="btn btn-primary" onclick="AdminProfileEdit()">수정</button>
						<button type="button" id="reset" class="btn btn-primary">취소</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script src='<c:url value="/resources/js/bootstrap.bundle.min.js"/>'></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src='<c:url value="/resources/js/sb-admin-2.min.js"/>'></script>
<script src='<c:url value="/resources/js/jquery.min.js"/>'></script>
<script src='<c:url value="/resources/js/jquery.easing.min.js"/>'></script>
<script src='<c:url value="/resources/js/DeleteSelection.js"/>'></script>
<script src='<c:url value="/resources/js/AdminMember.js"/>'></script>
<script src="https://cdn.ckeditor.com/ckeditor5/27.1.0/classic/ckeditor.js"></script>
<script src='<c:url value="/resources/js/Member.js"/>'></script>
<script src='<c:url value="/resources/js/Board.js"/>'></script>
<script src='<c:url value="/resources/js/Authority.js"/>'></script>

<%-- <c:if test="${sessionScope.member eq null || sessionScope.member.getGrade() ne 'Admin'}">
		<script>AuthCheck();</script>
</c:if> --%>
