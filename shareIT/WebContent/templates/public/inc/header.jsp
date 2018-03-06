<%@page import="util.SlugUtil"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CatDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"  content="initial-scale=1, width=device-width">
<title>Home</title>
<link href="<%=request.getContextPath() %>/templates/public/css/styles7.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/templates/public/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/templates/public/engine1/style6.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/engine1/jquery.js"></script>


</head>
<body class="home" >
    <!-- start div #wrapper -->
    <div id="wrapper">
        <!-- start header -->
        <header>
            <div class="border-left"></div>
            <div class="logo">
                <a href="<%=request.getContextPath() %>/index"><img src="<%=request.getContextPath() %>/templates/public/img/logo.png" alt=""></a>
                <span class="border-bottom"></span>
            </div>
            <div class="search">
            <%
            	String UrlSeoSearch = request.getContextPath()+"/tim-kiem.html";
            %>
                <form action="<%=UrlSeoSearch%>" method="get">
                    <input class="field" type="text" name="key" placeholder="Search News..." >
                    <input class="submit" type="submit"  value="" >
                </form>
            </div>
            <nav class="menu">
                <ul>
                <%
                String urlSEOIndex = request.getContextPath()+"/trang-chu.html";
                String urlSEOVideo = request.getContextPath()+"/video.html";
                String urlSEOVContact = request.getContextPath()+"/lien-he.html";
                %>
                	
                    <li><span class="border-bottom"></span><a href="<%=urlSEOIndex %>">Trang chủ</a></li>
                    <li><span class="border-bottom"></span><a href="<%=urlSEOIndex%>">Danh mục</a>
                    	<ul class="dropdown">
                    		<%
                    	CatDAO catDAO = new CatDAO();
                    	ArrayList<Category> listCat = (ArrayList<Category>) catDAO.getItems();
                    	if(listCat.size()>0){
                    		for(Category objCat: listCat){
                    			if (objCat.getParent_id() ==0){
                    				String urlSEO = request.getContextPath()+"/"+SlugUtil.makeSlug(objCat.getName())+"/"+objCat.getId()+".html";
                    		
                    %>
                    <li><span class="border-bottom"></span><a href="<%=urlSEO%>"><%=objCat.getName() %></a>
                        <ul class="dropdown" style="top: 0px;">
                        <%
                        	ArrayList<Category> listChil = (ArrayList<Category>) catDAO.getItemsChil(objCat.getId());
                        	if(listChil.size()>0){
                        		for (Category objCategoryChil : listChil){
                        			String urlSEOCatChil = request.getContextPath()+"/"+SlugUtil.makeSlug(objCategoryChil.getName())+"/"+objCategoryChil.getId()+".html";
                        			%>
                            <li><a href="<%=urlSEOCatChil%>"><%=objCategoryChil.getName() %></a></li>
                            <%}} %>
                            <li><a href="<%=urlSEO%>">Tin Tổng Hợp</a></li>
                        </ul>
                    </li>
                    <%}}} %>
                    	</ul>
                    </li>
                       <li><span class="border-bottom"></span><a href="<%=urlSEOVideo%>">Video</a></li>
                     <li><span class="border-bottom"></span><a href="<%=urlSEOVContact%>">Liên hệ</a></li>
                  
                </ul>                                              
            </nav>   
            <select class="mobile-menu" onchange='document.location.href=this.options[this.selectedIndex].value;'> 
                <option value="">Go To...</option> 
                <option value="index-2.jsp">Home</option>
                <option value="category.jsp">Category</option>
                <option value="blog-single.jsp">News single</option>
                <option value="features.jsp">Features</option>
                <option value="full-width.jsp">-Full width</option>
                <option value="blog-single-with-rating.jsp">-News single with rating box</option>
                <option value="contact.jsp">-Contact</option>
            </select>
            <div class="clear"></div>
        </header>
        <!-- end header -->

