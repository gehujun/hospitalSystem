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
			    <label for="exampleInputName2">设备名:</label>
			    <input type="text" class="form-control" id="faciname" placeholder="设备名">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">维修厂商:</label>
			    <input type="text" class="form-control" id="facirefirm" placeholder="维修厂商">
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
    		  url: '${pageContext.request.contextPath}/facilityrepair?method=queryByJoin',
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
    		    field: 'fc.faciname',
    		    title: '设备名称'
    		  }, {
    		    field: 'dept.deptname',
    		    title: '科室名称'
    		  }, {
    		    field: 'facirebudget',
    		    title: '维修预算'
    		  }, {
    		    field: 'faciredate',
    		    title: '维修时间'
    		  }, {
    		    field: 'facirefirm',
    		    title: '维修厂商'
    		  }, {
    		    field: 'facirefee',
    		    title: '维修金额'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue='<button onclick="updateFun('+row.faciid+')" type="button" class="btn btn-success">修改</button>';
    				  var deleteValue='<button onclick="deleteFun('+row.faciid+')" type="button" class="btn btn-success">删除</button>';
    				  
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
				url:"${pageContext.request.contextPath}/facilityrepair?method=queryFaciPartly",
				dataType : 'json',
	            success : function(datas) {				//返回list数据并循环获取  
// 					这是供应商id
// 						alert(datas);
						if(datas != null)
	                	{
							var select = $("#facinameAdd");
		            		select.empty();
		                	for (var i = 0; i < datas.length; i++) {	
		                		select.append("<option value='" +datas[i].faciid+ "'>"
		                      	      + datas[i].faciname+ "</option>");  
		               			} 
	                	}
						else{
		                	alert("设备已满，无法添加");
		                }
	            }
// 				error: function(datas){
// 					alert("设备已满，无法添加");
// 				}
			})
			
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/facilityrepair?method=queryDeptPartly",
				dataType : 'json',
	            success : function(datas) {				//返回list数据并循环获取  
// 					这是经办人id
	                	var select = $("#deptidAdd");
	            		select.empty();
	                for (var i = 0; i < datas.length; i++) { 
	                	select.append("<option value='" +datas[i].deptid+ "'>"
	                            + datas[i].deptname+ "</option>");  
	                }  
	            }
			})
			
		})
	})
	

//条件查询请求
	$(function(){
    	$("#btn_query").click(function(){
        	//ajax请求后台
        	var faciname=$("#faciname").val();
        	var facirefirm=$("#facirefirm").val();

        	$.ajax({
        		type:"post",
        		url:"${pageContext.request.contextPath}/facilityrepair?method=queryByCondition",
        		data:{faciname:faciname,facirefirm:facirefirm},
        		dataType:"json",
        		success:function(json){
        				$("#table").bootstrapTable("load",json);
        			}
        	})
        	
        	
        });
    })
    
    

//修改
	function updateFun(faciid){
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/facilityrepair?method=queryByJoin",
			dataType : "json",
	        success : function(datas) {//返回list数据并循环获取  
	        	
	        	document.getElementById("facinameUpdate").options.length = 0;//清空选项
	            var select = $("#facinameUpdate");
	            for (var i = 0; i < datas.length; i++) { 
	            	select.append("<option value='" +datas[i].fc.faciid+ "'>"  
	                        + datas[i].fc.faciname + "</option>");
	            	
	            } 
	            
	            document.getElementById("deptnameUpdate").options.length = 0;//清空选项
	            var select = $("#deptnameUpdate");
	            for (var i = 0; i < datas.length; i++) { 
	            	select.append("<option value='" +datas[i].dept.deptid+ "'>"  
	                        + datas[i].dept.deptname + "</option>");  
	            }  
	          	
	        }
		})
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/facilityrepair?method=sendUpdate",
			data:{faciid:faciid},
			dataType:"json",
			success:function(data){
				 //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
// 		    			alert(data.mediid);
		    		   $("#facirebudgetUpdate").val(data.facirebudget);
		    		   $("#faciredateUpdate").val(data.faciredate);
		    		   $("#facirefirmUpdate").val(data.facirefirm);
		    		   $("#facirefeeUpdate").val(data.facirefee);

		    		   //初始化为原来的值
		    		   var opt = document.getElementById("facinameUpdate");
		    		   for(i=0;i<opt.length;i++){
		    			   if(opt[i].value==data.faciid)
		    				  opt[i].selected = true;
		    				   
		    		   } 
		    		   
		    		   //初始化为原来的值
		    		   var opt = document.getElementById("deptnameUpdate");
		    		   for(i=0;i<opt.length;i++){
		    			   if(opt[i].value==data.faciredeptid)
		    				   opt[i].selected = true;
		    		   } 
		    		   
		    	})
			}	
		})
	}
	
//删除
	function deleteFun(faciid){
			
		$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/facilityrepair?method=delete",
				data:{faciid:faciid},
				success:function(data){
					$.ajax({
		        		type:"post",
		        		url:"${pageContext.request.contextPath}/facilityrepair?method=queryByJoin",
		        		data:{faciid:faciid},
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
            
				<input type="hidden" name="faciid" id="faciidUpdate">
				 	
				<!--	设备名称	 -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">设备名称:</label>
					    <div class="col-sm-10">
					    <select class="form-control" name="faciname" id="facinameUpdate">
					      </select>
					    </div>
				 </div>
				 
				 <!--	科室名称  -->
				  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">科室名称:</label>
					    <div class="col-sm-10">
					    <select class="form-control" name="deptname" id="deptnameUpdate">
					      </select>
					    </div>
				 </div>
				 
				 <!--	维修预算	  -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">维修预算:</label>
					    <div class="col-sm-10">
					      <input type="text" name="facirebudget" class="form-control" id="facirebudgetUpdate" placeholder="维修预算；">
					    </div>
				 </div>
				 
				 
				 <!--	维修时间	  -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">维修时间:</label>
					    <div class="col-sm-10">
					      <input type="text" name="faciredate" class="form-control" id="faciredateUpdate" placeholder="维修时间；">
					    </div>
				 </div>
				 
				 
				 <!--	维修厂商 -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">维修厂商:</label>
					    <div class="col-sm-10">
					      <input type="text" name="facirefirm" class="form-control" id="facirefirmUpdate" placeholder="维修厂商；">
					    </div>
				 </div>
				 
				 
				 <!--	维修金额-->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">维修金额:</label>
					    <div class="col-sm-10">
					      <input type="text" name="facirefee" class="form-control" id="facirefeeUpdate" placeholder="维修金额；">
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
            
				<!--	设备名称	 -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">设备名称:</label>
					    <div class="col-sm-10">
					    <select class="form-control" name="faciid" id="facinameAdd">
					      </select>
					    </div>
				 </div>
				 
				 <!--	科室名称  -->
				  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">科室名称:</label>
					    <div class="col-sm-10">
					    <select class="form-control" name="deptid" id="deptidAdd">
					      </select>
					    </div>
				 </div>
				 
				 <!--	维修预算	  -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">维修预算:</label>
					    <div class="col-sm-10">
					      <input type="text" name="facirebudget" class="form-control" id="facirebudgetAdd" placeholder="维修预算；">
					    </div>
				 </div>
				 
				 
				 <!--	维修时间	  -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">维修时间:</label>
					    <div class="col-sm-10">
					      <input type="text" name="faciredate" class="form-control" id="faciredateAdd" placeholder="维修时间；">
					    </div>
				 </div>
				 
				 
				 <!--	维修厂商 -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">维修厂商:</label>
					    <div class="col-sm-10">
					      <input type="text" name="facirefirm" class="form-control" id="facirefirmAdd" placeholder="维修厂商；">
					    </div>
				 </div>
				 
				 
				 <!--	维修金额-->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">维修金额:</label>
					    <div class="col-sm-10">
					      <input type="text" name="facirefee" class="form-control" id="facirefeeAdd" placeholder="维修金额；">
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
     var faciid = document.getElementById("facinameUpdate").value;
     $("#faciidUpdate").val(faciid);
     var ta = $("#myUpdateForm").serializeArray();
     
     $.ajax({
     	 type:"post",//提交方式
     	 url:"${pageContext.request.contextPath}/facilityrepair?method=update",
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
	var fc = $("#myAddForm").serializeArray();
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/facilityrepair?method=add",
		data:fc,
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