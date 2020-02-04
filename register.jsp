<!DOCTYPE html>
 <html>
 <head>
 <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <meta name="description" content="">
 <meta name="author" content="">

 <title>Register</title>

 <!-- Bootstrap Core CSS -->
 <link href="css/bootstrap.min.css" rel="stylesheet">

 <!-- Theme CSS -->
 <link href="css/freelancer.min.css" rel="stylesheet">
 <link href="css/Registration.css" rel="stylesheet">
 <link rel="stylesheet" href="css/custom.css">

 <!-- Custom Fonts -->
 <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
 <!--<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
 <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">-->
 
 <script src="js/jquery-1.12.3.min.js"></script>
 <script src="js/bootstrap.min.js"></script>
 <script src="js/ragistervalidate.js" type="application/javascript"></script>

 </head>

 <body id="page-top" class="index" >
 <div class="container">
 <!-- Navigation -->
 <nav id="mainNav" class="navbar navbar-default navbar-fixed-top navbar-custom">

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
                     <a href="#page-top">Registration</a>
                 </li>
                 <li class="page-scroll">
                     <a href="Login.jsp">Login</a>
                 </li>
                 <li class="page-scroll">
                     <a href="contactus.jsp">Contact us</a>
                 </li>
             </ul>
         </div>
      
         </nav>
         </div>   <!-- /.navbar-collapse -->
     </div>
     <!-- /.container-fluid -->
<div id="banner">
       <img src="images/registration-image.jpg" alt="banner">
   </div>
 <section id="portfolio">
     <div class="container">
         <div class="row">
             <div class="col-lg-12 text-center">
                 <h2>REGISTRATION FORM</h2>
                 <hr>
                  </div>
                 <!-- / form starts
                 <p>Use tab keys to move from one input field to the next.</p>-->
             <form action="registerServlet" method="post" enctype="multipart/form-data" class="form-signin" name="myForm" onsubmit="return validate();">
                 <!-- <h2 class="form-signin-heading">Please sign in</h2>
                 <br>-->
             <input type="text"   name="username" class="form-control" placeholder="Type Your User Name" required="required"/>
             <input type="text" name="inputname" class="form-control" placeholder="Type Your Name"/>
             <div class="radiobutton">
             <input type="radio" name="gender" value="male" checked> Male
             <input type="radio" name="gender" value="female"> Female
             </div>  
             <input type="date" class="form-control" name="Age"/>
             
             <input type="email" class="form-control" name="email" placeholder="Email address"/>
             
             <input type="password" class="form-control" name="password" placeholder="Password"/>
             
             <input type="password" class="form-control" name="confirmpassword" placeholder="Confirm Password"/>
             
             <input type="tel" class="form-control" name="mobileno" placeholder="Contact No."/>
             <div class="form-control image-file">
             select Image <input type="file" name="filePath">
             
             </div>
             
             <input type="textarea" class="form-control" name="address" placeholder="Address"/>
           
             <input type="text" class="form-control" name="institute" placeholder="Institute Name"/>
                  <div class="checkbox">
                                    <input type="submit" class="btn btn-primary btn-block btn-large" value="Register"/>
                    </div>
                    <h2>
                    <% String error="";
        		if(request.getParameter("error")!=null){
        			error=request.getParameter("error");
        		}
        %>
        <p style="color: white"><%=error %></p>
        </h2>
              </form>
         </div>
     </div>
  </section> 
  <footer class="text-center" id="contactus">
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