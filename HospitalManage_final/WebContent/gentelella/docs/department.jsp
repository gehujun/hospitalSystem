<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


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

<div id="toolbar">
   <div id="showorhide" class="a">
		<form class="form-inline" id="mySearchForm">
		  <div class="form-group">
		    <label for="deptId">科室编号</label>
		    <input type="text" class="form-control" id="deptId" >
		  </div>
		  <div class="form-group">
		    <label for="deptName">科室名称</label>
		    <input type="text" class="form-control" id="deptName" >
		  </div>
		  <button type="button" class="btn btn-success" id="btn_query">查询</button>
		  <button type="button" class="btn btn-success" id="btn_add" onclick="$('#myAddModal').modal().on('shown.bs.modal')">添加科室</button>
		</form>
   
   </div>
   <button id="open" class="btn btn-success">展开或折叠</button>
</div>



<table id="table"></table>
<!-- 工具栏 -->
<div id="toolbar"></div>
<script type="text/javascript">
	//数据加载
    $(function(){
    	//表格数据的加载
    	$('#table').bootstrapTable({
    		  url: '${pageContext.request.contextPath }/department?method=query',
    		  //url: 'data.json',//访问json数据
    		  method: 'get',
    		  dataType: "json",
    		  toolbar:"#toolbar",//工具栏
    		  pagination:true,//允许分页
    		  pageSize:5,//每页显示3条
    		  pageList:[1,3,4,5,8,10],//每页显示的条数
    		//  search: true,//允许搜索
    		  striped:true,//各行换颜色
    		  showColumns:true,//可以需要显示的列
    		  showRefresh:true,//允许刷新
    		  showExport:true,//允许导出
    		  exportDataType:true,//可以全部导出
    		  exportTypes:['pdf','xlsx'],//导出的数据类型（pdf文件、excel文件）
    		  columns: [{
    		    field: 'deptname',
    		    title: '科室名称'
    		  }, {
    		    field: 'deptid',
    		    title: '科室编号'
    		  }, {
    		    field: 'deptadr',
    		    title: '科室地址'
    		  },  {
    		    field: 'depttel',
    		    title: '科室电话'
    		  }, {
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue='<button onclick="updateFun('+row.deptid+') " type="button" class="btn btn-success">修改</button>';
    				  var deleteValue='<button onclick="deleteFun('+row.deptid+') " type="button" class="btn btn-success">删除</button>';
    				  
    				  return updateValue+deleteValue;
    			  }
    		  }]
    		})
    })
    
    //查询请求
	$(function(){
    	$("#btn_query").click(function(){
        	//ajax请求后台
        	var deptName=$("#deptName").val();
    		var deptId=$("#deptId").val();
        	
        	$.ajax({
        		type:"post",
        		url:"${pageContext.request.contextPath }/department?method=query",
        		data:{ "deptName":deptName,"deptId":deptId},
        		dataType:"json",
        		success:function(json){
        				$("#table").bootstrapTable("load",json);
        			}
        	})
        });
    })
	
	//修改
	function updateFun(deptId){
		
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/department?method=sendUpdate",
			data:{deptId:deptId},
			dataType:"json",
			success:function(data){
				
				 //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    		
		    		   $("#deptIdUpdate").val(data.deptid);
		    		   $("#deptNameUpdate").val(data.deptname);
		    		   $("#deptAddressUpdate").val(data.deptadr);
		    		   $("#deptPhoneUpdate").val(data.depttel);
		    	})
				
			}	
		})
	}
	
	//删除
	function deleteFun(deptId){
		
		
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/department?method=delete",
			data:{deptId:deptId},
			success:function(data){
				$.ajax({
	        		type:"post",
	        		url:"${pageContext.request.contextPath }/department?method=query",
	        		data:{ "deptName":null,"deptId":null},
	        		dataType:"json",
	        		success:function(json){
	        				$("#table").bootstrapTable("load",json);
	        			}
	        	})
			}
		})
		
	}
	
	
 </script>
 
 
 <!-- 添加科室窗体 -->
<form id="myAddForm" method="post">

<input type="hidden" name="deptId" id="deptIdAdd">

<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">增加科室信息</h4>
            </div>
            <div class="modal-body">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">科室名称:</label>
					    <div class="col-sm-10">
					      <input type="text" name="deptName" class="form-control" id="deptNameAdd" placeholder="科室名称；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">科室地址:</label>
					    <div class="col-sm-10">
					      <input type="text" name="deptAddress" class="form-control" id="deptAddressAdd" placeholder="科室地址；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">科室电话:</label>
					    <div class="col-sm-10">
					      <input type="text" name="deptPhone" class="form-control" id="deptPhoneAdd" placeholder="科室电话；">
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

<input type="hidden" name="deptId" id="deptIdUpdate">

<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改科室信息</h4>
            </div>
            <div class="modal-body">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">科室名称:</label>
					    <div class="col-sm-10">
					      <input type="text" name="deptName" class="form-control" id="deptNameUpdate" placeholder="科室名称；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">科室地址:</label>
					    <div class="col-sm-10">
					      <input type="text" name="deptAddress" class="form-control" id="deptAddressUpdate" placeholder="科室地址；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">科室电话:</label>
					    <div class="col-sm-10">
					      <input type="text" name="deptPhone" class="form-control" id="deptPhoneUpdate" placeholder="科室电话；">
					    </div>
				 </div>
				 
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnUpadte" class="btn btn-primary">更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</form>
 
<script>
//提交更新按钮
$("#btnUpadte").click(function(){
	
    //获取表单中的数据
     var tDepartemnt = $("#myUpdateForm").serializeArray();
    
    $.ajax({
    	 type:"post",//提交方式
    	 url:"${pageContext.request.contextPath}/department?method=update",
    	 data:tDepartemnt,//数据
    	 dataType:"json",//返回的数据类型为json
    	 success:function(json){
    		 if(json ==1){
    			 alert("成功点击");
    			 $("#myUpdateModal").modal('hide');  //手动关闭
    			 $("#table").bootstrapTable("refresh");//刷新表格中的数据
    			 
    		 }
    	 }
    })
})
//提交添加按钮
$('#btnAdd').click(function(){
	//获取
	
	var tDepartemnt = $("#myAddForm").serializeArray();
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/department?method=add",
		data:tDepartemnt,
		dataType:"json",
		success:function(json){
			if(json ==1){
				alert("成功点击");
   			 $("#myAddModal").modal('hide');  //手动关闭
   			 $("#table").bootstrapTable("refresh");//刷新表格中的数据
			}
		}
	})
	
})

</script>

</body>
</html>