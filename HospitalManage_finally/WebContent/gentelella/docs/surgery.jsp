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
			  <div class="form-group">
			    <label for="exampleInputName2">手术名字:</label>
			    <input type="text" name="surgname" class="form-control" id="surgnameSearch" placeholder="手术名字">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">手术费用:</label>
			    <input type="text" name="surgfee" class="form-control" id="surgfeeSearch" placeholder="手术名字">
			  </div>
<!-- 			  <div class="form-group">
			    <label for="exampleInputEmail2">科室电话:</label>
			    <input type="text" name="surgpushfee" class="form-control" id="surgpushfeeSearch" placeholder="科室电话">
			  </div> -->
			  <button id="btn_query" type="button" class="btn btn-success">查询</button>
			  <button id="btn_add" type="button" class="btn btn-success" onclick="addFun()">添加手术</button>
			  
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
    		url:"${pageContext.request.contextPath}/surgery?method=query",//访问地址
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
    		    field: 'surgid',
    		    title: '手术id',
    		    sortable:true
    		  }, {
    		    field: 'surgname',
    		    title: ' 手术名字'
    		  }, {
    		    field: 'professionaltitle.profname',
    		    title: '职称名称'
    		  }, {
    		    field: 'surgpushfee',
    		    title: '手术金额提成'
    		  }, {
    		    field: 'surgfee',
    		    title: '手术费用'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue =' <button  onclick="updateFun('+row.surgid+')" type="button" class="btn btn-success">修改</NOtton>';
    				  var deleteValue =' <button  onclick="deleteFun('+row.surgid+')" type="button" class="btn btn-success">删除</NOtton>';
    				  
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
	                var surgname = $("#surgnameSearch").val();
	                /* var surgprofid = $("#surgprofidSearch").val(); */
	                var surgfee = $("#surgfeeSearch").val();
	              
	                $.ajax({
	                     type: "post",//请求的方式
	                     url: "${pageContext.request.contextPath}/surgery?method=query",
	                     data: {surgname:surgname,surgfee:surgfee}, //查询条件
	                     dataType:"json",//返回的数据类型为json
	                     success : function(json) {
	                        $("#table").bootstrapTable('load', json);//主要是要这种写法
	                    }
	                });

	    });
  	
  	  
  	  
  })
  
   //修改
   function updateFun(surgid){
	   	 
		    $.ajax({  
		       type : 'get',  
		       url : "${pageContext.request.contextPath}/position?method=query",  
		       dataType : 'json',      
		       success : function(datas) {//返回list数据并循环获取  
		       	
		           var select = $("#queryupdate");		          
           			select.empty();
		           for (var i = 0; i < datas.length; i++) { 
		           	select.append("<option value='" +datas[i].profid+ "'>"  
		                       + datas[i].profname + "</option>");  
		           }  	         
		       } 
		       
		   	});
	 
	  $.ajax({
		    type:"post",//提交方式
		    url:"${pageContext.request.contextPath}/surgery?method=sendUpdate",
		    data:{surgid:surgid},//传主键值
		    dataType:"json",//返回的数据类型为json
		    success:function(data){
		    	
		    	  //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
		    		
		    		   $("#surgidUpdate").val(data.surgid);
		    		   $("#surgnameUpdate").val(data.surgname);
		    		   $("#surgprofidUpdate").val(data.surgprofid); 
		    		   $("#surgpushfeeUpdate").val(data.surgpushfee);
		    		   $("#surgfeeUpdate").val(data.surgfee);
		    	})
		    }
	  })
  }
  
function deleteFun(surgid){
	  $.ajax({
		  type:"post",
		  url:"${pageContext.request.contextPath}/surgery?method=delete",
		  data:{surgid:surgid},//传主键值
		  dataType:"json",//返回的数据类型为json
		  success:function(data){
			  location.reload(true);
		  },
		  error: function(xhr, type) {
		  }
	  })
  } 
  
//添加
function addFun(){
		    	
	
    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/position?method=query",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#queryadd");
	           select.empty();
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].profid+ "'>"  
	                       + datas[i].profname + "</option>");  
	           }  	         
	       } 
	       
	   	});
	  //弹出添加窗体
	  $('#myAddModal').modal().on('shown.bs.modal',function() {
		  
	  })
}
  
  
 </script>
 
 
<!-- 修改页面窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">

<input type="hidden" name="surgid" id="surgidUpdate">

<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 60%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">修改手术信息</h4>
            </div>
            <div class="modal-body">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术名字:</label>
					    <div class="col-sm-10">
					      <input type="text" name="surgname" class="form-control" id="surgnameUpdate" placeholder="手术名字；">
					    </div>
				 </div>
<!-- 				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术名字:</label>
					    <div class="col-sm-10">
					      <input type="text" name="surgprofid" class="form-control" id="surgprofidUpdate" placeholder="手术名字；">
					    </div>
				 </div> -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术金额提成:</label>
					    <div class="col-sm-10">
					      <input type="text" name="surgpushfee" class="form-control" id="surgpushfeeUpdate" placeholder="手术提成；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术费用:</label>
					    <div class="col-sm-10">
					      <input type="text" name="surgfee" class="form-control" id="surgfeeUpdate" placeholder="手术费用；">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">职称名称:</label>
					    <div class="col-sm-10">
					         <select id="queryupdate" name="surgprofid" style="width:100px" class="form-control input-sm">
		      
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
 
  
<!-- 添加窗体 -->
<!-- 模态框（Modal） -->
<form id="myAddForm" method="post">

<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myAddModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 60%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myAddModalLabel">添加手术</h4>
            </div>
            <div class="modal-body">
                 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术名字:</label>
					    <div class="col-sm-10">
					      <input type="text" name="surgname" class="form-control" id="surgnameAdd" placeholder="手术名字:">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">职称编号:</label>
					    <div class="col-sm-10">
					     <select id="queryadd" name="surgprofid" style="width:100px" class="form-control input-sm">
		      
		      				</select>
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术金额提成:</label>
					    <div class="col-sm-10">
					      <input type="text" name="surgpushfee" class="form-control" id="surgpushfeeAdd" placeholder="手术金额提成:">
					    </div>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">手术费用:</label>
					    <div class="col-sm-10">
					      <input type="text" name="surgfee" class="form-control" id="surgfeeAdd" placeholder="手术费用:">
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
	    	 url:"${pageContext.request.contextPath}/surgery?method=update",
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
			url:"${pageContext.request.contextPath}/surgery?method=add",
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