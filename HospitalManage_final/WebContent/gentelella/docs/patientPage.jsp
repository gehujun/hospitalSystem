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
	    <label for="patId">病人编号</label>
	    <input type="text" class="form-control" id="patId" >
	  </div>
	  <div class="form-group">
	    <label for="patName">病人名称</label>
	    <input type="text" class="form-control" id="patName" >
	  </div>
	  <button type="button" class="btn btn-success" id="btn_query">查询</button>
	  <button type="button" class="btn btn-success" id="btn_add" onclick="$('#myAddModal').modal().on('shown.bs.modal')">添加病人</button>
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
    		  url: '${pageContext.request.contextPath }/patient?method=query',
    		  //url: 'data.json',//访问json数据
    		  method: 'get',
    		  toolbar:"#toolbar",//工具栏
    		  dataType: "json",
    		  pagination:true,//允许分页
    		  pageSize:3,//每页显示3条
    		  pageList:[1,3,4,5,8,10],//每页显示的条数
    		//  search: true,//允许搜索
    		  striped:true,//各行换颜色
    		  showColumns:true,//可以需要显示的列
    		  showRefresh:true,//允许刷新
    		  showExport:true,//允许导出
    		  exportDataType:true,//可以全部导出
    		  exportTypes:['pdf','xlsx'],//导出的数据类型（pdf文件、excel文件）
    		  columns: [{
    		    field: 'patid',
    		    title: '病人名称'
    		  }, {
    		    field: 'patname',
    		    title: '病人编号'
    		  }, {
    		    field: 'patsex',
    		    title: '病人性别'
    		  }, {
    		    field: 'patage',
    		    title: '病人年龄'
    		  }, {
    		    field: 'patlinkmantel',
    		    title: '病人电话'
    		  }, {
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue='<button onclick="updateFun('+row.patid+') " type="button" class="btn btn-success">修改</button>';
    				  var deleteValue='<button onclick="deleteFun('+row.patid+') " type="button" class="btn btn-success">删除</button>';
    				  
    				  return updateValue+deleteValue;
    			  }
    		  }]
    		})
    })
    //查询请求
	$(function(){
    	$("#btn_query").click(function(){
        	//ajax请求后台
        	var patName=$("#patName").val();
    		var patId=$("#patId").val();
        	
        	$.ajax({
        		type:"post",
        		url:"${pageContext.request.contextPath }/patient?method=query",
        		data:{ "patName":patName,"patId":patId},
        		dataType:"json",
        		success:function(json){
        				$("#table").bootstrapTable("load",json);
        			}
        	})
        });
    })

	
	//修改
	function updateFun(patid){
		
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/patient?method=sendUpdate",
			data:{patid:patid},
			dataType:"json",
			success:function(data){
				
				 //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    		
		    		   $("#patIdUpdate").val(data.patid);
		    		   $("#patNameUpdate").val(data.patname);
		    		   $("#patSexUpdate").val(data.patsex);
		    		   $("#patAgeUpdate").val(data.patage);
		    		   
		    	})
			}	
		})
	}
	
	//删除
	function deleteFun(patid){
		
    $.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/patient?method=delete",
			data:{patid:patid},
			success:function(data){
				$.ajax({
	        		type:"post",
	        		url:"${pageContext.request.contextPath }/patient?method=query",
	        		data:{ "patid":null,"patname":null},
	        		dataType:"json",
	        		success:function(json){
	        				$("#table").bootstrapTable("load",json);
	        			}
	        	})
			}
		})
	}
	
	
 </script>
 

<!-- 修改病人窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">

<input type="hidden" name="patid" id="patIdUpdate">

<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改科室信息</h4>
            </div>
            <div class="modal-body">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病人名称:</label>
					    <div class="col-sm-10">
					      <input type="text" name="patName" class="form-control" id="patNameUpdate" placeholder="病人名称；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病人性别:</label>
					    <div class="col-sm-10">
					      <input type="text" name="patSex" class="form-control" id="patSexUpdate" placeholder="病人性别">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病人年龄:</label>
					    <div class="col-sm-10">
					      <input type="text" name="patAge" class="form-control" id="patAgeUpdate" placeholder="病人年龄；">
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
 
 
<form id="myAddForm" method="post">

<input type="hidden" name="patId" id="patIdAdd">

<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">增加病人</h4>
            </div>
            <div class="modal-body">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病人名称:</label>
					    <div class="col-sm-10">
					      <input type="text" name="patName" class="form-control" id="patNameAdd" placeholder="病人名称；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病人性别:</label>
					    <div class="col-sm-10">
					      <input type="text" name="patSex" class="form-control" id="patSexAdd" placeholder="病人性别；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病人年龄:</label>
					    <div class="col-sm-10">
					      <input type="text" name="patAge" class="form-control" id="patAgeAdd" placeholder="病人年龄；">
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
 
 <script type="text/javascript">
 
//提交更新按钮
 $("#btnUpadte").click(function(){
 	alert("成功点击");
     //获取表单中的数据
      var patient = $("#myUpdateForm").serializeArray();
     
     $.ajax({
     	 type:"post",//提交方式
     	 url:"${pageContext.request.contextPath}/patient?method=update",
     	 data:patient,//数据
     	 dataType:"json",//返回的数据类型为json
     	 success:function(json){
     		 if(json ==1){
     			 
     			 $("#myUpdateModal").modal('hide');  //手动关闭
     			 $("#table").bootstrapTable("refresh");//刷新表格中的数据
     			 
     		 }
     	 }
     })
 })
 
 //提交添加按钮
$('#btnAdd').click(function(){
	//获取
	alert("成功点击");
	var patient = $("#myAddForm").serializeArray();
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/patient?method=add",
		data:patient,
		dataType:"json",
		success:function(json){
			if(json ==1){
   			 
   			 $("#myAddModal").modal('hide');  //手动关闭
   			 $("#table").bootstrapTable("refresh");//刷新表格中的数据
			}
		}
	})
})
 	
 </script>
 
</body>
</html>