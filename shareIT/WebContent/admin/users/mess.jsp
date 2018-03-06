<%@page import="model.bean.Message"%>
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
						<li><i class="fa fa-table"></i><a href="<%=request.getContextPath()%>/admin/users/profile">Profile</a></li>
						<!-- <li><i class="fa fa-th-list"></i>Basic Table</li> -->
					</ol>
				</div>
			</div>
		<div class="col-md-12 current-chat">
            <div class="row current-chat-area">
                <div class="col-md-12">
                      <ul class="media-list">
                      <%
                      	if (request.getAttribute("listMess")!= null){
                      		objUserInfor = (Users) session.getAttribute("userInfor");
                      		Users objUser2 = (Users) request.getAttribute("objUser2");
                      		ArrayList<Message> listMess = (ArrayList<Message>) request.getAttribute("listMess");
                      		for (Message objMessage : listMess){
                      %>
                        <li class="media">
                            <div class="media-body">
                                <div class="media">
                                    <a class="pull-left" href="#">
                                    <%
                                    	if(objMessage.getId_sent() == objUserInfor.getId()){
                                    %>
                                        <img style="width: 45px; height: 45px;" class="media-object img-circle " src="<%=request.getContextPath()%>/files/<%=objUserInfor.getPicture()%>">
                                  <%} else { %>
                                  	 <img style="width: 45px; height: 45px;" class="media-object img-circle " src="<%=request.getContextPath()%>/files/<%=objUser2.getPicture()%>">
                                  <%} %>
                                    </a>
                                    <div class="media-body">
                                       <%=objMessage.getContent() %>
                                        <br>
                                        <%
                                    	if(objMessage.getId_sent() == objUserInfor.getId()){
                                    %>
                                    <small class="text-muted"><%=objUserInfor.getFullname() %> | <%=objMessage.getDate_create() %></small>
                                  <%} else { %>
                                  <small class="text-muted"><%=objUser2.getFullname() %> | <%=objMessage.getDate_create() %></small>
                                  <%} %>
                                        <hr>
                                    </div>
                                </div>
                            </div>
                        </li>
                         <%	}
                      	} %>
                      	 <li class="media" id="nextChat">
                        </li>
                    </ul>  
                </div>
            </div>
            <div class="row current-chat-footer">
            <div class="panel-footer">
            <%
            	if(request.getAttribute("objUser2")!= null){
            	 objUserInfor = (Users) session.getAttribute("userInfor");
              		Users objUser2 = (Users) request.getAttribute("objUser2");
            %>
                <div class="input-group">
                  <input type="text" class="form-control" id="textChat">
                  <span class="input-group-btn">
                    <a href="javascript:void(0)" class="btn btn-success" onclick="doChat()">Send</a>
                  </span>
                </div>
                </div>
            </div>
		</div>
		<script>
				function  doChat() {
					var textChat = $('#textChat').val();
					var uid1= <%=objUserInfor.getId() %>;
					var uid2 = <%=objUser2.getId() %>;
					$.ajax({
						url: '<%=request.getContextPath()%>/admin/users/mess',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								uuid1 : uid1,
								uuid2 : uid2,
								utextChat : textChat,
								},
						success: function(data){
							// Xử lý thành công
							$('#nextChat').html(data);
						},
						error: function (){
						// Xử lý nếu có lỗi
						alert('Loi');
						}
					});
				}
			</script>
			<%} %>
              <!-- page end-->
          </section>
      </section>
      
      <!--main content end-->
   <%@include file="/templates/admin/inc/footer.jsp" %>  