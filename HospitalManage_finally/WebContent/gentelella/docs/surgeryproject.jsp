<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手术项目管理</title>

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

<div id="toolbar">
   <div id="showorhide" class="a">
          <form class="form-inline" id="mySearchForm">
			  <div class="form-group">
			    <label for="exampleInputName2">手术编号:</label>
			    <input type="text" name="surId" class="form-control" id="surIdSearch" placeholder="手术编号">
			  </div>
			  
			  <div class="form-group">
			    <label for="exampleInputEmail2">手术室:</label>
			    <select name="surRoom" id="surRoomSearch">
			        <option value="">请选择</option>
			    </select>
			  </div>
			  
			  <div class="form-group">
			    <label for="exampleInputEmail2">医师:</label>
			    <select name="surDoc" id="surDocSearch">
			        <option value="">请选择</option>
			    </select>
			  </div>
			  
			  <button id="btn_query" type="button" class="btn btn-success">查询</button>
			  <button id="btn_add" type="button" class="btn btn-success" >添加职称</button>
			  
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
		url:"${pageContext.request.contextPath}/surgeryproject?method=query",//访问地址
		method:"get",//请求的方式
		toolbar:"#toolbar",//工具栏
		pagination:true,//允许分页
		pageSize:8,//每页显示的条数
		pageList:[2,3,5,8,10],//每页显示的条数集合
		showColumns:true,//是否显示所有的列
		showRefresh:true,//是否显示刷新
		striped:true,//隔行变色   每一行的唯一标识，一般为主键列
	    showExport:true,//允许导出
		    exportDataType:'all',//可以全部导出
		    exportTypes:['pdf','excel'],//导出的数据类型（pdf文件、excel文件）
		columns: [{
		    field: 'surid',
		    title: '手术编号',
		    sortable:true
		  }, {
		    field: 'maid',
		    title: '医嘱编号'
		  }, {
		    field: 'name',
		    title: '手术名称'
		  },{
		    field: 'inpatient.pat.patname',
		    title: '病人名称'
		  },{
		    field: 'surroomid',
		    title: '手术室'
		  },{
		    field: 'employee.empname',
		    title: '手术医师'
		  },{
			  title:"操作",
			  formatter:function(value,row,index){
				  var updateValue =' <button  onclick="updateFun('+row.surid+')" type="button" class="btn btn-success">修改</button>';
				  var deleteValue =' <button  onclick="deleteFun('+row.surid+')" type="button" class="btn btn-success">删除</button>';
				  
				  return updateValue+deleteValue;
			  }
		  }]
	})
	
	//--展开或折叠   #:id名称     .:class名称

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

	
	//手术室
	$.ajax({  
        type : 'get',  
        url : "${pageContext.request.contextPath}/surgeryproject?method=querySurRoom",
        dataType : 'json',      
        success : function(datas) {//返回list数据并循环获取  
        	
            var select = $("#surRoomSearch");
            for (var i = 0; i < datas.length; i++) { 
            	select.append("<option value='" +datas[i].surroomid+ "'>"  
                        + datas[i].surroomid + "</option>");  
            }  
          
        } 
    });
	//医师
	$.ajax({  
        type : 'get',  
        url : "${pageContext.request.contextPath}/user?method=query",  
        dataType : 'json',      
        success : function(datas) {//返回list数据并循环获取 
        	
            var select = $("#surDocSearch");
            for (var i = 0; i < datas.length; i++) { 
            	select.append("<option value='" +datas[i].empid+ "'>"  
                        + datas[i].empname + "</option>");  
            }
          
        }
    });
	
	
})

</script>

<!-- 搜索 -->
<script type="text/javascript">
$(function(){
	  <!--搜索条件-->
	//条件搜索
		$("#btn_query").click(function() {
					//获取表单中的数据
				     var searchPosition = $("#mySearchForm").serializeArray();
	                $.ajax({
	                     type: "post",//请求的方式
	                     url: "${pageContext.request.contextPath}/surgeryproject?method=query",
	                     data: searchPosition, //查询条件
	                     dataType:"json",//返回的数据类型为json
	                     success : function(json) {
	                        $("#table").bootstrapTable('load', json);//主要是要这种写法
	                    }
	                });
	    });
	})
</script>

<!-- 删除 -->
<script type="text/javascript">
function deleteFun(surid) {
	$.ajax({
		type:"post",//提交方式
	    url:"${pageContext.request.contextPath}/surgeryproject?method=delete",
	    data:{surid:surid},//传主键值
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
</script>

<!-- 增加 -->
<script type="text/javascript">
$(function(){
	$("#btn_add").click(function(){
		$('#myAddModal').modal().on('shown.bs.modal');
		//医嘱
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/surgeryproject?method=maidSelect",
			dataType : 'json',
            success : function(datas) {//返回list数据并循环获取  
            	document.getElementById("maIdAdd").options.length = 0;//清空选项
                var select = $("#maIdAdd");
                for (var i = 0; i < datas.length; i++) { 
                	select.append("<option value='" +datas[i].maid+ "'>"
                            + datas[i].maid + "</option>");  
                }  
              
            }
		})
		//手术室
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/surgeryproject?method=querySurRoom",
			dataType : 'json',
            success : function(datas) {//返回list数据并循环获取  
            	document.getElementById("surRoomIdAdd").options.length = 0;//清空选项
                var select = $("#surRoomIdAdd");
                for (var i = 0; i < datas.length; i++) { 
                	select.append("<option value='" +datas[i].surroomid+ "'>"
                            + datas[i].surroomid + "</option>");  
                }  
              
            }
		})
		//手术医师
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/user?method=query",
			dataType : 'json',
            success : function(datas) {//返回list数据并循环获取  
            	document.getElementById("chiefSurgeonAdd").options.length = 0;//清空选项
                var select = $("#chiefSurgeonAdd");
                for (var i = 0; i < datas.length; i++) { 
                	select.append("<option value='" +datas[i].empid+ "'>"
                            + datas[i].empname + "</option>");  
                }
            }
		})
		//手术病人
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/patient?method=query",
			dataType : 'json',
            success : function(datas) {//返回list数据并循环获取  
            	document.getElementById("surPatAdd").options.length = 0;//清空选项
                var select = $("#surPatAdd");
                for (var i = 0; i < datas.length; i++) { 
                	select.append("<option value='" +datas[i].patid+ "'>"
                            + datas[i].patname + "</option>");  
                }
            }
		})
		
		
	})
})
</script>

<!-- 修改 -->
<script type="text/javascript">
function updateFun(surid){
	
		$('#myUpdateModal').modal().on('shown.bs.modal');
		//医嘱
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/surgeryproject?method=maidSelect",
			dataType : 'json',
            success : function(datas) {//返回list数据并循环获取  
            	document.getElementById("maIdUpdate").options.length = 0;//清空选项
                var select = $("#maIdUpdate");
                for (var i = 0; i < datas.length; i++) { 
                	select.append("<option value='" +datas[i].maid+ "'>"
                            + datas[i].maid + "</option>");  
                }  
              
            }
		})
		//手术室
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/surgeryproject?method=querySurRoom",
			dataType : 'json',
            success : function(datas) {//返回list数据并循环获取  
            	document.getElementById("surRoomIdUpdate").options.length = 0;//清空选项
                var select = $("#surRoomIdUpdate");
                for (var i = 0; i < datas.length; i++) { 
                	select.append("<option value='" +datas[i].surroomid+ "'>"
                            + datas[i].surroomid + "</option>");  
                }  
              
            }
		})
		//手术医师
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/user?method=query",
			dataType : 'json',
            success : function(datas) {//返回list数据并循环获取  
            	document.getElementById("chiefSurgeonUpdate").options.length = 0;//清空选项
                var select = $("#chiefSurgeonUpdate");
                for (var i = 0; i < datas.length; i++) { 
                	select.append("<option value='" +datas[i].empid+ "'>"
                            + datas[i].empname + "</option>");  
                }
            }
		})
		//手术病人
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/patient?method=query",
			dataType : 'json',
            success : function(datas) {//返回list数据并循环获取  
            	document.getElementById("surPatUpdate").options.length = 0;//清空选项
                var select = $("#surPatUpdate");
                for (var i = 0; i < datas.length; i++) { 
                	select.append("<option value='" +datas[i].patid+ "'>"
                            + datas[i].patname + "</option>");  
                }
            }
		})
	
		//初始化模态框
		$.ajax({
		    type:"post",//提交方式
		    url:"${pageContext.request.contextPath}/surgeryproject?method=sendUpdate",
		    data:{surid:surid},//传主键值
		    dataType:"json",//返回的数据类型为json
		    success:function(data){
		    	 alert("update");
		    	  //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    		
		    		   $("#suridUpdate").val(data.surid);
		    		   $("#nameUpdate").val(data.name);
		    		   $("#surDateUpdate").val(data.surdate);
		    		   
		    		   var opt = document.getElementById("maIdUpdate");
		   		   		for(i=0;i<opt.length;i++){
		   			   	if(opt[i].value==data.maid)
		   				   opt[i].selected = true;
		   		   		}
			   		   	var opt = document.getElementById("surRoomIdUpdate");
		   		   		for(i=0;i<opt.length;i++){
		   			   	if(opt[i].value==data.surroomid)
		   				   opt[i].selected = true;
		   		   		}
			   		   	var opt = document.getElementById("surPatUpdate");
		   		   		for(i=0;i<opt.length;i++){
		   			   	if(opt[i].value==data.inpatid)
		   				   opt[i].selected = true;
		   		   		}
			   		   	var opt = document.getElementById("chiefSurgeonUpdate");
		   		   		for(i=0;i<opt.length;i++){
		   			   	if(opt[i].value==data.chiefsurgeon)
		   				   opt[i].selected = true;
		   		   		}
		    	})
		    }
	  })
}
</script>

<!-- 添加窗体 -->
<!-- 模态框（Modal） -->
<form id="myAddForm" method="post">
<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加手术案例</h4>
            </div>
            <div class="modal-body">
                   
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">医嘱:</label>
					    <div class="col-sm-10">
					    	<select class="form-control" name="maId" id="maIdAdd">
					      		<option value="0">请选择</option>
					      	</select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术名称:</label>
					    <div class="col-sm-10">
					   
					    <input type="text" name="name" class="form-control" id="nameAdd" placeholder="手术名称:"> 
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术室:</label>
					    <div class="col-sm-10">
					    	<select class="form-control" name="surRoomId" id="surRoomIdAdd">
					      		<option value="0">请选择</option>
					      	</select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术病人:</label>
					    <div class="col-sm-10">
					    	<select class="form-control" name="surPat" id="surPatAdd">
					      		<option value="0">请选择</option>
					      	</select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术医师:</label>
					    <div class="col-sm-10">
					    	<select class="form-control" name="chiefSurgeon" id="chiefSurgeonAdd">
					      		<option value="0">请选择</option>
					      	</select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术时间:</label>
					    <div class="col-sm-10">
					    	<input type="text" name="surDate" class="form-control" id="surDateAdd" placeholder="手术时间:"> 
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

<!-- 添加窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">
<input type="hidden" name="surid" id="suridUpdate">
<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改手术案例</h4>
            </div>
            <div class="modal-body">
                   
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">医嘱:</label>
					    <div class="col-sm-10">
					    	<select class="form-control" name="maId" id="maIdUpdate">
					      		<option value="0">请选择</option>
					      	</select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术名称:</label>
					    <div class="col-sm-10">
					   
					    <input type="text" name="name" class="form-control" id="nameUpdate" placeholder="手术名称:"> 
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术室:</label>
					    <div class="col-sm-10">
					    	<select class="form-control" name="surRoomId" id="surRoomIdUpdate">
					      		<option value="0">请选择</option>
					      	</select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术病人:</label>
					    <div class="col-sm-10">
					    	<select class="form-control" name="surPat" id="surPatUpdate">
					      		<option value="0">请选择</option>
					      	</select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术医师:</label>
					    <div class="col-sm-10">
					    	<select class="form-control" name="chiefSurgeon" id="chiefSurgeonUpdate">
					      		<option value="0">请选择</option>
					      	</select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术时间:</label>
					    <div class="col-sm-10">
					    	<input type="text" name="surDate" class="form-control" id="surDateUpdate" placeholder="手术时间:"> 
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

<!-- 提交按钮 -->
<script type="text/javascript">
$('#btnAdd').click(function(){
	//获取
	alert("成功点击");
	
	var porf = $("#myAddForm").serializeArray();
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/surgeryproject?method=add",
		data:porf,
		dataType:"json",
		success:function(json){
			if(json ==1){
   			 
   			 $("#myAddModal").modal('hide');  //手动关闭
   			 $("#table").bootstrapTable("refresh");//刷新表格中的数据
			}
		}
	})
})

$("#btnUpdate").click(function(){
	    //获取表单中的数据
	    var professionaltitle = $("#myUpdateForm").serializeArray();
	    
	    $.ajax({
	    	 type:"post",//提交方式
	    	 url:"${pageContext.request.contextPath}/surgeryproject?method=update",
	    	 data:professionaltitle,//数据
	    	 dataType:"json",//返回的数据类型为json
	    	 success:function(json){
	    		 
	    		 if(json ==1){
	    			 
	    			 $("#myUpdateModal").modal('hide');  //手动关闭
	    			 $("#table").bootstrapTable("refresh");//刷新表格中的数据

	    		 }
	    	 }
	    })
	  })
</script>


</body>
</html>