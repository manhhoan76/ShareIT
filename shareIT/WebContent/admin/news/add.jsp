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
						<li><i class="fa fa-table"></i><a href="<%=request.getContextPath()%>/admin/news">Tin tức</a></li>
						<li><i class="fa fa-table"></i><a href="<%=request.getContextPath()%>/admin/news/add">Add</a></li>
					</ol>
				</div>
			</div>
			 <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                             Add News
                          </header>
                          <div class="panel-body">
                          	<%
                          		if(request.getAttribute("listCatParent") != null){
                          			ArrayList<Category> listCatParent = (ArrayList<Category>) request.getAttribute("listCatParent");
                          			if(listCatParent.size()>0){
                          				
                          	%>
                              <form class="form-horizontal" enctype="multipart/form-data" method="post" action="<%=request.getContextPath()%>/admin/news/add">
                               
                                  <div class="form-group">
                                      <label class="col-sm-2 control-label">Name</label>
                                      <div class="col-sm-10">
                                          <input type="text" class="form-control round-input" name="tentin">
                                      </div>
                                  </div>
                                    <div class="form-group">
                                  <header class="panel-heading">
                                     Preview
                                  </header>
                                  <div class="panel-body">
                                      <div class="form">
                                              <div class="form-group">
                                                  <label class="control-label col-sm-2">Preview</label>
                                                  <div class="col-sm-10">
                                                      <textarea class="form-control ckeditor" id="editor1" rows="6" name="mota"></textarea>
                                                  </div>
                                              </div>
                                      </div>
                                  </div>
								</div>
								<div class="form-group">
                                  <header class="panel-heading">
                                     Detail
                                  </header>
                                  <div class="panel-body">
                                      <div class="form">
                                              <div class="form-group">
                                                  <label class="control-label col-sm-2">Detail</label>
                                                  <div class="col-sm-10">
                                                      <textarea name="chitiet" class="form-control ckeditor" id="editor2" rows="6"></textarea>
                                                  </div>
                                              </div>
                                      </div>
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
                                      <label class="control-label col-lg-2" for="inputSuccess">Slide</label>
                                      <div class="col-lg-10">
                                          <select class="form-control round-input" name="slide">
                                               <option value="0">No</option>
                                              <option value="1">Yes</option>
                                          </select>
                                      </div>
                                  </div>
                                 <div class="form-group">
                                      <label for="exampleInputFile" style="margin-left: 205px;">Ảnh đại diện</label>
                                      <input type="file" id="exampleInputFile" style="margin-left: 205px;" name="hinh">
                                  </div>
                                  <div class="form-group">
	                                  <div style="margin-left: 182px;" class="col-lg-10" >
										<input type="submit" class="btn btn-success btn-sm"  title="Bootstrap 3 themes generator" value="Thêm">
									</div>
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
      <!--main content end-->
   <%@include file="/templates/admin/inc/footer.jsp" %>  