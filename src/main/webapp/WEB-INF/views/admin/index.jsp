<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인강인강 관리자</title>
</head>
<body id="page-top">
    <div id="wrapper">
    	<!-- 좌측 배너 부분 -->
		<jsp:include page="layout/banner.jsp"/>
		<!-- 좌측 배너 부분 -->
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<!-- 상단 헤더 부분 -->
				<jsp:include page="layout/header.jsp"/>
				<!-- 상단 헤더 부분 -->
				
				<!-- 본문 -->
				<div class="container-fluid">
					<div class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
					</div>
					<div class="row">
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-primary shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div class="text-xs font-weight-bold text-primary text-uppercase mb-1">오늘의 방문자 수</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800">${todayVisitorsCount }명</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-calendar fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-success shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div class="text-xs font-weight-bold text-success text-uppercase mb-1">오늘의 게시글 작성 수</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800">${todayBoardWriteTotalCount }개</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-comments fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-info shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
										<div class="text-xs font-weight-bold text-info text-uppercase mb-1">오늘의 가입 회원 수</div>
											<div class="row no-gutters align-items-center">
												<div class="col-auto">
													<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${todayRegisterMemberTotalCount }명</div>
												</div>
												<div class="col">
													<div class="progress progress-sm mr-2">
														<div class="progress-bar bg-info" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-xl-3 col-md-6 mb-4">
							<div class="card border-left-warning shadow h-100 py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div class="text-xs font-weight-bold text-warning text-uppercase mb-1">오늘의 매출</div>
											<div class="h5 mb-0 font-weight-bold text-gray-800">
												<c:choose>
													<c:when test="${TodayCourseTotalSales ne null}">
														<fmt:formatNumber value="${TodayCourseTotalSales }" pattern="###,###,###"/>원
													</c:when>
													<c:otherwise>
														금일 매출이 없습니다.
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						
						<!-- 강의 주간 매출 graph -->
						<div class="col-xl-8 col-lg-7">
							<div class="card shadow mb-4">
								<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">주간 강의 매출 통계</h6>
									
									<c:choose>
												<c:when test="${WeekCourseTotalSales ne null}">
													<fmt:formatNumber value="${WeekCourseTotalSales}" pattern="###,###,###"/>원
												</c:when>
												<c:otherwise>
													주간 매출이 없습니다.
												</c:otherwise>
											</c:choose>
								</div>
								<div class="card-body">
									<div class="chart-area">
										<canvas id="myAreaChart" width="200" height="100">
										 	
										</canvas>
									</div>
								</div>
							</div>
						</div>
						
						<!-- 강의 구매 비율 chart -->
						<div class="col-xl-4 col-lg-5">
							<div class="card shadow mb-4">
								<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">강의 구매 비율</h6>
								</div>
								<div class="card-body">
									<div class="chart-pie pt-4 pb-2">
										<canvas id="myPieChartCourse"></canvas>
									</div>
									<div class="mt-4 text-center small">
										<c:set var="i" value="0" />
										<span id="spanColor" class="mr-2">
											<c:forEach var="listCourseTotal" items="${listCourseTotal}">
													<i class="fas fa-circle" onload="makeColor()" id="circle1[${i}]" style="float: left;"></i><div style="font-size: 12px; color: black;">${listCourseTotal.title}(${listCourseTotal.percent}%)</div> <br>
												<c:set var="i" value="${i+1}"/>
											</c:forEach>
										</span>
									</div>
								</div>
							</div>
						</div>
						
						<!-- 노트 주간 매출 graph -->
						<div class="col-xl-8 col-lg-7">
							<div class="card shadow mb-4">
								<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">주간 노트 매출 통계</h6>
									<c:choose>
										<c:when test="${WeekNoteTotalSales ne null}">
											<fmt:formatNumber value="${WeekNoteTotalSales}" pattern="###,###,###"/>원
										</c:when>
										<c:otherwise>
											주간 매출이 없습니다.
										</c:otherwise>
									</c:choose>
								</div>
								<div class="card-body">
									<div class="chart-area">
										<canvas id="myAreaChartNote" width="200" height="100">
										 	
										</canvas>
									</div>
								</div>
							</div>
						</div>
						
						<!-- 노트 구매 비율 chart -->
						<div class="col-xl-4 col-lg-5">
							<div class="card shadow mb-4">
								<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">노트 구매 비율</h6>
								</div>
								<div class="card-body">
									<div class="chart-pie pt-4 pb-2">
										<canvas id="myPieChartNote"></canvas>
									</div>
									<div class="mt-4 text-center small">
										<c:set var="i" value="0" />
										<span id="spanColor" class="mr-2">
											<c:forEach var="listNoteTotal" items="${listNoteTotal}">
													<i class="fas fa-circle" onload="makeColor2()" id="circle2[${i}]" style="float: left;"></i><div style="font-size: 12px; color: black;">${listNoteTotal.title}(${listNoteTotal.percent}%)</div> <br>
												<c:set var="i" value="${i+1}"/>
											</c:forEach>
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 본문 -->
			</div>
			
			<!-- 하단 푸터 부분 -->
			<jsp:include page="layout/footer.jsp"/>
    		<!-- 하단 푸터 부분 -->
		</div>
	</div>
    
	<!-- Chart -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
	<%-- <script src='<c:url value="/resources/js/Chart.min.js"/>'></script> --%>
	
	<!-- 주간 강의 매출 Chart -->
	<script>
		Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
		Chart.defaults.global.defaultFontColor = '#858796';
	
		function number_format(number, decimals, dec_point, thousands_sep) {
			// *     example: number_format(1234.56, 2, ',', ' ');
			// *     return: '1 234,56'
			number = (number + '').replace(',', '').replace(' ', '');
			var n = !isFinite(+number) ? 0 : +number,
				prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
				sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
				dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
				s = '',
				toFixedFix = function(n, prec) {
					var k = Math.pow(10, prec);
					return '' + Math.round(n * k) / k;
				};
			// Fix for IE parseFloat(0.55).toFixed(0) = 0;
			s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
			if (s[0].length > 3) {
				s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
			}
			if ((s[1] || '').length < prec) {
				s[1] = s[1] || '';
				s[1] += new Array(prec - s[1].length + 1).join('0');
			}
			return s.join(dec);
		}
		var ctx = document.getElementById("myAreaChart");
	
		var OrderDate = new Array();
		var Earnings = new Array();
		
		<c:forEach var="list" items="${list}">
			OrderDate.push("${list.payment_date}");
			Earnings.push("${list.payment}");
		</c:forEach>
			
		var myLineChart = new Chart(ctx, {
			type: 'line',
			data: {
				
				labels: OrderDate,
				datasets: [{
					label: "Earnings",
					lineTension: 0.3,
					backgroundColor: "rgba(78, 115, 223, 0.05)",
					borderColor: "rgba(78, 115, 223, 1)",
					pointRadius: 3,
					pointBackgroundColor: "rgba(78, 115, 223, 1)",
					pointBorderColor: "rgba(78, 115, 223, 1)",
					pointHoverRadius: 3,
					pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
					pointHoverBorderColor: "rgba(78, 115, 223, 1)",
					pointHitRadius: 10,
					pointBorderWidth: 2,
					data: Earnings
				}],
			},
			options: {
				maintainAspectRatio: false,
				layout: {
					padding: {
						left: 10,
						right: 25,
						top: 25,
						bottom: 0
					}
				},
				scales: {
					xAxes: [{
						time: {
							unit: 'date'
						},
						gridLines: {
							display: false,
							drawBorder: false
						},
						ticks: {
							maxTicksLimit: 7
						}
					}],
					yAxes: [{
						ticks: {
							maxTicksLimit: 5,
							padding: 10,
							// Include a dollar sign in the ticks
							callback: function(value, index, values) {
								return '$' + number_format(value);
							}
						},
						gridLines: {
							color: "rgb(234, 236, 244)",
							zeroLineColor: "rgb(234, 236, 244)",
							drawBorder: false,
							borderDash: [2],
							zeroLineBorderDash: [2]
						}
					}],
				},
				legend: {
					display: false
				},
				tooltips: {
					backgroundColor: "rgb(255,255,255)",
					bodyFontColor: "#858796",
					titleMarginBottom: 10,
					titleFontColor: '#6e707e',
					titleFontSize: 14,
					borderColor: '#dddfeb',
					borderWidth: 1,
					xPadding: 15,
					yPadding: 15,
					displayColors: false,
					intersect: false,
					mode: 'index',
					caretPadding: 10,
					callbacks: {
						label: function(tooltipItem, chart) {
							var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
							return datasetLabel + ': $' + number_format(tooltipItem.yLabel);
						}
					}
				}
			}
		});
	</script>
	
	
	
</body>

<!-- 강의 구매 비율 Chart -->
	<script>
	
	var OrderTitle1 = new Array();
	var Earnings1 = new Array();
	
	<c:forEach var="listCourseTotal" items="${listCourseTotal}">
		OrderTitle1.push("${listCourseTotal.title}");
		Earnings1.push("${listCourseTotal.percent}");
	</c:forEach>

	<!-- 랜덤 색상 생성 -->
		$(function(){
			var newColor1 = new Array();
			var rgbColor1 = new Array();
			
			for(var i=0; i < OrderTitle1.length; i++) {
				newColor1[i] = '#' + Math.round(Math.random() * 0xffffff).toString(16);
			}
			
			for(var i=0; i < OrderTitle1.length; i++) {
				document.getElementById('circle1[' + i + ']').style.color = newColor1[i];
				console.log(document.getElementById('circle1[' + i + ']'));
				
				// 색상 확인
				var element1 = document.getElementById('circle1[' + i + ']');
				var cssObj1 = window.getComputedStyle(element1);
				var bgColor1 = cssObj1.getPropertyValue('color');
				rgbColor1.push(bgColor1);
				
			}
		
		
	//Set new default font family and font color to mimic Bootstrap's default styling
	Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
	Chart.defaults.global.defaultFontColor = '#858796';
	
	// Pie Chart Example
	var ctx = document.getElementById("myPieChartCourse");

	<c:set var='i' value='0'/>
	var arr = newColor1;
	
	var myPieChart = new Chart(ctx, {
	type : 'doughnut',
	data : {
		labels : OrderTitle1 ,
		datasets : [ {
			data : Earnings1 ,
			backgroundColor : [ // 구매 비율 통계 color
				arr[${i}], arr[${i+1}], arr[${i+2}], arr[${i+3}], arr[${i+4}], 
				arr[${i+5}], arr[${i+6}], arr[${i+7}], arr[${i+8}], arr[${i+9}]
				
				],
			hoverBackgroundColor : [ // 구매 비율 통계 hover color
				 /* '#0074F0', '#D03040', '#8033de', '#4fc4db', '#dbd24f' */  ],
		}],
	},
	options : {
		maintainAspectRatio : false,
		tooltips : {
			backgroundColor : "rgb(255,255,255)",
			bodyFontColor : "#858796",
			borderColor : '#dddfeb',
			borderWidth : 1,
			xPadding : 15,
			yPadding : 15,
			displayColors : false,
			caretPadding : 10,
		},
		legend : {
			display : false
		},
		cutoutPercentage : 80,
	},
	});
	
	
		});
	</script>
	
	<!-- 주간 노트 매출 Chart -->
	<script>
		Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
		Chart.defaults.global.defaultFontColor = '#858796';
	
		function number_format(number, decimals, dec_point, thousands_sep) {
			// *     example: number_format(1234.56, 2, ',', ' ');
			// *     return: '1 234,56'
			number = (number + '').replace(',', '').replace(' ', '');
			var n = !isFinite(+number) ? 0 : +number,
				prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
				sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
				dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
				s = '',
				toFixedFix = function(n, prec) {
					var k = Math.pow(10, prec);
					return '' + Math.round(n * k) / k;
				};
			// Fix for IE parseFloat(0.55).toFixed(0) = 0;
			s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
			if (s[0].length > 3) {
				s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
			}
			if ((s[1] || '').length < prec) {
				s[1] = s[1] || '';
				s[1] += new Array(prec - s[1].length + 1).join('0');
			}
			return s.join(dec);
		}
		var ctx = document.getElementById("myAreaChartNote");
	
		var OrderDate = new Array();
		var Earnings = new Array();
		
		<c:forEach var="listNote" items="${listNote}">
			OrderDate.push("${listNote.payment_date}");
			Earnings.push("${listNote.payment}");
		</c:forEach>
			
		var myLineChart = new Chart(ctx, {
			type: 'line',
			data: {
				
				labels: OrderDate,
				datasets: [{
					label: "Earnings",
					lineTension: 0.3,
					backgroundColor: "rgba(78, 115, 223, 0.05)",
					borderColor: "rgba(78, 115, 223, 1)",
					pointRadius: 3,
					pointBackgroundColor: "rgba(78, 115, 223, 1)",
					pointBorderColor: "rgba(78, 115, 223, 1)",
					pointHoverRadius: 3,
					pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
					pointHoverBorderColor: "rgba(78, 115, 223, 1)",
					pointHitRadius: 10,
					pointBorderWidth: 2,
					data: Earnings
				}],
			},
			options: {
				maintainAspectRatio: false,
				layout: {
					padding: {
						left: 10,
						right: 25,
						top: 25,
						bottom: 0
					}
				},
				scales: {
					xAxes: [{
						time: {
							unit: 'date'
						},
						gridLines: {
							display: false,
							drawBorder: false
						},
						ticks: {
							maxTicksLimit: 7
						}
					}],
					yAxes: [{
						ticks: {
							maxTicksLimit: 5,
							padding: 10,
							// Include a dollar sign in the ticks
							callback: function(value, index, values) {
								return '$' + number_format(value);
							}
						},
						gridLines: {
							color: "rgb(234, 236, 244)",
							zeroLineColor: "rgb(234, 236, 244)",
							drawBorder: false,
							borderDash: [2],
							zeroLineBorderDash: [2]
						}
					}],
				},
				legend: {
					display: false
				},
				tooltips: {
					backgroundColor: "rgb(255,255,255)",
					bodyFontColor: "#858796",
					titleMarginBottom: 10,
					titleFontColor: '#6e707e',
					titleFontSize: 14,
					borderColor: '#dddfeb',
					borderWidth: 1,
					xPadding: 15,
					yPadding: 15,
					displayColors: false,
					intersect: false,
					mode: 'index',
					caretPadding: 10,
					callbacks: {
						label: function(tooltipItem, chart) {
							var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
							return datasetLabel + ': $' + number_format(tooltipItem.yLabel);
						}
					}
				}
			}
		});
	</script>
	
	<!-- 노트 구매 비율 Chart -->
	<script>
	
	var OrderTitle2 = new Array();
	var Earnings2 = new Array();
	
	<c:forEach var="listNoteTotal" items="${listNoteTotal}">
		OrderTitle2.push("${listNoteTotal.title}");
		Earnings2.push("${listNoteTotal.percent}");
	</c:forEach>

	<!-- 랜덤 색상 생성 -->
		$(function(){
			var newColor2 = new Array();
			var rgbColor2 = new Array();
			
			for(var i=0; i < OrderTitle2.length; i++) {
				newColor2[i] = '#' + Math.round(Math.random() * 0xffffff).toString(16);
			}
			
			for(var i=0; i < OrderTitle2.length; i++) {
				document.getElementById('circle2[' + i + ']').style.color = newColor2[i];
				console.log(document.getElementById('circle2[' + i + ']'));
				
				// 색상 확인
				var element2 = document.getElementById('circle2[' + i + ']');
				var cssObj2 = window.getComputedStyle(element2);
				var bgColor2 = cssObj2.getPropertyValue('color');
				rgbColor2.push(bgColor2);
				
			}
		
		
	//Set new default font family and font color to mimic Bootstrap's default styling
	Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
	Chart.defaults.global.defaultFontColor = '#858796';
	
	// Pie Chart Example
	var ctx2 = document.getElementById("myPieChartNote");

	<c:set var='i' value='0'/>
	var arr2 = newColor2;
	
	var myPieChartNote = new Chart(ctx2, {
	type : 'doughnut',
	data : {
		labels : OrderTitle2 ,
		datasets : [ {
			data : Earnings2 ,
			backgroundColor : [ // 구매 비율 통계 color
				arr2[${i}], arr2[${i+1}], arr2[${i+2}], arr2[${i+3}], arr2[${i+4}], 
				arr2[${i+5}], arr2[${i+6}], arr2[${i+7}], arr2[${i+8}], arr2[${i+9}]
				
				],
			hoverBackgroundColor : [ // 구매 비율 통계 hover color
				 /* '#0074F0', '#D03040', '#8033de', '#4fc4db', '#dbd24f' */  ],
		}],
	},
	options : {
		maintainAspectRatio : false,
		tooltips : {
			backgroundColor : "rgb(255,255,255)",
			bodyFontColor : "#858796",
			borderColor : '#dddfeb',
			borderWidth : 1,
			xPadding : 15,
			yPadding : 15,
			displayColors : false,
			caretPadding : 10,
		},
		legend : {
			display : false
		},
		cutoutPercentage : 80,
	},
	});
	
	
		});
	</script>
</html>