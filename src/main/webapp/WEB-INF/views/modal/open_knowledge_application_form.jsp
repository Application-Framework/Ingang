<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal" id="open_knowledge_modal">
	<div class="modal_content card card-body d-flex" id="open_knowledge_modal_content" title="클릭하면 창이 닫힙니다.">
		<div class="mb-2">
			<h5 class="float-start card-title">감사합니다, ${member.m_name}님<br>
				지식공유가 되기 위해서<br>
				아래 정보가 필요해요
			</h5>
			<span class="float-end" id="close_btn" style="cursor: pointer;"><svg width="15" aria-hidden="true" focusable="false" data-prefix="fal" data-icon="times" class="svg-inline--fa fa-times fa-w-10" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512"><path fill="#868E96" d="M193.94 256L296.5 153.44l21.15-21.15c3.12-3.12 3.12-8.19 0-11.31l-22.63-22.63c-3.12-3.12-8.19-3.12-11.31 0L160 222.06 36.29 98.34c-3.12-3.12-8.19-3.12-11.31 0L2.34 120.97c-3.12 3.12-3.12 8.19 0 11.31L126.06 256 2.34 379.71c-3.12 3.12-3.12 8.19 0 11.31l22.63 22.63c3.12 3.12 8.19 3.12 11.31 0L160 289.94 262.56 392.5l21.15 21.15c3.12 3.12 8.19 3.12 11.31 0l22.63-22.63c3.12-3.12 3.12-8.19 0-11.31L193.94 256z"></path></svg></span>
		</div>
		<div class="flex-glow-1 mb-3 pe-2" style="overflow-y: auto;">
	       	<div class="mb-4">
	       		<label class="form-label">인강인강 아이디</label><span class="modal_label--essential">*</span>
	        	<input disabled type="text" name="id" value="${member.m_id}" class="single-input">
			</div>
			
			<div class="mb-4">
				<label class="form-label">연락받을 이메일</label><span class="modal_label--essential">*</span>
	        	<input type="text" name="email" placeholder="자주 사용하는 이메일을 입력해주세요"  class="single-input" required>
			</div>
			
			<div class="mb-4">
				<label class="form-label">이름 (실명)</label><span class="modal_label--essential">*</span>
	        	<input type="text" name="name" placeholder="실명을 입력해주세요"  class="single-input" required>
			</div>
			
			<div class="mb-4">
				<label class="form-label">연락처</label><span class="modal_label--essential">*</span>
	        	<input type="text" name="phone" placeholder="010-0000-0000"  class="single-input" required>
			</div>
			
			<div class="mb-4">
				<label class="form-label">희망분야</label><span class="modal_label--essential">*</span>
				<div class="form-text">아직 계획 중인 강의가 없으시다면 지식공유자의 직무와 연관된 분야를 골라주세요.</div>
	        	<select name="hope_main_category" class="nice-select" style="float:none;" required>
	        		<option value="">test</option>
	        		<c:forEach var="category" items="${mainCategoryList}">
	        			<option value="${category.main_type_no}">${category.main_type_name}</option>
	        		</c:forEach>
	        	</select>
			</div>
			
			<div class="mb-4">
				<label class="form-label">나를 소개하는 글</label><span class="modal_label--essential">*</span>
				<div class="form-text">지식공유자님에 대한 소개와, 제작할 강의에 관련된 내용을 적어주세요. 가능한 상세하게 적어주시면 구체적인 안내를 받을 수 있습니다.</div>
				<textarea name="introduction" class="single-textarea" required></textarea>
			</div>
			
			<div class="mb-4">
				<label class="form-label">나를 표현할 수 있는 링크</label>
				<input type="text" name="link" placeholder="github 링크 or 블로그 링크"  class="single-input" required>
			</div>
		</div>
		<div class="mt-auto">
			<input type="submit" class="single-input genric-btn primary radius w-100" value="제출"/>
 		</div>
	</div>
</div>