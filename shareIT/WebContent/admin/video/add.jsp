<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Category"%>
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
						<li><i class="fa fa-table"></i><a href="<%=request.getContextPath()%>/admin/video">Video</a></li>
						<li><i class="fa fa-table"></i><a href="<%=request.getContextPath()%>/admin/video/add">Add</a></li>
					</ol>
				</div>
			</div>
			<%
	  	if (request.getParameter("msg") != null){
	  		int msg = Integer.parseInt(request.getParameter("msg"));
	  		switch (msg){
	  		case 0: out.print("<div class='panel-body'>"+"<div class='alert alert-danger fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Warning!</strong> Trùng tên Quảng cáo</div>");
	  		break;
	  		}
	  	}
	  %>
			 <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                             Add Video
                          </header>
                          <div class="panel-body">
                          	<%
                          		if(request.getAttribute("listCatParent") != null){
                          			ArrayList<Category> listCatParent = (ArrayList<Category>) request.getAttribute("listCatParent");
                          			if(listCatParent.size()>0){
                          				
                          	%>
                              <form class="form-horizontal addAds" method="post" action="<%=request.getContextPath() %>/admin/video/add" enctype="multipart/form-data">
                               
                                  <div class="form-group">
                                      <label class="col-sm-2 control-label">Tên Video</label>
                                      <div class="col-sm-10">
                                          <input type="text" class="form-control round-input" name="name">
                                      </div>
                                  </div>
								  <div class="form-group">
                                      <label class="col-sm-2 control-label">Link</label>
                                      <div class="col-sm-10">
                                          <input type="url" class="form-control round-input" name="link">
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <label class="control-label col-lg-2" for="inputSuccess">Cat Parent</label>
                                      <div class="col-lg-10">
                                          <select id="danhmuccha"  onchange="danhmuccon(this)" class="form-control round-input" name="danhmuccha" >
                                          <%
                                          for(Category objCategory: listCatParent){
                                          %>
                                              <option value="<%=objCategory.getId() %>" ><%=objCategory.getName() %></option>
                                         <%} %>
                                          </select>
                                      </div>
                                  </div>
                                  <div class="form-group">
    								  <label class="control-label col-lg-2" for="inputSuccess">Chil Cat</label>
									      <div class="col-lg-10">
									        <select id="danhmucchil"  class="form-control round-input" name="danhmucchil">
									          <option value="0">--Không có danh mục con--</option>
									        </select>
									   	 </div>
									 </div>
									 <div class="form-group">
                                      <label class="control-label col-lg-2" for="inputSuccess">Show</label>
                                      <div class="col-lg-10">
                                          <select class="form-control round-input" name="show">
                                               <option value="0">No</option>
                                              <option value="1">Yes</option>
                                          </select>
                                      </div>
                                  </div>
	                              <div style="margin-left: 182px;" class="col-lg-10" >
										<input type="submit" class="btn btn-success btn-sm"  title="Bootstrap 3 themes generator" value="Thêm">
								</div>
                              </form>
                               <%} }%>
                               <script language="javascript">
								function  danhmuccon(obj){
									var id = obj.value;
									$.ajax({
										url: '<%=request.getContextPath()%>/admin/news/selectCat',
										type: 'POST',
										cache: false,
										data: {
												//Dữ liệu gửi đi
												cid : id,
												},
										success: function(data){
											// Xử lý thành công
											$('#danhmucchil').html(data);
										},
										error: function (){
										// Xử lý nếu có lỗi
										alert('Loi');
										}
									});
								}
							</script>
                          </div>
                      </section>
                  </div>
              </div>
              <!-- page end-->
          </section>
      </section>
      <script type="text/javascript">
		$(document).ready(function() {
			$('.addAds').validate({
				rules : {
					name : {
						required : true,
					},
					link : {
						required : true,
					},
				},
				messages : {
					name : {
						required : "Bạn chưa nhập tên quảng cáo",
					},
					link : {
						required : "Bạn chưa nhập link liên hết",
					},
				},
			});
		});
	
	</script>
		<style>
		.error {
			color: red;
			font-weight: bold;
		}
		</style>
      
      <!--main content end-->
   <%@include file="/templates/admin/inc/footer.jsp" %>  