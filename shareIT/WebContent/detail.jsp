<%@page import="util.SlugUtil"%>
<%@page import="model.dao.CommentDAO"%>
<%@page import="model.bean.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/public/inc/header.jsp" %>
        <!-- end header -->
        
        <!-- start div #main-title -->
        <%
                		if(request.getAttribute("objNew")!= null){
                			News objNew = (News) request.getAttribute("objNew");
                			CommentDAO commentDAO = new CommentDAO();
                			int numberComMent = (Integer) commentDAO.countCommentByIdNews(objNew.getId());
                		
                	%>
        <div class="main-title">
            <p style="font-size: 77px;"><%=objNew.getCat_name() %></p>
        </div>
        <!-- end div #main-title -->
        
		<!-- start div #main -->
	    <div id="main">
            <div class="main-content">
            	<div class="left-container">
                	<article>
                	
                    	<div class="title-box">
                            <h1 style="text-transform: uppercase; font-weight: bold; font-family: serif; font-size: 30px; line-height:40px; "><%=objNew.getName() %></h1>
                        </div>
                        <div class="post-thumb">
                        	<img src="<%=request.getContextPath() %>/files/<%=objNew.getPicture() %>" alt="">
                            <ul class="rating calc">
                                <li class="active nr_1"><a href="javascript:void(0)"></a></li>
                                <li class="active nr_2"><a href="javascript:void(0)"></a></li>
                                <li class="active nr_3"><a href="javascript:void(0)"></a></li>
                                <li class="nr_4"><a href="javascript:void(0)"></a></li>
                                <li class="nr_5"><a href="javascript:void(0)"></a></li>
                            </ul>
                        </div>
                        <div class="post-info">
                            <ul>
                                <li style="font-family: serif; text-transform: uppercase; font-weight: bold;"><%=objNew.getCat_name() %><br><span>category</span></li>
                                <li><%=numberComMent %><br><span>Comments</span></li>
                                <li><%=objNew.getDate_create().getDate() %><br><span><%=objNew.getDate_create().getMonth() %></span></li>
                            </ul>
                            <div class="clear"></div>    
                        </div>
                        <div class="post-content">
                        	<p ><h5 style="font-weight: bold; font-family: serif; "><%=objNew.getPreview() %></h5></p>
                            <p><%=objNew.getDetail() %></p>
                            <div class="marked-title first">
                                <h3>you may also want to read</h3>
                            </div>
                            <div class="row-fluid">
                            <%
                            	if(request.getAttribute("listNewsAdd")!= null){
                            		ArrayList<News> listNewsAdd = (ArrayList<News>) request.getAttribute("listNewsAdd");
                            		 for (News objNewADD : listNewsAdd){
                            			 String urlSEOAdd = request.getContextPath()+"/"+SlugUtil.makeSlug(objNewADD.getCat_name())+"/"+objNewADD.getCat_id()+"/"+SlugUtil.makeSlug(objNewADD.getName())+"/"+objNewADD.getId()+".html";
                            			 %>
                                <div class="span4">
                                    <article class="small single">
                                        <div class="post-thumb">
                                            <a href="<%=urlSEOAdd%>"><img src="<%=request.getContextPath() %>/files/<%=objNewADD.getPicture() %>" alt="<%=objNewADD.getName()%>"></a>
                                            <div class="overlay">
                                                <div class="op"></div>
                                                <div class="cat">
                                                    <div class="icon"></div>
                                                    Detail
                                                </div>
                                            </div>
                                        </div>
                                        <div class="cat-post-desc">
                                            <p class="date"><%=objNewADD.getDate_create() %></p>
                                            <h3><a href="<%=urlSEOAdd%>"><%=objNewADD.getName() %></a></h3>
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
                                		ArrayList<Comment> listComment = (ArrayList<Comment>) request.getAttribute("listComment");
                                		for(Comment objComment: listComment){
                                			if (objComment.getParent_id() ==0 && objComment.getActive()==1){
                                %>
                                <ul class="level-1">
                                	<li>
                                        <span class="comment">
                                            <a href="javascript:void(0)" onclick="showBoxComment(<%=objComment.getId() %>,<%=objNew.getId() %>,<%=objComment.getId() %>)" class="reply">Reply</a>
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
                                        	ArrayList<Comment> listCommentChil = (ArrayList<Comment>) commentDAO.getItemsForPublicByParent(objComment.getNews_id(), objComment.getId());
												if (listCommentChil.size()>0){
													for (Comment objCommentChil : listCommentChil){
														if (objCommentChil.getActive() ==1){
                                        	%>
                                            <li>
                                                <span class="comment">
                                                    <a href="javascript:void(0)" onclick="showBoxComment(<%=objCommentChil.getId() %>,<%=objNew.getId() %>,<%=objComment.getId() %>)" class="reply" >Reply</a>
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
                                    <h3>Leave a comment</h3>
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
                                        <button class="btn btn-icon submit" type="submit"><span class="icon"></span><a href="javascript:void(0)" onclick="comment(<%=objNew.getId() %>)" style="color: white;">Comment</a></button>
                                    </form>
                                    <div class="clear"></div>
                                </div>
                            </div>
                            </div>
                        </div>
                    </article>
                    <div class="clear"></div>
                </div>
                <%} %>
                <%@include file="/templates/public/inc/rightside.jsp" %>
                <div class="clear"></div>
            </div>	
        </div>
	    <!-- end div #main -->
    		<script>
				function  showBoxComment(idComment,idNew,idParent ) {
					$.ajax({
						url: '<%=request.getContextPath()%>/comment/reply/show',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
							cidComment	:idComment,
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
				function  comment(idNew) {
					var name = $('#name_comment').val();
					var email = $('#email_comment').val();
					var website = $('#website_comment').val();
					var content = $('#content_comment').val();
					$.ajax({
						url: '<%=request.getContextPath()%>/comment',
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
				function  reply(idComment,idNew,idParent) {
					var name = $('#name_reply').val();
					var email = $('#email_reply').val();
					var website = $('#website_reply').val();
					var content = $('#content_reply').val();
					$.ajax({
						url: '<%=request.getContextPath()%>/comment/reply',
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
    </div>
	<!-- end div #wrapper -->    
    
    <!-- start footer -->
     <%@include file="/templates/public/inc/footer.jsp" %>