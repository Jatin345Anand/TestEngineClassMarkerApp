<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login Form</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/freelancer.min.css" rel="stylesheet" type="text/css">
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
   <link rel="stylesheet" href="css/forgetpassword.css">
   <script type="application/javascript" src="js/jquery-1.12.3.min.js"></script>
<script type="application/javascript" src="js/bootstrap.min.js"></script>
  </head>
  <body class="loginpage">
   <nav id="mainNav" class="navbar navbar-default navbar-fixed-top navbar-custom">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span> Menu <i class="fa fa-bars"></i>
            </button>
            
            <a class="navbar-brand" href="index.jsp">Online Test Engine</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
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
                <li class="page-scroll">
                    <a href="contactus.jsp">Contact us</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container-fluid -->
</nav>
<div id="banner">
       <img src="images/login-background.jpg" alt="banner">
   </div>
<div class="login">
	<h1>Forget Password</h1>
    <form method="post" action="sendPasswordServlet">
        <label id="labelstyle" for="">EMAIL</label>
    	<input type="email" name="email" placeholder="Enter Your Email....." required/>
        <button type="submit" class="btn btn-primary btn-block btn-large">Send Password</button>
        
          <% String status="";
        		if(request.getParameter("status")!=null){
        			status=request.getParameter("status");
        		}
        %>
              
              <h3><p style="color: black; font-size: 18px; font-weight: 700; margin-top: 25px; margin-right: 300px; capitalize; text-align: center;"><%=status %></p></h3>
		
    </form>
</div>
   <footer class="text-center" id="logincontactus">
    <div class="footer-above">
        <div class="container">
            <div class="row">
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
    </div>
    <div class="footer-below">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    Copyright &copy; Your Website 2016
                </div>
            </div>
        </div>
    </div>
</footer>
  </body>
</html>