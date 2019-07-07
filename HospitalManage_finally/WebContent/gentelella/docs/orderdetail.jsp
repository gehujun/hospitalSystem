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

 
<!-- 搜索工具栏 -->
<div id="toolbar">
   <div id="showorhide" class="a">
          <form class="form-inline" id="mySearchForm">
<!-- 			  <div class="form-group">
			    <label for="exampleInputName2">药品编号:</label>
			    <input type="text" name="ordid" class="form-control" id="ordidSearch" placeholder="药品编号">
			  </div> -->
			  <div class="form-group">
			    <label for="exampleInputName2">药品数量:</label>
			    <input type="text" name="detnum" class="form-control" id="detnumSearch" placeholder="药品数量">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputEmail2">药品批号:</label>
			    <input type="text" name="detbatchnum" class="form-control" id="detbatchnumearch" placeholder="药品数量">
			  </div>
			  <button id="btn_query" type="button" class="btn btn-success">查询</button>
			  <button id="btn_add" type="button" class="btn btn-success" onclick="addFun()">添加订单细则</button>
			  
		 </form>
   </div>
   <button id="open" class="btn btn-success">展开或折叠</button>
</div>
<table id="table"></table>

<!-- 工具栏 -->
<div id="toolbar"></div>

<script type="text/javascript">

    $(function(){
     
    	$("#table").bootstrapTable({
    		url:"${pageContext.request.contextPath}/orderdetail?method=query",//访问地址
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
    		    field: 'detid',
    		    title: '细则号',
    		    sortable:true
    		  }, {
    		    field: 'ordid',
    		    title: ' 订单号'
    		  }, {
    		    field: 'mediid',
    		    title: '药品编号'
    		  }, {
    		    field: 'detnum',
    		    title: '数量'
    		  },{
    		    field: 'dettotalfee',
    		    title: '总价'
    		  }, {
    		    field: 'detbatchnum',
    		    title: '批号'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue =' <button  onclick="updateFun('+row.detid+')" type="button" class="btn btn-success">修改</NOtton>';
    				  var deleteValue =' <button  onclick="deleteFun('+row.detid+')" type="button" class="btn btn-success">删除</NOtton>';
    				  
    				  return updateValue+deleteValue;
    			  }
    		  }]
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
    	    	
    })
 </script>
 
 <script type="text/javascript">
  $(function(){
	  <!--搜索条件-->
  	//条件搜索
		$("#btn_query").click(function() {
	                //var ordid = $("#ordidSearch").val();
	                var detnum = $("#detnumSearch").val();
	                var detbatchnum = $("#detbatchnum").val();
	              
	                $.ajax({
	                     type: "post",//请求的方式
	                     url: "${pageContext.request.contextPath}/orderdetail?method=query",
	                     //data: {ordid:ordid,deptAddress:deptAddress,deptPhone:deptPhone}, //查询条件
	                     data:{detnum:detnum,detbatchnum:detbatchnum},
	                     dataType:"json",//返回的数据类型为json
	                     success : function(json) {
	                        $("#table").bootstrapTable('load', json);//主要是要这种写法
	                    }
	                });

	    });
  	
  	  
  	  
  })
  
   //修改
   function updateFun(detid){
	 
	  $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/order?method=query",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#orderqueryupdate");
	           select.empty();
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].ordid+ "'>"  
	                       + datas[i].ordid + "</option>");  
	           }  	         
	       } 	       
	   	});
	  
	  $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/medicine?method=query",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#medicinequeryupdate");
	           select.empty();
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].mediid+ "'>"  
	                       + datas[i].mediid + "</option>");  
	           }  	         
	       } 	       
	   	});
	  
	
	  	  
	  
	  $.ajax({
		    type:"post",//提交方式
		    url:"${pageContext.request.contextPath}/orderdetail?method=sendUpdate",
		    data:{detid:detid},//传主键值
		    dataType:"json",//返回的数据类型为json
		    success:function(data){
		    	
		    	  //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    		   $("#detidUpdate").val(data.detid);
		    		   $("#ordidUpdate").val(data.ordid);
		    		   $("#mediidUpdate").val(data.mediid);
		    		   $("#detnumUpdate").val(data.detnum);
		    		   $("#dettotalfeeUpdate").val(data.dettotalfee);
		    		   $("#detbatchnumUpdate").val(data.detbatchnum);
		    	})
		    }
	  })
  }
  
  
  //添加
  function addFun(){
		
	  $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/order?method=query",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#orderadd");
	           select.empty();
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].ordid+ "'>"  
	                       + datas[i].ordid + "</option>");  
	           }  	         
	       } 	       
	   	});
	  
	  
	  $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/medicine?method=query",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#medicineadd");
	           select.empty();
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].mediid+ "'>"  
	                       + datas[i].mediid + "</option>");  
	           }  	         
	       } 	       
	   	});
	  	  
	  //弹出添加窗体
	  $('#myAddModal').modal().on('shown.bs.modal',function() {
		  
	  })
 }
  
  
  
function deleteFun(detid){
	  $.ajax({
		  type:"post",
		  url:"${pageContext.request.contextPath}/orderdetail?method=delete",
		  data:{detid:detid},//传主键值
		  dataType:"json",//返回的数据类型为json
		  success:function(data){
			  location.reload(true);
		  },
		  error: function(xhr, type) {
		  }
	  })
  } 
  
  
 </script>
 
 
<!-- 修改页面窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">

<input type="hidden" name="detid" id="detidUpdate">

<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 60%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改订单细则信息</h4>
            </div>
            <div class="modal-body">
            
            	<div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">订单号:</label>
					    <div class="col-sm-10">
					      <select id="orderqueryupdate" name="ordid" style="width:100px" class="form-control input-sm">
		      
		      			  </select>
					    </div>
				</div>
                <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">药品编号:</label>
					    <div class="col-sm-10">
					      <select id="medicinequeryupdate" name="mediid" style="width:100px" class="form-control input-sm">
		      
		      			  </select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">数量:</label>
					    <div class="col-sm-10">
					      <input type="text" name="detnum" class="form-control" id="detnumUpdate" placeholder="数量；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">总价:</label>
					    <div class="col-sm-10">
					      <input type="text" name="dettotalfee" class="form-control" id="dettotalfeeUpdate" placeholder="总价；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">批号:</label>
					    <div class="col-sm-10">
					      <input type="text" name="detbatchnum" class="form-control" id="detbatchnumUpdate" placeholder="批号；">
					    </div>
				 </div>
<!-- 				 <div class="form-group"> -->
<!-- 					    <label for="inputEmail3" class="col-sm-2 control-label">科室负责人:</label> -->
<!-- 					    <div class="col-sm-10"> -->
<!-- 					         <select id="userUpdate" name="userId"> -->
<!-- 					              <option value="1">张三</option> -->
<!-- 					              <option value="2">李四</option> -->
<!-- 					         </select> -->
<!-- 					    </div> -->
<!-- 				 </div> -->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnUpadte" class="btn btn-primary">更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</form>
 
 
 
<!-- 添加窗体 -->
<!-- 模态框（Modal） -->
<form id="myAddForm" method="post">

<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myAddModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 60%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myAddModalLabel">添加订单细则</h4>
            </div>
            <div class="modal-body">
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">订单号:</label>
					    <div class="col-sm-10">
					     <select id="orderadd" name="ordid" style="width:100px" class="form-control input-sm">
		      
		      			  </select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">药品编号:</label>
					    <div class="col-sm-10">
					      <select id="medicineadd" name="mediid" style="width:100px" class="form-control input-sm">		      
		      			  </select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">数量:</label>
					    <div class="col-sm-10">
					      <input type="text" name="detnum" class="form-control" id="detnumAdd" placeholder="数量:">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">总价:</label>
					    <div class="col-sm-10">
					      <input type="text" name="dettotalfee" class="form-control" id="dettotalfeeAdd" placeholder="总价:">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">批号:</label>
					    <div class="col-sm-10">
					      <input type="text" name="detbatchnum" class="form-control" id="detbatchnumAdd" placeholder="批号:">
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
  $("#btnUpadte").click(function(){
	  
	    //获取表单中的数据
	     var tDepartemnt = $("#myUpdateForm").serializeArray();
	    
	    $.ajax({
	    	 type:"post",//提交方式
	    	 url:"${pageContext.request.contextPath}/orderdetail?method=update",
	    	 data:tDepartemnt,//数据
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
		//alert("sfdsf");
		//获取
		var workinghours = $("#myAddForm").serializeArray();
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/orderdetail?method=add",
			data:workinghours,
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
<!-- 修改页面窗体 -->
 




</body>
</html>