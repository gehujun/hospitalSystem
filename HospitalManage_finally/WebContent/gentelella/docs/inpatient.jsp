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
	    <label for="exampleInputName">住院号:</label>
	    <input type="text" name="inpatId" class="form-control" id="SearchById" placeholder="住院号">
	</div>
	<div class="form-group">
	    <label for="exampleInputName">入院科室ID:</label>
	    <input type="text" name="deptId" class="form-control" id="SearchBydeptId" placeholder="入院科室ID">
	    
	</div>
	<div class="form-group">
	    <label for="exampleInputName">床号:</label>
	    <input type="text" name="bedId" class="form-control" id="SearchBybedId" placeholder="床号">
	    
	</div>
		<button type="button" class="btn btn-success" id="searchbyinpatId">确认</button>
		
</form>

<table id = "table">
</table>

<script type="text/javascript">
	$(function(){
	
	 //下拉数据加载  
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/department?method=query",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#deptIdSelect");
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].deptid+ "'>"  
	                       + datas[i].deptname + "</option>");  
	           }  
	         
	       } 
	       
	   	});
	})
	    $(function(){
	    	
	   	 //下拉数据加载  
	   	    $.ajax({  
	   	       type : 'get',  
	   	       url : "${pageContext.request.contextPath}/bed?method=query",  
	   	       dataType : 'json',      
	   	       success : function(datas) {//返回list数据并循环获取  
	   	       	
	   	           var select = $("#bedIdSelect");
	   	           for (var i = 0; i < datas.length; i++) { 
	   	        	var tmp = 1;
	   	           	select.append("<option value='" +datas[i].bedid+ "'>"  
	   	                       + datas[i].bedid + "</option>");
	   	           	tmp = tmp+1;
	   	           }  
	   	         	
	   	       } 
	   	       
	   	   	});
	   	 }) 
	$(function(){
		//表格数据的加载
		$('#table').bootstrapTable({
			url: '${pageContext.request.contextPath}/inpatient?method=query',//访问json数据 访问后台代码返回json数据
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
				field: 'inpatid',
				title: '住院号',
				sortable:true
			  }, {
				field: 'pat.patname',
			    title: '病人姓名',
				sortable:true
			  }, {
				field: 'pat.patsex',
		    	title: '病人性别'
			  },  {
				field: 'pat.patage',
		    	title: '病人年龄'
			  },{
				field: 'pat.pattel',
		    	title: '病人电话'
			  }, {
				field: 'dept.deptname',
		    	title: '入院科室'
			  }, {
				field: 'entertime',
		    	title: '入院时间'
			  }, {
				field: 'b.wardname',
		    	title: '病区名'
			  }, {
				field: 'bedid',
		    	title: '床号'
			  }, {
				field: 'state',
		    	title: '状态'
			  }, {
				field: 'pat.patlinkmanname',
			    title: '联系人姓名'
			  }, {
				field: 'pat.patlinkmantel',
				title: '联系人电话'
			  }, { 
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue =' <button  onclick="updateFun('+row.inpatid+')" type="button" class="btn btn-success">修改</button>';
    				  var deleteValue =' <button  onclick="deleteFun('+row.inpatid+')" type="button" class="btn btn-success">删除</button>';
    				  
    				  return updateValue+deleteValue;
    			  }
    		  }]
		
			})
	})
</script>
<script type="text/javascript">

	$(function(){
		$("#searchbyinpatId").click(function(){
			var inpatId = $("#SearchById").val();
			var deptId = $("#SearchBydeptId").val();
			var bedId = $("#SearchBybedId").val();
			$.ajax({
				type: "post",
				url: "${pageContext.request.contextPath}/inpatient?method=query",
				data:{inpatId:inpatId,deptId:deptId,bedId:bedId},
				dataType:"json",
				success: function(json) {
					//document.write("<p>hello</p>");
					$("#table").bootstrapTable('load', json);
				}
			})
		})
	})
	
	function updateFun(inpatId) { 
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/inpatient?method=sendUpdate",
			data:{inpatId:inpatId},
			dataType:"json",
			success:function(data) {
				
				/* var opts = document.getElementByID("bedId").value;
				alert(date[0].state); */
				
				$('#myUpdateModal').modal().on('shown.bs.modal',function() {
					   $("#inpatIdUpdate").val(data[0].inpatid);
		    		   $("#patNameUpdate").val(data[0].pat.patname);
		    		   /* $("#patSexUpdate").val(data[0].pat.patSex); */
		    		   $("#patAgeUpdate").val(data[0].pat.patage);
		    		   $("#patTelUpdate").val(data[0].pat.pattel);
		    		   $("#empLinkmanNameUpdate").val(data[0].pat.patlinkmanname);
		    		   $("#empLinkmanTelUpdate").val(data[0].pat.patlinkmantel);
		    		   /* $("#patSexUpdate").val(data[0].pat.patSex);
		    		   $("#bedIdSelect").val(data[0].bedId);
		    		   $("#stateUpdate").val(date[0].state); */
		    		   var patSexs = document.getElementByID("patSexUpdate");
		    		  /* document.getElementByID("bedId").value = data[0]. */
		    		  $("#bedIdSelect").val(data[0].bedid);
		    		   $("#stateUpdate").val(date[0].state);
		    		   $("#deptIdSelect").val(date[0].deptid);
		    		   $("#patSexUpdate").val(data[0].pat.patsex);

		    		 
		    	})
		    	
			}
		})
	}
	
	function deleteFun(inpatId) {
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/inpatient?method=delete",
			data:{inpatId:inpatId},
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

<input type="hidden" name="inpatId" id="inpatIdUpdate">


<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

<div class="table-responsive">
  <div class="modal-dialog modal-sm1" style="width:800px;">
   <div class="modal-content">
   <div class="modal-header">
    
     <!-- 交互信息详情 -->
     <label data-locale="i18n_interactiveInformationDetails" style="cursor: pointer;"></label>
     <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
     <h4 class="modal-title" id="myModalLabel">修改住院病人信息</h4>
   
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
		    <label for="inputEmail3" class="col-sm-2 control-label">病人电话:</label>
		    <div class="col-sm-10">
		      <input type="text" name="patTel" class="form-control" id="patTelUpdate" placeholder="病人电话">
		    </div>
	 </div>
	 <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">联系人姓名:</label>
		    <div class="col-sm-10">
		      <input type="text" name="patLinkmanName" class="form-control" id="empLinkmanNameUpdate" placeholder="联系人姓名">
		    </div>
	 </div>
	 <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">联系人电话:</label>
		    <div class="col-sm-10">
		      <input type="text" name="patLinkmanTel" class="form-control" id="empLinkmanTelUpdate" placeholder="联系人电话">
		    </div>
	 </div>
	 <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">入院科室:</label>
		    <div class="col-sm-10">
		      
		      <select id="deptIdSelect" name="deptId" style="width:100px" class="form-control input-sm">
		      
		      </select>
		    </div>
	 </div>
	 <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">病人床号:</label>
		    <div class="col-sm-10">
		      <!-- <input type="text" name="bedId" class="form-control" id="bedIdUpdate" placeholder="病人床号"> -->
		      <select id="bedIdSelect" name="bedId" style="width:100px" class="form-control input-sm">
		      
		      </select>
		    </div>
	 </div>
	 <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">病人状态:</label>
		    <div class="col-sm-10">
		      
		      <select id="stateUpdate" name="state" style="width:100px" class="form-control input-sm">
		      
			      <option value="1">住院</option>
				  <option value="0">出院</option>
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
	     var inpatient = $("#myUpdateForm").serializeArray();
	    //alert(inpatient);
	    //document.write(inpatient);
	    $.ajax({
	    	 type:"post",//提交方式
	    	 url:"${pageContext.request.contextPath}/inpatient?method=update",
	    	 data:inpatient,//数据
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