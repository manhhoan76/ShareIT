<%@page import="model.dao.CatDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
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
						<li><i class="fa fa-home"></i><a href="index.html">Home</a></li>
						<li><i class="fa fa-table"></i><a href="index.html">Tin tức</a></li>
						<!-- <li><i class="fa fa-th-list"></i>Basic Table</li> -->
					</ol>
				</div>
				
			</div>
				<%
	  	if (request.getParameter("msg") != null){
	  		int msg = Integer.parseInt(request.getParameter("msg"));
	  		switch (msg){
	  		case 0: out.print("<div class='panel-body'>"+"<div class='alert alert-danger fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Oh no!</strong> Lỗi xảy ra</div>");
	  		break;
	  		case 1: out.print("<div class='panel-body'>"+"<div class='alert alert-danger fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Oh no!</strong> Trùng tên danh mục</div>");
	  		break;
	  		}
	  	}
	  %>
              <!-- page start-->                        
              <section class="panel">
                          <div class="panel-body">
                          		<%
										if (request.getAttribute("objCat") !=""){
											Category objCat =(Category) request.getAttribute("objCat");
										
								%>
                          
                              <form class="form-horizontal" method="post" action="<%=request.getContextPath() %>/admin/cat/edit?cid=<%=objCat.getId()%>">
                              	  <div class="form-group">
                                      <label class="control-label col-lg-2" for="inputSuccess">Tên danh mục tin</label>
                                      <div class="col-lg-10">
                                        <input class="form-control m-bot15" type="text" value="<%=objCat.getName() %>"  placeholder="Nhập tên danh mục tin" name="nameCat">
                                      </div>
                                      <label class="control-label col-lg-2" for="inputSuccess">Danh mục cha</label>
                                      <div class="col-lg-10">
                                          <select class="form-control m-bot15" name="danhmuccha">
                                          <%
                                          	if(objCat.getParent_id()==0){
                                          %>
                                              <option value="0">--Không có--</option>
                                              <%
                                          	if(request.getAttribute("listCat")!= null){
												ArrayList<Category> listCat = (ArrayList<Category>) request.getAttribute("listCat");
												if(listCat.size()>0){
													for (Category item: listCat){
														if (item.getParent_id()==0  && objCat.getId() != item.getId()){
				
										%>
                                          <option value="<%=item.getId()%>"><%=item.getName() %></option>
                                          <% }}}} %>
                                              <%} else {
													if(request.getAttribute("listCat")!= null){
													ArrayList<Category> listCat = (ArrayList<Category>) request.getAttribute("listCat");
													String select = "";
													
													if(listCat.size()>0){
														for (Category item: listCat){
															if (item.getParent_id()==0 && objCat.getId()!= item.getId()){
																if (item.getId() == objCat.getParent_id()){
																	select = "selected ='selected'";
																}else 
																{
																	select="";
																}
												
	
					
											%>
                                              <option <%=select %> value="<%=item.getId()%>"><%=item.getName() %></option>
                                             <%
														}}}}
												%>
												<option value="0">--Không có--</option>
												<%	
                                              }}
                                             %>
                                          </select>
                                      </div>
                                      <div style="margin-left: 582px;" class="col-lg-10"  >
											<input type="submit" class="btn btn-success btn-sm"  name="sua" title="Bootstrap 3 themes generator" value="Sửa">
											<a class="btn btn-info btn-sm" href="<%=request.getContextPath() %>/admin/cat" title="Return">Quay lại</a>
										</div>
                                  </div>
                               </form>
                          </div>
                      </section>
              <!-- page end-->
          </section>
      </section>
      <!--main content end-->
   <%@include file="/templates/admin/inc/footer.jsp" %>  