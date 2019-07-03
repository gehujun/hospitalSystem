<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手术项目管理</title>

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

<div id="toolbar">
   <div id="showorhide" class="a">
          <form class="form-inline" id="mySearchForm">
			  <div class="form-group">
			    <label for="exampleInputName2">手术编号:</label>
			    <input type="text" name="surId" class="form-control" id="surIdSearch" placeholder="手术编号">
			  </div>
			  
			  <div class="form-group">
			    <label for="exampleInputEmail2">手术室:</label>
			    <select name="surRoom" id="surRoomSearch">
			        <option value="0">请选择</option>
			    </select>
			  </div>
			  
			  <div class="form-group">
			    <label for="exampleInputEmail2">医师:</label>
			    <select name="surDoc" id="surDocSearch">
			        <option value="0">请选择</option>
			    </select>
			  </div>
			  
			  <button id="btn_query" type="button" class="btn btn-success">查询</button>
			  <button id="btn_add" type="button" class="btn btn-success" >添加职称</button>
			  
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
		url:"${pageContext.request.contextPath}/surPro?method=query",//访问地址
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
		    field: 'surid',
		    title: '岗位编号',
		    sortable:true
		  }, {
		    field: 'maid',
		    title: '医嘱编号'
		  }, {
		    field: 'name',
		    title: '手术名称'
		  },{
		    field: 'name',
		    title: '手术名称'
		  },{
		    field: 'surroomid',
		    title: '基本薪资'
		  },{
			  title:"操作",
			  formatter:function(value,row,index){
				  var updateValue =' <button  onclick="updateFun('+row.profid+')" type="button" class="btn btn-success">修改</button>';
				  var authorityValue = ' <button  onclick="authorityFun('+row.profid+')" type="button" class="btn btn-success">分配权限</button>';
				  var deleteValue =' <button  onclick="deleteFun('+row.profid+')" type="button" class="btn btn-success">删除</button>';
				  
				  return updateValue+authorityValue+deleteValue;
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
        url : "${pageContext.request.contextPath}/department?method=query",  
        dataType : 'json',      
        success : function(datas) {//返回list数据并循环获取  
        	
            var select = $("#deptIdSearch");
            for (var i = 0; i < datas.length; i++) { 
            	select.append("<option value='" +datas[i].deptid+ "'>"  
                        + datas[i].deptname + "</option>");  
            }  
          
        } 
        
    }); 
	
})

</script>



</body>
</html>