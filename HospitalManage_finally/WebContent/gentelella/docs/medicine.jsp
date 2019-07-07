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
			    <label for="exampleInputName2">生产厂商:</label>
			    <input type="text" class="form-control" id="medifirm" placeholder="生产厂商">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputName2">药库地址:</label>
			    <input type="text" class="form-control" id="meliaddr" placeholder="药库地址">
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
    		  url: '${pageContext.request.contextPath}/medicine?method=query',
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
    		    field: 'mediid',
    		    title: '药品编号'
    		  }, {
    		    field: 'ml.meliaddr',
    		    title: '药库地址'
    		  }, {
    		    field: 'mediname',
    		    title: '品名'
    		  }, {
    		    field: 'medispecifi',
    		    title: '规格'
    		  }, {
    		    field: 'mediunit',
    		    title: '单位'
    		  }, {
    		    field: 'mediprice',
    		    title: '单价'
    		  }, {
    		    field: 'mediexpirationdate',
    		    title: '保质期'
    		  },{
    		    field: 'medifirm',
    		    title: '生产厂商'
    		  },{
    			  title:"操作",
    			  formatter:function(value,row,index){
    				  var updateValue='<button onclick="updateFun('+row.mediid+') " type="button" class="btn btn-success">修改</button>';
    				  var deleteValue='<button onclick="deleteFun('+row.mediid+') " type="button" class="btn btn-success">删除</button>';
    				  
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
				url:"${pageContext.request.contextPath}/medicine?method=queryByJoin",
				dataType : 'json',
	            success : function(datas) {				//返回list数据并循环获取  

	                	var select = $("#mediaddrAdd");
	            		select.empty();
	                for (var i = 0; i < datas.length; i++) { 
	                	select.append("<option value='" +datas[i].meliid+ "'>"
	                            + datas[i].meliaddr+ "</option>");  
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
        	var meliaddr=$("#meliaddr").val();
        	var medifirm=$("#medifirm").val();

        	$.ajax({
        		type:"post",
        		url:"${pageContext.request.contextPath }/medicine?method=queryByCondition",
        		data:{meliaddr:meliaddr,medifirm:medifirm},
        		dataType:"json",
        		success:function(json){
        				$("#table").bootstrapTable("load",json);
        			}
        	})
        	
        	
        });
    })
    
    

//修改
	function updateFun(mediid){
		

		$.ajax({
			type:'get',
			url:"${pageContext.request.contextPath}/medicine?method=queryByJoin",
			dataType : "json",
	        success : function(datas) {//返回list数据并循环获取  
	        	document.getElementById("mediaddrUpdate").options.length = 0;//清空选项
	            var select = $("#mediaddrUpdate");
	            for (var i = 0; i < datas.length; i++) { 
	            	select.append("<option value='" +datas[i].meliid+ "'>"  
	                        + datas[i].meliaddr + "</option>");  
	            }  
	          	
	        }
		})
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/medicine?method=sendUpdate",
			data:{mediid:mediid},
			dataType:"json",
			success:function(data){
				 //弹出修改窗体
		    	 $('#myUpdateModal').modal().on('shown.bs.modal',function() {
// 		    			alert(data.mediid);
		    		   $("#mediidUpdate").val(data.mediid);
// 		    		   $("#medlidUpdate").val(data.meliid);
		    		   $("#medinameUpdate").val(data.mediname);
		    		   $("#medispecifiUpdate").val(data.medispecifi);
		    		   $("#mediunitUpdate").val(data.mediunit);
		    		   $("#medipriceUpdate").val(data.mediprice);
		    		   $("#mediexpirationdateUpdate").val(data.mediexpirationdate);
		    		   $("#medifirmUpdate").val(data.medifirm);

		    		   //初始化为原来的值
		    		   var opt = document.getElementById("mediaddrUpdate");
		    		   for(i=0;i<opt.length;i++){
		    			   if(opt[i].value==data.meliid)
		    				   opt[i].selected = true;
		    		   } 
		    		   
		    	})
			}	
		})
	}
	
//删除
	function deleteFun(mediid){
			
		$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath }/medicine?method=delete",
				data:{mediid:mediid},
				success:function(data){
					$.ajax({
		        		type:"post",
		        		url:"${pageContext.request.contextPath }/medicine?method=query",
		        		data:{ "mediid":null},
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

<!-- 修改 -->
<!-- 修改按钮窗体 -->
<!-- 模态框（Modal） -->
<form id="myUpdateForm" method="post">



<div class="modal fade" id="myUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times;</button>
                <h4 class="modal-title" id="myModalLabel">修改药品信息</h4>
            </div>
            
			
            <div class="modal-body">
            	
            	<input type="hidden" name="mediid" id="mediidUpdate">
				 	
				<!--	药库号 	 -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">药库号:</label>
					    <div class="col-sm-10">
					      <input type="hidden" name="meliid" id="meliidUpdate">
					    <select class="form-control" name="mediaddr" id="mediaddrUpdate">
					      
					      
					      </select>
					    </div>
				 </div>
				 
				 <!--	品名	  -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">品名:</label>
					    <div class="col-sm-10">
					      <input type="text" name="mediname" class="form-control" id="medinameUpdate" placeholder="品名；">
					    </div>
				 </div>
				 
				 <!--	规格	  -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">规格:</label>
					    <div class="col-sm-10">
					      <input type="text" name="medispecifi" class="form-control" id="medispecifiUpdate" placeholder="规格；">
					    </div>
				 </div>
				 
				 <!--	单位	  -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">单位:</label>
					    <div class="col-sm-10">
					      <input type="text" name="mediunit" class="form-control" id="mediunitUpdate" placeholder="单位；">
					    </div>
				 </div>
				 
				 <!--	单价	  -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">单价:</label>
					    <div class="col-sm-10">
					      <input type="text" name="mediprice" class="form-control" id="medipriceUpdate" placeholder="单价；">
					    </div>
				 </div>
				 
				 <!--	保质期	  -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">保质期:</label>
					    <div class="col-sm-10">
					      <input type="text" name="mediexpirationdate" class="form-control" id="mediexpirationdateUpdate" placeholder="保质期；">
					    </div>
				 </div>
				 
				 <!--	生产厂商  -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">生产厂商:</label>
					    <div class="col-sm-10">
					      <input type="text" name="medifirm" class="form-control" id="medifirmUpdate" placeholder="生产厂商；">
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
	
	
<!-- 添加改完  -->
<!-- 添加按钮窗体 -->
<!-- 模态框（Modal） -->
<form id="myAddForm" method="post">

<!-- <input type="hidden" name="mediid" id="mediidAdd"> -->
<div class="modal fade" id="myAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"> &times; </button>
                <h4 class="modal-title" id="myModalLabel">增加药品</h4>
            </div>
            
            <div class="modal-body">
            

			
			
				 <!--     药库号      -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">药库号:</label>
					    <div class="col-sm-10">
					    <input type="hidden" name="meliid" id="meliidAdd">
					    <select class="form-control" name="mediaddr" id="mediaddrAdd">
					      	<option value="0">请选择</option>
					      </select>
					    </div>
				 </div>
				 
				 <!--     品名      -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">品名:</label>
					    <div class="col-sm-10">
					      <input type="text" name="mediname" class="form-control" id="medinameAdd" placeholder="药品名称；">
					    </div>
				 </div>
				 
				 <!--     规格      -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">规格:</label>
					    <div class="col-sm-10">
					      <input type="text" name="medispecifi" class="form-control" id="medispecifiAdd" placeholder="规格；">
					    </div>
				 </div>
				 
				 <!--     单位      -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">单位:</label>
					    <div class="col-sm-10">
					      <input type="text" name="mediunit" class="form-control" id="mediunitAdd" placeholder="单位；">
					    </div>
				 </div>
				 
				 <!--     单价      -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">单价:</label>
					    <div class="col-sm-10">
					      <input type="text" name="mediprice" class="form-control" id="medipriceAdd" placeholder="单价；">
					    </div>
				 </div>
				 
				 <!--     保质期      -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">保质期:</label>
					    <div class="col-sm-10">
					      <input type="text" name="mediexpirationdate" class="form-control" id="mediexpirationdateAdd" placeholder="保质期；">
					    </div>
				 </div>
				 
				 <!--     生产厂商      -->
				 <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">生产厂商:</label>
					    <div class="col-sm-10">
					      <input type="text" name="medifirm" class="form-control" id="medifirmAdd" placeholder="生产厂商；">
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
     var mediid = document.getElementById("mediidUpdate").value;
     $("#mediidUpdate").val(mediid);
     var meliid = document.getElementById("mediaddrUpdate").value;
     $("#meliidUpdate").val(meliid);
     var med = $("#myUpdateForm").serializeArray();
     
     $.ajax({
     	 type:"post",//提交方式
     	 url:"${pageContext.request.contextPath}/medicine?method=update",
     	 data:med,//数据
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
	var med = $("#myAddForm").serializeArray();
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/medicine?method=add",
		data:med,
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