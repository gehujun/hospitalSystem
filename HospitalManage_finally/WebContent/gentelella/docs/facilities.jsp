<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${pageContext.request.contextPath }/plugin/css/bootstrap.3.3.7.css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/plugin/css/bootstrap-theme.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugin/css/bootstrap-table.css" rel="stylesheet">

<script type="text/javascript" src="${pageContext.request.contextPath }/plugin/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/plugin/js/bootstrap3.3.7.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/plugin/js/bootstrap-table.js"></script>

</head>
<body>

<!-- 搜索栏 -->
<div id="toolbar">
   <div id="showorhide" class="a">
          <form class="form-inline" id="mySearchForm">
			  <div class="form-group">
			    <label for="exampleInputName2">设施名称:</label>
			    <input type="text" class="form-control" id="faciname" placeholder="设施名称">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">设施厂商:</label>
			    <input type="text" class="form-control" id="facifirm" placeholder="设施厂商">
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
    		  url: '${pageContext.request.contextPath }/facilities?method=query',
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
    		  exportDataType:'all',//可以全部导出
    		  exportTypes:['pdf','xlsx'],//导出的数据类型（pdf文件、excel文件）
    		  columns: [{
    		    field: 'faciid',
    		    title: '设施id'
    		  }, {
    			field: 'faciname',
      		    title: '设施名称'
    		  }, {
    			field: 'faciprice',
       		    title: '设施价格'
    		  }, {
    		    field: 'facinum',
    		    title: '设施数量'
    		  }, {
    		    field: 'facifirm',
    		    title: '设施厂商'
    		  }, {
    		    field: 'dep.deptname',
    		    title: '科室名称'
    		  }, {
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue='<button onclick="updateFun('+row.faciid+') " type="button" class="btn btn-success">修改</button>';
    				  var deleteValue='<button onclick="deleteFun('+row.faciid+') " type="button" class="btn btn-success">删除</button>';
    				  
    				  return updateValue+deleteValue;
    			  }
    		  }]
    		})
    })

//添加按钮的事件
	$(function(){
		$("#btn_add").click(function(){
			$('#myAddModal').modal().on('shown.bs.modal');
			//通过调用科室方法获取对象
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/facilities?method=queryByJoin",
				dataType : 'json',
	            success : function(datas) {//返回list数据并循环获取  
	            //	document.getElementById("regDeptNameAdd").options.length = 0;//清空选项
	                var select = $("#deptnameAdd");
	                select.empty();
	                for (var i = 0; i < datas.length; i++) { 
	                	select.append("<option value='" +datas[i].deptid+ "'>"  
	                            + datas[i].deptname + "</option>");  
	                }  
	              
	            }
			})
			//医生也应该一样，但是没有employee的controller，直接在后台写死的
						
			
		})
	})
	

//查询请求
	$(function(){
    	$("#btn_query").click(function(){
        	//ajax请求后台
        	var faciname=$("#faciname").val();
    		var facifirm=$("#facifirm").val();
        	
        	$.ajax({
        		type:"post",
        		url:"${pageContext.request.contextPath }/facilities?method=queryByCondition",
        		data:{ "faciname":faciname,"facifirm":facifirm},
        		dataType:"json",
        		success:function(json){
        				$("#table").bootstrapTable("load",json);
        			}
        	})
        	
        	
        });
    })

//修改
	function updateFun(faciid){
		
	//通过调用科室方法获取对象 regDeptNameUpdate
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/facilities?method=queryByJoin",
			dataType : 'json',
	        success : function(datas) {//返回list数据并循环获取  
	        	document.getElementById("deptnameUpdate").options.length = 0;//清空选项
	            var select = $("#deptnameUpdate");
	            for (var i = 0; i < datas.length; i++) { 
	            	select.append("<option value='" +datas[i].deptid+ "'>"  
	                        + datas[i].deptname + "</option>");  
	            }  
	          	
	        }
		})
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/facilities?method=sendUpdate",
			data:{faciid:faciid},
			dataType:"json",
			success:function(data){
				 //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    			//alert(data.faciid);
		    		   $("#faciidUpdate").val(data.faciid);
		    		   $("#facinameUpdate").val(data.faciname);
		    		   $("#facipriceUpdate").val(data.faciprice);
		    		   $("#facinumUpdate").val(data.facinum);
		    		   $("#facifirmUpdate").val(data.facifirm);
		    		   //初始化 为原来的值
		    		   var opt = document.getElementById("deptnameUpdate");
		    		   for(i=0;i<opt.length;i++){
		    			   if(opt[i].value==data.deptname)
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
				url:"${pageContext.request.contextPath }/facilities?method=delete",
				data:{faciid:faciid},
				success:function(data){
					$.ajax({
		        		type:"post",
		        		url:"${pageContext.request.contextPath }/facilities?method=query",
		        		data:{ "faciid":null},
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

<!-- <input type="hidden" name="faciname" id="regIdUpdate"> -->

<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改设施信息</h4>
            </div>
            <div class="modal-body">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">设施编号:</label>
					    <div class="col-sm-10">
					      <input type="text" name="faciid" class="form-control" id="faciidUpdate" placeholder="设施编号；">
					    </div>
				 </div>
				 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">设施名称:</label>
					    <div class="col-sm-10">
					      <input type="text" name="faciname" class="form-control" id="facinameUpdate" placeholder="设施名称；">
					    </div>
				 </div>
				 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">设施价格:</label>
					    <div class="col-sm-10">
					      <input type="text" name="faciprice" class="form-control" id="facipriceUpdate" placeholder="设施价格；">
					    </div>
				 </div>
				 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">设施数量:</label>
					    <div class="col-sm-10">
					      <input type="text" name="facinum" class="form-control" id="facinumUpdate" placeholder="设施数量；">
					    </div>
				 </div>
				 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">设施厂商:</label>
					    <div class="col-sm-10">
					      <input type="text" name="facifirm" class="form-control" id="facifirmUpdate" placeholder="设施厂商；">
					    </div>
				 </div>
				 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">放置科室:</label>
					    <div class="col-sm-10">
					      <input type="hidden" name="deptid" id="deptidUpdate">
					    <select class="form-control" name="deptname" id="deptnameUpdate">
					      	<option value="0">请选择</option>
					      </select>
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
	
<!-- 添加按钮窗体 -->
<!-- 模态框（Modal） -->
<form id="myAddForm" method="post">

<!-- <input type="hidden" name="faciname" id="regIdAdd"> -->

<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加设施</h4>
            </div>
            <div class="modal-body">
<!--                    <div class="form-group"> -->
<!-- 					    <label for="inputEmail3" class="col-sm-2 control-label">设施编号:</label> -->
<!-- 					    <div class="col-sm-10"> -->
<!-- 					      <input type="text" name="faciid" class="form-control" id="faciidAdd" placeholder="设施编号；"> -->
<!-- 					    </div> -->
<!-- 				 </div> -->
				 
				  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">设施名称:</label>
					    <div class="col-sm-10">
					      <input type="text" name="faciname" class="form-control" id="facinameAdd" placeholder="设施名称；">
					    </div>
				 </div>
				 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">设施价格:</label>
					    <div class="col-sm-10">
					      <input type="text" name="faciprice" class="form-control" id="facipriceAdd" placeholder="设施价格；">
					    </div>
				 </div>
				 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">设施数量:</label>
					    <div class="col-sm-10">
					      <input type="text" name="facinum" class="form-control" id="facinumAdd" placeholder="设施数量；">
					    </div>
				 </div>
				 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">设施厂商:</label>
					    <div class="col-sm-10">
					      <input type="text" name="facifirm" class="form-control" id="facifirmAdd" placeholder="设施厂商；">
					    </div>
				 </div>
				 
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">放置科室:</label>
					    <div class="col-sm-10">
					    <input type="hidden" name="deptid" id="deptidAdd">
					    <select class="form-control" name="deptname" id="deptnameAdd">
					      	<option value="0">请选择</option>
					      </select>
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
 $("#btnUpadte").click(function(){
 	alert("成功点击");
     //获取表单中的数据
     var deptid = document.getElementById("deptnameUpdate").value;
     $("#deptidUpdate").val(deptid);
     var reg = $("#myUpdateForm").serializeArray();
     
     $.ajax({
     	 type:"post",//提交方式
     	 url:"${pageContext.request.contextPath}/facilities?method=update",
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
	//var deptid = document.getElementById("deptnameAdd").value;
	//$("#deptidAdd").val(deptid);
	
	var reg = $("#myAddForm").serializeArray();
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/facilities?method=add",
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