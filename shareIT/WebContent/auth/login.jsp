<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8" http-equiv="Content-Type">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
    <link rel="shortcut icon" href="img/favicon.png">

    <title>Login Page 2 | Creative - Bootstrap 3 Responsive Admin Template</title>

   <!-- Bootstrap CSS -->    
    <link href="<%=request.getContextPath() %>/templates/admin/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="<%=request.getContextPath() %>/templates/admin/css/bootstrap-theme2.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="<%=request.getContextPath() %>/templates/admin/css/elegant-icons-style.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/templates/admin/css/font-awesome.min.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="<%=request.getContextPath() %>/templates/admin/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/templates/admin/css/style-responsive.css" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
      <script src="js/lte-ie7.js"></script>
    <![endif]-->
      <!-- javascripts -->
    <script src="<%=request.getContextPath() %>/templates/admin/js/jquery.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/js/bootstrap.min.js"></script>
    <!-- nicescroll -->
    <script src="<%=request.getContextPath() %>/templates/admin/js/jquery.scrollTo.min.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/js/jquery.nicescroll.js" type="text/javascript"></script>
    <!--custome script for all page-->
    <script src="<%=request.getContextPath() %>/templates/admin/js/scripts.js"></script>

    

</head>

  <body class="login-img3-body">

    <div class="container">
<%
	  	if (request.getParameter("msg") != null){
	  		int msg = Integer.parseInt(request.getParameter("msg"));
	  		switch (msg){
	  		case 0: out.print("<div class='panel-body'>"+"<div style='text-align: center;' class='alert alert-danger fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Warning!</strong> Sai tên hoặc mật khẩu</div>");
	  		break;
	  		case 1: out.print("<div class='panel-body'>"+"<div style='text-align: center;' class='alert alert-danger fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Warning!</strong> Tài khoản đã bị khóa -- Liên hệ ADMIN: GunnerH2-- 096969696969</div>");
	  		break;
	  		}
	  	}
	  %>
      <form class="login-form" action="<%=request.getContextPath() %>/login" method="post">        
        <div class="login-wrap">
            <p class="login-img"><i class="icon_lock_alt"></i></p>
            <div class="input-group">
              <span class="input-group-addon"><i class="icon_profile"></i></span>
              <input type="text" class="form-control" placeholder="Username"  name="username" autofocus>
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_key_alt"></i></span>
                <input type="password" class="form-control" placeholder="Password" name="password">
            </div>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> Remember me
            </label>
            <button class="btn btn-primary btn-lg btn-block" type="submit">Login</button>
        </div>
      </form>
    <div class="text-right">
            <div class="credits">
                <!-- 
                    All the links in the footer should remain intact. 
                    You can delete the links only if you purchased the pro version.
                    Licensing information: https://bootstrapmade.com/license/
                    Purchase the pro version form: https://bootstrapmade.com/buy/?theme=NiceAdmin
                -->
                <a href="https://www.facebook.com/hoan.gunner">New</a> by <a href="https://www.facebook.com/hoan.gunner">H2Gunner</a>
            </div>
        </div>
    </div>


  </body>
</html>
