<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- 包含头部信息用于适应不同设备 -->
<meta name="viewport"
	content="width=device-width, initial-scale=1
    	user-scalable=no">
<!-- 包含 bootstrap 样式表 -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/BootStrap/css/bootstrap.min.css">
<title>课程管理</title>

<script type="text/javascript">
	//删除课程
	function del(id, questionid) {
		$.get("${pageContext.request.contextPath }/homework?method=delete&id="
				+ id + "&questionid=" + questionid, function(data) {
			if ("success" == data) {
				alert("删除成功!");
				window.location.reload();
			} else {
				alert("删除失败!");
			}
		});
	}

	//若课程信息修改有错-->则提示课程信息修改出错
	window.onload = error;
	function error() {
		var error = "${error}"; //如果不等于空，说明返回来时出错
		if (error.value != null) {
			alert(error.value);
		}
	}

	//面包屑导航
	function setbreadcrumb() {
		$("ol.breadcrumb", parent.document).html(
				"<li><a href='javascript:void(0)''>首页</a></li>"
						+ "<li class='active'>修改课程<li>");
	}
</script>
</head>
<body>

	<%
		int a = 0;
	%>

	<div class="text-center">
		<strong style="font-size: 20px;">作业总览</strong>
	</div>
	<table class="table table-hover table-bordered table-striped">
		<thead>
			<tr>

				<th>序号</th>
				<th>作业名</th>
				<th>班级</th>
				<th>类型</th>
				<th>起止时间</th>
				<th>修改时间</th>
				<th>作业状态</th>
				<th>评阅</th>
				<th style="width: 45px;">删除</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${!empty list}">
				<c:forEach items="${list}" var="c" varStatus="status">
					<c:if test="${status.count%2==0 } ">
						<tr class="success" style="color: #98FB98; font-size: 20px;">
					</c:if>
					<c:if test="${status.count%2!=0 }">
						<tr>
					</c:if>
					<td><%=++a%></td>
					<td>${ c.name }</td>
					<td>${ c.creater }</td>
					<td>${ c.type }</td>
					<td>${ c.starttime }</td>
					<td>${ c.endtime }</td>
					<td>${ c.state == 3 ? '已删除':'正常' }</td>
					<td><a
						href="${pageContext.request.contextPath }/homework?method=query&id=${c.homeworkid }"
						onclick="setbreadcrumb()">修改</a></td>
					<td><a type="button" class="close"
						href="javascript:del('${c.homeworkid }','${c.question.id }')">
							<span class="glyphicon glyphicon-trash"></span> <span
							class="sr-only">关闭</span>
					</a></td>
					</tr>

				</c:forEach>
			</c:if>
		</tbody>
	</table>

	<!-- JavaScript 放置在文档最后面可以使页面加载速度更快 -->
	<!-- 可选: 包含 jQuery 库 -->
	<script
		src="${pageContext.request.contextPath }/BootStrap/js/jquery-3.1.1.js"></script>
	<!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
	<script
		src="${pageContext.request.contextPath }/BootStrap/js/bootstrap.min.js"></script>
</body>
</html>