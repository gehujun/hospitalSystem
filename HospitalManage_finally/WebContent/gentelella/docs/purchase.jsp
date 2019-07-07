<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- jsp包含指令 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/plugin/css/bootstrap.3.3.7.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/plugin/css/bootstrap-theme.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/css/bootstrap-table.css" rel="stylesheet">

<script type="text/javascript" src="${pageContext.request.contextPath }/plugin/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/plugin/js/bootstrap3.3.7.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/plugin/js/bootstrap-table.js"></script>


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

<!-- 搜索栏 -->
<div id="toolbar">
   <div id="showorhide" class="a">
          <form class="form-inline" id="mySearchForm">
          	  <div class="form-group">
			    <label for="exampleInputName2">购置设备名称:</label>
			    <input type="text" class="form-control" id="purname" placeholder="购置设备名称">
			  </div>
			  
			  <div class="form-group">
			    <label for="exampleInputName2">供应商名称:</label>
			    <input type="text" class="form-control" id="supname" placeholder="供应商名称">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">购置人员:</label>
			    <input type="text" class="form-control" id="empname" placeholder="购置人员">
			  </div>
			  <button id="btn_query" type="button" class="btn btn-success">查询</button>
			  <button id="btn_add" type="button" class="btn btn-success">添加</button>
		 </form>
   </div>
   <button id="open" class="btn btn-success">展开或折叠</button>
   
</div>

<!-- 挂号表单 -->
<table id="table"></table>
<script type="text/javascript">
//数据加载
$(function(){
    	//表格数据的加载
    	$('#table').bootstrapTable({
    		  url: '${pageContext.request.contextPath}/purchase?method=query',
    		  //url: 'data.json',//访问json数据
    		  method: 'get',
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
    		    field: 'purid',
    		    title: '设备编号'
    		  }, {
    		    field: 'purname',
    		    title: '购置设备名称'
    		  }, {
    		    field: 'sup.supname',
    		    title: '供应商'
    		  }, {
    		    field: 'emp.empname',
    		    title: '购置人员'
    		  }, {
    		    field: 'purdate',
    		    title: '购置时间'
    		  }, {
    		    field: 'purnum',
    		    title: '购置数量'
    		  }, {
    		    field: 'purfee',
    		    title: '购置金额'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue='<button onclick="updateFun('+row.purid+') " type="button" class="btn btn-success">修改</button>';
    				  var deleteValue='<button onclick="deleteFun('+row.purid+') " type="button" class="btn btn-success">删除</button>';
    				  
    				  return updateValue+deleteValue;
    			  }
    		  }]
    		})
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
	

//添加按钮的事件
	$(function(){
		$("#btn_add").click(function(){
			$('#myAddModal').modal().on('shown.bs.modal');
			//var deptName = $("#deptNameSearch").val();
			//通过调用科室方法获取对象
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/purchase?method=querySupId",
				dataType : 'json',
	            success : function(datas) {				//返回list数据并循环获取  
	                	var select = $("#supidAdd");
	            		select.empty();
	                for (var i = 0; i < datas.length; i++) { 
	                	select.append("<option value='" +datas[i].supid+ "'>"
	                            + datas[i].supname+ "</option>");  
	                }  
	            }
			})
			
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/purchase?method=queryEmpId",
				dataType : 'json',
	            success : function(datas) {				//返回list数据并循环获取  
// 					这是经办人id
	                	var select = $("#empidAdd");
	            		select.empty();
	                for (var i = 0; i < datas.length; i++) { 
	                	select.append("<option value='" +datas[i].empid+ "'>"
	                            + datas[i].empname+ "</option>");  
	                }  
	            }
			})
			
		})
	})
	

//查询请求
	$(function(){
    	$("#btn_query").click(function(){
        	//ajax请求后台
        	var purname=$("#purname").val();
        	var supname=$("#supname").val();
        	var empname=$("#empname").val();

        	$.ajax({
        		type:"post",
        		url:"${pageContext.request.contextPath }/purchase?method=queryByCondition",
        		data:{purname:purname,supname:supname,empname:empname},
        		dataType:"json",
        		success:function(json){
        				$("#table").bootstrapTable("load",json);
        			}
        	})
        	
        	
        });
    })
    
    

//修改
	function updateFun(purid){
		

		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/purchase?method=querySupId",
			dataType : "json",
	        success : function(datas) {//返回list数据并循环获取  
	        	document.getElementById("supidUpdate").options.length = 0;//清空选项
	            var select = $("#supidUpdate");
	            for (var i = 0; i < datas.length; i++) { 
	            	select.append("<option value='" +datas[i].supid+ "'>"  
	                        + datas[i].supname + "</option>");
	            	
	            } 
	          	
	        }
		})
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/purchase?method=queryEmpId",
			dataType : "json",
	        success : function(datas) {//返回list数据并循环获取  
	            
	            document.getElementById("empidUpdate").options.length = 0;//清空选项
	            var select = $("#empidUpdate");
	            for (var i = 0; i < datas.length; i++) { 
	            	select.append("<option value='" +datas[i].empid+ "'>"  
	                        + datas[i].empname + "</option>");  
	            }  
	          	
	        }
		})
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/purchase?method=sendUpdate",
			data:{purid:purid},
			dataType:"json",
			success:function(data){
				 //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
// 		    			alert(data.mediid);
		    		   $("#puridUpdate").val(data.purid);
		    		   $("facinameUpdate").val(data.faciname)
// 		    		   $("#purfirmUpdate").val(data.mediname);
// 		    		   $("#purmanUpdate").val(data.medispecifi);
		    		   $("#purdateUpdate").val(data.purdate);
		    		   $("#purnumUpdate").val(data.purnum);
		    		   $("#purfeeUpdate").val(data.purfee);

		    		   //初始化为原来的值
		    		   var opt = document.getElementById("supidUpdate");
		    		   for(i=0;i<opt.length;i++){
		    			   if(opt[i].value==data.supid)
		    				   opt[i].selected = true;
		    		   } 
		    		   
		    		   //初始化为原来的值
		    		   var opt = document.getElementById("empidUpdate");
		    		   for(i=0;i<opt.length;i++){
		    			   if(opt[i].value==data.empid)
		    				   opt[i].selected = true;
		    		   }
		    		   
		    	})
			}	
		})
	}
	
//删除
	function deleteFun(purid){
			
		$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath }/purchase?method=delete",
				data:{purid:purid},
				success:function(data){
					$.ajax({
		        		type:"post",
		        		url:"${pageContext.request.contextPath }/purchase?method=query",
		        		data:{ purid:null},
		        		dataType:"json",
		        		success:function(json){
		        				$("#table").bootstrapTable("load",json);
		        			}
		        	})
				}
			})
		}
</script>


<!-- 修改 -->
<!-- 修改按钮窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">

<!-- <input type="hidden" name="mediid" id="puridUpdate"> -->

<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
                <h4 class="modal-title" id="myModalLabel">修改医院购置表</h4>
            </div>
            
			
            <div class="modal-body">
            	<input type="hidden" name="purid" id="puridUpdate">
                   
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">设备名称:</label>
					    <div class="col-sm-10">
					      <input type="text" name="faciname" class="form-control" id="facinameUpdate" placeholder="设备名称;" />
					    </div>
				 </div> 
				 
				  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">供应商:</label>
					    <div class="col-sm-10">
					    <select class="form-control" name="supid" id="supidUpdate" >
					      </select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">购置人:</label>
					    <div class="col-sm-10">
					    <select class="form-control" name="empid" id="empidUpdate">
					      </select>
					    </div>
				 </div>
				 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">购置时间:</label>
					    <div class="col-sm-10">
					      <input type="text" name="purdate" class="form-control" id="purdateUpdate" placeholder="购置时间；">
					    </div>
				 </div> 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">购置数量:</label>
					    <div class="col-sm-10">
					      <input type="text" name="purnum" class="form-control" id="purnumUpdate" placeholder="购置数量；">
					    </div>
				 </div> 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">购置金额:</label>
					    <div class="col-sm-10">
					      <input type="text" name="purfee" class="form-control" id="purfeeUpdate" placeholder="购置金额；">
					    </div>
				 </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnUpadte" class="btn btn-primary">更改</button>
            </div>
        </div>
    </div>
</div>
</form>
	
	
<!-- 添加改完  -->
<!-- 添加按钮窗体 -->
<!-- 模态框（Modal） -->
<form id="myAddForm" method="post">

<!-- <input type="hidden" name="mediid" id="mediidAdd"> -->
<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times; </button>
                <h4 class="modal-title" id="myModalLabel">添加设备</h4>
            </div>
            
            
            <div class="modal-body"> 
            
            
            	<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">设备名称:</label>
					    <div class="col-sm-10">
					      <input type="text" name="faciname" class="form-control" id="facinameAdd" placeholder="设备名称；">
					    </div>
				 </div>

				<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">供应商:</label>
					    <div class="col-sm-10">
					    <select class="form-control" name="supid" id="supidAdd">
					      	<option value="0">请选择</option>
					      </select>
					    </div>
				 </div>
				 
				 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">购置人员:</label>
					    <div class="col-sm-10">
					    <select class="form-control" name="empid" id="empidAdd">
					      	<option value="0">请选择</option>
					      </select>
					    </div>
				 </div>
				    
				    
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">购置时间:</label>
					    <div class="col-sm-10">
					      <input type="text" name="purdate" class="form-control" id="purdateAdd" placeholder="购置时间；">
					    </div>
				 </div>  

  
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">购置数量:</label>
					    <div class="col-sm-10">
					      <input type="text" name="purnum" class="form-control" id="purnumAdd" placeholder="购置数量；">
					    </div>
				 </div>   
				 
				 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">购置金额:</label>
					    <div class="col-sm-10">
					      <input type="text" name="purfee" class="form-control" id="purfeeAdd" placeholder="购置金额；">
					    </div>
				 </div> 
				 
				 
            	</div>
            	<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnAdd" class="btn btn-primary">添加</button>
            	</div>
            	
            	
			</div>
        </div>    
    </div>

</form>


<script type="text/javascript">
 
//提交更新按钮
 $("#btnUpadte").click(function(){
 	alert("成功点击");
     //获取表单中的数据
//      var meliid = document.getElementById("facinameUpdate").value;
//      $("#faciidUpdate").val(meliid);
     var med = $("#myUpdateForm").serializeArray();
     
     $.ajax({
     	 type:"post",//提交方式
     	 url:"${pageContext.request.contextPath}/purchase?method=update",
     	 data:med,//数据
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
	alert("成功点击");
	var med = $("#myAddForm").serializeArray();
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/purchase?method=add",
		data:med,
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

	
</body>
</html>