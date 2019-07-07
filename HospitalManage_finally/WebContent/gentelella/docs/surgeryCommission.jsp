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
			    <label for="exampleInputName2">医师:</label>
			    <input type="text" class="form-control" id="empname" placeholder="医师">
			</div>
			  <div class="form-group">
			    <label for="exampleInputName2">手术类型:</label>
			    <input type="text" class="form-control" id="surctype" placeholder="手术类型">
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
    		  url: '${pageContext.request.contextPath}/surgeryCommission?method=queryByJoin',
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
    			 field: 'emp.empname',
      		    title: '医师姓名'
    		  }, {
    		    field: 'surctype',
    		    title: '手术类型'
    		  }, {
    		    field: 'surcpushfee',
    		    title: '手术提成金额'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue='<button onclick="updateFun('+row.surcid+') " type="button" class="btn btn-success">修改</button>';
    				  var deleteValue='<button onclick="deleteFun('+row.surcid+') " type="button" class="btn btn-success">删除</button>';
    				  
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
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/surgeryCommission?method=querySCEmpId",
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
        	var empname=$("#empname").val();
        	var surctype=$("#surctype").val();

        	$.ajax({
        		type:"post",
        		url:"${pageContext.request.contextPath}/surgeryCommission?method=queryByCondition",
        		data:{empname:empname,surctype:surctype},
        		dataType:"json",
        		success:function(json){
        				$("#table").bootstrapTable("load",json);
        			}
        	})
        	
        	
        });
    })
    
    

//修改
	function updateFun(empid){
// 		alert(traid);
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/surgeryCommission?method=queryByJoin",
			dataType : "json",
	        success : function(datas) {//返回list数据并循环获取  
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
			url:"${pageContext.request.contextPath}/surgeryCommission?method=sendUpdate",
			data:{empid:empid},
			dataType:"json",
			success:function(data){
				 //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
// 		    			alert(data.mediid);
					   $("#surcidUpdate").val(data.surcid);
		    		   $("#surctypeUpdate").val(data.surctype);
 		    		   $("#surcpushfeeUpdate").val(data.surcpushfee);
// 		    		   $("#ordorddateUpdate").val(data.ordorddate);
// 		    		   $("#orddeldateUpdate").val(data.orddeldate);

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
	function deleteFun(empid){
			
		$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/surgeryCommission?method=delete",
				data:{empid:empid},
				success:function(data){
					$.ajax({
		        		type:"post",
		        		url:"${pageContext.request.contextPath }/surgeryCommission?method=queryByJoin",
		        		data:{empid:null},
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
                <h4 class="modal-title" id="myModalLabel">修改手术提成</h4>
            </div>
            
			
            <div class="modal-body">
            
				<input type="hidden" name="traid" id="traidUpdate">
				
				 
				 <!--	活动人员  -->
				  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">医师姓名:</label>
					    <div class="col-sm-10">
					    <select class="form-control" name="empid" id="empidUpdate">
					      </select>
					    </div>
				 </div>
				 
				 <!--	接送车辆车牌	  -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术类型:</label>
					    <div class="col-sm-10">
					      <input type="text" name="surctype" class="form-control" id="surctypeUpdate" placeholder="手术类型；">
					    </div>
				 </div>
				 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术提成金额:</label>
					    <div class="col-sm-10">
					      <input type="text" name="surcpushfee" class="form-control" id="surcpushfeeUpdate" placeholder="手术提成金额；">
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
                <h4 class="modal-title" id="myModalLabel">增加手术提成</h4>
            </div>
            
            <div class="modal-body">
            
				  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术类型:</label>
					    <div class="col-sm-10">
					      <input type="text" name="surctype" class="form-control" id="surctypeAdd" placeholder="手术类型；">
					    </div>
				 </div>

				 
				 <!--     差旅人员      -->
				<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">医师姓名:</label>
					    <div class="col-sm-10">
					    <select class="form-control" name="empid" id="empidAdd">
					      	<option value="0">请选择</option>
					      </select>
					    </div>
				 </div>
				 
				  <!--     接送车辆车牌      -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术提成金额:</label>
					    <div class="col-sm-10">
					      <input type="text" name="surcpushfee" class="form-control" id="surcpushfeeAdd" placeholder="手术提成金额；">
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
     var empid = document.getElementById("empidUpdate").value;
     $("#traidUpdate").val(empid);
     var ta = $("#myUpdateForm").serializeArray();
     
     $.ajax({
     	 type:"post",//提交方式
     	 url:"${pageContext.request.contextPath}/surgeryCommission?method=update",
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
		url:"${pageContext.request.contextPath}/surgeryCommission?method=add",
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