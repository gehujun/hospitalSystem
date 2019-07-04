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
			    <label for="exampleInputName2">租金:</label>
			    <input type="text" name="bedrent" class="form-control" id="bedrentSearch" placeholder="租金">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">病区:</label>
			    <input type="text" name="wardname" class="form-control" id="wardnameSearch" placeholder="病区名">
			  </div>
			  <button id="btn_query" type="button" class="btn btn-success">查询</button>
			  <button id="btn_add" type="button" class="btn btn-success">添加</button>
			  
		 </form>
   </div>
   <button id="open" class="btn btn-success">展开或折叠</button>
</div>
<table id="table"></table>

<!-- 工具栏 -->
<div id="toolbar"></div>

<script type="text/javascript">

$(function(){
	
	 //下拉数据加载  
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/bed?method=wardnameSelect",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#wardnameSelect");
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].wardname+ "'>"  
	                       + datas[i].wardname + "</option>");  
	           }  
	         
	       } 
	       
	   	});
	})
	
	$(function(){
	
	 //下拉数据加载  
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/bed?method=empidSelect",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#empiddocSelect");
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].empid+ "'>"  
	                       + datas[i].empname + "</option>");  
	           }  
	         
	       } 
	       
	   	});
	})

	
	 //下拉数据加载  
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/bed?method=empidSelect",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#empidnurSelect");
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].empid+ "'>"  
	                       + datas[i].empname + "</option>");  
	           }  
	         
	       } 
	       
	   	});
	   	

$(function(){
	
	 //下拉数据加载  
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/bed?method=wardnameSelect",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#wardnameSelect1");
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].wardname+ "'>"  
	                       + datas[i].wardname + "</option>");  
	           }  
	         
	       } 
	       
	   	});
	})
	
	$(function(){
	
	 //下拉数据加载  
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/bed?method=empidSelect",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#empiddocSelect1");
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].empid+ "'>"  
	                       + datas[i].empname + "</option>");  
	           }  
	         
	       } 
	       
	   	});
	})
	 //下拉数据加载  
	    $.ajax({  
	       type : 'get',  
	       url : "${pageContext.request.contextPath}/bed?method=empidSelect",  
	       dataType : 'json',      
	       success : function(datas) {//返回list数据并循环获取  
	       	
	           var select = $("#empidnurSelect1");
	           for (var i = 0; i < datas.length; i++) { 
	           	select.append("<option value='" +datas[i].empid+ "'>"  
	                       + datas[i].empname + "</option>");  
	           }  
	         
	       } 
	       
	   	});


	    
    $(function(){
    	 
    	$("#table").bootstrapTable({
    		url:"${pageContext.request.contextPath}/bed?method=query",//访问地址
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
    		    field: 'bedid',
    		    title: '床位号',
    		    sortable:true
    		  }, {
    		    field: 'wardname',
    		    title: '病区名'
    		  }, {
    		    field: 'isemptybed',
    		    title: '是否空床'
    		  }, {
    			  field: 'bedrent',
      		      title: '租金'
    		  },{ 	
    			  field: 'empdoc.empname',
    	    	  title: '经管医生'
    		  },{
    		    field: 'empnur.empname',
    		    title: '护理人员'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue =' <button  onclick="updateFun('+row.bedid+')" type="button" class="btn btn-success">修改</button>';
    				  var deleteValue =' <button  onclick="deleteFun('+row.bedid+')" type="button" class="btn btn-success">删除</button>';
    				  
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
	               
	                var wardname = $("#wardnameSearch").val();
	                var bedrent = $("#bedrentSearch").val();
	               
	                $.ajax({
	                     type: "post",//请求的方式
	                     url: "${pageContext.request.contextPath}/bed?method=query",
	                     data: {wardname:wardname,bedrent:bedrent}, //查询条件
	                     dataType:"json",//返回的数据类型为json
	                     success : function(json) {
	                        $("#table").bootstrapTable('load', json);//主要是要这种写法
	                    }
	                });

	    });
  	
  	  
  	  
  })
  


  	$(function(){
		$("#btn_add").click(function(){
			
			$('#myAddModal').modal().on('shown.bs.modal');
			//通过调用科室方法获取对象
			$.ajax({
				type:'get',
				url:"${pageContext.request.contextPath}/bed?method=query",
				dataType : 'json',
	            success : function(datas) {//返回list数据并循环获取  
	            	
	            	
			}
		})
	})
  	})

  //修改
   function updateFun(bedid){
	 
	  $.ajax({
		    type:"post",//提交方式
		    url:"${pageContext.request.contextPath}/bed?method=sendUpdate",
		    data:{bedid:bedid},//传主键值
		    dataType:"json",//返回的数据类型为json
		    success:function(data){
		    	
		    	  //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {		    		
		    		   $("#bedrentupdate").val(data.bedrent);
		    		   $("#isemptybedupdate").val(data.isemptybed);
		    		   $("#bedidupdate").val(data.bedid);
		    		   
		    		   var opt1 = document.getElementById("wardnameSelect");
		    		   for(i=0;i<opt1.length;i++){
		    			   if(opt1[i].value==data.wardname)
		    				   opt1[i].selected = true;
		    		   }
		    		   
		    		   var opt2 = document.getElementById("empiddocSelect");
		    		   for(i=0;i<opt2.length;i++){
		    			   if(opt2[i].value==data.empdoc.empid)
		    				   opt2[i].selected = true;
		    		   }
		    		   
		    		   var opt = document.getElementById("empidnurSelect");
		    		   for(i=0;i<opt.length;i++){
		    			   if(opt[i].value==data.empnur.empid)
		    				   opt[i].selected = true;
		    		   }
		    		   

		    	})
		    }
	  })
  }
  
  function deleteFun(bedid) {
	  $.ajax({
		    type:"post",//提交方式
		    url:"${pageContext.request.contextPath}/bed?method=delete",
		    data:{bedid:bedid},//传主键值
		    dataType:"json",//返回的数据类型为json
		    success:function(data){
		    	location.reload(true);	 //刷新界面，刷新的是52行以下的界面   ${pageContext.request.contextPath}/department?method=query		
		    			
		    },
		    error:function(xhr,type){
		    	
		    }
		    	})
	  }
   
</script>

<form id="myUpdateForm" method="post">

<input type="hidden" name="bedid" id="bedidupdate">
<table class="table table-striped table-bordered"> </table>
 <div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  
<div class="table-responsive">
  <div class="modal-dialog modal-sm1" style="width:550px;">
  	 <div class="modal-content">
  	 <div class="modal-header">
  	 <!-- 交互信息详情 -->
     	<label data-locale="i18n_interactiveInformationDetails" style="cursor: pointer;"></label>
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">修改床位信息</h4>
     </div>
            <div class="modal-body">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病区名:</label>
					    <div class="col-sm-10">
					    
					       <select id="wardnameSelect" name="wardname" style="width:150px" class="form-control input-sm">
		      			
		     			</select>
			      </div>
			      <table class="table table-striped table-bordered">
       
    			 </table>
			</div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">是否空床:</label>
					    <div class="col-sm-10">
					    
					      <select id="isbedemptyUpdate" name="isbedempty" style="width:150px" class="form-control input-sm">
							         <option value="是">是</option>
									  <option value="否">否</option>
			            </select>
					    </div>
					    <table class="table table-striped table-bordered">
       
    			 		</table>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">租金:</label>
					    <div class="col-sm-10">
					      <input type="text" name="bedrent" style="width:150px"  class="form-control" id="bedrentupdate" placeholder="租金">
					    </div>
					    <table class="table table-striped table-bordered">
       
    			 	</table>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">经管医生:</label>
					    <div class="col-sm-10">
					 <!--   <input type="hidden" name="empiddoc" id="empiddocAdd">  --> 
					          <select id="empiddocSelect" name="empiddoc" style="width:150px" class="form-control input-sm">
		      
		     			 </select>
					    </div>
					    <table class="table table-striped table-bordered">
       
    			 </table>
				 </div>
				  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">护理人员:</label>
					    <div class="col-sm-10">
					    	<!--  <input type="hidden" name="empidnur" id="empidnurAdd">-->
					          <select id="empidnurSelect" name="empidnur" style="width:150px" class="form-control input-sm">
		  			          </select>
					    </div>
				 </div>
				 <table class="table table-striped table-bordered">
       
    			 </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnUpadte" class="btn btn-primary">更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</div>
</form>


<!-- 添加模态框 -->
<form id="myAddForm" method="post">

<input type="hidden" name="bedid" id="bedidadd">
<table class="table table-striped table-bordered"> </table>
 <div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  
<div class="table-responsive">
  <div class="modal-dialog modal-sm1" style="width:550px;">
  	 <div class="modal-content">
  	 <div class="modal-header">
  	 <!-- 交互信息详情 -->
     	<label data-locale="i18n_interactiveInformationDetails" style="cursor: pointer;"></label>
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">添加床位信息</h4>
     </div>
            <div class="modal-body">
                   <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">病区名:</label>
					    <div class="col-sm-10">
					    
					       <select id="wardnameSelect1" name="wardname" style="width:150px" class="form-control input-sm">
		      				<option value = '0'>请选择</option>
		     			</select>
			      </div>
			      <table class="table table-striped table-bordered">
       
    			 </table>
			</div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">是否空床:</label>
					    <div class="col-sm-10">
					    
					      <select id="isbedemptyAdd" name="isbedempty" style="width:150px" class="form-control input-sm">
							         <option value="1">是</option>
									  <option value="2">否</option>
			            </select>
					    </div>
					    <table class="table table-striped table-bordered">
       
    			 		</table>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">租金:</label>
					    <div class="col-sm-10">
					      <input type="text" name="bedrent" style="width:150px"  class="form-control" id="bedrentadd" placeholder="租金">
					    </div>
					    <table class="table table-striped table-bordered">
       
    			 	</table>
				 </div>
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">经管医生:</label>
					    <div class="col-sm-10">
					 <!--   <input type="hidden" name="empiddoc" id="empiddocAdd">  --> 
					          <select id="empiddocSelect1" name="empiddoc" style="width:150px" class="form-control input-sm">
		      					 <option value = '0'>请选择</option>
		     			 </select>
					    </div>
					    <table class="table table-striped table-bordered">
       
    			 </table>
				 </div>
				  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">护理人员:</label>
					    <div class="col-sm-10">
					    	<!--  <input type="hidden" name="empidnur" id="empidnurAdd">-->
					          <select id="empidnurSelect1" name="empidnur" style="width:150px" class="form-control input-sm">
		  			          <option value = '0'>请选择</option>
		  			          </select>
					    </div>
				 </div>
				 <table class="table table-striped table-bordered">
       
    			 </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="btnAdd" class="btn btn-primary">添加</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</div>
</form>


<script type="text/javascript">
  $("#btnUpadte").click(function(){
	  
	    //获取表单中的数据
	     var bed = $("#myUpdateForm").serializeArray();
	    
	    $.ajax({
	    	 type:"post",//提交方式
	    	 url:"${pageContext.request.contextPath}/bed?method=update",
	    	 data:bed,//数据
	    	 dataType:"json",//返回的数据类型为json
	    	 success:function(json){
	    		 
	    		 if(json ==1){
	    			 
	    			 $("#myUpdateModal").modal('hide');  //手动关闭
	    			 $("#table").bootstrapTable("refresh");//刷新表格中的数据
	    			 
	    			 
	    		 }
	    	 }
	    })
  })
  
  
    $("#btnAdd").click(function(){
	  
    	alert("成功点击");
	    //获取表单中的数据
	     var bed = $("#myAddForm").serializeArray();
	    
	    $.ajax({
	    	 type:"post",//提交方式
	    	 url:"${pageContext.request.contextPath}/bed?method=add",
	    	 data:bed,//数据
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