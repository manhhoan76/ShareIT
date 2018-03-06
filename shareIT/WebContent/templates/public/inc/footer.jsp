<%@page import="util.SlugUtil"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.NewsDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<footer>
        <div class="footer-top">
            <div class="logo">
            <%
            String urlSEOIndex2 = request.getContextPath()+"/trang-chu.html";
            %>
                <a href="<%=urlSEOIndex2%>"><img src="<%=request.getContextPath() %>/templates/public/img/logo.png" alt=""></a>
                <span class="border-bottom"></span>
            </div>
            <div class="widget first">
                <div class="desc">
                    <p>Trang web được thực hiện bởi: Nguyễn Mạnh Hoan</p>
                    <p>Mọi thông tin xin liên hệ bên dưới</p>
                </div>
                <ul class="info">
                    <li class="address">Hòa Minh, Liên Chiểu, Đà Nẵng</li>
                    <li class="phone">(+84) 0981 615 773</li>
                    <li class="mail"><a href="mailto:manhhoan76.cntt@gmail.com">manhhoan76.cntt@gmail.com</a></li>
                </ul>
            </div>
            <div class="widget">
                <div class="title">
                    <h3>latest news</h3>
                </div>
                <div class="news">
                <%
                	NewsDAO newsDAO = new NewsDAO();
                	ArrayList<News> listNewLast = (ArrayList<News>) newsDAO.getItemsLast();
                	if (listNewLast.size()>0){
                		for(News objNewsLast : listNewLast){
                			String urlSEOLast = request.getContextPath()+"/"+SlugUtil.makeSlug(objNewsLast.getCat_name())+"/"+objNewsLast.getCat_id()+"/"+SlugUtil.makeSlug(objNewsLast.getName())+"/"+objNewsLast.getId()+".html";
                %>
                    <article class="twoboxes">
                        <div class="post-thumb">
                            <a href="<%=urlSEOLast%>"><img style="width: 112px; height: 57px;" src="<%=request.getContextPath() %>/files/<%=objNewsLast.getPicture() %>" alt=""></a>
                            <div class="overlay">
                                <div class="op"></div>
                                <div class="cat">
                                    <div class="icon"></div>
                                </div>
                            </div>
                        </div>
                        <div class="right-desc">
                            <h3><a href="<%=urlSEOLast%>"><%=objNewsLast.getName() %></a></h3>
                            <ul class="rating calc">
                                <li class="active nr_1"><a href="javascript:void(0)"></a></li>
                                <li class="active nr_2"><a href="javascript:void(0)"></a></li>
                                <li class="active nr_3"><a href="javascript:void(0)"></a></li>
                                <li class="nr_4"><a href="javascript:void(0)"></a></li>
                                <li class="nr_5"><a href="javascript:void(0)"></a></li>
                            </ul>
                            <div class="clear"></div>
                            <p class="date"><%=objNewsLast.getDate_create() %></p>    
                        </div>
                        <div class="clear"></div>
                    </article>
                    <%}} %>
                    
                </div>
            </div>
            <div class="widget last">
                <div class="title">
                    <h3>most popular</h3>
                </div>
                <div class="news">
                <%
                	ArrayList<News> listMostView = (ArrayList<News>) newsDAO.getItemsMostView();
                		if(listMostView.size()>0){
                			for(News objNews:listMostView){
                				String urlSEONew = request.getContextPath()+"/"+SlugUtil.makeSlug(objNews.getCat_name())+"/"+objNews.getCat_id()+"/"+SlugUtil.makeSlug(objNews.getName())+"/"+objNews.getId()+".html";
                %>
                    <article class="twoboxes">
                        <div class="post-thumb">
                            <a href="<%=urlSEONew%>"><img style="width: 112px; height: 57px;" src="<%=request.getContextPath() %>/files/<%=objNews.getPicture() %>" alt=""></a>
                            <div class="overlay">
                                <div class="op"></div>
                                <div class="cat">
                                    <div class="icon"></div>
                                </div>
                            </div>
                        </div>
                        <div class="right-desc">
                            <h3><a href="<%=urlSEONew%>"><%=objNews.getName()%></a></h3>
                            <ul class="rating calc">
                                <li class="active nr_1"><a href="javascript:void(0)"></a></li>
                                <li class="active nr_2"><a href="javascript:void(0)"></a></li>
                                <li class="active nr_3"><a href="javascript:void(0)"></a></li>
                                <li class="nr_4"><a href="javascript:void(0)"></a></li>
                                <li class="nr_5"><a href="javascript:void(0)"></a></li>
                            </ul>
                            <div class="clear"></div>
                            <p class="date"><%=objNews.getDate_create() %></p>    
                        </div>
                        <div class="clear"></div>
                    </article>
                    <%}} %>
                    
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="footer-bottom">
            <div class="copyright">
                <p>Copyright 2017 @ <span>VINAENTER</span>Make by H2Gunner</p>
            </div>
            <div class="clear"></div>
        </div>  
    </footer>
    <!-- end footer -->
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery-1.8.3.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/html5shiv.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/fancydropdown.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery.easing-1.3.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/functions.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery.quicksand.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/main.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/scrolltopcontrol.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/sudo.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/contentslider.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery.nivo.slider.pack.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery.countdown.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery.validate.pack.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/misc.js"></script>
<script type="text/javascript">
    /* <![CDATA[ */
    /*global $:false */
    $(function() {
        "use strict";
        //caching
        //next and prev buttons
        var $cn_next = $('#cn_next');
        var $cn_prev    = $('#cn_prev');
        //wrapper of the left items
        var $cn_list    = $('#cn_list');
        var $pages      = $cn_list.find('.cn_page');
        //how many pages
        var cnt_pages   = $pages.length;
        //the default page is the first one
        var page        = 1;
        //list of news (left items)
        var $items      = $cn_list.find('.cn_item');
        //the current item being viewed (right side)
        var $cn_preview = $('#cn_preview');
        //index of the item being viewed. 
        //the default is the first one
        var current     = 1;
        /*
        for each item we store its index relative to all the document.
        we bind a click event that slides up or down the current item
        and slides up or down the clicked one. 
        Moving up or down will depend if the clicked item is after or
        before the current one
        */
        $items.each(function(i){
            var $item = $(this);
            $item.data('idx',i+1);
            
            $item.bind('click',function(){
                var $this       = $(this);
                $cn_list.find('.selected').removeClass('selected');
                $this.addClass('selected');
                var idx         = $(this).data('idx');
                var $current    = $cn_preview.find('.cn_content:nth-child('+current+')');
                var $next       = $cn_preview.find('.cn_content:nth-child('+idx+')');
                
                if(idx > current){
                    $current.stop().animate({'top':'-357px'},600,'easeOutBack',function(){
                        $(this).css({'top':'357px'});
                    });
                    $next.css({'top':'357px'}).stop().animate({'top':'0px'},600,'easeOutBack');
                }
                else if(idx < current){
                    $current.stop().animate({'top':'357px'},600,'easeOutBack',function(){
                        $(this).css({'top':'357px'});
                    });
                    $next.css({'top':'-357px'}).stop().animate({'top':'0px'},600,'easeOutBack');
                }
                current = idx;
            });
        });
        /*
        shows next page if exists:
        the next page fades in
        also checks if the button should get disabled
        */
        $cn_next.bind('click',function(e){
            var $this = $(this);
            $cn_prev.removeClass('disabled');
            ++page;
            if(page === cnt_pages)
                { $this.addClass('disabled'); }
            if(page > cnt_pages){ 
                page = cnt_pages;
                return;
            }   
            $pages.hide();
            $cn_list.find('.cn_page:nth-child('+page+')').fadeIn();
            e.preventDefault();
        });
        /*
        shows previous page if exists:
        the previous page fades in
        also checks if the button should get disabled
        */
        $cn_prev.bind('click',function(e){
            var $this = $(this);
            $cn_next.removeClass('disabled');
            --page;
            if(page === 1)
                { $this.addClass('disabled'); }
            if(page < 1){ 
                page = 1;
                return;
            }
            $pages.hide();
            $cn_list.find('.cn_page:nth-child('+page+')').fadeIn();
            e.preventDefault();
        });
    });
    /* ]]> */
</script> 

</body>


</html>