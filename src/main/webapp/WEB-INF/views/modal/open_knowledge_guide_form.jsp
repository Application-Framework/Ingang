<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="modal" id="open_knowledge_modal">
	<div class="modal_content card card-body d-flex" id="open_knowledge_modal_content" title="클릭하면 창이 닫힙니다.">
		<div class="mb-2">
			<h5 class="float-start card-title">${member.m_name}님! 안녕하세요.<br>
				지식공유에 동참해 주셔서 감사합니다!<br>
				인강인강의 이야기를 들어주세요!
			</h5>
			<span class="float-end" id="close_btn" style="cursor: pointer;"><svg width="15" aria-hidden="true" focusable="false" data-prefix="fal" data-icon="times" class="svg-inline--fa fa-times fa-w-10" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 320 512"><path fill="#868E96" d="M193.94 256L296.5 153.44l21.15-21.15c3.12-3.12 3.12-8.19 0-11.31l-22.63-22.63c-3.12-3.12-8.19-3.12-11.31 0L160 222.06 36.29 98.34c-3.12-3.12-8.19-3.12-11.31 0L2.34 120.97c-3.12 3.12-3.12 8.19 0 11.31L126.06 256 2.34 379.71c-3.12 3.12-3.12 8.19 0 11.31l22.63 22.63c3.12 3.12 8.19 3.12 11.31 0L160 289.94 262.56 392.5l21.15 21.15c3.12 3.12 8.19 3.12 11.31 0l22.63-22.63c3.12-3.12 3.12-8.19 0-11.31L193.94 256z"></path></svg></span>
		</div>
		<div class="flex-glow-1 mb-3" style="overflow-y: auto;">
			<p class="card-text"><b>1. 인프런은 성장기회의 평등을 추구합니다.</b><br>
				우리는 때로 무언가를 배워야만 합니다.<br>
				하지만 여러 이유로 당연하다고 생각되어 지는것들이 누군가에게는 사치가 되기도 합니다.<br>
				인프런은 누구나, 경제적으로 시간적 제약없이 내가 원하는 것을 배우고, 지식을 나눌 수 있는 공간입니다.
			</p>
			<p class="card-text"><b>2. 전문 지식으로 수익이 가능한 유일한 곳.</b><br>
				인프런은 기술 강의, 멘토링으로 의미 있는 보상을 가질 수 있는 유일한 플랫폼 입니다. <br>
				97만명의 수강생이 강의를 신청할 때마다 수익을 얻을 수 있어요!<br>
				지속가능한 수익과 명예를 가져가세요 :)
			</p>
			<p class="card-text"><b>3. 인프런은 70% ~ 100% 의 비율의 높은 수익을 제공합니다.</b><br>
				좋은 지식은 합당한 보상에서 나온다고 인프런은 생각합니다. <br>
				때문에 인프런은 다른 학습 서비스에 비해 월등히 높은 수익을 드리고 있어요.<br>
				실제로 인프런엔 꾸준히 월 수백 ~ 수천 만원 이상의 수익을 가져가는 많은 지식공유자들이 계셔요. 
			</p>
			<p class="card-text"><b>4. 인프런의 강의는 지식공유자가 자유롭게 운영할 수 있습니다.</b><br>
				지식공유자는 학생추가, 새소식 알림, 운영, 쿠폰 발행 등으로 자신의 강의를 자유롭게 운영할 수 있습니다. 학습자들과 소식을 공유하고 자유롭게 운영해 주세요.
			</p>
	   	</div>
	   	<div class="mt-auto">
			<a href="javascript:getOpenKnowledgeApplicationForm();"  class="genric-btn primary radius w-100">지식 공유 참여</a>
		</div>
	</div>
</div>