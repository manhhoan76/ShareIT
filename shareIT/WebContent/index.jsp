<%@page import="util.SlugUtil"%>
<%@page import="model.dao.CommentDAO"%>
<%@page import="model.bean.Ads"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/public/inc/header.jsp" %>
        <!-- start slider -->
        <div class="cn_wrapper" >
            <div id="cn_preview"  class="cn_preview">
              <div id="wowslider-container1">
				<div class="ws_images"><ul>
				<%
            	if (request.getAttribute("listSlide")!= null){
            		ArrayList<News> listSlide = (ArrayList<News>) request.getAttribute("listSlide");
            		int i=0;
            			for (News objNewsSlide : listSlide){
            				String urlSEO = request.getContextPath()+"/"+SlugUtil.makeSlug(objNewsSlide.getCat_name())+"/"+objNewsSlide.getCat_id()+"/"+SlugUtil.makeSlug(objNewsSlide.getName())+"/"+objNewsSlide.getId()+".html";
            				%>
								<li><a href="<%=urlSEO%>"><img style="width: 1200px; height: 550px;" src="<%=request.getContextPath()%>/files/<%=objNewsSlide.getPicture() %>"  title="<%=objNewsSlide.getName() %>" id="wows1_<%=i%>"/></a></li>
							<% i = i +1; }} %>
							</ul></div>
							<div class="ws_bullets"><div>
							<%
				            	if (request.getAttribute("listSlide")!= null){
				            		ArrayList<News> listSlide = (ArrayList<News>) request.getAttribute("listSlide");
				            		int i=0;
				            			for (News objNewsSlide : listSlide){
				            				String urlSEO = request.getContextPath()+"/"+SlugUtil.makeSlug(objNewsSlide.getCat_name())+"/"+objNewsSlide.getCat_id()+"/"+SlugUtil.makeSlug(objNewsSlide.getName())+"/"+objNewsSlide.getId()+".html";
								%>
								<a href="<%=urlSEO%>" title="<%=objNewsSlide.getName()%>"><span style="font-family: serif;"><img style="width: 90px; height: 50px;" src="<%=request.getContextPath() %>/files/<%=objNewsSlide.getPicture() %>" alt="<%=objNewsSlide.getName()%>"/><%=i %></span></a>
							<% i= i+1;}} %>
							</div></div><div class="ws_script" style="position:absolute;left:-99%"><a href="http://wowslider.net">javascript slideshow</a> by WOWSlider.com v8.8</div>
							<div class="ws_shadow"></div>
							</div>	
							<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/engine1/wowslider.js"></script>
							<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/engine1/script.js"></script>
				</div>	
            
        </div>
        <!-- end slider -->
        
		<!-- start div #main -->
	    <div id="main">
            <div class="main-content">
                <div class="left-container">
                <%		
                			for (Category objCat : listCat){
                				if (objCat.getParent_id() ==0 && objCat.getShow_index()==1){
                %>
                	<div class="row-fluid">
                        <div class="marked-title">
                            <h3><a style="color: white; text-transform: uppercase; font-family: serif;" href="<%=request.getContextPath()%>/cat?cid=<%=objCat.getId()%>"><%=objCat.getName() %></a></h3>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span2">
                        <%
                        	NewsDAO newsDAO = new NewsDAO();
                        	News objNews = (News) newsDAO.getItemCatParent(objCat.getId());
                        	if(objNews != null){
                        		CommentDAO commentDAO = new CommentDAO();
                        		int numberComment = (Integer) commentDAO.countCommentByIdNews(objNews.getId());
                        		String urlSEO = request.getContextPath()+"/"+SlugUtil.makeSlug(objNews.getCat_name())+"/"+objNews.getCat_id()+"/"+SlugUtil.makeSlug(objNews.getName())+"/"+objNews.getId()+".html";
                        	
                        %>
                            <article>
                                <div class="post-thumb">
                                    <a href="<%=urlSEO%>"><img src="<%=request.getContextPath() %>/files/<%=objNews.getPicture() %>" alt=""></a>
                                    <div class="overlay">
                                        <div class="op"></div>
                                        <div class="cat">
                                            <div class="icon"></div>
                                            Detail
                                        </div>
                                    </div>
                                    <ul class="rating calc">
                                        <li class="active nr_1"><a href="javascript:void(0)"></a></li>
                                        <li class="active nr_2"><a href="javascript:void(0)"></a></li>
                                        <li class="active nr_3"><a href="javascript:void(0)"></a></li>
                                        <li class="nr_4"><a href="javascript:void(0)"></a></li>
                                        <li class="nr_5"><a href="javascript:void(0)"></a></li>
                                    </ul>
                                </div>
                                <div class="cat-post-desc">
                                    <h3><a href="<%=urlSEO%>"><%=objNews.getName() %></a></h3>
                                    <p class="date"><%=objNews.getDate_create() %>  // <%=objNews.getView() %> View  // <%=numberComment %> Comment</p>
                                    <p style="font-size: 12px;"><%=objNews.getPreview() %></p>
                                </div>
                            </article>
                            <%} %>
                        </div>
                        <div class="span2">
                        <%
                        	ArrayList<News> listNews = (ArrayList<News>) newsDAO.getItemsByIDCatParent(objCat.getId(), 1, 3);
                        		if(listNews.size()>0){
                        			for (News objNewsInclu: listNews){
                        				String urlSEOInClu = request.getContextPath()+"/"+SlugUtil.makeSlug(objNewsInclu.getCat_name())+"/"+objNewsInclu.getCat_id()+"/"+SlugUtil.makeSlug(objNewsInclu.getName())+"/"+objNewsInclu.getId()+".html";
                        %>
                            <article class="twoboxes">
                                <div class="post-thumb">
                                    <a href="<%=urlSEOInClu%>"><img src="<%=request.getContextPath() %>/files/<%=objNewsInclu.getPicture() %>" alt=""></a>
                                    <div class="overlay">
                                        <div class="op"></div>
                                        <div class="cat">
                                            <div class="icon"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="right-desc">
                                    <h3><a href="<%=urlSEOInClu%>"><%=objNewsInclu.getName() %></a></h3>
                                    <ul class="rating calc">
                                        <li class="active nr_1"><a href="javascript:void(0)"></a></li>
                                        <li class="active nr_2"><a href="javascript:void(0)"></a></li>
                                        <li class="active nr_3"><a href="javascript:void(0)"></a></li>
                                        <li class="nr_4"><a href="javascript:void(0)"></a></li>
                                        <li class="nr_5"><a href="javascript:void(0)"></a></li>
                                    </ul>
                                    <div class="clear"></div>
                                    <p class="date"><%=objNewsInclu.getDate_create() %></p>    
                                </div>
                                <div class="clear"></div>
                            </article>
                           <%}} %>
                        </div>
                    </div>
                    <%}} %>
                    <div class="clear"></div>
                </div>
				<%@include file="/templates/public/inc/rightside.jsp" %>
                <div class="clear"></div>
            </div>	
			 <div class="sponsers">
				<div class="sponinner">
					<div class="sponshead">
						<h5 class="colr bold">Advertisement</h5>
					</div>
					<div class="spnscrousal">
						<div class="carouselsec_wrapp">
							<ul>
							<%
								if(request.getAttribute("listAds")!= null){
									ArrayList<Ads> listAdsShow = (ArrayList<Ads>) request.getAttribute("listAds");
									for (Ads objAds : listAdsShow){
							%>
								<li>
									<a href="<%=objAds.getLink()%>"><img src="<%=request.getContextPath() %>/files/<%=objAds.getPicture() %>" alt="<%=objAds.getName()%>" /></a>
								</li>
								<% }} %>
							</ul>
						</div>
					</div>
				</div>
			</div>
			 <div class="clear"></div>
        </div>
	    <!-- end div #main -->
       </div>
	   
	<!-- end div #wrapper -->    
    
    <!-- start footer -->
   <%@include file="/templates/public/inc/footer.jsp" %>