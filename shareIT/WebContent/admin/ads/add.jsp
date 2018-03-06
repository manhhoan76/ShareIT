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
						<li><i class="fa fa-table"></i><a href="<%=request.getContextPath()%>/admin/ads">Quảng cáo</a></li>
						<li><i class="fa fa-table"></i><a href="<%=request.getContextPath()%>/admin/ads/add">Add</a></li>
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
                             Add User
                          </header>
                          <div class="panel-body">
                              <form class="form-horizontal addAds" method="post" action="<%=request.getContextPath() %>/admin/ads/add" enctype="multipart/form-data">
                               
                                  <div class="form-group">
                                      <label class="col-sm-2 control-label">Tên Quảng cáo</label>
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
                                      <label for="exampleInputFile" style="margin-left: 205px;">Ảnh Quảng cáo</label>
                                      <input type="file" id="exampleInputFile" style="margin-left: 205px;" name="hinh">
                                  </div>
	                                  <div style="margin-left: 182px;" class="col-lg-10" >
										<input type="submit" class="btn btn-success btn-sm"  title="Bootstrap 3 themes generator" value="Thêm">
								</div>
                              </form>
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