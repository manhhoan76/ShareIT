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
                        if (request.getAttribute("listVideo")!=null){
                    		ArrayList<Video> listVideo = (ArrayList<Video>) request.getAttribute("listVideo");
                    		for (Video objVideo : listVideo){
                    			String urlSEOVideoChil = request.getContextPath()+"/video/"+SlugUtil.makeSlug(objVideo.getName())+"/"+objVideo.getId()+".html";
                        %>
                        <div class="span1" style="margin-left: 0px;">
                        
                            <article class="small">
                                <div class="post-thumb">
                                    <a href="<%=urlSEOVideoChil%>"><iframe  src="<%=objVideo.getLink() %>" frameborder="0" allowfullscreen></iframe></a>
                                    <div class="overlay">
                                        <div class="op"></div>
                                        <div class="cat">
                                            <div class="icon"></div>
                                            Detail
                                        </div>
                                    </div>
                                </div>
                                <div class="cat-post-desc">
                                    <p class="date"><%=objVideo.getDate_create() %> </p>
                                    <h3><a href="<%=urlSEOVideoChil%>"><%=objVideo.getName() %></a></h3>
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
                    <div class="clear"></div>
                    <div class="post-navi">
                        <div class="numbers">
			<%
				if (request.getAttribute("sumPage")!= null){
				int current_page = (Integer) request.getAttribute("current_page");
				int sumPage = (Integer) request.getAttribute("sumPage");
				String active="";
				int pre = current_page-1;
				int next = current_page+1;
				String urlSEOFirst = request.getContextPath()+"/video/1.html";
				String urlSEOLast = request.getContextPath()+"/video/"+sumPage+".html";
				String urlSEOPre = request.getContextPath()+"/video/"+pre+".html";
				String urlSEONext = request.getContextPath()+"/video/"+next+".html";
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
						String urlSEO = request.getContextPath()+"/video/"+i+".html";
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
					String urlSEO = request.getContextPath()+"/video/"+i+".html";
					if (i==1){
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
					String urlSEO = request.getContextPath()+"/video/"+i+".html";
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
                  
                </div>
                  <%@include file="/templates/public/inc/rightside.jsp" %>
                <div class="clear"></div>
            </div>	
        </div>
	    <!-- end div #main -->
  
    </div>
	<!-- end div #wrapper -->    
    
    <!-- start footer -->
      <%@include file="/templates/public/inc/footer.jsp" %>