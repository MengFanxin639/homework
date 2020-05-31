<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<!-- uedite -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/utf8-jsp/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${pageContext.request.contextPath}/utf8-jsp/lang/zh-cn/zh-cn.js"></script>

<title>课程管理-增加</title>

<script type="text/javascript">
	function addCourse() {
		var form = document.forms[0];
		form.action = "${pageContext.request.contextPath }/question";
		form.method = "post";
		form.submit();
	}
</script>

</head>
<body>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">

				<form class="form-horizontal" role="form" action=""
					name="courseForm">
					<input type="hidden" name="method" value="add"> <input
						type="hidden" name="q" value="1">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">题目简述:</label>
						<div class="col-sm-2">
							<input type="text" name="simpleinfo" class="form-control"
								value="${question.object.simpleinfo }" id="inputEmail3" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">正确答案:</label>
						<div class="col-sm-2">
							<input type="text" name="answer" class="form-control"
								value="${question.object.answer }" id="inputEmail3" />
						</div>
						多个答案请用","隔开
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">题目描述:</label>
						<div class="col-sm-2"></div>
					</div>
					<div>
						<form id="person" method="post" action=""
							enctype="multipart/form-data">
							<div>
								<input id="userinfo" name="description" type="hidden" />
								<script id="myEditor" type="text/plain"
									style="width: 100%; height: 256px;">${question.object.description }</script>
							</div>
						</form>
						<div>
							<button id="kh_button" onclick="save()" class="savebut">保存修改</button>
						</div>
					</div>
					<script type="text/javascript">
						/*实例化编辑器 */
						var um = UE.getEditor('myEditor');
					</script>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="button" class="btn btn-default"
								onclick="addCourse()">添加</button>
							&nbsp;&nbsp;&nbsp;
							<button type="reset" class="btn btn-default">重置</button>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>

	<!-- JavaScript 放置在文档最后面可以使页面加载速度更快 -->
	<!-- 可选: 包含 jQuery 库 -->
	<script
		src="${pageContext.request.contextPath }/BootStrap/js/jquery-3.1.1.js"></script>
	<!-- 可选: 合并了 Bootstrap JavaScript 插件 -->
	<script
		src="${pageContext.request.contextPath }/BootStrap/js/bootstrap.min.js"></script>
</body>
</html>