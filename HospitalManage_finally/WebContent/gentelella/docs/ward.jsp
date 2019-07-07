<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>病区信息</title>
<!-- jsp包含指令 -->
<%@ include file="../../common/commoncss.jsp" %>
<%@ include file="../../common/commonjs.jsp" %>

<body>

<form class="form-inline" id = "mySearchForm">
	<div class="form-group">
	    <label for="exampleInputName">病区名:</label>
	    <input type="text" name="wardName" class="form-control" id="SearchBywardName" placeholder="病区名">
	    
	</div>
	<div class="form-group">
	    <label for="exampleInputName">负责人编号:</label>
	    <input type="text" name="empId" class="form-control" id="SearchByempId" placeholder="负责人编号">
	    
	</div>
	
		<button type="button" class="btn btn-success" id="searchbyInfro">确认</button>
		
</form>
<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myAddModal">
	添加病区
</button>
<form id="myAddForm" method="post">
<!-- <input type="hidden" name="mprId" id="mprIdUpdate"> -->
<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
	  <div class="modal-dialog modal-sm1" style="width:800px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					添加病区信息
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
				    <label for="inputEmail3" class="col-sm-2 control-label">病区名:</label>
				    <div class="col-sm-10">
				      <input type="text" name="wardName" class="form-control" id="AddwardName" placeholder="病区名">
				    </div>
	 			</div>
	 			<div class="form-group">
				    <label for="inputEmail3" class="col-sm-2 control-label">床位数:</label>
				    <div class="col-sm-10">
				      <input type="text" name="bedNum" class="form-control" id="AddbedNum" placeholder="床位数">
				    </div>
	 			</div>
	 			<div class="form-group">
				    <label for="inputEmail3" class="col-sm-2 control-label">负责人姓名:</label>
				      <div class="col-sm-10">
					      <select id="empNameSelect1" name="empId" style="width:100px" class="form-control input-sm">
					      </select>
	 				</div>
	 			</div>
	 			<div class="form-group">
				    <label for="inputEmail3" class="col-sm-2 control-label">空床数:</label>
				    <div class="col-sm-10">
				      <input type="text" name="emptyBedNum" class="form-control" id="AddemptyBedNum" placeholder="空床数">
				    </div>
	 			</div>
	 			
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary" id = "InfroAdd">添加
				</button>
			</div>
		</div>
	</div>
  </div>
</div>
</form>
<table id = "table">
</table>

<script type="text/javascript">
	$(function(){
	
	 //下拉数据加载  
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/user?method=query",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#empNameSelect1");
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].empid+ "'>"  
	                       + datas[i].empname + "</option>");  
	           }
	           var select = $("#empNameSelect2");
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].empid+ "'>"  
	                       + datas[i].empname + "</option>");  
	           }  
	         
	       } 
	       
	   	});
	})
	
	
	
	$(function(){
		//表格数据的加载
		$('#table').bootstrapTable({
			url: '${pageContext.request.contextPath}/ward?method=query',//访问json数据 访问后台代码返回json数据
			 pagination: true, //允许分页 注意大小写
			 pageSize:3,//每页显示三条数据
			 pageList:[1,3,4,5,8,10],//每页显示的条数 如果页面总共只有四条数据，5，8，10不会显示出来
			 /* search:true,  */
			 striped: true,//隔行换颜色
			 showColumns:true,//可以选择需要显示的列
			 showRefresh:true,//允许刷新
			 showExport:true,
			 exportDataType:true,//可以全部导出
			 exportTypes:['pdf','xlsx'],//导出文件的类型
			 /* toolbar:"#toolbar", */
			 //修改输出的内容
			  columns: [{
				field: 'wardname',
				title: '病区名',
				sortable:true
			  }, {
				field: 'bednum',
			    title: '床位数'
			  }, {
				field: 'emp.empname',
		    	title: '负责人姓名'
			  }, {
				field: 'emptybednum',
		    	title: '空床数'
			  }, { 
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue =' <button  onclick="updateFun(\''+row.wardname+'\')" type="button" class="btn btn-success">修改</button>';
    				  var deleteValue =' <button  onclick="deleteFun(\''+row.wardname+'\')" type="button" class="btn btn-success">删除</button>';
    				  
    				  return updateValue+deleteValue;
    			  }
    		  }]
		
			})
	})
</script>
<script type="text/javascript">

	$(function(){
		$("#searchbyInfro").click(function(){
			var wardName = $("#SearchBywardName").val();
			
			var empId = $("#SearchByempId").val();
			$.ajax({
				type: "post",
				url: "${pageContext.request.contextPath}/ward?method=query",
				data:{wardName:wardName,empId:empId},
				dataType:"json",
				success: function(json) {
					//document.write("<p>hello</p>");
					$("#table").bootstrapTable('load', json);
				}
			})
		})
	})
	
	function updateFun(wardName) { 
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/ward?method=sendUpdate",
			data:{wardName:wardName},
			dataType:"json",
			success:function(data) {
				
				
				$('#myUpdateModal').modal().on('shown.bs.modal',function() {
					   $("#bedNumUpdate").val(data.bednum);
					   $("#wardNameUpdate").val(data.wardname);
		    		   $("#emptyBedNumUpdate").val(data.emptybednum);
		    		   $("#empNameSelect2").val(data.emp.empid);

		    	})
		    	
			}
		})
	}
	
	function deleteFun(wardName) {
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/ward?method=delete",
			data:{wardName:wardName},
			dataType:"json",
			success:function(data) {
				
				alert("删除完成");
				/* location.reload(true); */
				$("#table").bootstrapTable("refresh");//刷新表格中的数据
			}
		})
	}
</script>

<form id="myUpdateForm" method="post">

<input type="hidden" name="wardName" id="wardNameUpdate">


<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

<div class="table-responsive">
  <div class="modal-dialog modal-sm1" style="width:800px;">
   <div class="modal-content">
   <div class="modal-header">
    
     <!-- 交互信息详情 -->
     <label data-locale="i18n_interactiveInformationDetails" style="cursor: pointer;"></label>
     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
     <h4 class="modal-title" id="myModalLabel">修改病区信息</h4>
   
   </div> 
   <div class="modal-body">
   	<div class="form-group">
    	<label for="inputEmail3" class="col-sm-2 control-label">负责人姓名:</label>
	      <div class="col-sm-10">	
		      <select id="empNameSelect2" name="empId" style="width:100px" class="form-control input-sm">
		  
		      </select>
		 </div>
	</div>
	 <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">床位数:</label>
		    <div class="col-sm-10">
		      <input type="text" name="bedNum" class="form-control" id="bedNumUpdate" placeholder="床位数">
		    </div>
	 </div>
	 <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">空床数:</label>
		    <div class="col-sm-10">
		      <input type="text" name="emptyBedNum" class="form-control" id="emptyBedNumUpdate" placeholder="空床数">
		    </div>
	 </div>
      <table class="table table-striped table-bordered">
       
     </table>
    </div>
    <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" id="InfroUpadte" class="btn btn-primary">更改</button>
    </div>
   </div>
  </div>
 </div>
</div>
</form>

<script type="text/javascript">
  $("#InfroUpadte").click(function(){
	  	
	    //获取表单中的数据
	     var ward = $("#myUpdateForm").serializeArray();
	    //alert(inpatient);
	    //document.write(inpatient);
	    $.ajax({
	    	 type:"post",//提交方式
	    	 url:"${pageContext.request.contextPath}/ward?method=update",
	    	 data:ward,//数据
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
<script type="text/javascript">
  $("#InfroAdd").click(function(){
	  	
	    //获取表单中的数据
	     var ward = $("#myAddForm").serializeArray();
	    //alert(inpatient);
	    //document.write(inpatient);
	    $.ajax({
	    	 type:"post",//提交方式
	    	 url:"${pageContext.request.contextPath}/ward?method=add",
	    	 data:ward,//数据
	    	 dataType:"json",//返回的数据类型为json
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