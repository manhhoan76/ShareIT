<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<%@include file="/templates/admin/inc/sidebar.jsp" %>
     
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-table"></i> Tin tức</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="<%=request.getContextPath()%>/admin/index">Home</a></li>
						<li><i class="fa fa-table"></i><a href="<%=request.getContextPath()%>/admin/comment">Bình luận</a></li>
						<!-- <li><i class="fa fa-th-list"></i>Basic Table</li> -->
					</ol>
				</div>
				      <!-- page start-->                        
              	 <div  style="margin-left: 4px; margin-right: -252px;" class="row">
              	 <%
					if (request.getAttribute("sumView") != null){
						int sumView = (Integer) request.getAttribute("sumView");
				%>
				<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12">
					<div class="info-box blue-bg">
						<i class="fa fa-signal"></i>
						<div class="count"><%=sumView %></div>
						<div class="title">Xem trang</div>						
					</div><!--/.info-box-->			
				</div><!--/.col-->
				<%} %>
				 <%
					if (request.getAttribute("sumNew") != null){
						int sumNew = (Integer) request.getAttribute("sumNew");
				%>
				<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12">
					<div class="info-box dark-bg">
						<i class="fa fa-thumbs-o-up"></i>
						<div class="count"><%=sumNew %></div>
						<div class="title">Số tin</div>						
					</div><!--/.info-box-->			
				</div><!--/.col--><%} %>
				<%
					if (request.getAttribute("sumComment") != null){
						int sumComment = (Integer) request.getAttribute("sumComment");
				%>
				<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12">
					<div class="info-box green-bg">
						<i class="fa fa-comment"></i>
						<div class="count"><%=sumComment %></div>
						<div class="title">Bình luận</div>						
					</div><!--/.info-box-->			
				</div><!--/.col--><%} %>
				 <%
					if (request.getAttribute("sumUser") != null){
						int sumUser = (Integer) request.getAttribute("sumUser");
				%>
				<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12">
					<div class="info-box red-bg">
						<i class="fa fa-user"></i>
						<div class="count"><%=sumUser %></div>
						<div class="title">Người dùng</div>						
					</div><!--/.info-box-->			
				</div><!--/.col--><%} %>
				<%
					if (request.getAttribute("sumAds") != null){
						int sumAds = (Integer) request.getAttribute("sumAds");
				%>
				<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12">
					<div class="info-box orange-bg">
						<i class="fa fa-shopping-cart"></i>
						<div class="count"><%=sumAds %></div>
						<div class="title">Quảng cáo</div>						
					</div><!--/.info-box-->			
				</div><!--/.col--><%} %>
			</div><!--/.row-->
		
              <!-- page end-->
          </section>
         
      </section>
      <!--main content end-->
   <%@include file="/templates/admin/inc/footer.jsp" %>  