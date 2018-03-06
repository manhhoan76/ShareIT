<%@page import="model.bean.Ads"%>
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
						<li><i class="fa fa-table"></i><a href="<%=request.getContextPath()%>/admin/ads">Quảng cáo</a></li>
						<!-- <li><i class="fa fa-th-list"></i>Basic Table</li> -->
					</ol>
				</div>
				<div style="margin-left: 16px; margin-top: -14px; margin-bottom: 5px;">
					<a class="btn btn-success btn-sm" href="<%=request.getContextPath()%>/admin/ads/add" title="Bootstrap 3 themes generator">Thêm</a>
					<form class="navbar-form" style="display: inline-block;" action="<%=request.getContextPath()%>/admin/ads/search" method="get">
                            <input class="form-control" placeholder="Search" type="text" name="key">
                      </form>
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
                                 <th><i class="icon_profile"></i> Tên quảng cáo</th>
                                 <th><i class="icon_calendar"></i> Hình ảnh</th>
                                 <th><i class="icon_pin_alt"></i> Link</th>
                                 <th><i class="icon_cogs"></i> Action</th>
                              </tr>
                              <%
                              	if (request.getAttribute("listAds")!= null){
                              		ArrayList<Ads> listAds = (ArrayList<Ads>) request.getAttribute("listAds");
                              			if (listAds.size()>0){
                              				for (Ads objAds: listAds){
                              					
                              	
                              %>
                              <tr>
								 <td><%=objAds.getId() %></td>
                                 <td><%=objAds.getName() %></td>
                                 <td><img alt="<%=objAds.getName() %>" class="img-rounded" style="width: 127px; height: 89px;" src="<%=request.getContextPath() %>/files/<%=objAds.getPicture() %>" /></td>
                                 <td><a href="<%=objAds.getLink() %>"><%=objAds.getLink() %></a></td>
                                 <td>
                                  <div class="btn-group">
                                  <ul class="nav nav-tabs">
                                  	<li><a class="btn btn-primary" href="<%=request.getContextPath()%>/admin/ads/edit?aid=<%=objAds.getId()%>"><i class="icon_pencil-edit_alt"></i></a></li>
                                	<li id="active-<%=objAds.getId() %>">
                                		<%
                                			if(objAds.getActive() == 1){
                                		%>
                                	<a onclick="active(<%=objAds.getId() %>, <%=objAds.getActive() %>)" class="btn btn-success" href="javascript:void(0)" ><i class="icon_check_alt2"></i></a>
                                	<% }else{  %>
                                	<a onclick="active(<%=objAds.getId() %>, <%=objAds.getActive() %>)" class="btn btn-warning" href="javascript:void(0)" ><i class="icon_upload"></i></a>
                                	<%} %>
                                	</li>
                                	
                                	<li><a class="btn btn-danger" onclick="return confirm('Bạn có muốn xóa không')" href="<%=request.getContextPath()%>/admin/ads/del?aid=<%=objAds.getId()%>"><i class="icon_close_alt2"></i></a></li>
                                 
                                  </ul>
                                  </div>
                                  </td>
                              </tr>
                              <script>
								function  active(id, active) {
									$.ajax({
										url: '<%=request.getContextPath()%>/admin/ads/active',
										type: 'POST',
										cache: false,
										data: {
												//Dữ liệu gửi đi
												aid : id,
												aactive : active,
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
                              <% 			}
                              			}
                              	} %>
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
				String urlSEOFirst = request.getContextPath()+"/admin/ads?page=1";
				String urlSEOLast = request.getContextPath()+"/admin/ads?page="+sumPage;
				String urlSEOPre = request.getContextPath()+"/admin/ads?page="+pre;
				String urlSEONext = request.getContextPath()+"/admin/ads?page="+next;
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
						String urlSEO = request.getContextPath()+"/admin/ads?page="+i;
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
					String urlSEO = request.getContextPath()+"/admin/ads?page="+i;if (i==1){
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
					String urlSEO = request.getContextPath()+"/admin/ads?page="+i;
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