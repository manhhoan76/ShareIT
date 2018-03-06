<%@page import="model.bean.Video"%>
<%@page import="model.bean.News"%>
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
						<li><i class="fa fa-table"></i><a href="<%=request.getContextPath()%>/admin/video">Video</a></li>
					</ol>
				</div>
			</div>
			<%
	  	if (request.getParameter("msg") != null){
	  		int msg = Integer.parseInt(request.getParameter("msg"));
	  		switch (msg){
	  		case 0: out.print("<div class='panel-body'>"+"<div class='alert alert-danger fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Oh no!</strong> Lỗi xảy ra</div>");
	  		break;
	  		case 1: out.print("<div class='panel-body'>"+"<div class='alert alert-danger fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Oh no!</strong> Trùng tên tin</div>");
	  		break;
	  		}
	  	}
	  %>
			 <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                             Add News
                          </header>
                          <div class="panel-body">
                          	<%
                          		if(request.getAttribute("listCatParent") != null){
                          			Video objVideo = (Video) request.getAttribute("objVideo");
                          			Category objCatParent = (Category) request.getAttribute("catParent");
                          			ArrayList<Category> listCatParent = (ArrayList<Category>) request.getAttribute("listCatParent");
                          			if(listCatParent.size()>0){
                          				
                          	%>
                              <form class="form-horizontal" enctype="multipart/form-data" method="post" action="<%=request.getContextPath()%>/admin/video/edit?vid=<%=objVideo.getId()%>">
                               
                                  <div class="form-group">
                                      <label class="col-sm-2 control-label">Tên Video</label>
                                      <div class="col-sm-10">
                                          <input type="text" class="form-control round-input" name="nameVideo" value="<%=objVideo.getName()%>">
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <label class="col-sm-2 control-label">link</label>
                                      <div class="col-sm-10">
                                          <input type="text" class="form-control round-input" name="link" value="<%=objVideo.getLink()%>">
                                      </div>
                                  </div>
								   <div class="form-group">
                                      <label class="control-label col-lg-2" for="inputSuccess">Cat Parent</label>
                                      <div class="col-lg-10">
                                          <select id="danhmuccha"  onchange="danhmuccon(this)" class="form-control round-input" name="danhmuccha" >
                                          <%
                                          String select = "";
                                    		  if (objCatParent == null ){
                                          for(Category objCategory: listCatParent){
                                        	  if (objVideo.getCat_id() == objCategory.getId()){
													select = "selected ='selected'";
												}else 
												{
													select="";
												}
                                          %>
                                              <option <%=select %> value="<%=objCategory.getId() %>" ><%=objCategory.getName() %></option>
                                         <%}} else { 
                                        	 for(Category objCategory: listCatParent){
                                           	  if (objCatParent.getId() == objCategory.getId()){
   													select = "selected ='selected'";
   												}else 
   												{
   													select="";
   												}
                                         %>
                                         <option <%=select %> value="<%=objCategory.getId() %>" ><%=objCategory.getName() %></option>
                                         <%}} %>
                                          </select>
                                      </div>
                                  </div>
                                  <div class="form-group">
    								  <label class="control-label col-lg-2" for="inputSuccess">Chil Cat</label>
									      <div class="col-lg-10">
									        <select id="danhmucchil"  class="form-control round-input" name="danhmucchil">
									         <%
									         ArrayList<Category> listCatChild = (ArrayList<Category>) request.getAttribute("listCatChild");
									         	if(listCatChild != null){
									         			for (Category objCatChil: listCatChild){
									         				if (objVideo.getCat_id() == objCatChil.getId()){
																select = "selected ='selected'";
															}else 
															{
																select="";
															}
									         				
									         %>
									          <option <%=select %> value="<%=objCatChil.getId()%>"><%=objCatChil.getName() %></option>
									          <%			
								         			}
								         		
								         	} else {  %>
								         	<option  value="0">---Không có danh mục con --- </option>
								         	<%} %>
									        </select>
									   	 </div>
									 </div>
                                  <div class="form-group">
                                      <label class="control-label col-lg-2" for="inputSuccess">Slide</label>
                                      <div class="col-lg-10">
                                          <select class="form-control round-input" name="show">
                                          	<%
                                          		if(objVideo.getShow_link() == 1){
                                          	%>
                                               <option value="1">Yes</option>
                                              <option value="0">No</option>
                                              <% } else { %>
                                              <option value="0">No</option>
                                              <option value="1">Yes</option>
                                              <%} %>
                                          </select>
                                      </div>
                                  </div>
                                  <div class="form-group">
	                                  <div style="margin-left: 182px;" class="col-lg-10" >
										<input type="submit" class="btn btn-success btn-sm"  title="Bootstrap 3 themes generator" value="Sửa">
										<a class="btn btn-info btn-sm" href="<%=request.getContextPath() %>/admin/video" title="Return">Quay lại</a>
									</div>
								</div>
                              </form>
                                <script language="javascript">
								function  danhmuccon(obj){
									var id = obj.value;
									var vid = <%=objVideo.getId()%>;
									$.ajax({
										url: '<%=request.getContextPath()%>/admin/video/selectCatEdit',
										type: 'POST',
										cache: false,
										data: {
												//Dữ liệu gửi đi
												cid : id,
												vvid : vid
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
                              <%} }%>
                            
                          </div>
                      </section>
                  </div>
              </div>
              <!-- page end-->
          </section>
      </section>
      <!--main content end-->
   <%@include file="/templates/admin/inc/footer.jsp" %>  