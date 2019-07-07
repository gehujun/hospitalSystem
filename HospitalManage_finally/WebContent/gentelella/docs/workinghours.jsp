<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>值班时间安排</title>

<!-- jsp包含指令 -->
<%@ include file="../../common/commoncss.jsp" %>
<%@ include file="../../common/commonjs.jsp" %>
<style type="text/css">
  .a{
     display: inline;//显示
  }
  .b{
     display: none;//隐藏
  }
</style>

</head>
<body>

<!-- 搜索工具栏 -->
<div id="toolbar">
   <div id="showorhide" class="a">
          <form class="form-inline" id="mySearchForm">
          	  <div class="form-group">
			    <label for="exampleInputName2">值班安排编号:</label>
			    <input type="text" name="workid" class="form-control" id="workidSearch" placeholder="值班安排编号">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">员工编号:</label>
			    <input type="text" name="workempid" class="form-control" id="workempidSearch" placeholder="员工编号">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">值班人姓名:</label>
			    <input type="text" name="empname" class="form-control" id="empnameSearch" placeholder="值班人姓名">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputEmail2">值班日期:</label>
			    <input type="text" name="worktime" class="form-control" id="worktimeSearch" placeholder="值班日期">
			  </div>
			  <button id="btn_query" type="button" class="btn btn-success">查询</button>
			  <button id="btn_add" type="button" class="btn btn-success" onclick="addFun()">添加排班</button>
			  
		 </form>
   </div>
   <button id="open" class="btn btn-success">展开或折叠</button>
</div>
<table id="table"></table>

<!-- 工具栏 -->
<div id="toolbar"></div>

<script type="text/javascript">

    $(function(){
    	 
    	$("#table").bootstrapTable({
    		url:"${pageContext.request.contextPath}/workinghours?method=query",//访问地址
    		method:"get",//请求的方式
    		toolbar:"#toolbar",//工具栏
    		pagination:true,//允许分页
    		pageSize:3,//每页显示的条数
    		pageList:[1,3,4,5,8,10],//每页显示的条数集合
    		showColumns:true,//是否显示所有的列
    		showRefresh:true,//是否显示刷新
    		striped:true,//隔行变色   每一行的唯一标识，一般为主键列
		    showExport:true,//允许导出
  		    exportDataType:true,//'all',//可以全部导出
  		    exportTypes:['pdf','xlsx'],//导出的数据类型（pdf文件、excel文件）
    		columns: [{
    		    field: 'workid',
    		    title: '值班安排编号',
    		    sortable:true
    		  }, {
    		    field: 'workempid',
    		    title: '员工编号'
    		  }, {
    		    field: 'employee.empname',
    		    title: '值班人姓名'
    		  }, {
    		    field: 'worktime',
    		    title: '值班日期'
    		  }, {
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue =' <button  onclick="updateFun('+row.workid+')" type="button" class="btn btn-success">修改</button>';
    				  var deleteValue =' <button  onclick="deleteFun('+row.workid+')" type="button" class="btn btn-success">删除</button>';
    				  
    				  return updateValue+deleteValue;
    			  }
    		  }]
    	})
    	
    	<!--展开或折叠   #:id名称     .:class名称-->
    	$("#open").click(function(){
    		
    		 //判断  attr：属性   a:显示      b：隐藏
    		 if($("#showorhide").attr("class")=="a"){
    			 
    			 $("#showorhide").attr("class","b");//由显示变为隐藏
    			 $("#showorhide").slideDown("fast");//向下快速滑动
    		 }else{
    			 $("#showorhide").attr("class","a");//由显示变为隐藏
    			 $("#showorhide").slideUp("fast");//向上快速滑动
    		 }
    	})
    })
</script>

<script type="text/javascript">
  $(function(){
	  <!--搜索条件-->
  	    //条件搜索
		$("#btn_query").click(function() {
					var workid = $("#workidSearch").val();
	                var workempid = $("#workempidSearch").val();
	                var empname = $("#empnameSearch").val();
	                var worktime = $("#worktimeSearch").val();
	              
	                $.ajax({
	                     type: "post",//请求的方式
	                     url: "${pageContext.request.contextPath}/workinghours?method=query",
	                     data: {workid:workid,workempid:workempid,empname:empname,worktime:worktime}, //查询条件
	                     dataType:"json",//返回的数据类型为json
	                     success : function(json) {
	                        $("#table").bootstrapTable('load', json);//主要是要这种写法
	                    }
	                });
	    });
  })
  
   //修改
   function updateFun(workid){
	  
	//下拉数据加载  值班人名字来自员工表，empname的下拉菜单
		$.ajax({  
		  type : 'get',  
		  url : "${pageContext.request.contextPath}/user?method=query",
		  dataType : 'json',      
		  success : function(datas) {//返回list数据并循环获取  
		  	
		      var select = $("#queryEmployeeUpdate");
		      select.empty();
		      for (var i = 0; i < datas.length; i++) { 
		      		select.append("<option value='" +datas[i].empid+ "'>"  
		                  + datas[i].empname + "</option>");  
		      }  
		    
		  } 
		  
	    });
	 
	  $.ajax({
		    type:"post",//提交方式
		    url:"${pageContext.request.contextPath}/workinghours?method=sendUpdate",
		    data:{workid:workid},//传值
		    dataType:"json",//返回的数据类型为json
		    success:function(data){
		    	
		    	  //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    			//alert(data.workid);
		    		   $("#workidUpdate").val(data.workid);
		    		   $("#workempidUpdate").val(data.workempid);
		    		   $("#worktimeUpdate").val(data.worktime);
		    	})
		    }
	  })
	  
  }
  
  //删除
  function deleteFun(workid) {
	$.ajax({
		type:"post",//提交方式
	    url:"${pageContext.request.contextPath}/workinghours?method=delete",
	    data:{workid:workid},//传值
	    dataType:"json",//返回的数据类型为json
	    success : function(data) {
            //location.reload(true);
            $("#myUpdateModal").modal('hide');  //手动关闭
	    	$("#table").bootstrapTable("refresh");//刷新表格中的数据
            },
            error:function(xhr,type){
            	
            }
	})
  }
  
  //添加
  function addFun(){
	  
	//下拉数据加载  值班人名字来自员工表，empname的下拉菜单
		$.ajax({  
		  type : 'get',  
		  url : "${pageContext.request.contextPath}/user?method=query",
		  dataType : 'json',      
		  success : function(datas) {//返回list数据并循环获取  
		  	
		      var select = $("#queryEmployeeAdd");
		      select.empty();
		      for (var i = 0; i < datas.length; i++) { 
		      		select.append("<option value='" +datas[i].empid+ "'>"  
		                  + datas[i].empname + "</option>");  
		      }  
		    
		  } 
		  
	    });
		    	
	  //弹出添加窗体
	  $('#myAddModal').modal().on('shown.bs.modal',function() {
		   $("#workidAdd").val(data.workid);
		   $("#workempidAdd").val(data.workempid);
		   $("#worktimeAdd").val(data.worktime);
	  })
 }
  
  
 </script>
 


<!-- 添加排班窗体 -->
<!-- 模态框（Modal） -->
<form id="myAddForm" method="post">

<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myAddModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myAddModalLabel">添加排班</h4>
            </div>
            <div class="modal-body">
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">值班人姓名:</label>
					    <div class="col-sm-10">
					      <select id="queryEmployeeAdd" name="workempid" style="width:100px" class="form-control input-sm"></select>
					      <table class="table table-striped table-bordered"></table>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">值班日期:</label>
					    <div class="col-sm-10">
					      <input type="text" name="worktime" class="form-control" id="worktimeAdd" placeholder="值班日期:">
					      <table class="table table-striped table-bordered"></table>
					    </div>
				 </div>
				 
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnAdd" class="btn btn-primary">添加</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</form>

<!-- 修改页面窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">

<input type="hidden" name="workid" id="workidUpdate">

<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myUpdateModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myUpdateModalLabel">修改排班情况</h4>
            </div>
            <div class="modal-body">
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">值班人姓名:</label>
					    <div class="col-sm-10">
					      <select id="queryEmployeeUpdate" name="workempid" style="width:100px" class="form-control input-sm"></select>
					      <table class="table table-striped table-bordered"></table>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">值班日期:</label>
					    <div class="col-sm-10">
					      <input type="text" name="worktime" class="form-control" id="worktimeUpdate" placeholder="日期；">
					      <table class="table table-striped table-bordered"></table>
					    </div>
				 </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnUpdate" class="btn btn-primary">更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</form>

<script type="text/javascript">
  $(function(){
	  $("#btnUpdate").click(function(){
		  
		    //获取表单中的数据
		    var workinghours = $("#myUpdateForm").serializeArray();
		    //alert(workinghours.workid);
		    $.ajax({
		    	 type:"post",//提交方式
		    	 url:"${pageContext.request.contextPath}/workinghours?method=update",
		    	 data:workinghours,//数据
		    	 dataType:"json",//返回的数据类型为json
		    	 success:function(json){
		    		 
		    		 if(json == 1){
		    			 
		    			 $("#myUpdateModal").modal('hide');  //手动关闭
		    			 $("#table").bootstrapTable("refresh");//刷新表格中的数据

		    		 }
		    	 }
		    })
	  })
	  
	   //提交添加按钮
	$('#btnAdd').click(function(){
		//alert("sfdsf");
		//获取
		var workinghours = $("#myAddForm").serializeArray();
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/workinghours?method=add",
			data:workinghours,
			dataType:"json",
			success:function(json){
				if(json == 1){
	   			 
	   			 $("#myAddModal").modal('hide');  //手动关闭
	   			 $("#table").bootstrapTable("refresh");//刷新表格中的数据
				}
			}
		})
		
	})  
  })
  
</script>
    
</body>
</html>