<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="img/css/ys.css">
<link rel="stylesheet" type="text/css" href="img/css/bootstrap.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>
<body>
	<a style=float:left; class="nav-link" id="my1" href="index.jsp">首页 </a>
	<h1 style=text-align:center;>用户管理</h1>
	<div class="content">
		<div class="between">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">手机号</th>
						<th scope="col">邮箱</th>
						<th scope="col">密码</th>
						<th scope="col">昵称</th>		
						<th>
							<a class="nav-link" id="reg" href="javascript:void(0)" data-toggle="modal" data-target="#regfrom">添加</a>
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="user" items="${alluser}">
						<tr>
							<td>${user.userid}</td>
							<td>${user.email}</td>
							<td>${user.password}</td>
							<td>${user.username}</td>
							<td>
								<form style=float:right action="/MUSIC/UserServlet" onsubmit="return deleteUser('${user.userid}')">
									<input type="hidden" name="method" value="deleteUser">							
									<input type="hidden" name="userid" value="${user.userid}">
									<input type="submit" value="删除">
								</form>
							</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
	
	<div class="modal fade" id="regfrom" tabindex="-1" role="dialog" aria-labelledby="regfromTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
	   		<div class="modal-content">
	     		<div class="modal-header">
		        	<h5 class="modal-title" id="regfromTitle">添加</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
		      	</div>
		     	<div class="modal-body">
					<form action="/MUSIC/UserServlet">
						<input type="hidden" name="method" value="manageRegister">
						<input class="form-control form-control-lg" type="text" name="userid" required="required" placeholder="手机号">			
						<input class="form-control form-control-lg" type="text" name="password" required="required"  placeholder="密码">
						<input class="form-control form-control-lg" type="text" name="email" required="required" placeholder="邮箱">
						<input class="form-control form-control-lg" type="text" name="username" required="required" placeholder="昵称">
						<h3>${regMsg}</h3>
						<input class="btn btn-large btn-primary btn-block" type="submit" value="添加">
						<input class="btn btn-large btn-secondary btn-block" data-dismiss="modal" type="button" value="关闭">
					</form>
		     	</div>
			</div>
		</div>
	</div>
	
	<script>
		function deleteUser(user) {
			if (confirm("是否要删除" + user + "用户") == true)
				return true;
			else
				return false;
		}
	</script>
	
	<script src="js/jquery-3.4.1.js" type="text/javascript"></script>
	<script src="js/bootstrap.bundle.js" type="text/javascript"></script>
</body>
</html>