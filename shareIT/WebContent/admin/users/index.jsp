<%@page import="model.dao.NewsDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/admin/inc/header.jsp" %>
<%@include file="/templates/admin/inc/sidebar.jsp" %>
     
      
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-user-md"></i> Profile</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="<%=request.getContextPath()%>/admin/index">Home</a></li>
						<li><i class="fa fa-user-md"></i><a href="<%=request.getContextPath()%>/admin/users">User</a></li>
					</ol>
				</div>
			</div>
			<div style="margin-left: 16px; margin-top: -14px; margin-bottom: 5px;">
					<a class="btn btn-success btn-sm" href="<%=request.getContextPath()%>/admin/users/add" title="Bootstrap 3 themes generator">Thêm</a>
					<form class="navbar-form" style="display: inline-block;" action="<%=request.getContextPath()%>/admin/users/search" method="get">
                            <input class="form-control" placeholder="Search" type="text" name="key">
                      </form>
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
			
		<%
      	if (request.getAttribute("listUsers") != null){
      		Users objUsersInfor= (Users) session.getAttribute("userInfor");
      		ArrayList<Users> listUsers = (ArrayList<Users>) request.getAttribute("listUsers");
      		if (listUsers.size()>0){
      			for (Users objUser: listUsers){
      				if (objUsersInfor.getId() != objUser.getId()){
      	
      %>
		<div>
              <div class="row">
                <!-- profile-widget -->
                <div class="col-lg-12">
                    <div class="profile-widget profile-widget-info">
                          <div class="panel-body">
                            <div class="col-lg-2 col-sm-2">
                              <h4><%=objUser.getFullname() %></h4>               
                              <div class="follow-ava">
                                  <img src="<%=request.getContextPath() %>/files/<%=objUser.getPicture() %>" alt="">
                              </div>
                              <h6><%=objUser.getUsername() %></h6>
                            </div>
                            <div class="col-lg-4 col-sm-4 follow-info">
                                <p><%=objUser.getCaption() %></p>
                                <p>@<%=objUser.getUsername() %></p>
                                <h6>
                                 <span><i class="icon_clock_alt"></i><%=objUser.getPhone()%></span>
                                 <span><i class="icon_pin_alt"></i><%=objUser.getAddress() %></span>
                                </h6>
                                
                            </div>
                            <div class="col-lg-3 col-sm-6 follow-info weather-category">
                                      <ul>
                                          <li class="active">
                                              <i class="fa fa-comments fa-2x"> </i><br>
                                              <%
                                              	NewsDAO newsDAO = new NewsDAO();
                                              	int numberNew = (Integer) newsDAO.countNewsByUserId(objUser.getId());
                                              %>
                                              Số tin đã đăng: <%=numberNew %>
                                          </li>
                                      </ul>
                            </div>
							<div class="col-lg-3 col-sm-6 follow-info weather-category">
                                      <ul>
                                          <li class="active">
                                              <i class="fa fa-bell fa-2x"> </i><br>
											  Trạng thái:  <%
                                  				if (objUser.getActive() ==1){
                                		 	%>
                                          Active
                                          <% }else { %>
                                          Block
                                          <%} %>
                                          </li>
                                      </ul>
                            </div>
                          </div>
                    </div>
                </div>
              </div>
              <!-- page start-->
              <div class="row">
                 <div class="col-lg-12">
                    <section class="panel">
                          <header class="panel-heading tab-bg-info">
                              <ul class="nav nav-tabs">
                             <%
                             	if ("admin".equals(objUsersInfor.getUsername())){ 
                             %>
                                  <li class="">
                                      <a href="<%=request.getContextPath()%>/admin/user/edit?uid=<%=objUser.getId() %>">
                                          <i class="icon-envelope"></i>
                                          Edit Profile
                                      </a>
                                  </li>
								  <li class="">
                                      <a href="<%=request.getContextPath()%>/admin/users/del?uid=<%=objUser.getId() %>" onclick="return confirm('Bạn có muốn xóa không')">
                                          <i class="icon-envelope"></i>
                                          Delete
                                      </a>
                                  </li>
                                   <li class="" id="active-<%=objUser.getId() %>">
                                      <a   onclick="active(<%=objUser.getId() %>,<%=objUser.getActive() %>)" href="javascript:void(0)" >
                                          <i class="icon-envelope"></i>
                                           <%
                                  				if (objUser.getActive() ==1){
                                		 	%>
                                          Block
                                          <% }else { %>
                                          Active
                                          <%} %>
                                      </a>
                                  </li>
                                  <%} %>
                                   <li class="">
                                      <a href="<%=request.getContextPath()%>/admin/users/mess?uid2=<%=objUser.getId() %>&uid1=<%=objUsersInfor.getId() %>" >
                                          <i class="icon-envelope"></i>
                                          Message
                                      </a>
                                  </li>
                              </ul>
                          </header>
                      </section>
                 </div>
              </div>
		</div>
		<script>
				function  active(id, active) {
					$.ajax({
						url: '<%=request.getContextPath()%>/admin/users/active',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								uid : id,
								uactive : active,
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
		<% }}}} %>
		
              <!-- page end-->
          </section>
           <div class="pagination" style="margin-left: 23px;   margin-top: -15px;">           
			<div class="numbers">
			<%
				if (request.getAttribute("sumPage")!= null){
				int current_page = (Integer) request.getAttribute("current_page");
				int sumPage = (Integer) request.getAttribute("sumPage");
				String active="";
				int pre = current_page-1;
				int next = current_page+1;
				String urlSEOFirst = request.getContextPath()+"/admin/users?page=1";
				String urlSEOLast = request.getContextPath()+"/admin/users?page="+sumPage;
				String urlSEOPre = request.getContextPath()+"/admin/users?page="+pre;
				String urlSEONext = request.getContextPath()+"/admin/users?page="+next;
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
						String urlSEO = request.getContextPath()+"/admin/users?page="+i;
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
					String urlSEO = request.getContextPath()+"/admin/users?page="+i;if (i==1){
						%>	
						<a <%=active %> style=" text-align: center;" href="<%=urlSEO%>"><%=i %></a>
						<%} else {%>
						<a <%=active %> style=" text-align: center;" href="<%=urlSEO%>">|<%=i %></a>
						<%
						}}}} 
				if (current_page>=(sumPage-3) && sumPage >3) { 
				   for (int i=sumPage-3; i<=sumPage;i++){
					if (current_page == i){
						active = "class='btn btn-success btn-xs'";
					}else {
						active ="";
					}
					String urlSEO = request.getContextPath()+"/admin/users?page="+i;
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