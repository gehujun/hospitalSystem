<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
			    <label for="exampleInputName2">活动目的地:</label>
			    <input type="text" name="tradest" class="form-control" id="tradestSearch" placeholder="活动目的地">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">活动出发地:</label>
			    <input type="text" name="trastart" class="form-control" id="trastartSearch" placeholder="活动出发地">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputEmail2">活动内容:</label>
			    <input type="text" name="tracontent" class="form-control" id="tracontentSearch" placeholder="活动内容">
			  </div>
			  <button id="btn_query" type="button" class="btn btn-success">查询</button>
			  <button id="btn_add" type="button" class="btn btn-success" onclick="addFun()">添加差旅活动</button>
			  
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
    		url:"${pageContext.request.contextPath}/travelactivities?method=query",//访问地址
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
    		    field: 'traid',
    		    title: '活动id',
    		    sortable:true
    		  }, {
    		    field: 'traname',
    		    title: ' 活动名字'
    		  },{
    		    field: 'tradest',
    		    title: ' 活动目的地'
    		  }, {
    		    field: 'trastart',
    		    title: '活动出发地'
    		  }, {
    		    field: 'tracontent',
    		    title: '活动内容'
    		  }, {
    		    field: 'trafee',
    		    title: '活动经费'
    		  },{
    		    field: 'tradate',
    		    title: '活动时间'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue =' <button  onclick="updateFun('+row.traid+')" type="button" class="btn btn-success">修改</NOtton>';
    				  var deleteValue =' <button  onclick="deleteFun('+row.traid+')" type="button" class="btn btn-success">删除</NOtton>';
    				  
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
	                var tradest = $("#tradestSearch").val();
	                var trastart = $("#trastartSearch").val();
	                var tracontent = $("#tracontentSearch").val();
	              
	                $.ajax({
	                     type: "post",//请求的方式
	                     url: "${pageContext.request.contextPath}/travelactivities?method=query",
	                     data: {tradest:tradest,trastart:trastart,tracontent:tracontent}, //查询条件
	                     dataType:"json",//返回的数据类型为json
	                     success : function(json) {
	                        $("#table").bootstrapTable('load', json);//主要是要这种写法
	                    }
	                });

	    });
  	
  	  
  	  
  })
  
   //修改
   function updateFun(traid){
	 
	  $.ajax({
		    type:"post",//提交方式
		    url:"${pageContext.request.contextPath}/travelactivities?method=sendUpdate",
		    data:{traid:traid},//传主键值
		    dataType:"json",//返回的数据类型为json
		    success:function(data){
		    	
		    	  //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    		
		    		   $("#traidUpdate").val(data.traid);
		    		   $("#tranameUpdate").val(data.traname);
		    		   $("#tradestUpdate").val(data.tradest);
		    		   $("#trastartUpdate").val(data.trastart);
		    		   $("#tracontentUpdate").val(data.tracontent);
		    		   $("#trafeeUpdate").val(data.trafee);
		    		   $("#tradateUpdate").val(data.tradate);
		    	})
		    }
	  })
  }
  
function deleteFun(traid){
	  $.ajax({
		  type:"post",
		  url:"${pageContext.request.contextPath}/travelactivities?method=delete",
		  data:{traid:traid},//传主键值
		  dataType:"json",//返回的数据类型为json
		  success:function(data){
			  location.reload(true);
		  },
		  error: function(xhr, type) {
		  }
	  })
  } 
  
  
//添加
function addFun(){
		    	
	  //弹出添加窗体
	  $('#myAddModal').modal().on('shown.bs.modal',function() {
		  
	  })
}
  
 </script>
 
 
<!-- 修改页面窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">

<input type="hidden" name="traid" id="traidUpdate">

<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog"  style="width: 60%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改差旅活动信息</h4>
            </div>
            <div class="modal-body">
                  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">活动目的地:</label>
					    <div class="col-sm-10">
					      <input type="text" name="tradest" class="form-control" id="tradestUpdate" placeholder="动目的地；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">活动名字:</label>
					    <div class="col-sm-10">
					      <input type="text" name="traname" class="form-control" id="tranameUpdate" placeholder="活动名字；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">活动出发地:</label>
					    <div class="col-sm-10">
					      <input type="text" name="trastart" class="form-control" id="trastartUpdate" placeholder="活动出发地；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">活动内容:</label>
					    <div class="col-sm-10">
					      <input type="text" name="tracontent" class="form-control" id="tracontentUpdate" placeholder="活动内容；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">活动经费:</label>
					    <div class="col-sm-10">
					      <input type="text" name="trafee" class="form-control" id="trafeeUpdate" placeholder="活动内容；">
					    </div>
				 </div>
				  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">活动时间:</label>
					    <div class="col-sm-10">
					      <input type="text" name="tradate" class="form-control" id="tradateUpdate" placeholder="活动内容；">
					    </div>
				 </div>
<!-- 				 <div class="form-group"> -->
<!-- 					    <label for="inputEmail3" class="col-sm-2 control-label">科室负责人:</label> -->
<!-- 					    <div class="col-sm-10"> -->
<!-- 					         <select id="userUpdate" name="userId"> -->
<!-- 					              <option value="1">张三</option> -->
<!-- 					              <option value="2">李四</option> -->
<!-- 					         </select> -->
<!-- 					    </div> -->
<!-- 				 </div> -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnUpadte" class="btn btn-primary">更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</form>
 
 
 
 
 
  
<!-- 添加窗体 -->
<!-- 模态框（Modal） -->
<form id="myAddForm" method="post">

<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myAddModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 60%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myAddModalLabel">添加差旅活动</h4>
            </div>
            <div class="modal-body">
            	<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">活动名字:</label>
					    <div class="col-sm-10">
					      <input type="text" name="traname" class="form-control" id="tranameAdd" placeholder="活动名字:">
					    </div>
				 </div>
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">活动目的地:</label>
					    <div class="col-sm-10">
					      <input type="text" name="tradest" class="form-control" id="tradestAdd" placeholder="活动目的地:">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">活动出发地:</label>
					    <div class="col-sm-10">
					      <input type="text" name="trastart" class="form-control" id="trastartAdd" placeholder="活动出发地:">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">活动内容:</label>
					    <div class="col-sm-10">
					      <input type="text" name="tracontent" class="form-control" id="tracontentAdd" placeholder="活动内容:">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">活动经费:</label>
					    <div class="col-sm-10">
					      <input type="text" name="trafee" class="form-control" id="trafeeAdd" placeholder="活动经费:">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">活动时间:</label>
					    <div class="col-sm-10">
					      <input type="text" name="tradate" class="form-control" id="tradateAdd" placeholder="活动时间:">
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
  $("#btnUpadte").click(function(){
	  
	    //获取表单中的数据
	     var travelactivities = $("#myUpdateForm").serializeArray();
	    
	    $.ajax({
	    	 type:"post",//提交方式
	    	 url:"${pageContext.request.contextPath}/travelactivities?method=update",
	    	 data:travelactivities,//数据
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
		//alert("sfdsf");
		//获取
		var workinghours = $("#myAddForm").serializeArray();
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/travelactivities?method=add",
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
  
</script>
<!-- 修改页面窗体 -->
 

</body>
</html>