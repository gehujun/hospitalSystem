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

<!-- 搜索栏 -->
<div id="toolbar">
   <div id="showorhide" class="a">
          <form class="form-inline" id="mySearchForm">
			  <div class="form-group">
			    <label for="exampleInputName2">差旅人员:</label>
			    <input type="text" class="form-control" id="travelempname" placeholder="差旅人员">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">活动名称:</label>
			    <input type="text" class="form-control" id="traname" placeholder="活动名称">
			  </div>
			  <button id="btn_query" type="button" class="btn btn-success">查询</button>
			  <button id="btn_add" type="button" class="btn btn-success">添加</button>
		 </form>
   </div>
   <button id="open" class="btn btn-success">展开或折叠</button>
   
</div>


<table id="table"></table>
<script type="text/javascript">
//数据加载
$(function(){
    	//表格数据的加载
    	$('#table').bootstrapTable({
    		  url: '${pageContext.request.contextPath}/travelarrange?method=queryByJoin',
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
    		    field: 'travelactivities.traname',
    		    title: '活动名称'
    		  }, {
    		    field: 'emp.empname',
    		    title: '差旅人员'
    		  }, {
    		    field: 'travellicence',
    		    title: '接送车辆车牌'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue='<button onclick="updateFun('+row.travelid+','+row.travelempid+') " type="button" class="btn btn-success">修改</button>';
    				  var deleteValue='<button onclick="deleteFun('+row.travelid+','+row.travelempid+') " type="button" class="btn btn-success">删除</button>';
    				  
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
			//通过调用科室方法获取对象
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/travelarrange?method=queryTravelId",
				dataType : 'json',
	            success : function(datas) {				//返回list数据并循环获取  
// 					这是供应商id
	                	var select = $("#traidAdd");
	            		select.empty();
	                for (var i = 0; i < datas.length; i++) { 
	                	select.append("<option value='" +datas[i].traid+ "'>"
	                            + datas[i].traname+ "</option>");  
	                }  
	            }
			})
			
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/travelarrange?method=queryTravelEmpId",
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
	

//条件查询请求
	$(function(){
    	$("#btn_query").click(function(){
        	//ajax请求后台
        	var traname=$("#traname").val();
        	var empname=$("#travelempname").val();

        	$.ajax({
        		type:"post",
        		url:"${pageContext.request.contextPath}/travelarrange?method=queryByCondition",
        		data:{traname:traname,empname:empname},
        		dataType:"json",
        		success:function(json){
        				$("#table").bootstrapTable("load",json);
        			}
        	})
        	
        	
        });
    })
    
    

//修改
	function updateFun(traid,empid){
// 		alert(traid);
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/travelarrange?method=queryByJoin",
			dataType : "json",
	        success : function(datas) {//返回list数据并循环获取  
	        	
	        	document.getElementById("tranameUpdate").options.length = 0;//清空选项
	            var select = $("#tranameUpdate");
	            for (var i = 0; i < datas.length; i++) { 
	            	select.append("<option value='" +datas[i].travelactivities.traid+ "'>"  
	                        + datas[i].travelactivities.traname + "</option>");
	            	
	            } 
	            
	            document.getElementById("empidUpdate").options.length = 0;//清空选项
	            var select = $("#empidUpdate");
	            for (var i = 0; i < datas.length; i++) { 
	            	select.append("<option value='" +datas[i].emp.empid+ "'>"  
	                        + datas[i].emp.empname + "</option>");  
	            }  
	          	
	        }
		})
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/travelarrange?method=sendUpdate",
			data:{traid:traid,empid:empid},
			dataType:"json",
			success:function(data){
				 //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
// 		    			alert(data.mediid);
		    		   $("#travellicenceUpdate").val(data.travellicence);
// 		    		   $("#travelempidUpdate").val(data.travelempid);
// 		    		   $("#ordorddateUpdate").val(data.ordorddate);
// 		    		   $("#orddeldateUpdate").val(data.orddeldate);

		    		   $("#tranameUpdate").val(data.travelid);
						$("#empidUpdate").val(data.travelempid); 
		    		   
		    	})
			}	
		})
	}
	
//删除
	function deleteFun(traid,empid){
			
		$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/travelarrange?method=delete",
				data:{traid:traid,empid:empid},
				success:function(data){
					$.ajax({
		        		type:"post",
		        		url:"${pageContext.request.contextPath}/travelarrange?method=queryByJoin",
		        		data:{traid:traid},
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


<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
                <h4 class="modal-title" id="myModalLabel">修改差旅安排信息</h4>
            </div>
            
			
            <div class="modal-body">
            
				<input type="hidden" name="traid" id="traidUpdate">
				 	
				<!--	活动名称	 -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">活动名称:</label>
					    <div class="col-sm-10">
					    <select class="form-control" name="traname" id="tranameUpdate">
					      </select>
					    </div>
				 </div>
				 
				 <!--	活动人员  -->
				  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">活动人员:</label>
					    <div class="col-sm-10">
					    <select class="form-control" name="empid" id="empidUpdate">
					      </select>
					    </div>
				 </div>
				 
				 <!--	接送车辆车牌	  -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">接送车牌:</label>
					    <div class="col-sm-10">
					      <input type="text" name="travellicence" class="form-control" id="travellicenceUpdate" placeholder="接送车辆车牌；">
					    </div>
				 </div>
				 
				
            </div>
            <div class="modal-footer" >
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnUpadte" class="btn btn-primary">更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</form>
	
	
<!-- 添加  -->
<!-- 添加按钮窗体 -->
<!-- 模态框（Modal） -->
<form id="myAddForm" method="post">

<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times; </button>
                <h4 class="modal-title" id="myModalLabel">增加差旅安排</h4>
            </div>
            
            <div class="modal-body">
            
				 <!--     活动名称     -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">活动名称:</label>
					    <div class="col-sm-10">
					    <select class="form-control" name="traid" id="traidAdd">
					      	<option value="0">请选择</option>
					      </select>
					    </div>
				 </div>

				 
				 <!--     差旅人员      -->
				<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">差旅人员:</label>
					    <div class="col-sm-10">
					    <select class="form-control" name="empid" id="empidAdd">
					      	<option value="0">请选择</option>
					      </select>
					    </div>
				 </div>
				 
				  <!--     接送车辆车牌      -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">接送车辆车牌:</label>
					    <div class="col-sm-10">
					      <input type="text" name="travellicence" class="form-control" id="travellicenceAdd" placeholder="接送车辆车牌；">
					    </div>
				 </div>
				 

			 </div>
          
            
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnAdd" class="btn btn-primary">添加</button>
            </div>
            
              </div>
            
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</form>


<script type="text/javascript">
 
//提交更新按钮
 $("#btnUpadte").click(function(){
 	alert("成功点击");
     //获取表单中的数据
     var traid = document.getElementById("tranameUpdate").value;
     $("#traidUpdate").val(traid);
     var ta = $("#myUpdateForm").serializeArray();
     
     $.ajax({
     	 type:"post",//提交方式
     	 url:"${pageContext.request.contextPath}/travelarrange?method=update",
     	 data:ta,//数据
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
	var ta = $("#myAddForm").serializeArray();
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/travelarrange?method=add",
		data:ta,
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