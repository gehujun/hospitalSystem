<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>挂号单号</title>
<%@ include file="../../common/commoncss.jsp" %>
<%@ include file="../../common/commonjs.jsp" %>

<style type="text/css">
  .a{
     display: inline;//显示
  }
  .b{
     display: none;//隐藏
  }
  
  .modal-dialog .modal-content .modal-body{
  	height:auto;
  }
  
</style>

<script type="text/javascript">
var maxModalHeight = document.documentElement.clienceHeight - 170 + 'px';
$("#maxModalHeight").css({
               'max-height': 'maxModalHeight',
               'overflow' : 'auto'
  });
</script>

</head>
<body>

<!-- 搜索栏 -->
<div id="toolbar">
   <div id="showorhide" class="a">
          <form class="form-inline" id="mySearchForm">
			  <div class="form-group">
			    <label for="exampleInputName2">挂单号id:</label>
			    <input type="text" class="form-control" name="regId" id="regId" placeholder="挂单号">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">挂单日期:</label>
			    <input type="text" class="form-control" name="regDate" id="regDate" placeholder="挂单日期">
			  </div>
			  <div class="form-group">			  
			  	<label for="exampleInputEmail2">病人:</label>
			    <select name="regPatName" id="regPatNameSearch">
			        <option value="0">请选择</option>
			    </select>
			  </div>
			  <div class="form-group">
			  	<label for="exampleInputEmail2">科室:</label>
			    <select name="deptId" id="regDeptSearch">
			        <option value="0">请选择</option>
			    </select>
			  </div>
			  <button id="btn_query" type="button" class="btn btn-success">查询</button>
			  <button id="btn_add" type="button" class="btn btn-success">添加</button>
		 </form>
   </div>
   <button id="open" class="btn btn-success">展开或折叠</button>
   
</div>

<!-- 挂号表单 -->
<table id="table"></table>
<!-- 工具栏 -->
<div id="toolbar"></div>
<script type="text/javascript">
//数据加载
$(function(){
    	//表格数据的加载
    	$('#table').bootstrapTable({
    		  url: '${pageContext.request.contextPath }/Registrationform?method=query',
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
    		    field: 'regid',
    		    title: '挂单编号'
    		  }, {
    		    field: 'regDate',
    		    title: '挂单日期'
    		  }, {
    		    field: 'regfee',
    		    title: '挂单费用'
    		  }, {
    		    field: 'dept.deptname',
    		    title: '挂单科室'
    		  }, {
    		    field: 'doc.empname',
    		    title: '挂单医生'
    		  }, {
    		    field: 'emp.empname',
    		    title: '挂单处理人'
    		  }, {
    		    field: 'patient.patname',
    		    title: '挂单病人'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue='<button onclick="updateFun('+row.regid+') " type="button" class="btn btn-success">修改</button>';
    				  var deleteValue='<button onclick="deleteFun('+row.regid+') " type="button" class="btn btn-success">删除</button>';
    				  
    				  return updateValue+deleteValue;
    			  }
    		  }]
    		})
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
	
	//科室下拉
	$.ajax({  
        type : 'get',  
        url : "${pageContext.request.contextPath}/department?method=query",  
        dataType : 'json',      
        success : function(datas) {//返回list数据并循环获取  
        	
            var select = $("#regDeptSearch");
            for (var i = 0; i < datas.length; i++) { 
            	select.append("<option value='" +datas[i].deptid+ "'>"  
                        + datas[i].deptname + "</option>");  
            }  
          
        } 
        
    });

	//病人下拉
	$.ajax({  
        type : 'get',  
        url : "${pageContext.request.contextPath}/patient?method=query",  
        dataType : 'json',      
        success : function(datas) {//返回list数据并循环获取  
        	
            var select = $("#regPatNameSearch");
            for (var i = 0; i < datas.length; i++) { 
            	select.append("<option value='" +datas[i].patid+ "'>"  
                        + datas[i].patname + "</option>");  
            }  
          
        } 
        
    });

//添加按钮的事件
	$(function(){
		
		
		$("#btn_add").click(function(){
			
			$('#myAddModal').modal().on('shown.bs.modal');
			//病人下拉
			$.ajax({  
	        type : 'get',  
	        url : "${pageContext.request.contextPath}/patient?method=query",  
	        dataType : 'json',      
	        success : function(datas) {//返回list数据并循环获取  
	        	document.getElementById("regPatNameAdd").options.length = 0;//清空选项
	            var select = $("#regPatNameAdd");
	            for (var i = 0; i < datas.length; i++) { 
	            	select.append("<option value='" +datas[i].patid+ "'>"  
	                        + datas[i].patname + "</option>");  
	            	}  
	        	}
	    	});
			//医生下拉
			$.ajax({  
	        type : 'get',  
	        url : "${pageContext.request.contextPath}/user?method=query",  
	        dataType : 'json',
	        success : function(datas) {//返回list数据并循环获取  
	        	document.getElementById("regDocNameAdd").options.length = 0;//清空选项
	            var select = $("#regDocNameAdd");
	            for (var i = 0; i < datas.length; i++) { 
	            	select.append("<option value='" +datas[i].empid+ "'>"  
	                        + datas[i].empname + "</option>");  
	            	}  
	        	}
	    	});
			
			//通过调用科室方法获取对象
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/department?method=query",
				dataType : 'json',
	            success : function(datas) {//返回list数据并循环获取  
	            	document.getElementById("regDeptNameAdd").options.length = 0;//清空选项
	                var select = $("#regDeptNameAdd");
	                for (var i = 0; i < datas.length; i++) { 
	                	select.append("<option value='" +datas[i].deptid+ "'>"
	                            + datas[i].deptname + "</option>");  
	                }
	            }
			});
			//处理人
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/user?method=query",
				dataType : 'json',
	            success : function(datas) {//返回list数据并循环获取  
	            	document.getElementById("empNameAdd").options.length = 0;//清空选项
	                var select = $("#empNameAdd");
	                for (var i = 0; i < datas.length; i++) { 
	                	select.append("<option value='" +datas[i].empid+ "'>"
	                            + datas[i].empname + "</option>");  
	                }
	            }
			})
		})
	})
	
	

//查询请求
	$(function(){
    	$("#btn_query").click(function(){
        	//ajax请求后台
        	var regId=$("#regId").val();
    		var regDate=$("#resDate").val();
    		
    		var searchPosition = $("#mySearchForm").serializeArray();
        	$.ajax({
        		type:"post",
        		url:"${pageContext.request.contextPath }/Registrationform?method=query",
        		data:searchPosition,
        		dataType:"json",
        		success:function(json){
        				$("#table").bootstrapTable("load",json);
        			}
        	})
        	
        	
        });
    })

//修改
	function updateFun(regid){
	//通过调用科室方法获取对象 regDeptNameUpdate
		
		$('#myUpdateModal').modal().on('shown.bs.modal');
			//病人下拉
			$.ajax({  
	        type : 'get',  
	        url : "${pageContext.request.contextPath}/patient?method=query",  
	        dataType : 'json',      
	        success : function(datas) {//返回list数据并循环获取  
	        	document.getElementById("regPatNameUpdate").options.length = 0;//清空选项
	            var select = $("#regPatNameUpdate");
	            for (var i = 0; i < datas.length; i++) { 
	            	select.append("<option value='" +datas[i].patid+ "'>"  
	                        + datas[i].patname + "</option>");  
	            	}  
	        	}
	    	});
			//医生下拉
			$.ajax({  
	        type : 'get',  
	        url : "${pageContext.request.contextPath}/user?method=query",  
	        dataType : 'json',
	        success : function(datas) {//返回list数据并循环获取  
	        	document.getElementById("regDocNameUpdate").options.length = 0;//清空选项
	            var select = $("#regDocNameUpdate");
	            for (var i = 0; i < datas.length; i++) { 
	            	select.append("<option value='" +datas[i].empid+ "'>"  
	                        + datas[i].empname + "</option>");  
	            	}  
	        	}
	    	});
			
			//通过调用科室方法获取对象
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/department?method=query",
				dataType : 'json',
	            success : function(datas) {//返回list数据并循环获取  
	            	document.getElementById("regDeptNameUpdate").options.length = 0;//清空选项
	                var select = $("#regDeptNameUpdate");
	                for (var i = 0; i < datas.length; i++) { 
	                	select.append("<option value='" +datas[i].deptid+ "'>"
	                            + datas[i].deptname + "</option>");  
	                }
	            }
			});
			//处理人
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/user?method=query",
				dataType : 'json',
	            success : function(datas) {//返回list数据并循环获取  
	            	document.getElementById("empNameUpdate").options.length = 0;//清空选项
	                var select = $("#empNameUpdate");
	                for (var i = 0; i < datas.length; i++) { 
	                	select.append("<option value='" +datas[i].empid+ "'>"
	                            + datas[i].empname + "</option>");  
	                }
	            }
			})
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/Registrationform?method=sendUpdate",
			data:{regid:regid},
			dataType:"json",
			success:function(data){
				 //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    			
		    		   $("#regIdUpdate").val(data.regid);
		    		   alert(data.regfee);
		    		   $("#regfeeUpdate").val(data.regfee);
		    		   
		    		   //初始化 为原来的值
		    		   
		    		   var opt = document.getElementById("regDeptNameUpdate");
		    		   for(i=0;i<opt.length;i++){
		    			   if(opt[i].value==data.regdeptid)
		    				   opt[i].selected = true;
		    		   }
		    		   
		    		   var opt = document.getElementById("empNameUpdate");
		    		   for(i=0;i<opt.length;i++){
		    			   if(opt[i].value==data.empid)
		    				   opt[i].selected = true;
		    		   }
		    		   
		    		   var opt = document.getElementById("regDocNameUpdate");
		    		   for(i=0;i<opt.length;i++){
		    			   if(opt[i].value==data.regdocid)
		    				   opt[i].selected = true;
		    		   }
		    		   
		    		   var opt = document.getElementById("regPatNameUpdate");
		    		   for(i=0;i<opt.length;i++){
		    			   if(opt[i].value==data.regpatid)
		    				   opt[i].selected = true;
		    		   }
		    		   
		    	})
			}	
		})
	}
	
//删除
	function deleteFun(regid){
			
		$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath }/Registrationform?method=delete",
				data:{regid:regid},
				success:function(data){
					$.ajax({
		        		type:"post",
		        		url:"${pageContext.request.contextPath }/Registrationform?method=query",
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
	
<script type="text/javascript">
//请求科室名称


</script>

<!-- 修改按钮窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">

<input type="hidden" name="regId" id="regIdUpdate">

<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改挂单</h4>
            </div>
            <div class="modal-body">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">挂单病人:</label>
					    <div class="col-sm-10">
					    <input type="hidden" name="regPatId" id="regPatIdAdd">
					    <select class="form-control" name="regPatName" id="regPatNameUpdate">
					      	<option value="0">请选择</option>
					     </select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">挂单科室:</label>
					    <div class="col-sm-10">
					    <input type="hidden" name="regDeptId" id="regDeptIdAdd">
					    <select class="form-control" name="regDeptName" id="regDeptNameUpdate">
					      	<option value="0">请选择</option>
					      </select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">挂单医生:</label>
					    <div class="col-sm-10">
					      	
					    	<input type="hidden" name="regDocId" id="regDocIdAdd">
						    <select class="form-control" name="regDocName" id="regDocNameUpdate">
						      	<option value="0">请选择</option>
						      </select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">处理人:</label>
					    <div class="col-sm-10">
						    <select class="form-control" name="empName" id="empNameUpdate">
						      	<option value="0">请选择</option>
						      </select>
					    </div>
				 </div>
				 <br>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">挂单费用:</label>
					    <div class="col-sm-10">
					    	<input type="text" name="reffee" class="form-control" id="regfeeUpdate" placeholder="费用；">
					    </div>
				 </div>
				 <br>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnUpdate" class="btn btn-primary">更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</form>

<!-- 添加按钮窗体 -->
<!-- 模态框（Modal） -->
<form id="myAddForm" method="post">

<input type="hidden" name="regId" id="regIdAdd">
<div class="modal fade"  id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">增加挂单</h4>
            </div>
            <div class="modal-body maxModalHeight" style="overflow : auto ">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">挂单病人:</label>
					    <div class="col-sm-10">
					    <input type="hidden" name="regPatId" id="regPatIdAdd">
					    <select class="form-control" name="regPatName" id="regPatNameAdd">
					      	<option value="0">请选择</option>
					     </select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">挂单科室:</label>
					    <div class="col-sm-10">
					    <input type="hidden" name="regDeptId" id="regDeptIdAdd">
					    <select class="form-control" name="regDeptName" id="regDeptNameAdd">
					      	<option value="0">请选择</option>
					      </select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">挂单医生:</label>
					    <div class="col-sm-10">
					      	
					    	<input type="hidden" name="regDocId" id="regDocIdAdd">
						    <select class="form-control" name="regDocName" id="regDocNameAdd">
						      	<option value="0">请选择</option>
						      </select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">处理人:</label>
					    <div class="col-sm-10">
						    <select class="form-control" name="empName" id="empNameAdd">
						      	<option value="0">请选择</option>
						      </select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">挂单费用:</label>
					    <div class="col-sm-10">
					    	<input type="text" name="reffee" class="form-control" id="regfeeAdd" placeholder="费用；">
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
 $("#btnUpdate").click(function(){
 	alert("成功点击");
     //获取表单中的数据
    
     var reg = $("#myUpdateForm").serializeArray();
     
     $.ajax({
     	 type:"post",//提交方式
     	 url:"${pageContext.request.contextPath}/Registrationform?method=update",
     	 data:reg,//数据
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
	var regDeptId = document.getElementById("regDeptNameAdd").value;
	$("#regDeptIdAdd").val(regDeptId);
	
	var reg = $("#myAddForm").serializeArray();
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/Registrationform?method=add",
		data:reg,
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