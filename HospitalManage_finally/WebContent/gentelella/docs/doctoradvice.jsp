<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医嘱信息</title>
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
			    <label for="exampleInputName2">医嘱编号:</label>
			    <input type="text" name="docaid" class="form-control" id="docaidSearch" placeholder="医嘱编号">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">病人编号:</label>
			    <input type="text" name="patid" class="form-control" id="patidSearch" placeholder="病人编号">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">病人姓名:</label>
			    <input type="text" name="patname" class="form-control" id="patnameSearch" placeholder="病人姓名">
			  </div>
			  
			  <div class="form-group">
			    <label for="exampleInputEmail2">开具医生编号:</label>
			    <input type="text" name="docaempdocid" class="form-control" id="docaempdocidSearch" placeholder="医生编号">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">医生姓名:</label>
			    <input type="text" name="empname" class="form-control" id="empnameSearch" placeholder="医生姓名">
			  </div>
			  
			  <div class="form-group">
			    <label for="exampleInputEmail2">开具日期:</label>
			    <input type="text" name="docadate" class="form-control" id="docadateSearch" placeholder="开具日期">
			  </div>
			  <button id="btn_query" type="button" class="btn btn-success">查询</button>
			  <button id="btn_add" type="button" class="btn btn-success" onclick="addFun()">添加医嘱</button>
			  
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
    		url:"${pageContext.request.contextPath}/doctoradvice?method=query",//访问地址
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
    		    field: 'docaid',
    		    title: '医嘱编号',
    		    sortable:true
    		  }, {
    		    field: 'patid',
    		    title: '病人编号'
    		  },{
    		    field: 'patient.patname',
    		    title: '病人姓名'
    		  }, {
    		    field: 'docaillnessexplain',
    		    title: '病情描述'
    		  },{
    		    field: 'docatreatmentscheme',
    		    title: '治疗方案'
    		  },{
    		    field: 'docaempdocid',
    		    title: '医生编号'
    		  },{
    		    field: 'employee.empname',
    		    title: '医生姓名'
    		  },{
    		    field: 'docadate',
    		    title: '开具日期'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue =' <button  onclick="updateFun('+row.docaid+')" type="button" class="btn btn-success">修改</button>';
    				  var deleteValue =' <button  onclick="deleteFun('+row.docaid+')" type="button" class="btn btn-success">删除</button>';
    				  
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
	                var docaid = $("#docaidSearch").val();
	                var patid = $("#patidSearch").val();
	                var patname = $("#patnameSearch").val();
	                var docaempdocid = $("#docaempdocidSearch").val();
	                var empname = $("#empnameSearch").val();
	                var docadate = $("#docadateSearch").val();
	              
	                $.ajax({
	                     type: "post",//请求的方式
	                     url: "${pageContext.request.contextPath}/doctoradvice?method=query",
	                     data: {docaid:docaid,patid:patid,patname:patname,docaempdocid:docaempdocid,empname:empname,docadate:docadate}, //查询条件
	                     dataType:"json",//返回的数据类型为json
	                     success : function(json) {
	                        $("#table").bootstrapTable('load', json);//主要是要这种写法
	                    }
	                });
	
	    });
  })
  
   //修改
   function updateFun(docaid){
	  
	    //下拉数据加载  //医生名字来自员工表，empname的下拉菜单
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
	    
	    //病人姓名
		$.ajax({  
			  type : 'get',  
			  url : "${pageContext.request.contextPath}/patient?method=query",
			  dataType : 'json',      
			  success : function(datas) {//返回list数据并循环获取  
			  	
			      var select = $("#queryPatientUpdate");
			      select.empty();
			      for (var i = 0; i < datas.length; i++) { 
			      		select.append("<option value='" +datas[i].patid+ "'>"  
			                  + datas[i].patname + "</option>");  
			      }  
			    
			  } 
			  
		    });
	  
	  $.ajax({
		    type:"post",//提交方式
		    url:"${pageContext.request.contextPath}/doctoradvice?method=sendUpdate",
		    data:{docaid:docaid},//传值
		    dataType:"json",//返回的数据类型为json
		    success:function(data){
		    	
		    	  //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    		
		    		   $("#docaidUpdate").val(data.docaid);
		    		   $("#patidUpdate").val(data.patid);
		    		   $("#docaillnessexplainUpdate").val(data.docaillnessexplain);
		    		   $("#docatreatmentschemeUpdate").val(data.docatreatmentscheme);
		    		   $("#docaempdocidUpdate").val(data.docaempdocid);
		    		   $("#docadateUpdate").val(data.docadate);

		    	})
		    }
	  })
	  
  }
  
  //删除
  function deleteFun(docaid) {
	$.ajax({
		type:"post",//提交方式
	    url:"${pageContext.request.contextPath}/doctoradvice?method=delete",
	    data:{docaid:docaid},//传值
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
	  
	//下拉数据加载  //医生名字来自员工表，empname的下拉菜单
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
	
	    //病人姓名
		$.ajax({  
			  type : 'get',  
			  url : "${pageContext.request.contextPath}/patient?method=query",
			  dataType : 'json',      
			  success : function(datas) {//返回list数据并循环获取  
			  	
			      var select = $("#queryPatientAdd");
			      select.empty();
			      for (var i = 0; i < datas.length; i++) { 
			      		select.append("<option value='" +datas[i].patid+ "'>"  
			                  + datas[i].patname + "</option>");  
			      }  
			    
			  } 
			  
		 });
	 
	  $.ajax({
		    type:"post",//提交方式
		    url:"${pageContext.request.contextPath}/doctoradvice?method=add",
		    //data:,
		    dataType:"json",//返回的数据类型为json
		    success:function(data){
		    	
		    	  //弹出添加窗体
		    	 $('#myAddModal').modal().on('shown.bs.modal',function() {
		    		
		    		   $("#patidAdd").val(data.patid);
		    		   $("#docadateAdd").val(data.docadate);
		    		   $("#docaillnessexplainAdd").val(data.docaillnessexplain);
		    		   $("#docaillnessexplainAdd").val(data.docaillnessexplain);
		    		   $("#docatreatmentschemeAdd").val(data.docatreatmentscheme);
		    		   $("#docaempdocidAdd").val(data.docaempdocid);
		    		   
		    	})
		    }
	  })
 }
  
 </script>

<!-- 修改页面窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">

<input type="hidden" name="docaid" id="docaidUpdate">

<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改医嘱</h4>
            </div>
            <div class="modal-body">
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病人姓名:</label>
					    <div class="col-sm-10">
					      <select id="queryPatientUpdate" name="patid" style="width:100px" class="form-control input-sm"></select>
					    </div>
				 </div>
				 
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病情描述:</label>
					    <div class="col-sm-10">
					      <input type="text" name="docaillnessexplain" class="form-control" id="docaillnessexplainUpdate" placeholder="病情描述：">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">治疗方案:</label>
					    <div class="col-sm-10">
					      <input type="text" name="docatreatmentscheme" class="form-control" id="docatreatmentschemeUpdate" placeholder="治疗方案；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">医生姓名:</label>
					    <div class="col-sm-10">
					      <select id="queryEmployeeUpdate" name="docaempdocid" style="width:100px" class="form-control input-sm"></select>
					    </div>
				 </div>
				 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">开具日期:</label>
					    <div class="col-sm-10">
					      <input type="text" name="docadate" class="form-control" id="docadateUpdate" placeholder="开具日期：">
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

<!-- 添加医嘱窗体 -->
<!-- 模态框（Modal） -->
<form id="myAddForm" method="post">

<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加医嘱</h4>
            </div>
            <div class="modal-body">
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病人姓名:</label>
					    <div class="col-sm-10">
					      <select id="queryPatientAdd" name="patid" style="width:100px" class="form-control input-sm"></select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">开具日期:</label>
					    <div class="col-sm-10">
					      <input type="text" name="docadate" class="form-control" id="docadateAdd" placeholder="开具日期:">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病情描述:</label>
					    <div class="col-sm-10">
					      <input type="text" name="docaillnessexplain" class="form-control" id="docaillnessexplainAdd" placeholder="病情描述:">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">治疗方案:</label>
					    <div class="col-sm-10">
					      <input type="text" name="docatreatmentscheme" class="form-control" id="docatreatmentschemeAdd" placeholder="治疗方案:">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">医生姓名:</label>
					    <div class="col-sm-10">
					      <select id="queryEmployeeAdd" name="docaempdocid" style="width:100px" class="form-control input-sm"></select>
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
  $(function(){
	  $("#btnUpadte").click(function(){
		  
		    //获取表单中的数据
		     var doctoradvice = $("#myUpdateForm").serializeArray();
		    
		    $.ajax({
		    	 type:"post",//提交方式
		    	 url:"${pageContext.request.contextPath}/doctoradvice?method=update",
		    	 data:doctoradvice,//数据
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
		var doctoradvice = $("#myAddForm").serializeArray();
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/doctoradvice?method=add",
			data:doctoradvice,
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