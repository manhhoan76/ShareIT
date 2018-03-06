<%@page import="model.bean.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu">                
                  <li class="active">
                      <a class="" href="<%=request.getContextPath() %>/admin/index">
                          <i class="icon_house_alt"></i>
                          <span>Dashboard</span>
                      </a>
                  </li>
				  <li class="sub-menu">
                      <a href="<%=request.getContextPath() %>/admin/news" class="">
                          <i class="icon_document_alt"></i>
                          <span>Tin Tức</span>
                      </a>
                  </li>       
                  <li class="sub-menu">
                      <a href="<%=request.getContextPath() %>/admin/cat" class="">
                          <i class="icon_desktop"></i>
                          <span>Danh mục</span>
                      </a>
                  </li>
                  <li>
                      <a class="" href="<%=request.getContextPath() %>/admin/users">
                          <i class="icon_genius"></i>
                          <span>User</span>
                      </a>
                  </li>
                   <li class="sub-menu">
                      <a href="<%=request.getContextPath() %>/admin/video" class="">
                          <i class="icon_table"></i>
                          <span>Video</span>                
                      </a>
                  </li>   
                  <%
                  Users objUserInfor = (Users) session.getAttribute("userInfor");
                  if ("admin".equals(objUserInfor.getUsername())) {
                  %>
                  <li>                     
                      <a class="" href="<%=request.getContextPath() %>/admin/comment">
                          <i class="icon_piechart"></i>
                          <span>Comment News</span>
                          
                      </a>
                                         
                  </li>
                  <li>                     
                      <a class="" href="<%=request.getContextPath() %>/admin/commentVideo">
                          <i class="icon_piechart"></i>
                          <span>Comment Video</span>
                          
                      </a>
                                         
                  </li>
                  <li class="sub-menu">
                      <a href="<%=request.getContextPath() %>/admin/ads" class="">
                          <i class="icon_table"></i>
                          <span>Quảng cáo</span>                
                      </a>
                  </li>  
                  <li class="sub-menu">
                      <a href="<%=request.getContextPath() %>/admin/contact" class="">
                          <i class="icon_table"></i>
                          <span>Liên hệ</span>                
                      </a>
                  </li>  
                   <%} %>      
             </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->