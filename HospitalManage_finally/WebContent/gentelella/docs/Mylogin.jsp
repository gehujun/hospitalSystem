<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!--可无视-->
<link rel="stylesheet" href="${pageContext.request.contextPath }/plugin/css/bootstrap.3.3.7.css">


<!--主要样式-->
<link type="text/css" href="${pageContext.request.contextPath }/plugin/css/style.css" rel="stylesheet" />

<script type="text/javascript" src="${pageContext.request.contextPath }/plugin/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/plugin/js/bootstrap3.3.7.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/plugin/js/bootstrap-table.js"></script>

<script>
if(self!=top)
{
	parent.location.href='index.html';
}
function CheckLogin(obj){
	if(obj.username.value=='')
	{
		alert('请输入用户名');
		obj.username.focus();
		return false;
	}
	if(obj.password.value=='')
	{
		alert('请输入登录密码');
		obj.password.focus();
		return false;
	}
	return true;
}
</script>
</head>

<body>

<div class="container" align="right">
	<div class="col-md-6 col-md-offset-3" style="margin-top: 20%;">
		<div class="inset">
			<form name="login" id="login" method="post" action="${pageContext.request.contextPath}/user?method=login" onSubmit="return CheckLogin(document.login);">
				<input type="hidden" name="enews" value="login">
				<div>
					<h2>医院管理系统</h2>
					<span style="text-align: left;text-indent: 0.4em;"><label>用户名</label></span>
					<span><input type="text" name="userName" class="textbox" ></span>
				</div>
				<div>
					<span style="text-align: left;text-indent: 0.4em;"><label>密码</label></span>
					<span><input  name="userPwd" type="password" class="password"></span>
				</div>
				<div>
					<img id="imgObj" alt="" src="${pageContext.request.contextPath}/kaptcha.do" title="看不清，点击刷新">
					<input name="code" placeholder="验证码" required="" type="text">
				</div>
				<div class="sign">
					<input type="reset"  class="submit" value="重置"/>
					<input type="submit" value="登录" class="submit" />
				</div>
				
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function() {
	$("#imgObj").click(function(){
		   $(this).attr("src","${pageContext.request.contextPath}/kaptcha.do");
	   })
})

</script>

<script type="text/javascript">
if(document.login.equestion.value==0)
{
	showanswer.style.display='none';
}
</script>


</body>
</html>