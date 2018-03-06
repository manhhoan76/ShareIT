<%@page import="model.bean.Users"%>
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
						<li><i class="fa fa-home"></i><a href="<%=request.getContextPath()%>/admin/index">Home</a></li>
						<li><i class="fa fa-table"></i><a href="<%=request.getContextPath()%>/admin/users">Users</a></li>
						<li><i class="fa fa-table"></i><a href="<%=request.getContextPath()%>/admin/users/edit">Edit</a></li>
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
	  		case 1: out.print("<div class='panel-body'>"+"<div class='alert alert-danger fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Oh no!</strong> Trùng Username</div>");
	  		break;
	  		}
	  	}
	  %>
			 <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                             Edit User
                          </header>
                          <div class="panel-body">
                          <%
                          if (request.getAttribute("objUser") != null){
                        		Users objUser = (Users) request.getAttribute("objUser");
                          %>
                              <form class="form-horizontal editUser" method="post" action="<%=request.getContextPath() %>/admin/user/edit?uid=<%=objUser.getId() %>" enctype="multipart/form-data">
                               
                                  <div class="form-group">
                                      <label class="col-sm-2 control-label">Username</label>
                                      <div class="col-sm-10">
                                          <input type="text" class="form-control round-input" value="<%=objUser.getUsername() %>" name="username">
                                      </div>
                                  </div>
								  <div class="form-group">
                                      <label class="col-sm-2 control-label">Password</label>
                                      <div class="col-sm-10">
                                          <input type="password" class="form-control round-input" name="password" placeholder="Nhập password mới vào đây" >
                                      </div>
                                  </div>
								  <div class="form-group">
                                      <label class="col-sm-2 control-label">Fullname</label>
                                      <div class="col-sm-10">
                                          <input type="text" class="form-control round-input" name="fullname" value="<%=objUser.getFullname() %>">
                                      </div>
                                  </div>
								  <div class="form-group">
                                      <label class="col-sm-2 control-label" >Address</label>
                                      <div class="col-sm-10">
                                          <input type="text" class="form-control round-input" name="address" value="<%=objUser.getAddress() %>">
                                      </div>
                                  </div>
								  <div class="form-group">
                                      <label class="col-sm-2 control-label" >Phone</label>
                                      <div class="col-sm-10">
                                          <input type="text" class="form-control round-input" name="phone" value="<%=objUser.getPhone() %>">
                                      </div>
                                  </div>
								<!--    <div class="form-group">
                                      <label class="control-label col-lg-2" for="inputSuccess">Selects</label>
                                      <div class="col-lg-10">
                                          <select class="form-control round-input">
                                              <option>1</option>
                                              <option>2</option>
                                              <option>3</option>
                                              <option>4</option>
                                              <option>5</option>
                                          </select>
                                      </div>
                                  </div> -->
								  <div class="form-group">
                                  <header class="panel-heading">
                                     Caption
                                  </header>
                                  <div class="panel-body">
                                      <div class="form">
                                              <div class="form-group">
                                                  <label class="control-label col-sm-2">Caption</label>
                                                  <div class="col-sm-10">
                                                      <textarea id="editor" class="form-control ckeditor" name="caption" rows="6" ><%=objUser.getCaption() %></textarea>
                                                  </div>
                                              </div>
                                      </div>
                                  </div>
								</div>
                                 <div class="form-group">
                                      <label for="exampleInputFile" style="margin-left: 205px;">Ảnh đại diện</label>
                                      <input type="file" id="exampleInputFile" style="margin-left: 205px;" name="hinh" >
                                      <%
											if(!"".equals(objUser.getPicture())) {
										%>
											<img style="margin-left: 207px; width: 150px; height: 100px; margin-top: 16px;" class="img-rounded" alt="<%=objUser.getPicture() %>" src="<%=request.getContextPath()%>/files/<%=objUser.getPicture()%>">
									<% } %>
                                  </div>
	                                  <div style="margin-left: 182px;" class="col-lg-10" >
										<input type="submit" class="btn btn-success btn-sm"  title="Bootstrap 3 themes generator" value="Sửa">
										<a class="btn btn-info btn-sm" href="<%=request.getContextPath() %>/admin/users" title="Return">Quay lại</a>
								</div>
                              </form>
                              <%} %>
                          </div>
                      </section>
                  </div>
              </div>
              <!-- page end-->
          </section>
      </section>
       <script type="text/javascript">
		$(document).ready(function() {
			$('.editUser').validate({
				rules : {
					username : {
						required : true,
					},
					fullname : {
						required : true,
					},
					address : {
						required : true,
					},
					phone : {
						required : true,
						number: true,
					},
					caption : {
						required : true,
					},
					
				},
				messages : {
					username : {
						required : "Bạn chưa nhập Username",
					},
					fullname : {
						required : "Bạn chưa nhập Fullname",
					},
					address : {
						required : "Bạn chưa nhập Address",
					},
					phone : {
						required : "Bạn chưa nhập Phone",
						number: "Số phone phải là số",
					},
					caption : {
						required : "Bạn chưa nhập Caption",
					},
					
				},
			});
		});
	CKEDITOR.replace('editor');
	
	</script>
		<style>
		.error {
			color: red;
			font-weight: bold;
		}
		</style>
      
      <!--main content end-->
   <%@include file="/templates/admin/inc/footer.jsp" %>  