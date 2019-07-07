<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>病人信息</title>
<!-- jsp包含指令 -->
<%@ include file="../../common/commoncss.jsp" %>
<%@ include file="../../common/commonjs.jsp" %>

<body>

<form class="form-inline" id = "mySearchForm">
	<div class="form-group">
	    <label for="exampleInputName">主治医师编号:</label>
	    <input type="text" name="empId" class="form-control" id="SearchByempId" placeholder="主治医师编号">
	    
	</div>
	<div class="form-group">
	    <label for="exampleInputName">医嘱号:</label>
	    <input type="text" name="maId" class="form-control" id="SearchBymaId" placeholder="医嘱号">
	    
	</div>
	<div class="form-group">
	    <label for="exampleInputName">疾病名称:</label>
	    <input type="text" name="diseaseName" class="form-control" id="SearchBydiseaseName" placeholder="疾病名称">
	</div>
		<button type="button" class="btn btn-success" id="searchbyInfro">确认</button>
		
</form>
<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myAddModal">
	添加医嘱
</button>
<form id="myAddForm" method="post">
<!-- <input type="hidden" name="maId" id="maIdUpdate"> -->
<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="table-responsive">
  	  <div class="modal-dialog modal-sm1" style="width:800px;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					添加医嘱
				</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
				    <label for="inputEmail3" class="col-sm-2 control-label">疾病名称:</label>
				    <div class="col-sm-10">
				      <input type="text" name="diseaseName" class="form-control" id="AdddiseaseName" placeholder="疾病名称">
				    </div>
	 			</div>
	 			<div class="form-group">
				    <label for="inputEmail3" class="col-sm-2 control-label">开具日期:</label>
				    <div class="col-sm-10">
				      <input type="text" name="madate" class="form-control" id="Addmadate" placeholder="开具日期">
				    </div>
	 			</div>
	 			<div class="form-group">
				    <label for="inputEmail3" class="col-sm-2 control-label">医嘱内容:</label>
				    <div class="col-sm-10">
				      <input type="text" name="maContent" class="form-control" id="AddmaContent" placeholder="医嘱内容">
				    </div>
	 			</div>
	 			<div class="form-group">
				    <label for="inputEmail3" class="col-sm-2 control-label">主治医师:</label>
				    
				      <div class="col-sm-10">
		      				
					      <select id="empNameSelect2" name="empId" style="width:100px" class="form-control input-sm">
					      
					      </select>
				    
	 				</div>
	 			</div>
	 			<div class="form-group">
				    <label for="inputEmail3" class="col-sm-2 control-label">住院号:</label>
				    <div class="col-sm-10">
				      <select id="inpatIdSelect" name="inpatId" style="width:100px" class="form-control input-sm">
					      
					  </select>
					  
				    </div>
	 			</div>
	 			<div class="form-group">
				    <label for="inputEmail3" class="col-sm-2 control-label">能否出院:</label>
				    <div class="col-sm-10">
				      <select id="isOutSelect" name="isOut" style="width:100px" class="form-control input-sm">
				      	<option value="0">否</option>
						<option value="1">能</option>
				      </select>
				    </div>
	 			</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary" id = "InfroAdd">添加
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
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
	
	 //下拉数据加载  
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/inpatient?method=inpatIdSelect",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       		
	           var select = $("#inpatIdSelect");
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].inpatid+ "'>"  
	                       + datas[i].inpatid + "</option>");  
	           }  
	         
	       } 
	       
	   	});
	})
	$(function(){
		//表格数据的加载
		$('#table').bootstrapTable({
			url: '${pageContext.request.contextPath}/medicaladvice?method=query',//访问json数据 访问后台代码返回json数据
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
				field: 'maid',
				title: '医嘱号',
				sortable:true
			  }, {
				field: 'diseasename',
			    title: '疾病名称'
			  }, {
				field: 'macontent',
		    	title: '医嘱内容'
			  }, {
				field: 'pat.patname',
		    	title: '病人姓名'
			  }, {
				field: 'pat.patsex',
		    	title: '病人性别'
			  },{
				field: 'pat.patage',
		    	title: '病人年龄'
			  },{
				field: 'emp.empname',
		    	title: '主治医师'
			  }, {
				field: 'emp.emptel',
		    	title: '医生电话'
			  }, {
				field: 'prot.profname',
		    	title: '医生职称'
			  }, { 
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue =' <button  onclick="updateFun('+row.maid+')" type="button" class="btn btn-success">修改</button>';
    				  var deleteValue =' <button  onclick="deleteFun('+row.maid+')" type="button" class="btn btn-success">删除</button>';
    				  
    				  return updateValue+deleteValue;
    			  }
    		  }]
		
			})
	})
</script>
<script type="text/javascript">

	$(function(){
		$("#searchbyInfro").click(function(){
			var empId = $("#SearchByempId").val();
			var maId = $("#SearchBymaId").val();
			var diseaseName = $("#SearchBydiseaseName").val();
			$.ajax({
				type: "post",
				url: "${pageContext.request.contextPath}/medicaladvice?method=query",
				data:{empId:empId,maId:maId,diseaseName:diseaseName},
				dataType:"json",
				success: function(json) {
					//document.write("<p>hello</p>");
					$("#table").bootstrapTable('load', json);
				}
			})
		})
	})
	
	function updateFun(maId) { 
		
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/medicaladvice?method=sendUpdate",
			data:{maId:maId},
			dataType:"json",
			success:function(data) {
			
				/* var opts = document.getElementByID("bedId").value;
				alert(date[0].state); */
				
				$('#myUpdateModal').modal().on('shown.bs.modal',function() {
					alert(data.maid);
					alert(data.pat.patage);
						/*  alert(data.emp.empName);  */
					   $("#maIdUpdate").val(data.maid);
					   $("#patNameUpdate").val(data.pat.patname);
		    		   $("#patAgeUpdate").val(data.pat.patage);
		    		   $("#diseaseNameUpdate").val(data.diseasename);
		    		   $("#maContentUpdate").val(data.macontent);
		    		   
		    		   $("#empNameSelect1").val(data.emp.empid);
		    		   $("#patSexUpdate").val(data.pat.patsex);
		    		   $("#isOutSelect").val(data.isout);


		    	})
		    	
			}
		})
	}
	
	function deleteFun(maId) {
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/medicaladvice?method=delete",
			data:{maId:maId},
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
<input type="hidden" name="maId" id="maIdUpdate">
<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

<div class="table-responsive">
  <div class="modal-dialog modal-sm1" style="width:800px;">
   <div class="modal-content">
   <div class="modal-header">
    
     <!-- 交互信息详情 -->
     <label data-locale="i18n_interactiveInformationDetails" style="cursor: pointer;"></label>
     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
     <h4 class="modal-title" id="myModalLabel">修改医嘱信息</h4>
   
   </div> 
   <div class="modal-body">
   	<div class="form-group">
	    <label for="inputEmail3" class="col-sm-2 control-label">病人姓名:</label>
	    <div class="col-sm-10">
	      <input type="text" name="patName" class="form-control" id="patNameUpdate" placeholder="病人姓名">
	    </div>
	</div>
	<div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">病人性别:</label>
		    <div class="col-sm-10">
		      <!-- <input type="text" name="patSex" class="form-control" id="patSexUpdate" placeholder="病人性别"> -->
		      <select id="patSexUpdate" name="patSex" style="width:100px" class="form-control input-sm">
		      
			      <option value="男">男</option>
				  <option value="女">女</option>
			  </select>
		    </div>
	 </div>
	 <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">病人年龄:</label>
		    <div class="col-sm-10">
		      <input type="text" name="patAge" class="form-control" id="patAgeUpdate" placeholder="病人年龄">
		    </div>
	 </div>
	 <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">疾病名称:</label>
		    <div class="col-sm-10">
		      <input type="text" name="diseaseName" class="form-control" id="diseaseNameUpdate" placeholder="疾病名称">
		    </div>
	 </div>
	 <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">主治医师:</label>
		    <div class="col-sm-10">
		      
		      <select id="empNameSelect1" name="empId" style="width:100px" class="form-control input-sm">
		      
		      </select>
		    </div>
	 </div>
	 <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">医嘱内容:</label>
		    <div class="col-sm-10">
		      <input type="text" name="maContent" class="form-control" id="maContentUpdate" placeholder="医嘱内容">
		    </div>
	 </div>
	 <div class="form-group">
		   <label for="inputEmail3" class="col-sm-2 control-label">能否出院:</label>
		   <div class="col-sm-10">
		     <!-- <input type="text" name="isOut" class="form-control" id="AddisOut" placeholder="能否出院"> -->
		     <select id="isOutSelect" name="isOut" style="width:100px" class="form-control input-sm">
		      	<option value="0">否</option>
				<option value="1">能</option>
		      </select>
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
	     var medicaladvice = $("#myUpdateForm").serializeArray();
	    //alert(inpatient);
	    /* document.write(medicaladvice); */
	    $.ajax({
	    	 type:"post",//提交方式
	    	 url:"${pageContext.request.contextPath}/medicaladvice?method=update",
	    	 data:medicaladvice,//数据
	    	 dataType:"json",//返回的数据类型为json
	    	 success:function(json){
	    		 
	    		 if(json ==1){
	    			 
	    			 $("#myUpdateModal").modal('hide');  //手动关闭
	    			 //$("#table").bootstrapTable("refresh");//刷新表格中的数据
	    			 location.reload(true);   
	    			 
	    		 }
	    	 }
	    })
	    
  })
  
</script> 
<script type="text/javascript">
  $("#InfroAdd").click(function(){
	  	
	    //获取表单中的数据
	     var medicaladvice = $("#myAddForm").serializeArray();
	    //alert(inpatient);
	    //document.write(inpatient);
	    $.ajax({
	    	 type:"post",//提交方式
	    	 url:"${pageContext.request.contextPath}/medicaladvice?method=add",
	    	 data:medicaladvice,//数据
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