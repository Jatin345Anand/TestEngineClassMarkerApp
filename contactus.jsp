<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contact us:</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/contectus.css"></link>
    <script type="text/javascript" src="js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <style>
    </style>
</head>
<body>
   <nav id="mainNav" class="navbar navbar-default navbar-fixed-top navbar-custom colornav">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>Menu<i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand" href="index.jsp">Online Test Engine</a>
        </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="page-scroll">
                    <a href="index.jsp">Home</a>
                </li>
                <li class="page-scroll">
                    <a href="explore.jsp">Explore</a>
                </li>
                <li class="page-scroll">
                    <a href="register.jsp">Registration</a>
                </li>
                <li class="page-scroll">
                    <a href="Login.jsp">Login</a>
                </li>
                <li class="page-scroll dropdown">
                    <a href="#page-top">Contact us</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<section class="col-lg-12">
<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-4">
            <div class="well-sm">
                <form class="form-horizontal wellborder col-lg-offset-1" method="post" action="contactUsServlet">
                    <fieldset>
                        <legend class="text-center header">Contact us</legend>
                        <div class="form-group">
                            <div class="col-md-10 col-md-offset-1">
                                <input id="fname" name="name" type="text" placeholder="Full Name" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-10 col-md-offset-1">
                                <input id="lname" name="phone" type="text" placeholder="phone" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-10 col-md-offset-1">
                                <input id="email" name="email" type="text" placeholder="Email Address" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-10 col-md-offset-1">
                                <input id="phone" name="subject" type="text" placeholder="Subject" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-10 col-md-offset-1">
                                <textarea class="form-control" id="message" name="message" placeholder="Enter your massage for us here. We will get back to you within 2 business days." rows="7"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12 text-center">
                                <button type="submit" class="btn btn-primary btn-lg">Submit</button>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <div class="col-md-12 text-center">            
                     <% String status="";
        		if(request.getParameter("status")!=null){
        			status=request.getParameter("status");
        		}
        %>
              
              <h3><p style="color: black; font-size: 18px; font-weight: 700; margin-top: 25px; margin-right: 300px; capitalize; text-align: center;"><%=status %></p></h3>
		
                        </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
        <div class="col-lg-6">
            <div>
                <div class="panel1">
                    <legend class="text-center header">My Home</legend>
                    <div class="panel-body text-center">
                        <div>
                        A-1, Dheeraj Vihar<br />
                        Jain Nager, Karala<br />
                        #(91) 8447698386<br />
                        vishkarmasunil@gmail.com<br />
                        </div>
                        <div id="map1" class="map">
                        </div>
                    </div>
                </div>
            </div>
        </div>
       </div>
      </div> 
</section>
<div class="clearfix"></div>
<footer>
	<div class="footer-above">
        <div class="container">
                <div class="footer-col col-md-4">
                    <h3>Location</h3>
                    <p>A-1, Dheeraj Vihar, Karala,
                        <br>New Delhi 110081, INDIA</p>
                </div>
                <div class="footer-col col-md-4">
                    <h3>Around the Web</h3>
                    <ul class="list-inline">
                        <li>
                            <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-facebook"></i></a>
                        </li>
                        <li>
                            <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-google-plus"></i></a>
                        </li>
                        <li>
                            <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-twitter"></i></a>
                        </li>
                        <li>
                            <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-linkedin"></i></a>
                        </li>
                        <li>
                            <a href="#" class="btn-social btn-outline"><i class="fa fa-fw fa-dribbble"></i></a>
                        </li>
                    </ul>
                </div>
                <div class="footer-col col-md-4">
                    <h3>About Online Test Engine</h3>
					<p>Online Test Engine Provide online examination facility to collage students.</a></p>
                </div>
            </div>
    </div>
    <div class="footer-below">
        <div class="container">
                <div class="col-lg-12">
                    Copyright &copy; Your Website 2016
                </div>
        </div>
    </div>
</footer>
<script src="http://maps.google.com/maps/api/js?key=AIzaSyCjUThmTZizsurMhYWHOPDGlaNZcFknXIU"></script>
<script src="js/map.js" type="application/javascript"></script>
</body>
</html>