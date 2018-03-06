<%@page import="model.dao.CommentVideoDAO"%>
<%@page import="model.bean.CommentVideo"%>
<%@page import="model.bean.Video"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/public/inc/header.jsp" %>
        <!-- end header -->
        <!-- start div #main-title -->
        <div class="main-title">
            <p style="font-size: 77px;">Video</p>
        </div>
        <!-- end div #main-title -->
        
		<!-- start div #main -->
	    <div id="main">
            <div class="main-content">
            	<div class="left-container">
                	<div class="marked-title">
                	<h3><span style="  color: white;  font-weight: bold; font-family: serif;" ><span>Video</span></span></h3>
                    </div>
                    <div class="row-fluid">
                    <%
                        if (request.getAttribute("objVideo")!=null){
                        	Video objVideo = (Video) request.getAttribute("objVideo");
                        	String urlSEOVideoDetail = request.getContextPath()+"/video/"+SlugUtil.makeSlug(objVideo.getName())+"/"+objVideo.getId()+".html";
                        %>
                        <div class="span1">
                            <article>
									<iframe style="width: 100%; height: 400px;" src="<%=objVideo.getLink() %>"  frameborder="0" allowfullscreen></iframe>                                    <div class="overlay">
                                    <ul class="rating calc">
                                        <li class="active nr_1"><a href="javascript:void(0)"></a></li>
                                        <li class="active nr_2"><a href="javascript:void(0)"></a></li>
                                        <li class="active nr_3"><a href="javascript:void(0)"></a></li>
                                        <li class="nr_4"><a href="javascript:void(0)"></a></li>
                                        <li class="nr_5"><a href="javascript:void(0)"></a></li>
                                    </ul>
                                </div>
                                <div class="cat-post-desc">
                                    <h3><a href="<%=urlSEOVideoDetail%>"><%=objVideo.getName() %></a></h3>
                                    <p class="date"><%=objVideo.getDate_create() %>  // 45 Comments</p>
                                </div>
                            </article>
                        </div>
                    
                    </div>
                    <div class="clear"></div>
                    <div class="post-navi">
			<div style="clear: both;"></div> 
                    </div>
                      <div class="marked-title first">
                                <h3>you may also want to read</h3>
                            </div>
                            <div class="row-fluid">
                            <%
                            	if(request.getAttribute("listVideoAdd")!= null){
                            		ArrayList<Video> listVideoAdd = (ArrayList<Video>) request.getAttribute("listVideoAdd");
                            		 for (Video objVideoAdd : listVideoAdd){
                            			 String urlSEOVideoDetailAdd = request.getContextPath()+"/video/"+SlugUtil.makeSlug(objVideoAdd.getName())+"/"+objVideoAdd.getId()+".html";
                            %>
                                <div class="span4">
                                    <article class="small single">
                                        <div class="post-thumb">
                                        	 <a href="<%=urlSEOVideoDetailAdd%>"><iframe style="width: 162px;" src="<%=objVideoAdd.getLink() %>" frameborder="0" allowfullscreen></iframe></a>
                                            <div class="overlay">
                                                <div class="op"></div>
                                                <div class="cat">
                                                    <div class="icon"></div>
                                                    Detail
                                                </div>
                                            </div>
                                        </div>
                                        <div class="cat-post-desc">
                                            <p class="date"><%=objVideoAdd.getDate_create() %></p>
                                            <h3><a href="<%=urlSEOVideoDetailAdd%>"><%=objVideoAdd.getName() %></a></h3>
                                            <ul class="rating calc">
                                                <li class="active nr_1"><a href="javascript:void(0)"></a></li>
                                                <li class="active nr_2"><a href="javascript:void(0)"></a></li>
                                                <li class="active nr_3"><a href="javascript:void(0)"></a></li>
                                                <li class="nr_4"><a href="javascript:void(0)"></a></li>
                                                <li class="nr_5"><a href="javascript:void(0)"></a></li>
                                            </ul>
                                        </div>
                                    </article>    
                                </div>
                                <%}} %>
                               
                            </div>
                    <div class="comments">
                            <%
                            	if(request.getAttribute("numberComment") != null){
                            		int numberComment = (Integer) request.getAttribute("numberComment");
                            	
                            %>
                            	<div class="marked-title">
                                    <h3><%=numberComment %> comments</h3>
                                </div>	
                                <%} %>
                                <%
                                	if (request.getAttribute("listComment")!= null){
                                		ArrayList<CommentVideo> listComment = (ArrayList<CommentVideo>) request.getAttribute("listComment");
                                		for(CommentVideo objComment: listComment){
                                			if (objComment.getParent_id() ==0 && objComment.getActive()==1){
                                %>
                                <ul class="level-1">
                                	<li>
                                        <span class="comment">
                                            <a href="javascript:void(0)" onclick="showBoxCommentVideo(<%=objComment.getId() %>,<%=objVideo.getId() %>,<%=objComment.getId() %>)" class="reply">Reply</a>
                                            <span class="date">
                                                <span><%=objComment.getDate_create().getDay() %><br><%=objComment.getDate_create().getMonth() %>/<%=objComment.getDate_create().getYear() %></span>
                                            </span>
                                            <span class="content"><span class="title"><b><%=objComment.getName() %></b>, wrote:</span><br><%=objComment.getContent() %></span>
                                            <span class="clear"></span>
                                        </span>
                                         <ul id="reply-<%=objComment.getId() %>" style="margin-left : -75px;">
                              				 </ul>
                                        <ul class="level-2">
                                        <%
                                        CommentVideoDAO commentVideoDAO = new CommentVideoDAO();
                                        	ArrayList<CommentVideo> listCommentChil = (ArrayList<CommentVideo>) commentVideoDAO.getItemsForPublicByParent(objComment.getVideo_id(), objComment.getId());
												if (listCommentChil.size()>0){
													for (CommentVideo objCommentChil : listCommentChil){
														if (objCommentChil.getActive() ==1){
                                        	%>
                                            <li>
                                                <span class="comment">
                                                    <a href="javascript:void(0)" onclick="showBoxCommentVideo(<%=objCommentChil.getId() %>,<%=objVideo.getId() %>,<%=objComment.getId() %>)" class="reply" >Reply</a>
                                                    <span class="date">
                                                        <span><%=objCommentChil.getDate_create().getDay() %><br><%=objCommentChil.getDate_create().getMonth() %>/<%=objCommentChil.getDate_create().getYear() %></span>
                                                    </span>
                                                    <span class="content"><span class="title"><b><%=objCommentChil.getName() %></b>, wrote:</span><br><%=objCommentChil.getContent() %></span>
                                                    <span class="clear"></span>
                                                </span>
                                            </li>
                                            <li id="reply-<%=objCommentChil.getId() %>" style="margin-left: -150px;">
                               			</li>
                                        <%} else {
                                       %>
                                         <li>
                                                <span class="comment">
                                                    <span class="content"><span class="title"><b><%=objCommentChil.getName() %></b>, wrote:</span><br>Comment đang được xem xét</span>
                                                    <span class="clear"></span>
                                                </span>
                                            </li>
                                        <% } }} %>
                                    	
                                        </ul>
                                    </li>
                                   
                                </ul>
                                <%} else {
                                	if (objComment.getParent_id() ==0 && objComment.getActive()!=1){
                                %>
                                	 <ul class="level-1">
                                	<li>
                                        <span class="comment">
                                            <span class="content"><span class="title"><b><%=objComment.getName() %></b>, wrote:</span><br>Comment đang được xem xét</span>
                                            <span class="clear"></span>
                                        </span>
                                        </li>
                                        </ul>
                               <%}}}} %>
                            </div>
                  		 <div id="comment-alert">
                            <div class="contact-content">
                                <div class="marked-title">
                                    <h3>Comment</h3>
                                </div>
                                <div class="contact-form" >
                                    <form >
                                        <div class="top-form">
                                            <span class="parent name">
                                                <input class="field" type="text" name="" id="name_comment" placeholder="Your Name">
                                                <span class="icon"></span>    
                                            </span>
                                            <span class="parent email">
                                                <input class="field" id="email_comment" type="text" name="" placeholder="Your Email">
                                                <span class="icon"></span>    
                                            </span>
                                            <span class="parent web last">
                                                <input class="field" type="text" id="website_comment" name="" placeholder="Your Website">
                                                <span class="icon"></span>    
                                            </span>
                                            <div class="clear"></div>
                                        </div>
                                        <div class="bottom-form">
                                            <textarea id="content_comment" placeholder="Your Comment"></textarea>
                                        </div>
                                        <button class="btn btn-icon submit" type="submit"><span class="icon"></span><a href="javascript:void(0)" onclick="commentVideo(<%=objVideo.getId() %>)" style="color: white;">Comment</a></button>
                                    </form>
                                    <div class="clear"></div>
                                </div>
                            </div>
                            </div>
                </div>
                 <%} %>
                  <%@include file="/templates/public/inc/rightside.jsp" %>
                <div class="clear"></div>
            </div>	
        </div>
	    <!-- end div #main -->
  
    </div>
	<!-- end div #wrapper -->    
    <script>
				function  showBoxCommentVideo(idComment,idNew,idParent ) {
					$.ajax({
						url: '<%=request.getContextPath()%>/commentVideo/reply/show',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								cidComment:idComment,
							cidParent : idParent,
							cidNew : idNew,
								},
						success: function(data){
							// Xử lý thành công
							$('#reply-'+idComment).html(data);
						},
						error: function (){
						// Xử lý nếu có lỗi
						alert('Loi');
						}
					});
				}
		</script>
    	<script>
				function  commentVideo(idNew) {
					var name = $('#name_comment').val();
					var email = $('#email_comment').val();
					var website = $('#website_comment').val();
					var content = $('#content_comment').val();
					$.ajax({
						url: '<%=request.getContextPath()%>/commentVideo',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								cnam:name,
								cemail: email,
								cwebsite: website,
								ccontent: content,
								cidNew : idNew,
								},
						success: function(data){
							// Xử lý thành công
							$('#comment-alert').html(data);
						},
						error: function (){
						// Xử lý nếu có lỗi
						alert('Loi');
						}
					});
				}
		</script>
		<script>
				function  replyVideo(idComment,idNew,idParent) {
					var name = $('#name_reply').val();
					var email = $('#email_reply').val();
					var website = $('#website_reply').val();
					var content = $('#content_reply').val();
					$.ajax({
						url: '<%=request.getContextPath()%>/commentVideo/reply',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								cnam:name,
								cemail: email,
								cwebsite: website,
								ccontent: content,
								cidParent : idParent,
								cidNew : idNew,
								},
						success: function(data){
							// Xử lý thành công
							$('#reply-'+idComment).html(data);
						},
						error: function (){
						// Xử lý nếu có lỗi
						alert('Loi');
						}
					});
				}
		</script>
    <!-- start footer -->
      <%@include file="/templates/public/inc/footer.jsp" %>