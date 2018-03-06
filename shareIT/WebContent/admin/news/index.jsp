<%@page import="model.bean.Category"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.NewsDAO"%>
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
						<!-- <li><i class="fa fa-th-list"></i>Basic Table</li> -->
					</ol>
				</div>
				<div style="margin-left: 16px; margin-top: -14px; margin-bottom: 5px; display: inline;">
					
                      <form class="navbar-form" style="display: inline-flex;" action="<%=request.getContextPath()%>/admin/news/search">
                           <div>
                            <input class="form-control" placeholder="Search" type="text" name="key">
                      </div>
                      <div style="margin-bottom: 6px;" class="col-lg-1">
	                      <select class="form-control round-input" name="catID">
	                       <option value="0">Tìm theo tên</option>
	                      <%
	                      	if(request.getAttribute("listCat")!= null){
	                      		ArrayList<Category> listCat = (ArrayList<Category>) request.getAttribute("listCat");
	                      		for(Category objCat : listCat){
	                      	
	                      %>
	                           <option value="<%=objCat.getId()%>"><%=objCat.getName() %></option>
	                           <% }} %>
	                       </select>
                       </div>
                        </form>
                       <a class="btn btn-success btn-sm"  href="<%=request.getContextPath()%>/admin/news/add" title="Bootstrap 3 themes generator" >Thêm</a>
				</div>
			</div>
							 <%
	  	if (request.getParameter("msg") != null){
	  		int msg = Integer.parseInt(request.getParameter("msg"));
	  		switch (msg){
	  		case 1: out.print("<div class='panel-body'>"+"<div class='alert alert-success fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Well done!</strong> Bạn đã Thêm thành công</div>");
	  		break;
	  		case 2: out.print("<div class='panel-body'>"+"<div class='alert alert-success fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Well done!</strong> Bạn đã Sửa thành công</div>");
	  		break;
	  		case 3: out.print("<div class='panel-body'>"+"<div class='alert alert-success fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Well done!</strong> Bạn đã Xóa thành công</div>");
	  		break;
	  		case 4: out.print("<div class='panel-body'>"+"<div class='alert alert-danger fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Oh no!</strong> ID không tồn tại</div>");
	  		break;
	  		case 5: out.print("<div class='panel-body'>"+"<div class='alert alert-success fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Well done!</strong> Bạn đã Active thành công</div>");
	  		break;
	  		case 6: out.print("<div class='panel-body'>"+"<div class='alert alert-success fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Well done!</strong> Bạn đã Block thành công</div>");
	  		break;
	  		case 0: out.print("<div class='panel-body'>"+"<div class='alert alert-danger fade in'>"+"<button data-dismiss='alert' class='close close-sm' type='button'>"+"<i class='icon-remove'></i>"+"</button><strong>Warning!</strong> Đã có lỗi xảy ra</div>");
	  		break;
	  		}
	  	}
	  %>
              <!-- page start-->                        
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <table class="table table-striped table-advance table-hover">
                           <tbody>
                              <tr>
								<th><i class=""></i>ID</th>
                                 <th><i class="icon_profile"></i> Tên bài viết</th>
                                 <th><i class="icon_image" style="width: 250px;"></i> Hình ảnh</th>
                                 <th><i class="icon_mail_alt" ></i> Danh mục	</th>
                                 <th><i class="icon_calendar"></i> Ngày tạo</th>
                                 <th><i class="icon_cogs"></i> Action</th>
                              </tr>
                              <%
                              	if (request.getAttribute("listNews") != null){
                              		ArrayList<News> listNews = (ArrayList<News>) request.getAttribute("listNews");
                              		if (listNews.size()>0){
                              			for (News objNews: listNews){
                              	
                              %>
                              <tr>
								 <td><%=objNews.getId() %></td>
                                 <td><%=objNews.getName() %></td>
                                 <td><img alt="<%=objNews.getName() %>" class="img-rounded" style="width: 127px; height: 89px;" src="<%=request.getContextPath() %>/files/<%=objNews.getPicture() %>" /></td>
                                  <td><%=objNews.getCat_name() %></td>
                                 <td><%=objNews.getDate_create() %></td>
                                 <td>
                                  <div class="btn-group">
                                  <ul class="nav nav-tabs">
                                  	<li><a class="btn btn-primary" href="<%=request.getContextPath()%>/admin/news/edit?nid=<%=objNews.getId()%>"><i class="icon_pencil-edit_alt"></i></a></li>
                                	<%
                                		objUserInfor = (Users) session.getAttribute("userInfor");
                                		if ("admin".equals(objUserInfor.getUsername())){
                                	%>
                                	<li id="active-<%=objNews.getId() %>">
                                		<%
                                			if(objNews.getIs_slide() == 1){
                                		%>
                                	<a onclick="active(<%=objNews.getId() %>, <%=objNews.getIs_slide() %>)" class="btn btn-success" href="javascript:void(0)" ><i class="icon_check_alt2"></i></a>
                                	<% }else{  %>
                                	<a onclick="active(<%=objNews.getId() %>, <%=objNews.getIs_slide() %>)" class="btn btn-warning" href="javascript:void(0)" ><i class="icon_upload"></i></a>
                                	<%} %>
                                	</li>
                                	<%} %>
                                	<li><a class="btn btn-danger" onclick="return confirm('Bạn có muốn xóa không')" href="<%=request.getContextPath()%>/admin/news/del?nid=<%=objNews.getId()%>"><i class="icon_close_alt2"></i></a></li>
                                 
                                  </ul>
                                  </div>
                                  </td>
                              </tr>
                                <script>
								function  active(id, active) {
									$.ajax({
										url: '<%=request.getContextPath()%>/admin/news/active',
										type: 'POST',
										cache: false,
										data: {
												//Dữ liệu gửi đi
												nid : id,
												nactive : active,
												},
										success: function(data){
											// Xử lý thành công
											$('#active-'+id).html(data);
										},
										error: function (){
										// Xử lý nếu có lỗi
										alert('Loi');
										}
									});
								}
						</script>
                              <%
                              			}}}
                              %>
                           </tbody>
                        </table>
                      </section>
                  </div>
              </div>
              <!-- page end-->
          </section>
      <div class="pagination" style="margin-left: 23px;   margin-top: -31px;">           
			<div class="numbers">
			<%
				if (request.getAttribute("sumPage")!= null){
				int current_page = (Integer) request.getAttribute("current_page");
				int sumPage = (Integer) request.getAttribute("sumPage");
				String active="";
				int pre = current_page-1;
				int next = current_page+1;
				String urlSEOFirst = request.getContextPath()+"/admin/news?page=1";
				String urlSEOLast = request.getContextPath()+"/admin/news?page="+sumPage;
				String urlSEOPre = request.getContextPath()+"/admin/news?page="+pre;
				String urlSEONext = request.getContextPath()+"/admin/news?page="+next;
				%>
				<a href="<%=urlSEOFirst%>">[<<]</a>
				<% if (pre <=0) {%>
				<a href="<%=urlSEOFirst%>">[<]</a>
				<% }else { %>
				<a href="<%=urlSEOPre%>">[<]</a>
				<%} %>
				<%
				if (sumPage <4) {
					for (int i=1; i<=sumPage;i++){
						if (current_page == i){
							active = "class='active'";
						}else {
							active ="";
						}
						String urlSEO = request.getContextPath()+"/admin/news?page="+i;
						if (i==1){
							%>	
								<a <%=active %> style=" text-align: center;" href="<%=urlSEO%>"><%=i %></a>
								<%} else {%>
								<a <%=active %> style=" text-align: center;" href="<%=urlSEO%>">|<%=i %></a>
								<%
								}}} else {
				if (current_page <(sumPage-3) && sumPage>3){
				for (int i=current_page; i<=current_page+3;i++){
					if (current_page == i){
						active = "class='active'";
					}else {
						active ="";
					}
					String urlSEO = request.getContextPath()+"/admin/news?page="+i;if (i==1){
						%>	
						<a <%=active %> style=" text-align: center;" href="<%=urlSEO%>"><%=i %></a>
						<%} else {%>
						<a <%=active %> style=" text-align: center;" href="<%=urlSEO%>">|<%=i %></a>
						<%
						}}}} 
				if (current_page>=(sumPage-3) && sumPage >3) { 
				   for (int i=sumPage-3; i<=sumPage;i++){
					if (current_page == i){
						active = "class='active btn btn-success btn-xs'";
					}else {
						active ="";
					}
					String urlSEO = request.getContextPath()+"/admin/news?page="+i;
					if (i==1){
						%>	
							<a <%=active %> style=" text-align: center;" href="<%=urlSEO%>"><%=i %></a>
							<%} else {%>
							<a <%=active %> style=" text-align: center;" href="<%=urlSEO%>">|<%=i %></a>
							<%
							}} }%>
				<% if (next >= sumPage) {%>
				<a href="<%=urlSEOLast%>">[>]</a>
				<% }else { %>
				<a href="<%=urlSEONext%>">[>]</a>
				<%} %>
				<a href="<%=urlSEOLast%>">[>>]</a>
				<%} %>
			</div> 
			<div style="clear: both;"></div> 
		 </div> 
      </section>
      <!--main content end-->
     
   <%@include file="/templates/admin/inc/footer.jsp" %>  