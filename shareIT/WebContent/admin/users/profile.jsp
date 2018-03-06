<%@page import="model.dao.NewsDAO"%>
<%@page import="model.dao.UserDAO"%>
<%@page import="model.bean.Message"%>
<%@page import="model.dao.MessageDAO"%>
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
						<li><i class="fa fa-table"></i><a href="<%=request.getContextPath()%>/admin/users/profile">Profile</a></li>
						<!-- <li><i class="fa fa-th-list"></i>Basic Table</li> -->
					</ol>
				</div>
			</div>
			<%
				if (session.getAttribute("userInfor")!=null){
					objUserInfor = (Users) session.getAttribute("userInfor");
			%>
			  <div class="row">
                <!-- profile-widget -->
                <div class="col-lg-12">
                    <div class="profile-widget profile-widget-info">
                          <div class="panel-body">
                            <div class="col-lg-3 col-sm-3">
                              <h4><%=objUserInfor.getFullname() %></h4>               
                              <div class="follow-ava">
                                  <img style="width: 90px; height: 90px;" src="<%=request.getContextPath() %>/files/<%=objUserInfor.getPicture() %>" alt="<%=objUserInfor.getUsername()%>">
                              </div>
                            </div>
                            <div class="col-lg-6 col-sm-6 follow-info">
                                <p><%=objUserInfor.getCaption() %></p>
                                <p>@<%=objUserInfor.getUsername() %></p>
								<p><i class="fa fa-twitter">jenifertweet</i></p>
                                <h6>
                                    <span><i class="icon_calendar"></i><%=objUserInfor.getPhone() %></span>
                                    <span><i class="icon_pin_alt"></i><%=objUserInfor.getAddress() %></span>
                                </h6>
                            </div>
                             <div class="col-lg-3 col-sm-6 follow-info weather-category">
                                      <ul>
                                          <li class="active">
                                              
                                              <i class="fa fa-comments fa-2x"> </i><br>
                                              <%
											  NewsDAO newsDAO = new NewsDAO();
                                              int numNew = newsDAO.countNewsByUserId(objUserInfor.getId());
											  %>
											  Số tin đã tạo: <%=numNew %>
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
                                  <li class="active">
                                      <a data-toggle="tab" href="#recent-activity">
                                          <i class="icon-home"></i>
                                         Message
                                      </a>
                                  </li>
                                 <li class="">
                                      <a href="<%=request.getContextPath()%>/admin/user/edit?uid=<%=objUserInfor.getId()%>">
                                          <i class="icon-envelope"></i>
                                          Edit Profile
                                      </a>
                                  </li>
                              </ul>
                          </header>
                          <div class="panel-body">
                              <div class="tab-content">
                                  <div id="recent-activity" class="tab-pane active">
                                      <div class="profile-activity"> 
                                                                               
                                         <div class="row current-chat-area">
							                <div class="col-md-12">
							                      <ul class="media-list">
							                      <%
							                      if(request.getAttribute("listUser")!=null){
													ArrayList<Users> listUser = (ArrayList<Users>) request.getAttribute("listUser");	
													for (Users objUsers : listUser){
							                      	MessageDAO messageDAO = new MessageDAO();
							                      	Message objMess = (Message) messageDAO.getItem(objUsers.getId(), objUserInfor.getId());
							                      	if(objMess != null){
							                      %>
													<li class="media">
							                            <div class="media-body">
							                              <a href="<%=request.getContextPath() %>/admin/users/mess?uid1=<%=objUserInfor.getId() %>&uid2=<%=objUsers.getId() %>" style="color: black;">
							                                <div class="media">
							                                    <div class="pull-left" >
							                                        <img style="width: 45px; height: 45px;" class="media-object img-circle" src="<%=request.getContextPath()%>/files/<%=objUsers.getPicture()%>">
							                                    </div>
							                                    <div class="media-body">
							                                        <%=objMess.getContent() %>
							                                        <br>
							                                       <small class="text-muted"><%=objUsers.getFullname() %> | <%=objMess.getDate_create() %></small>
							                                        <hr>
							                                    </div>
							                                </div>
							                                </a>
							                            </div>
							                        </li>
							                        
							                        <% }}} %>
							                    </ul>  
							                </div>
							            </div>
							               </div>
							            </div>
                                      </div>
                                  </div>
                                  </section>
                              </div>
                           </div>
                           <%} %>
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