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
			    <label for="exampleInputName2">病区名称:</label>
			    <input type="text" name="wardname" class="form-control" id="wardnameSearch" placeholder="病区名称">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">值班人员:</label>
			    <input type="text" name="emp" class="form-control" id="empSearch" placeholder="值班人员">
			  </div>
			  <button id="btn_query" type="button" class="btn btn-success">查询</button>
			  <button id="btn_add" type="button" class="btn btn-success">添加</button>
			  
		 </form>
   </div>
   <button id="open" class="btn btn-success">展开或折叠</button>
</div>
<table id="table"></table>

<!-- 工具栏 -->
<div id="toolbar"></div>

<script type="text/javascript">
	
	$(function(){
	
	 //下拉数据加载  
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/dutytable?method=wardnameSelect",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#wardnameSelect");
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].wardname+ "'>"  
	                       + datas[i].wardname + "</option>");  
	           }  
	         
	       } 
	       
	   	});
	})
	
	$(function(){
	
	 //下拉数据加载  
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/dutytable?method=empSelect",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#empSelect");
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].empid+ "'>"  
	                       + datas[i].empname + "</option>");  
	           }  
	         
	       } 
	       
	   	});
	})

		$(function(){
	
	 //下拉数据加载  
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/dutytable?method=wardnameSelect",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#wardnameSelect1");
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].wardname+ "'>"  
	                       + datas[i].wardname + "</option>");  
	           }  
	         
	       } 
	       
	   	});
	})
	
	$(function(){
	
	 //下拉数据加载  
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/dutytable?method=empSelect",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#empSelect1");
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].empid+ "'>"  
	                       + datas[i].empname + "</option>");  
	           }  
	         
	       } 
	       
	   	});
	})
    
    $(function(){
    	 
    	$("#table").bootstrapTable({
    		url:"${pageContext.request.contextPath}/dutytable?method=query",//访问地址
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
    		    field: 'dutyid',
    		    title: '值班表号',
    		    sortable:true
    		  }, {
    		    field: 'dutydate',
    		    title: '日期'
    		  }, {
    		    field: 'wardname',
    		    title: '病区'
    		  },{
    		    field: 'emp.empname',
    		    title: '值班人员'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue =' <button  onclick="updateFun('+row.dutyid+')" type="button" class="btn btn-success">修改</button>';
    				  var deleteValue =' <button  onclick="deleteFun('+row.dutyid+')" type="button" class="btn btn-success">删除</button>';
    				  
    				  return updateValue+deleteValue;
    			  }
    		  }]
    	})
    	
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
	                alert("Yeah!");
	                var emp = $("#empSearch").val();
	                var wardname = $("#wardnameSearch").val();
	              
	               //var dutytable =  $("#mySearchForm").serializeArray();
	              
	                $.ajax({
	                     type: "post",//请求的方式
	                     url: "${pageContext.request.contextPath}/dutytable?method=query",
	                     data: {emp:emp,wardname:wardname}, //查询条件
	                     dataType:"json",//返回的数据类型为json
	                     success : function(json) {
	                        $("#table").bootstrapTable('load', json);//主要是要这种写法
	                    }
	                });

	    });
  	
  	  
  	  
  })
  


  	$(function(){
		$("#btn_add").click(function(){
			alert("开始调用");
			$('#myAddModal').modal().on('shown.bs.modal');
			
			//通过调用科室方法获取对象
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/dutytable?method=query",
				dataType : 'json',
	            success : function(datas) {//返回list数据并循环获取  	
	            	
	            	
	            }
						
	            	
	            })
	})
  	})

  //修改
   function updateFun(dutyid){
	 
	  $.ajax({
		    type:"post",//提交方式
		    url:"${pageContext.request.contextPath}/dutytable?method=sendUpdate",
		    data:{dutyid:dutyid},//传主键值
		    dataType:"json",//返回的数据类型为json
		    success:function(data){
		    	
		    	  //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {		    		
						
		    		   $("#dutydateupdate").val(data.dutydate);
		    		   $("#dutyidupdate").val(data.dutyid);
		    		   var opt1 = document.getElementById("wardnameSelect1");
		    		   for(i=0;i<opt1.length;i++){
		    			   if(opt1[i].value==data.wardname)
		    				   opt1[i].selected = true;
		    		   }
		    		   
		    		   var opt2 = document.getElementById("empSelect1");
		    		   
		    		   for(i=0;i<opt2.length;i++){
		    			   if(opt2[i].value==data.emp.empid)
		    				   opt2[i].selected = true;
		    		   }
		    		   
		    		
		    		   

		    	})
		    }
	  })
  }
  
  function deleteFun(dutyid) {
	  $.ajax({
		    type:"post",//提交方式
		    url:"${pageContext.request.contextPath}/dutytable?method=delete",
		    data:{dutyid:dutyid},//传主键值
		    dataType:"json",//返回的数据类型为json
		    success:function(data){
		    	location.reload(true);	 //刷新界面，刷新的是52行以下的界面   ${pageContext.request.contextPath}/department?method=query		
		    			
		    },
		    error:function(xhr,type){
		    	
		    }
		    	})
	  }
   
</script>




  
  
 <!-- 添加模态框 -->
<form id="myAddForm" method="post">

<!-- <input type="hidden" name="dutyid" id="dutyidupdate">  -->
<table class="table table-striped table-bordered"> </table>
<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

<div class="table-responsive">
  <div class="modal-dialog modal-sm1" style="width:550px;">
  	 <div class="modal-content">
  	 <div class="modal-header">
  	 <!-- 交互信息详情 -->
     	<label data-locale="i18n_interactiveInformationDetails" style="cursor: pointer;"></label>
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">添加值班信息</h4>
     </div>
            <div class="modal-body">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病区名称:</label>
					    <div class="col-sm-10">
					    
					       <select id="wardnameSelect" name="wardname" style="width:150px" class="form-control input-sm">
		      			
		     			</select>
			      </div>
			      <table class="table table-striped table-bordered">
       
    			 </table>
			</div>
			 	<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">值班人员:</label>
					    <div class="col-sm-10">
					          <select id="empSelect" name="emp" style="width:150px" class="form-control input-sm">
		      
		     			 </select>
					    </div>
					    <table class="table table-striped table-bordered">
       
    			 </table>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">值班时间:</label>
					    <div class="col-sm-10">
					      <input type="text" name="dutydate" style="width:150px"  class="form-control" id="dutydateadd" placeholder="值班时间">
					    </div>
					    <table class="table table-striped table-bordered">
       
    			 	</table>
				 </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnAdd" class="btn btn-primary">添加</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</div>
</div>
</form>

<!-- 更新模态框！！------------------------------------------ -->

<form id="myUpdateForm" method="post">

<input type="hidden" name="dutyid" id="dutyidupdate">

<table class="table table-striped table-bordered"> </table>
 <div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

<div class="table-responsive">
  <div class="modal-dialog modal-sm1" style="width:550px;">
  	 <div class="modal-content">
  	 <div class="modal-header">
  	 <!-- 交互信息详情 -->
     	<label data-locale="i18n_interactiveInformationDetails" style="cursor: pointer;"></label>
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">修改值班信息</h4>
     </div>
            <div class="modal-body">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病区名称:</label>
					    <div class="col-sm-10">
					    
					       <select id="wardnameSelect1" name="wardname" style="width:150px" class="form-control input-sm">
		      			
		     			</select>
			      </div>
			      <table class="table table-striped table-bordered">
       
    			 </table>
			</div>
			 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">值班人员:</label>
					    <div class="col-sm-10">
					          <select id="empSelect1" name="emp" style="width:150px" class="form-control input-sm">
		      
		     			 </select>
					    </div>
					    <table class="table table-striped table-bordered">
       
    			 </table>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">值班时间:</label>
					    <div class="col-sm-10">
					      <input type="text" name="dutydate" style="width:150px"  class="form-control" id="dutydateupdate" placeholder="值班时间">
					    </div>
					    <table class="table table-striped table-bordered">
       
    			 	</table>
				 </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnUpadte" class="btn btn-primary">更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</div>
</form>


<script type="text/javascript">
  $("#btnUpadte").click(function(){
	  
	    //获取表单中的数据
	     var dutytable = $("#myUpdateForm").serializeArray();
	    
	    $.ajax({
	    	 type:"post",//提交方式 
	    	 url:"${pageContext.request.contextPath}/dutytable?method=update",
	    	 data:dutytable,//数据
	    	 dataType:"json",//返回的数据类型为json
	    	 success:function(json){
	    		 alert(json);
	    		 if(json ==1){
	    			 
	    			 $("#myUpdateModal").modal('hide');  //手动关闭
	    			 $("#table").bootstrapTable("refresh");//刷新表格中的数据
	    			 
	    			 
	    		 }
	    	 }
	    })
  })
  
  
    $("#btnAdd").click(function(){
	  
    	
	    //获取表单中的数据
	     var dutytable = $("#myAddForm").serializeArray();
	    
	    $.ajax({
	    	 type:"post",//提交方式
	    	 url:"${pageContext.request.contextPath}/dutytable?method=add",
	    	 data:dutytable,//数据
	    	 dataType:"json",//返回的数据类型为json
	    	 success:function(json){
	    		 
	    		 if(json ==1){
	    			 $("#myAddModal").modal('hide');  //手动关闭
	    			 $("#table").bootstrapTable("refresh");//刷新表格中的数据
	    			 
	    			 
	    		 }
	    	 }
	    })
	    alert("添加成功");
  })
  
</script>
  
</body>
</html>