<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<!-- Bootstrap core CSS -->
 
<link href="${pageContext.request.contextPath}/gentelella/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/gentelella/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/gentelella/vendors/nprogress/nprogress.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/gentelella/vendors/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/gentelella/build/css/custom.min.css" rel="stylesheet">

</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<!-- 左侧的导航栏 所有-->
			<div class="col-md-3 left_col">
				<!-- 导航栏的分段 -->
				<div class="left_col scroll-view">
				
					<div class="navbar nav_title" style="border: 0;">
						<a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>山城医院</span></a>
					</div>
					
					<div class="clearfix">
					</div>
					
					<div class="profile clearfix">
						<div class="profile_pic">
							<img src="images/img.jpg" alt="..." class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>Welcome,</span>
							<h2>啦啦啦</h2>
						</div>
					</div>
					</br>
					
					<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<h2>通用功能</h2>
							<ul class="nav side-menu">
							
								<c:forEach items="${mymenuList}" var="one">
									<li>
										<a >
											<i class="fa fa-home"></i>
											${one.menuName }
											<span class="fa fa-chevron-down"></span>
										</a>
										<ul class="nav child_menu">
											<c:forEach items="${one.childMenu}" var="two">
												<li >
													<a dataUrl="${two.menuUrl}">
														<i class="fa fa-home"></i>
														${two.menuName}
														<span class="fa fa-chevron-down"></span>
													</a>
													<ul class="nav child_menu" style="display : block">
														<c:forEach items="${two.childMenu}" var="three">
															<li><a
															dataUrl="/${three.menuUrl}">
															<i class="icon-leaf"></i> ${three.menuName}
														</a></li>
														</c:forEach>
													</ul>
												</li>
											</c:forEach>
										</ul>
									</li>
								</c:forEach>
								
								
								
							</ul>
							
						</div>
						
					</div>
					
				</div>

			</div>
			<!-- 顶层导航栏 -->
			<div class="top_nav">
				<div class="nav_menu">
					<nav>
						<div class="nav toggle">
							<a id="menu_toggle"><i class="fa fa-bars"></i>
							</a>
						</div>
						<ul class="nav navbar-nav navbar-right">
							<li class="">
								<a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
								<img src="images/img.jpg" alt="">John Doe
								<span class=" fa fa-angle-down"></span>
								</a>
								<ul class="dropdown-menu dropdown-usermenu pull-right">
									<li><a href="javascript:;"> Profile</a></li>
									<li><a href="javascript:;"> Settings</a></li>
									<li><a href="javascript:;"> Help</a></li>
									<li><a href="gentelella/docs/login.jsp"> <i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
								</ul>
							</li>
							<li role="presentation" class="">
								<a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
									<i class="fa fa-envelope-o"></i>
									<span class="badge bg-grey">6</span>
								</a>
								<ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
								</ul>
							</li>
						</ul>
					</nav>
				</div>
			</div>
			
			<div class="right_col" role="main">
				<iframe id="nyFrame" scrolling="auto" src="http://wwww.baidu.com"  width="100%" height="800"></iframe>
			</div>
		
		
		</div>	
	</div>





<!-- 一大堆乱七八糟的特效，但是必须要 -->
<script src="${pageContext.request.contextPath}/gentelella/vendors/jquery/dist/jquery.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/gentelella/vendors/bootstrap/dist/js/bootstrap.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/gentelella/vendors/fastclick/lib/fastclick.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/gentelella/vendors/nprogress/nprogress.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/gentelella/vendors/Chart.js/dist/Chart.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/gentelella/vendors/jquery-sparkline/dist/jquery.sparkline.min.js" type="a7b8a97130e3a6531e11e0b5-text/javascript"></script>

<script src="${pageContext.request.contextPath}/gentelella/vendors/Flot/jquery.flot.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/gentelella/vendors/Flot/jquery.flot.pie.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/gentelella/vendors/Flot/jquery.flot.time.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/gentelella/vendors/Flot/jquery.flot.stack.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/gentelella/vendors/Flot/jquery.flot.resize.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/gentelella/vendors/flot.orderbars/js/jquery.flot.orderBars.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/gentelella/vendors/flot-spline/js/jquery.flot.spline.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/gentelella/vendors/flot.curvedlines/curvedLines.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/gentelella/vendors/DateJS/build/date.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/gentelella/vendors/moment/min/moment.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/gentelella/vendors/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/gentelella/build/js/custom.min.js" type="text/javascript"></script>

<script type="text/javascript">

$(function(){
	$(".child_menu a").click(function(){
		var url = "${pageContext.request.contextPath}/gentelella/docs/"+$(this).attr("dataUrl");
		
		$("#nyFrame").attr("src",url);
	})
})


(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
})(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

ga('create', 'UA-23581568-13', 'auto');
ga('send', 'pageview');

</script>

</body>
</html>