<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/templates/public/inc/header.jsp" %>
        <!-- end header -->
        
        <!-- start div #main-title -->
        <div class="main-title">
            <p>Contact</p>
        </div>
        <!-- end div #main-title -->
                
		<!-- start div #main -->
	    <div id="main">
            <div class="main-content contact">
            	<!-- <div class="contact-iframe">
                    <iframe height="480" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d958.4831807215268!2d108.14629272918327!3d16.068980599305068!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3142192a440b9b29%3A0x6aeae487f356e6c5!2zTmluaCBU4buRbiwgSMOyYSBLaMOhbmggQuG6r2MsIExpw6puIENoaeG7g3UsIMSQw6AgTuG6tW5nLCBWaeG7h3QgTmFt!5e0!3m2!1svi!2s!4v1504791230220"></iframe>   
                </div> -->
                <div class="infos">
                	<h1>H2new@vinaenter.com</h1>
                    <h2>H2New // (+84) 0981-615-773 // Liên Chiểu, Đà Nẵng</h2>
                </div>
                <div class="contact-content">
                    <div class="marked-title">
                        <h3>Your <span>Mail</span></h3>
                    </div>
                    <div class="contact-form" id="alert_contact">
                        <form >
                            <div class="top-form">
                                <span class="parent name">
                                    <input class="field" type="text" name=""  placeholder="Your Name" id="name_contact">
                                    <span class="icon"></span>    
                                </span>
                                <span class="parent email">
                                    <input class="field" type="text" name="" placeholder="Your Email" id="email_contact">
                                    <span class="icon"></span>    
                                </span>
                                <span class="parent web last">
                                    <input class="field" type="text" name="" placeholder="Your Website" id="website_contact">
                                    <span class="icon"></span>    
                                </span>
                                <div class="clear"></div>
                            </div>
                            <div class="bottom-form">
                                <textarea placeholder="Enter Your Message" id="content_contact"></textarea>
                            </div>
                            <button class="btn btn-icon submit" type="submit"><span class="icon"></span><a href="javascript:void(0)" onclick="contact()" style="color: white;">Sent</a></button>
                        </form>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>	
        </div>
	    <!-- end div #main -->
    <script>
				function  contact() {
					var name = $('#name_contact').val();
					var email = $('#email_contact').val();
					var website = $('#website_contact').val();
					var content = $('#content_contact').val();
					$.ajax({
						url: '<%=request.getContextPath()%>/contact/alert',
						type: 'POST',
						cache: false,
						data: {
								//Dữ liệu gửi đi
								cnam:name,
								cemail: email,
								cwebsite: website,
								ccontent: content,
								},
						success: function(data){
							// Xử lý thành công
							$('#alert_contact').html(data);
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

    <%@include file="/templates/public/inc/footer.jsp" %>