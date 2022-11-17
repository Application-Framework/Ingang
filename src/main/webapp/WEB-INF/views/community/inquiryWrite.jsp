<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">



  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<script src=" https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.18/lang/summernote-ko-KR.min.js"></script>
<script src="<c:url value='/resources/js/community/summernote.js'/>"></script>
<!-- 태그 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
<link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" />
<link rel="stylesheet" href="<c:url value='/resources/css/community/tag.css'/>">

<title>게시글 작성</title>
</head>

<body>
	<div class="container-fluid">
		<div>
			<div class="form-title text-left">
			<br>
				<font size="10px;" color="#DB3A00">1:1 문의하기</font>
			</div><br>
			<div class="d-flex flex-column">
				<form>
					<div class="form-group row">
						<input type="hidden" id="m_no" name="m_no" value="${sessionScope.member.getM_no()}">
						<div class="col-xs-12 col-md-12">
							제목
							<div class="input-group my-2 mb-1">
								<input type="text" id="title" class="form-control" placeholder="제목을 입력해 주세요.">
							</div>
						</div><br><br>
						
						<div class="col-xs-12 col-md-12">
							카테고리
							<select class="form-control" id="category" name="category">
								<option value="noSelcet">문의 유형 선택</option>
								<option value="강의문의">강의 문의</option>
								<option value="노트문의">노트 문의</option>
								<option value="커뮤니티">커뮤니티 문의</option>
								<option value="회원문의">회원 문의</option>
								<option value="결제문의">결제 문의</option>
								<option value="기타문의">기타 문의</option>
							</select>
						</div>
					</div>
					
					내용
					<div class="form-group">
						<textarea id="content" class="form-control" name="content"></textarea>
					</div>
					<div align="right">
						<button type="button" class="btn btn-danger" id="btnWrite">작성</button>
						<button type="button" class="btn btn-outline-danger" onclick="self.close();">취소</button>
					</div>
				</form>
				
				
			</div>
		</div>
	</div>
<script src="<c:url value='/resources/js/community/tag.js'/>"></script>
<script type="text/javascript">

$('#btnWrite').click(function() {
	var m_no = $("#m_no").val();
	var title = $("#title").val();
	var content = $("#content").val();
	var category = $("#category").val();
	var param = {'m_no': m_no , 'title': title,'content': content, 'category': category};
	if(!title) {
		swal({
			title: "1:1문의 작성",
			text: "제목이 입력되지 않았습니다.",
			icon: "warning",
			timer: 3000
		});
		return false;
	}	
	if(category == "noSelcet") {
		swal({
			title: "1:1문의 작성",
			text: "카테고리가 선택되지 않았습니다.",
			icon: "warning",
			timer: 3000
		});
		return false;
	}
	if(!content) {
		swal({
			title: "1:1문의 작성",
			text: "내용이 입력되지 않았습니다.",
			icon: "warning",
			timer: 3000
		});
		return false;
	}
	else {
		$.ajax({
			url: "WriteInquiry",
			type: "POST",
			data: param,
			success: function(data) {
				if (data != 1) {
					swal({
						title: "1:1문의 작성",
						text: "1:1문의 작성이 실패하였습니다.",
						icon: "error",
						timer: 3000
					});
				}
				else {
					opener.parent.location.reload();
					window.close();
				}
			},
			error: function() {
				swal({
					title: "인강인강",
					text: "문제가 발생하였습니다.\n잠시 후 다시 시도해주세요.",
					icon: "error",
					timer: 3000
				});
			}
		});
	}
})
</script>
</body>
</html>