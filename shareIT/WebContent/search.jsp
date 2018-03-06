<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/public/inc/header.jsp" %>
        <!-- end header -->
        <!-- start div #main-title -->
        <div class="main-title">
            <p style="font-size: 77px;">Search</p>
        </div>
        <!-- end div #main-title -->
        
		<!-- start div #main -->
	    <div id="main">
            <div class="main-content">
            	<div class="left-container">
                	<div class="marked-title">
                	<%
                	String key = new String(request.getParameter("key").getBytes("ISO-8859-1"),"UTF-8");
                		int sumNew = (Integer) request.getAttribute("sumNew");
                	%>
                	<h3><span style="  color: white;  font-weight: bold; font-family: serif;" ><span>Có <%=sumNew %> kết quả tìm kiếm từ: <%=key %></span></span></h3>
                    </div>
                    <div class="row-fluid">
                        <div class="span1">
                        <%
                        if (request.getAttribute("listNew")!=null){
                    		ArrayList<News> listNew = (ArrayList<News>) request.getAttribute("listNew");
                    		for (News objNew : listNew){
                    			String urlSEONewSearch = request.getContextPath()+"/"+SlugUtil.makeSlug(objNew.getCat_name())+"/"+objNew.getCat_id()+"/"+SlugUtil.makeSlug(objNew.getName())+"/"+objNew.getId()+".html";
                        %>
                            <article class="small">
                                <div class="post-thumb">
                                    <a href="<%=urlSEONewSearch%>"><img src="<%=request.getContextPath() %>/files/<%=objNew.getPicture() %>" alt="<%=objNew.getName()%>"></a>
                                    <div class="overlay">
                                        <div class="op"></div>
                                        <div class="cat">
                                            <div class="icon"></div>
                                            Detail
                                        </div>
                                    </div>
                                </div>
                                <div class="cat-post-desc">
                                    <p class="date"><%=objNew.getDate_create() %> // <%=objNew.getCat_name() %></p>
                                    <h3><a href="<%=urlSEONewSearch%>"><%=objNew.getName() %></a></h3>
                                     <p><%=objNew.getPreview() %></p>
                                    <ul class="rating calc">
                                        <li class="active nr_1"><a href="javascript:void(0)"></a></li>
                                        <li class="active nr_2"><a href="javascript:void(0)"></a></li>
                                        <li class="active nr_3"><a href="javascript:void(0)"></a></li>
                                        <li class="nr_4"><a href="javascript:void(0)"></a></li>
                                        <li class="nr_5"><a href="javascript:void(0)"></a></li>
                                    </ul>
                                </div>
                            </article>
                            <%}} %>
                        </div>
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
				String urlSEOFirst = request.getContextPath()+"/tim-kiem/"+key+"/page/1.html";
				String urlSEOLast = request.getContextPath()+"/tim-kiem/"+key+"/page/"+sumPage+".html";
				String urlSEOPre = request.getContextPath()+"/tim-kiem/"+key+"/page/"+pre+".html";
				String urlSEONext = request.getContextPath()+"/tim-kiem/"+key+"/page/"+next+".html";
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
						String urlSEO = request.getContextPath()+"/tim-kiem/"+key+"/page/"+i+".html";
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
					String urlSEO = request.getContextPath()+"/tim-kiem/"+key+"/page/"+i+".html";
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
					String urlSEO = request.getContextPath()+"/tim-kiem/"+key+"/page/"+i+".html";
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