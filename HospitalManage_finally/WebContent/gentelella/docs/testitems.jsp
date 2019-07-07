<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>检验项目</title>

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
			    <label for="exampleInputName2">检验项目编号:</label>
			    <input type="text" name="testid" class="form-control" id="testidSearch" placeholder="检验项目编号">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">检验项目名称:</label>
			    <input type="text" name="testname" class="form-control" id="testnameSearch" placeholder="检验项目名称">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">检验类型:</label>
			    <input type="text" name="testtype" class="form-control" id="testtypeSearch" placeholder="检验类型">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">检验费用:</label>
			    <input type="text" name="testfee" class="form-control" id="testfeeSearch" placeholder="检验费用">
			  </div>
			  
			  <button id="btn_query" type="button" class="btn btn-success">查询</button>
			  <button id="btn_add" type="button" class="btn btn-success" onclick="addFun()">添加检验项目</button>
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
    		url:"${pageContext.request.contextPath}/testitems?method=query",//访问地址
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
    		    field: 'testid',
    		    title: '检验项目编号',
    		    sortable:true
    		  }, {
    		    field: 'testname',
    		    title: '检验项目名称'
    		  },{
    		    field: 'testtype',
    		    title: '检验项目类型'
    		  }, {
    		    field: 'testfee',
    		    title: '检验费用'
    		  },{
    		    field: 'testexplain',
    		    title: '检验内容'
    		  },{
    		    field: 'testtaboo',
    		    title: '检验注意事项'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue =' <button  onclick="updateFun('+row.testid+')" type="button" class="btn btn-success">修改</button>';
    				  var deleteValue =' <button  onclick="deleteFun('+row.testid+')" type="button" class="btn btn-success">删除</button>';
    				  
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
	                var testid = $("#testidSearch").val();
	                var testname = $("#testnameSearch").val();
	                var testtype = $("#testtypeSearch").val();
	                var testfee = $("#testfeeSearch").val();
	              
	                $.ajax({
	                     type: "post",//请求的方式
	                     url: "${pageContext.request.contextPath}/testitems?method=query",
	                     data: {testid:testid,testname:testname,testtype:testtype,testfee:testfee}, //查询条件
	                     dataType:"json",//返回的数据类型为json
	                     success : function(json) {
	                        $("#table").bootstrapTable('load', json);//主要是要这种写法
	                    }
	                });
					
	    });
  })
  
   //修改
   function updateFun(testid){
	 
	  $.ajax({
		    type:"post",//提交方式
		    url:"${pageContext.request.contextPath}/testitems?method=sendUpdate",
		    data:{testid:testid},//传值
		    dataType:"json",//返回的数据类型为json
		    success:function(data){
		    	
		    	  //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    		
		    		   $("#testidUpdate").val(data.testid);
		    		   $("#testnameUpdate").val(data.testname);
		    		   $("#testtypeUpdate").val(data.testtype);
		    		   $("#testfeeUpdate").val(data.testfee);
		    		   $("#testexplainUpdate").val(data.testexplain);
		    		   $("#testtabooUpdate").val(data.testtaboo);
		    	})
		    }
	  })
	  
  }
  
  //删除
  function deleteFun(testid) {
	$.ajax({
		type:"post",//提交方式
	    url:"${pageContext.request.contextPath}/testitems?method=delete",
	    data:{testid:testid},//传值
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
		    	
   	//弹出添加窗体
   	$('#myAddModal').modal().on('shown.bs.modal',function() {
   
   	})
 }
  
 </script>

<!-- 修改页面窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">

<input type="hidden" name="testid" id="testidUpdate">

<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myUpdateModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myUpdateModalLabel">修改检验项目</h4>
            </div>
            <div class="modal-body">
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">检验项目名称:</label>
					    <div class="col-sm-10">
					      <input type="text" name="testname" class="form-control" id="testnameUpdate" placeholder="检验项目名称">
					      <table class="table table-striped table-bordered"></table>
					    </div>
				 </div>
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">检验项目类型:</label>
					    <div class="col-sm-10">
					      <input type="text" name="testtype" class="form-control" id="testtypeUpdate" placeholder="检验项目类型">
					      <table class="table table-striped table-bordered"></table>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">检验费用:</label>
					    <div class="col-sm-10">
					      <input type="text" name="testfee" class="form-control" id="testfeeUpdate" placeholder="检验费用">
					      <table class="table table-striped table-bordered"></table>
					    </div>
                 </div>
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">检验内容:</label>
					    <div class="col-sm-10">
					      <input type="text" name="testexplain" class="form-control" id="testexplainUpdate" placeholder="检验内容">
					     <table class="table table-striped table-bordered"></table>
					    </div>
                 </div>
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">检验注意事项:</label>
					    <div class="col-sm-10">
					      <input type="text" name="testtaboo" class="form-control" id="testtabooUpdate" placeholder="检验注意事项">
					     <table class="table table-striped table-bordered"></table>
					    </div>
                 </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnUpdate" class="btn btn-primary">修改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</div>
</form>

<!-- 添加页面窗体 -->
<!-- 模态框（Modal） -->
<form id="myAddForm" method="post">

<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myAddModalLabel">添加检验项目</h4>
            </div>
            <div class="modal-body">
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">检验项目名称:</label>
					    <div class="col-sm-10">
					      <input type="text" name="testname" class="form-control" id="testnameAdd" placeholder="检验项目名称">
					   	  <table class="table table-striped table-bordered"></table>
					    </div>
				 </div>
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">检验项目类型:</label>
					    <div class="col-sm-10">
					      <input type="text" name="testtype" class="form-control" id="testtypeAdd" placeholder="检验项目类型：">
					      <table class="table table-striped table-bordered"></table>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">检验费用:</label>
					    <div class="col-sm-10">
					      <input type="text" name="testfee" class="form-control" id="testfeeAdd" placeholder="检验费用；">
					     <table class="table table-striped table-bordered"></table>
					    </div>
                 </div>
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">检验内容:</label>
					    <div class="col-sm-10">
					      <input type="text" name="testexplain" class="form-control" id="testexplainAdd" placeholder="检验内容">
					     <table class="table table-striped table-bordered"></table>
					    </div>
                 </div>
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">检验注意事项:</label>
					    <div class="col-sm-10">
					      <input type="text" name="testtaboo" class="form-control" id="testtabooAdd" placeholder="检验注意事项">
					     <table class="table table-striped table-bordered"></table>
					    </div>
                 </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnAdd" class="btn btn-primary">添加</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</div>
</form>

<script type="text/javascript">
  $(function(){
	  $("#btnUpdate").click(function(){
		  
		    //获取表单中的数据
		     var testitems = $("#myUpdateForm").serializeArray();
		    
		    $.ajax({
		    	 type:"post",//提交方式
		    	 url:"${pageContext.request.contextPath}/testitems?method=update",
		    	 data:testitems,//数据
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
		//获取
		var testitems = $("#myAddForm").serializeArray();
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/testitems?method=add",
			data:testitems,
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