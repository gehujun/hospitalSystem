<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>病历管理页面</title>

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
			    <label for="exampleInputName2">病历号:</label>
			    <input type="text" class="form-control" id="medhId" placeholder="科室名称">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">病人名字</label>
			    <input type="text" class="form-control" id="medhPatId" placeholder="科室地址">
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
    		  url: '${pageContext.request.contextPath }/Medicalhistory?method=query',
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
    		  exportDataType:true,//可以全部导出
    		  exportTypes:['pdf','xlsx'],//导出的数据类型（pdf文件、excel文件）
    		  columns: [{
    		    field: 'medhid',
    		    title: '病历号'
    		  }, {
    		    field: 'patient.patname',
    		    title: '病人'
    		  }, {
    		    field: 'medhtype',
    		    title: '类别'
    		  }, {
    		    field: 'medhhistory',
    		    title: '既往病史'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue='<button onclick="updateFun('+row.medhid+') " type="button" class="btn btn-success">修改</button>';
    				  var deleteValue='<button onclick="deleteFun('+row.medhid+') " type="button" class="btn btn-success">删除</button>';
    				  return updateValue+deleteValue;
    			  }
    		  }]
    		})
    })

//删除
	function deleteFun(medhid){
			
		$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath }/Medicalhistory?method=delete",
				data:{medhid:medhid},
				success:function(data){
					$.ajax({
		        		type:"post",
		        		url:"${pageContext.request.contextPath }/Medicalhistory?method=query",
		        		data:{ "patid":null,"patname":null},
		        		dataType:"json",
		        		success:function(json){
		        				$("#table").bootstrapTable("load",json);
		        			}
		        	})
				}
			})
		}
//添加
	$(function(){
		$("#btn_add").click(function(){
			$('#myAddModal').modal().on('shown.bs.modal');
			//通过调用科室方法获取对象
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/patient?method=query",
				dataType : 'json',
	            success : function(datas) {//返回list数据并循环获取  
	            	document.getElementById("patNameAdd").options.length = 0;//清空选项
	                var select = $("#patNameAdd");
	                for (var i = 0; i < datas.length; i++) { 
	                	select.append("<option value='" +datas[i].patid+ "'>"
	                            + datas[i].patname + "</option>");  
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
        	var medhId=$("#medhId").val();
    		var medhPatId=$("#medhPatId").val();
        	
        	$.ajax({
        		type:"post",
        		url:"${pageContext.request.contextPath }/Medicalhistory?method=query",
        		data:{ "medhId":medhId,"medhPatId":medhPatId},
        		dataType:"json",
        		success:function(json){
        				$("#table").bootstrapTable("load",json);
        			}
        	})
        	
        	
        });
    })
	
//修改
	function updateFun(medhid){
		
	//通过调用科室方法获取对象 regDeptNameUpdate
		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/patient?method=query",
			dataType : 'json',
	        success : function(datas) {//返回list数据并循环获取  
	        	document.getElementById("patNameUpdate").options.length = 0;//清空选项
	            var select = $("#patNameUpdate");
	            for (var i = 0; i < datas.length; i++) { 
	            	select.append("<option value='" +datas[i].patid+ "'>"  
	                        + datas[i].patname + "</option>");  
	            }  
	        }
		})
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath }/Medicalhistory?method=sendUpdate",
			data:{medhid:medhid},
			dataType:"json",
			success:function(data){
				 //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    		 alert(data.medhId)
		    			$("#medhIdUpdate").val(data.medhid);	    		   
		    			$("#medhhisUpdate").val(data.medhhistory);

		    		   //初始化 为原来的值
		    		   var opt = document.getElementById("patNameUpdate");
		    		   for(i=0;i<opt.length;i++){
		    			   if(opt[i].value==data.medhpatid)
		    				   opt[i].selected = true;
		    		   }
		    		   var opt = document.getElementById("patTypeUpdate");
		    		   for(i=0;i<opt.length;i++){
		    			   if(opt[i].value==data.medhtype)
		    				   opt[i].selected = true;
		    		   }
		    	})
			}	
		})
	}
	
	
</script>

	
<!-- 添加按钮窗体 -->
<!-- 模态框（Modal） -->
<form id="myAddForm" method="post">

<input type="hidden" name="medhid" id="medhidAdd">

<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">增加病历</h4>
            </div>
            <div class="modal-body">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病人名称:</label>
					    <div class="col-sm-10">
					      <input type="hidden" name="patId" id="patIdAdd">
					    <select class="form-control" name="patName" id="patNameAdd">
					      	<option value="0">请选择</option>
					      </select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">类别:</label>
					    <div class="col-sm-10">
					    <select class="form-control" name="patType" id="patTypeAdd">
					      	<option value="住院">住院</option>
					      	<option value="门诊">门诊</option>
					      </select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">既往病历:</label>
					    <div class="col-sm-10">
					      <input type="text" name="medhhis" class="form-control" id="medhhisAdd" placeholder="既往病历；">
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

<!-- 修改按钮窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">

<input type="hidden" name="medhId" id="medhIdUpdate">

<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改病历信息</h4>
            </div>
            <div class="modal-body">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病人名称:</label>
					    <div class="col-sm-10">
					      <input type="hidden" name="patId" id="patIdUpdate">
					    <select class="form-control" name="patName" id="patNameUpdate">
					      	<option value="0">请选择</option>
					      </select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">类别:</label>
					    <div class="col-sm-10">
					      
					    <select class="form-control" name="patType" id="patTypeUpdate">
					      	<option value="住院">住院</option>
					      	<option value="门诊">门诊</option>
					      </select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">既往病历:</label>
					    <div class="col-sm-10">
					      <input type="text" name="medhhis" class="form-control" id="medhhisUpdate" placeholder="挂单医生；">
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

<script type="text/javascript">
//提交添加按钮
$('#btnAdd').click(function(){
	//获取
	alert("成功点击");
	var patNameAdd = document.getElementById("patNameAdd").value;
	$("#patIdAdd").val(patNameAdd);
	
	var reg = $("#myAddForm").serializeArray();
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/Medicalhistory?method=add",
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

//提交更新按钮
 $("#btnUpadte").click(function(){
 	alert("成功点击");
     //获取表单中的数据
     var patNameUpdate = document.getElementById("patNameUpdate").value;
     $("#patIdUpdate").val(patNameUpdate);
     var medh = $("#myUpdateForm").serializeArray();
     
     $.ajax({
     	 type:"post",//提交方式
     	 url:"${pageContext.request.contextPath}/Medicalhistory?method=update",
     	 data:medh,//数据
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



</body>

</html>