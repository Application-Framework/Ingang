<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>title</title>
<!-- Bootstrap cdn 설정 -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style>
.tab_wrap {max-width: 800px;margin: 50px auto 0;padding: 0 15px;}
.tab_wrap .tit_list {position: relative;font-size: 0;}
.tab_wrap .tit_list:before {content:'';position: absolute;bottom: 0;left: 0;width: 100%;height: 1px;background: #ddd;z-index: 1;}
.tab_wrap .tit_list > li {display: inline-block;vertical-align: top;margin-right: 3px;}
.tab_wrap .tit_list > li a {display: inline-block;padding: 10px 15px;border: 1px solid #fff;border-radius:4px 4px 0 0;font-size: 14px;color: #000;text-decoration: none;}
.tab_wrap .tit_list > li a:hover {background: #efefef;border-color: #efefef;}
.tab_wrap .tit_list > li.active a {position: relative;border-color: #ddd;border-bottom: 1px solid #fff;background: #fff;color: #337ab7;z-index: 2;}
.tab_wrap .tab_con {border: 1px solid #ddd;border-top: none;}
.tab_wrap .tab_con .tab_list {display: none;height: 200px;padding: 15px;}
</style>

<script>
$(document).ready(function(){
    tab();
})

function tab(){
    //탭메뉴 클릭할 때 실행
    $('.tab_wrap .tit_list > li a').on('click', function(e) {
        e.preventDefault();
    
        //초기화
        $('.tab_wrap .tit_list > li').removeClass('active');
        $('.tab_wrap .tab_list').hide(); 
        
        //실행
        $(this).parent().addClass('active'); 
        var activeTab = $(this).attr('href');
        $(activeTab).show();

        //파라미터 확인
        urlParam =  location.search.substr(location.search.indexOf("?") + 1);
        if(urlParam != ''){
            urlParam = '?' + urlParam;
        }

        //파라미터 변경
        getNewUrl('tabName', urlParam); //(변경·추가할 파라미터 이름, 현재 파라미터)
        function getNewUrl(paramName, oldUrl) {
            var newUrl;
            var urlChk = new RegExp('[?&]'+paramName+'\\s*=');
            var urlChk2 = new RegExp('(?:([?&])'+paramName+'\\s*=[^?&]*)')
            
            
            if (urlChk.test(oldUrl)) { //해당 파라미터가 있을 때
                newUrl = oldUrl.replace(urlChk2, "$1"+paramName+"=" + activeTab.substr(1));
            } else if (/\?/.test(oldUrl)) { //해당 파라미터가 없고 다른 파라미터가 있을 때
                newUrl = oldUrl + "&"+paramName+"=" + activeTab.substr(1);
            } else { //파라미터가 없을 때
                newUrl = oldUrl + "?"+paramName+"=" + activeTab.substr(1);
            }

            history.pushState(null, null, newUrl);
        }
    });

    //파라미터 값 검사
    function getParameter(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
            results = regex.exec(location.search);
        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }

    var getParam = getParameter('tabName'); //선택한 탭 파라미터
    var loadChk = getParameter('loadChk'); //첫 로드 여부 체크
    
    if(getParam != ''){ //파라미터 값이 있으면 파라미터 값 기준으로 탭메뉴 선택
        $('.tab_wrap .tit_list > li a[href="#'+getParam+'"]').parent().addClass('active'); 
        $('.tab_wrap .tit_list > li a[href="#'+getParam+'"]').trigger('click');

        if(loadChk == 'on'){ //처음 로드되었으면 스크롤 이동
            //탭 위치로 이동

            //파라미터 확인
            var urlParam =  location.search.substr(location.search.indexOf("?") + 1);
            if(urlParam != ''){
                urlParam = '?' + urlParam;
            }
            
            //loadChk 파라미터 값 변경
            loadChange('loadChk', urlParam);
            function loadChange(paramName, oldUrl) {
                var newUrl;
                var urlChk = new RegExp('[?&]'+paramName+'\\s*=');
                var urlChk2 = new RegExp('(?:([?&])'+paramName+'\\s*=[^?&]*)')
                newUrl = oldUrl.replace(urlChk2, "$1"+paramName+"=off");
                history.pushState(null, null, newUrl);
            }
        }
    }else{ //파라미터 값이 없으면 active 클래스 기준으로 탭메뉴 선택
        var activeChk = 0;
        $('.tab_wrap .tit_list > li').each(function(i) { 
            if ($(this).hasClass('active')){
                $(this).addClass('active'); 
                $(this).find('a').trigger('click');
                activeChk ++
            }
        });

        //active 지정 안했을 시 첫 탭메뉴 선택
        if(activeChk == 0){
            $('.tab_wrap .tit_list > li:first-child a').trigger('click');
        }
    }

    //뒤로가기 탭메뉴 복구
    window.onpopstate = function(event) {
        //초기화
        $('.tab_wrap .tit_list > li').removeClass('active');
        $('.tab_wrap .tab_list').hide(); 
        var getParam2 = getParameter('tabName'); //선택한 탭 파라미터
        
        //탭메뉴 열기
        if(getParam2 != ''){
            $('.tab_wrap .tit_list > li a[href="#'+getParam2+'"]').parent().addClass('active');
            $('#'+getParam2).show()
        }else{
            $('.tab_wrap .tit_list > li:first-child').addClass('active');
            $('.tab_wrap .tab_list:first-of-type').show();
        }
    };
}
/* $( document ).ready(function() {
	var name_by_id = $('#test_id').attr('name');
}); */
</script>
</head>
<body>
<!-- <a id="test_obj" href="https://www.naver.com" >페이지 이동</a>
<a onclick="test()">asd</a>
 
<script>
    function test() {
        var tmp = document.getElementById("test_obj").href;
        alert(tmp);
    }
</script> -->


<div class="tab_wrap">
    <!-- 탭메뉴 제목 -->
    <ul class="tit_list">
        <!-- 디폴트 선택 li에 active 클래스 추가 -->
        <li class="active"><a href="#con01">1번째 탭</a></li>
        <li><a href="#con02">2번째 탭</a></li>
        <li><a href="#con03">3번째 탭</a></li>
    </ul>
    <!-- 탭메뉴 컨텐츠 -->
    <div class="tab_con">
        <div id="con01" class="tab_list">
            1번째 컨텐츠
        </div>
            
        <div id="con02" class="tab_list">
            2번째 컨텐츠
        </div>

        <div id="con03" class="tab_list">
            3번째 컨텐츠
        </div>
    </div>
</div>


</body>
</html>


