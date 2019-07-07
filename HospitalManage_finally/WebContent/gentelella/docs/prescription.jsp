<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>处方单</title>

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
			    <label for="exampleInputName2">处方单编号:</label>
			    <input type="text" name="presid" class="form-control" id="presidSearch" placeholder="处方单编号">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">药品编号:</label>
			    <input type="text" name="mediid" class="form-control" id="mediidSearch" placeholder="药品编号">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">药品名称:</label>
			    <input type="text" name="mediname" class="form-control" id="medinameSearch" placeholder="药品名称">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">所属医嘱号:</label>
			    <input type="text" name="presdocaid" class="form-control" id="presdocaidSearch" placeholder="所属医嘱号">
			  </div>
			  
			  <button id="btn_query" type="button" class="btn btn-success">查询</button>
			  <button id="btn_add" type="button" class="btn btn-success" onclick="addFun()">添加处方单</button>
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
    		url:"${pageContext.request.contextPath}/prescription?method=query",//访问地址
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
    		    field: 'presid',
    		    title: '处方单编号',
    		    sortable:true
    		  }, {
    		    field: 'mediid',
    		    title: '药品编号'
    		  },{
    		    field: 'medicine.mediname',
    		    title: '药品名称'
    		  }, {
    		    field: 'medinum',
    		    title: '药品数量'
    		  },{
    		    field: 'presdocaid',
    		    title: '所属医嘱号'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue =' <button  onclick="updateFun('+row.presid+')" type="button" class="btn btn-success">修改</button>';
    				  var deleteValue =' <button  onclick="deleteFun('+row.presid+')" type="button" class="btn btn-success">删除</button>';
    				  
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
	                var presid = $("#presidSearch").val();
	                var mediid = $("#mediidSearch").val();
	                var mediname = $("#medinameSearch").val();
	                var presdocaid = $("#presdocaidSearch").val();
	              
	                $.ajax({
	                     type: "post",//请求的方式
	                     url: "${pageContext.request.contextPath}/prescription?method=query",
	                     data: {presid:presid,mediid:mediid,mediname:mediname,presdocaid:presdocaid}, //查询条件
	                     dataType:"json",//返回的数据类型为json
	                     success : function(json) {
	                        $("#table").bootstrapTable('load', json);//主要是要这种写法
	                    }
	                });
					//药品名字来自药品表，暂时还没实现，没有写mediname的下拉菜单
	    });
  })
  
   //修改
   function updateFun(presid){
	  
	//下拉数据加载  所属医嘱来自医嘱表
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/doctoradvice?method=query",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#queryDoctorAdviceUpdate");
	           select.empty();
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].docaid+ "'>"  
	                       + datas[i].docaid + "</option>");  
	           }  
	         
	       } 
	       
	   	});
	
	  //下拉数据加载  药品名称来自药品表
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/medicine?method=query",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#queryMedicineUpdate");
	           select.empty();
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].mediid+ "'>"  
	                       + datas[i].mediname + "</option>");  
	           }  
	         
	       } 
	       
	   	});
	 
	  $.ajax({
		    type:"post",//提交方式
		    url:"${pageContext.request.contextPath}/prescription?method=sendUpdate",
		    data:{presid:presid},//传值
		    dataType:"json",//返回的数据类型为json
		    success:function(data){
		    	
		    	  //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    		
		    		   $("#presidUpdate").val(data.presid);
		    		   $("#mediidUpdate").val(data.mediid);
		    		   $("#medinumUpdate").val(data.medinum);
		    		   $("#presdocaidUpdate").val(data.presdocaid);
		    	})
		    }
	  })
	  
  }
  
  //删除
  function deleteFun(presid) {
	$.ajax({
		type:"post",//提交方式
	    url:"${pageContext.request.contextPath}/prescription?method=delete",
	    data:{presid:presid},//传值
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
	  
	//下拉数据加载  所属医嘱来自医嘱表
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/doctoradvice?method=query",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#queryDoctorAdviceAdd");
	           select.empty();
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].docaid+ "'>"  
	                       + datas[i].docaid + "</option>");  
	           }  
	         
	       } 
	       
	   	});
	
	  //下拉数据加载  药品名称来自药品表
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/medicine?method=query",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#queryMedicineAdd");
	           select.empty();
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].mediid+ "'>"  
	                       + datas[i].mediname + "</option>");  
	           }  
	         
	       } 
	       
	   	});
    	
    //弹出添加窗体
   	 $('#myAddModal').modal().on('shown.bs.modal',function() {	   
   		 
 	})
 }
  
 </script>
 
 


<!-- 修改页面窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">

<input type="hidden" name="presid" id="presidUpdate">

<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myUpdateModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myUpdateModalLabel">添加处方单</h4>
            </div>
            <div class="modal-body">
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">药品名称:</label>
					    <div class="col-sm-10">
					      <select id="queryMedicineUpdate" name="mediid" style="width:100px" class="form-control input-sm"></select>
					    </div>
				 </div>
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">药品数量:</label>
					    <div class="col-sm-10">
					      <input type="text" name="medinum" class="form-control" id="medinumUpdate" placeholder="药品数量：">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">所属医嘱号:</label>
					    <div class="col-sm-10">
					      <select id="queryDoctorAdviceUpdate" name="presdocaid" style="width:100px" class="form-control input-sm"></select>
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

<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myAddModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myAddModalLabel">添加处方单</h4>
            </div>
            <div class="modal-body">
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">药品名称:</label>
					    <div class="col-sm-10">
					      <select id="queryMedicineAdd" name="mediid" style="width:100px" class="form-control input-sm"></select>
					    </div>
				 </div>
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">药品数量:</label>
					    <div class="col-sm-10">
					      <input type="text" name="medinum" class="form-control" id="medinumAdd" placeholder="药品数量：">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">所属医嘱号:</label>
					    <div class="col-sm-10">
					      <select id="queryDoctorAdviceAdd" name="presdocaid" style="width:100px" class="form-control input-sm"></select>
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
	  $('#btnUpdate').click(function(){
		  
		    //获取表单中的数据
		     var prescription = $("#myUpdateForm").serializeArray();
		    
		    $.ajax({
		    	 type:"post",//提交方式
		    	 url:"${pageContext.request.contextPath}/prescription?method=update",
		    	 data:prescription,//数据
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
		var prescription = $("#myAddForm").serializeArray();
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/prescription?method=add",
			data:prescription,
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