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

<div id="toolbar">
   <div id="showorhide" class="a">
          <form class="form-inline" id="mySearchForm">
			  <div class="form-group">
			    <label for="exampleInputName2">药库号:</label>
			    <input type="text" name="meliid" class="form-control" id="meliid" placeholder="药库号">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">药库地址:</label>
			    <input type="text" name="meliaddr" class="form-control" id="meliaddr" placeholder="药库地址">
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
    		  url: '${pageContext.request.contextPath }/medicineLibrary?method=query',
    		  //url: 'data.json',//访问json数据
    		  method: 'get',
    		  dataType: "json",
    		  toolbar:"#toolbar",//工具栏
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
    		    field: 'meliid',
    		    title: '药库号'
    		  }, {
    			field: 'meliaddr',
      		    title: '地址'
    		  }, {
    			field: 'emp.empname',
        		title: '负责人'
    		  }, {
    		    field: 'meliarea',
    		    title: '面积'
    		  }, {
    		    field: 'meliunit',
    		    title: '单位'
    		  }, {
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue='<button onclick="updateFun('+row.meliid+') " type="button" class="btn btn-success">修改</button>';
    				  var deleteValue='<button onclick="deleteFun('+row.meliid+') " type="button" class="btn btn-success">删除</button>';
    				  
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
</script>	
	
 <script type="text/javascript">
 
//添加按钮的事件
$(function(){
	$("#btn_add").click(function(){
		$('#myAddModal').modal().on('shown.bs.modal');
		//通过调用科室方法获取对象
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/medicineLibrary?method=queryByJoin",
			dataType : 'json',
            success : function(datas) {//返回list数据并循环获取  
            	  var select = $("#empnameAdd");
                  select.empty();
                for (var i = 0; i < datas.length; i++) { 
                	select.append("<option value='" +datas[i].empid+ "'>"
                            + datas[i].empname+ "</option>");  
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
    		var meliid=$("#meliid").val();
    		var meliaddr=$("#meliaddr").val();
        	
        	$.ajax({
        		type:"post",
        		url:"${pageContext.request.contextPath}/medicineLibrary?method=queryByCondition",
        		data:{ meliid:meliid,meliaddr:meliaddr},
        		dataType:"json",
        		success:function(json){
        				$("#table").bootstrapTable("load",json);
        			}
        	})
        });
    })

	
	//修改
	function updateFun(meliid){	
	
	 $.ajax({
	      type:'get',
	      url:"${pageContext.request.contextPath}/medicineLibrary?method=queryByJoin",
	      dataType : "json",
	          success : function(datas) {//返回list数据并循环获取  
	            document.getElementById("melimanUpdate").options.length = 0;//清空选项
	              var select = $("#melimanUpdate");
	              for (var i = 0; i < datas.length; i++) { 
	                select.append("<option value='" +datas[i].empid+ "'>"  
	                          + datas[i].empname + "</option>");  
	              }  
	              
	          }
	    })
	
	$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/medicineLibrary?method=sendUpdate",
			data:{meliid:meliid},
			dataType:"json",
			success:function(data){
				
				 //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    		
		    		   $("#meliidUpdate").val(data.meliid);
		    		   $("#meliaddrUpdate").val(data.meliaddr);
		    		   $("#meliareaUpdate").val(data.meliarea);
		    		   $("#meliunitUpdate").val(data.meliunit);
		    		//   $("#empidUpdate").val(data.empid);
		    		 //初始化为原来的值
		               var opt = $("#melimanUpdate");
		               for(i=0;i<opt.length;i++){
		                 if(opt[i].value==data.empname)
		                   opt[i].selected = true;
		               }
		    		 
		    	})
			}	
		})
	}
	
	
	
	//删除
	function deleteFun(meliid){
		
    $.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/medicineLibrary?method=delete",
			data:{meliid:meliid},
			success:function(data){
				$.ajax({
	        		type:"post",
	        		url:"${pageContext.request.contextPath }/medicineLibrary?method=query",
	        		data:{ "meliid":null},
	        		dataType:"json",
	        		success:function(json){
	        				$("#table").bootstrapTable("load",json);
	        			}
	        	})
			}
		})
	}
	
	
 </script>
 

<!-- 修改病人窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">

<!-- <input type="hidden" name="meLiId" id="meLiIdUpdate"> -->

<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改药库信息</h4>
            </div>
            <div class="modal-body">
            
            <div class="form-group">
              <label for="inputEmail3" class="col-sm-2 control-label">药库编号:</label>
              <div class="col-sm-10">
                <input type="text" name="meliid" class="form-control" id="meliidUpdate" placeholder="药品编号;" />
              </div>
          	</div>
            
            	<div class="form-group">
              		<label for="inputEmail3" class="col-sm-2 control-label">负责人:</label>
              		<div class="col-sm-10">
                		<input type="hidden" name="empid" id="empidUpdate">
              			<select class="form-control" name="meliman" id="melimanUpdate">
	                	</select>
              		</div>
            	</div>
            
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">地址:</label>
					    <div class="col-sm-10">
					      <input type="text" name="meliaddr" class="form-control" id="meliaddrUpdate" placeholder="地址">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">面积:</label>
					    <div class="col-sm-10">
					      <input type="text" name="meliarea" class="form-control" id="meliareaUpdate" placeholder="面积">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">单位:</label>
					    <div class="col-sm-10">
					      <input type="text" name="meliunit" class="form-control" id="meliunitUpdate" placeholder="单位">
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
 
 
<form id="myAddForm" method="post">

<!-- <input type="hidden" name="meLiId" id="meLiIdAdd"> -->

<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加药库</h4>
            </div>
            <div class="modal-body">
            
<!--             <div class="form-group"> -->
<!--               <label for="inputEmail3" class="col-sm-2 control-label">药库编号:</label> -->
<!--               <div class="col-sm-10"> -->
<!--                 <input type="text" name="meliid" class="form-control" id="meliidAdd" placeholder="药库编号;" /> -->
<!--               </div> -->
<!--           	</div> -->
            
            	<div class="form-group">
              		<label for="inputEmail3" class="col-sm-2 control-label">负责人:</label>
              		<div class="col-sm-10">
                		<input type="hidden" name="empid" id="empidAdd">
              			<select class="form-control" name="meliman" id="empnameAdd">
              			<option value="0">请选择</option>
	                	</select>
              		</div>
            	</div>
            	
            	
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">地址:</label>
					    <div class="col-sm-10">
					      <input type="text" name="meliaddr" class="form-control" id="meliaddrAdd" placeholder="地址；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">面积:</label>
					    <div class="col-sm-10">
					      <input type="text" name="meliarea" class="form-control" id="meliareaAdd" placeholder="面积；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">单位:</label>
					    <div class="col-sm-10">
					      <input type="text" name="meliunit" class="form-control" id="meliunitAdd" placeholder="单位；">
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
     var empid=document.getElementById("melimanUpdate").value;   
	$("#empidUpdate").val(empid);
	var emd=$("#myUpdateForm").serializeArray();
     

 $.ajax({
     type:"post",//提交方式
     url:"${pageContext.request.contextPath}/medicineLibrary?method=update",
     data:emd,//数据
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
	
	var medl= $("#myAddForm").serializeArray();
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/medicineLibrary?method=add",
		data:medl,
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