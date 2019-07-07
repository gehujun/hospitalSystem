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

<div id="toolbar">
   <div id="showorhide" class="a">
          <form class="form-inline" id="mySearchForm">
			  <div class="form-group">
			    <label for="exampleInputName2">供应商名字:</label>
			    <input type="text" name="supname" class="form-control" id="supnameSearch" placeholder="供应商名字">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">供应商地址:</label>
			    <input type="text" name="supaddr" class="form-control" id="supaddrSearch" placeholder="供应商地址">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputEmail2">供应商电话:</label>
			    <input type="text" name="suptel" class="form-control" id="suptelSearch" placeholder="供应商电话">
			  </div>
			  <button id="btn_query" type="button" class="btn btn-success">查询</button>
			  <button id="btn_add" type="button" class="btn btn-success " onclick="addFun()">添加</button>
			  
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
    		url:"${pageContext.request.contextPath}/supplier?method=query",//访问地址
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
    		    field: 'supid',
    		    title: '供应商号',
    		    sortable:true
    		  }, {
    		    field: 'supname',
    		    title: ' 供应商名称'
    		  }, {
    		    field: 'supaddr',
    		    title: '供应商地址'
    		  }, {
    		    field: 'suptel',
    		    title: '供应商电话'
    		  }, {
    		    field: 'supcreditconditions',
    		    title: '信贷状况'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue =' <button  onclick="updateFun('+row.supid+')" type="button" class="btn btn-success">修改</button>';
    				  var deleteValue =' <button  onclick="deleteFun('+row.supid+')" type="button" class="btn btn-success">删除</button>';
    				  
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
	                var supname = $("#supnameSearch").val();
	                var supaddr = $("#supaddrSearch").val();
	                var suptel = $("#suptelSearch").val();
	              
	                $.ajax({
	                     type: "post",//请求的方式
	                     url: "${pageContext.request.contextPath}/supplier?method=query",
	                     data: {supname :supname ,supaddr:supaddr,suptel:suptel}, //查询条件
	                     dataType:"json",//返回的数据类型为json
	                     success : function(json) {
	                        $("#table").bootstrapTable('load', json);//主要是要这种写法
	                    }
	                });

	    });
  	  
  	  
  })
  
   //修改
   function updateFun(supid){
	 
	  $.ajax({
		    type:"post",//提交方式
		    url:"${pageContext.request.contextPath}/supplier?method=sendUpdate",
		    data:{supid:supid},//传主键值
		    dataType:"json",//返回的数据类型为json
		    success:function(data){
		    	
		    	  //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    		
		    		   $("#supidUpdate").val(data.supid);
		    		   $("#supnameUpdate").val(data.supname);
		    		   $("#supaddrUpdate").val(data.supaddr);
		    		   $("#suptelUpdate").val(data.suptel);
		    	})
		    }
	  })
  }
	  
	  
	  //添加
	function addFun(){
			    	
		  //弹出添加窗体
		  $('#myAddModal').modal().on('shown.bs.modal',function() {
			  
		  })
	 }
  
  
  
  function deleteFun(supid){
	  $.ajax({
		  type:"post",
		  url:"${pageContext.request.contextPath}/supplier?method=delete",
		  data:{supid:supid},//传主键值
		  dataType:"json",//返回的数据类型为json
		  success:function(data){
			  location.reload(true);
		  }
	  })
  }
  
 </script>
 
 
 
 
 
<!-- 修改页面窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">

<input type="hidden" name="supid" id="supidUpdate">

<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog"   style="width: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改供应商信息</h4>
            </div>
            <div class="modal-body">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">名称:</label>
					    <div class="col-sm-10">
					      <input type="text" name="supname" class="form-control" id="supnameUpdate" placeholder="供应商名称；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">地址:</label>
					    <div class="col-sm-10">
					      <input type="text" name="supaddr" class="form-control" id="supaddrUpdate" placeholder="供应商地址；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">供应商电话:</label>
					    <div class="col-sm-10">
					      <input type="text" name="suptel" class="form-control" id="suptelUpdate" placeholder="供应商电话；">
					    </div>
				 </div>
<!-- 				 <div class="form-group"> -->
<!-- 					    <label for="inputEmail3" class="col-sm-2 control-label">信贷状况:</label> -->
<!-- 					    <div class="col-sm-10"> -->
<!-- 					         <select id="userUpdate" name="userId"> -->
<!-- 					              <option value="1">张三</option> -->
<!-- 					              <option value="2">李四</option> -->
<!-- 					         </select> -->
<!-- 					    </div> -->
<!-- 				 </div> -->

<!-- 				 <div class="form-group"> -->
<!-- 					    <label for="inputEmail3" class="col-sm-2 control-label">信贷状况:</label> -->
<!-- 					    <div class="col-sm-10"> -->
<!-- 					      <input type="text" name="supCreditConditions" class="form-control" id="supCreditConditionsUpdate" placeholder="信贷状况；"> -->
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
 
 
<!--  添加 的模态框-->
 <form id="myAddForm" method="post">

<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">增加供应商</h4>
            </div>
            <div class="modal-body">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">供应商名称:</label>
					    <div class="col-sm-10">
					      <input type="text" name="supname" class="form-control" id="supnameAdd" placeholder="供应商名称；">
					    </div>
				 </div>

				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">供应商地址:</label>
					    <div class="col-sm-10">
					      <input type="text" name="supaddr" class="form-control" id="supaddrAdd" placeholder="供应商地址；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">供应商电话:</label>
					    <div class="col-sm-10">
					      <input type="text" name="suptel" class="form-control" id="suptelAdd" placeholder="供应商电话；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">供应商信贷:</label>
					    <div class="col-sm-10">
					      <input type="text" name="supcreditconditions" class="form-control" id="supcreditconditionsAdd" placeholder="供应商信贷；">
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
	     var tSupplier = $("#myUpdateForm").serializeArray();
	    
	    $.ajax({
	    	 type:"post",//提交方式
	    	 url:"${pageContext.request.contextPath}/supplier?method=update",
	    	 data:tSupplier,//数据
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
			url:"${pageContext.request.contextPath}/supplier?method=add",
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