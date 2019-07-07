<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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

<div id="toolbar">
   <div id="showorhide" class="a">
	<form class="form-inline" id="mySearchForm">
	  <div class="form-group">
	    <label for="empName">员工姓名</label>
	    <input type="text" class="form-control" name="empName" id="empNameSearch" >
	  </div>
	  <div class="form-group">
	    <label for="profName">职称名称</label>
	  	<select name="profName" id="profNameSearch">
			<option value="0">请选择</option>
		</select>
	  </div>
	  <button type="button" class="btn btn-success" id="btn_query">查询</button>
	  <button type="button" class="btn btn-success" id="btn_add">添加员工</button>
	</form>
	</div>
   <button id="open" class="btn btn-success">展开或折叠</button>
</div>

<table id="table"></table>
<!-- 工具栏 -->
<div id="toolbar"></div>
<!-- bootstrap表格的js -->
<script type="text/javascript">
	$(function(){
		$("#table").bootstrapTable({
			url:"${pageContext.request.contextPath}/user?method=query",//访问地址
			method:"get",//请求的方式
			toolbar:"#toolbar",//工具栏
			pagination:true,//允许分页
			pageSize:8,//每页显示的条数
			pageList:[2,3,5,8,10],//每页显示的条数集合
			showColumns:true,//是否显示所有的列
			showRefresh:true,//是否显示刷新
			striped:true,//隔行变色   每一行的唯一标识，一般为主键列
		    showExport:true,//允许导出
			    exportDataType:'all',//可以全部导出
			    exportTypes:['pdf','excel'],//导出的数据类型（pdf文件、excel文件）
			columns: [{
			    field: 'empid',
			    title: '员工编号',
			    sortable:true
			  }, {
			    field: 'prof.profname',
			    title: '职位名称'
			  }, {
			    field: 'empname',
			    title: '员工名字'
			  }, {
			    field: 'empsex',
			    title: '性别'
			  },{
				  title:"操作",
				  formatter:function(value,row,index){
					  var updateValue =' <button  onclick="updateFun('+row.empid+')" type="button" class="btn btn-success">修改</button>';
					  var deleteValue =' <button  onclick="deleteFun('+row.empid+')" type="button" class="btn btn-success">删除</button>';
					  return updateValue+deleteValue;
				  }
			  }]
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
		
		 //下拉数据加载  
	    $.ajax({  
	        type : 'get',  
	        url : "${pageContext.request.contextPath}/position?method=query",  
	        dataType : 'json',      
	        success : function(datas) {//返回list数据并循环获取  
	        	
	            var select = $("#profNameSearch");
	            for (var i = 0; i < datas.length; i++) { 
	            	select.append("<option value='" +datas[i].profid+ "'>"  
	                        + datas[i].profname + "</option>");  
	            }  
	          
	        } 
	        
	    });
	})

</script>

<!-- 查询按钮js -->
<script type="text/javascript">
$(function(){
	  <!--搜索条件-->
	//条件搜索
		$("#btn_query").click(function() {
			//获取表单中的数据
	     	var searchPosition = $("#mySearchForm").serializeArray();
          var empName = $("#empNameSearch").val();
          var profName = $("#profNameSearch").val();
          
            $.ajax({
                 type: "post",//请求的方式
                 url: "${pageContext.request.contextPath}/user?method=query",
                 data: {empName:empName,profName:profName}, //查询条件
                 dataType:"json",//返回的数据类型为json
                 success : function(json) {
                    $("#table").bootstrapTable('load', json);//主要是要这种写法
                }
            });

	    });
})
</script>

<!-- 删除按钮 -->
<script type="text/javascript">
function deleteFun(empid) {
	$.ajax({
		type:"post",//提交方式
	    url:"${pageContext.request.contextPath}/user?method=delete",
	    data:{empid:empid},//传主键值
	    dataType:"json",//返回的数据类型为json
	    success : function(data) {
            //location.reload(true);
            $("#myUpdateModal").modal('hide');  //手动关闭
	    	$("#table").bootstrapTable("refresh");//刷新表格中的数据
            },
            error:function(xhr,type){
            	
            }
	})
  }

</script>

<!-- 增加按钮 -->
<script type="text/javascript">
$(function(){
	$("#btn_add").click(function(){
		$('#myAddModal').modal().on('shown.bs.modal');
		//职称下拉
		$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/position?method=query",
				dataType : 'json',
	            success : function(datas) {//返回list数据并循环获取  
	            	document.getElementById("empProfAdd").options.length = 0;//清空选项
	                var select = $("#empProfAdd");
	                for (var i = 0; i < datas.length; i++) { 
	                	select.append("<option value='" +datas[i].profid+ "'>"
	                            + datas[i].profname + "</option>");  
	                }  
	            }
			})
	})
})

</script>

<!-- 修改 -->
<script type="text/javascript">
function updateFun(empid){
	  //科室选项
	  $.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/position?method=query",
			dataType : 'json',
          success : function(datas) {//返回list数据并循环获取  
        	  	document.getElementById("empProfUpdate").options.length = 0;//清空选项
            	var select = $("#empProfUpdate");
            	for (var i = 0; i < datas.length; i++) { 
            		select.append("<option value='" +datas[i].profid+ "'>"
                        + datas[i].profname + "</option>");  
            	}
        	}
		})
	  
	  $.ajax({
		    type:"post",//提交方式
		    url:"${pageContext.request.contextPath}/user?method=sendUpdate",
		    data:{empid:empid},//传主键值
		    dataType:"json",//返回的数据类型为json
		    success:function(data){
		    	 
		    	  //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    		
	    		   $("#empIdUpdate").val(data.empid);
	    		   $("#empNameUpdate").val(data.empname);
	    		   $("#empSexUpdate").val(data.empsex);
	    		   empNameUpdate
	    		   	var opt = document.getElementById("empProfUpdate");
	   		   		for(i=0;i<opt.length;i++){
	   			   	if(opt[i].value==data.profid)
	   				   opt[i].selected = true;
	   		   		}   
		    	})
		    }
	  })
	  
	  
}
</script>

<!-- 添加病人窗体 -->
<!-- 模态框（Modal） -->
<form id="myAddForm" method="post">
<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加职工</h4>
            </div>
            <div class="modal-body">
                   
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">职工姓名:</label>
					    <div class="col-sm-10">
					      <input type="text" name="empName" class="form-control" id="empNameAdd" placeholder="职工姓名:">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">职工性别</label>
					    <div class="col-sm-10">
					    
						<select class="form-control" name="empSex" id="empSexAdd">
					      	<option value="男">男</option>
					      	<option value="女">女</option>
					      </select>
					    <!-- <input type="text" name="deptid" class="form-control" id="deptidAdd" placeholder="科室号:"> -->
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">职工职位:</label>
					    <div class="col-sm-10">
					    	<select class="form-control" name="empProf" id="empProfAdd">
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

<!-- 修改页面窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">
<input type="hidden" name="empId" id="empIdUpdate">
<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加职工</h4>
            </div>
            <div class="modal-body">
                   
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">职工姓名:</label>
					    <div class="col-sm-10">
					      <input type="text" name="empName" class="form-control" id="empNameUpdate" placeholder="职工姓名:">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">职工性别</label>
					    <div class="col-sm-10">					    
						<select class="form-control" name="empSex" id="empSexUpdate">
					      	<option value="男">男</option>
					      	<option value="女">女</option>
					      </select>
					    <!-- <input type="text" name="deptid" class="form-control" id="deptidAdd" placeholder="科室号:"> -->
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">职工职位:</label>
					    <div class="col-sm-10">
					    	<select class="form-control" name="empProf" id="empProfUpdate">
					    		<option value="0">请选择</option>
					    	</select>
					    </div>
				 </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnUpadte" class="btn btn-primary">修改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</form>

<!-- 提交按钮 -->
<script type="text/javascript">
$('#btnAdd').click(function(){
	//获取
	alert("成功点击");

	var emp = $("#myAddForm").serializeArray();
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/user?method=add",
		data:emp,
		dataType:"json",
		success:function(json){
			if(json ==1){
   			 
   			 $("#myAddModal").modal('hide');  //手动关闭
   			 $("#table").bootstrapTable("refresh");//刷新表格中的数据
			}
		}
	})
})

$("#btnUpadte").click(function(){
		  
		//获取表单中的数据
	    var emp = $("#myUpdateForm").serializeArray();
	    
	    $.ajax({
	    	 type:"post",//提交方式
	    	 url:"${pageContext.request.contextPath}/user?method=update",
	    	 data:emp,//数据
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