<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>音乐网站</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="img/css/ys.css">
<link rel="stylesheet" type="text/css" href="img/css/bootstrap.css">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
</head>

<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand offset-lg-3" href="index.jsp">Music</a>

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item "><a class="nav-link disabled" id="my1"
					href="my.jsp">我的音乐 </a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0" action="/MUSIC/MusicServlet">
				<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="search">
				<input type="hidden" name="pageIndex" value="1"> 
				<input type="hidden" name="method" value="search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
			</form>
			<ul class="navbar-nav mr-auto">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle disabled" id="my2" href="#"
					id="navbarDropdown" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">个人中心</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="javascript:void(0)" data-toggle="modal" data-target="#infofrom">个人设置</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="/MUSIC/UserServlet?method=cancel">注销</a>
					</div>
				</li>
				<li class="nav-item">
					<a class="nav-link" id="login" href="javascript:void(0)" data-toggle="modal" data-target="#loginfrom">登陆</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" id="reg" href="javascript:void(0)" data-toggle="modal" data-target="#regfrom">注册</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="content">
		<div class="between">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">歌曲</th>
						<th scope="col">歌手</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="music" items="${search.beanList}">
						<tr>
							<td>
							<a href="javascript:void(0)" onclick="play('img/${music.musicAdd}')">
								${music.music} </a>
								<a href="javascript:void(0)" class="btn btn-sm btn-info" 
									data-toggle="modal" data-target="#addMusic" onclick="musicChoose('${music.musicid}')">添加</a>
							</td>
							<td>${music.authorName}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>

			<nav aria-label="Page navigation" style="width: 300px; margin: 0 auto">
				<ul class="pagination">
					<li class="page-item">
					<a class="page-link" href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
					</li>
					
					<c:forEach var="page" begin="1" end="${search.totalPage}" varStatus="statu">
					<li class="page-item">
						<a class="page-link" href="/MUSIC/MusicServlet?method=search&pageIndex=${statu.count}&search=${nowSearch}">
							${statu.count}
						</a>
					</li>
					</c:forEach>
					
					<li class="page-item">
					<a class="page-link" href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
					</li>
				</ul>
			</nav>
		</div>
	</div>



	<div class="bottom">
		<audio id="player" src="" controls="controls" autoplay="autoplay">
		</audio>
	</div>
	
	<div class="modal fade" id="loginfrom" tabindex="-1" role="dialog" aria-labelledby="loginfromTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
	   		<div class="modal-content">
	     		<div class="modal-header">
		        	<h5 class="modal-title" id="loginfromTitle">登陆</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
		      	</div>
		     	<div class="modal-body">
		       		<form action="/MUSIC/UserServlet">
						<input class="form-control form-control-lg" type="text" name="userid" 
							required="required" value="${user.userid}" placeholder="用户名">
						<input class="form-control form-control-lg" type="text" name="password" 
							required="required" value="${user.password}" placeholder="密码">
						<h3>${loginMsg}</h3>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" checked="checked" name="method" id="inlineRadio1" value="loginByUserid">
						  <label class="form-check-label" for="inlineRadio1">手机号登陆</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="method" id="inlineRadio2" value="loginByEmail">
						  <label class="form-check-label" for="inlineRadio2">邮箱登陆</label>
						</div>
						<input class="btn btn-large btn-primary btn-block" type="submit" value="登录">
						<input class="btn btn-large btn-secondary btn-block" data-dismiss="modal" type="button" value="关闭">
					</form>
		     	</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="regfrom" tabindex="-1" role="dialog" aria-labelledby="regfromTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
	   		<div class="modal-content">
	     		<div class="modal-header">
		        	<h5 class="modal-title" id="regfromTitle">注册</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
		      	</div>
		     	<div class="modal-body">
					<form action="/MUSIC/UserServlet">
						<input type="hidden" name="method" value="register">
						<input class="form-control form-control-lg" type="text" name="userid" required="required" value="${user.userid}" placeholder="手机号">			
						<input class="form-control form-control-lg" type="text" name="password" required="required" value="${user.password}" placeholder="密码">
						<input class="form-control form-control-lg" type="text" name="email" required="required" value="${user.password}" placeholder="邮箱">
						<input class="form-control form-control-lg" type="text" name="username" required="required" value="${user.password}" placeholder="昵称">
						<h3>${regMsg}</h3>
						<input class="btn btn-large btn-primary btn-block" type="submit" value="注册">
						<input class="btn btn-large btn-secondary btn-block" data-dismiss="modal" type="button" value="关闭">
					</form>
		     	</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="addMusic" tabindex="-1" role="dialog" aria-labelledby="addMusicTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
	   		<div class="modal-content">
	     		<div class="modal-header">
		        	<h5 class="modal-title" id="addMusicTitle">添加到</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
		      	</div>
		     	<div class="modal-body">
					<form action="/MUSIC/MusicServlet">
						<input type="hidden" name="method" value="addMusic">
						<input type="hidden" name="userid" value="${user.userid}">
						<input id="musicChoose" type="hidden" name="musicid">
						<select class="custom-select" name="musicListName">
							<c:forEach var="listname" items="${musicListName}">
								<option value="${listname}">${listname}</option>
							</c:forEach>
					 	</select>
						<input class="btn btn-large btn-primary btn-block" type="submit" value="添加">
					 	<input type="button" class="btn btn-large btn-secondary btn-block" data-dismiss="modal" value="取消">
					</form>
		     	</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="infofrom" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="infofromTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
	   		<div class="modal-content">
	     		<div class="modal-header">
		        	<h5 class="modal-title" id="infofromTitle">个人设置</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
		      	</div>
		     	<div class="modal-body">
					<form action="#">
						<div class="input-group mb-3">
  							<div class="input-group-prepend">
  								<span class="input-group-text">昵称</span>
							</div>
							<input class="form-control form-control-lg" type="text" disabled="disabled" value="${user.username}">
							<div class="input-group-append">
  								<button class="btn btn-outline-primary" type="button" 
  										data-toggle="modal" data-target="#changeInfofrom"
  										onclick="alterInfoFrom('alterUserName','username','新昵称')">修改</button>
							</div>	
						</div>
						<div class="input-group mb-3">
  							<div class="input-group-prepend">
  								<span class="input-group-text">密码</span>
							</div>
							<input class="form-control form-control-lg" type="text" disabled="disabled" value="******">
							<div class="input-group-append">
  								<button class="btn btn-outline-primary" type="button" 
  										data-toggle="modal" data-target="#changeInfofrom"
  										onclick="alterInfoFrom('alterPassword','newpassword','新密码')">修改</button>
							</div>		
						</div>
						<div class="input-group mb-3">
  							<div class="input-group-prepend">
  								<span class="input-group-text">手机号</span>
							</div>
							<input class="form-control form-control-lg" type="text" disabled="disabled" value="${user.userid}">	
							<div class="input-group-append">
  								<button class="btn btn-outline-primary" type="button" 
  										data-toggle="modal" data-target="#changeInfofrom"
  										onclick="alterInfoFrom('alterUserid','newuserid','新手机号')">修改</button>
							</div>	
						</div>
						<div class="input-group mb-3">
  							<div class="input-group-prepend">
  								<span class="input-group-text">邮箱</span>
							</div>
							<input class="form-control form-control-lg" type="text" disabled="disabled" value="${user.email}">
							<div class="input-group-append">
  								<button class="btn btn-outline-primary" type="button" 
  										data-toggle="modal" data-target="#changeInfofrom"
  										onclick="alterInfoFrom('alterEmail','email','新邮箱')">修改</button>
							</div>		
						</div>
						<h3>${alterMsg}</h3>
						<input class="btn btn-large btn-secondary btn-block" data-dismiss="modal" type="button" value="关闭">
					</form>
		     	</div>
			</div>
		</div>
	</div>	

	<div class="modal fade" id="changeInfofrom" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="changeInfofromTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
	   		<div class="modal-content">
	     		<div class="modal-header">
		        	<h5 class="modal-title" id="changeInfofromTitle">更改个人信息</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			        <span aria-hidden="true">&times;</span>
			        </button>
		      	</div>
		     	<div class="modal-body row align-items-center" style="height:250px">
					<form class="col" id="submitfrom" action="/MUSIC/UserServlet">
						<input type="hidden" id="alterMethod" name="method" value="alterUserName">
						<input type="hidden" name="userid" value="${ user.userid }">
						<input class="form-control form-control-lg" type="text" id="alterName" name="username" required="required">
						<input class="form-control form-control-lg" type="text" name="password" required="required" placeholder="密码">
					</form>	
		     	</div>
		     	<div class="modal-footer">
					<input class="btn btn-large btn-primary btn-block" onclick="submit()" value="确认">
					<input class="btn btn-large btn-secondary btn-block" data-dismiss="modal" type="button" value="取消">
     			</div>
			</div>
		</div>
	</div>

	<script>
		window.onload = function(){
			if(${!empty user.userid}) {
				document.getElementById("login").style.display="none";
				document.getElementById("reg").style.display="none";
				document.getElementById("my1").className="nav-link";
				document.getElementById("my2").className="nav-link dropdown-toggle";
				}
			if(${!empty regMsg}) {
				$('#regfrom').modal('show');
			}
			if(${!empty loginMsg}) {
					$('#loginfrom').modal('show');
				}
			if(${!empty alterMsg}) {
				$('#infofrom').modal('show');
			}
		}
		
		function play(url) {
			document.getElementById("player").src=url;
		}		
	    
	    function musicChoose(type) {
			document.getElementById("musicChoose").value=type;
	    }
	    
	    function submit() {
			document.getElementById("submitfrom").submit();
		}
	    
	    function alterInfoFrom(method,altername,placeholder) {
			document.getElementById("alterMethod").value=method;
			document.getElementById("alterName").name=altername;
			document.getElementById("alterName").placeholder=placeholder;				
		}
	</script>
	<script src="js/jquery-3.4.1.js" type="text/javascript"></script>
	<script src="js/bootstrap.bundle.js" type="text/javascript"></script>
</body>
</html>