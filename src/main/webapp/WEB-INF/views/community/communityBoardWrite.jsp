<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="java.time.LocalDateTime"%>
<%
	//현재시간 구해서 String으로 formating
	LocalDateTime nowTime = LocalDateTime.now();
	
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String now = nowTime.format(dateTimeFormatter);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet">

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<!-- 태그 -->
<link rel="stylesheet" type="text/css" href="/tag_create.css">
<script type="module" src="/tag_create.js"></script>
<!--코드미러 -->
<script src="<c:url value="/resources/js/codemirror.js"/>"></script>
<link href='<c:url value="/resources/css/codemirror.css"/>' rel='stylesheet' />
<style type="text/css">

.containerTop {
	display: flex;
	justify-content: space-between;
	align-items: flex-end
}


* {
  margin: 0;
  padding: 0;
  list-style: none;
}

ul {
  padding: 0px 0;
}

ul li {
  display: inline-block;
  margin: 0 0px;
  font-size: 10px;
  letter-spacing: -.5px;
}

form {
  padding-top: 0px;
}

ul li.tag-item {
  padding: 4px 4px;
  background-color: #F2F2F2;
  color: #000;
}

.tag-item:hover {
  background-color: #262626;
  color: #fff;
}

.del-btn {
  font-size: 7px;
  font-weight: bold;
  cursor: pointer;
  margin-left: 8px;
}
</style>
<title>게시글 작성</title>
<script type="text/javascript">
$(document).ready(function () {

  var tag = {};
  var counter = 0;

  // 태그를 추가한다.
  function addTag(value) {
    tag[counter] = value; // 태그를 Object 안에 추가
    counter++; // counter 증가 삭제를 위한 del-btn 의 고유 id 가 된다.
  }

  // 최종적으로 서버에 넘길때 tag 안에 있는 값을 array type 으로 만들어서 넘긴다.
  function marginTag() {
    return Object.values(tag)
      .filter(function (word) {
        return word !== "";
      });
  }

  $("#tag")
    .on("keyup", function (e) {
      var self = $(this);
      console.log("keypress");

      // input 에 focus 되있을 때 엔터 및 스페이스바 입력시 구동
      if (e.key === "Enter" || e.keyCode == 32) {

        var tagValue = self.val(); // 값 가져오기

        // 값이 없으면 동작 안합니다.
        if (tagValue !== "") {

          // 같은 태그가 있는지 검사한다. 있다면 해당값이 array 로 return 된다.
          var result = Object.values(tag)
            .filter(function (word) {
              return word === tagValue;
            })

          // 태그 중복 검사
          if (result.length == 0) {
            $("#tag-list")
              .append("<li class='tag-item'>" + tagValue + "<span class='del-btn' idx='" + counter + "'>x</span></li>&nbsp;");
            addTag(tagValue);
            self.val("");
          } else {
            alert("태그값이 중복됩니다.");
          }
        }
        e.preventDefault(); // SpaceBar 시 빈공간이 생기지 않도록 방지
      }
    });

  // 삭제 버튼
  // 삭제 버튼은 비동기적 생성이므로 document 최초 생성시가 아닌 검색을 통해 이벤트를 구현시킨다.
  $(document) .on("click", ".del-btn", function (e) {
      var index = $(this)
        .attr("idx");
      tag[index] = "";
      $(this)
        .parent()
        .remove();
    });
})


$(function() {

  $('.button-class1').click(function(){
    if( $(this).hasClass('btn btn-outline-danger') ) $(this).removeClass('btn btn-outline-danger');
    if( !$(this).hasClass('btn btn-danger') ) $(this).addClass('btn btn-danger');
    if( $('.button-class2').hasClass('btn btn-danger') ) $('.button-class2').removeClass('btn btn-danger');
    if( !$('.button-class2').hasClass('btn btn-outline-danger') ) $('.button-class2').addClass('btn btn-outline-danger');
    if( $('.button-class3').hasClass('btn btn-danger') ) $('.button-class3').removeClass('btn btn-danger');
    if( !$('.button-class3').hasClass('btn btn-outline-danger') ) $('.button-class3').addClass('btn btn-outline-danger');
  });
  
  $('.button-class2').click(function(){
	if( $(this).hasClass('btn btn-outline-danger') ) $(this).removeClass('btn btn-outline-danger');
	if( !$(this).hasClass('btn btn-danger') ) $(this).addClass('btn btn-danger');
	if( $('.button-class1').hasClass('btn btn-danger') ) $('.button-class1').removeClass('btn btn-danger');
	if( !$('.button-class1').hasClass('btn btn-outline-danger') ) $('.button-class1').addClass('btn btn-outline-danger');
	if( $('.button-class3').hasClass('btn btn-danger') ) $('.button-class3').removeClass('btn btn-danger');
	if( !$('.button-class3').hasClass('btn btn-outline-danger') ) $('.button-class3').addClass('btn btn-outline-danger');
  });
  
  $('.button-class3').click(function(){
	if( $(this).hasClass('btn btn-outline-danger') ) $(this).removeClass('btn btn-outline-danger');
	if( !$(this).hasClass('btn btn-danger') ) $(this).addClass('btn btn-danger');
	if( $('.button-class1').hasClass('btn btn-danger') ) $('.button-class1').removeClass('btn btn-danger');
	if( !$('.button-class1').hasClass('btn btn-outline-danger') ) $('.button-class1').addClass('btn btn-outline-danger');
    if( $('.button-class2').hasClass('btn btn-danger') ) $('.button-class2').removeClass('btn btn-danger');
    if( !$('.button-class2').hasClass('btn btn-outline-danger') ) $('.button-class2').addClass('btn btn-outline-danger');
  });

});
</script>
</head>

<body>
	<div class="container-fluid">
		<div>
			<div class="form-title text-left">
			<br>
				카테고리
				<div class="input-group my-2 mb-1">
					<input type="button"  class="btn btn-danger button-class1"  onclick="" value="자유주제">&nbsp;
					<input type="button" class="btn btn-outline-danger button-class2" onclick="" value="질문">&nbsp;
					<input type="button" class="btn btn-outline-danger button-class3" id="btn3" onclick="" value="스터디">
				</div>
			</div><br>
			<div class="d-flex flex-column">
				<form>
					<div class="form-group row">
						<div class="col-xs-12 col-md-12">
							제목
							<div class="input-group my-2 mb-1">
								<input type="text" value="" class="form-control" placeholder="제목을 입력해 주세요.">
							</div>
						</div><br><br>
						
						<div class="col-xs-12 col-md-12">
							태그
							<div class="input-group my-2 mb-1">
								<input type="text" value=""  id="tag" class="form-control" placeholder="태그를 설정해주세요.">
							</div>
							<ul id="tag-list"> </ul>
						</div>
					</div>
					
					내용
					<div class="form-group">
						<textarea class="form-control" rows="15" id="content" name="content" placeholder="내용을 작성해주세요"></textarea>	
					</div>
					<div align="right">
						<button type="button" class="btn btn-danger" onclick="">작성</button>
						<button type="button" class="btn btn-outline-danger" onclick="self.close();">취소</button>
					</div>
				</form>
				
				
			</div>
		</div>
	</div>


	<script> 
	  var editor = CodeMirror.fromTextArea(myTextarea, {
	    lineNumbers: true
	  });

	  function scheduleDetailGo(sId) { 
		  var popup = window.open('ScheduleDetailView?schedule_id='+sId , 'a', 'width=800px,height=840px,left=300,top=100');
	  }

	 
	</script>



<!-- Modal -->
<!--
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			
			<div class="modal-body">
			
				<ul class="nav nav-pills" id="myTab" style="width: 100%;">
					<li class="nav-item" id="myTabActive1" style="width: 15%;"><a class="nav-link active" data-toggle="tab" href="#qwe"><h6 style="color: #5D5D5D;" align="center"> 자유주제</h6></a></li>
					<li class="nav-item" id="myTabActive2" style="width: 15%;"><a class="nav-link" data-toggle="tab" href="#asd" ><h6 style="color: #5D5D5D;" align="center">질문</h6></a></li>
					<li class="nav-item" id="myTabActive2" style="width: 15%;"><a class="nav-link" data-toggle="tab" href="#asd" ><h6 style="color: #5D5D5D;" align="center">스터디</h6></a></li>
				</ul>
				
				<div class="tab-content">
					<div class="tab-pane fade show active" id="qwe">
						<article class="blog_item">
							<div class="blog_details" style="padding: 10px 10px 10px 10px;">
							
								<a class="d-inline-block" href="single-blog.html">
								</a>
							</div>
						</article>
					</div>
					<div class="tab-pane fade" id="asd">
						<div class="tab-pane fade show active" id="qwe">
							<article class="blog_item">
								<div class="blog_details" style="padding: 10px 10px 10px 10px;">
									<a class="d-inline-block" href="single-blog.html">
									</a>
								</div>
							</article>
						</div>
					</div>
				</div>
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">작성</button>
			</div>
		</div>
	</div>
</div>
-->


</body>
</html>