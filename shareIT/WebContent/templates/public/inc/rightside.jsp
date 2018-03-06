<%@page import="util.SlugUtil"%>
<%@page import="model.dao.CommentDAO"%>
<%@page import="model.bean.Video"%>
<%@page import="model.dao.VideoDAO"%>
<%@page import="model.bean.News"%>
<%@page import="model.dao.NewsDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CatDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
                <div class="right-container">
                    <div class="sidebar">
                        <div class="widget">
                            <div class="marked-title">
                                <h3>New Video</h3>
                            </div>
                            <%
                            	VideoDAO videoDAO = new VideoDAO();
                            	ArrayList<Video> listVideo = (ArrayList<Video>) videoDAO.getItemsByNumber();
                            %>
                            <div id="video-youtube">
									<iframe   width="343" height="225" src="<%=listVideo.get(0).getLink() %>" frameborder="0" allowfullscreen></iframe>
								</div>
                            <div class="clear"></div>
                            <div class="tab-content">
                                <div class="tab-pane active" id="tab-1">
                                    <div class="news">
                                    <%
                                    	for (Video objVideo : listVideo){
                                    %>
                                        <div class="item">
                                            <h3><a  onclick="showVideo(<%=objVideo.getId() %>)"  href="javascript:void(0)"><%=objVideo.getName() %></a></h3>
                                            <p><%=objVideo.getDate_create() %> // <%=objVideo.getView() %></p>
                                            <div class="clear"></div>
                                        </div>
                                        <%} %>
                                        <div class="item" >
                                        <%
                                        	String URLSeo = request.getContextPath()+"/video.html";
                                        %>
                                            <button class=" btn btn-warning"><a href="<%=URLSeo%>">-----More Video-----</a></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                       <div class="widget">
                        	<%
                        		CatDAO categoryDao = new CatDAO();
                        		Category objLapTrinh = (Category) categoryDao.checkCatName("Lập trình");
                        		ArrayList<Category> listCatChil = (ArrayList<Category>) categoryDao.getItemsChil(objLapTrinh.getId());
                        	%>
                            <ul class="nav nav-tabs" id="myTab">
                            <%
                            	for (Category objCatChil : listCatChil){
                            %>
                                <li><a href="#tab-<%=objCatChil.getId() %>" data-toggle="tab"><%=objCatChil.getName() %></a></li>
                                <%} %>
                            </ul>
                            <div class="tab-content">
                            <%
                            for (Category objCatChil : listCatChil){
                            %>
                                <div class="tab-pane active" id="tab-<%=objCatChil.getId()%>">
                                    <div class="news">
                                    <%
                                    NewsDAO newsDAO = new NewsDAO();
                                    CommentDAO commentDAO = new CommentDAO();
                                    ArrayList<News> listNewsChil = (ArrayList<News>) newsDAO.getItemsByIDCat(objCatChil.getId(), 0, 4);
                                    if(listNewsChil.size()>0){
                                    	for (News objNewsChil : listNewsChil){
                                    		 String urlSEOChil = request.getContextPath()+"/"+SlugUtil.makeSlug(objNewsChil.getCat_name())+"/"+objNewsChil.getCat_id()+"/"+SlugUtil.makeSlug(objNewsChil.getName())+"/"+objNewsChil.getId()+".html";
                                    		int numberComment = (Integer) commentDAO.countCommentByIdNews(objNewsChil.getId());
                                    %>
                                        <div class="item">
                                            <h3><a href="<%=urlSEOChil%>"><%=objNewsChil.getName() %></a></h3>
                                            <p><%=objNewsChil.getDate_create() %> // <%=objNewsChil.getCat_name() %> // <%=numberComment %> -comments</p>
                                            <ul class="rating calc">
                                                <li class="active nr_1"><a href="javascript:void(0)"></a></li>
                                                <li class="active nr_2"><a href="javascript:void(0)"></a></li>
                                                <li class="active nr_3"><a href="javascript:void(0)"></a></li>
                                                <li class="nr_4"><a href="javascript:void(0)"></a></li>
                                                <li class="nr_5"><a href="javascript:void(0)"></a></li>
                                            </ul>
                                            <div class="clear"></div>
                                        </div>
                                        <%}}%>
                                    </div>
                                </div>
                                <%}%>
                            </div>
                            <div class="clear"></div>
							<script>
								function  showVideo(id) {
									$.ajax({
										url: '<%=request.getContextPath()%>/video/show',
										type: 'POST',
										cache: false,
										data: {
												//Dữ liệu gửi đi
												vid : id,
												},
										success: function(data){
											// Xử lý thành công
											$('#video-youtube').html(data);
										},
										error: function (){
										// Xử lý nếu có lỗi
										alert('Loi');
										}
									});
								}
							</script>
                        </div>
                    </div>
                </div>