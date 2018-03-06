<%@page import="model.bean.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
  <link rel="shortcut icon" type="image/png" href="<%=request.getContextPath() %>/templates/admin/img/favicon.png">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Creative - Bootstrap 3 Responsive Admin Template">
    <meta name="author" content="GeeksLabs">
    <meta name="keyword" content="Creative, Dashboard, Admin, Template, Theme, Bootstrap, Responsive, Retina, Minimal">
    
    <title>New -- Megazine</title>

    <!-- Bootstrap CSS -->    
    <link href="<%=request.getContextPath() %>/templates/admin/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="<%=request.getContextPath() %>/templates/admin/css/bootstrap-theme2.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="<%=request.getContextPath() %>/templates/admin/css/elegant-icons-style.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/templates/admin/css/font-awesome.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/templates/admin/css/chat.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="<%=request.getContextPath() %>/templates/admin/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath() %>/templates/admin/css/style-responsive.css" rel="stylesheet" />
    <script src="<%= request.getContextPath() %>/templates/admin/ckeditor/ckeditor.js" type="text/javascript"></script>
	<!-- container section end -->
    <!-- javascripts -->
    <script src="<%=request.getContextPath() %>/templates/admin/js/jquery.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/js/bootstrap.min.js"></script>
    <!-- nicescroll -->
    <script src="<%=request.getContextPath() %>/templates/admin/js/jquery.scrollTo.min.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/js/jquery.nicescroll.js" type="text/javascript"></script>
    <!--custome script for all page-->
    <script src="<%=request.getContextPath() %>/templates/admin/js/scripts.js"></script>
     <script src="<%=request.getContextPath() %>/templates/admin/js/chat.js"></script>
	<script src="<%= request.getContextPath() %>/templates/admin/js/jquery-3.1.1.min.js" type="text/javascript"></script>
		<script src="<%= request.getContextPath() %>/templates/admin/js/jquery.validate.js" type="text/javascript"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
      <script src="js/respond.min.js"></script>
      <script src="js/lte-ie7.js"></script>
    <![endif]-->
  </head>

  <body>
  <!-- container section start -->
  <section id="container" class="">
      <!--header start-->
      <header class="header dark-bg">
            <div class="toggle-nav">
                <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"><i class="icon_menu"></i></div>
            </div>

            <!--logo start-->
            <a href="index.html" class="logo">Vina <span class="lite">Enter</span></a>
            <!--logo end-->

           <!--  <div class="nav search-row" id="top_menu">
                 search form start
                <ul class="nav top-menu">                    
                    <li>
                        <form class="navbar-form">
                            <input class="form-control" placeholder="Search" type="text">
                            <select class="form-control round-input" name="show">
                                  <option value="0">No</option>
                                 <option value="1">Yes</option>
                            </select>
                        </form>
                    </li>  
                                      
                </ul>
                 search form end                
            </div> -->

            <div class="top-nav notification-row">                
                <!-- notificatoin dropdown start-->
                <ul class="nav pull-right top-menu">
                <%
                	if(session.getAttribute("userInfor") != null){
                		Users objUser = (Users) session.getAttribute("userInfor");
                %>
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="profile-ava">
                                <img style="width: 30px; height: 30px;" alt="" src="<%=request.getContextPath() %>/files/<%=objUser.getPicture()%>">
                            </span>
                            <span class="username"><%=objUser.getFullname() %></span>
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu extended logout">
                            <div class="log-arrow-up"></div>
                            <li class="eborder-top">
                                <a href="<%=request.getContextPath()%>/admin/users/profile"><i class="icon_profile"></i> My Profile</a>
                            </li>
                            <li>
                                <a href="<%=request.getContextPath()%>/logout"><i class="icon_key_alt"></i> Log Out</a>
                            </li>
                        </ul>
                    </li>
                    <% } else { %>
                      <li><a style="color: white;" href="<%=request.getContextPath()%>/login">Login</a></li>
                      <%} %>
                    <!-- user login dropdown end -->
                </ul>
                <!-- notificatoin dropdown end-->
            </div>
      </header>      
      <!--header end-->
